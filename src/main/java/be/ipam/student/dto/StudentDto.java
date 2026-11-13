package be.ipam.student.dto;

import be.ipam.student.model.Student;
import lombok.Value;

import java.io.Serializable;
import java.util.UUID;

/**
 * DTO for {@link Student}
 */
@Value
public class StudentDto implements Serializable {
    UUID publicId;
    String name;
}