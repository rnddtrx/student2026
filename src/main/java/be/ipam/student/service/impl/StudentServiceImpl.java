package be.ipam.student.service.impl;
import be.ipam.student.dto.CourseDto;
import be.ipam.student.dto.StudentDto;
import be.ipam.student.exception.StudentNotFoundException;
import be.ipam.student.mapper.CourseMapper;
import be.ipam.student.mapper.StudentMapper;
import be.ipam.student.model.Course;
import be.ipam.student.model.Student;
import be.ipam.student.repository.CourseRepository;
import be.ipam.student.repository.StudentRepository;
import be.ipam.student.service.StudentService;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
class StudentServiceImpl implements StudentService {

    private final StudentRepository studentRepository;
    private final StudentMapper studentMapper;

    StudentServiceImpl(StudentRepository studentRepository, StudentMapper studentMapper) {
        this.studentRepository = studentRepository;
        this.studentMapper = studentMapper;
    }

    @Override
    public StudentDto getStudentByPublicId(UUID publicId) {
        Optional<StudentDto> student = studentRepository.getStudentByPublicId(publicId)
                .map(studentMapper::toDto);
        return student.orElse(null);
    }

    @Override
    public StudentDto addStudent(StudentDto studentDto) {
        Student student = studentMapper.toEntity(studentDto);
        Student savedStudent = studentRepository.save(student);
        return studentMapper.toDto(savedStudent);
    }

    @Override
    public void deleteStudent(UUID publicId) {
        Student student = studentRepository.getStudentByPublicId(publicId)
                .orElseThrow(() -> new StudentNotFoundException(publicId));
        studentRepository.delete(student);
    }
}
