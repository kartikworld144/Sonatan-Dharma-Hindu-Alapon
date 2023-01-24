package com.kartikworld.bangali;


import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class QuestionCollection extends AppCompatActivity {
    RadioGroup radioGroup;
    TextView lblQuestion;
    RadioButton optionA;
    RadioButton optionB;
    RadioButton optionC;
    RadioButton optionD;
    Button confirm;
    String rightAnswer;
    String Answer;
    public static List<QuestionModule> question_list;
    int score;
    public static String SUBJECT_NAME = "";
    public static ArrayList<ArrayList<QuestionModule>> questionBank = new ArrayList<>();
    public static ArrayList<HashMap<String, String>> subjectList = new ArrayList<>();
    LinearLayout rootLay;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_question_collection);
        rootLay = findViewById(R.id.rootLay);
        confirm = findViewById(R.id.confirm);
        lblQuestion = findViewById(R.id.lblPergunta);
        optionA = findViewById(R.id.opcaoA);
        optionB = findViewById(R.id.opcaoB);
        optionC = findViewById(R.id.opcaoC);
        optionD = findViewById(R.id.opcaoD);
        score = 0;
        radioGroup = findViewById(R.id.radioGroup);
        loadQuestion();

    }


    @Override
    protected void onRestart() {
        super.onRestart();
        loadQuestion();
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (rootLay != null)
            rootLay.startAnimation(AnimationUtils.loadAnimation(QuestionCollection.this, R.anim.middle_to_top));
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }

    private void loadQuestion() {
        //Toast.makeText(getApplicationContext(), "Total Questions: "+question_list.size(), Toast.LENGTH_SHORT).show();
        if (question_list.size() > 0) {
            QuestionModule q = question_list.remove(0);
            lblQuestion.setText(q.getQuestion());
            List<String> answers = q.getAnswers();

            optionA.setText(answers.get(0));
            optionB.setText(answers.get(1));
            optionC.setText(answers.get(2));
            optionD.setText(answers.get(3));
            rightAnswer = q.getRightAnswer();
        } else {
            Intent intent = new Intent(this, ScoreActivity.class);
            intent.putExtra("score", score);
            startActivity(intent);
            finish();
        }
    }

    public void loadAnswer(View view) {
        int op = radioGroup.getCheckedRadioButtonId();

        switch (op) {
            case R.id.opcaoA:
                Answer = "A";
                break;

            case R.id.opcaoB:
                Answer = "B";
                break;

            case R.id.opcaoC:
                Answer = "C";
                break;

            case R.id.opcaoD:
                Answer = "D";
                break;

            default:
                return;

        }

        radioGroup.clearCheck();

        this.startActivity(isRightOrWrong(Answer));

    }

    private Intent isRightOrWrong(String Answer) {
        Intent screen;
        if (Answer.equals(rightAnswer)) {
            this.score += 1;
            screen = new Intent(this, RightActivity.class);

        } else {
            screen = new Intent(this, WrongActivity.class);
        }

        return screen;
    }


    //===============================================================================================


    //====================================================================
    //====================================================================
    public static ArrayList<QuestionModule> questions;

    public static void createQuestionBank() {
        QuestionCollection.subjectList = new ArrayList<>();
        QuestionCollection.questionBank = new ArrayList<>();


        //------------- Subject 1
        questions = new ArrayList() {
            {
                add(new QuestionModule("1.Krishna is a God of compassion, tenderness and?", "B", "Harmony", "Love", "War", "Peace"));
                add(new QuestionModule("2.He is widely revered among Indian divinities. Which avatar is Krishna?", "A", "Eight avatar", "Ninth avatar", "Sixth avatar", "Seventh avatar"));
                add(new QuestionModule("3.Which day do the Hindus celebrate Krishna's birthday?", "C", "Hindu calendar", "Janmashtami", "Roman calendar", "Gregorian calendar"));
                add(new QuestionModule("4.Krishna is also known by numerous names, such as Govinda and", "D", "Mukunda", "Vasudeva and Makhan chor", "Madhusudhana", "All of the above"));
                add(new QuestionModule("5.In some sub-traditions, Lord Krishna is worshipped as", "A", "Bhagavad Gita", "War of Art", "Dharma", "Art of War"));
                add(new QuestionModule("6.How many stages does Krishna iconography show him?", "D", "An infant", "Young boy and surrounded by women devotees", "A young man with Radha", "All of the above"));
                add(new QuestionModule("7.What does the word Krishna in Sanskrit primarily mean?", "D", "Black", "Dark", "Dark blue", "All of the above"));
                add(new QuestionModule("8.Where does the name Krishna originate from?", "D", "Kirshi", "Rishna", "Krish", "Sanskrit"));
                add(new QuestionModule("9.What are the sub-traditions that arose in the context of the medieval era called?", "B", "Krishnaism", "Bhakti movement", "Bhagwati movement", "Krishna Movement"));
                add(new QuestionModule("10.In some sub-traditions, Krishna is worshipped as?", "D", "Shiva", "Kohinoor", "Lingam", "Svayam Bhagavan"));

            }
        };
        QuestionModule.createQuestionsForSubject("Quiz", R.drawable.book, questions);


        //------------- Subject 2
        questions = new ArrayList() {
            {
                add(new QuestionModule("১।রামচন্দ্রকে কতো বছরের জন্য বনবাসে প্রেরণ করা হয়েছিল ?", "C", "৮", "১২", "১৪", "১৬"));
                add(new QuestionModule("২।নিচের কোন দেশের রাষ্ট্রধর্ম হিন্দু ", "C", "উগান্ডা", "ভারত", "নেপাল", "ঘানা"));
                add(new QuestionModule("৩।শ্রীকৃষ্ণ অর্জ্জুনকে কি নামে ডাকতেন ?", "D", "রাধেয়", "কৌন্তেয়", "গুড়াকেশ", "পার্থ"));
                add(new QuestionModule("৪।শ্রীকৃষ্ণের পালক পিতার নাম কি ছিল ?", "C", "কুন্তিভোজ", "বসুদেব", "নন্দরাজ", "সুসেন"));
                add(new QuestionModule("৫।বেদ কার থেকে উদ্ভুত ?", "B", "মানুষ", "ঈশ্বর", "মুনি-ঋষি", "অসুর"));
                add(new QuestionModule("৬।মহাভারতের যুদ্ধ কতদিন ব্যাপী হয়েছিল ?", "B", "৭", "১৮", "১১", "৫৪"));
                add(new QuestionModule("৭।রাবণের স্ত্রীর নাম কি ছিল ?", "C", "সুমিত্রা", "সীতা", "মন্দোদরী", "উর্মিলা"));
                add(new QuestionModule("৮।শিবতান্ডব স্তোত্রের প্রণেতা কে ?", "B", "বিশ্বামিত্র", "রাবণ", "ঋষি মার্কন্ডেয়", "অশ্বসেন"));
                add(new QuestionModule("৯।কর্ণের পালক পিতার নাম কি ছিল ?", "B", "পান্ডু", "অধিরথ", "ভূড়িশ্রবা", "ধৃতরাষ্ট্র"));
                add(new QuestionModule("১০।হিন্দুধর্মের সবচেয়ে বড় মন্দির কোন দেশে অবস্থিত ?", "C", "ভারত", "নেপাল", "কম্বোডিয়া", "বাংলাদেশ"));
            }
        };
        QuestionModule.createQuestionsForSubject("কুইজ", R.drawable.book, questions);


        //------------- Subject 3
        questions = new ArrayList() {
            {
                add(new QuestionModule("১। রামচন্দ্রকে কতো বছরের জন্য বনবাসে প্রেরণ করা হয়েছিল ?", "D", "৮", "১২", "১৪", "১৬"));
                add(new QuestionModule("২।", "A", "", "", "", ""));
                add(new QuestionModule("৩।", "", "", "", "", ""));
                add(new QuestionModule("৪।", "", "", "", "", ""));
                add(new QuestionModule("৫।", "", "", "", "", ""));
                add(new QuestionModule("৬।", "", "", "", "", ""));
                add(new QuestionModule("৭।", "", "", "", "", ""));
                add(new QuestionModule("৮।", "", "", "", "", ""));
                add(new QuestionModule("৯।", "", "", "", "", ""));
                add(new QuestionModule("১০।", "", "", "", "", ""));
            }
        };
        QuestionModule.createQuestionsForSubject("কুইজ", R.drawable.book, questions);


        //------------- Subject 4
        questions = new ArrayList() {
            {
                add(new QuestionModule("১। বলরাম পূর্বজন্মে কে ছিলেন ?", "D", "হিরন্যাক্ষ", "সুগ্রীব", "বলি", "লক্ষণ"));
                add(new QuestionModule("২।রামচন্দ্র কার থেকে অস্ত্রবিদ্যা লাভ করেন ?", "A", "বিশ্বামিত্র", "বশিষ্ট", "পরশুরাম", "মেধস"));
                add(new QuestionModule("৩।রাবণের পিতার নাম কি ছিল ?", "C", "শুক্রাচার্য", "বিশ্বামিত্র", "পুল্যস্ত", "দুর্বাশা"));
                add(new QuestionModule("৪।মহাভারতের মূল রচনাকারী কে ছিলেন ?", "B", "পরাশর", "বেদব্যাস", "বিশ্বামিত্র", "বাল্মিকী"));
                add(new QuestionModule("৫।বেদে কাকে দেবতাদের মুখ বলা হয়েছে ?", "A", "অগ্নি", "ঊষা", "বরুণ", "রুদ্র"));
                add(new QuestionModule("৬।বেদে ভগবান শিবকে কি নামে অবিহিত করা হয়েছে ?", "C", "মহাকাল", "মহেশ্বর", "রুদ্র", "নটরাজ"));
                add(new QuestionModule("৭।রাম কার পুত্র ছিলেন ?", "D", "সুমিত্রা", "গান্ধারী", "কৈকেয়ী", "কৌশল্যা"));
                add(new QuestionModule("৮।কাকে King of Martial Arts বলা হয় ?", "A", "পরশুরাম", "ভীষ্ম", "মেঘনাদ", "অর্জুন"));
                add(new QuestionModule("৯।হিন্দুধর্মের প্রধান ধর্মগ্রন্থ কোনটি?", "B", "উপনিষদ", "বেদসংহিতা", "পুরাণ", "পরাশর স্মৃতি"));
                add(new QuestionModule("১০।কুরুক্ষেত্র যুদ্ধের সময় ভগবান শ্রীকৃষ্ণ ও অর্জুনের কথোপকথন কী নামে পরিচিত?", "C", "বৈষ্ণব", "পুরাণ", "ভগবত গীতা", "রামায়ণ"));
            }
        };
        QuestionModule.createQuestionsForSubject("কুইজ", R.drawable.book, questions);


        //------------- Subject 5
        questions = new ArrayList() {
            {
                add(new QuestionModule("১। রামচন্দ্রকে কতো বছরের জন্য বনবাসে প্রেরণ করা হয়েছিল ?", "C", "৮", "১২", "১৪", "১৬"));
                add(new QuestionModule("২।", "A", "", "", "", ""));
                add(new QuestionModule("৩।", "", "", "", "", ""));
                add(new QuestionModule("৪।", "", "", "", "", ""));
                add(new QuestionModule("৫।", "", "", "", "", ""));
                add(new QuestionModule("৬।", "", "", "", "", ""));
                add(new QuestionModule("৭।", "", "", "", "", ""));
                add(new QuestionModule("৮।", "", "", "", "", ""));
                add(new QuestionModule("৯।", "", "", "", "", ""));
                add(new QuestionModule("১০।", "", "", "", "", ""));
            }
        };
        QuestionModule.createQuestionsForSubject("কুইজ", R.drawable.book, questions);


        //------------- Subject 6
        questions = new ArrayList() {
            {
                add(new QuestionModule("১। রামচন্দ্রকে কতো বছরের জন্য বনবাসে প্রেরণ করা হয়েছিল ?", "C", "৮", "১২", "১৪", "১৬"));
                add(new QuestionModule("২।", "A", "", "", "", ""));
                add(new QuestionModule("৩।", "", "", "", "", ""));
                add(new QuestionModule("৪।", "", "", "", "", ""));
                add(new QuestionModule("৫।", "", "", "", "", ""));
                add(new QuestionModule("৬।", "", "", "", "", ""));
                add(new QuestionModule("৭।", "", "", "", "", ""));
                add(new QuestionModule("৮।", "", "", "", "", ""));
                add(new QuestionModule("৯।", "", "", "", "", ""));
                add(new QuestionModule("১০।", "", "", "", "", ""));
            }
        };
        QuestionModule.createQuestionsForSubject("কুইজ", R.drawable.book, questions);


        //------------- Subject 7
        questions = new ArrayList() {
            {
                add(new QuestionModule("১। রামচন্দ্রকে কতো বছরের জন্য বনবাসে প্রেরণ করা হয়েছিল ?", "C", "৮", "১২", "১৪", "১৬"));
                add(new QuestionModule("২।", "A", "", "", "", ""));
                add(new QuestionModule("৩।", "", "", "", "", ""));
                add(new QuestionModule("৪।", "", "", "", "", ""));
                add(new QuestionModule("৫।", "", "", "", "", ""));
                add(new QuestionModule("৬।", "", "", "", "", ""));
                add(new QuestionModule("৭।", "", "", "", "", ""));
                add(new QuestionModule("৮।", "", "", "", "", ""));
                add(new QuestionModule("৯।", "", "", "", "", ""));
                add(new QuestionModule("১০।", "", "", "", "", ""));
            }
        };
        QuestionModule.createQuestionsForSubject("কুইজ", R.drawable.book, questions);



        //------------- Subject 8
        questions = new ArrayList() {
            {
                add(new QuestionModule("১। রামচন্দ্রকে কতো বছরের জন্য বনবাসে প্রেরণ করা হয়েছিল ?", "C", "৮", "১২", "১৪", "১৬"));
                add(new QuestionModule("২।", "A", "", "", "", ""));
                add(new QuestionModule("৩।", "", "", "", "", ""));
                add(new QuestionModule("৪।", "", "", "", "", ""));
                add(new QuestionModule("৫।", "", "", "", "", ""));
                add(new QuestionModule("৬।", "", "", "", "", ""));
                add(new QuestionModule("৭।", "", "", "", "", ""));
                add(new QuestionModule("৮।", "", "", "", "", ""));
                add(new QuestionModule("৯।", "", "", "", "", ""));
                add(new QuestionModule("১০।", "", "", "", "", ""));
            }
        };
        QuestionModule.createQuestionsForSubject("কুইজ", R.drawable.book, questions);


        //------------- Subject 9
        questions = new ArrayList() {
            {
                add(new QuestionModule("১। রামচন্দ্রকে কতো বছরের জন্য বনবাসে প্রেরণ করা হয়েছিল ?", "C", "৮", "১২", "১৪", "১৬"));
                add(new QuestionModule("২।", "A", "", "", "", ""));
                add(new QuestionModule("৩।", "", "", "", "", ""));
                add(new QuestionModule("৪।", "", "", "", "", ""));
                add(new QuestionModule("৫।", "", "", "", "", ""));
                add(new QuestionModule("৬।", "", "", "", "", ""));
                add(new QuestionModule("৭।", "", "", "", "", ""));
                add(new QuestionModule("৮।", "", "", "", "", ""));
                add(new QuestionModule("৯।", "", "", "", "", ""));
                add(new QuestionModule("১০।", "", "", "", "", ""));
            }
        };
        QuestionModule.createQuestionsForSubject("কুইজ", R.drawable.book, questions);


        //------------- Subject 10
        questions = new ArrayList() {
            {
                add(new QuestionModule("১। রামচন্দ্রকে কতো বছরের জন্য বনবাসে প্রেরণ করা হয়েছিল ?", "C", "৮", "১২", "১৪", "১৬"));
                add(new QuestionModule("২।", "A", "", "", "", ""));
                add(new QuestionModule("৩।", "", "", "", "", ""));
                add(new QuestionModule("৪।", "", "", "", "", ""));
                add(new QuestionModule("৫।", "", "", "", "", ""));
                add(new QuestionModule("৬।", "", "", "", "", ""));
                add(new QuestionModule("৭।", "", "", "", "", ""));
                add(new QuestionModule("৮।", "", "", "", "", ""));
                add(new QuestionModule("৯।", "", "", "", "", ""));
                add(new QuestionModule("১০।", "", "", "", "", ""));
            }
        };
        QuestionModule.createQuestionsForSubject("কুইজ", R.drawable.book, questions);


    }


}
