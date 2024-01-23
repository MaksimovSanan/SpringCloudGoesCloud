package ru.maksimov.historyservice.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.maksimov.historyservice.models.Question;
import ru.maksimov.historyservice.repositories.HistoryRepository;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api")
public class HistoryController {

    private final HistoryRepository historyRepository;

    @Autowired
    public HistoryController(HistoryRepository historyRepository) {
        this.historyRepository = historyRepository;
    }

    @GetMapping("/questions")
    public List<Question> getQuestions(@RequestParam int amount){
        List<Question> questions = historyRepository.findAll();
        Collections.shuffle(questions);
        return questions.stream().limit(amount).collect(Collectors.toList());
    }
}
