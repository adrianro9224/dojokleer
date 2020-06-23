package com.kleer.dojo.controller;

import com.kleer.dojo.domain.BodyResponseBase;
import com.kleer.dojo.entity.CountryEntity;
import com.kleer.dojo.enums.RegisterStatusEnum;
import com.kleer.dojo.service.CountryService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;
import java.util.Optional;

@RestController
@RequestMapping(name = "/country")
public class CountryController {

    private final CountryService countryService;

    public CountryController(CountryService countryService) {
        this.countryService = countryService;
    }

    @RequestMapping("/{registerStatus}")
    public ResponseEntity<BodyResponseBase> getByStatus(@PathVariable("registerStatus")RegisterStatusEnum registerStatusEnum){
        Optional<Collection<CountryEntity>> collectionOptional = this.countryService.findByStatus(registerStatusEnum);
        if(!collectionOptional.isPresent()) {
            return ResponseEntity.noContent().build();
        }else{
            return ResponseEntity.ok(
                    new BodyResponseBase(
                            collectionOptional.get(),
                            ":)"
                    )
            );
        }
    }
}
