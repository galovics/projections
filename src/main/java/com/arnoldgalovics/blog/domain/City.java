package com.arnoldgalovics.blog.domain;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class City {
    @Id
    private Long id;

    @OneToMany(mappedBy = "city", cascade = CascadeType.ALL)
    private Set<Person> people = new HashSet<>();

    public Long getId() {
        return id;
    }

    public void setId(final Long id) {
        this.id = id;
    }

    public Set<Person> getPeople() {
        return people;
    }
}
