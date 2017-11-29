package com.app.rakez.winnersprit.data;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by RAKEZ on 11/29/2017.
 */

public class DataRepository {



    public  List<String> getQuestionId(){
        List<String> questionId = new ArrayList<>();
        for(int i = 0 ; i < 20 ; i++){
            questionId.add("ques"+i); //1
        }
        return questionId;
    }

    public List<String> getQuestions(){
        List<String> questions = new ArrayList<>();
        questions.add("If their are 720 buses coming from biratnagar to kathmandu dated on 27th July, 2018. The main highway through which bus are coming is 10 cm wide? Do you think the buses can easily pass through?"); //1
        questions.add("Very Short Question"); //2
        questions.add("Long Question than usaual content of the question. This is example of long question"); //3
        questions.add("Long Question than usaual content of the question. This is example of long question. Question exceeding 5 lines what happens. Long Question than usaual content of the question. This is example of long question"); //4
        questions.add("Capital of Nepal?"); //5
        questions.add("Capital of Nepal?"); //6
        questions.add("Capital of Nepal?"); //7
        questions.add("Capital of Nepal?"); //8
        questions.add("Capital of Nepal?"); //9
        questions.add("Capital of Nepal?"); //10
        questions.add("Capital of Nepal?"); //11
        questions.add("Capital of Nepal?"); //12
        questions.add("Capital of Nepal?"); //13
        questions.add("Capital of Nepal?"); //14
        questions.add("Capital of Nepal?"); //15
        questions.add("Capital of Nepal?"); //16
        questions.add("Capital of Nepal?"); //17
        questions.add("Capital of Nepal?"); //18
        questions.add("Capital of Nepal?"); //19
        questions.add("Capital of Nepal?"); //20

        return questions;
    }

    public List<List<String>> getAnswers(){
        List<String> answer;
        List<List<String>> answersList = new ArrayList<>();
        for(int i=0 ; i<20 ; i++){
            answer  = new ArrayList<>();
            answer.add("Short option : "+i); //1
            answer.add("Medium size option for the question : "+i); //2
            answer.add("Long option for the question according to the demand. This answer should exceed 2 lines : "+i); //3
            answer.add("No : "+i); //4
            answersList.add(answer);
        }
        return answersList;

    }

    public List<Integer> getcorrectAnswers(){
        List<Integer> correntAnswers = new ArrayList<>();
        for(int i = 0 ; i < 20 ; i++){
            correntAnswers.add((i%4)+1); //1
        }
        return correntAnswers;
    }
}
