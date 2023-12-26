package com.es.questions.populators;

import com.es.questions.model.QuestionModel;
import de.hybris.platform.commercefacades.product.data.ProductData;
import de.hybris.platform.converters.Populator;
import de.hybris.platform.core.model.product.ProductModel;
import de.hybris.platform.servicelayer.dto.converter.ConversionException;
import de.hybris.platform.servicelayer.dto.converter.Converter;
import questions.data.QuestionsData;

import java.util.List;

public class ProductDataPopulator implements Populator<ProductModel, ProductData> {
    private Converter<QuestionModel, QuestionsData> questionsDataConverter;

    @Override
    public void populate(ProductModel product, ProductData productData) throws ConversionException {
        List<QuestionModel> questions = product.getQuestions();

        productData.setQuestions(questionsDataConverter.convertAll(questions));
    }

    public void setQuestionsDataConverter(Converter<QuestionModel, QuestionsData> questionsDataConverter) {
        this.questionsDataConverter = questionsDataConverter;
    }
}
