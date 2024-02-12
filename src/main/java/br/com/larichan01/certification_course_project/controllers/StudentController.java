package br.com.larichan01.certification_course_project.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.larichan01.certification_course_project.dtos.StudentCertificationAnswerDTO;
import br.com.larichan01.certification_course_project.dtos.StudentTechnologyDTO;
import br.com.larichan01.certification_course_project.services.StudentService;
import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/students")
@AllArgsConstructor
public class StudentController {
    
    private final StudentService service;

    @PostMapping("/verifyIfHasCertification")
    public ResponseEntity<Boolean> verifyIfHasCertification(@RequestBody StudentTechnologyDTO dto) {
        return ResponseEntity.status(HttpStatus.OK).body(service.studentHasCertificationInTechnology(dto));
    }

    @PostMapping("/answerCertification")
    public ResponseEntity<Object> postMethodName(@RequestBody StudentCertificationAnswerDTO entity) throws Exception {
        try {
            return ResponseEntity.ok().body(service.insertStudentAnswers(entity));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
