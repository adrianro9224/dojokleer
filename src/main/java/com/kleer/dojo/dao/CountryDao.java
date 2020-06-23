package com.kleer.dojo.dao;

import com.kleer.dojo.entity.CountryEntity;
import com.kleer.dojo.enums.RegisterStatusEnum;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Collection;
import java.util.Optional;

public interface CountryDao extends JpaRepository<CountryEntity, Integer> {
    Optional<Collection<CountryEntity>> findByStatus(RegisterStatusEnum registerStatusEnum);
    Optional<CountryEntity> findByCountryIdAndStatus(Integer id, RegisterStatusEnum registerStatusEnum);
}
