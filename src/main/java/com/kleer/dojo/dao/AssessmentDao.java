package com.kleer.dojo.dao;

import com.kleer.dojo.entity.AssessmentEntity;
import com.kleer.dojo.enums.RegisterStatusEnum;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AssessmentDao extends JpaRepository<AssessmentEntity, Integer> {
    Optional<AssessmentEntity> findByAssessmentIdAndStatus(Integer id, RegisterStatusEnum registerStatusEnum);
}
