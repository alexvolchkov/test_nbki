package ru.volchkov.test_nbki.controller;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.volchkov.test_nbki.model.Cat;
import ru.volchkov.test_nbki.service.CatService;

import java.util.List;
import java.util.Optional;

/**
 * @author Aleksandr Volchkov
 */

@RestController
@RequestMapping("/api/v1/cat")
@AllArgsConstructor
public class CatController {

    private final CatService catService;

    @GetMapping("/")
    public List<Cat> findAll() {
        return catService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Cat> findById(@PathVariable int id) {
        Optional<Cat> cat = catService.findById(id);
        return new ResponseEntity<>(
                cat.orElse(null),
                cat.isPresent() ? HttpStatus.OK : HttpStatus.NOT_FOUND
        );
    }

    @PostMapping("/")
    public ResponseEntity<Cat> create(@RequestBody Cat cat) {
        return new ResponseEntity<>(
                catService.save(cat),
                HttpStatus.CREATED
        );
    }

    @PutMapping("/")
    public ResponseEntity<Void> update(@RequestBody Cat cat) {
        catService.update(cat);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> update(@PathVariable int id) {
        catService.deleteById(id);
        return ResponseEntity.ok().build();
    }
}
