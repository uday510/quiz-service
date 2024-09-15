package com.app.quizservice.service;



import com.app.quizservice.dto.QuestionDTO;
import com.app.quizservice.enums.DifficultyLevel;
import com.app.quizservice.model.Question;

import java.util.List;

public interface QuestionService {

    List<Question> getAllQuestions();

    Question findById(Integer id);

    Question createQuestion(QuestionDTO questionDTO);

    Question updateQuestion(Integer id, QuestionDTO questionDTO);

    void deleteQuestion(Integer id);

    List<Question> getQuestionsByCategory(String category);

    List<Question> getQuestionsByDifficulty(DifficultyLevel difficulty);
}
