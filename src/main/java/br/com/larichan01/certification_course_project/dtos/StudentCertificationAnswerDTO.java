package br.com.larichan01.certification_course_project.dtos;

import java.util.List;

import br.com.larichan01.certification_course_project.enums.Tech;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class StudentCertificationAnswerDTO {
    private String email;
    private Tech technology;
    private List<QuestionAnswerDTO> answers;
}
