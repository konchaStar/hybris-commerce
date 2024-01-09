package com.es.questions.controllers.cms;

import com.es.questions.controllers.QuestionsControllerConstants;
import com.es.questions.facades.ProductFacade;
import com.es.questions.model.QuestionCMSComponentModel;
import de.hybris.platform.acceleratorservices.data.RequestContextData;
import de.hybris.platform.addonsupport.controllers.cms.AbstractCMSAddOnComponentController;
import de.hybris.platform.core.model.product.ProductModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.util.Objects;

@Controller("QuestionCMSComponentController")
@RequestMapping(QuestionsControllerConstants.Actions.Cms.QuestionCMSComponent)
public class QuestionCMSComponentController extends AbstractCMSAddOnComponentController<QuestionCMSComponentModel> {
    private static final String QUESTIONS = "questions";

    @Autowired
    private ProductFacade productFacade;

    @Override
    protected void fillModel(HttpServletRequest request, Model model, QuestionCMSComponentModel component) {
        RequestContextData data = getRequestContextData(request);

        if (!Objects.nonNull(data)) {
            ProductModel product = data.getProduct();

            if(!Objects.nonNull(product)) {
                String code = product.getCode();

                productFacade.getProduct(code).ifPresent(prod -> model.addAttribute(QUESTIONS, prod.getQuestions()));
            }

        }
    }

    public void setProductFacade(ProductFacade productFacade) {
        this.productFacade = productFacade;
    }
}
