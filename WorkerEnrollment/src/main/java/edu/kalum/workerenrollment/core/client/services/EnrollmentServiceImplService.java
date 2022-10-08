
package edu.kalum.workerenrollment.core.client.services;

import java.net.MalformedURLException;
import java.net.URL;
import javax.xml.namespace.QName;
import javax.xml.ws.Service;
import javax.xml.ws.WebEndpoint;
import javax.xml.ws.WebServiceClient;
import javax.xml.ws.WebServiceException;
import javax.xml.ws.WebServiceFeature;


/**
 * This class was generated by the JAX-WS RI.
 * JAX-WS RI 2.2.10
 * Generated source version: 2.2
 * 
 */
@WebServiceClient(name = "EnrollmentServiceImplService", targetNamespace = "http://beans.core.enrollment.kalum.edu/", wsdlLocation = "http://ruser-82jw:8080/EnrollmentServiceImplService/EnrollmentServiceImpl?wsdl")
public class EnrollmentServiceImplService
    extends Service
{

    private final static URL ENROLLMENTSERVICEIMPLSERVICE_WSDL_LOCATION;
    private final static WebServiceException ENROLLMENTSERVICEIMPLSERVICE_EXCEPTION;
    private final static QName ENROLLMENTSERVICEIMPLSERVICE_QNAME = new QName("http://beans.core.enrollment.kalum.edu/", "EnrollmentServiceImplService");

    static {
        URL url = null;
        WebServiceException e = null;
        try {
            url = new URL("http://ruser-82jw:8080/EnrollmentServiceImplService/EnrollmentServiceImpl?wsdl");
        } catch (MalformedURLException ex) {
            e = new WebServiceException(ex);
        }
        ENROLLMENTSERVICEIMPLSERVICE_WSDL_LOCATION = url;
        ENROLLMENTSERVICEIMPLSERVICE_EXCEPTION = e;
    }

    public EnrollmentServiceImplService() {
        super(__getWsdlLocation(), ENROLLMENTSERVICEIMPLSERVICE_QNAME);
    }

    public EnrollmentServiceImplService(WebServiceFeature... features) {
        super(__getWsdlLocation(), ENROLLMENTSERVICEIMPLSERVICE_QNAME, features);
    }

    public EnrollmentServiceImplService(URL wsdlLocation) {
        super(wsdlLocation, ENROLLMENTSERVICEIMPLSERVICE_QNAME);
    }

    public EnrollmentServiceImplService(URL wsdlLocation, WebServiceFeature... features) {
        super(wsdlLocation, ENROLLMENTSERVICEIMPLSERVICE_QNAME, features);
    }

    public EnrollmentServiceImplService(URL wsdlLocation, QName serviceName) {
        super(wsdlLocation, serviceName);
    }

    public EnrollmentServiceImplService(URL wsdlLocation, QName serviceName, WebServiceFeature... features) {
        super(wsdlLocation, serviceName, features);
    }

    /**
     * 
     * @return
     *     returns IEnrollmentService
     */
    @WebEndpoint(name = "EnrollmentServiceImplPort")
    public IEnrollmentService getEnrollmentServiceImplPort() {
        return super.getPort(new QName("http://beans.core.enrollment.kalum.edu/", "EnrollmentServiceImplPort"), IEnrollmentService.class);
    }

    /**
     * 
     * @param features
     *     A list of {@link javax.xml.ws.WebServiceFeature} to configure on the proxy.  Supported features not in the <code>features</code> parameter will have their default values.
     * @return
     *     returns IEnrollmentService
     */
    @WebEndpoint(name = "EnrollmentServiceImplPort")
    public IEnrollmentService getEnrollmentServiceImplPort(WebServiceFeature... features) {
        return super.getPort(new QName("http://beans.core.enrollment.kalum.edu/", "EnrollmentServiceImplPort"), IEnrollmentService.class, features);
    }

    private static URL __getWsdlLocation() {
        if (ENROLLMENTSERVICEIMPLSERVICE_EXCEPTION!= null) {
            throw ENROLLMENTSERVICEIMPLSERVICE_EXCEPTION;
        }
        return ENROLLMENTSERVICEIMPLSERVICE_WSDL_LOCATION;
    }

}