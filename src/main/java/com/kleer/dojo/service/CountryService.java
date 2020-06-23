package com.kleer.dojo.service;

import com.kleer.dojo.entity.CountryEntity;
import com.kleer.dojo.enums.RegisterStatusEnum;

import java.util.Collection;
import java.util.Optional;

public interface CountryService {
    Optional<Collection<CountryEntity>> findByStatus(RegisterStatusEnum registerStatusEnum);
    Optional<CountryEntity> findByCountryIdAndStatus(Integer id, RegisterStatusEnum registerStatusEnum);
}
