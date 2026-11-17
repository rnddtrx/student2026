package be.ipam.student.service;

import be.ipam.student.dto.EnrolmentDto;

import java.util.List;
import java.util.UUID;

public interface EnrolmentService {
    EnrolmentDto getEnrolmentById(UUID enrolmentPublicId);
    EnrolmentDto createEnrolment(UUID coursePublicId, UUID studentPublicId);
    void deleteEnrolment(UUID publicId);
    List<EnrolmentDto> getEnrolmentsByStudent(UUID studentPublicId);
}
