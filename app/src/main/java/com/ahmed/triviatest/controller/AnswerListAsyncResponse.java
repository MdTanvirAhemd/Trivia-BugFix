package com.ahmed.triviatest.controller;


import com.ahmed.triviatest.model.Question;

import java.util.ArrayList;

public interface AnswerListAsyncResponse {
    void processFinished(ArrayList<Question> questionArrayList);
}
