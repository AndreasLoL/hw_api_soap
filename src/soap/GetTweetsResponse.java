
package soap;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for anonymous complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType>
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="Tweets">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;sequence>
 *                   &lt;element name="Tweet" type="{http://veebiteenused.ttu.ee}Tweet" maxOccurs="unbounded" minOccurs="0"/>
 *                 &lt;/sequence>
 *               &lt;/restriction>
 *             &lt;/complexContent>
 *           &lt;/complexType>
 *         &lt;/element>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "tweets"
})
@XmlRootElement(name = "GetTweetsResponse")
public class GetTweetsResponse {

    @XmlElement(name = "Tweets", required = true)
    protected GetTweetsResponse.Tweets tweets;

    /**
     * Gets the value of the tweets property.
     * 
     * @return
     *     possible object is
     *     {@link GetTweetsResponse.Tweets }
     *     
     */
    public GetTweetsResponse.Tweets getTweets() {
        return tweets;
    }

    /**
     * Sets the value of the tweets property.
     * 
     * @param value
     *     allowed object is
     *     {@link GetTweetsResponse.Tweets }
     *     
     */
    public void setTweets(GetTweetsResponse.Tweets value) {
        this.tweets = value;
    }


    /**
     * <p>Java class for anonymous complex type.
     * 
     * <p>The following schema fragment specifies the expected content contained within this class.
     * 
     * <pre>
     * &lt;complexType>
     *   &lt;complexContent>
     *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
     *       &lt;sequence>
     *         &lt;element name="Tweet" type="{http://veebiteenused.ttu.ee}Tweet" maxOccurs="unbounded" minOccurs="0"/>
     *       &lt;/sequence>
     *     &lt;/restriction>
     *   &lt;/complexContent>
     * &lt;/complexType>
     * </pre>
     * 
     * 
     */
    @XmlAccessorType(XmlAccessType.FIELD)
    @XmlType(name = "", propOrder = {
        "tweet"
    })
    public static class Tweets {

        @XmlElement(name = "Tweet")
        protected List<Tweet> tweet;

        /**
         * Gets the value of the tweet property.
         * 
         * <p>
         * This accessor method returns a reference to the live list,
         * not a snapshot. Therefore any modification you make to the
         * returned list will be present inside the JAXB object.
         * This is why there is not a <CODE>set</CODE> method for the tweet property.
         * 
         * <p>
         * For example, to add a new item, do as follows:
         * <pre>
         *    getTweet().add(newItem);
         * </pre>
         * 
         * 
         * <p>
         * Objects of the following type(s) are allowed in the list
         * {@link Tweet }
         * 
         * 
         */
        public List<Tweet> getTweet() {
            if (tweet == null) {
                tweet = new ArrayList<Tweet>();
            }
            return this.tweet;
        }

    }

}
