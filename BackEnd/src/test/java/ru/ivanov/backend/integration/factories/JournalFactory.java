package ru.ivanov.backend.integration.factories;

import com.fasterxml.uuid.Generators;
import org.springframework.stereotype.Component;
import ru.ivanov.backend.models.Journal;

import java.util.UUID;
import java.util.concurrent.ThreadLocalRandom;

/**
 * @author Ivan Ivanov
 **/
@Component
public class JournalFactory {
    public Journal getRandomJournaL() {
        Journal journal = new Journal();
        journal.setId(generateRandomId());
        journal.setName(generateRandomName());
        journal.setNumber(generateRandomNumber());
        return journal;
    }

    private UUID generateRandomId() {
        return Generators.timeBasedEpochGenerator().generate();
    }

    private String generateRandomName() {
        StringBuilder builder = new StringBuilder("journal");
        int index = ThreadLocalRandom.current().nextInt(1, 1000);
        builder.append(index);
        return builder.toString();
    }

    private String generateRandomNumber() {
        StringBuilder builder = new StringBuilder();

        int month = ThreadLocalRandom.current().nextInt(1, 12);
        if(month<10){
            builder.append(0);
        }
        builder.append(month);
        builder.append('/');
        int year = ThreadLocalRandom.current().nextInt(10, 25);
        builder.append(year);

        return builder.toString();
    }
}
