package ru.falseteam.vktests.entity;

import lombok.*;

import javax.persistence.*;

/**
 * @author Sumin Vladislav
 * @version 1.1
 */
@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "groups")
public class Group {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(unique = true)
    private long id;

    @Column(nullable = false, unique = true)
    private String name;

    @Column(nullable = false)
    //@Enumerated(EnumType.STRING)
    private Role role;
}
