package de.hybris.training.core.daos;

import com.es.questions.model.QuestionModel;

import java.util.Date;
import java.util.List;

public interface QuestionDao {
    List<QuestionModel> getQuestions();

    List<QuestionModel> getQuestionsCreatedAfterTime(Date time);
}
