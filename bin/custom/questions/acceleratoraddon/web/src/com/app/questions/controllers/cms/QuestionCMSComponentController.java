package com.app.questions.controllers.cms;

import de.hybris.platform.commercefacades.product.data.ProductData;
import com.app.questions.facade.ProductQuestionsFacade;
import com.app.questions.model.QuestionCMSComponentModel;
import de.hybris.platform.addonsupport.controllers.cms.AbstractCMSAddOnComponentController;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

@RequestMapping(value = "/view/QuestionCMSComponentController")
@Controller("QuestionCMSComponentController")
public class QuestionCMSComponentController extends AbstractCMSAddOnComponentController<QuestionCMSComponentModel> {

    @Resource
    private ProductQuestionsFacade productQuestionsFacade;

    @Override
    protected void fillModel(HttpServletRequest request, Model model, QuestionCMSComponentModel component) {

        String productCode = getRequestContextData(request).getProduct().getCode();
        ProductData productData = productQuestionsFacade.getProductData(productCode);

        model.addAttribute("productQuestions", productData);
        model.addAttribute("questionsNumber", Math.min(component.getNumberOfQuestionsToShow(),
                productData.getQuestions().size()));
        model.addAttribute("fontSize", component.getFontSize());

    }

}
