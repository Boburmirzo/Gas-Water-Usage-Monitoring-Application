package com.offsidegaming.monitoring.model.entity;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "measurement_type")
@Data
public class MeasurementType {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "measurement_type_id_generator")
    @SequenceGenerator(name = "measurement_type_id_generator", sequenceName = "measurement_type_id_seq", allocationSize = 10)
    private Long id;

    @NotNull
    @Column(nullable = false, unique = true)
    private String name;

    @NotNull
    @Column(nullable = false)
    private String type;

}