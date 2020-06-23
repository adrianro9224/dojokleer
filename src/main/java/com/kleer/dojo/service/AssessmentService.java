package com.kleer.dojo.service;

import com.kleer.dojo.entity.AssessmentEntity;
import com.kleer.dojo.enums.RegisterStatusEnum;

import java.util.Optional;

public interface AssessmentService {
    Optional<AssessmentEntity> createOne(AssessmentEntity assessmentEntity);
    Optional<AssessmentEntity> findByAssessmentIdAndStatus(Integer id, RegisterStatusEnum registerStatusEnum);
}
