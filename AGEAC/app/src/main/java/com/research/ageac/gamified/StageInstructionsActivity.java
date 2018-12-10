package com.research.ageac.gamified;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.research.ageac.gamified.quizlibrary.PrerequisitesBaseClass;

import java.text.DecimalFormat;

public class StageInstructionsActivity extends AppCompatActivity {

    String topic;
    int level;
    int totalAttempts, failedAttempts, questionsAttempts, questionsWrongAnswered;
    int stage, maxStages;
    int coins, totalExtraLives, totalBronzeTimers, totalSilverTimers, totalGoldTimers, totalTimers;
    int xpLevel, xpPoints, xpLevelPrevTarget, xpLevelNextTarget;
    int bronzeTimersUsed, silverTimersUsed, goldTimersUsed, extraLivesUsed;

    TextView attemptsNScore, levelNStageNameTxtv, topicNameTxtv, coinsTxtv, timersTxtv, extraLivesTxtv, prerequisitesTxtv, quizInstructionsTxtv;
    Button btnStartQuiz;
    ImageView backBtn;

    DBAdapter dbAdapter;
    StageAttemptsDTO stageAttemptsDTO;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        final int flags = View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                | View.SYSTEM_UI_FLAG_FULLSCREEN
                | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY;
        getWindow().getDecorView().setSystemUiVisibility(flags);
        setContentView(R.layout.activity_stage_instructions);

        final View decorView = getWindow().getDecorView();
        decorView.setOnSystemUiVisibilityChangeListener(new View.OnSystemUiVisibilityChangeListener() {

            @Override
            public void onSystemUiVisibilityChange(int visibility) {
                if ((visibility & View.SYSTEM_UI_FLAG_FULLSCREEN) == 0) {
                    decorView.setSystemUiVisibility(flags);
                }
            }
        });

        DecimalFormat df = new DecimalFormat("#.00");

        dbAdapter = new DBAdapter(StageInstructionsActivity.this);
        dbAdapter.addLogEntry("Instructions","OnCreate");

        coins = Integer.parseInt(getIntent().getExtras().getString(BackUpDBKeys.BACKUPDB_KEY_COINS));
        extraLivesUsed = Integer.parseInt(getIntent().getExtras().getString(BackUpDBKeys.BACKUPDB_KEY_EXTRA_LIVES_USED));
        totalExtraLives = Integer.parseInt(getIntent().getExtras().getString("total_extra_lives"));
        bronzeTimersUsed = Integer.parseInt(getIntent().getExtras().getString(BackUpDBKeys.BACKUPDB_KEY_BRONZE_TIMERS_USED));
        silverTimersUsed = Integer.parseInt(getIntent().getExtras().getString(BackUpDBKeys.BACKUPDB_KEY_SILVER_TIMERS_USED));
        goldTimersUsed = Integer.parseInt(getIntent().getExtras().getString(BackUpDBKeys.BACKUPDB_KEY_GOLD_TIMERS_USED));
        totalBronzeTimers = Integer.parseInt(getIntent().getExtras().getString("total_bronze_timers"));
        totalSilverTimers = Integer.parseInt(getIntent().getExtras().getString("total_silver_timers"));
        totalGoldTimers = Integer.parseInt(getIntent().getExtras().getString("total_gold_timers"));
        totalTimers = Integer.parseInt(getIntent().getExtras().getString("total_timers"));
        xpLevel = Integer.parseInt(getIntent().getExtras().getString(BackUpDBKeys.BACKUPDB_KEY_XP_LEVEL));
        xpPoints = Integer.parseInt(getIntent().getExtras().getString(BackUpDBKeys.BACKUPDB_KEY_XP_TOTAL_POINTS));
        xpLevelPrevTarget = Integer.parseInt(getIntent().getExtras().getString(BackUpDBKeys.BACKUPDB_KEY_XP_LEVEL_PREV_TARGET));
        xpLevelNextTarget = Integer.parseInt(getIntent().getExtras().getString(BackUpDBKeys.BACKUPDB_KEY_XP_LEVEL_NEXT_TARGET));

