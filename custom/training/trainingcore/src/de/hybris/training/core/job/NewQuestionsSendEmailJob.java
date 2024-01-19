package de.hybris.training.core.job;

import com.es.questions.model.QuestionModel;
import de.hybris.platform.basecommerce.model.site.BaseSiteModel;
import de.hybris.platform.cronjob.enums.CronJobResult;
import de.hybris.platform.cronjob.enums.CronJobStatus;
import de.hybris.platform.cronjob.model.CronJobHistoryModel;
import de.hybris.platform.cronjob.model.CronJobModel;
import de.hybris.platform.servicelayer.cronjob.AbstractJobPerformable;
import de.hybris.platform.servicelayer.cronjob.CronJobHistoryService;
import de.hybris.platform.servicelayer.cronjob.PerformResult;
import de.hybris.platform.processengine.BusinessProcessService;
import de.hybris.platform.servicelayer.model.ModelService;
import de.hybris.platform.site.BaseSiteService;
import de.hybris.platform.store.BaseStoreModel;
import de.hybris.training.core.model.NewQuestionsEmailProcessModel;
import de.hybris.training.core.services.QuestionService;
import org.apache.commons.collections.CollectionUtils;

import java.util.Comparator;
import java.util.Date;
import java.util.List;

public class NewQuestionsSendEmailJob extends AbstractJobPerformable<CronJobModel> {
    private static final String NEW_QUESTIONS_PROCESS_NAME = "newQuestionsEmailProcess";
    private static final String BASE_SITE = "electronics";

    private CronJobHistoryService cronJobHistoryService;
    private BusinessProcessService businessProcessService;
    private BaseSiteService baseSiteService;
    private ModelService modelService;
    private QuestionService questionService;

    @Override
    public PerformResult perform(CronJobModel cronJob) {
        NewQuestionsEmailProcessModel process = createNewQuestionsEmailProcessModel();
        List<QuestionModel> questions = getNewQuestions(cronJob);

        if (CollectionUtils.isEmpty(questions)) {
            return new PerformResult(CronJobResult.FAILURE, CronJobStatus.FINISHED);
        }

        process.setQuestions(questions);

        modelService.save(process);
        businessProcessService.startProcess(process);

        return new PerformResult(CronJobResult.SUCCESS, CronJobStatus.FINISHED);
    }

    private List<QuestionModel> getNewQuestions(CronJobModel cronJob) {
        Date lastExecuteTime = getLastCronJobSuccessTime(cronJob);

        return questionService.getQuestionsCreatedAfterTime(lastExecuteTime);
    }
    private Date getLastCronJobSuccessTime(CronJobModel cronJob) {
        return cronJobHistoryService.getCronJobHistoryBy(cronJob.getCode()).stream()
                .filter(job -> CronJobStatus.FINISHED.equals(job.getStatus()))
                .filter(job -> CronJobResult.SUCCESS.equals(job.getResult()))
                .max(Comparator.comparing(CronJobHistoryModel::getEndTime))
                .map(CronJobHistoryModel::getEndTime)
                .orElse(null);
    }

    private NewQuestionsEmailProcessModel createNewQuestionsEmailProcessModel() {
        NewQuestionsEmailProcessModel process = businessProcessService
                .createProcess(NEW_QUESTIONS_PROCESS_NAME + System.currentTimeMillis(), NEW_QUESTIONS_PROCESS_NAME);
        BaseSiteModel baseSite = baseSiteService.getBaseSiteForUID(BASE_SITE);
        List<BaseStoreModel> baseStoreList = baseSite.getStores();

        process.setSite(baseSite);
        process.setLanguage(baseSite.getDefaultLanguage());

        if (CollectionUtils.isNotEmpty(baseStoreList)) {
            process.setStore(baseSite.getStores().get(0));
        }

        return process;
    }

    public void setCronJobHistoryService(CronJobHistoryService cronJobHistoryService) {
        this.cronJobHistoryService = cronJobHistoryService;
    }

    public void setBusinessProcessService(BusinessProcessService businessProcessService) {
        this.businessProcessService = businessProcessService;
    }

    public void setBaseSiteService(BaseSiteService baseSiteService) {
        this.baseSiteService = baseSiteService;
    }

    @Override
    public void setModelService(ModelService modelService) {
        this.modelService = modelService;
    }

    public void setQuestionService(QuestionService questionService) {
        this.questionService = questionService;
    }
}
