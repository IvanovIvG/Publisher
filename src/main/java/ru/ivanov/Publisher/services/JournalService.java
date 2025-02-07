package ru.ivanov.Publisher.services;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.ivanov.Publisher.dto.JournalDTO;
import ru.ivanov.Publisher.models.Journal;
import ru.ivanov.Publisher.repositories.JournalRepository;

import java.util.List;

/**
 * @author Ivan Ivanov
 **/
@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class JournalService {
    private final JournalRepository journalRepository;
    private final ModelMapper modelMapper;


    @Transactional
    public JournalDTO create(JournalDTO journalDTO) {
        Journal journal = convertToEntity(journalDTO);
        if (thereIsNoJournalWithSameId(journal)) {
            return convertToDTO(journalRepository.save(journal));
        } else {
            throw new IllegalArgumentException("There is already journal with such id");
        }
    }

    public JournalDTO readById(int id){
        return convertToDTO(getJournalById(id));
    }

    public Journal getJournalById(int id) {
        return journalRepository.findById(id).
                orElseThrow(() -> new IllegalArgumentException("There is no journal with such id"));
    }

    public List<JournalDTO> readAll() {
        return journalRepository.findAll().stream().map(this::convertToDTO).toList();
    }

    @Transactional
    public JournalDTO update(JournalDTO journalDTO) {
        Journal journal = convertToEntity(journalDTO);
        if (thereIsJournalWithSameId(journal)) {
            return convertToDTO(journalRepository.save(journal));
        } else {
            throw new IllegalArgumentException("There is no journal with such id");
        }
    }

    @Transactional
    public void delete(int id) {
        journalRepository.deleteById(id);
    }

    private boolean thereIsNoJournalWithSameId(Journal journal) {
        return !thereIsJournalWithSameId(journal);
    }

    private boolean thereIsJournalWithSameId(Journal journal) {
        int journalId = journal.getId();
        return journalRepository.findById(journalId).isPresent();
    }

    private Journal convertToEntity(JournalDTO journalDTO) {
        return modelMapper.map(journalDTO, Journal.class);
    }

    private JournalDTO convertToDTO(Journal journal) {
        return modelMapper.map(journal, JournalDTO.class);
    }
}
