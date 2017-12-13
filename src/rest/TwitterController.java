package rest;

import service.TwitterService;
import soap.*;

import javax.enterprise.context.RequestScoped;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;
import java.util.Optional;

/**
 * Created by ANDREAS on 03.12.2017.
 */

@Path("twitter")
@RequestScoped
public class TwitterController {

    @Context
    private UriInfo context;

    TwitterService service;

    /**
     * Creates a new instance of InvoicesResource
     */
    public TwitterController() {
        service = new TwitterService();
    }

    @GET
    @Path("/tweet/{id}")
    @Produces("application/json")
    public Tweet getTweet(@PathParam("id") String id,
                          @QueryParam("token") String token) {
        return service.getTweet(token, id);
    }

    @POST
    @Path("/tweet")
    @Consumes("application/json")
    @Produces("application/json")
    public Tweet addTweet(AddTweetRequest addTweetRequest, @QueryParam("token") String token) {
        return service.addTweet(token, addTweetRequest.getMessage(), addTweetRequest.getOwnerID());
    }

    @GET
    @Path("/comment/{id}")
    @Produces("application/json")
    public Tweet getComment(@PathParam("id") String id,
                          @QueryParam("token") String token) {
        return service.getComment(token, id);
    }

    @GET
    @Path("/tweets/{userId}")
    @Produces("application/json")
    public List<Tweet> getTweets(@PathParam("userId") String userId, @QueryParam("token") String token,
                                 @DefaultValue("") @QueryParam("start") String startDateString,
                                 @DefaultValue("") @QueryParam("end") String endDateString) {
        //http://localhost:8080/soap_api/webresources/twitter/tweets/0d4b975e-edfc-4ee9-a3d5-a3eff954fe20?token=qwerty&start=2017-10-10%2012%3A00%3A00&end=2017-12-15%2012%3A00%3A00
        Optional<LocalDateTime> start;
        Optional<LocalDateTime> end;
        if (startDateString.equals("")) {
            start = Optional.empty();
        } else {
            start = Optional.of(LocalDateTime.parse(startDateString, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
        }

        if (endDateString.equals("")) {
            end = Optional.empty();
        } else {
            end = Optional.of(LocalDateTime.parse(endDateString, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
        }

        return service.getTweets(token, userId, start, end);
    }


    @GET
    @Path("/comments/{userId}")
    @Produces("application/json")
    public List<Comment> getComments(@PathParam("userId") String userId, @QueryParam("token") String token,
                                     @DefaultValue("") @QueryParam("start") String startDateString,
                                     @DefaultValue("") @QueryParam("end") String endDateString) {
        Optional<LocalDateTime> start;
        Optional<LocalDateTime> end;
        if (startDateString.equals("")) {
            start = Optional.empty();
        } else {
            start = Optional.of(LocalDateTime.parse(startDateString, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
        }

        if (endDateString.equals("")) {
            end = Optional.empty();
        } else {
            end = Optional.of(LocalDateTime.parse(endDateString, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
        }

        return service.getComments(token, userId, start, end);
    }

    @POST
    @Path("/comment")
    @Consumes("application/json")
    @Produces("application/json")
    public TweetWithComments addCommentToTweet(AddCommentToTweetRequest request, @QueryParam("token") String token) {
        return service.addCommentToTweet(token, request.getMessage(), request.getOwnerID(), request.getTweetID());
    }

    @GET
    @Path("/tweet/{id}/comments")
    @Consumes("application/json")
    @Produces("application/json")
    public TweetWithComments getTweetComments(@PathParam("id") String id, @QueryParam("token") String token) {
        return service.getTweetComments(token, id);
    }


}
