
package soap;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for TweetWithComments complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="TweetWithComments">
 *   &lt;complexContent>
 *     &lt;extension base="{http://veebiteenused.ttu.ee}Tweet">
 *       &lt;sequence>
 *         &lt;element name="Comments">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;sequence>
 *                   &lt;element name="Comment" type="{http://veebiteenused.ttu.ee}Comment" maxOccurs="unbounded" minOccurs="0"/>
 *                 &lt;/sequence>
 *               &lt;/restriction>
 *             &lt;/complexContent>
 *           &lt;/complexType>
 *         &lt;/element>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "TweetWithComments", propOrder = {
    "comments"
})
public class TweetWithComments
    extends Tweet
{

    @XmlElement(name = "Comments", required = true)
    protected TweetWithComments.Comments comments;

    /**
     * Gets the value of the comments property.
     * 
     * @return
     *     possible object is
     *     {@link TweetWithComments.Comments }
     *     
     */
    public TweetWithComments.Comments getComments() {
        return comments;
    }

    /**
     * Sets the value of the comments property.
     * 
     * @param value
     *     allowed object is
     *     {@link TweetWithComments.Comments }
     *     
     */
    public void setComments(TweetWithComments.Comments value) {
        this.comments = value;
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
     *         &lt;element name="Comment" type="{http://veebiteenused.ttu.ee}Comment" maxOccurs="unbounded" minOccurs="0"/>
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
        "comment"
    })
    public static class Comments {

        @XmlElement(name = "Comment")
        protected List<Comment> comment;

        /**
         * Gets the value of the comment property.
         * 
         * <p>
         * This accessor method returns a reference to the live list,
         * not a snapshot. Therefore any modification you make to the
         * returned list will be present inside the JAXB object.
         * This is why there is not a <CODE>set</CODE> method for the comment property.
         * 
         * <p>
         * For example, to add a new item, do as follows:
         * <pre>
         *    getComment().add(newItem);
         * </pre>
         * 
         * 
         * <p>
         * Objects of the following type(s) are allowed in the list
         * {@link Comment }
         * 
         * 
         */
        public List<Comment> getComment() {
            if (comment == null) {
                comment = new ArrayList<Comment>();
            }
            return this.comment;
        }

    }

}
