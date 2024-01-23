package ru.maksimov.mathservice.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.maksimov.mathservice.models.Question;
import ru.maksimov.mathservice.services.MathService;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api")
public class MathController {

    @Autowired
    private MathService mathService;

    @GetMapping("/questions")
    public List<Question> getRandomQuestions(@RequestParam int amount){
        List<Question> questions = new ArrayList<>();
        for(int i = 0; i < amount; ++i) {
            questions.add(mathService.getRandomQuestion());
        }
        return questions;
    }
}
