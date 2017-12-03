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
import java.util.ArrayList;
import java.util.Arrays;
import java.util.GregorianCalendar;
import java.util.List;

/**
 * Created by ANDREAS on 03.12.2017.
 */
public class TwitterService {

    public static final RethinkDB r = RethinkDB.r;

    public static Connection.Builder connBuilder;

    public List<String> tokens;
    public Gson gson;

    public TwitterService() {
        tokens = new ArrayList<>(Arrays.asList("asd123", "abc123", "qwerty"));
        connBuilder = r.connection().hostname("localhost").port(28015);
        tokens = new ArrayList<>(Arrays.asList("asd123", "abc123", "qwerty"));
        gson = new GsonBuilder()
                .registerTypeAdapter(XMLGregorianCalendar.class, new XMLGregorianCalendarConverter.Deserializer())
                .registerTypeAdapter(XMLGregorianCalendar.class, new XMLGregorianCalendarConverter.Serializer())
                .create();
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
        if (tokenIsValid(token)) {

            Connection conn = connBuilder.connect();

            r.db("twitter").table("tweet").insert(r.array(
                    r.hashMap("message", message)
                            .with("ownerID", ownerId)
                            .with("creationDate", getCurrentDate().toString())
            )).run(conn);

            Tweet tweet = new Tweet();
            tweet.setCreationDate(getCurrentDate());
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


    public List<Tweet> getTweets(String token) {
        if (tokenIsValid(token)) {
            Connection conn = connBuilder.connect();

            List<Tweet> tweets = new ArrayList<>();
            Cursor c = r.db("twitter").table("tweet").run(conn);
            for (Object comment : c) {
                JsonElement jsonElement = gson.toJsonTree(comment);
                tweets.add(gson.fromJson(jsonElement, Tweet.class));
            }
            conn.close();
            return tweets;
        }

        return null;
    }

    public List<Comment> getComments(String token, String userId) {
        if (tokenIsValid(token)) {
            Connection conn = connBuilder.connect();

            List<Comment> comments = new ArrayList<>();
            Cursor c = r.db("twitter").table("comment").filter(r.hashMap("ownerID", userId)).run(conn);

            for (Object comment : c) {
                JsonElement jsonElement = gson.toJsonTree(comment);
                comments.add(gson.fromJson(jsonElement, Comment.class));
            }

            conn.close();
            return comments;
        }

        return null;
    }

    public TweetWithComments addCommentToTweet(String token, String message, String ownerId, String tweetId) {
        if (tokenIsValid(token)) {
            Connection conn = connBuilder.connect();

            r.db("twitter").table("comment").insert(r.array(
                    r.hashMap("message", message)
                            .with("ownerID", ownerId)
                            .with("creationDate", getCurrentDate().toString())
                            .with("tweetID", tweetId)
            )).run(conn);


            Tweet tweet = getTweet(token, tweetId);

            List<Comment> comments = new ArrayList<>();
            Cursor c = r.db("twitter").table("comment").filter(r.hashMap("tweetID", tweetId)).run(conn);

            for (Object comment : c) {
                JsonElement jsonElement = gson.toJsonTree(comment);
                comments.add(gson.fromJson(jsonElement, Comment.class));
            }

            TweetWithComments tweetWithComments = new TweetWithComments();
            tweetWithComments.comments = new TweetWithComments.Comments();
            tweetWithComments.comments.comment = comments;
            tweetWithComments.creationDate = tweet.getCreationDate();
            tweetWithComments.message = tweet.getMessage();
            tweetWithComments.ownerID = tweet.getOwnerID();
            tweetWithComments.id = tweet.getID();

            conn.close();
            return tweetWithComments;
        }

        return null;
    }

    public TweetWithComments getTweetComments(String token, String tweetId) {
        if (tokenIsValid(token)) {
            Connection conn = connBuilder.connect();

            Tweet tweet = getTweet(token, tweetId);

            List<Comment> comments = new ArrayList<>();
            Cursor c = r.db("twitter").table("comment").filter(r.hashMap("tweetID", tweetId)).run(conn);

            for (Object comment : c) {
                JsonElement jsonElement = gson.toJsonTree(comment);
                comments.add(gson.fromJson(jsonElement, Comment.class));
            }

            TweetWithComments tweetWithComments = new TweetWithComments();
            tweetWithComments.comments = new TweetWithComments.Comments();
            tweetWithComments.comments.comment = comments;
            tweetWithComments.creationDate = tweet.getCreationDate();
            tweetWithComments.message = tweet.getMessage();
            tweetWithComments.ownerID = tweet.getOwnerID();
            tweetWithComments.id = tweet.getID();

            conn.close();
            return tweetWithComments;
        }

        return null;

    }



}
