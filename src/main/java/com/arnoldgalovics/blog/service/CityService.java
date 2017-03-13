package com.arnoldgalovics.blog.service;

import java.util.Collection;

import com.arnoldgalovics.blog.domain.Language;
import com.arnoldgalovics.blog.domain.LanguageProjection;

public interface CityService {
    Collection<String> getLanguagesOfCity(Long cityId);

    Collection<Language> getLanguagesOfCityWithJPQL(Long cityId);

    Collection<Language> getLanguagesOfCityWithCriteriaAPI(Long cityId);

    Collection<LanguageProjection> getLanguagesOfCityWithSpringDataProjection(Long cityId);
}
