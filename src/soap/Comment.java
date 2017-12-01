
package soap;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for Comment complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="Comment">
 *   &lt;complexContent>
 *     &lt;extension base="{http://veebiteenused.ttu.ee}Tweet">
 *       &lt;sequence>
 *         &lt;element name="TweetID" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Comment", propOrder = {
    "tweetID"
})
public class Comment
    extends Tweet
{

    @XmlElement(name = "TweetID", required = true)
    protected String tweetID;

    /**
     * Gets the value of the tweetID property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTweetID() {
        return tweetID;
    }

    /**
     * Sets the value of the tweetID property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTweetID(String value) {
        this.tweetID = value;
    }

}
