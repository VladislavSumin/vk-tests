package ru.falseteam.vktests.entity;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.util.Set;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "test_questions")
public class TestQuestion {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(unique = true)
    private long id;

    @Column(nullable = false)
    @Type(type = "text")
    private String question;

    @ManyToOne
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Test test;

    @OneToMany(mappedBy = "question")
    private Set<TestQuestionAnswer> answers;
}
