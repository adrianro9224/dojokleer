package com.kleer.dojo.service;

import com.kleer.dojo.dao.CountryDao;
import com.kleer.dojo.entity.CountryEntity;
import com.kleer.dojo.enums.RegisterStatusEnum;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Optional;

@Service
public class CountryServiceImpl implements CountryService{
    private CountryDao countryDao;

    public CountryServiceImpl(CountryDao countryDao) {
        this.countryDao = countryDao;
    }

    @Override
    public Optional<Collection<CountryEntity>> findByStatus(RegisterStatusEnum registerStatusEnum) {
        return this.countryDao.findByStatus(registerStatusEnum);
    }
}
