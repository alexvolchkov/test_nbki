package ru.volchkov.test_nbki.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.volchkov.test_nbki.model.Cat;
import ru.volchkov.test_nbki.repository.CatRepository;

import java.util.List;
import java.util.Optional;

/**
 * @author Aleksandr Volchkov
 */

@Service
@AllArgsConstructor
public class CatServiceImpl implements CatService {

    private final CatRepository catRepository;

    @Override
    public Cat save(Cat cat) {
        return catRepository.save(cat);
    }

    @Override
    public Optional<Cat> findById(int id) {
        return catRepository.findById(id);
    }

    @Override
    public void deleteById(int id) {
        catRepository.deleteById(id);
    }

    @Override
    public void update(Cat cat) {
        catRepository.save(cat);
    }

    @Override
    public List<Cat> findAll() {
        return catRepository.findAll();
    }
}
