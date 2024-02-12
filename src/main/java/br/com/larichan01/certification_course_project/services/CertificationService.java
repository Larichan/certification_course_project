package br.com.larichan01.certification_course_project.services;

import java.util.List;

import org.springframework.stereotype.Service;

import br.com.larichan01.certification_course_project.enums.Tech;
import br.com.larichan01.certification_course_project.models.Certification;
import br.com.larichan01.certification_course_project.repositories.CertificationRepository;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class CertificationService {
    private final CertificationRepository repository;

    public List<Certification> findTop10CertificationsByTechnology(Tech technology) {
        return repository.findFirst10ByTechnologyOrderByGradeDesc(technology);
    }
}
