package com.kleer.dojo.controller;

import com.kleer.dojo.domain.AssessmentSummaryRadarChart;
import com.kleer.dojo.domain.BodyResponseBase;
import com.kleer.dojo.entity.AssessmentAnswerEntity;
import com.kleer.dojo.entity.AssessmentEntity;
import com.kleer.dojo.enums.RegisterStatusEnum;
import com.kleer.dojo.exceptions.AssessmentNotFoundException;
import com.kleer.dojo.payload.CreateAssessmentForm;
import com.kleer.dojo.payload.RegisterAssessmentAnswersForm;
import com.kleer.dojo.service.AssessmentAnswerService;
import com.kleer.dojo.service.AssessmentService;
import com.kleer.dojo.service.CountryService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/assessment")
public class AssessmentController {

    private final AssessmentService assessmentService;

    private final AssessmentAnswerService assessmentAnswerService;

    private final CountryService countryService;

    public AssessmentController(
            AssessmentService assessmentService,
            CountryService countryService,
            AssessmentAnswerService assessmentAnswerService
    ) {
        this.assessmentService = assessmentService;
        this.countryService = countryService;
        this.assessmentAnswerService = assessmentAnswerService;
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

    @PostMapping("/{assessmentId}/answers")
    public ResponseEntity<BodyResponseBase> registerAssessmentAnswers(
            @PathVariable("assessmentId") Integer assessmentId,
            @Valid @RequestBody RegisterAssessmentAnswersForm registerAssessmentAnswersForm,
            BindingResult bindingResult
    ){
        if(bindingResult.hasErrors()){
            return new ResponseEntity<>(
                new BodyResponseBase(
                            null,
                            ":(",
                            bindingResult.getAllErrors()
                    ),
                    HttpStatus.BAD_REQUEST);   
        }else {
            try {
                Optional<List<AssessmentAnswerEntity>> optionalAssessmentAnswerEntities = this.assessmentAnswerService.saveAll(registerAssessmentAnswersForm.getAnswersIds(), assessmentId);
                if(!optionalAssessmentAnswerEntities.isPresent()){
                    return new ResponseEntity<>(
                        new BodyResponseBase(
                                null,
                                "Ocurrio un error durante la creacion de la respuestas del assessment"
                            ),
                            HttpStatus.INTERNAL_SERVER_ERROR
                        );
                }else {
                    return ResponseEntity.ok(
                            new BodyResponseBase(
                                    null,
                                        ":)"
                            )
                    );
                }
            } catch (AssessmentNotFoundException e) {
                return new ResponseEntity<>(
                    new BodyResponseBase(
                                null,
                                "El assessment no existe o es invalido"

                        ),
                        HttpStatus.BAD_REQUEST);
            }
        }
    }

    @GetMapping("/{assessmentId}/summary")
    public ResponseEntity<BodyResponseBase> getAssessmentSummaryByAssessmentId(
            @PathVariable("assessmentId") Integer assessmentId
    ){
        Optional<Collection<AssessmentAnswerEntity>> optionalAssessmentAnswerEntities =
                this.assessmentAnswerService.findByAssessmentIdAndStatus(assessmentId, RegisterStatusEnum.ACTIVE);
        if(!optionalAssessmentAnswerEntities.isPresent()){
            return ResponseEntity.noContent().build();
        }else {
            Collection<AssessmentAnswerEntity> assessmentAnswerEntities = optionalAssessmentAnswerEntities.get();
            return ResponseEntity.ok(
                    new BodyResponseBase(
                            new AssessmentSummaryRadarChart(
                                    assessmentAnswerEntities,
                                    "Assessment: " + assessmentAnswerEntities.stream().collect(Collectors.toList()).get(0).getAssessmentEntity().getEmail()
                            ),
                            "Assessment completed"
                    )
            );
        }
    }
}
