package com.kleer.dojo.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.kleer.dojo.entity.*;
import com.kleer.dojo.enums.RegisterStatusEnum;
import com.kleer.dojo.exceptions.AssessmentNotFoundException;
import com.kleer.dojo.payload.CreateAssessmentForm;
import com.kleer.dojo.payload.RegisterAssessmentAnswersForm;
import com.kleer.dojo.service.AssessmentAnswerService;
import com.kleer.dojo.service.AssessmentService;
import com.kleer.dojo.service.CountryService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentMatchers;
import org.mockito.BDDMockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.Arrays;
import java.util.Collections;
import java.util.Optional;

@RunWith(SpringRunner.class)
@WebMvcTest(AssessmentController.class)
public class AssessmentControllerTest {
    
    @MockBean
    private AssessmentService assessmentService;
    
    @MockBean
    private AssessmentAnswerService assessmentAnswerService;
    
    @MockBean
    private CountryService countryService;
    
    @Autowired
    private MockMvc mockMvc;
    
    private CreateAssessmentForm createAssessmentForm;
    
    private ObjectMapper objectMapper;
    
    private RegisterAssessmentAnswersForm registerAssessmentAnswersForm;
    
    @BeforeEach
    public void init() {
        this.objectMapper = new ObjectMapper();
        this.createAssessmentForm = new CreateAssessmentForm(1, "test@mailinator.com");
        this.registerAssessmentAnswersForm = new RegisterAssessmentAnswersForm(Arrays.asList(1, 2));
    }
    
    @Test
    public void createOneReturn400() throws Exception{
        this.createAssessmentForm.setCountryId(null);
        mockMvc.perform(
            MockMvcRequestBuilders
                .post("/assessment")
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .content(this.objectMapper.writeValueAsString(this.createAssessmentForm))
        ).andExpect(MockMvcResultMatchers.status().isBadRequest());
    }
    
    @Test
    public void createOneReturn500() throws Exception{
        BDDMockito.given(
            this.assessmentService.createOne(ArgumentMatchers.any(AssessmentEntity.class))
        ).willReturn(Optional.empty());
        
        BDDMockito.given(
            this.countryService.findByCountryIdAndStatus(ArgumentMatchers.any(Integer.class), ArgumentMatchers.any(RegisterStatusEnum.class))
        ).willReturn(Optional.of(new CountryEntity()));
        mockMvc.perform(
            MockMvcRequestBuilders
                .post("/assessment")
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .content(this.objectMapper.writeValueAsString(this.createAssessmentForm))
        ).andExpect(MockMvcResultMatchers.status().isInternalServerError());
    }
    
    @Test
    public void createOneReturn200() throws Exception{
        BDDMockito.given(
            this.assessmentService.createOne(ArgumentMatchers.any(AssessmentEntity.class))
        ).willReturn(Optional.of(new AssessmentEntity()));
        
        BDDMockito.given(
            this.countryService.findByCountryIdAndStatus(ArgumentMatchers.any(Integer.class), ArgumentMatchers.any(RegisterStatusEnum.class))
        ).willReturn(Optional.of(new CountryEntity()));
        
        mockMvc.perform(
            MockMvcRequestBuilders
                .post("/assessment")
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .content(this.objectMapper.writeValueAsString(this.createAssessmentForm))
        ).andExpect(MockMvcResultMatchers.status().isOk());
    }
    
    @Test
    public void registerAssessmentAnswersReturn400() throws Exception{
        this.registerAssessmentAnswersForm.setAnswersIds(null);
        mockMvc.perform(
            MockMvcRequestBuilders
                .post("/assessment/1/answers")
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .content(this.objectMapper.writeValueAsString(this.registerAssessmentAnswersForm))
        ).andExpect(MockMvcResultMatchers.status().isBadRequest());
    }
    
