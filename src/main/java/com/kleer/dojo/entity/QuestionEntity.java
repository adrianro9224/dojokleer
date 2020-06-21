package com.kleer.dojo.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.kleer.dojo.enums.RegisterStatusEnum;

import javax.persistence.*;
import java.util.Collection;

@Entity(name = "question_entity")
public class QuestionEntity extends AbstractEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "question_id", unique = true, nullable = false)
    private Integer questionId;

    @Column(name = "question", nullable = false, unique = false)
    private String question;

    @Column(name = "question_category_id", nullable = false, insertable = false, updatable = false)
    private Integer questionCategoryId;

    @JsonBackReference
    @ManyToOne
    @JoinColumn(name = "question_category_id")
    private QuestionCategoryEntity questionCategoryEntity;

    @JsonManagedReference
    @OneToMany(mappedBy = "questionEntity")
    private Collection<QuestionAnswerEntity> answerEntities;

    public QuestionEntity() {
    }

    public QuestionEntity(RegisterStatusEnum status, String question, QuestionCategoryEntity questionCategoryEntity) {
        super(status);
        this.question = question;
        this.questionCategoryEntity = questionCategoryEntity;
    }

    public Integer getQuestionId() {
        return questionId;
    }

    public void setQuestionId(Integer questionId) {
        this.questionId = questionId;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public Integer getQuestionCategoryId() {
        return questionCategoryId;
    }

    public void setQuestionCategoryId(Integer questionCategoryId) {
        this.questionCategoryId = questionCategoryId;
    }

    public QuestionCategoryEntity getQuestionCategoryEntity() {
        return questionCategoryEntity;
    }

    public void setQuestionCategoryEntity(QuestionCategoryEntity questionCategoryEntity) {
        this.questionCategoryEntity = questionCategoryEntity;
    }

    public Collection<QuestionAnswerEntity> getAnswerEntities() {
        return answerEntities;
    }

    public void setAnswerEntities(Collection<QuestionAnswerEntity> answerEntities) {
        this.answerEntities = answerEntities;
    }
}
