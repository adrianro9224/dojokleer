package com.kleer.dojo.payload;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.List;

public class RegisterAssessmentAnswersForm {

    @NotNull
    @NotEmpty
    private List<Integer> answersIds;

    public RegisterAssessmentAnswersForm() {
    }

    public RegisterAssessmentAnswersForm(@NotNull @NotEmpty List<Integer> answersIds) {
        this.answersIds = answersIds;
    }

    public List<Integer> getAnswersIds() {
        return answersIds;
    }

    public void setAnswersIds(List<Integer> answersIds) {
        this.answersIds = answersIds;
    }
}
