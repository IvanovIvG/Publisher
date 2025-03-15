package ru.ivanov.backend.services;

import com.fasterxml.uuid.Generators;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.ivanov.backend.dto.JournalDTO;
import ru.ivanov.backend.models.Journal;
import ru.ivanov.backend.repositories.JournalRepository;

import java.util.List;
import java.util.UUID;

/**
 * @author Ivan Ivanov
 **/
@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class JournalService {
    private final JournalRepository journalRepository;
    private final ModelMapper modelMapper;

    public JournalDTO readById(UUID id) {
        Journal journal = getJournalById(id);
        return convertToDTO(journal);
    }

    public Journal getJournalById(UUID id) {
        return journalRepository.findById(id).
                orElseThrow(() -> new IllegalArgumentException("There is no journal with such id"));
    }

    public List<JournalDTO> readAll() {
        return journalRepository.findAll().stream().map(this::convertToDTO).toList();
    }

    @Transactional
    public JournalDTO create(JournalDTO journalDTO) {
        Journal journal = convertToEntity(journalDTO);
        journal.setId(generateJournalUniqueID());
        return convertToDTO(journalRepository.save(journal));

    }

    @Transactional
    public JournalDTO update(JournalDTO journalDTO) {
        Journal journal = convertToEntity(journalDTO);
        if (journalExists(journal)) {
            journal = journalRepository.save(journal);
        } else {
            throw new IllegalArgumentException("There is no journal with such id");
        }
        return convertToDTO(journal);
    }

    @Transactional
    public void delete(UUID id) {
        journalRepository.deleteById(id);
    }

    private UUID generateJournalUniqueID() {
        UUID id = Generators.timeBasedEpochGenerator().generate();
        while (suchJournalIDIsNotUnique(id)) {
            id = Generators.timeBasedEpochGenerator().generate();
        }
        return id;
    }

    private boolean suchJournalIDIsNotUnique(UUID id) {
        return journalRepository.existsById(id);
    }

    private boolean journalExists(Journal journal){
        return journalRepository.existsById(journal.getId());
    }

    private Journal convertToEntity(JournalDTO journalDTO) {
        return modelMapper.map(journalDTO, Journal.class);
    }

    private JournalDTO convertToDTO(Journal journal) {
        return modelMapper.map(journal, JournalDTO.class);
    }
}
