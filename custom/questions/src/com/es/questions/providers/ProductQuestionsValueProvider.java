package com.es.questions.providers;

import de.hybris.platform.core.model.c2l.LanguageModel;
import de.hybris.platform.core.model.product.ProductModel;
import de.hybris.platform.servicelayer.i18n.CommonI18NService;
import de.hybris.platform.solrfacetsearch.config.IndexConfig;
import de.hybris.platform.solrfacetsearch.config.IndexedProperty;
import de.hybris.platform.solrfacetsearch.config.exceptions.FieldValueProviderException;
import de.hybris.platform.solrfacetsearch.provider.FieldNameProvider;
import de.hybris.platform.solrfacetsearch.provider.FieldValue;
import de.hybris.platform.solrfacetsearch.provider.FieldValueProvider;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class ProductQuestionsValueProvider implements FieldValueProvider {
    private static final String ITEM_NOT_PRODUCT_ERROR = "Item is not product";

    private FieldNameProvider fieldNameProvider;

    @Override
    public Collection<FieldValue> getFieldValues(IndexConfig indexConfig, IndexedProperty indexedProperty, Object object) throws FieldValueProviderException {

        if (object instanceof ProductModel) {
            ProductModel product = (ProductModel) object;
            Collection<FieldValue> fieldValues = new ArrayList<FieldValue>();

            if (indexedProperty.isLocalized()) {
                Collection<LanguageModel> languages = indexConfig.getLanguages();

                for (LanguageModel language : languages) {
                    fieldValues.addAll(createFieldValue(product, language, indexedProperty));
                }
            } else {
                fieldValues.addAll(createFieldValue(product, null, indexedProperty));
            }
            return fieldValues;
        }

        throw new FieldValueProviderException(ITEM_NOT_PRODUCT_ERROR);
    }

    protected List<FieldValue> createFieldValue(ProductModel product, LanguageModel language, IndexedProperty indexedProperty) {
        List<FieldValue> fieldValues = new ArrayList();

        Integer count = getQuestionsCount(product);

        if (count != 0) {
            addFieldValues(fieldValues, indexedProperty, language, count);
        }

        return fieldValues;
    }

    protected void addFieldValues(List<FieldValue> fieldValues, IndexedProperty indexedProperty, LanguageModel language, Object value) {
        Collection<String> fieldNames = fieldNameProvider.getFieldNames(indexedProperty, language == null ? null : language.getIsocode());

        for (String fieldName : fieldNames) {
            fieldValues.add(new FieldValue(fieldName, value));
        }
    }

    private Integer getQuestionsCount(ProductModel product) {
        return product.getQuestions().size();
    }

    public void setFieldNameProvider(FieldNameProvider fieldNameProvider) {
        this.fieldNameProvider = fieldNameProvider;
    }
}
