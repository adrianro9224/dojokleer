package com.kleer.dojo.service;

import com.kleer.dojo.entity.QuestionCategoryEntity;
import com.kleer.dojo.enums.RegisterStatusEnum;

import java.util.Collection;
import java.util.Optional;

public interface QuestionCategoryService {
    Optional<Collection<QuestionCategoryEntity>> findByStatusIn(RegisterStatusEnum... registerStatus);
}
