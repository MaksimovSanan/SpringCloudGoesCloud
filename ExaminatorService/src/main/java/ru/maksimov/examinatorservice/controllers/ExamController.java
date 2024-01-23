package ru.maksimov.examinatorservice.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import ru.maksimov.examinatorservice.models.Exam;
import ru.maksimov.examinatorservice.models.Question;
import ru.maksimov.examinatorservice.models.Section;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
public class ExamController {

    private final RestTemplate restTemplate;
    private final DiscoveryClient discoveryClient;

    @Autowired
    public ExamController(RestTemplate restTemplate, DiscoveryClient discoveryClient) {
        this.restTemplate = restTemplate;
        this.discoveryClient = discoveryClient;
    }

    @PostMapping("/exam")
    public Exam getExam(@RequestBody Map<String, Integer> spec) {
        List<Section> sections = spec.entrySet().stream().map(this::getUrl)
                .map(url->restTemplate.getForObject(url, Question[].class))
                .map(Arrays::asList)
                .map(Section::new)
                .collect(Collectors.toList());

        return new Exam("EXAM", sections);
    }

    private String getUrl(Map.Entry<String, Integer> entry) {
        return "http://" + entry.getKey() + "/api/questions?amount=" + entry.getValue();
    }
}
