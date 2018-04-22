package ru.falseteam.vktests.entity;

import lombok.*;

import javax.persistence.*;
import java.util.Set;

/**
 * @author Sumin Vladislav
 * @version 1.2
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

    @OneToMany(mappedBy = "group", fetch = FetchType.LAZY)
    private Set<User> users;
}
