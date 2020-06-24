package com.kleer.dojo.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.kleer.dojo.enums.RegisterStatusEnum;

import javax.persistence.*;

@Entity(name = "assessment_answer")
public class AssessmentAnswerEntity extends AbstractEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "assessment_answer_id", nullable = false, unique = true)
    private Integer assessmentAnswerId;

    @Column(name = "question_answer_id", nullable = false, updatable = false, insertable = false)
    private Integer questionAnswerId;

    @JsonBackReference
    @ManyToOne
    @JoinColumn(name = "question_answer_id")
    private QuestionAnswerEntity questionAnswerEntity;

    @Column(name = "assessment_id", nullable = false, updatable = false, insertable = false)
    private Integer assessmentId;

    @JsonBackReference
    @ManyToOne
    @JoinColumn(name = "assessment_id")
    private AssessmentEntity assessmentEntity;

    public AssessmentAnswerEntity() {
    }

    public AssessmentAnswerEntity(RegisterStatusEnum status, QuestionAnswerEntity questionAnswerEntity, AssessmentEntity assessmentEntity) {
        super(status);
        this.questionAnswerEntity = questionAnswerEntity;
        this.assessmentEntity = assessmentEntity;
    }

    public Integer getAssessmentAnswerId() {
        return assessmentAnswerId;
    }

    public void setAssessmentAnswerId(Integer assessmentAnswerId) {
        this.assessmentAnswerId = assessmentAnswerId;
    }

    public Integer getQuestionAnswerId() {
        return questionAnswerId;
    }

    public void setQuestionAnswerId(Integer questionAnswerId) {
        this.questionAnswerId = questionAnswerId;
    }

    public QuestionAnswerEntity getQuestionAnswerEntity() {
        return questionAnswerEntity;
    }

    public void setQuestionAnswerEntity(QuestionAnswerEntity questionEntity) {
        this.questionAnswerEntity = questionEntity;
    }

    public Integer getAssessmentId() {
        return assessmentId;
    }

    public void setAssessmentId(Integer assessmentId) {
        this.assessmentId = assessmentId;
    }

    public AssessmentEntity getAssessmentEntity() {
        return assessmentEntity;
    }

    public void setAssessmentEntity(AssessmentEntity assessmentEntity) {
        this.assessmentEntity = assessmentEntity;
    }
}
