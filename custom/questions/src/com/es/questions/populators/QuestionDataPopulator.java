package com.es.questions.populators;

import com.es.questions.model.QuestionModel;
import de.hybris.platform.converters.Populator;
import de.hybris.platform.servicelayer.dto.converter.ConversionException;
import questions.data.QuestionsData;

public class QuestionDataPopulator implements Populator<QuestionModel, QuestionsData> {
    @Override
    public void populate(QuestionModel questionModel, QuestionsData questionsData) throws ConversionException {
        questionsData.setQuestion(questionModel.getQuestion());
        questionsData.setAnswer(questionModel.getAnswer());
        questionsData.setQuestionCustomerName(questionModel.getQuestionCustomer().getName());
        questionsData.setAnswerCustomerName(questionModel.getAnswerCustomer().getName());
    }
}
