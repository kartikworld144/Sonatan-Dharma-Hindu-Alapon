package com.kartikworld.bangali;

import android.app.AlertDialog;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

public class QuizActivity extends AppCompatActivity implements View.OnClickListener{

    TextView totalQuestionsTextView;
    TextView questionTextView;
    Button ansA, ansB, ansC, ansD;
    Button submitBtn;

    int score=0;
    int totalQuestion = QuestionAnswer.question.length;
    int currentQuestionIndex = 0;
    String selectedAnswer = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.quiz_activity);

        totalQuestionsTextView = findViewById(R.id.total_question);
        questionTextView = findViewById(R.id.question);
        ansA = findViewById(R.id.ans_A);
        ansB = findViewById(R.id.ans_B);
        ansC = findViewById(R.id.ans_C);
        ansD = findViewById(R.id.ans_D);
        submitBtn = findViewById(R.id.submit_btn);

        ansA.setOnClickListener(this);
        ansB.setOnClickListener(this);
        ansC.setOnClickListener(this);
        ansD.setOnClickListener(this);
        submitBtn.setOnClickListener(this);

        getDigitENGFromBNG();

        loadNewQuestion();

        submitBtn.setBackgroundColor(Color.BLUE);
        //+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
        // add toolbar
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Back");

        // add back arrow to toolbar
        if (getSupportActionBar() != null){
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
        }
        // don't forget click listener for back button
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setNavigationOnClickListener(arrow -> onBackPressed());
        //-------------------------------------------------------------------------------------------------

    }

    @Override
    public void onClick(View view) {

        ansA.setBackgroundColor(Color.WHITE);
        ansB.setBackgroundColor(Color.WHITE);
        ansC.setBackgroundColor(Color.WHITE);
        ansD.setBackgroundColor(Color.WHITE);
        submitBtn.setBackgroundColor(Color.GREEN);
        

            Button clickedButton = (Button) view;
            if(clickedButton.getId()==R.id.submit_btn){
                if(selectedAnswer.equals(QuestionAnswer.correctAnswers[currentQuestionIndex])){
                    score++;
                }
                currentQuestionIndex++;
                loadNewQuestion();
                submitBtn.setBackgroundColor(Color.YELLOW);

            }else{
                //choices button clicked
                selectedAnswer  = clickedButton.getText().toString();
                clickedButton.setBackgroundColor(Color.MAGENTA);

            }

        }


    void loadNewQuestion(){

        if(currentQuestionIndex == totalQuestion ){
            finishQuiz();
            return;
        }

        questionTextView.setText(QuestionAnswer.question[currentQuestionIndex]);
        ansA.setText(QuestionAnswer.choices[currentQuestionIndex][0]);
        ansB.setText(QuestionAnswer.choices[currentQuestionIndex][1]);
        ansC.setText(QuestionAnswer.choices[currentQuestionIndex][2]);
        ansD.setText(QuestionAnswer.choices[currentQuestionIndex][3]);

    }

    void finishQuiz(){

        getDigitBNG();

    }

    void restartQuiz(){
        score = 0;
        currentQuestionIndex =0;
        loadNewQuestion();
    }


    public String  getDigitENGFromBNG(){

        String count =Integer.toString(totalQuestion);
        count = count.replace("0", "০").replace("1", "১").replace("2", "২").replace("3", "৩");
        count = count.replace("4", "৪").replace("5", "৫").replace("6", "৬").replace("7", "৭");
        count = count.replace("8", "৮").replace("9", "৯");
        totalQuestionsTextView.setText("মোট প্রশ্ন : "+count +" টি");


        return (null);
    }


    public String  getDigitBNG(){

        String scr =Integer.toString(score);
        scr = scr.replace("0", "০").replace("1", "১").replace("2", "২").replace("3", "৩");
        scr = scr.replace("4", "৪").replace("5", "৫").replace("6", "৬").replace("7", "৭");
        scr = scr.replace("8", "৮").replace("9", "৯");

        String totalQues =Integer.toString(totalQuestion);
        totalQues = totalQues.replace("0", "০").replace("1", "১").replace("2", "২").replace("3", "৩");
        totalQues = totalQues.replace("4", "৪").replace("5", "৫").replace("6", "৬").replace("7", "৭");
        totalQues = totalQues.replace("8", "৮").replace("9", "৯");



        String passStatus = "";

        if(score > totalQuestion*0.60){
            passStatus = "আপনি গৃহীত হয়েছেন";
        }else{
            passStatus = "আপনি ব্যর্থ হয়েছেন";
        }
        new AlertDialog.Builder(this)
                .setTitle(passStatus)
                .setMessage(totalQues+" এর মধ্যে "+ "আপনার স্কোর হল "+ scr)
                .setPositiveButton("পুনরায় শুরু করুন",(dialogInterface, i) -> restartQuiz() )
                .setCancelable(false)
                .show();
        return (null);
    }

}