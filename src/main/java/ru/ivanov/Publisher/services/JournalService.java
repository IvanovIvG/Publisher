package ru.ivanov.Publisher.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.ivanov.Publisher.models.Journal;
import ru.ivanov.Publisher.repositories.JournalRepository;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

/**
 * @author Ivan Ivanov
 **/
@Service
@Transactional(readOnly = true)
public class JournalService {
    private final JournalRepository journalRepository;

    @Autowired
    public JournalService(JournalRepository journalRepository) {
        this.journalRepository = journalRepository;
    }

    @Transactional
    public Journal create(Journal journal) {
        return journalRepository.save(journal);
    }

    public Journal readById(int id) {
        Optional<Journal> foundJournal = journalRepository.findById(id);
        return foundJournal.orElseThrow(() -> new NoSuchElementException("There is no journal with such id"));
    }

    public List<Journal> readAll() {
        return journalRepository.findAll();
    }

    @Transactional
    public Journal update(Journal journal) {
        Optional<Journal> journalWithSameId = journalRepository.findById(journal.getId());
        if (journalWithSameId.isPresent()) {
            return journalRepository.save(journal);
        }
        return journal;
    }

    @Transactional
    public void delete(int id){
        journalRepository.deleteById(id);
    }
}
