package be.ipam.student.service.impl;

import be.ipam.student.dto.CourseDto;
import be.ipam.student.mapper.CourseMapper;
import be.ipam.student.model.Course;
import be.ipam.student.repository.CourseRepository;
import be.ipam.student.service.CourseService;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
class CourseServiceImpl implements CourseService {
    private final CourseMapper courseMapper;
    private final CourseRepository courseRepository;

    //Injection of dependency
    CourseServiceImpl(CourseMapper courseMapper, CourseRepository courseRepository) {
        this.courseMapper = courseMapper;
        this.courseRepository = courseRepository;
    }

    @Override
    public CourseDto getCourseByPublicId(UUID publicId){
        Optional<Course> course = courseRepository.getCourseByPublicId(publicId);
        return course.map(courseMapper::toDto).orElse(null);
    }

    @Override
    public CourseDto addCourse(CourseDto courseDto){
        Course course = courseMapper.toEntity(courseDto);
        Course savedCourse = courseRepository.save(course);
        return courseMapper.toDto(savedCourse);
    }
}
