//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.8-b130911.1802 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2020.12.31 at 05:42:23 PM CET 
//


package it.cnr.irea.edi.template_manager.model.template.generated;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElements;
import javax.xml.bind.annotation.XmlID;
import javax.xml.bind.annotation.XmlIDREF;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.adapters.CollapsedStringAdapter;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;


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
 *         &lt;choice maxOccurs="unbounded">
 *           &lt;element ref="{}help"/>
 *           &lt;element ref="{}label"/>
 *           &lt;element ref="{}hasRoot"/>
 *         &lt;/choice>
 *         &lt;element ref="{}produces"/>
 *       &lt;/sequence>
 *       &lt;attribute name="isMandatory" use="required" type="{http://www.w3.org/2001/XMLSchema}boolean" />
 *       &lt;attribute name="isMultiple" use="required" type="{http://www.w3.org/2001/XMLSchema}boolean" />
 *       &lt;attribute ref="{http://www.w3.org/XML/1998/namespace}id use="required""/>
 *       &lt;attribute name="alternativeTo" type="{http://www.w3.org/2001/XMLSchema}IDREF" />
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "helpOrLabelOrHasRoot",
    "produces"
})
@XmlRootElement(name = "element")
public class Element {

    @XmlElements({
        @XmlElement(name = "help", type = Help.class),
        @XmlElement(name = "label", type = Label.class),
        @XmlElement(name = "hasRoot", type = String.class)
    })
    protected List<Object> helpOrLabelOrHasRoot;
    @XmlElement(required = true)
    protected Produces produces;
    @XmlAttribute(name = "isMandatory", required = true)
    protected boolean isMandatory;
    @XmlAttribute(name = "isMultiple", required = true)
    protected boolean isMultiple;
    @XmlAttribute(name = "id", namespace = "http://www.w3.org/XML/1998/namespace", required = true)
    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
    @XmlID
    @XmlSchemaType(name = "ID")
    protected String id;
    @XmlAttribute(name = "alternativeTo")
    @XmlIDREF
    @XmlSchemaType(name = "IDREF")
    protected Object alternativeTo;

    /**
     * Gets the value of the helpOrLabelOrHasRoot property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the helpOrLabelOrHasRoot property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getHelpOrLabelOrHasRoot().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link Help }
     * {@link Label }
     * {@link String }
     * 
     * 
     */
    public List<Object> getHelpOrLabelOrHasRoot() {
        if (helpOrLabelOrHasRoot == null) {
            helpOrLabelOrHasRoot = new ArrayList<Object>();
        }
        return this.helpOrLabelOrHasRoot;
    }

    /**
     * Gets the value of the produces property.
     * 
     * @return
     *     possible object is
     *     {@link Produces }
     *     
     */
    public Produces getProduces() {
        return produces;
    }

    /**
     * Sets the value of the produces property.
     * 
     * @param value
     *     allowed object is
     *     {@link Produces }
     *     
     */
    public void setProduces(Produces value) {
        this.produces = value;
    }

    /**
     * Gets the value of the isMandatory property.
     * 
     */
    public boolean isIsMandatory() {
        return isMandatory;
    }

    /**
     * Sets the value of the isMandatory property.
     * 
     */
    public void setIsMandatory(boolean value) {
        this.isMandatory = value;
    }

    /**
     * Gets the value of the isMultiple property.
     * 
     */
    public boolean isIsMultiple() {
        return isMultiple;
    }

    /**
     * Sets the value of the isMultiple property.
     * 
     */
    public void setIsMultiple(boolean value) {
        this.isMultiple = value;
    }

    /**
     * Gets the value of the id property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getId() {
        return id;
    }

    /**
     * Sets the value of the id property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setId(String value) {
        this.id = value;
    }

    /**
     * Gets the value of the alternativeTo property.
     * 
     * @return
     *     possible object is
     *     {@link Object }
     *     
     */
    public Object getAlternativeTo() {
        return alternativeTo;
    }

    /**
     * Sets the value of the alternativeTo property.
     * 
     * @param value
     *     allowed object is
     *     {@link Object }
     *     
     */
    public void setAlternativeTo(Object value) {
        this.alternativeTo = value;
    }

}
