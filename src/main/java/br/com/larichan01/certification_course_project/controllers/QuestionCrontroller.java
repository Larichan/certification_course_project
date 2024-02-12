package br.com.larichan01.certification_course_project.controllers;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.larichan01.certification_course_project.dtos.QuestionWithAlternativesDTO;
import br.com.larichan01.certification_course_project.enums.Tech;
import br.com.larichan01.certification_course_project.services.QuestionService;
import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/questions")
@AllArgsConstructor
public class QuestionCrontroller {
    
    private final QuestionService service;

    @GetMapping("/{technology}")
    public List<QuestionWithAlternativesDTO> findAllByTechnology(@PathVariable Tech technology) {
        return service.findAllQuestionsWithAnswersByTechnology(technology);
    }
}
