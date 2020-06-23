package com.kleer.dojo.entity;

import com.kleer.dojo.enums.RegisterStatusEnum;

import javax.persistence.*;

@Entity(name = "country")
public class CountryEntity  extends AbstractEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "country_id", unique = true, nullable = false)
    private Integer countryId;

    @Column(name = "name", nullable = false, unique = true)
    private String name;

    public CountryEntity() {
    }

    public CountryEntity(RegisterStatusEnum status, String name) {
        super(status);
        this.name = name;
    }

    public Integer getCountryId() {
        return countryId;
    }

    public void setCountryId(Integer countryId) {
        this.countryId = countryId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
