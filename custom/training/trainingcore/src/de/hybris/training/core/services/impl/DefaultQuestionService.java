package de.hybris.training.core.services.impl;

import com.es.questions.model.QuestionModel;
import de.hybris.training.core.daos.QuestionDao;
import de.hybris.training.core.services.QuestionService;

import java.util.Date;
import java.util.List;
import java.util.Objects;

public class DefaultQuestionService implements QuestionService {
    private QuestionDao questionDao;

    @Override
    public List<QuestionModel> getQuestionsCreatedAfterTime(Date date) {
        if (Objects.nonNull(date)){
            return questionDao.getQuestionsCreatedAfterTime(date);
        } else {
            return questionDao.getQuestions();
        }
    }

    public void setQuestionDao(QuestionDao questionDao) {
        this.questionDao = questionDao;
    }
}
