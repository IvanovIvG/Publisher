package ru.ivanov.backend.integration.publisher;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.CollectionType;
import org.flywaydb.core.Flyway;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import ru.ivanov.backend.dto.JournalDTO;
import ru.ivanov.backend.integration.factories.JournalFactory;
import ru.ivanov.backend.models.Journal;
import ru.ivanov.backend.repositories.JournalRepository;
import ru.ivanov.backend.utils.converters.JournalEntityToDTOConverter;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * @author Ivan Ivanov
 **/
@SpringBootTest
@AutoConfigureMockMvc
@EnableConfigurationProperties
@WithMockUser
public class PublisherGetRequestTests {
    @Autowired
    private JournalRepository journalRepository;
    @Autowired
    private Flyway flyway;
    @Autowired
    private JournalFactory journalFactory;
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper objectMapper;
    @Autowired
    private JournalEntityToDTOConverter converter;

    @BeforeEach
    void clearDatabase() {
        flyway.clean();
        flyway.migrate();
    }

    @Test
    public void requestReturnsAllJournalsInDatabase() throws Exception {
        journalRepository.deleteAll();
        List<Journal> journals = new ArrayList<>();
        for (int i = 0; i < 100; i++) {
            Journal journal = journalFactory.getRandomJournaL();
            journals.add(journal);
        }
        journalRepository.saveAll(journals);
        List<JournalDTO> databaseJournalDTOs = converter.covertListToDTO(journals);

        MvcResult result = mockMvc.perform(get("/publisher")).andExpect(status().isOk())
                .andReturn();
        String json = result.getResponse().getContentAsString();
        CollectionType collectionType = objectMapper.getTypeFactory().
                constructCollectionType(List.class, JournalDTO.class);
        List<JournalDTO> responseJournalDTOs = objectMapper.readValue(json, collectionType);

        assertNotNull(responseJournalDTOs);
        assertEquals(100, responseJournalDTOs.size());
        assertIterableEquals(databaseJournalDTOs, responseJournalDTOs);
    }
}
