package com.app.questions.populator;

import de.hybris.platform.commercefacades.product.data.ProductData;
import de.hybris.platform.commercefacades.search.converters.populator.SearchResultVariantProductPopulator;
import de.hybris.platform.commerceservices.search.resultdata.SearchResultValueData;

import java.util.Optional;

public class SearchProductWarrantyYearsPopulator extends SearchResultVariantProductPopulator {

    @Override
    public void populate(SearchResultValueData source, ProductData target) {
        super.populate(source, target);
        Object obj = this.getValue(source, "warrantyYears");
        if (obj != null) {
            if (obj instanceof Integer){
                target.setWarrantyYears(this.<Integer>getValue(source, "warrantyYears"));
            } else {
                target.setWarrantyYears(Integer.valueOf(this.<String>getValue(source, "warrantyYears")));
            }
        } else {
            target.setWarrantyYears(0);
        }
    }

}
