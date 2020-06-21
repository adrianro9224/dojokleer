package com.kleer.dojo.controller;

import com.kleer.dojo.domain.BodyResponseBase;
import com.kleer.dojo.entity.QuestionCategoryEntity;
import com.kleer.dojo.enums.RegisterStatusEnum;
import com.kleer.dojo.service.QuestionCategoryService;
import org.apache.log4j.Logger;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;
import java.util.Optional;

@RestController
@RequestMapping("/questioncategory")
public class QuestionCategoryController {

    private final QuestionCategoryService questionCategoryService;
    private final Logger logger;

    public QuestionCategoryController(QuestionCategoryService questionCategoryService) {
        this.questionCategoryService = questionCategoryService;
        this.logger = Logger.getLogger(QuestionCategoryController.class);
    }

    @GetMapping("/status/{registerStatus}")
    public ResponseEntity<BodyResponseBase> getByStatus(@PathVariable("registerStatus") RegisterStatusEnum registerStatusEnum){
        Optional<Collection<QuestionCategoryEntity>> optionalQuestionCategoryEntities = this.questionCategoryService.findByStatusIn(registerStatusEnum);
        if(!optionalQuestionCategoryEntities.isPresent()){
            return ResponseEntity.noContent().build();
        }else{
            return ResponseEntity.ok(
                    new BodyResponseBase(
                            optionalQuestionCategoryEntities.get(),
                            ":D"
                    )
            );
        }
    }
}
