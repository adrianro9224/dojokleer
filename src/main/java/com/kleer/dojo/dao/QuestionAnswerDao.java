package com.kleer.dojo.dao;

import com.kleer.dojo.entity.QuestionAnswerEntity;
import com.kleer.dojo.enums.RegisterStatusEnum;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface QuestionAnswerDao extends JpaRepository<QuestionAnswerEntity, Integer> {
    Optional<QuestionAnswerEntity> findByQuestionAnswerIdAndStatus(
            Integer questionAnswerId,
            RegisterStatusEnum registerStatus
    );
}
