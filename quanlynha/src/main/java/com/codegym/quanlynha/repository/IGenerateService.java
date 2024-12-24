package com.codegym.quanlynha.repository;

import java.util.Optional;

public interface IGenerateService<E> {
    Iterable<E> findAll();

    Optional<E> findById(int id);

    void save(E e);

    void remove(Long id);
}
