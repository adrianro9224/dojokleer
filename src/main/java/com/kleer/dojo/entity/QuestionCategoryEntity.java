package com.kleer.dojo.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.kleer.dojo.enums.RegisterStatusEnum;

import javax.persistence.*;
import java.util.Collection;

@Entity(name = "question_category")
public class QuestionCategoryEntity extends AbstractEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "question_category_id", unique = true, nullable = false)
    private Integer questionCategoryId;

    @Column(name = "category_name", unique = true, nullable = false)
    private String categoryName;

    @Column(name = "category_description", length = 512)
    private String categoryDescription;

    @JsonManagedReference
    @OneToMany(mappedBy = "questionCategoryEntity")
    private Collection<QuestionEntity> questionEntities;
    
    @Column(name = "order", nullable = false, unique = true)
    private Integer order;

    public QuestionCategoryEntity() {
    }

    public QuestionCategoryEntity(RegisterStatusEnum status, String categoryName, String categoryDescription, Integer order) {
        super(status);
        this.categoryName = categoryName;
        this.categoryDescription = categoryDescription;
        this.order = order;
    }

    public Integer getQuestionCategoryId() {
        return questionCategoryId;
    }

    public void setQuestionCategoryId(Integer questionCategoryId) {
        this.questionCategoryId = questionCategoryId;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public String getCategoryDescription() {
        return categoryDescription;
    }

    public void setCategoryDescription(String categoryDescription) {
        this.categoryDescription = categoryDescription;
    }

    public Collection<QuestionEntity> getQuestionEntities() {
        return questionEntities;
    }

    public void setQuestionEntities(Collection<QuestionEntity> questionEntities) {
        this.questionEntities = questionEntities;
    }
    
    public Integer getOrder() {
        return order;
    }
    
    public void setOrder(Integer order) {
        this.order = order;
    }
}
