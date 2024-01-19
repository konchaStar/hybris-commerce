package de.hybris.training.facades.populators;

import de.hybris.platform.commercefacades.product.data.ProductData;
import de.hybris.platform.converters.Populator;
import de.hybris.platform.core.model.product.ProductModel;
import de.hybris.platform.servicelayer.dto.converter.ConversionException;

import java.util.Optional;

public class WarrantyYearsProductPopulator implements Populator<ProductModel, ProductData> {

    @Override
    public void populate(ProductModel product, ProductData productData) throws ConversionException {
        Integer warranty = Optional.ofNullable(product.getWarrantyYears()).orElse(0);

        productData.setWarrantyYears(warranty);
    }
}
