package ru.ivanov.backend.utils.converters;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;
import ru.ivanov.backend.dto.JournalDTO;
import ru.ivanov.backend.models.Journal;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Ivan Ivanov
 **/
@Component
@RequiredArgsConstructor
public class JournalEntityToDTOConverter {
    private final ModelMapper modelMapper;

//    public JournalEntityToDTOConverter(ModelMapper modelMapper) {
//        this.modelMapper = modelMapper;
//    }

    public Journal convertToEntity(JournalDTO journalDTO) {
        return modelMapper.map(journalDTO, Journal.class);
    }

    public List<Journal> covertListToEntity(List<JournalDTO> journalDTOs){
        List<Journal> journals = new ArrayList<>();
        for(JournalDTO journalDTO: journalDTOs){
            Journal journal = convertToEntity(journalDTO);
            journals.add(journal);
        }
        return journals;
    }

    public JournalDTO convertToDTO(Journal journal) {
        return modelMapper.map(journal, JournalDTO.class);
    }

    public List<JournalDTO> covertListToDTO(List<Journal> journals){
        List<JournalDTO> journalDTOs = new ArrayList<>();
        for(Journal journal: journals){
            JournalDTO journalDTO = convertToDTO(journal);
            journalDTOs.add(journalDTO);
        }
        return journalDTOs;
    }
}
