package com.app.questions.job;

import com.app.questions.model.QuestionModel;
import com.app.questions.model.QuestionNotificationCronJobModel;
import com.app.questions.model.QuestionNotificationProcessModel;
import com.app.questions.service.QuestionService;
import de.hybris.platform.basecommerce.model.site.BaseSiteModel;
import de.hybris.platform.cronjob.enums.CronJobResult;
import de.hybris.platform.cronjob.enums.CronJobStatus;
import de.hybris.platform.processengine.BusinessProcessService;
import de.hybris.platform.servicelayer.cronjob.AbstractJobPerformable;
import de.hybris.platform.servicelayer.cronjob.PerformResult;
import de.hybris.platform.servicelayer.model.ModelService;
import de.hybris.platform.site.BaseSiteService;
import de.hybris.platform.store.BaseStoreModel;
import de.hybris.platform.store.services.BaseStoreService;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

public class QuestionNotificationJob extends AbstractJobPerformable<QuestionNotificationCronJobModel> {

    @Resource
    private BusinessProcessService businessProcessService;
    @Resource
    private QuestionService questionService;
    @Resource
    private ModelService modelService;
    @Resource
    private BaseStoreService baseStoreService;
    @Resource
    private BaseSiteService baseSiteService;

    private final String UID = "electronics";

    @Override
    public PerformResult perform(QuestionNotificationCronJobModel questionNotificationCronJobModel) {
        Date cronJobStartDate = new Date();

        QuestionNotificationProcessModel questionNotificationProcessModel = businessProcessService
                .createProcess("question-notification-" + System.currentTimeMillis(), "questionsNotificationProcess");

        List<QuestionModel> questions;

        if (questionNotificationCronJobModel.getLastNotificationDate() != null){
             questions = questionService
                    .getQuestionsAfterDate(questionNotificationCronJobModel.getLastNotificationDate());
        } else {
            questions = questionService.getQuestionsAfterDate(new Date());
        }

        questionNotificationProcessModel.setQuestions(List.copyOf(questions));
        questionNotificationCronJobModel.setLastNotificationDate(cronJobStartDate);

        modelService.save(questionNotificationCronJobModel);
        modelService.save(questionNotificationProcessModel);

        BaseStoreModel baseStoreModel = baseStoreService.getBaseStoreForUid(UID);
        BaseSiteModel baseSiteModel = baseSiteService.getBaseSiteForUID(UID);
        questionNotificationProcessModel.setStore(baseStoreModel);
        questionNotificationProcessModel.setSite(baseSiteModel);
        questionNotificationProcessModel.setLanguage(baseSiteModel.getDefaultLanguage());
        questionNotificationProcessModel.setCurrency(baseStoreModel.getDefaultCurrency());

        businessProcessService.startProcess(questionNotificationProcessModel);
        return new PerformResult(CronJobResult.SUCCESS, CronJobStatus.FINISHED);
    }

}
