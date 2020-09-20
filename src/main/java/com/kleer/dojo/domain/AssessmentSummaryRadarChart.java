package com.kleer.dojo.domain;

import com.kleer.dojo.entity.AssessmentAnswerEntity;
import com.kleer.dojo.entity.QuestionCategoryEntity;

import java.util.Collection;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

public class AssessmentSummaryRadarChart {
    private Collection<String> categoryLabels;
    private String label;
    private Collection<Integer> data;

    public AssessmentSummaryRadarChart() {
    }

    public AssessmentSummaryRadarChart(Collection<String> categoryLabels, String label, Collection<Integer> data) {
        this.categoryLabels = categoryLabels;
        this.label = label;
        this.data = data;
    }

    public AssessmentSummaryRadarChart(Collection<AssessmentAnswerEntity> assessmentAnswerEntities, String label) {
        this.categoryLabels = new LinkedList<>();
        this.data = new LinkedList<>();
        this.label = label;
        HashMap<String, Integer> categoryPoints = new HashMap<>();
        List<QuestionCategoryEntity> categories = new LinkedList<>();
        assessmentAnswerEntities.forEach(assessmentAnswerEntity -> {
            categories.add(assessmentAnswerEntity.getQuestionAnswerEntity().getQuestionEntity().getQuestionCategoryEntity());
            categoryPoints.put(
                    assessmentAnswerEntity.getQuestionAnswerEntity().getQuestionEntity().getQuestionCategoryEntity().getCategoryName(),
                    assessmentAnswerEntity.getQuestionAnswerEntity().getBrings()
            );
        });
        categories.stream()
            .sorted((questionCategoryEntity, t1) -> questionCategoryEntity.getOrder().compareTo(t1.getOrder()))
            .forEach(questionCategoryEntity -> {
                this.categoryLabels.add(questionCategoryEntity.getCategoryName());
                this.data.add(categoryPoints.get(questionCategoryEntity.getCategoryName()));
            });
    }

    public Collection<String> getCategoryLabels() {
        return categoryLabels;
    }

    public void setCategoryLabels(Collection<String> categoryLabels) {
        this.categoryLabels = categoryLabels;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public Collection<Integer> getData() {
        return data;
    }

    public void setData(Collection<Integer> data) {
        this.data = data;
    }
}
