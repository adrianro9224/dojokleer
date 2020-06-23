package com.kleer.dojo.controller;

import com.kleer.dojo.domain.BodyResponseBase;
import com.kleer.dojo.entity.AssessmentEntity;
import com.kleer.dojo.enums.RegisterStatusEnum;
import com.kleer.dojo.payload.CreateAssessmentForm;
import com.kleer.dojo.service.AssessmentService;
import com.kleer.dojo.service.CountryService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.Optional;

@RestController
@RequestMapping(name = "/assessment")
public class AssessmentController {

    private final AssessmentService assessmentService;

    private final CountryService countryService;

    public AssessmentController(AssessmentService assessmentService, CountryService countryService) {
        this.assessmentService = assessmentService;
        this.countryService = countryService;
    }

    @PostMapping("")
    public ResponseEntity<BodyResponseBase> createOne(@Valid @RequestBody CreateAssessmentForm createAssessmentForm, BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            return new ResponseEntity<>(
                new BodyResponseBase(
                            null,
                            ":(",
                        bindingResult.getAllErrors()

                    ),
                    HttpStatus.BAD_REQUEST);
        }else{
            Optional<AssessmentEntity> optionalAssessmentEntity = this.assessmentService.createOne(
                    new AssessmentEntity(
                            RegisterStatusEnum.ACTIVE,
                            this.countryService.findByCountryIdAndStatus(createAssessmentForm.getCountryId(), RegisterStatusEnum.ACTIVE).get(),
                            createAssessmentForm.getEmail()
                    )
            );
            if(!optionalAssessmentEntity.isPresent()){
                return new ResponseEntity<>(
                    new BodyResponseBase(
                            null,
                            ":'("
                        ),
                        HttpStatus.INTERNAL_SERVER_ERROR
                    );
            }else {
                return ResponseEntity.ok(
                        new BodyResponseBase(
                                optionalAssessmentEntity.get(),
                                ":)"
                        )
                );
            }
        }
    }
}
