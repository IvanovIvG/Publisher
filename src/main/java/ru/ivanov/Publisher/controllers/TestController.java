package ru.ivanov.Publisher.controllers;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.ivanov.Publisher.models.Test;
import ru.ivanov.Publisher.services.TestService;

import java.util.List;

/**
 * @author Ivan Ivanov
 */
@Controller()
@RequestMapping("/tests")
public class TestController {
    private final TestService testService;

    @Autowired
    public TestController(TestService testService){
        this.testService = testService;
    }

    @GetMapping()
    public String showAll(Model model) {
        List<Test> tests = testService.readAll();
        model.addAttribute("tests", tests);
        return "tests/showAll";
    }

    @GetMapping("/new")
    public String newTest(@ModelAttribute Test test) {
        return "tests/new";
    }

    @PostMapping()
    public String createTest(@ModelAttribute("test") @Valid Test newTest,
                         BindingResult bindingResult) {
        if (bindingResult.hasErrors())
            return "tests/new";

        testService.create(newTest);
        return "redirect:/tests";
    }

    @GetMapping("/{id}/edit")
    public String editTest(Model model, @PathVariable("id") int id) {
        model.addAttribute("test", testService.read(id));
        return "tests/edit";
    }

    @PatchMapping("/{id}")
    public String update(@ModelAttribute("test") @Valid Test test, BindingResult bindingResult) {
        if (bindingResult.hasErrors())
            return "tests/edit";

        testService.update(test);
        return "redirect:/tests";
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id") int id) {
        System.out.println("hello");
        testService.delete(id);
        return "redirect:/tests";
    }
}
