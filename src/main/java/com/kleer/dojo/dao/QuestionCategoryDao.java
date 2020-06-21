package com.kleer.dojo.dao;

import com.kleer.dojo.entity.QuestionCategoryEntity;
import com.kleer.dojo.enums.RegisterStatusEnum;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Collection;
import java.util.Optional;

public interface QuestionCategoryDao extends JpaRepository<QuestionCategoryEntity, Integer> {
    Optional<Collection<QuestionCategoryEntity>> findByStatusIn(RegisterStatusEnum... registerStatus);
}
