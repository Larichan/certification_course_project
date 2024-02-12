package br.com.larichan01.certification_course_project.dtos;

import java.util.List;
import java.util.UUID;

import br.com.larichan01.certification_course_project.enums.Tech;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class QuestionWithAlternativesDTO {
    private UUID id;
    private String description;
    private Tech technology;
    List<AlternativesDTO> alternatives;
}
