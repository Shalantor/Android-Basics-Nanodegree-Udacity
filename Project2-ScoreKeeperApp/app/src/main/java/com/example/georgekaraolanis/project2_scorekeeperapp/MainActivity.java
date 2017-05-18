package com.example.georgekaraolanis.project2_scorekeeperapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private int goalsTeamA = 0;
    private int behindsTeamA = 0;
    private int scoreTeamA = 0;
    private int goalsTeamB = 0;
    private int behindsTeamB = 0;
    private int scoreTeamB = 0;
    private static final int POINTS_FOR_GOAL = 6;
    private static final int POINTS_FOR_BEHIND = 1;

    /**Variables for undo button*/
    private int typeOfLastChange = 0;
    private int teamThatMadeLastChange = 0;

    /**Strings for bundle*/
    private static final String SCORE_TEAM_A = "scoreTeamA";
    private static final String GOALS_TEAM_A = "goalsTeamA";
    private static final String BEHINDS_TEAM_A = "behindsTeamA";
    private static final String SCORE_TEAM_B = "scoreTeamB";
    private static final String GOALS_TEAM_B = "goalsTeamB";
    private static final String BEHINDS_TEAM_B = "behindsTeamB";
    private static final String TEAM_LAST_CHANGE = "teamThatMadeLastChange";
    private static final String TYPE_LAST_CHANGE = "typeOfLastChange";


    @Override
    public void onSaveInstanceState(Bundle save){

        /**Store values*/
        save.putInt(SCORE_TEAM_A,scoreTeamA);
        save.putInt(GOALS_TEAM_A,goalsTeamA);
        save.putInt(BEHINDS_TEAM_A,behindsTeamA);
        save.putInt(SCORE_TEAM_B,scoreTeamB);
        save.putInt(GOALS_TEAM_B,goalsTeamB);
        save.putInt(BEHINDS_TEAM_B,behindsTeamB);
        save.putInt(TYPE_LAST_CHANGE,typeOfLastChange);
        save.putInt(TEAM_LAST_CHANGE,teamThatMadeLastChange);

        super.onSaveInstanceState(save);
    }

    @Override
    public void onRestoreInstanceState(Bundle save) {

        /**Restore values*/
        scoreTeamA = save.getInt(SCORE_TEAM_A);
        goalsTeamA = save.getInt(GOALS_TEAM_A);
        behindsTeamA = save.getInt(BEHINDS_TEAM_A);
        scoreTeamB = save.getInt(SCORE_TEAM_B);
        goalsTeamB = save.getInt(GOALS_TEAM_B);
        behindsTeamB = save.getInt(BEHINDS_TEAM_B);
        typeOfLastChange = save.getInt(TYPE_LAST_CHANGE);
        teamThatMadeLastChange = save.getInt(TEAM_LAST_CHANGE);

        /**Get references to TextViews*/
        TextView teamATextView = (TextView) findViewById(R.id.team_A_text_view);
        TextView teamBTextView = (TextView) findViewById(R.id.team_B_text_view);

        /**Set texts in those TextViews*/
        teamATextView.setText(goalsTeamA + "." + behindsTeamA + "(" + scoreTeamA + ")");
        teamBTextView.setText(goalsTeamB + "." + behindsTeamB + "(" + scoreTeamB + ")");

    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void scoreGoalTeamA(View view){

        /**Get TextView reference*/
        TextView teamATextView = (TextView) findViewById(R.id.team_A_text_view);

        /**Update score and goals*/
        goalsTeamA += 1;
        scoreTeamA += POINTS_FOR_GOAL;

        /**Show updated values*/
        teamATextView.setText(goalsTeamA + "." + behindsTeamA + "(" + scoreTeamA + ")");

        /**Undo conditions*/
        typeOfLastChange = 1;
        teamThatMadeLastChange = 1;

    }


    public void scoreGoalTeamB(View view){

        /**Get TextView reference*/
        TextView teamBTextView = (TextView) findViewById(R.id.team_B_text_view);

        /**Update score and goals*/
        goalsTeamB += 1;
        scoreTeamB += POINTS_FOR_GOAL;

        /**Show updated values*/
        teamBTextView.setText(goalsTeamB + "." + behindsTeamB + "(" + scoreTeamB + ")");

        /**Undo conditions*/
        typeOfLastChange = 1;
        teamThatMadeLastChange = 2;

    }


    public void scoreBehindTeamA(View view){

        /**Get TextView reference*/
        TextView teamATextView = (TextView) findViewById(R.id.team_A_text_view);

        /**Update score and behinds*/
        behindsTeamA += 1;
        scoreTeamA += POINTS_FOR_BEHIND;

        /**Show updated values*/
        teamATextView.setText(goalsTeamA + "." + behindsTeamA + "(" + scoreTeamA + ")");

        /**Undo conditions*/
        typeOfLastChange = 2;
        teamThatMadeLastChange = 1;

    }


    public void scoreBehindTeamB(View view){

        /**Get TextView reference*/
        TextView teamBTextView = (TextView) findViewById(R.id.team_B_text_view);

        /**Update score and behinds*/
        behindsTeamB += 1;
        scoreTeamB += POINTS_FOR_BEHIND;

        /**Show updated values*/
        teamBTextView.setText(goalsTeamB + "." + behindsTeamB + "(" + scoreTeamB + ")");

        /**Undo conditions*/
        typeOfLastChange = 2;
        teamThatMadeLastChange = 2;

    }


    public void resetScores(View view){

        /**Reset scores, number of goals and behinds*/
        goalsTeamA = 0;
        behindsTeamA = 0;
        scoreTeamA = 0;
        goalsTeamB = 0;
        behindsTeamB = 0;
        scoreTeamB = 0;

        /**Get TextView references*/
        TextView teamATextView = (TextView) findViewById(R.id.team_A_text_view);
        TextView teamBTextView = (TextView) findViewById(R.id.team_B_text_view);

        /**Set scores in TextViews*/
        teamATextView.setText(goalsTeamA + "." + behindsTeamA + "(" + scoreTeamA + ")");
        teamBTextView.setText(goalsTeamB + "." + behindsTeamB + "(" + scoreTeamB + ")");

        /**Also reset undo conditions*/
        typeOfLastChange = 0;

    }

    /**The method below undos only the very last change that took place.
     * It doesn't work for changes made before the last one.*/
    public void undoLastChange(View view){

        /**Do nothing if there was no change*/
        if (typeOfLastChange == 0){
            return;
        }

        if (teamThatMadeLastChange == 1){
            if(typeOfLastChange == 1) {
                goalsTeamA -= 1;
                scoreTeamA -= POINTS_FOR_GOAL;
            }
            else{
                behindsTeamA -=1;
                scoreTeamA -= POINTS_FOR_BEHIND;
            }
            /**Set textview*/
            TextView teamATextView = (TextView) findViewById(R.id.team_A_text_view);
            teamATextView.setText(goalsTeamA + "." + behindsTeamA + "(" + scoreTeamA + ")");
        }
        else{
            if(typeOfLastChange == 1) {
                goalsTeamB -= 1;
                scoreTeamB -= POINTS_FOR_GOAL;
            }
            else{
                behindsTeamB -=1;
                scoreTeamB -= POINTS_FOR_BEHIND;
            }
            /**Set textview*/
            TextView teamBTextView = (TextView) findViewById(R.id.team_B_text_view);
            teamBTextView.setText(goalsTeamB + "." + behindsTeamB + "(" + scoreTeamB + ")");
        }

        typeOfLastChange = 0;

    }
}
