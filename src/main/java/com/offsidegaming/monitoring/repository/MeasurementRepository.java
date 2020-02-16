package com.offsidegaming.monitoring.repository;

import com.offsidegaming.monitoring.model.entity.Measurement;
import com.offsidegaming.monitoring.model.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MeasurementRepository extends JpaRepository<Measurement, Long> {

    List<Measurement> findAllByUser(User user);

}