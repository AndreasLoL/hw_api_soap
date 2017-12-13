package service;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.rethinkdb.RethinkDB;
import com.rethinkdb.net.Connection;
import com.rethinkdb.net.Cursor;
import com.sun.org.apache.xerces.internal.jaxp.datatype.XMLGregorianCalendarImpl;
import soap.*;
import utils.XMLGregorianCalendarConverter;

import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;
import java.time.LocalDateTime;
import java.util.*;

/**
 * Created by ANDREAS on 03.12.2017.
 */
public class TwitterService {

    public static final RethinkDB r = RethinkDB.r;

    public static Connection.Builder connBuilder;

    public List<String> tokens;
    public Gson gson;

    public ValidationService validationService;

    public TwitterService() {
        connBuilder = r.connection().hostname("localhost").port(28015);
        tokens = new ArrayList<>(Arrays.asList("asd123", "abc123", "qwerty"));
        gson = new GsonBuilder()
                .registerTypeAdapter(XMLGregorianCalendar.class, new XMLGregorianCalendarConverter.Deserializer())
                .registerTypeAdapter(XMLGregorianCalendar.class, new XMLGregorianCalendarConverter.Serializer())
                .create();

        validationService = new ValidationService();
    }

    private boolean tokenIsValid(String token) {
        return tokens.contains(token);
    }

    private XMLGregorianCalendar getCurrentDate() {
        try {
            GregorianCalendar gregorianCalendar = new GregorianCalendar();
            DatatypeFactory datatypeFactory = DatatypeFactory.newInstance();
            return datatypeFactory.newXMLGregorianCalendar(gregorianCalendar);
        } catch (DatatypeConfigurationException e) {
            e.printStackTrace();
        }
        return new XMLGregorianCalendarImpl();
    }

    public Tweet getTweet(String token, String id) {

        if (tokenIsValid(token)) {

            Connection conn = connBuilder.connect();

            Cursor c = r.db("twitter").table("tweet").filter(r.hashMap("id", id)).run(conn);
            for (Object tweet : c) {
                JsonElement jsonElement = gson.toJsonTree(tweet);
                conn.close();
                return gson.fromJson(jsonElement, Tweet.class);
            }
        }

        return null;
    }

    public Tweet addTweet(String token, String message, String ownerId) {
        if (tokenIsValid(token) && validationService.tweetIsValid(message)) {

            Connection conn = connBuilder.connect();

            r.db("twitter").table("tweet").insert(r.array(
                    r.hashMap("message", message)
                            .with("ownerID", ownerId)
                            .with("creationDate", getCurrentDate().toString())
            )).run(conn);

            Tweet tweet = new Tweet();
            tweet.setCreationDate(getCurrentDate());
            tweet.setID("");
            tweet.setMessage(message);
            tweet.setOwnerID(ownerId);

            conn.close();
            return tweet;
        }

        return null;
    }

    public Comment getComment(String token, String commentId) {
        if (tokenIsValid(token)) {

            Connection conn = connBuilder.connect();

            Cursor c = r.db("twitter").table("comment").filter(r.hashMap("id", commentId)).run(conn);
            for (Object comment : c) {
                JsonElement jsonElement = gson.toJsonTree(comment);
                conn.close();
                return gson.fromJson(jsonElement, Comment.class);
            }
        }
        return null;
    }


    public List<Tweet> getTweets(String token, String userId, Optional<LocalDateTime> startDate, Optional<LocalDateTime> endDate) {
        if (tokenIsValid(token)) {
            Connection conn = connBuilder.connect();

            List<Tweet> tweets = new ArrayList<>();
            Cursor c = r.db("twitter").table("tweet").filter(r.hashMap("ownerID", userId)).run(conn);
            for (Object tweet : c) {
                JsonElement jsonElement = gson.toJsonTree(tweet);
                Tweet castedTweet = gson.fromJson(jsonElement, Tweet.class);

                if (bothDatesExist(startDate, endDate)) {
                    LocalDateTime tweetDate = castedTweet.getCreationDate().toGregorianCalendar().toZonedDateTime().toLocalDateTime();
                    if (objectIsInDateRange(startDate, endDate, tweetDate)) {
                        tweets.add(castedTweet);
                    }
                } else if (startDate.isPresent()) {
                    LocalDateTime tweetDate = castedTweet.getCreationDate().toGregorianCalendar().toZonedDateTime().toLocalDateTime();
                    if (tweetDate.isAfter(startDate.get())) {
                        tweets.add(castedTweet);
                    }
                } else if (endDate.isPresent()) {
                    LocalDateTime tweetDate = castedTweet.getCreationDate().toGregorianCalendar().toZonedDateTime().toLocalDateTime();
                    if (tweetDate.isBefore(endDate.get())) {
                        tweets.add(castedTweet);
                    }
                } else {
                    tweets.add(castedTweet);
                }
            }
            conn.close();
            return tweets;
        }

        return null;
    }

