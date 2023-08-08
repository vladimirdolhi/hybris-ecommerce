package com.app.questions.provider;

import de.hybris.platform.solrfacetsearch.config.IndexedProperty;
import de.hybris.platform.solrfacetsearch.provider.impl.AbstractFacetValueDisplayNameProvider;
import de.hybris.platform.solrfacetsearch.search.SearchQuery;
import org.apache.commons.lang.StringUtils;

public class ProductWarrantyYearsFacetDisplayNameProvider extends AbstractFacetValueDisplayNameProvider {

    @Override
    public String getDisplayName(SearchQuery searchQuery, IndexedProperty indexedProperty, String facetValue) {
        if (StringUtils.isNotBlank(facetValue)) {
            return facetValue;
        }
        return StringUtils.EMPTY;
    }

}
