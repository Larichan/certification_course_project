package br.com.larichan01.certification_course_project.mappers;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import br.com.larichan01.certification_course_project.dtos.AlternativesDTO;
import br.com.larichan01.certification_course_project.dtos.QuestionWithAlternativesDTO;
import br.com.larichan01.certification_course_project.models.Question;

@Mapper
public interface QuestionWithAlternativesDTOMapper {
    QuestionWithAlternativesDTOMapper INSTANCE = Mappers.getMapper(QuestionWithAlternativesDTOMapper.class);

    @Mapping(source = "question.id", target = "id")
    @Mapping(source = "question.description", target = "description")
    @Mapping(source = "question.technology", target = "technology")
    @Mapping(source = "alternatives", target = "alternatives")
    QuestionWithAlternativesDTO build(Question question, List<AlternativesDTO> alternatives);
}
