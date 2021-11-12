package com.ahmed.triviatest.data;

import android.util.Log;

import com.ahmed.triviatest.controller.AnswerListAsyncResponse;
import com.ahmed.triviatest.controller.AppController;
import com.ahmed.triviatest.model.Question;
import com.android.volley.Request;
import com.android.volley.toolbox.JsonArrayRequest;

import org.json.JSONException;

import java.util.ArrayList;
import java.util.List;

public class Repository {
    ArrayList<Question> questionArrayList = new ArrayList<>();

    String url = "https://raw.githubusercontent.com/curiousily/simple-quiz/master/script/statements-data.json";

    public List<Question> getQuestions(final AnswerListAsyncResponse callBack) {

        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET,
                url, null, response -> {
            for (int i = 0; i < response.length(); i++) {

                try {
                    Question question = new Question(response.getJSONArray(i).get(0).toString(),
                            response.getJSONArray(i).getBoolean(1));

                    //Add questions to arraylist/list
                    questionArrayList.add(question);

                  //  Log.d("Hello", "getQuestions: " + i);


                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
            if (null != callBack) callBack.processFinished(questionArrayList);



        }, error -> {

        });

     //   Log.d("TAGG","----------->" + jsonArrayRequest.toString()+ "<------------");


        AppController.getInstance().addToRequestQueue(jsonArrayRequest);

        return questionArrayList;
    }
}
