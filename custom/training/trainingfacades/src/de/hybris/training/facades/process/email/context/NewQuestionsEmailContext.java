package de.hybris.training.facades.process.email.context;

import com.es.questions.model.QuestionModel;
import de.hybris.platform.acceleratorservices.model.cms2.pages.EmailPageModel;
import de.hybris.platform.acceleratorservices.process.email.context.AbstractEmailContext;
import de.hybris.platform.basecommerce.model.site.BaseSiteModel;
import de.hybris.platform.cms2.model.pages.AbstractPageModel;
import de.hybris.platform.core.model.c2l.LanguageModel;
import de.hybris.platform.core.model.user.CustomerModel;
import de.hybris.platform.processengine.model.BusinessProcessModel;
import de.hybris.training.core.model.NewQuestionsEmailProcessModel;
import org.apache.commons.configuration.Configuration;

import javax.annotation.Nonnull;
import java.util.List;

public class NewQuestionsEmailContext extends AbstractEmailContext<NewQuestionsEmailProcessModel> {
    private static final String PROPERTY_EMAIL_FOR_SENDING = "mail.from";
    private static final String PROPERTY_EMAIL_FOR_RECEIVING = "mail.to";
    private static final String PROPERTY_EMAIL_DISPLAY_NAME = "mail.name.to";
    private static final String PROPERTY_EMAIL_FROM_DISPLAY_NAME = "mail.name.from";

    private List<QuestionModel> questions;

    @Override
    public void init(@Nonnull final NewQuestionsEmailProcessModel process, @Nonnull final EmailPageModel emailPage) {
        super.init(process, emailPage);

        Configuration configuration = getConfigurationService().getConfiguration();
        List<QuestionModel> questions = process.getQuestions();

        put(EMAIL, configuration.getString(PROPERTY_EMAIL_FOR_RECEIVING));
        put(DISPLAY_NAME, configuration.getString(PROPERTY_EMAIL_DISPLAY_NAME));
        put(FROM_EMAIL, configuration.getString(PROPERTY_EMAIL_FOR_SENDING));
        put(FROM_DISPLAY_NAME, configuration.getString(PROPERTY_EMAIL_FROM_DISPLAY_NAME));

        setQuestions(questions);
    }

    @Override
    protected BaseSiteModel getSite(NewQuestionsEmailProcessModel businessProcess) {
        return businessProcess.getSite();
    }

    @Override
    protected CustomerModel getCustomer(NewQuestionsEmailProcessModel businessProcess) {
        return businessProcess.getCustomer();
    }

    @Override
    protected LanguageModel getEmailLanguage(NewQuestionsEmailProcessModel businessProcess) {
        return businessProcess.getLanguage();
    }

    public List<QuestionModel> getQuestions() {
        return questions;
    }

    public void setQuestions(List<QuestionModel> questions) {
        this.questions = questions;
    }
}
