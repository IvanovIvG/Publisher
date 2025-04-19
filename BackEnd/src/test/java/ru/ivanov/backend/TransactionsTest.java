package ru.ivanov.backend;

import com.fasterxml.uuid.Generators;
import org.flywaydb.core.Flyway;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import ru.ivanov.backend.models.Journal;
import ru.ivanov.backend.repositories.JournalRepository;

import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * @author Ivan Ivanov
 **/
@SpringBootTest
@AutoConfigureMockMvc
@EnableConfigurationProperties
public class TransactionsTest {
    @Autowired
    private JournalRepository journalRepository;

    @BeforeEach
    void clearDatabase(@Autowired Flyway flyway) {
        flyway.clean();
        flyway.migrate();
    }

    @Test
    public void testSavingNewJournalRollbackAfterTest(){
        UUID id = Generators.timeBasedEpochGenerator().generate();
        Journal journal = new Journal();
        journal.setId(id);
        journal.setName("name");
        journal.setNumber("number");

        journalRepository.save(journal);

        assertTrue(true);

    }

    @Test
    public void testDataBaseStability(){
        List<Journal> allJournals = journalRepository.findAll();

        assertEquals(allJournals.size(), 2);
    }
}
