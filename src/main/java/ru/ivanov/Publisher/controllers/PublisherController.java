package ru.ivanov.Publisher.controllers;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.ivanov.Publisher.models.Journal;
import ru.ivanov.Publisher.services.JournalService;

import java.util.List;

/**
 * @author Ivan Ivanov
 **/
@Controller
@RequestMapping("/publisher")
public class PublisherController {
    private final JournalService journalService;

    @Autowired
    public PublisherController(JournalService journalService) {
        this.journalService = journalService;
    }

    @GetMapping()
    public String showAllJournals(Model model){
        List<Journal> journals = journalService.readAll();
        model.addAttribute("allJournals", journals);
        return "publisher/allJournals";
    }

    @GetMapping("/new")
    public String goToNewJournalCreationPage(@ModelAttribute("newJournal") Journal journal) {
        return "publisher/createJournal";
    }

    @PostMapping()
    public String createJournal(@ModelAttribute("newJournal") @Valid Journal newJournal,
                             BindingResult bindingResult) {
        if (bindingResult.hasErrors())
            return "publisher/createJournal";

        journalService.create(newJournal);
        return "redirect:/publisher";
    }

    @PatchMapping("/{journalId}/edit")
    public String updateJournal(@PathVariable("journalId") int journalId,
                                    @ModelAttribute("journalToUpdate") @Valid Journal journalToUpdate,
                                    BindingResult bindingResult) {
        if (bindingResult.hasErrors())
            return "publisher/allJournals";

        journalService.update(journalToUpdate);
        return "redirect:/publisher";
    }

    @DeleteMapping("/{journalId}")
    public String deleteJournal(@PathVariable("journalId") int journalId) {
        journalService.delete(journalId);
        return "redirect:/publisher";
    }
}
