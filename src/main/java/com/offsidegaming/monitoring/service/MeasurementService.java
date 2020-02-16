package com.offsidegaming.monitoring.service;

import com.offsidegaming.monitoring.model.entity.Measurement;
import com.offsidegaming.monitoring.model.entity.MeasurementType;
import com.offsidegaming.monitoring.model.entity.User;
import com.offsidegaming.monitoring.model.response.MeasurementResponse;
import com.offsidegaming.monitoring.repository.MeasurementRepository;
import com.offsidegaming.monitoring.repository.MeasurementTypeRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class MeasurementService {

    private static final Logger LOGGER = LoggerFactory.getLogger(MeasurementService.class);
    private final MeasurementRepository measurementRepository;
    private final MeasurementTypeRepository measurementTypeRepository;
    private final UserService userService;

    public MeasurementService(MeasurementRepository measurementRepository,
                              MeasurementTypeRepository measurementTypeRepository,
                              UserService userService) {
        this.measurementRepository = measurementRepository;
        this.measurementTypeRepository = measurementTypeRepository;
        this.userService = userService;
    }

    public Map<String, List<MeasurementResponse>> getByUseId(Long userId) {
        if (Objects.nonNull(userId) && userService.userExists(userId)) {
            User user = userService.getUserById(userId);
            List<MeasurementType> measurementTypes = measurementTypeRepository.findAll();
            List<MeasurementResponse> measurementResponses = measurementRepository.findAllByUser(user)
                    .stream()
                    .map(e -> parse(e, measurementTypes))
                    .collect(Collectors.toList());
            LOGGER.debug(String.format("User %s queried %d entities.", userId, measurementResponses.size()));
            return measurementResponses.stream().collect(Collectors.groupingBy(MeasurementResponse::getMeasurementName));
        } else {
            throw new RuntimeException("User with id %s not found.");
        }
    }

    private MeasurementResponse parse(Measurement measurement, List<MeasurementType> measurementTypes) {
        String typeName = measurementTypes.stream()
                .filter(e -> e.getId().equals(measurement.getMeasurementId()))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("No such Id"))
                .getName();
        return MeasurementResponse.builder()
                .measurementName(typeName)
                .amount(measurement.getAmount())
                .created(measurement.getCreated().toString())
                .build();
    }

    public Map<Long, List<Measurement>> submitMeasurements(List<MeasurementResponse> measurementResponses, Long userId) {
        if (containsUnique(measurementResponses)) {
            User user = userService.getUserById(userId);
            List<MeasurementType> measurementTypes = measurementTypeRepository.findAll();
            List<Measurement> measurements = measurementResponses
                    .stream()
                    .map(e -> parse(e, user, measurementTypes))
                    .collect(Collectors.toList());
            return measurementRepository.saveAll(measurements)
                    .stream()
                    .collect(Collectors.groupingBy(Measurement::getMeasurementId));
        } else {
            throw new RuntimeException("Measurement type duplication is not allowed.");
        }
    }

    private boolean containsUnique(List<MeasurementResponse> measurementResponses) {
        Set<String> set = new HashSet<>();
        for (MeasurementResponse measurementResponse : measurementResponses) {
            if (!set.add(measurementResponse.getMeasurementName())) return false;
        }
        return true;
    }

    private Measurement parse(MeasurementResponse measurementResponse, User user, List<MeasurementType> measurementTypes) {
        String typeName = measurementResponse.getMeasurementName();
        Long typeId = measurementTypes.stream()
                .filter(e -> e.getName().equals(typeName))
                .findFirst()
                .orElseThrow(() -> new RuntimeException(String.format("Type with name %s not found.", typeName)))
                .getId();
        return Measurement.builder()
                .amount(measurementResponse.getAmount())
                .created(new Date())
                .user(user)
                .measurementId(typeId)
                .build();
    }

}