    private boolean objectIsInDateRange(Optional<LocalDateTime> startDate, Optional<LocalDateTime> endDate, LocalDateTime commentDate) {
        return startDate.get().isBefore(commentDate) && endDate.get().isAfter(commentDate);
    }

    private boolean bothDatesExist(Optional<LocalDateTime> startDate, Optional<LocalDateTime> endDate) {
        return startDate.isPresent() && endDate.isPresent();
    }

    public List<Comment> getComments(String token, String userId, Optional<LocalDateTime> startDate, Optional<LocalDateTime> endDate) {
        if (tokenIsValid(token)) {
            Connection conn = connBuilder.connect();

            List<Comment> comments = new ArrayList<>();
            Cursor c = r.db("twitter").table("comment").filter(r.hashMap("ownerID", userId)).run(conn);

            for (Object comment : c) {
                JsonElement jsonElement = gson.toJsonTree(comment);
                Comment castedComment = gson.fromJson(jsonElement, Comment.class);
                if (bothDatesExist(startDate, endDate)) {
                    LocalDateTime commentDate = castedComment.getCreationDate().toGregorianCalendar().toZonedDateTime().toLocalDateTime();
                    if (objectIsInDateRange(startDate, endDate, commentDate)) {
                        comments.add(castedComment);
                    }
                } else if (startDate.isPresent()) {
                    LocalDateTime commentDate = castedComment.getCreationDate().toGregorianCalendar().toZonedDateTime().toLocalDateTime();
                    if (commentDate.isAfter(startDate.get())) {
                        comments.add(castedComment);
                    }
                } else if (endDate.isPresent()) {
                    LocalDateTime commentDate = castedComment.getCreationDate().toGregorianCalendar().toZonedDateTime().toLocalDateTime();
                    if (commentDate.isBefore(endDate.get())) {
                        comments.add(castedComment);
                    }

                } else {
                    System.out.println("Adding all");
                    comments.add(castedComment);
                }
            }

            conn.close();
            return comments;
        }

        return null;
    }

    public TweetWithComments addCommentToTweet(String token, String message, String ownerId, String tweetId) {
        if (tokenIsValid(token) && validationService.tweetIsValid(message)) {
            Connection conn = connBuilder.connect();

            Tweet tweet = getTweet(token, tweetId);

            if (tweet == null) {
                return null;
            }

            r.db("twitter").table("comment").insert(r.array(
                    r.hashMap("message", message)
                            .with("ownerID", ownerId)
                            .with("creationDate", getCurrentDate().toString())
                            .with("tweetID", tweetId)
            )).run(conn);

            List<Comment> comments = new ArrayList<>();
            Cursor c = r.db("twitter").table("comment").filter(r.hashMap("tweetID", tweetId)).run(conn);

            for (Object comment : c) {
                JsonElement jsonElement = gson.toJsonTree(comment);
                comments.add(gson.fromJson(jsonElement, Comment.class));
            }

            TweetWithComments tweetWithComments = new TweetWithComments();
            tweetWithComments.setComments(new TweetWithComments.Comments());
            tweetWithComments.getComments().getComment().addAll(comments);
            tweetWithComments.setCreationDate(tweet.getCreationDate());
            tweetWithComments.setMessage(tweet.getMessage());
            tweetWithComments.setOwnerID(tweet.getOwnerID());
            tweetWithComments.setID(tweet.getID());

            conn.close();
            return tweetWithComments;
        }

        return null;
    }

    public TweetWithComments getTweetComments(String token, String tweetId) {
        if (tokenIsValid(token)) {
            Connection conn = connBuilder.connect();

            Tweet tweet = getTweet(token, tweetId);

            if (tweet == null) {
                return null;
            }

            List<Comment> comments = new ArrayList<>();
            Cursor c = r.db("twitter").table("comment").filter(r.hashMap("tweetID", tweetId)).run(conn);

            for (Object comment : c) {
                JsonElement jsonElement = gson.toJsonTree(comment);
                comments.add(gson.fromJson(jsonElement, Comment.class));
            }

            TweetWithComments tweetWithComments = new TweetWithComments();
            tweetWithComments.setComments(new TweetWithComments.Comments());
            tweetWithComments.getComments().getComment().addAll(comments);
            tweetWithComments.setCreationDate(tweet.getCreationDate());
            tweetWithComments.setMessage(tweet.getMessage());
            tweetWithComments.setOwnerID(tweet.getOwnerID());
            tweetWithComments.setID(tweet.getID());

            conn.close();
            return tweetWithComments;
        }

        return null;

    }



}
