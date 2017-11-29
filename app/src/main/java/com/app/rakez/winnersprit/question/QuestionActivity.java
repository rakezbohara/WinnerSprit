package com.app.rakez.winnersprit.question;

import android.app.Dialog;
import android.graphics.Color;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.app.rakez.winnersprit.R;
import com.app.rakez.winnersprit.data.DataRepository;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class QuestionActivity extends AppCompatActivity implements View.OnClickListener{


    private static String TAG = "Q/A Activity Log";
    @BindView(R.id.question_exit) TextView exitTV;
    @BindView(R.id.question_bookmark) ImageButton bookmarkQuestion;
    @BindView(R.id.question_timeout) TextView timeOutTV;
    @BindView(R.id.question_no_RV) RecyclerView questionNoRV;
    @BindView(R.id.question_TV) TextView questionTV;
    @BindView(R.id.answer_RV) RecyclerView answerRV;
    @BindView(R.id.question_next) TextView nextQuestionTV;

    DataRepository dataRepository;

    //Q/A elements
    List<String> questionId;
    List<String> questionList;
    List<List<String>> answersList;
    List<Integer> correctAnswer;
    //Current Question
    List<String> currentAnswerList;
    Integer currentCorrectAnswer;
    String currentQuestion;
    String currentQuestionId;

    Integer currentQuestionNo = 0;
    List<Integer> selectedAnswersList;

    RecyclerView.LayoutManager linearLayoutManager;
    AdapterAnswer adapterAnswer;

    Long answerTime;
    List<Long> answerTimeList;

    //Timer
    CountDownTimer countDownTimer;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_question);
        ButterKnife.bind(this);
        initialize();
        initializeQuestion(questionId.get(currentQuestionNo), questionList.get(currentQuestionNo),answersList.get(currentQuestionNo),correctAnswer.get(currentQuestionNo));
        nextQuestionTV.setOnClickListener(this);
    }
    private void initialize() {
        dataRepository = new DataRepository();
        questionId = new ArrayList<>();
        questionList = new ArrayList<>();
        answersList = new ArrayList<>();
        currentAnswerList = new ArrayList<>();
        selectedAnswersList = new ArrayList<>();
        answerTimeList = new ArrayList<>();
        countDownTimer = new CountDownTimer(30000,1000) {
            @Override
            public void onTick(long l) {
                answerTime = 30000-l;
                if(l<10000){
                    timeOutTV.setTextColor(Color.RED);
                    timeOutTV.setText("00:0"+l/1000);
                }else{
                    timeOutTV.setTextColor(Color.WHITE);
                    timeOutTV.setText("00:"+l/1000);
                }

            }
            @Override
            public void onFinish() {
                timeOutTV.setText("00:00");
                nextQuestion();
            }
        };
        questionId = dataRepository.getQuestionId();
        questionList = dataRepository.getQuestions();
        answersList = dataRepository.getAnswers();
        Log.d(TAG,"answer3 size is: "+answersList.size());
        Log.d(TAG,"answer4 size is: "+answersList.get(currentQuestionNo).size());
        correctAnswer = dataRepository.getcorrectAnswers();
        linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        answerRV.setLayoutManager(linearLayoutManager);
    }

    private void initializeQuestion(String questionId, String question, List<String> answers, Integer correctAnswer) {
        Log.d(TAG,"answer size is: "+answers.size());
        currentQuestionId = questionId;
        currentQuestion = question;
        currentAnswerList = answers;
        currentCorrectAnswer = correctAnswer;
        Log.d(TAG,"answer2 size is: "+currentAnswerList.size());
        questionTV.setText(currentQuestion);
        adapterAnswer = new AdapterAnswer(this, currentAnswerList);
        answerRV.setAdapter(adapterAnswer);
        countDownTimer.start();
    }

    @Override
    public void onClick(View view) {
        int id = view.getId();
        switch (id){
            case R.id.question_exit:
                //flow
                break;
            case R.id.question_next:
                nextQuestion();
                break;
            //other case

            default:
                break;
        }
    }

    void nextQuestion(){
        selectedAnswersList.add(adapterAnswer.getSelectedAnswer());
        answerTimeList.add(answerTime);
        currentQuestionNo++;
        if(currentQuestionNo==19){
            nextQuestionTV.setText("FINISH");
            initializeQuestion(questionId.get(currentQuestionNo), questionList.get(currentQuestionNo), answersList.get(currentQuestionNo),correctAnswer.get(currentQuestionNo));
        }else{
            if(currentQuestionNo<20){
                initializeQuestion(questionId.get(currentQuestionNo), questionList.get(currentQuestionNo), answersList.get(currentQuestionNo),correctAnswer.get(currentQuestionNo));
            }else{
                Toast.makeText(this, "Send to Result Dialog",Toast.LENGTH_SHORT).show();
            }
        }
    }

    public void showResultDialog(){
        Dialog resultDialog = new Dialog(this);
    }
}
