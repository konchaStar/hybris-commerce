package com.es.questions.facades;

import de.hybris.platform.commercefacades.product.data.ProductData;

import java.util.Optional;

public interface ProductFacade {

    Optional<ProductData> getProduct(String code);

}
