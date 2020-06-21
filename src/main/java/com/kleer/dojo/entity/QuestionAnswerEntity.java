package com.kleer.dojo.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.kleer.dojo.enums.RegisterStatusEnum;

import javax.persistence.*;

@Entity(name = "question_answer")
public class QuestionAnswerEntity extends AbstractEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "question_answer", nullable = false, unique = true)
    private Integer questionAnswerId;

    @Column(name = "question_id", nullable = false, insertable = false, updatable = false)
    private Integer questionId;

    @JsonBackReference
    @ManyToOne
    @JoinColumn(name = "question_id")
    private QuestionEntity questionEntity;

    @Column(name = "answer", nullable = false, length = 512)
    private String answer;

    @Column(name = "brings", nullable = false)
    private Integer brings;

    public QuestionAnswerEntity() {
    }

    public QuestionAnswerEntity(RegisterStatusEnum status, QuestionEntity questionEntity, String answer, Integer brings) {
        super(status);
        this.questionEntity = questionEntity;
        this.answer = answer;
        this.brings = brings;
    }

    public Integer getQuestionAnswerId() {
        return questionAnswerId;
    }

    public void setQuestionAnswerId(Integer questionAnswerId) {
        this.questionAnswerId = questionAnswerId;
    }

    public Integer getQuestionId() {
        return questionId;
    }

    public void setQuestionId(Integer questionId) {
        this.questionId = questionId;
    }

    public QuestionEntity getQuestionEntity() {
        return questionEntity;
    }

    public void setQuestionEntity(QuestionEntity questionEntity) {
        this.questionEntity = questionEntity;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public Integer getBrings() {
        return brings;
    }

    public void setBrings(Integer brings) {
        this.brings = brings;
    }
}
