package edu.kalum.enrollment.models.dao;

import edu.kalum.enrollment.models.entities.EnrollmentRequest;
import edu.kalum.enrollment.models.entities.StatusEnrollmentProcess;

public interface IEnrollmentProcessDao {
    public StatusEnrollmentProcess executeEnrollmentProcess(EnrollmentRequest request);
}
