package com.kleer.dojo.payload;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public class CreateAssessmentForm {
    @NotNull
    private Integer countryId;

    @NotNull
    @NotEmpty
    private String email;

    public CreateAssessmentForm() {
    }

    public CreateAssessmentForm(@NotNull Integer countryId, @NotNull @NotEmpty String email) {
        this.countryId = countryId;
        this.email = email;
    }

    public Integer getCountryId() {
        return countryId;
    }

    public void setCountryId(Integer countryId) {
        this.countryId = countryId;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
