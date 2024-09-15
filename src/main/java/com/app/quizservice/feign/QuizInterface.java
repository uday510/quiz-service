package com.app.quizservice.feign;

import com.app.quizservice.model.QuestionResponse;
import com.app.quizservice.model.QuestionWrapper;
import com.app.quizservice.util.ApiResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@FeignClient("QUESTION-SERVICE")
public interface QuizInterface {
    @GetMapping("/api/questions/generate")
    ResponseEntity<ApiResponse<List<Integer>>> getQuestionsForQuiz(@RequestParam String category, @RequestParam int numQuestions);

    @PostMapping("/api/questions/getQuestions")
    ResponseEntity<ApiResponse<List<QuestionWrapper>>> getQuestionsByIds(@RequestBody List<Integer> questionIds);

    @PostMapping("/api/questions/getScore")
    ResponseEntity<ApiResponse<Object>> submitQuiz(@RequestBody List<QuestionResponse> responses);
}
