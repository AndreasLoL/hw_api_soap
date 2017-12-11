
package soap;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the soap package. 
 * <p>An ObjectFactory allows you to programatically 
 * construct new instances of the Java representation 
 * for XML content. The Java representation of XML 
 * content can consist of schema derived interfaces 
 * and classes representing the binding of schema 
 * type definitions, element declarations and model 
 * groups.  Factory methods for each of these are 
 * provided in this class.
 * 
 */
@XmlRegistry
public class ObjectFactory {

    private final static QName _GetTweetCommentsResponse_QNAME = new QName("http://veebiteenused.ttu.ee", "GetTweetCommentsResponse");
    private final static QName _GetCommentResponse_QNAME = new QName("http://veebiteenused.ttu.ee", "GetCommentResponse");
    private final static QName _AddTweetResponse_QNAME = new QName("http://veebiteenused.ttu.ee", "AddTweetResponse");
    private final static QName _AddCommentToTweetResponse_QNAME = new QName("http://veebiteenused.ttu.ee", "AddCommentToTweetResponse");
    private final static QName _GetTweetResponse_QNAME = new QName("http://veebiteenused.ttu.ee", "GetTweetResponse");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: soap
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link GetCommentsResponse }
     * 
     */
    public GetCommentsResponse createGetCommentsResponse() {
        return new GetCommentsResponse();
    }

    /**
     * Create an instance of {@link GetTweetsResponse }
     * 
     */
    public GetTweetsResponse createGetTweetsResponse() {
        return new GetTweetsResponse();
    }

    /**
     * Create an instance of {@link TweetWithComments }
     * 
     */
    public TweetWithComments createTweetWithComments() {
        return new TweetWithComments();
    }

    /**
     * Create an instance of {@link Tweet }
     * 
     */
    public Tweet createTweet() {
        return new Tweet();
    }

    /**
     * Create an instance of {@link AddCommentToTweetRequest }
     * 
     */
    public AddCommentToTweetRequest createAddCommentToTweetRequest() {
        return new AddCommentToTweetRequest();
    }

    /**
     * Create an instance of {@link GetCommentRequest }
     * 
     */
    public GetCommentRequest createGetCommentRequest() {
        return new GetCommentRequest();
    }

    /**
     * Create an instance of {@link GetTweetRequest }
     * 
     */
    public GetTweetRequest createGetTweetRequest() {
        return new GetTweetRequest();
    }

    /**
     * Create an instance of {@link GetCommentsResponse.Comments }
     * 
     */
    public GetCommentsResponse.Comments createGetCommentsResponseComments() {
        return new GetCommentsResponse.Comments();
    }

    /**
     * Create an instance of {@link GetTweetCommentsRequest }
     * 
     */
    public GetTweetCommentsRequest createGetTweetCommentsRequest() {
        return new GetTweetCommentsRequest();
    }

    /**
     * Create an instance of {@link GetTweetsRequest }
     * 
     */
    public GetTweetsRequest createGetTweetsRequest() {
        return new GetTweetsRequest();
    }

    /**
     * Create an instance of {@link GetTweetsResponse.Tweets }
     * 
     */
    public GetTweetsResponse.Tweets createGetTweetsResponseTweets() {
        return new GetTweetsResponse.Tweets();
    }

    /**
     * Create an instance of {@link GetCommentsRequest }
     * 
     */
    public GetCommentsRequest createGetCommentsRequest() {
        return new GetCommentsRequest();
    }

    /**
     * Create an instance of {@link AddTweetRequest }
     * 
     */
    public AddTweetRequest createAddTweetRequest() {
        return new AddTweetRequest();
    }

    /**
     * Create an instance of {@link Comment }
     * 
     */
    public Comment createComment() {
        return new Comment();
    }

    /**
     * Create an instance of {@link TweetWithComments.Comments }
     * 
     */
    public TweetWithComments.Comments createTweetWithCommentsComments() {
        return new TweetWithComments.Comments();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link TweetWithComments }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://veebiteenused.ttu.ee", name = "GetTweetCommentsResponse")
    public JAXBElement<TweetWithComments> createGetTweetCommentsResponse(TweetWithComments value) {
        return new JAXBElement<TweetWithComments>(_GetTweetCommentsResponse_QNAME, TweetWithComments.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Comment }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://veebiteenused.ttu.ee", name = "GetCommentResponse")
    public JAXBElement<Comment> createGetCommentResponse(Comment value) {
        return new JAXBElement<Comment>(_GetCommentResponse_QNAME, Comment.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Tweet }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://veebiteenused.ttu.ee", name = "AddTweetResponse")
    public JAXBElement<Tweet> createAddTweetResponse(Tweet value) {
        return new JAXBElement<Tweet>(_AddTweetResponse_QNAME, Tweet.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link TweetWithComments }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://veebiteenused.ttu.ee", name = "AddCommentToTweetResponse")
    public JAXBElement<TweetWithComments> createAddCommentToTweetResponse(TweetWithComments value) {
        return new JAXBElement<TweetWithComments>(_AddCommentToTweetResponse_QNAME, TweetWithComments.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Tweet }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://veebiteenused.ttu.ee", name = "GetTweetResponse")
    public JAXBElement<Tweet> createGetTweetResponse(Tweet value) {
        return new JAXBElement<Tweet>(_GetTweetResponse_QNAME, Tweet.class, null, value);
    }

}
