package com.arnoldgalovics.blog.service;

import java.util.function.Consumer;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Component
public class TransactionalRunner {
    @PersistenceContext
    private EntityManager entityManager;

    public void doInTransaction(final Consumer<EntityManager> c) {
        c.accept(entityManager);
    }
}
