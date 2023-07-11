package com.app.questions.controllers.cms;

import de.hybris.platform.commercefacades.product.ProductFacade;
import de.hybris.platform.commercefacades.product.ProductOption;
import de.hybris.platform.commercefacades.product.data.ProductData;
import com.app.questions.model.QuestionCMSComponentModel;
import de.hybris.platform.addonsupport.controllers.cms.AbstractCMSAddOnComponentController;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

@RequestMapping(value = "/view/QuestionCMSComponentController")
@Controller("QuestionCMSComponentController")
public class QuestionCMSComponentController extends AbstractCMSAddOnComponentController<QuestionCMSComponentModel> {

    @Resource
    private ProductFacade productFacade;

    @Override
    protected void fillModel(HttpServletRequest request, Model model, QuestionCMSComponentModel component) {

        String productCode = getRequestContextData(request).getProduct().getCode();

        final List<ProductOption> options = new ArrayList<>(List.of(ProductOption.QUESTIONS));

        ProductData productData = productFacade.getProductForCodeAndOptions(productCode, options);

        model.addAttribute("productQuestions", productData);
        model.addAttribute("questionsNumber", Math.min(component.getNumberOfQuestionsToShow(),
                productData.getQuestions().size()));
        model.addAttribute("fontSize", component.getFontSize());

    }

}
