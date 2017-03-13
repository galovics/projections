package com.arnoldgalovics.blog;

import static java.util.stream.Collectors.toList;
import static org.assertj.core.api.Assertions.assertThat;

import java.util.Collection;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.arnoldgalovics.blog.domain.Language;
import com.arnoldgalovics.blog.domain.LanguageProjection;
import com.arnoldgalovics.blog.service.CityService;
import com.arnoldgalovics.blog.service.CityServiceImpl;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = Configuration.class)
public class ProjectionsTest {
    @Autowired
    private CityService service;

    @Test
    public void testStraightforwardImplementation() {
        Collection<String> result = service.getLanguagesOfCity(CityServiceImpl.CITY_BUDAPEST_ID);
        assertThat(result).containsOnly("EN", "HU");
    }

    @Test
    public void testJPQLProjection() {
        Collection<Language> result = service.getLanguagesOfCityWithJPQL(CityServiceImpl.CITY_BUDAPEST_ID);
        assertThat(result).containsOnly(new Language("EN"), new Language("HU"));
    }

    @Test
    public void testCriteriaAPIProjection() {
        Collection<Language> result = service.getLanguagesOfCityWithCriteriaAPI(CityServiceImpl.CITY_BUDAPEST_ID);
        assertThat(result).containsOnly(new Language("EN"), new Language("HU"));
    }

    @Test
    public void testSpringDataProjection() {
        Collection<LanguageProjection> result = service.getLanguagesOfCityWithSpringDataProjection(CityServiceImpl.CITY_BUDAPEST_ID);
        assertThat(result.stream().map(LanguageProjection::getLanguage).collect(toList())).containsOnly("EN", "HU");
    }
}
