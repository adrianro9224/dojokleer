package com.kleer.dojo.service;

import com.kleer.dojo.dao.AssessmentAnswerDao;
import com.kleer.dojo.dao.AssessmentDao;
import com.kleer.dojo.dao.QuestionAnswerDao;
import com.kleer.dojo.entity.AssessmentAnswerEntity;
import com.kleer.dojo.entity.AssessmentEntity;
import com.kleer.dojo.enums.RegisterStatusEnum;
import com.kleer.dojo.exceptions.AssessmentNotFoundException;
import com.kleer.dojo.exceptions.QuestionAnswerNotFoundException;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

@Service
public class AssessmentAnswerServiceImpl implements AssessmentAnswerService{

    private final AssessmentAnswerDao assessmentAnswerDao;

    private final AssessmentDao assessmentDao;

    private final QuestionAnswerDao questionAnswerDao;

    private final Logger logger;

    public AssessmentAnswerServiceImpl(
            AssessmentAnswerDao assessmentAnswerDao,
            AssessmentDao assessmentDao,
            QuestionAnswerDao questionAnswerDao
    ) {
        this.assessmentAnswerDao = assessmentAnswerDao;
        this.assessmentDao = assessmentDao;
        this.questionAnswerDao = questionAnswerDao;
        this.logger = Logger.getLogger(AssessmentAnswerServiceImpl.class);
    }

    @Override
    public Optional<Collection<AssessmentAnswerEntity>> findByAssessmentIdAndStatus(Integer assessmentId, RegisterStatusEnum registerStatusEnum) {
        return this.assessmentAnswerDao.findByAssessmentIdAndStatus(assessmentId, registerStatusEnum);
    }

    @Override
    @Transactional
    public Optional<List<AssessmentAnswerEntity>> saveAll(Collection<Integer> questionAnswerIds, Integer assessmentId) throws AssessmentNotFoundException {
        AssessmentEntity assessmentEntity = this.assessmentDao.findById(assessmentId).orElseThrow(() -> new AssessmentNotFoundException());
        List<AssessmentAnswerEntity> assessmentAnswerEntities = new LinkedList<>();
        questionAnswerIds.forEach(questionAnswerId -> {
            try {
                assessmentAnswerEntities.add(
                        new AssessmentAnswerEntity(
                                RegisterStatusEnum.ACTIVE,
                                this.questionAnswerDao.findByQuestionAnswerIdAndStatus(questionAnswerId, RegisterStatusEnum.ACTIVE)
                                        .orElseThrow(() -> new QuestionAnswerNotFoundException()),
                                assessmentEntity
                        )
                );
            } catch (QuestionAnswerNotFoundException e) {
                this.logger.error(e);
            }
        });
        return Optional.of(this.assessmentAnswerDao.saveAll(assessmentAnswerEntities));
    }
}
