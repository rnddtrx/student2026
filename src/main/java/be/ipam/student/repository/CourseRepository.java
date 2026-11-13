package be.ipam.student.repository;

import be.ipam.student.model.Course;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Observable;
import java.util.Optional;
import java.util.UUID;

public interface CourseRepository extends JpaRepository<Course, Long> {

    Optional<Course> getCourseByPublicId(UUID publicId);

}