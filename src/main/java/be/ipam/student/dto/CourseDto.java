package be.ipam.student.dto;

import lombok.Value;

import java.io.Serializable;
import java.util.UUID;

/**
 * DTO for {@link be.ipam.student.model.Course}
 */
@Value
public class CourseDto implements Serializable {
    UUID publicId;
    String name;
}