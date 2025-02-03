package ru.ivanov.Publisher.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.ivanov.Publisher.models.Journal;
import ru.ivanov.Publisher.repositories.JournalRepository;

import java.util.List;
import java.util.Optional;

/**
 * @author Ivan Ivanov
 **/
@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class JournalService {
    private final JournalRepository journalRepository;

    @Transactional
    public Journal create(Journal journal) {
        if(thereIsNoJournalWithSameId(journal)){
            return journalRepository.save(journal);
        }
        else{
            throw new IllegalArgumentException("There is already journal with such id");
        }
    }

    public Journal readById(int id) {
        Optional<Journal> foundJournal = journalRepository.findById(id);
        return foundJournal.orElseThrow(() -> new IllegalArgumentException("There is no journal with such id"));
    }

    public List<Journal> readAll() {
        return journalRepository.findAll();
    }

    @Transactional
    public Journal update(Journal journal) {
        if (thereIsJournalWithSameId(journal)) {
            return journalRepository.save(journal);
        }
        else{
            throw new IllegalArgumentException("There is no journal with such id");
        }
    }

    @Transactional
    public void delete(int id){
        journalRepository.deleteById(id);
    }

    private boolean thereIsNoJournalWithSameId(Journal journal){
        return !thereIsJournalWithSameId(journal);
    }

    private boolean thereIsJournalWithSameId(Journal journal){
        int journalId = journal.getId();
        return journalRepository.findById(journalId).isPresent();
    }
}
