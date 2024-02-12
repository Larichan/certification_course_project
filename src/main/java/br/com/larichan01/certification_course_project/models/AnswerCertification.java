package br.com.larichan01.certification_course_project.models;

import java.time.LocalDateTime;
import java.util.UUID;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@EntityListeners(AuditingEntityListener.class)
public class AnswerCertification {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @JoinColumn(name = "certification_id")
    @ManyToOne
    private Certification certification;

    @JoinColumn(name = "student_id")
    @ManyToOne
    private Student student;

    // @JoinColumn(name = "question_id", insertable = false, updatable = false)
    // private Question question;

    // @JoinColumn(name = "answer_id", insertable = false, updatable = false)
    // private Alternative answer;

    private Boolean isCorrect;

    @CreatedDate
    private LocalDateTime createdAt;
}
