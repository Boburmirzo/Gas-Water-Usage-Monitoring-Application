package com.offsidegaming.monitoring.repository;

import com.offsidegaming.monitoring.model.entity.MeasurementType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MeasurementTypeRepository extends JpaRepository<MeasurementType, Long> {
}
