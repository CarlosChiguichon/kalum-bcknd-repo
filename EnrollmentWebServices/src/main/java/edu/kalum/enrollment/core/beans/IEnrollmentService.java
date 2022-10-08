package edu.kalum.enrollment.core.beans;

import edu.kalum.enrollment.models.entities.EnrollmentRequest;
import edu.kalum.enrollment.models.entities.EnrollmentResponse;

import javax.jws.WebMethod;
import javax.jws.WebService;

@WebService
public interface IEnrollmentService {
    @WebMethod
    public EnrollmentResponse enrollmentProcess(EnrollmentRequest request);

}
