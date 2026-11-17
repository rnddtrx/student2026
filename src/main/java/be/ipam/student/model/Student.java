package be.ipam.student.model;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "students")
@Getter
@Setter
@NoArgsConstructor
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false)
    @JdbcTypeCode(SqlTypes.BIGINT)
    private Long id;

    @Column(name = "public_id", nullable = false, unique = true, updatable = false)
    @JdbcTypeCode(SqlTypes.UUID)
    private UUID publicId;

    @Column(name = "name", nullable = false, unique = true)
    @JdbcTypeCode(SqlTypes.NVARCHAR)
    private String name;

    @OneToMany(mappedBy = "student")
    private List<Enrolment> enrolments;

    @PrePersist
    protected void onCreate() {
        setPublicId(java.util.UUID.randomUUID());
    }

}
