package com.app.questions.populator;

import de.hybris.platform.commercefacades.product.data.ProductData;
import de.hybris.platform.commercefacades.search.converters.populator.SearchResultVariantProductPopulator;
import de.hybris.platform.commerceservices.search.resultdata.SearchResultValueData;

import java.util.Optional;

public class SearchProductQuestionPopulator extends SearchResultVariantProductPopulator {

    @Override
    public void populate(SearchResultValueData source, ProductData target) {
        super.populate(source, target);
        target.setQuestionsCount(Optional.ofNullable(this.<Integer>getValue(source, "questionsCount"))
                .orElse(0));
    }

}
