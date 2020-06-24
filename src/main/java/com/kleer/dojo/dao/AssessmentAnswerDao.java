package com.kleer.dojo.dao;

import com.kleer.dojo.entity.AssessmentAnswerEntity;
import com.kleer.dojo.enums.RegisterStatusEnum;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Collection;
import java.util.Optional;

public interface AssessmentAnswerDao extends JpaRepository<AssessmentAnswerEntity, Integer> {
    Optional<Collection<AssessmentAnswerEntity>> findByAssessmentIdAndStatus(Integer assessmentId, RegisterStatusEnum registerStatusEnum);
}
