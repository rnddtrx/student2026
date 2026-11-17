package be.ipam.student.dto;

import lombok.Value;

import java.io.Serializable;
import java.util.UUID;

/**
 * DTO for {@link be.ipam.student.model.Enrolment}
 */
@Value
public class EnrolmentDto implements Serializable {
    Long id;
    UUID publicId;
    CourseDto course;
    StudentDto student;
}