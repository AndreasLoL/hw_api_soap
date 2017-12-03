package rest;

import service.TwitterService;
import soap.*;

import javax.enterprise.context.RequestScoped;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import java.math.BigInteger;
import java.util.List;

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
    @Path("/tweets")
    @Produces("application/json")
    public List<Tweet> getTweets(@QueryParam("token") String token) {
        return service.getTweets(token);
    }


    @GET
    @Path("/comments/{userId}")
    @Produces("application/json")
    public List<Comment> getComments(@PathParam("userId") String userId, @QueryParam("token") String token) {
        return service.getComments(token, userId);
    }

    @POST
    @Path("/comment")
    @Consumes("application/json")
    @Produces("application/json")
    public TweetWithComments addCommentToTweet(AddCommentToTweetRequest request, @QueryParam("token") String token) {
        return service.addCommentToTweet(token, request.getMessage(), request.getOwnerID(), request.getTweetID());
    }

    @POST
    @Path("/tweet/{id}?comments=true")
    @Consumes("application/json")
    @Produces("application/json")
    public TweetWithComments getTweetComments(@PathParam("id") String id, @QueryParam("token") String token) {
        return service.getTweetComments(token, id);
    }


}
