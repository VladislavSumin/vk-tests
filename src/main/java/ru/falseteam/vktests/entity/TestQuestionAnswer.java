package ru.falseteam.vktests.entity;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.hibernate.annotations.Type;

import javax.annotation.Priority;
import javax.persistence.*;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "test_question_answers")
public class TestQuestionAnswer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(unique = true)
    private long id;

    @Column(nullable = false)
    @Type(type = "text")
    private String answer;

    @Column(nullable = false)
    private boolean isRight;

    @ManyToOne
    @OnDelete(action = OnDeleteAction.CASCADE)
    private TestQuestion question;
}
