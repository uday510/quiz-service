package com.app.quizservice.controller;

import com.app.quizservice.model.QuestionResponse;
import com.app.quizservice.model.QuestionWrapper;
import com.app.quizservice.model.Quiz;
import com.app.quizservice.service.QuizService;
import com.app.quizservice.util.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.logging.Logger;

@RestController
@RequestMapping("/api/quiz")
public class QuizController {

    @Autowired
    QuizService quizService;

    @PostMapping("")
    public ResponseEntity<ApiResponse<Object>> createQuiz(
            @RequestParam String category,
            @RequestParam(defaultValue = "5") int numQuestions,
            @RequestParam String title) {

        try {
            Quiz quiz = quizService.createQuiz(category, numQuestions, title);
            return ResponseEntity.status(HttpStatus.CREATED)
                    .body(new ApiResponse<>(quiz, "Quiz created successfully"));
        } catch (Exception e) {
            Logger.getLogger("Error: " + e.getMessage());
            System.out.println("Error: " + e.getMessage());
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(new ApiResponse<>(null, "Error creating quiz"));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<List<QuestionWrapper>>> getQuizzes(@PathVariable int id) {
        try {
            List<QuestionWrapper> questions = quizService.getQuizQuestions(id);

            return ResponseEntity.status(HttpStatus.OK)
                    .body(new ApiResponse<>(questions, "Questions retrieved successfully"));
        } catch (Exception e) {
            Logger.getLogger("Error: " + e.getMessage());
            System.out.println("Error: " + e.getMessage());
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(new ApiResponse<>(null, "Error retrieving questions"));
    }

    @PostMapping("submit/{id}")
    public ResponseEntity<ApiResponse<Object>> submitQuiz(@PathVariable int id, @RequestBody List<QuestionResponse> responses) {
        try {
            int score = quizService.submitQuiz(id, responses);
            String message = "Quiz submitted successfully. Your score is " + score + "/" + responses.size();
            return ResponseEntity.status(HttpStatus.OK)
                    .body(new ApiResponse<>(null, message));
        } catch (Exception e) {
            Logger.getLogger("Error: " + e.getMessage());
            System.out.println("Error: " + e.getMessage());
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(new ApiResponse<>(null, "Error submitting quiz"));
    }

}