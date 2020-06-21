package com.kleer.dojo.service;

import com.kleer.dojo.dao.QuestionCategoryDao;
import com.kleer.dojo.entity.QuestionCategoryEntity;
import com.kleer.dojo.enums.RegisterStatusEnum;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Optional;

@Service
public class QuestionCategoryServiceImpl implements QuestionCategoryService{

    private final QuestionCategoryDao questionCategoryDao;

    public QuestionCategoryServiceImpl(QuestionCategoryDao questionCategoryDao) {
        this.questionCategoryDao = questionCategoryDao;
    }

    @Override
    public Optional<Collection<QuestionCategoryEntity>> findByStatusIn(RegisterStatusEnum... registerStatus) {
        return this.questionCategoryDao.findByStatusIn(registerStatus);
    }
}
