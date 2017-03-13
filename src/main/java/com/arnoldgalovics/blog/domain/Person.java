package com.arnoldgalovics.blog.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class Person {
    @Id
    @GeneratedValue
    private Long id;

    @Column(name = "language")
    private String language;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "city_fk", referencedColumnName = "id")
    private City city;

    public City getCity() {
        return city;
    }

    public void setCity(final City city) {
        city.getPeople().add(this);
        this.city = city;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(final String language) {
        this.language = language;
    }
}
