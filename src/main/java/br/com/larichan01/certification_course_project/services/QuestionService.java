package br.com.larichan01.certification_course_project.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import br.com.larichan01.certification_course_project.dtos.QuestionWithAlternativesDTO;
import br.com.larichan01.certification_course_project.enums.Tech;
import br.com.larichan01.certification_course_project.mappers.AlternativesDTOMapper;
import br.com.larichan01.certification_course_project.mappers.QuestionWithAlternativesDTOMapper;
import br.com.larichan01.certification_course_project.models.Question;
import br.com.larichan01.certification_course_project.repositories.QuestionRepository;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class QuestionService {
    
    private final QuestionRepository repository;
    private final AlternativeService alternativeService;

    public List<Question> findAllQuestionsByTechnology(Tech technology) {
        return repository.findAll(Example.of(Question.builder().technology(technology).build()));
    }

    public List<QuestionWithAlternativesDTO> findAllQuestionsWithAnswersByTechnology(Tech technology) {
        var questions = findAllQuestionsByTechnology(technology);
        List<QuestionWithAlternativesDTO> result = new ArrayList<>();

        for(Question question : questions) {
            var alternativesDTO = alternativeService.findAllAlternativesByQuestion(question).stream()
            .map(alternative -> AlternativesDTOMapper.INSTANCE.build(alternative)).toList();
            
            result.add(QuestionWithAlternativesDTOMapper.INSTANCE.build(question, alternativesDTO));
        }
        return result;
    }
}
