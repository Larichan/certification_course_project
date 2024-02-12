package br.com.larichan01.certification_course_project.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import br.com.larichan01.certification_course_project.dtos.AlternativesDTO;
import br.com.larichan01.certification_course_project.models.Alternative;

@Mapper
public interface AlternativesDTOMapper {
    
    AlternativesDTOMapper INSTANCE = Mappers.getMapper(AlternativesDTOMapper.class);

    AlternativesDTO build(Alternative alternative);
}
