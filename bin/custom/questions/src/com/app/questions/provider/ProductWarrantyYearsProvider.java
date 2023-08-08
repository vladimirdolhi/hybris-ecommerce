package com.app.questions.provider;

import de.hybris.platform.core.model.c2l.LanguageModel;
import de.hybris.platform.core.model.product.ProductModel;
import de.hybris.platform.solrfacetsearch.config.IndexConfig;
import de.hybris.platform.solrfacetsearch.config.IndexedProperty;
import de.hybris.platform.solrfacetsearch.config.exceptions.FieldValueProviderException;
import de.hybris.platform.solrfacetsearch.provider.FieldNameProvider;
import de.hybris.platform.solrfacetsearch.provider.FieldValue;
import de.hybris.platform.solrfacetsearch.provider.FieldValueProvider;
import de.hybris.platform.solrfacetsearch.provider.impl.AbstractPropertyFieldValueProvider;
import org.springframework.beans.factory.annotation.Required;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

public class ProductWarrantyYearsProvider extends AbstractPropertyFieldValueProvider implements FieldValueProvider, Serializable {

    private FieldNameProvider fieldNameProvider;

    @Override
    public Collection<FieldValue> getFieldValues(IndexConfig indexConfig, IndexedProperty indexedProperty, Object model) throws FieldValueProviderException {

        if (model instanceof ProductModel product) {
            return createFieldValue(product, indexConfig, indexedProperty);
        } else {
            throw new FieldValueProviderException("Error: item is not a Product type !");
        }

    }

    protected Collection<FieldValue> createFieldValue(ProductModel product, IndexConfig indexConfig, IndexedProperty indexedProperty) {
        final List<FieldValue> fieldValues = new ArrayList<>();
        Integer warrantyYears = getWarrantyYears(product);
        addFieldValues(fieldValues, indexedProperty, null, warrantyYears);
        return fieldValues;
    }

    protected void addFieldValues(List<FieldValue> fieldValues, IndexedProperty indexedProperty, LanguageModel language, Object value) {
        Collection<String> fieldNames = fieldNameProvider.getFieldNames(indexedProperty,
                (language == null) ? null : language.getIsocode());
        for (String fieldName : fieldNames){
            fieldValues.add(new FieldValue(fieldName, value));
        }
    }

    private int getWarrantyYears(ProductModel product){
        return Optional.ofNullable(product.getWarrantyYears())
                .orElse(0);
    }

    @Required
    public void setFieldNameProvider(final FieldNameProvider fieldNameProvider)
    {
        this.fieldNameProvider = fieldNameProvider;
    }

}
