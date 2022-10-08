package edu.kalum.enrollment.models.entities;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class EnrollmentResponse implements Serializable {
    private int statusCode;
    private String description;
}
