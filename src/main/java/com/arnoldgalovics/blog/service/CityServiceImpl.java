package com.arnoldgalovics.blog.service;

import static java.util.stream.Collectors.toList;

import java.util.Collection;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.arnoldgalovics.blog.domain.City;
import com.arnoldgalovics.blog.domain.Language;
import com.arnoldgalovics.blog.domain.LanguageProjection;
import com.arnoldgalovics.blog.domain.Person;
import com.arnoldgalovics.blog.domain.PersonRepository;

@Service
@Transactional
public class CityServiceImpl implements CityService, InitializingBean {
    public static final Long CITY_BUDAPEST_ID = 1L;

    @PersistenceContext
    private EntityManager entityManager;

    @Autowired
    private PersonRepository pRepository;

    @Autowired
    private TransactionalRunner tRunner;

    @Override
    public void afterPropertiesSet() throws Exception {
        tRunner.doInTransaction(entityManager -> {
            City budapest = new City();
            budapest.setId(CITY_BUDAPEST_ID);
            Person p1 = new Person();
            p1.setLanguage("EN");
            p1.setCity(budapest);
            Person p2 = new Person();
            p2.setLanguage("HU");
            p2.setCity(budapest);
            entityManager.persist(budapest);
        });
    }

    @Override
    public Collection<String> getLanguagesOfCity(final Long cityId) {
        TypedQuery<Person> query = entityManager.createQuery("SELECT p FROM Person p WHERE p.city.id = :id", Person.class);
        query.setParameter("id", cityId);
        Collection<Person> people = query.getResultList();
        return people.stream().map(Person::getLanguage).distinct().collect(toList());
    }

    @Override
    public Collection<Language> getLanguagesOfCityWithJPQL(final Long cityId) {
        TypedQuery<Language> query = entityManager
                .createQuery("SELECT DISTINCT NEW com.arnoldgalovics.blog.domain.Language(p.language) FROM Person p WHERE p.city.id = :id", Language.class);
        query.setParameter("id", cityId);
        return query.getResultList();
    }

    @Override
    public Collection<Language> getLanguagesOfCityWithCriteriaAPI(final Long cityId) {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<Language> query = cb.createQuery(Language.class);
        Root<Person> root = query.from(Person.class);
        query.select(cb.construct(Language.class, root.get("language")));
        query.where(cb.equal(root.get("city").get("id"), cityId));
        query.distinct(true);
        return entityManager.createQuery(query).getResultList();
    }

    @Override
    public Collection<LanguageProjection> getLanguagesOfCityWithSpringDataProjection(final Long cityId) {
        return pRepository.findDistinctByCityId(cityId);
    }
}
