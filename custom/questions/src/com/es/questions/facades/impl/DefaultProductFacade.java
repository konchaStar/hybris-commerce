package com.es.questions.facades.impl;

import com.es.questions.facades.ProductFacade;
import de.hybris.platform.commercefacades.product.data.ProductData;
import de.hybris.platform.core.model.product.ProductModel;
import de.hybris.platform.product.ProductService;
import de.hybris.platform.servicelayer.dto.converter.Converter;

import java.util.Optional;

public class DefaultProductFacade implements ProductFacade {
    private ProductService productService;
    private Converter<ProductModel, ProductData> converter;

    @Override
    public Optional<ProductData> getProduct(String code) {
        ProductModel productModel = productService.getProductForCode(code);

        return Optional.ofNullable(productModel).map(product -> converter.convert(product));
    }

    public void setProductService(ProductService productService) {
        this.productService = productService;
    }

    public void setConverter(Converter<ProductModel, ProductData> converter) {
        this.converter = converter;
    }
}