        topic = getIntent().getExtras().getString("topic");
        level = getIntent().getExtras().getInt("level");
        stage = getIntent().getExtras().getInt("stage");
        maxStages = getIntent().getExtras().getInt("maxStages");

        stageAttemptsDTO = dbAdapter.getStageAttempts(topic, level, stage);

        coinsTxtv = findViewById(R.id.txtv_stage_instructions_coins);
        timersTxtv = findViewById(R.id.txtv_stage_instructions_timers);
        extraLivesTxtv = findViewById(R.id.txtv_stage_instructions_extra_lives);

        topicNameTxtv = findViewById(R.id.txtv_stage_instructions_topic_name);
        levelNStageNameTxtv = findViewById(R.id.txtv_stage_instructions_level_n_stage_name);
        prerequisitesTxtv = findViewById(R.id.prerequisites_txtv);
        String quizName = "com.research.ageac.gamified.quizlibrary." + topic + ".level" + level + ".Stage" + stage;
        PrerequisitesBaseClass quiz = null;
        try{
            quiz = (PrerequisitesBaseClass) Class.forName(quizName).newInstance();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        }
        prerequisitesTxtv.setText(quiz.getPrerequisites());

        coinsTxtv.setText(String.valueOf(coins));
        timersTxtv.setText(String.valueOf(totalTimers));
        extraLivesTxtv.setText(String.valueOf(totalExtraLives));

        setTopicText();
        levelNStageNameTxtv.setText("L-" + String.valueOf(level) + "/S-" + String.valueOf(stage));

        attemptsNScore = findViewById(R.id.statistics_score_attempts);

        totalAttempts = stageAttemptsDTO.getTotalAttempts();
        failedAttempts = stageAttemptsDTO.getFailedAttempts();
        questionsAttempts = stageAttemptsDTO.getQuestionsAttempted();
        questionsWrongAnswered = stageAttemptsDTO.getQuestionsAnsweredWrong();

        double accuracyRate = 0;
        if (questionsAttempts != 0)
            accuracyRate = ((questionsAttempts - questionsWrongAnswered) * 100) / (double) questionsAttempts;
        attemptsNScore.setText("Total Attempts : " + totalAttempts + "\nFailed Attempts : " + failedAttempts + "\nAccuracy Rate : " + df.format(accuracyRate) + " %");

        quizInstructionsTxtv = findViewById(R.id.quiz_instructions);

        String instruction = null;
        if(level == 1)
        {
            instruction = "Quiz Type - True/False(Similar)\nTime - 3 minutes\n\n1. 2 Coins per correct answer\n2. Bronze Timers Available\n3. Bonus Coins - 5/Attempts";
        }
        else if(level == 2)
        {
            instruction = "Quiz Type - Multiple Choice\nTime - 8 minutes\n\n1. 5 Coins per correct answer\n2. Bronze and Silver Timers Available\n3. Bonus Coins - 10/Attempts";
        }
        else if(level == 3)
        {
            instruction = "Quiz Type - Multiple Choice\nTime - 15 minutes\n\n1. 10 Coins per correct answer\n2. All Timers Available\n3. Bonus Coins - 20/Attempts";
        }
        quizInstructionsTxtv.setText(instruction);

