package com.offsidegaming.monitoring.model.entity;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "USERS")
@Data
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "user_id_generator")
    @SequenceGenerator(name = "user_id_generator", sequenceName = "user_id_seq", allocationSize = 30)
    private Long id;

    @Version
    private Long version;

    @NotNull
    @Email
    @Column(nullable = false, unique = true)
    private String email;
    private String password;
    private LocalDateTime dateOfRegistration;

    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY)
    private List<Measurement> gasEntities = new ArrayList<>();

}