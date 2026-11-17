package be.ipam.student.controller;

import be.ipam.student.dto.EnrolmentDto;
import be.ipam.student.dto.request.CreateEnrolmentRequestDto;
import be.ipam.student.service.EnrolmentService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/enrolments")
class EnrolmentController {

    private final EnrolmentService enrolmentService;

    public EnrolmentController(EnrolmentService enrolmentService) {
        this.enrolmentService = enrolmentService;
    }

    @PostMapping
    public ResponseEntity<EnrolmentDto> createEnrolment(
            @Valid @RequestBody CreateEnrolmentRequestDto request) {

        EnrolmentDto created = enrolmentService.createEnrolment(
                request.getCoursePublicId(), request.getStudentPublicId());
        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }

    @GetMapping("/{publicId}")
    public ResponseEntity<EnrolmentDto> getEnrolment(@PathVariable UUID publicId) {
        return ResponseEntity.ok(enrolmentService.getEnrolmentById(publicId));
    }

    @DeleteMapping("/{publicId}")
    public ResponseEntity<Void> deleteEnrolment(@PathVariable UUID publicId) {
        enrolmentService.deleteEnrolment(publicId);
        return ResponseEntity.noContent().build();
    }
}
