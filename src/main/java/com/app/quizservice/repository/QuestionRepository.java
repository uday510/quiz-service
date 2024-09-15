package com.app.quizservice.repository;


import com.app.quizservice.enums.DifficultyLevel;
import com.app.quizservice.model.Question;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface QuestionRepository extends JpaRepository<Question, Integer> {
    List<Question> findByCategory(String category);

    List<Question> findByDifficultyLevel(DifficultyLevel difficulty);

    @Query("SELECT q FROM Question q WHERE q.category = :category ORDER BY FUNCTION('RANDOM')")
    Page<Question> findRandomQuestionsByCategory(@Param("category") String category, Pageable pageable);
}
