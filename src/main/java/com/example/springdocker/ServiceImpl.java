package com.example.springdocker;

import java.util.List;
import java.util.Optional;

public interface ServiceImpl<T> {

    T save(T t);

    Optional<T> findById(String id);

    List<T> findAll();

    T findByName(String name);

    void deleteById(String id);

}
