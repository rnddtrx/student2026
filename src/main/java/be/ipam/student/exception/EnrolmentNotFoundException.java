package be.ipam.student.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.UUID;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class EnrolmentNotFoundException extends RuntimeException {
    public EnrolmentNotFoundException(UUID publicId) {
        super("Enrolment not found with id: " + publicId);
    }
}
