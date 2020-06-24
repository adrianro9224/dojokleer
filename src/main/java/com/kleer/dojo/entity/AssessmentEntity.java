package com.kleer.dojo.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.kleer.dojo.enums.RegisterStatusEnum;

import javax.persistence.*;

@Entity(name = "assessment")
public class AssessmentEntity extends AbstractEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "assessment_id", unique = true, nullable = false)
    private Integer assessmentId;

    @Column(name = "country_id", nullable = false, updatable = false, insertable = false)
    private Integer countryId;

    @JsonBackReference
    @ManyToOne
    @JoinColumn(name = "country_id")
    private CountryEntity countryEntity;

    @Column(name = "email", nullable = false)
    private String email;

    public AssessmentEntity() {
    }

    public AssessmentEntity(RegisterStatusEnum status, CountryEntity countryEntity, String email) {
        super(status);
        this.countryEntity = countryEntity;
        this.email = email;
    }

    public Integer getAssessmentId() {
        return assessmentId;
    }

    public void setAssessmentId(Integer assessmentId) {
        this.assessmentId = assessmentId;
    }

    public Integer getCountryId() {
        return countryId;
    }

    public void setCountryId(Integer countryId) {
        this.countryId = countryId;
    }

    public CountryEntity getCountryEntity() {
        return countryEntity;
    }

    public void setCountryEntity(CountryEntity countryEntity) {
        this.countryEntity = countryEntity;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
