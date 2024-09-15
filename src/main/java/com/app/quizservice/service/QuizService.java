package com.app.quizservice.service;


import com.app.quizservice.model.QuestionResponse;
import com.app.quizservice.model.QuestionWrapper;
import com.app.quizservice.model.Quiz;

import java.util.List;

public interface QuizService {

    Quiz createQuiz(String category, int numQuestions, String title);

    List<QuestionWrapper> getQuizQuestions(int id);

    int submitQuiz(int id, List<QuestionResponse> responses);
}
