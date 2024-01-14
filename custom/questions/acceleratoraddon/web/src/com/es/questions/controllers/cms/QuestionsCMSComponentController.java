package com.es.questions.controllers.cms;

import com.es.questions.facades.ProductFacade;
import com.es.questions.model.QuestionModel;
import com.es.questions.model.QuestionsCMSComponentModel;
import de.hybris.platform.acceleratorservices.data.RequestContextData;
import de.hybris.platform.addonsupport.controllers.cms.AbstractCMSAddOnComponentController;
import de.hybris.platform.core.model.product.ProductModel;
import de.hybris.platform.product.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Objects;

@Controller("QuestionsCMSComponentController")
@RequestMapping("/view/QuestionsCMSComponentController")
public class QuestionsCMSComponentController extends AbstractCMSAddOnComponentController<QuestionsCMSComponentModel> {
    private static final String QUESTIONS = "questions";

    @Autowired
    private ProductFacade productFacade;

    @Override
    protected void fillModel(HttpServletRequest request, Model model, QuestionsCMSComponentModel component) {
        RequestContextData data = getRequestContextData(request);

        if (Objects.nonNull(data)) {
            ProductModel product = data.getProduct();

            if(Objects.nonNull(product)) {
                String code = product.getCode();

                productFacade.getProduct(code).ifPresent(prod -> model.addAttribute(QUESTIONS, prod.getQuestions()));
            }

        }
    }

    public void setProductFacade(ProductFacade productFacade) {
        this.productFacade = productFacade;
    }
}