package com.arnoldgalovics.blog.domain;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;

public interface PersonRepository extends JpaRepository<Person, Long> {
    Collection<LanguageProjection> findDistinctByCityId(Long cityId);
}
