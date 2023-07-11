package com.app.questions.populator;

import de.hybris.platform.commercefacades.product.converters.populator.AbstractProductPopulator;
import de.hybris.platform.commercefacades.product.data.ProductData;
import com.app.questions.data.QuestionData;
import com.app.questions.model.QuestionModel;
import de.hybris.platform.converters.Converters;
import de.hybris.platform.converters.Populator;
import de.hybris.platform.core.model.product.ProductModel;
import de.hybris.platform.servicelayer.dto.converter.ConversionException;
import de.hybris.platform.servicelayer.dto.converter.Converter;

import javax.annotation.Resource;

public class ProductQuestionsPopulator<SOURCE extends ProductModel, TARGET extends ProductData>
        extends AbstractProductPopulator<SOURCE, TARGET> {

    @Resource
    private Converter<QuestionModel, QuestionData> questionConverter;

    @Override
    public void populate(final SOURCE source, final TARGET target) throws ConversionException {
        target.setQuestions(Converters.convertAll(source.getQuestions(), questionConverter));
    }
}
