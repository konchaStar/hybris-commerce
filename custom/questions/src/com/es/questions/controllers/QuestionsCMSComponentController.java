package com.es.questions.controllers;

import com.es.questions.facades.ProductFacade;
import com.es.questions.model.QuestionCMSComponentModel;
import de.hybris.platform.addonsupport.controllers.cms.AbstractCMSAddOnComponentController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/views/QuestionCMSComponentController")
public class QuestionsCMSComponentController extends AbstractCMSAddOnComponentController<QuestionCMSComponentModel> {
    private static final String QUESTIONS = "questions";

    @Autowired
    private ProductFacade productFacade;

    @Override
    protected void fillModel(javax.servlet.http.HttpServletRequest request, Model model, QuestionCMSComponentModel component) {
        String code = getRequestContextData(request).getProduct().getCode();

        productFacade.getProduct(code).ifPresent(product -> model.addAttribute(QUESTIONS, product.getQuestions()));
    }

    public void setProductFacade(ProductFacade productFacade) {
        this.productFacade = productFacade;
    }
}
