package ru.falseteam.vktests.entity;

import lombok.*;

import javax.persistence.*;
import java.util.Date;
import java.util.Set;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "tests")
public class Test {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(unique = true)
    private long id;

    @Column(nullable = false, unique = true)
    private String name;

    @Column(nullable = false)
    @Temporal(TemporalType.TIME)
    private Date timeLimit;

    @OneToMany(mappedBy = "test", fetch = FetchType.LAZY)
    private Set<TestQuestion> questions;
}
