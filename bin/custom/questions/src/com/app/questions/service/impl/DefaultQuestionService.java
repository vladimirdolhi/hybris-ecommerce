package com.app.questions.service.impl;

import com.app.questions.model.QuestionModel;
import com.app.questions.service.QuestionService;
import de.hybris.platform.servicelayer.search.FlexibleSearchQuery;
import de.hybris.platform.servicelayer.search.FlexibleSearchService;
import de.hybris.platform.servicelayer.search.SearchResult;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

public class DefaultQuestionService implements QuestionService {

    private static final String GET_QUESTIONS_AFTER_DATE_QUERY = """
            SELECT {Question.PK} FROM
            {
                Question
            }
            WHERE {Question.CREATIONTIME} > ?date
            """;

    @Resource
    private FlexibleSearchService flexibleSearchService;

    @Override
    public List<QuestionModel> getQuestionsAfterDate(Date date) {
        final FlexibleSearchQuery query = new FlexibleSearchQuery(GET_QUESTIONS_AFTER_DATE_QUERY);
        query.addQueryParameter("date", date);
        final SearchResult<QuestionModel> result = flexibleSearchService.search(query);

        return result.getResult();
    }
}
