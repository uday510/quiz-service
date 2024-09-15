package com.app.quizservice.service.impl;


import com.app.quizservice.feign.QuizInterface;
import com.app.quizservice.model.QuestionResponse;
import com.app.quizservice.model.QuestionWrapper;
import com.app.quizservice.model.Quiz;
import com.app.quizservice.model.QuizResponse;
import com.app.quizservice.repository.QuizRepository;
import com.app.quizservice.service.QuizService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;


@Service
public class QuizServiceImpl implements QuizService {

    @Autowired
    private QuizRepository quizRepository;

    @Autowired
    private QuizInterface quizInterface;

    @Override
    public Quiz createQuiz(String category, int numQuestions, String title) {
        List<Integer> questions = Objects.requireNonNull(quizInterface.getQuestionsForQuiz(category, numQuestions).getBody()).getData();
        Quiz quiz = new Quiz();
        quiz.setTitle(title);
        quiz.setCategory(category);
        quiz.setQuestionIds(questions);

        return quizRepository.save(quiz);
    }

    @Override
    public List<QuestionWrapper> getQuizQuestions(int id) {
        Optional<Quiz> quiz = quizRepository.findById(id);
        List<Integer> questionIds = quiz.orElseThrow().getQuestionIds();

        return Objects.requireNonNull(quizInterface.getQuestionsByIds(questionIds).getBody()).getData();
    }

    @Override
    public int submitQuiz(int id, List<QuestionResponse> responses) {

        quizInterface.submitQuiz(responses);

    }
}
