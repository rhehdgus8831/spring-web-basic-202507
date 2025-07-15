package com.spring.basic.chap3_2.practice;

import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/v3-2/feedback")
public class FeedbackController3_2 {

    private List<com.spring.basic.chap3_2.practice.Feedback> feedbackList = new ArrayList<>();

    @GetMapping
    public List<com.spring.basic.chap3_2.practice.Feedback> feedbackList() {
        return feedbackList;
    }

    @PostMapping
    public String  createFeedback(@RequestBody com.spring.basic.chap3_2.practice.Feedback feedback) {
        feedback.setId((long) (Math.random() * 1000000 + 1));
        feedbackList.add(feedback);
        return "Feedback created: " + feedback;
    }
}
