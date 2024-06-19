package ru.volchkov.test_nbki.service;

import ru.volchkov.test_nbki.model.Cat;

import java.util.List;
import java.util.Optional;

/**
 * @author Aleksandr Volchkov
 */
public interface CatService {

    Cat save(Cat cat);

    Optional<Cat> findById(int id);

    void deleteById(int id);

    void update(Cat cat);

    List<Cat> findAll();
}
