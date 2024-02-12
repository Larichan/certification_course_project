package br.com.larichan01.certification_course_project.repositories;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.larichan01.certification_course_project.models.Certification;
import java.util.List;
import br.com.larichan01.certification_course_project.enums.Tech;


public interface CertificationRepository extends JpaRepository<Certification, UUID> {
    List<Certification> findFirst10ByTechnologyOrderByGradeDesc(Tech technology);
}
