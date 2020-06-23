package com.kleer.dojo.service;

import com.kleer.dojo.dao.AssessmentDao;
import com.kleer.dojo.entity.AssessmentEntity;
import com.kleer.dojo.enums.RegisterStatusEnum;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AssessmentServiceImpl implements AssessmentService{

    private final AssessmentDao assessmentDao;

    public AssessmentServiceImpl(AssessmentDao assessmentDao) {
        this.assessmentDao = assessmentDao;
    }

    @Override
    public Optional<AssessmentEntity> createOne(AssessmentEntity assessmentEntity) {
        return Optional.of(this.assessmentDao.save(assessmentEntity));
    }

    @Override
    public Optional<AssessmentEntity> findByAssessmentIdAndStatus(Integer id, RegisterStatusEnum registerStatusEnum) {
        return this.assessmentDao.findByAssessmentIdAndStatus(id, registerStatusEnum);
    }
}
