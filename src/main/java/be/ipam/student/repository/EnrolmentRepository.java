package be.ipam.student.repository;

import aj.org.objectweb.asm.commons.Remapper;
import be.ipam.student.model.Enrolment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface EnrolmentRepository extends JpaRepository<Enrolment, Long> {
    Optional<Enrolment> getEnrolmentByPublicId(UUID publicId);
}