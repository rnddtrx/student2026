package be.ipam.student.service.impl;

import be.ipam.student.dto.EnrolmentDto;
import be.ipam.student.exception.CourseNotFoundException;
import be.ipam.student.exception.EnrolmentNotFoundException;
import be.ipam.student.exception.StudentNotFoundException;
import be.ipam.student.mapper.EnrolmentMapper;
import be.ipam.student.model.Course;
import be.ipam.student.model.Enrolment;
import be.ipam.student.model.Student;
import be.ipam.student.repository.CourseRepository;
import be.ipam.student.repository.EnrolmentRepository;
import be.ipam.student.repository.StudentRepository;
import be.ipam.student.service.EnrolmentService;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.beans.Transient;
import java.util.List;
import java.util.UUID;

@Service
class EnrolmentServiceImpl implements EnrolmentService {

    private final EnrolmentRepository enrolmentRepository;
    private final EnrolmentMapper enrolmentMapper;
    private final CourseRepository courseRepository;
    private final StudentRepository studentRepository;

    EnrolmentServiceImpl(EnrolmentRepository enrolmentRepository, EnrolmentMapper enrolmentMapper,
                         CourseRepository courseRepository, StudentRepository studentRepository) {
        this.enrolmentRepository = enrolmentRepository;
        this.enrolmentMapper = enrolmentMapper;
        this.courseRepository = courseRepository;
        this.studentRepository = studentRepository;
    }

    @Override
    public EnrolmentDto getEnrolmentById(UUID enrolmentPublicId) {
        Enrolment enrolment = enrolmentRepository.getEnrolmentByPublicId(enrolmentPublicId)
                .orElseThrow(() -> new EnrolmentNotFoundException(enrolmentPublicId));

        return enrolmentMapper.toDto(enrolment);
    }

    @Override
    @Transactional
    public EnrolmentDto createEnrolment(UUID coursePublicId, UUID studentPublicId){
        Course course = courseRepository.getCourseByPublicId(coursePublicId)
            .orElseThrow(() -> new CourseNotFoundException(coursePublicId));

        Student student = studentRepository.getStudentByPublicId(studentPublicId)
            .orElseThrow(() -> new StudentNotFoundException(studentPublicId));

        Enrolment enrolment = new Enrolment();
        enrolment.setCourse(course);
        enrolment.setStudent(student);

        return enrolmentMapper.toDto(enrolmentRepository.save(enrolment));
    }


    @Override
    public void deleteEnrolment(UUID publicId) {
        Enrolment enrolment = enrolmentRepository.getEnrolmentByPublicId(publicId)
                .orElseThrow(() -> new EnrolmentNotFoundException(publicId));
        enrolmentRepository.delete(enrolment);
    }

    @Override
    public List<EnrolmentDto> getEnrolmentsByStudent(UUID studentPublicId) {
        Student student = studentRepository.getStudentByPublicId(studentPublicId)
                .orElseThrow(() -> new StudentNotFoundException(studentPublicId));

        List<Enrolment> enrolments = enrolmentRepository.findAll().stream()
                .filter(enrolment -> enrolment.getStudent().equals(student))
                .toList();

        return enrolments.stream()
                .map(enrolmentMapper::toDto)
                .toList();
    }

}
