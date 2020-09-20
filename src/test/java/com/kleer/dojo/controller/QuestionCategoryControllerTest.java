package com.kleer.dojo.controller;

import com.kleer.dojo.entity.QuestionCategoryEntity;
import com.kleer.dojo.enums.RegisterStatusEnum;
import com.kleer.dojo.service.QuestionCategoryService;
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

import java.util.Collections;
import java.util.Optional;

@RunWith(SpringRunner.class)
@WebMvcTest(QuestionCategoryController.class)
public class QuestionCategoryControllerTest {
    
    @MockBean
    private QuestionCategoryService questionCategoryService;
    
    @Autowired
    private MockMvc mockMvc;
    
    @BeforeEach
    public void init(){
    
    }
    
    @Test
    public void getByStatusReturn400() throws Exception{
        mockMvc.perform(
            MockMvcRequestBuilders.get(
                "/questioncategory/status/X"
            ).accept(MediaType.APPLICATION_JSON)
        ).andExpect(MockMvcResultMatchers.status().isBadRequest());
    }
    
    @Test
    public void getByStatusReturn204() throws Exception{
        BDDMockito.given(
            this.questionCategoryService.findByStatusIn(ArgumentMatchers.any(RegisterStatusEnum.class))
        ).willReturn(Optional.empty());
        mockMvc.perform(
            MockMvcRequestBuilders.get(
                "/questioncategory/status/"+ RegisterStatusEnum.ACTIVE.toString()
            ).accept(MediaType.APPLICATION_JSON)
        ).andExpect(MockMvcResultMatchers.status().isNoContent());
    }
    
    @Test
    public void getByStatusReturn200() throws Exception{
        BDDMockito.given(
            this.questionCategoryService.findByStatusIn(ArgumentMatchers.any(RegisterStatusEnum.class))
        ).willReturn(Optional.of(Collections.singletonList(new QuestionCategoryEntity())));
        mockMvc.perform(
            MockMvcRequestBuilders.get(
                "/questioncategory/status/"+ RegisterStatusEnum.ACTIVE.toString()
            ).accept(MediaType.APPLICATION_JSON)
        ).andExpect(MockMvcResultMatchers.status().isOk());
    }
}
