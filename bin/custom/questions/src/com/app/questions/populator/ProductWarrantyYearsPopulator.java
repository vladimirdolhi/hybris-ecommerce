package com.app.questions.populator;

import de.hybris.platform.commercefacades.product.converters.populator.AbstractProductPopulator;
import de.hybris.platform.commercefacades.product.data.ProductData;
import de.hybris.platform.core.model.product.ProductModel;
import de.hybris.platform.servicelayer.dto.converter.ConversionException;

import java.util.Optional;

public class ProductWarrantyYearsPopulator<SOURCE extends ProductModel, TARGET extends ProductData>
        extends AbstractProductPopulator<SOURCE, TARGET> {
    @Override
    public void populate(SOURCE source, TARGET target) throws ConversionException {
        target.setWarrantyYears(Optional.ofNullable(source.getWarrantyYears()).orElse(0));
    }

}
