package ru.maksimov.mathservice.services;

import org.springframework.stereotype.Service;
import ru.maksimov.mathservice.models.Question;

import java.util.Random;

@Service
public class MathServiceFirstImplementation implements MathService{
    private Random random = new Random();

    private int max = 10;
    @Override
    public Question getRandomQuestion() {
        int a = random.nextInt(max);
        int b = random.nextInt(max);
        Question question = new Question(a + " + " + b + " = ?", String.valueOf(a + b));

        return question;
    }
}
