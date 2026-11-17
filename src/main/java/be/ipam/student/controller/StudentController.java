package be.ipam.student.controller;

import be.ipam.student.dto.CourseDto;
import be.ipam.student.dto.EnrolmentDto;
import be.ipam.student.dto.StudentDto;
import be.ipam.student.service.EnrolmentService;
import be.ipam.student.service.StudentService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/students")
class StudentController {
    public final StudentService studentService;
    public final EnrolmentService enrolmentService;

    public StudentController(StudentService studentService, EnrolmentService enrolmentService) {
        this.studentService = studentService;
        this.enrolmentService = enrolmentService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<StudentDto> getStudentById(@PathVariable UUID id) {
        StudentDto student = studentService.getStudentByPublicId(id);
        if (student == null) {
            return ResponseEntity.notFound().build();
        }
        else {
            return ResponseEntity.ok(student);
        }
    }

    @PostMapping
    public ResponseEntity<StudentDto> createStudent(@RequestBody StudentDto studentDto) {
        StudentDto createdStudent = studentService.addStudent(studentDto);
        return ResponseEntity.created(null).body(createdStudent);
    }

    @DeleteMapping
    public ResponseEntity<Void> deleteStudent(@PathVariable UUID id) {
        studentService.deleteStudent(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{studentPublicId}/enrolments")
    public ResponseEntity<List<EnrolmentDto>> getEnrolmentsByStudent(@PathVariable UUID studentPublicId) {
        return ResponseEntity.ok(enrolmentService.getEnrolmentsByStudent(studentPublicId));
    }

}