    @Test
    public void registerAssessmentAnswersReturn500() throws Exception{
        BDDMockito.given(this.assessmentAnswerService.saveAll(this.registerAssessmentAnswersForm.getAnswersIds(), 1))
            .willReturn(Optional.empty());
        mockMvc.perform(
            MockMvcRequestBuilders
                .post("/assessment/1/answers")
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .content(this.objectMapper.writeValueAsString(this.registerAssessmentAnswersForm))
        ).andExpect(MockMvcResultMatchers.status().isInternalServerError());
    }
    
    @Test
    public void registerAssessmentAnswersReturn400ThrowingAssessmentNotFound() throws Exception{
        BDDMockito.given(this.assessmentAnswerService.saveAll(this.registerAssessmentAnswersForm.getAnswersIds(), 1))
            .willThrow(AssessmentNotFoundException.class);
        mockMvc.perform(
            MockMvcRequestBuilders
                .post("/assessment/1/answers")
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .content(this.objectMapper.writeValueAsString(this.registerAssessmentAnswersForm))
        ).andExpect(MockMvcResultMatchers.status().isBadRequest());
    }
    
    @Test
    public void registerAssessmentAnswersReturn200() throws Exception{
        BDDMockito.given(this.assessmentAnswerService.saveAll(this.registerAssessmentAnswersForm.getAnswersIds(), 1))
            .willReturn(Optional.of(Collections.singletonList(new AssessmentAnswerEntity())));
        mockMvc.perform(
            MockMvcRequestBuilders
                .post("/assessment/1/answers")
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .content(this.objectMapper.writeValueAsString(this.registerAssessmentAnswersForm))
        ).andExpect(MockMvcResultMatchers.status().isOk());
    }
    
    @Test
    public void getAssessmentSummaryByAssessmentIdReturn204() throws Exception{
        BDDMockito.given(
            this.assessmentAnswerService
                .findByAssessmentIdAndStatus(
                    ArgumentMatchers.any(Integer.class),
                    ArgumentMatchers.any(RegisterStatusEnum.class)
                )
        ).willReturn(Optional.empty());
        mockMvc.perform(
            MockMvcRequestBuilders
                .get("/assessment/1/summary")
                .accept(MediaType.APPLICATION_JSON)
        ).andExpect(MockMvcResultMatchers.status().isNoContent());
    }
    
    @Test
    public void getAssessmentSummaryByAssessmentIdReturn200() throws Exception{
        AssessmentAnswerEntity assessmentAnswerEntity = new AssessmentAnswerEntity();
        assessmentAnswerEntity.setAssessmentAnswerId(1);
        assessmentAnswerEntity.setAssessmentEntity(
            new AssessmentEntity(
                RegisterStatusEnum.ACTIVE,
                new CountryEntity(RegisterStatusEnum.ACTIVE, "City x"),
                "a@b.com"
            )
        );
        assessmentAnswerEntity.setQuestionAnswerId(1);
        assessmentAnswerEntity.setQuestionAnswerEntity(
            new QuestionAnswerEntity(
                RegisterStatusEnum.ACTIVE,
                new QuestionEntity(
                    RegisterStatusEnum.ACTIVE,
                    "Question 1",
                    new QuestionCategoryEntity(
                        RegisterStatusEnum.ACTIVE,
                        "Category name",
                        "Category description",
                        1
                    )
                ),
                "Answer 1",
                5
            )
        );
        BDDMockito.given(
            this.assessmentAnswerService
                .findByAssessmentIdAndStatus(
                    ArgumentMatchers.any(Integer.class),
                    ArgumentMatchers.any(RegisterStatusEnum.class)
                )
        ).willReturn(Optional.of(Collections.singletonList(assessmentAnswerEntity)));
        
        mockMvc.perform(
            MockMvcRequestBuilders
                .get("/assessment/1/summary")
                .accept(MediaType.APPLICATION_JSON)
        ).andExpect(MockMvcResultMatchers.status().isOk());
    }
}
