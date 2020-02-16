package com.offsidegaming.monitoring.model.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Builder;
import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Entity
@Data
@Builder
public class Measurement {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "measurement_id_generator")
    @SequenceGenerator(name = "measurement_id_generator", sequenceName = "measurement_id_seq", allocationSize = 30)
    private Long id;

    @Version
    private Long version;

    @NotNull
    @Column(nullable = false)
    private Double amount;

    @NotNull
    @Temporal(TemporalType.TIMESTAMP)
    @Column(nullable = false, updatable = false)
    private Date created;

    @JsonIgnore
    @NotNull
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    User user;

    @NotNull
    @Column(nullable = false, updatable = false)
    private Long measurementId;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Measurement that = (Measurement) o;

        if (!amount.equals(that.amount)) return false;
        if (!created.equals(that.created)) return false;
        return measurementId.equals(that.measurementId);
    }

    @Override
    public int hashCode() {
        int result = amount.hashCode();
        result = 31 * result + created.hashCode();
        result = 31 * result + measurementId.hashCode();
        return result;
    }
}