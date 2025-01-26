package ru.ivanov.Publisher.controllers;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.ivanov.Publisher.models.Article;
import ru.ivanov.Publisher.models.Journal;
import ru.ivanov.Publisher.services.ArticleService;
import ru.ivanov.Publisher.services.JournalService;

import java.util.List;

/**
 * @author Ivan Ivanov
 **/
@Controller
@RequestMapping("/publisher/{journalId}")
public class JournalController {
    private final ArticleService articleService;
    private final JournalService journalService;

    @Autowired
    public JournalController(ArticleService articleService, JournalService journalService) {
        this.articleService = articleService;
        this.journalService = journalService;
    }

    @ModelAttribute
    public void getJournal(@PathVariable int journalId, Model model){
        Journal journal = journalService.readById(journalId);
        model.addAttribute("journal", journal);
    }

    @GetMapping()
    public String showArticles(@PathVariable int journalId, Model model){
        List<Article> articles = articleService.readAllByJournal(journalId);
        model.addAttribute("articles", articles);
        return "journal/showJournalPage";
    }

    @GetMapping("/new")
    public String goToNewArticleCreationPage(@ModelAttribute("newArticle") Article newArticle, @PathVariable int journalId) {
        newArticle.setJournal(journalService.readById(journalId));
        return "journal/newArticle";
    }

    @PostMapping()
    public String createArticle(@ModelAttribute("newArticle") @Valid Article newArticle,
                                BindingResult bindingResult) {
        if (bindingResult.hasErrors())
            return "journal/newArticle";

        articleService.create(newArticle);
        return "redirect:/publisher/{journalId}";
    }

    @PatchMapping("/{articleId}/edit")
    public String updateJournal(@PathVariable("articleId") int articleId,
                                    @ModelAttribute("article") @Valid Article articleToUpdate,
                                    BindingResult bindingResult) {
        if (bindingResult.hasErrors())
            return "journal/showJournalPage";

        articleService.update(articleToUpdate);
        return "redirect:/publisher/{journalId}";
    }

    @DeleteMapping("/{articleId}")
    public String delete(@PathVariable("articleId") int articleId) {
        articleService.delete(articleId);
        return "redirect:/publisher/{journalId}";
    }
}
