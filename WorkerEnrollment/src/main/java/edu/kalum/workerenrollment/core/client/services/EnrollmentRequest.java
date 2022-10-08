
package edu.kalum.workerenrollment.core.client.services;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for enrollmentRequest complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="enrollmentRequest">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="carreraID" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="ciclo" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="mesInicioPago" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="noExpediente" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "enrollmentRequest", propOrder = {
    "carreraID",
    "ciclo",
    "mesInicioPago",
    "noExpediente"
})
public class EnrollmentRequest {

    protected String carreraID;
    protected String ciclo;
    protected int mesInicioPago;
    protected String noExpediente;

    /**
     * Gets the value of the carreraID property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCarreraID() {
        return carreraID;
    }

    /**
     * Sets the value of the carreraID property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCarreraID(String value) {
        this.carreraID = value;
    }

    /**
     * Gets the value of the ciclo property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCiclo() {
        return ciclo;
    }

    /**
     * Sets the value of the ciclo property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCiclo(String value) {
        this.ciclo = value;
    }

    /**
     * Gets the value of the mesInicioPago property.
     * 
     */
    public int getMesInicioPago() {
        return mesInicioPago;
    }

    /**
     * Sets the value of the mesInicioPago property.
     * 
     */
    public void setMesInicioPago(int value) {
        this.mesInicioPago = value;
    }

    /**
     * Gets the value of the noExpediente property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNoExpediente() {
        return noExpediente;
    }

    /**
     * Sets the value of the noExpediente property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNoExpediente(String value) {
        this.noExpediente = value;
    }

}
