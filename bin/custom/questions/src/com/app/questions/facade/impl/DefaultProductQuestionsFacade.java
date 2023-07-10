package com.app.questions.facade.impl;

import com.app.questions.data.ProductData;
import com.app.questions.facade.ProductQuestionsFacade;
import de.hybris.platform.core.model.product.ProductModel;
import de.hybris.platform.product.ProductService;
import de.hybris.platform.servicelayer.dto.converter.Converter;

import javax.annotation.Resource;

public class DefaultProductQuestionsFacade implements ProductQuestionsFacade {

    @Resource
    private ProductService productService;

    @Resource
    private Converter<ProductModel, ProductData> productQuestionsConverter;

    @Override
    public ProductData getProductData(String code) {

        ProductModel productModel = productService.getProductForCode(code);

        return productQuestionsConverter.convert(productModel);
    }


}
