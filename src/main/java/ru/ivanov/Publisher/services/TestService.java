package ru.ivanov.Publisher.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.ivanov.Publisher.models.Test;
import ru.ivanov.Publisher.repositories.TestRepository;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

/**
 * Is it necessary to add validation on this abstraction level?
 *
 * @author Ivan Ivanov
 */
@Service
@Transactional(readOnly = true)
public class TestService {
    private final TestRepository testRepository;

    @Autowired
    public TestService(TestRepository testRepository) {
        this.testRepository = testRepository;
    }

    @Transactional
    public void create(Test test) {
        testRepository.save(test);
    }

    public Test read(int id) {
        Optional<Test> foundPerson = testRepository.findById(id);
        return foundPerson.orElseThrow(() -> new NoSuchElementException("There is no element with such id"));
    }

    public List<Test> readAll() {
        return testRepository.findAll();
    }

    @Transactional
    public void update(Test test) {
        Optional<Test> entityWithSameId = testRepository.findById(test.getId());
        if (entityWithSameId.isPresent()) {
            testRepository.save(test);
        }
    }

    @Transactional
    public void delete(int id){
        testRepository.deleteById(id);
    }
}
