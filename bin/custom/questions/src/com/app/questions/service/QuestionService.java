package com.app.questions.service;

import com.app.questions.model.QuestionModel;

import java.util.Date;
import java.util.List;

public interface QuestionService {
    List<QuestionModel> getQuestionsAfterDate(Date date);
}
