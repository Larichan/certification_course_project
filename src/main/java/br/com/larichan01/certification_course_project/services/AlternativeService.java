package br.com.larichan01.certification_course_project.services;

import java.util.List;

import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import br.com.larichan01.certification_course_project.models.Alternative;
import br.com.larichan01.certification_course_project.models.Question;
import br.com.larichan01.certification_course_project.repositories.AlternativeRepository;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class AlternativeService {
    
    private final AlternativeRepository repository;

    public List<Alternative>findAllAlternativesByQuestion(Question question) {
        return repository.findAll(Example.of(Alternative.builder().question(question).build()));
    }
}
