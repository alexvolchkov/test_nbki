package ru.volchkov.test_nbki.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import ru.volchkov.test_nbki.model.Cat;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * @author Aleksandr Volchkov
 */
@SpringBootTest
class CatServiceImplTest {

    @Autowired
    private CatService catService;

    @Test
    void whenCreate100_000_Cats() {
        for (int i = 0; i < 100_000; i++) {
            catService.save(Cat.builder().name("Cat" + i).age(i % 20).build());
        }
        int size = catService.findAll().size();
        assertEquals(100_000, size);
    }

    @Test
    void whenRead1_000_000_Cats() {
        for (int i = 0; i < 100_000; i++) {
            catService.save(Cat.builder().name("Cat" + i).age(i % 20).build());
        }
        Random random = new Random();
        ExecutorService executor = Executors.newFixedThreadPool(100);
        List<Long> times = Collections.synchronizedList(new ArrayList<>());
        for (int i = 0; i < 1_000_000; i++) {
            int index = random.nextInt(100_000) + 1;
            executor.submit(new CatRunnable(times, index, catService));
        }
        executor.shutdown();
        while (!executor.isTerminated()) {
        }
        long totalTime = times.stream().mapToLong(Long::longValue).sum();
        Collections.sort(times);
        long median = times.get(times.size() / 2);
        int index95th = (int) (times.size() * 0.95);
        int index99th = (int) (times.size() * 0.99);
        long percentile95th = times.get(index95th);
        long percentile99th = times.get(index99th);
        System.out.println("Total Time: " + totalTime);
        System.out.println("Median Time: " + median);
        System.out.println("95th Percentile: " + percentile95th);
        System.out.println("99th Percentile: " + percentile99th);
    }

    private class CatRunnable implements Runnable {
        private final List<Long> times;
        private final int id;
        private final CatService catService;

        private CatRunnable(List<Long> times, int id, CatService catService) {
            this.times = times;
            this.id = id;
            this.catService = catService;
        }

        @Override
        public void run() {
            long startTime = System.currentTimeMillis();
            catService.findById(id);
            long endTime = System.currentTimeMillis();
            synchronized (times) {
                times.add(endTime - startTime);
            }
        }
    }

}