        backBtn = findViewById(R.id.stage_instructions_back_btn);
        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        btnStartQuiz = (Button) findViewById(R.id.btn_start_quiz);
        btnStartQuiz.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent startQuizIntent;
                if (level == 1) {
                    startQuizIntent = new Intent(StageInstructionsActivity.this, TrueFalseQuizActivity.class);
                } else {
                    startQuizIntent = new Intent(StageInstructionsActivity.this, SingleChoiceQuizActivity.class);
                }
                startQuizIntent.putExtra("topic", topic);
                startQuizIntent.putExtra("level", level);
                startQuizIntent.putExtra("stage", stage);
                startQuizIntent.putExtra("attempts", stageAttemptsDTO.getTotalAttempts());
                startQuizIntent.putExtra("attemptsFailed", stageAttemptsDTO.getFailedAttempts());
                startQuizIntent.putExtra("maxStages", maxStages);
                startQuizIntent.putExtra(BackUpDBKeys.BACKUPDB_KEY_COINS, String.valueOf(coins));
                startQuizIntent.putExtra(BackUpDBKeys.BACKUPDB_KEY_EXTRA_LIVES_USED, String.valueOf(extraLivesUsed));
                startQuizIntent.putExtra("total_extra_lives", String.valueOf(totalExtraLives));
                startQuizIntent.putExtra(BackUpDBKeys.BACKUPDB_KEY_BRONZE_TIMERS_USED, String.valueOf(bronzeTimersUsed));
                startQuizIntent.putExtra(BackUpDBKeys.BACKUPDB_KEY_SILVER_TIMERS_USED, String.valueOf(silverTimersUsed));
                startQuizIntent.putExtra(BackUpDBKeys.BACKUPDB_KEY_GOLD_TIMERS_USED, String.valueOf(goldTimersUsed));
                startQuizIntent.putExtra("total_bronze_timers", String.valueOf(totalBronzeTimers));
                startQuizIntent.putExtra("total_silver_timers", String.valueOf(totalSilverTimers));
                startQuizIntent.putExtra("total_gold_timers", String.valueOf(totalGoldTimers));
                startQuizIntent.putExtra("total_timers", String.valueOf(totalTimers));
                startQuizIntent.putExtra(BackUpDBKeys.BACKUPDB_KEY_XP_LEVEL, String.valueOf(xpLevel));
                startQuizIntent.putExtra(BackUpDBKeys.BACKUPDB_KEY_XP_TOTAL_POINTS, String.valueOf(xpPoints));
                startQuizIntent.putExtra(BackUpDBKeys.BACKUPDB_KEY_XP_LEVEL_PREV_TARGET, String.valueOf(xpLevelPrevTarget));
                startQuizIntent.putExtra(BackUpDBKeys.BACKUPDB_KEY_XP_LEVEL_NEXT_TARGET, String.valueOf(xpLevelNextTarget));
                startActivityForResult(startQuizIntent, 111);
            }
        });
    }

    private void setTopicText() {
        if (topic.equalsIgnoreCase("array"))
            topicNameTxtv.setText("Array");
        if (topic.equalsIgnoreCase("linkedlist"))
            topicNameTxtv.setText("Linked List");
        if (topic.equalsIgnoreCase("stack"))
            topicNameTxtv.setText("Stack");
        if (topic.equalsIgnoreCase("queue"))
            topicNameTxtv.setText("Queue");
        if (topic.equalsIgnoreCase("tree"))
            topicNameTxtv.setText("Tree");
        if (topic.equalsIgnoreCase("bst"))
            topicNameTxtv.setText("BST");
        if (topic.equalsIgnoreCase("heap"))
            topicNameTxtv.setText("Heap");
        if (topic.equalsIgnoreCase("graph"))
            topicNameTxtv.setText("Graph");
        if (topic.equalsIgnoreCase("hashing"))
            topicNameTxtv.setText("Hashing");
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        coins = data.getIntExtra(BackUpDBKeys.BACKUPDB_KEY_COINS, 0);
        extraLivesUsed = data.getIntExtra(BackUpDBKeys.BACKUPDB_KEY_EXTRA_LIVES_USED, 0);
        totalExtraLives = data.getIntExtra("total_extra_lives", 0);
        bronzeTimersUsed = data.getIntExtra(BackUpDBKeys.BACKUPDB_KEY_BRONZE_TIMERS_USED, 0);
        silverTimersUsed = data.getIntExtra(BackUpDBKeys.BACKUPDB_KEY_SILVER_TIMERS_USED, 0);
        goldTimersUsed = data.getIntExtra(BackUpDBKeys.BACKUPDB_KEY_GOLD_TIMERS_USED, 0);
        totalBronzeTimers = data.getIntExtra("total_bronze_timers", 0);
        totalSilverTimers = data.getIntExtra("total_silver_timers", 0);
        totalGoldTimers = data.getIntExtra("total_gold_timers", 0);
        totalTimers = data.getIntExtra("total_timers", 0);
        xpLevel = data.getIntExtra(BackUpDBKeys.BACKUPDB_KEY_XP_LEVEL, 0);
        xpPoints = data.getIntExtra(BackUpDBKeys.BACKUPDB_KEY_XP_TOTAL_POINTS, 0);
        xpLevelPrevTarget = data.getIntExtra(BackUpDBKeys.BACKUPDB_KEY_XP_LEVEL_PREV_TARGET, 0);
        xpLevelNextTarget = data.getIntExtra(BackUpDBKeys.BACKUPDB_KEY_XP_LEVEL_NEXT_TARGET, 0);

        if (requestCode == 111) {
            Intent backIntent = new Intent();
            backIntent.putExtra(BackUpDBKeys.BACKUPDB_KEY_COINS, coins);
            backIntent.putExtra(BackUpDBKeys.BACKUPDB_KEY_EXTRA_LIVES_USED, extraLivesUsed);
            backIntent.putExtra("total_extra_lives", totalExtraLives);
            backIntent.putExtra(BackUpDBKeys.BACKUPDB_KEY_BRONZE_TIMERS_USED, bronzeTimersUsed);
            backIntent.putExtra(BackUpDBKeys.BACKUPDB_KEY_SILVER_TIMERS_USED, silverTimersUsed);
            backIntent.putExtra(BackUpDBKeys.BACKUPDB_KEY_GOLD_TIMERS_USED, goldTimersUsed);
            backIntent.putExtra("total_bronze_timers", totalBronzeTimers);
            backIntent.putExtra("total_silver_timers", totalSilverTimers);
            backIntent.putExtra("total_gold_timers", totalGoldTimers);
            backIntent.putExtra("total_timers", totalTimers);
            backIntent.putExtra(BackUpDBKeys.BACKUPDB_KEY_XP_LEVEL, xpLevel);
            backIntent.putExtra(BackUpDBKeys.BACKUPDB_KEY_XP_TOTAL_POINTS, xpPoints);
            backIntent.putExtra(BackUpDBKeys.BACKUPDB_KEY_XP_LEVEL_PREV_TARGET, xpLevelPrevTarget);
            backIntent.putExtra(BackUpDBKeys.BACKUPDB_KEY_XP_LEVEL_NEXT_TARGET, xpLevelNextTarget);
            setResult(RESULT_OK, backIntent);
            finish();
        }
    }

    @Override
    public void onBackPressed() {
        Intent backIntent = new Intent();
        backIntent.putExtra(BackUpDBKeys.BACKUPDB_KEY_COINS, coins);
        backIntent.putExtra(BackUpDBKeys.BACKUPDB_KEY_EXTRA_LIVES_USED, extraLivesUsed);
        backIntent.putExtra("total_extra_lives", totalExtraLives);
        backIntent.putExtra(BackUpDBKeys.BACKUPDB_KEY_BRONZE_TIMERS_USED, bronzeTimersUsed);
        backIntent.putExtra(BackUpDBKeys.BACKUPDB_KEY_SILVER_TIMERS_USED, silverTimersUsed);
        backIntent.putExtra(BackUpDBKeys.BACKUPDB_KEY_GOLD_TIMERS_USED, goldTimersUsed);
        backIntent.putExtra("total_bronze_timers", totalBronzeTimers);
        backIntent.putExtra("total_silver_timers", totalSilverTimers);
        backIntent.putExtra("total_gold_timers", totalGoldTimers);
        backIntent.putExtra("total_timers", totalTimers);
        backIntent.putExtra(BackUpDBKeys.BACKUPDB_KEY_XP_LEVEL, xpLevel);
        backIntent.putExtra(BackUpDBKeys.BACKUPDB_KEY_XP_TOTAL_POINTS, xpPoints);
        backIntent.putExtra(BackUpDBKeys.BACKUPDB_KEY_XP_LEVEL_PREV_TARGET, xpLevelPrevTarget);
        backIntent.putExtra(BackUpDBKeys.BACKUPDB_KEY_XP_LEVEL_NEXT_TARGET, xpLevelNextTarget);
        setResult(RESULT_OK, backIntent);
        finish();
    }

    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        if (hasFocus) {
            getWindow().getDecorView().setSystemUiVisibility(
                    View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                            | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                            | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                            | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                            | View.SYSTEM_UI_FLAG_FULLSCREEN
                            | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY);
        }
    }
}
