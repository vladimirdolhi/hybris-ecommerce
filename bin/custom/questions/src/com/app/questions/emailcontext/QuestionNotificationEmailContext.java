package com.app.questions.emailcontext;

import com.app.questions.model.QuestionModel;
import com.app.questions.model.QuestionNotificationProcessModel;
import de.hybris.platform.acceleratorservices.model.cms2.pages.EmailPageModel;
import de.hybris.platform.acceleratorservices.process.email.context.AbstractEmailContext;
import de.hybris.platform.basecommerce.model.site.BaseSiteModel;
import de.hybris.platform.core.model.c2l.LanguageModel;
import de.hybris.platform.core.model.user.CustomerModel;

import java.util.List;

public class QuestionNotificationEmailContext extends AbstractEmailContext<QuestionNotificationProcessModel> {

    private List<QuestionModel> questions;
    private final String RECEIVER_EMAIL = "sample@gmail.com";
    private final String QUESTIONS = "questions";


    @Override
    public void init(QuestionNotificationProcessModel businessProcessModel, EmailPageModel emailPageModel) {
        super.init(businessProcessModel, emailPageModel);
        setQuestions(businessProcessModel.getQuestions());
        put(DISPLAY_NAME, RECEIVER_EMAIL);
        put(EMAIL, RECEIVER_EMAIL);
        put(QUESTIONS, questions);
    }

    @Override
    protected BaseSiteModel getSite(QuestionNotificationProcessModel businessProcessModel) {
        return businessProcessModel.getSite();
    }

    @Override
    protected CustomerModel getCustomer(QuestionNotificationProcessModel businessProcessModel) {
        return businessProcessModel.getCustomer();
    }

    @Override
    protected LanguageModel getEmailLanguage(QuestionNotificationProcessModel businessProcessModel) {
        return businessProcessModel.getLanguage();
    }

    public List<QuestionModel> getQuestions() {
        return questions;
    }

    public void setQuestions(List<QuestionModel> questions) {
        this.questions = questions;
    }
}
