package br.com.larichan01.certification_course_project.controllers;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.larichan01.certification_course_project.enums.Tech;
import br.com.larichan01.certification_course_project.models.Certification;
import br.com.larichan01.certification_course_project.services.CertificationService;
import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/certifications")
@AllArgsConstructor
public class CertificationController {
    private final CertificationService service;

    @GetMapping("/{technology}")
    public ResponseEntity<List<Certification>> findRankingOfCertificationsByTechnology(@PathVariable Tech technology) {
        List<Certification> certifications = service.findTop10CertificationsByTechnology(technology);
        if(certifications.isEmpty()) {
            return ResponseEntity.noContent().build();
        }

        return ResponseEntity.ok().body(certifications);
    }
}
