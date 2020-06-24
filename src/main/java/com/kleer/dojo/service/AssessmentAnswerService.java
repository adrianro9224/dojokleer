package com.kleer.dojo.service;

import com.kleer.dojo.entity.AssessmentAnswerEntity;
import com.kleer.dojo.enums.RegisterStatusEnum;
import com.kleer.dojo.exceptions.AssessmentNotFoundException;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

public interface AssessmentAnswerService {
    Optional<Collection<AssessmentAnswerEntity>> findByAssessmentIdAndStatus(Integer assessmentId, RegisterStatusEnum registerStatusEnum);
    Optional<List<AssessmentAnswerEntity>> saveAll(Collection<Integer> questionAnswerIds, Integer assessmentId) throws AssessmentNotFoundException;
}
