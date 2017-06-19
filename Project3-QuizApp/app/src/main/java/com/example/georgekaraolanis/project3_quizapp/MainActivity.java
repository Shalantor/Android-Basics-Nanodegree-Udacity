package com.example.georgekaraolanis.project3_quizapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    /*Variables for questions*/

    /*Current question, zero for start*/
    private int currentQuestion = 0;

    private String username = "";
    private int userScore = 0;

    /*Correct answers to questions*/
    private int correct1 ;
    private int correct2 ;
    private int correct3 ;
    private String correct4 ;
    private String correct5 ;
    private String correct6 ;
    private int correct7 ;
    private int correct8 ;
    private int correct9 ;
    private int correct10 ;

    /*Constants for save instance state*/
    private static final String CURRENT_QUESTION = "CURRENT_QUESTION";
    private static final String SCORE = "SCORE";
    private static final String TEXT_ANSWER = "TEXT_ANSWER";
    private static final String MULTIPLE_CHOICE_ANSWERS = "MULTIPLE_CHOICE_ANSWERS";
    private static final String USERNAME = "USERNAME";

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (savedInstanceState != null) {
            currentQuestion = savedInstanceState.getInt(CURRENT_QUESTION, 0);
            userScore = savedInstanceState.getInt(SCORE, 0);
            username = savedInstanceState.getString(USERNAME, "");

            if (currentQuestion == 0) {
                EditText editText = (EditText) findViewById(R.id.edit_text);
                editText.setText(username);
            } else if (currentQuestion >= 1 && currentQuestion <= 3) {
                int multipleChoiceAnswers = savedInstanceState.getInt(MULTIPLE_CHOICE_ANSWERS, 0);
                showCheckBoxQuestion(multipleChoiceAnswers);
            } else if (currentQuestion >= 4 && currentQuestion <= 6) {
                String textAnswer = savedInstanceState.getString(TEXT_ANSWER, "");
                showEditText(textAnswer);
            } else if (currentQuestion >= 7 && currentQuestion <= 10) {
                int multipleChoiceAnswers = savedInstanceState.getInt(MULTIPLE_CHOICE_ANSWERS, 0);
                showRadioButtons(multipleChoiceAnswers);
            }
        }
        getCorrectAnswers();
    }

    /*Show a question with check boxes*/
    private void showCheckBoxQuestion(int checkedAnswers){

        /*Remove previous answers*/
        CheckBox first = (CheckBox) findViewById(R.id.checkbox_1);
        CheckBox second = (CheckBox) findViewById(R.id.checkbox_2);
        CheckBox third = (CheckBox) findViewById(R.id.checkbox_3);
        CheckBox fourth = (CheckBox) findViewById(R.id.checkbox_4);

        first.setChecked(false);
        second.setChecked(false);
        third.setChecked(false);
        fourth.setChecked(false);

        /*Hide ImageView, EditText and RadioButtons*/
        EditText editText = (EditText) findViewById(R.id.edit_text);
        editText.setVisibility(View.GONE);

        ImageView imageView = (ImageView) findViewById(R.id.image);
        imageView.setVisibility(View.GONE);

        LinearLayout radioButtons = (LinearLayout) findViewById(R.id.radio_buttons);
        radioButtons.setVisibility(View.GONE);

        /*Show checkboxes*/
        LinearLayout checkBoxes = (LinearLayout) findViewById(R.id.checkboxes);
        checkBoxes.setVisibility(View.VISIBLE);

        /*Change text of button, because of screen rotation*/
        Button submitButton = (Button) findViewById(R.id.submit_button);
        String buttonText = "SUBMIT ANSWER";
        submitButton.setText(buttonText);

        String questionText,firstOption,secondOption,thirdOption,fourthOption;

        /*Now get right question and answers*/
        switch (currentQuestion) {
            case 1:
                questionText = getResources().getString(R.string.question_1);
                firstOption = getResources().getString(R.string.question_1_checkbox_1);
                secondOption = getResources().getString(R.string.question_1_checkbox_2);
                thirdOption = getResources().getString(R.string.question_1_checkbox_3);
                fourthOption = getResources().getString(R.string.question_1_checkbox_4);
                break;
            case 2:
                questionText = getResources().getString(R.string.question_2);
                firstOption = getResources().getString(R.string.question_2_checkbox_1);
                secondOption = getResources().getString(R.string.question_2_checkbox_2);
                thirdOption = getResources().getString(R.string.question_2_checkbox_3);
                fourthOption = getResources().getString(R.string.question_2_checkbox_4);
                break;
            case 3:
                questionText = getResources().getString(R.string.question_3);
                firstOption = getResources().getString(R.string.question_3_checkbox_1);
                secondOption = getResources().getString(R.string.question_3_checkbox_2);
                thirdOption = getResources().getString(R.string.question_3_checkbox_3);
                fourthOption = getResources().getString(R.string.question_3_checkbox_4);
                break;
            default:
                questionText = "";
                firstOption = "";
                secondOption = "";
                thirdOption = "";
                fourthOption = "";
        }

        /*Show them on screen*/
        TextView questionPrompt = (TextView) findViewById(R.id.question);
        questionPrompt.setText(questionText);

        first.setText(firstOption);
        second.setText(secondOption);
        third.setText(thirdOption);
        fourth.setText(fourthOption);

        /*In case of rotation show already checked answers*/
        if (checkedAnswers != 0){
            int answer;
            int remaining = checkedAnswers;
            while(true){
                answer = remaining % 10;
                remaining = remaining / 10;

                if (answer == 4){
                    fourth.setChecked(true);
                }
                else if (answer == 3){
                    third.setChecked(true);
                }
                else if (answer == 2){
                    second.setChecked(true);
                }
                else if (answer == 1){
                    first.setChecked(true);
                }
                else if (answer == 0){
                    break;
                }
            }
        }
    }

    /*Method to show RadioButton question*/
    private void showRadioButtons(int checkedAnswer){

        /*Hide previous answers*/
        RadioButton first = (RadioButton) findViewById(R.id.radio_button_1);
        RadioButton second = (RadioButton) findViewById(R.id.radio_button_2);
        RadioButton third = (RadioButton) findViewById(R.id.radio_button_3);
        RadioButton fourth = (RadioButton) findViewById(R.id.radio_button_4);

        first.setChecked(false);
        second.setChecked(false);
        third.setChecked(false);
        fourth.setChecked(false);

        /*Hide ImageView,EditText and checkboxes*/
        EditText editText = (EditText) findViewById(R.id.edit_text);
        editText.setVisibility(View.GONE);

        ImageView imageView = (ImageView) findViewById(R.id.image);
        imageView.setVisibility(View.GONE);

        LinearLayout checkBoxes = (LinearLayout) findViewById(R.id.checkboxes);
        checkBoxes.setVisibility(View.GONE);

        /*Show radio buttons*/
        LinearLayout radioButtons = (LinearLayout) findViewById(R.id.radio_buttons);
        radioButtons.setVisibility(View.VISIBLE);

        Button submitButton = (Button) findViewById(R.id.submit_button);
        String buttonText = "SUBMIT ANSWER";
        submitButton.setText(buttonText);

        String questionText,firstOption,secondOption,thirdOption,fourthOption;

        /*Now get right question and answers*/
        switch (currentQuestion) {
            case 7:
                questionText = getResources().getString(R.string.question_7);
                firstOption = getResources().getString(R.string.question_7_radio_button_1);
                secondOption = getResources().getString(R.string.question_7_radio_button_2);
                thirdOption = getResources().getString(R.string.question_7_radio_button_3);
                fourthOption = getResources().getString(R.string.question_7_radio_button_4);
                break;
            case 8:
                questionText = getResources().getString(R.string.question_8);
                firstOption = getResources().getString(R.string.question_8_radio_button_1);
                secondOption = getResources().getString(R.string.question_8_radio_button_2);
                thirdOption = getResources().getString(R.string.question_8_radio_button_3);
                fourthOption = getResources().getString(R.string.question_8_radio_button_4);
                break;
            case 9:
                questionText = getResources().getString(R.string.question_9);
                firstOption = getResources().getString(R.string.question_9_radio_button_1);
                secondOption = getResources().getString(R.string.question_9_radio_button_2);
                thirdOption = getResources().getString(R.string.question_9_radio_button_3);
                fourthOption = getResources().getString(R.string.question_9_radio_button_4);
                break;
            case 10:
                questionText = getResources().getString(R.string.question_10);
                firstOption = getResources().getString(R.string.question_10_radio_button_1);
                secondOption = getResources().getString(R.string.question_10_radio_button_2);
                thirdOption = getResources().getString(R.string.question_10_radio_button_3);
                fourthOption = getResources().getString(R.string.question_10_radio_button_4);
                break;
            default:
                questionText = "";
                firstOption = "";
                secondOption = "";
                thirdOption = "";
                fourthOption = "";
        }

        /*Show them on screen*/
        TextView questionPrompt = (TextView) findViewById(R.id.question);
        questionPrompt.setText(questionText);

        first.setText(firstOption);
        second.setText(secondOption);
        third.setText(thirdOption);
        fourth.setText(fourthOption);

        /*In case of rotation show already checked answers*/
        if (checkedAnswer != 0){
            if (checkedAnswer == 1){
                first.setChecked(true);
            }
            else if (checkedAnswer == 2){
                second.setChecked(true);
            }
            else if (checkedAnswer == 3){
                third.setChecked(true);
            }
            else if (checkedAnswer == 4){
                fourth.setChecked(true);
            }
        }
    }

    /*Method to show EditText questions*/
    private void showEditText(String textAnswer){

        /*Hide CheckBoxes and RadioButtons*/
        LinearLayout checkBoxes = (LinearLayout) findViewById(R.id.checkboxes);
        checkBoxes.setVisibility(View.GONE);

        LinearLayout radioButtons = (LinearLayout) findViewById(R.id.radio_buttons);
        radioButtons.setVisibility(View.GONE);

        /*Show ImageView and EditText*/
        EditText editText = (EditText) findViewById(R.id.edit_text);
        editText.setVisibility(View.VISIBLE);
        editText.setHint("Enter answer here");
        editText.setText("");

        ImageView imageView = (ImageView) findViewById(R.id.image);
        imageView.setVisibility(View.VISIBLE);

        Button submitButton = (Button) findViewById(R.id.submit_button);
        String buttonText = "SUBMIT ANSWER";
        submitButton.setText(buttonText);

        /*Question variables*/
        String questionText;

        /*Now get right question and answers*/
        switch (currentQuestion) {
            case 4:
                questionText = getResources().getString(R.string.pic_question_text);
                imageView.setImageResource(R.drawable.saturn);
                break;
            case 5:
                questionText = getResources().getString(R.string.pic_question_text);
                imageView.setImageResource(R.drawable.pluto);
                break;
            case 6:
                questionText = getResources().getString(R.string.pic_question_text);
                imageView.setImageResource(R.drawable.mars);
                break;
            default:
                questionText = "";
        }

        /*Show on screen*/
        TextView questionPrompt = (TextView) findViewById(R.id.question);
        questionPrompt.setText(questionText);

        /*Answer before screen got rotated*/
        if (!textAnswer.equals("")){
            editText.setText(textAnswer);
        }
    }

    /*Method to show next question*/
    public void showNextQuestion(View view){

        if (currentQuestion == 10){
            /*Hide everything except last two buttons*/
            LinearLayout checkBoxes = (LinearLayout) findViewById(R.id.checkboxes);
            checkBoxes.setVisibility(View.GONE);

            LinearLayout radioButtons = (LinearLayout) findViewById(R.id.radio_buttons);
            radioButtons.setVisibility(View.GONE);

            EditText editText = (EditText) findViewById(R.id.edit_text);
            editText.setVisibility(View.GONE);

            TextView question = (TextView) findViewById(R.id.question);
            question.setVisibility(View.GONE);

            Button showAnswers = (Button) findViewById(R.id.show_answers);
            showAnswers.setVisibility(View.VISIBLE);

            Button restartQuiz = (Button) findViewById(R.id.back_to_start);
            restartQuiz.setVisibility(View.VISIBLE);

            Button submit = (Button) findViewById(R.id.submit_button);
            submit.setVisibility(View.GONE);

            Toast.makeText(this, "Congratulations " + username + ", you got " + userScore
                    + "/10 correct.", Toast.LENGTH_LONG).show();
            return;
        }

        if (currentQuestion == 0){
            /*Change text of button*/
            Button submitButton = (Button) findViewById(R.id.submit_button);
            submitButton.setText(getResources().getString(R.string.submit));

            EditText nameEditText = (EditText) findViewById(R.id.edit_text);
            username = nameEditText.getText().toString();
        }
        else {
            getAnswer();
        }

        currentQuestion += 1;

        if (currentQuestion <= 3) {
            showCheckBoxQuestion(0);
        } else if (currentQuestion <= 6) {
            showEditText("");
        } else if (currentQuestion <= 10) {
            showRadioButtons(0);
        }

    }

    /*get user answers*/
    private void getAnswer(){
        /*Checkbox question*/
        if (currentQuestion <= 3){
            CheckBox checkBox1 = (CheckBox) findViewById(R.id.checkbox_1);
            CheckBox checkBox2 = (CheckBox) findViewById(R.id.checkbox_2);
            CheckBox checkBox3 = (CheckBox) findViewById(R.id.checkbox_3);
            CheckBox checkBox4 = (CheckBox) findViewById(R.id.checkbox_4);

            int answer = 0;

            /*get checkbox values*/
            if (checkBox1.isChecked()){
                answer = 1;
            }

            if (checkBox2.isChecked()){
                answer = answer*10 + 2;
            }

            if (checkBox3.isChecked()){
                answer = answer*10 + 3;
            }

            if (checkBox4.isChecked()){
                answer = answer*10 + 4;
            }

            /*assign to right answer*/
            if (currentQuestion == 1){
                if (answer == correct1){
                    userScore += 1;
                }
            }
            else if (currentQuestion == 2){
                if (answer == correct2){
                    userScore += 1;
                }
            }
            else {
                if (answer == correct3){
                    userScore += 1;
                }
            }
        }
        else if (currentQuestion <= 6){
            EditText editText = (EditText) findViewById(R.id.edit_text);

            String answer;
            answer = editText.getText().toString();

            if (currentQuestion == 4){
                if (correct4.equals(answer.trim().toLowerCase())){
                    userScore += 1;
                }
            }
            else if(currentQuestion == 5){
                if (correct5.equals(answer.trim().toLowerCase())){
                    userScore += 1;
                }
            }
            else{
                if (correct6.equals(answer.trim().toLowerCase())){
                    userScore += 1;
                }
            }
        }
        else if(currentQuestion <= 10){
            RadioButton radio1 = (RadioButton) findViewById(R.id.radio_button_1);
            RadioButton radio2 = (RadioButton) findViewById(R.id.radio_button_2);
            RadioButton radio3 = (RadioButton) findViewById(R.id.radio_button_3);
            RadioButton radio4 = (RadioButton) findViewById(R.id.radio_button_4);

            int answer = 0;

            if (radio1.isChecked()){
                answer = 1;
            }
            else if(radio2.isChecked()){
                answer = 2;
            }
            else if(radio3.isChecked()){
                answer = 3;
            }
            else if(radio4.isChecked()){
                answer = 4;
            }

            if (currentQuestion == 7){
                if (answer == correct7){
                    userScore += 1;
                }
            }
            else if(currentQuestion == 8){
                if (answer == correct8){
                    userScore += 1;
                }
            }
            else if(currentQuestion == 9){
                if (answer == correct9){
                    userScore += 1;
                }
            }
            else if(currentQuestion == 10){
                if (answer == correct10){
                    userScore += 1;
                }
            }
        }

    }

    /*Get correct answers*/
    private void getCorrectAnswers(){
        correct1 = Integer.parseInt(getResources().getString(R.string.answer_to_question_1));
        correct2 = Integer.parseInt(getResources().getString(R.string.answer_to_question_2));
        correct3 = Integer.parseInt(getResources().getString(R.string.answer_to_question_3));
        correct4 = getResources().getString(R.string.answer_to_question_4);
        correct5 = getResources().getString(R.string.answer_to_question_5);
        correct6 = getResources().getString(R.string.answer_to_question_6);
        correct7 = Integer.parseInt(getResources().getString(R.string.answer_to_question_7));
        correct8 = Integer.parseInt(getResources().getString(R.string.answer_to_question_8));
        correct9 = Integer.parseInt(getResources().getString(R.string.answer_to_question_9));
        correct10 = Integer.parseInt(getResources().getString(R.string.answer_to_question_10));
    }

    /*For screen rotation*/
    @Override
    public void onSaveInstanceState(Bundle save){
        save.putInt(CURRENT_QUESTION,currentQuestion);
        save.putInt(SCORE,userScore);
        save.putString(USERNAME,username);

        EditText editText = (EditText) findViewById(R.id.edit_text);

        if (currentQuestion == 0) {
            save.putString(USERNAME, editText.getText().toString());
        }

        /*Check which questions is visible*/
        if (currentQuestion >= 4 && currentQuestion <= 6){
            save.putString(TEXT_ANSWER,editText.getText().toString());
        }
        else if (currentQuestion != 0){
            if (currentQuestion >= 1 && currentQuestion <= 3){
                CheckBox checkBox1 = (CheckBox) findViewById(R.id.checkbox_1);
                CheckBox checkBox2 = (CheckBox) findViewById(R.id.checkbox_2);
                CheckBox checkBox3 = (CheckBox) findViewById(R.id.checkbox_3);
                CheckBox checkBox4 = (CheckBox) findViewById(R.id.checkbox_4);

                int answer = 0;

                if (checkBox1.isChecked()){
                    answer = 1;
                }

                if (checkBox2.isChecked()){
                    answer = answer * 10 + 2;
                }

                if (checkBox3.isChecked()){
                    answer = answer * 10 + 3;
                }

                if (checkBox4.isChecked()){
                    answer = answer * 10 + 4;
                }
                save.putInt(MULTIPLE_CHOICE_ANSWERS,answer);
            }
            else{
                RadioButton radio1 = (RadioButton) findViewById(R.id.radio_button_1);
                RadioButton radio2 = (RadioButton) findViewById(R.id.radio_button_2);
                RadioButton radio3 = (RadioButton) findViewById(R.id.radio_button_3);
                RadioButton radio4 = (RadioButton) findViewById(R.id.radio_button_4);

                int answer = 0;

                if (radio1.isChecked()){
                    answer = 1;
                }
                else if (radio2.isChecked()){
                    answer = 2;
                }
                else if(radio3.isChecked()){
                    answer = 3;
                }
                else if (radio4.isChecked()){
                    answer = 4;
                }
                save.putInt(MULTIPLE_CHOICE_ANSWERS,answer);
            }

        }
    }

    public void showAnswers(View view){
        LinearLayout answers = (LinearLayout) findViewById(R.id.answers);
        answers.setVisibility(View.VISIBLE);

    }

    public void restartQuiz(View view){
        Intent i = getBaseContext().getPackageManager()
                .getLaunchIntentForPackage( getBaseContext().getPackageName() );
        i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(i);
    }

}