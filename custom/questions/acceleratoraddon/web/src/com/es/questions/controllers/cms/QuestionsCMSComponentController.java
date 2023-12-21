package com.es.questions.controllers;

import com.es.questions.model.QuestionCMSComponentModel;
import de.hybris.platform.addonsupport.controllers.cms.AbstractCMSAddOnComponentController;
import org.springframework.ui.Model;

@Controller()
public class QuestionsCMSComponentController extends AbstractCMSAddOnComponentController<QuestionCMSComponentModel> {
    @Override
    protected void fillModel(javax.servlet.http.HttpServletRequest request, Model model, QuestionCMSComponentModel component) {

    }
}
