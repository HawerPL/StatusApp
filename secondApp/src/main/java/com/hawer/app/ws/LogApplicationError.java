//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.3.2 
// See <a href="https://javaee.github.io/jaxb-v2/">https://javaee.github.io/jaxb-v2/</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2020.07.24 at 12:22:16 PM CEST 
//


package com.hawer.app.ws;

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
 * &lt;complexType&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="guid_app" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *         &lt;element name="guid_production" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *         &lt;element name="error" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "guidApp",
    "guidProduction",
    "error"
})
@XmlRootElement(name = "LogApplicationError")
public class LogApplicationError {

    @XmlElement(name = "guid_app", required = true)
    protected String guidApp;
    @XmlElement(name = "guid_production", required = true)
    protected String guidProduction;
    @XmlElement(required = true)
    protected String error;

    /**
     * Gets the value of the guidApp property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getGuidApp() {
        return guidApp;
    }

    /**
     * Sets the value of the guidApp property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setGuidApp(String value) {
        this.guidApp = value;
    }

    /**
     * Gets the value of the guidProduction property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getGuidProduction() {
        return guidProduction;
    }

    /**
     * Sets the value of the guidProduction property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setGuidProduction(String value) {
        this.guidProduction = value;
    }

    /**
     * Gets the value of the error property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getError() {
        return error;
    }

    /**
     * Sets the value of the error property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setError(String value) {
        this.error = value;
    }

}
