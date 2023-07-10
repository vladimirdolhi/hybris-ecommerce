package com.app.questions.populator;

import com.app.questions.data.QuestionData;
import com.app.questions.model.QuestionModel;
import de.hybris.platform.converters.Populator;
import de.hybris.platform.servicelayer.dto.converter.ConversionException;

public class QuestionPopulator implements Populator<QuestionModel, QuestionData> {

    @Override
    public void populate(QuestionModel source, QuestionData target) throws ConversionException {
        target.setQuestion(source.getQuestion());
        target.setQuestionCustomer(source.getQuestionCustomer().getName());
        target.setAnswer(source.getAnswer());
        if (source.getAnswerCustomer() != null) {
            target.setAnswerCustomer(source.getAnswerCustomer().getName());
        }
    }

}
