package br.com.larichan01.certification_course_project.repositories;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.larichan01.certification_course_project.models.Student;

public interface StudentRepository extends JpaRepository<Student, UUID> {
    
}
