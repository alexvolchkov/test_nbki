package ru.volchkov.test_nbki.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.volchkov.test_nbki.model.Cat;

/**

 * @author Aleksandr Volchkov
 */
public interface CatRepository extends JpaRepository<Cat, Integer> {
}