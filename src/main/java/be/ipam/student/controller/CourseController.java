package be.ipam.student.controller;

import be.ipam.student.dto.CourseDto;
import be.ipam.student.service.CourseService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/courses")
class CourseController {
    private final CourseService courseService;

    CourseController(CourseService courseService) {
        this.courseService = courseService;
    }

    //GET /courses/{id}
    @GetMapping("/{id}")
    public ResponseEntity<CourseDto>getCourseById(@PathVariable UUID id) {
        CourseDto course = courseService.getCourseByPublicId(id);
        if (course == null) {
            return ResponseEntity.notFound().build();
        }
        else {
            return ResponseEntity.ok(course);
        }
    }

    @PostMapping
    public ResponseEntity<CourseDto> createCourse(@RequestBody CourseDto courseDto) {
        CourseDto createdCourse = courseService.addCourse(courseDto);
        return ResponseEntity.created(null).body(createdCourse);
    }




}
