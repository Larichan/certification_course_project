package br.com.larichan01.certification_course_project.dtos;

import br.com.larichan01.certification_course_project.enums.Tech;
import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class StudentTechnologyDTO {
    private String email;
    private Tech technology;
}
