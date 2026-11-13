package be.ipam.student.service;

import be.ipam.student.dto.CourseDto;

import java.util.UUID;

public interface CourseService {
    CourseDto getCourseByPublicId(UUID publicId);

    CourseDto addCourse(CourseDto courseDto);
}
