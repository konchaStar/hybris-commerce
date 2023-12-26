package com.es.questions.populators;

import com.es.questions.model.QuestionModel;
import de.hybris.platform.converters.Populator;
import de.hybris.platform.servicelayer.dto.converter.ConversionException;
import questions.data.QuestionsData;

public class QuestionDataPopulator implements Populator<QuestionModel, QuestionsData> {
    @Override
    public void populate(QuestionModel question, QuestionsData questionsData) throws ConversionException {
        questionsData.setQuestion(question.getQuestion());
        questionsData.setAnswer(question.getAnswer());
        questionsData.setQuestionCustomerName(question.getQuestionCustomer().getName());
        questionsData.setAnswerCustomerName(question.getAnswerCustomer().getName());
    }
}
