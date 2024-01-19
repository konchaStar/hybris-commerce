package com.es.questions.controllers.cms;

import com.es.questions.facades.ProductFacade;
import com.es.questions.model.WarrantyCMSComponentModel;
import de.hybris.platform.acceleratorservices.data.RequestContextData;
import de.hybris.platform.addonsupport.controllers.cms.AbstractCMSAddOnComponentController;
import de.hybris.platform.core.model.product.ProductModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.util.Objects;

@Controller("WarrantyCMSComponentController")
@RequestMapping("/view/WarrantyCMSComponentController")
public class WarrantyCMSComponentController extends AbstractCMSAddOnComponentController<WarrantyCMSComponentModel> {
    private static final String WARRANTY = "warranty";

    @Override
    protected void fillModel(HttpServletRequest request, Model model, WarrantyCMSComponentModel component) {
        RequestContextData data = getRequestContextData(request);

        if (Objects.nonNull(data)) {
            ProductModel product = data.getProduct();

            if (Objects.nonNull(product)) {
                model.addAttribute(WARRANTY, product.getWarrantyYears());
            }
        }
    }
}
