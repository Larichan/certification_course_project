package br.com.larichan01.certification_course_project.services;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicInteger;

import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import br.com.larichan01.certification_course_project.dtos.QuestionAnswerDTO;
import br.com.larichan01.certification_course_project.dtos.QuestionWithAlternativesDTO;
import br.com.larichan01.certification_course_project.dtos.StudentCertificationAnswerDTO;
import br.com.larichan01.certification_course_project.dtos.StudentTechnologyDTO;
import br.com.larichan01.certification_course_project.models.AnswerCertification;
import br.com.larichan01.certification_course_project.models.Certification;
import br.com.larichan01.certification_course_project.models.Question;
import br.com.larichan01.certification_course_project.models.Student;
import br.com.larichan01.certification_course_project.repositories.AnswerCertificationRepository;
import br.com.larichan01.certification_course_project.repositories.CertificationRepository;
import br.com.larichan01.certification_course_project.repositories.StudentRepository;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class StudentService {

    private final StudentRepository repository;
    private final CertificationRepository certificationRepository;
    private final QuestionService questionService;
    private final AlternativeService alternativeService;
    private final AnswerCertificationRepository answerCertificationRepository;
    
    public boolean studentHasCertificationInTechnology(StudentTechnologyDTO student) {

        Student studentObject = studentExistsByEmail(student.getEmail());
        if(studentObject != null) {
            var certifications = certificationRepository.findAll(Example.of(Certification.builder().technology(student.getTechnology()).student(studentObject).build()));
            if(!certifications.isEmpty())
                return true;
        }

        return false;
    }

    public StudentCertificationAnswerDTO insertStudentAnswers(StudentCertificationAnswerDTO dto) throws Exception {

        boolean studentHasCertification = this.studentHasCertificationInTechnology(StudentTechnologyDTO.builder().technology(dto.getTechnology()).email(dto.getEmail()).build());
        
        if(studentHasCertification) {
            throw new Exception("Estudante já possui essa certificação!");
        }
        
        AtomicInteger correctAnswersCount = new AtomicInteger(0);
        Student student = studentExistsByEmail(dto.getEmail());

        List<Question> questionOfTechnology = questionService.findAllQuestionsByTechnology(dto.getTechnology());

        dto.getAnswers().forEach(answer -> {
            Question question = questionOfTechnology.stream().filter(q -> q.getId().equals(answer.getQuestionId())).findFirst().get();
            var correctAlternative = alternativeService.findAllAlternativesByQuestion(question).stream().filter(alternative -> alternative.getIsCorrect()).findFirst().get();
        
            if(correctAlternative.getId().equals(answer.getAlternativeId())) {
                answer.setIsCorrect(true);
                correctAnswersCount.incrementAndGet();
            } else {
                answer.setIsCorrect(false);
            }
        });

        if(student == null) {
            student = repository.save(Student.builder().email(dto.getEmail()).build());
        }

        Certification certification = certificationRepository.save(Certification.builder()
        .technology(dto.getTechnology())
        .student(student)
        .grade(correctAnswersCount.doubleValue())
        .build());
        
        for (QuestionAnswerDTO answer : dto.getAnswers()) {
            answerCertificationRepository.save(AnswerCertification.builder()
            .student(Student.builder().id(student.getId()).build())
            .certification(certification)
            .isCorrect(answer.getIsCorrect())
            .build());
        }

        return dto;
    }

    private Student studentExistsByEmail(String email) {
        Optional<Student> studentObject = repository.findOne(Example.of(Student.builder().email(email).build()));

        return studentObject.isPresent() ? studentObject.get() : null;
    }
}
