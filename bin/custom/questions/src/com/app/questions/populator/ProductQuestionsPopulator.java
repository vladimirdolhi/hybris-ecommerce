package com.app.questions.populator;

import com.app.questions.data.ProductData;
import com.app.questions.data.QuestionData;
import com.app.questions.model.QuestionModel;
import de.hybris.platform.converters.Converters;
import de.hybris.platform.converters.Populator;
import de.hybris.platform.core.model.product.ProductModel;
import de.hybris.platform.servicelayer.dto.converter.ConversionException;
import de.hybris.platform.servicelayer.dto.converter.Converter;

import javax.annotation.Resource;

public class ProductQuestionsPopulator implements Populator<ProductModel, ProductData> {

    @Resource
    private Converter<QuestionModel, QuestionData> questionConverter;

    @Override
    public void populate(ProductModel source, ProductData target) throws ConversionException {
        target.setQuestions(Converters.convertAll(source.getQuestions(), questionConverter));
    }
}
