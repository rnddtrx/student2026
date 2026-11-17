package be.ipam.student.service;

import be.ipam.student.dto.StudentDto;

import java.util.UUID;

public interface StudentService {
    StudentDto getStudentByPublicId(UUID publicId);

    StudentDto addStudent(StudentDto courseDto);

    void deleteStudent(UUID publicId);
}
