package com.research.ageac.gamified;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class MainActivity extends AppCompatActivity {

    View btnStartGame, btnAssignments, btnLeaderboard, btnBeginnersLeaderboard, viewShop, viewGenerator, viewProgress;
    ProgressBar xpLevelPercentageProgressBar;
    TextView coinsTxtv, timersTxtv, extraLivesTxtv, xpLevelTxtv, xpLevelProgressTxtv;
    int coins, totalExtraLives, totalBronzeTimers, totalSilverTimers, totalGoldTimers, totalTimers;
    int xpLevel, xpPoints, xpLevelPrevTarget, xpLevelNextTarget, xpLevelProgressPercentage;
    int bronzeTimersBought, bronzeTimersCollected, bronzeTimersUsed;
    int silverTimersBought, silverTimersCollected, silverTimersUsed;
    int goldTimersBought, goldTimersCollected, goldTimersUsed;
    int extraLivesBought, extraLivesCollected, extraLivesUsed;
    int daysStreak;

    DBAdapter dbAdapter;
    SharedPreferences sharedPreferences;

    private Boolean exit = false;

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
        setContentView(R.layout.activity_main);

        final View decorView = getWindow().getDecorView();
        decorView.setOnSystemUiVisibilityChangeListener(new View.OnSystemUiVisibilityChangeListener() {

            @Override
            public void onSystemUiVisibilityChange(int visibility) {
                if ((visibility & View.SYSTEM_UI_FLAG_FULLSCREEN) == 0) {
                    decorView.setSystemUiVisibility(flags);
                }
            }
        });

        coinsTxtv = findViewById(R.id.txtv_coins_earned);
        extraLivesTxtv = findViewById(R.id.txtv_main_extra_lives);
        xpLevelTxtv = findViewById(R.id.txtv_main_xp_level);
        xpLevelProgressTxtv = findViewById(R.id.txtv_main_xp_level_percentage);
        timersTxtv = findViewById(R.id.txtv_main_timers);

        xpLevelPercentageProgressBar = findViewById(R.id.prgbar_main_xp_level);

        dbAdapter = new DBAdapter(this);
        dbAdapter.addLogEntry("Main", "OnCreate");

        sharedPreferences = getSharedPreferences("Extras", MODE_PRIVATE);
        boolean containKey = sharedPreferences.contains("exists");
        if (!containKey) {

            Log.d("Values Used from - ", "Backup");

            BackUpValuesDTO backUpValuesDTO = dbAdapter.getBackUpData();

            SharedPreferences.Editor editor = sharedPreferences.edit();

            editor.putBoolean("exists", true);

            coins = backUpValuesDTO.getCoins();
            editor.putInt(BackUpDBKeys.BACKUPDB_KEY_COINS, coins);

            bronzeTimersBought = backUpValuesDTO.getBronzeTimersBought();
            editor.putInt(BackUpDBKeys.BACKUPDB_KEY_BRONZE_TIMERS_BOUGHT, bronzeTimersBought);
            bronzeTimersCollected = backUpValuesDTO.getBronzeTimersCollected();
            editor.putInt(BackUpDBKeys.BACKUPDB_KEY_BRONZE_TIMERS_COLLECTED, bronzeTimersCollected);
            bronzeTimersUsed = backUpValuesDTO.getBronzeTimersUsed();
            editor.putInt(BackUpDBKeys.BACKUPDB_KEY_BRONZE_TIMERS_USED, bronzeTimersUsed);

            silverTimersBought = backUpValuesDTO.getSilverTimersBought();
            editor.putInt(BackUpDBKeys.BACKUPDB_KEY_SILVER_TIMERS_BOUGHT, silverTimersBought);
            silverTimersCollected = backUpValuesDTO.getSilverTimersCollected();
            editor.putInt(BackUpDBKeys.BACKUPDB_KEY_SILVER_TIMERS_COLLECTED, silverTimersCollected);
            silverTimersUsed = backUpValuesDTO.getSilverTimersUsed();
            editor.putInt(BackUpDBKeys.BACKUPDB_KEY_SILVER_TIMERS_USED, silverTimersUsed);

            goldTimersBought = backUpValuesDTO.getGoldTimersBought();
            editor.putInt(BackUpDBKeys.BACKUPDB_KEY_GOLD_TIMERS_BOUGHT, goldTimersBought);
            goldTimersCollected = backUpValuesDTO.getGoldTimersCollected();
            editor.putInt(BackUpDBKeys.BACKUPDB_KEY_GOLD_TIMERS_COLLECTED, goldTimersCollected);
            goldTimersUsed = backUpValuesDTO.getGoldTimersUsed();
            editor.putInt(BackUpDBKeys.BACKUPDB_KEY_GOLD_TIMERS_USED, goldTimersUsed);

            extraLivesBought = backUpValuesDTO.getExtraLivesBought();
            editor.putInt(BackUpDBKeys.BACKUPDB_KEY_EXTRA_LIVES_BOUGHT, extraLivesBought);
            extraLivesCollected = backUpValuesDTO.getExtraLivesCollected();
            editor.putInt(BackUpDBKeys.BACKUPDB_KEY_EXTRA_LIVES_COLLECTED, extraLivesCollected);
            extraLivesUsed = backUpValuesDTO.getExtraLivesUsed();
            editor.putInt(BackUpDBKeys.BACKUPDB_KEY_EXTRA_LIVES_USED, extraLivesUsed);

            xpLevel = backUpValuesDTO.getXpLevel();
            editor.putInt(BackUpDBKeys.BACKUPDB_KEY_XP_LEVEL, xpLevel);
            xpPoints = backUpValuesDTO.getXpTotalPoints();
            editor.putInt(BackUpDBKeys.BACKUPDB_KEY_XP_TOTAL_POINTS, xpPoints);
            xpLevelPrevTarget = backUpValuesDTO.getXpLevelPrevTarget();
            editor.putInt(BackUpDBKeys.BACKUPDB_KEY_XP_LEVEL_PREV_TARGET, xpLevelPrevTarget);
            xpLevelNextTarget = backUpValuesDTO.getXpLevelNextTarget();
            editor.putInt(BackUpDBKeys.BACKUPDB_KEY_XP_LEVEL_NEXT_TARGET, xpLevelNextTarget);

            editor.putInt(BackUpDBKeys.BACKUPDB_KEY_TIMERS_TARGET, backUpValuesDTO.getTimersTarget());
            editor.putInt(BackUpDBKeys.BACKUPDB_KEY_TIMERS_REWARD, backUpValuesDTO.getTimersReward());

            editor.putInt(BackUpDBKeys.BACKUPDB_KEY_EXTRA_LIVES_TARGET, backUpValuesDTO.getExtraLivesTarget());
            editor.putInt(BackUpDBKeys.BACKUPDB_KEY_EXTRA_LIVES_REWARD, backUpValuesDTO.getExtraLivesReward());

            daysStreak = backUpValuesDTO.getDaysStreak();
            editor.putInt(BackUpDBKeys.BACKUPDB_KEY_DAYS_STREAK, daysStreak);
            editor.putInt(BackUpDBKeys.BACKUPDB_KEY_DAYS_TARGET, backUpValuesDTO.getDaysTarget());
            editor.putInt(BackUpDBKeys.BACKUPDB_KEY_DAYS_REWARD, backUpValuesDTO.getDaysReward());

            editor.putInt(BackUpDBKeys.BACKUPDB_KEY_ASSIGNMENTS_COMPLETED, backUpValuesDTO.getAssignmentsCompleted());
            editor.putInt(BackUpDBKeys.BACKUPDB_KEY_ASSIGNMENTS_TARGET, backUpValuesDTO.getAssignmentsTarget());
            editor.putInt(BackUpDBKeys.BACKUPDB_KEY_ASSIGNMENTS_REWARD, backUpValuesDTO.getAssignmentsReward());

            editor.putInt(BackUpDBKeys.BACKUPDB_KEY_STAGES_TARGET, backUpValuesDTO.getStagesTarget());
            editor.putInt(BackUpDBKeys.BACKUPDB_KEY_STAGES_REWARD, backUpValuesDTO.getStagesReward());

            editor.putBoolean(BackUpDBKeys.BACKUPDB_KEY_ALL_STAGES_ASSIGNMENTS_COMPLETED, backUpValuesDTO.isAllStagesAssignmentsCompleted());

            editor.putString(BackUpDBKeys.BACKUPDB_KEY_LAST_DATE_TIME_BRONZE_TIMER, backUpValuesDTO.getLastDateTimeBronzeTimer());
            editor.putString(BackUpDBKeys.BACKUPDB_KEY_LAST_DATE_TIME_SILVER_TIMER, backUpValuesDTO.getLastDateTimeSilverTimer());
            editor.putString(BackUpDBKeys.BACKUPDB_KEY_LAST_DATE_TIME_GOLD_TIMER, backUpValuesDTO.getLastDateTimeGoldTimer());
            editor.putString(BackUpDBKeys.BACKUPDB_KEY_LAST_DATE_TIME_EXTRA_LIFE, backUpValuesDTO.getLastDateTimeExtraLife());

            editor.putString(BackUpDBKeys.BACKUPDB_KEY_LAST_ACTIVE_DATE, backUpValuesDTO.getLastActiveDate());
            editor.commit();

        } else {

            Log.d("Values Used from - ", "Shared preference");

            coins = sharedPreferences.getInt(BackUpDBKeys.BACKUPDB_KEY_COINS, 0);

            extraLivesBought = sharedPreferences.getInt(BackUpDBKeys.BACKUPDB_KEY_EXTRA_LIVES_BOUGHT, 0);
            extraLivesCollected = sharedPreferences.getInt(BackUpDBKeys.BACKUPDB_KEY_EXTRA_LIVES_COLLECTED, 0);
            extraLivesUsed = sharedPreferences.getInt(BackUpDBKeys.BACKUPDB_KEY_EXTRA_LIVES_USED, 0);

            bronzeTimersBought = sharedPreferences.getInt(BackUpDBKeys.BACKUPDB_KEY_BRONZE_TIMERS_BOUGHT, 0);
            bronzeTimersCollected = sharedPreferences.getInt(BackUpDBKeys.BACKUPDB_KEY_BRONZE_TIMERS_COLLECTED, 0);
            bronzeTimersUsed = sharedPreferences.getInt(BackUpDBKeys.BACKUPDB_KEY_BRONZE_TIMERS_USED, 0);

            silverTimersBought = sharedPreferences.getInt(BackUpDBKeys.BACKUPDB_KEY_SILVER_TIMERS_BOUGHT, 0);
            silverTimersCollected = sharedPreferences.getInt(BackUpDBKeys.BACKUPDB_KEY_SILVER_TIMERS_COLLECTED, 0);
            silverTimersUsed = sharedPreferences.getInt(BackUpDBKeys.BACKUPDB_KEY_SILVER_TIMERS_USED, 0);

            goldTimersBought = sharedPreferences.getInt(BackUpDBKeys.BACKUPDB_KEY_GOLD_TIMERS_BOUGHT, 0);
            goldTimersCollected = sharedPreferences.getInt(BackUpDBKeys.BACKUPDB_KEY_GOLD_TIMERS_COLLECTED, 0);
            goldTimersUsed = sharedPreferences.getInt(BackUpDBKeys.BACKUPDB_KEY_GOLD_TIMERS_USED, 0);

            xpLevel = sharedPreferences.getInt(BackUpDBKeys.BACKUPDB_KEY_XP_LEVEL, 0);
            xpPoints = sharedPreferences.getInt(BackUpDBKeys.BACKUPDB_KEY_XP_TOTAL_POINTS, 0);
            xpLevelPrevTarget = sharedPreferences.getInt(BackUpDBKeys.BACKUPDB_KEY_XP_LEVEL_PREV_TARGET, 0);
            xpLevelNextTarget = sharedPreferences.getInt(BackUpDBKeys.BACKUPDB_KEY_XP_LEVEL_NEXT_TARGET, 0);

            daysStreak = sharedPreferences.getInt(BackUpDBKeys.BACKUPDB_KEY_DAYS_STREAK, 0);
        }

        totalExtraLives = extraLivesBought + extraLivesCollected - extraLivesUsed;
        totalBronzeTimers = bronzeTimersBought + bronzeTimersCollected - bronzeTimersUsed;
        totalSilverTimers = silverTimersBought + silverTimersCollected - silverTimersUsed;
        totalGoldTimers = goldTimersBought + goldTimersCollected - goldTimersUsed;
        totalTimers = totalBronzeTimers + totalSilverTimers + totalGoldTimers;

        xpLevelProgressPercentage = ((xpPoints - xpLevelPrevTarget) * 100) / (xpLevelNextTarget - xpLevelPrevTarget);

        coinsTxtv.setText(String.valueOf(coins));
        timersTxtv.setText(String.valueOf(totalTimers));
        extraLivesTxtv.setText(String.valueOf(totalExtraLives));
        xpLevelTxtv.setText(String.valueOf(xpLevel));
        xpLevelProgressTxtv.setText(String.valueOf(xpLevelProgressPercentage) + " %");
        xpLevelPercentageProgressBar.setProgress(xpLevelProgressPercentage);

        btnStartGame = findViewById(R.id.btn_play);
        btnAssignments = findViewById(R.id.btn_assignments);
        btnLeaderboard = findViewById(R.id.btn_leaderboard);

        btnBeginnersLeaderboard = findViewById(R.id.btn_beginners_leaderboard);
        String userType = dbAdapter.getUserType();
        if(userType.equalsIgnoreCase("Experienced"))
            btnBeginnersLeaderboard.setVisibility(View.GONE);

        viewShop = findViewById(R.id.view_shop);
        viewGenerator = findViewById(R.id.view_generators);
        viewProgress = findViewById(R.id.view_progress);

        viewShop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent shopActivityIntent = new Intent(MainActivity.this, ShopActivity.class);
                shopActivityIntent.putExtra(BackUpDBKeys.BACKUPDB_KEY_COINS, String.valueOf(coins));
                shopActivityIntent.putExtra(BackUpDBKeys.BACKUPDB_KEY_EXTRA_LIVES_BOUGHT, String.valueOf(extraLivesBought));
                shopActivityIntent.putExtra("total_extra_lives", String.valueOf(totalExtraLives));
                shopActivityIntent.putExtra(BackUpDBKeys.BACKUPDB_KEY_BRONZE_TIMERS_BOUGHT, String.valueOf(bronzeTimersBought));
                shopActivityIntent.putExtra(BackUpDBKeys.BACKUPDB_KEY_SILVER_TIMERS_BOUGHT, String.valueOf(silverTimersBought));
                shopActivityIntent.putExtra(BackUpDBKeys.BACKUPDB_KEY_GOLD_TIMERS_BOUGHT, String.valueOf(goldTimersBought));
                shopActivityIntent.putExtra("total_bronze_timers", String.valueOf(totalBronzeTimers));
                shopActivityIntent.putExtra("total_silver_timers", String.valueOf(totalSilverTimers));
                shopActivityIntent.putExtra("total_gold_timers", String.valueOf(totalGoldTimers));
                shopActivityIntent.putExtra("total_timers", String.valueOf(totalTimers));
                shopActivityIntent.putExtra(BackUpDBKeys.BACKUPDB_KEY_XP_TOTAL_POINTS, String.valueOf(xpPoints));
                startActivityForResult(shopActivityIntent, 100);
            }
        });

        viewGenerator.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent assignmentsActivityIntent = new Intent(MainActivity.this, LifeAndTimerGeneratorActivity.class);
                assignmentsActivityIntent.putExtra(BackUpDBKeys.BACKUPDB_KEY_BRONZE_TIMERS_COLLECTED, String.valueOf(bronzeTimersCollected));
                assignmentsActivityIntent.putExtra(BackUpDBKeys.BACKUPDB_KEY_SILVER_TIMERS_COLLECTED, String.valueOf(silverTimersCollected));
                assignmentsActivityIntent.putExtra(BackUpDBKeys.BACKUPDB_KEY_GOLD_TIMERS_COLLECTED, String.valueOf(goldTimersCollected));
                assignmentsActivityIntent.putExtra("total_bronze_timers", String.valueOf(totalBronzeTimers));
                assignmentsActivityIntent.putExtra("total_silver_timers", String.valueOf(totalSilverTimers));
                assignmentsActivityIntent.putExtra("total_gold_timers", String.valueOf(totalGoldTimers));
                assignmentsActivityIntent.putExtra("total_timers", String.valueOf(totalTimers));
                assignmentsActivityIntent.putExtra(BackUpDBKeys.BACKUPDB_KEY_EXTRA_LIVES_COLLECTED, String.valueOf(extraLivesCollected));
                assignmentsActivityIntent.putExtra("total_extra_lives", String.valueOf(totalExtraLives));
                assignmentsActivityIntent.putExtra(BackUpDBKeys.BACKUPDB_KEY_XP_TOTAL_POINTS, String.valueOf(xpPoints));
                startActivityForResult(assignmentsActivityIntent, 200);
            }
        });

        viewProgress.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent progressStatisticsActivityIntent = new Intent(MainActivity.this, ProgressStatisticsActivity.class);
                progressStatisticsActivityIntent.putExtra(BackUpDBKeys.BACKUPDB_KEY_BRONZE_TIMERS_BOUGHT, String.valueOf(bronzeTimersBought));
                progressStatisticsActivityIntent.putExtra(BackUpDBKeys.BACKUPDB_KEY_BRONZE_TIMERS_COLLECTED, String.valueOf(bronzeTimersCollected));
                progressStatisticsActivityIntent.putExtra(BackUpDBKeys.BACKUPDB_KEY_BRONZE_TIMERS_USED, String.valueOf(bronzeTimersUsed));
                progressStatisticsActivityIntent.putExtra(BackUpDBKeys.BACKUPDB_KEY_SILVER_TIMERS_BOUGHT, String.valueOf(silverTimersBought));
                progressStatisticsActivityIntent.putExtra(BackUpDBKeys.BACKUPDB_KEY_SILVER_TIMERS_COLLECTED, String.valueOf(silverTimersCollected));
                progressStatisticsActivityIntent.putExtra(BackUpDBKeys.BACKUPDB_KEY_SILVER_TIMERS_USED, String.valueOf(silverTimersUsed));
                progressStatisticsActivityIntent.putExtra(BackUpDBKeys.BACKUPDB_KEY_GOLD_TIMERS_BOUGHT, String.valueOf(goldTimersBought));
                progressStatisticsActivityIntent.putExtra(BackUpDBKeys.BACKUPDB_KEY_GOLD_TIMERS_COLLECTED, String.valueOf(goldTimersCollected));
                progressStatisticsActivityIntent.putExtra(BackUpDBKeys.BACKUPDB_KEY_GOLD_TIMERS_USED, String.valueOf(goldTimersUsed));
                progressStatisticsActivityIntent.putExtra("total_bronze_timers", String.valueOf(totalBronzeTimers));
                progressStatisticsActivityIntent.putExtra("total_silver_timers", String.valueOf(totalSilverTimers));
                progressStatisticsActivityIntent.putExtra("total_gold_timers", String.valueOf(totalGoldTimers));
                progressStatisticsActivityIntent.putExtra("total_timers", String.valueOf(totalTimers));
                progressStatisticsActivityIntent.putExtra(BackUpDBKeys.BACKUPDB_KEY_COINS, String.valueOf(coins));
                progressStatisticsActivityIntent.putExtra(BackUpDBKeys.BACKUPDB_KEY_EXTRA_LIVES_BOUGHT, String.valueOf(extraLivesBought));
                progressStatisticsActivityIntent.putExtra(BackUpDBKeys.BACKUPDB_KEY_EXTRA_LIVES_COLLECTED, String.valueOf(extraLivesCollected));
                progressStatisticsActivityIntent.putExtra(BackUpDBKeys.BACKUPDB_KEY_EXTRA_LIVES_USED, String.valueOf(extraLivesUsed));
                progressStatisticsActivityIntent.putExtra("total_extra_lives", String.valueOf(totalExtraLives));
                progressStatisticsActivityIntent.putExtra(BackUpDBKeys.BACKUPDB_KEY_XP_LEVEL, String.valueOf(xpLevel));
                progressStatisticsActivityIntent.putExtra(BackUpDBKeys.BACKUPDB_KEY_XP_TOTAL_POINTS, String.valueOf(xpPoints));
                progressStatisticsActivityIntent.putExtra(BackUpDBKeys.BACKUPDB_KEY_XP_LEVEL_PREV_TARGET, String.valueOf(xpLevelPrevTarget));
                progressStatisticsActivityIntent.putExtra(BackUpDBKeys.BACKUPDB_KEY_XP_LEVEL_NEXT_TARGET, String.valueOf(xpLevelNextTarget));
                startActivityForResult(progressStatisticsActivityIntent, 300);
            }
        });

        btnStartGame.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent topicsActivityIntent = new Intent(MainActivity.this, TopicsActivity.class);
                topicsActivityIntent.putExtra(BackUpDBKeys.BACKUPDB_KEY_COINS, String.valueOf(coins));
                topicsActivityIntent.putExtra(BackUpDBKeys.BACKUPDB_KEY_EXTRA_LIVES_USED, String.valueOf(extraLivesUsed));
                topicsActivityIntent.putExtra("total_extra_lives", String.valueOf(totalExtraLives));
                topicsActivityIntent.putExtra(BackUpDBKeys.BACKUPDB_KEY_BRONZE_TIMERS_USED, String.valueOf(bronzeTimersUsed));
                topicsActivityIntent.putExtra(BackUpDBKeys.BACKUPDB_KEY_SILVER_TIMERS_USED, String.valueOf(silverTimersUsed));
                topicsActivityIntent.putExtra(BackUpDBKeys.BACKUPDB_KEY_GOLD_TIMERS_USED, String.valueOf(goldTimersUsed));
                topicsActivityIntent.putExtra("total_bronze_timers", String.valueOf(totalBronzeTimers));
                topicsActivityIntent.putExtra("total_silver_timers", String.valueOf(totalSilverTimers));
                topicsActivityIntent.putExtra("total_gold_timers", String.valueOf(totalGoldTimers));
                topicsActivityIntent.putExtra("total_timers", String.valueOf(totalTimers));
                topicsActivityIntent.putExtra(BackUpDBKeys.BACKUPDB_KEY_XP_LEVEL, String.valueOf(xpLevel));
                topicsActivityIntent.putExtra(BackUpDBKeys.BACKUPDB_KEY_XP_TOTAL_POINTS, String.valueOf(xpPoints));
                topicsActivityIntent.putExtra(BackUpDBKeys.BACKUPDB_KEY_XP_LEVEL_PREV_TARGET, String.valueOf(xpLevelPrevTarget));
                topicsActivityIntent.putExtra(BackUpDBKeys.BACKUPDB_KEY_XP_LEVEL_NEXT_TARGET, String.valueOf(xpLevelNextTarget));
                startActivityForResult(topicsActivityIntent, 500);
            }
        });

        btnAssignments.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent assignmentsActivityIntent = new Intent(MainActivity.this, AssignmentsActivity.class);
                assignmentsActivityIntent.putExtra(BackUpDBKeys.BACKUPDB_KEY_COINS, String.valueOf(coins));
                assignmentsActivityIntent.putExtra(BackUpDBKeys.BACKUPDB_KEY_BRONZE_TIMERS_COLLECTED, String.valueOf(bronzeTimersCollected));
                assignmentsActivityIntent.putExtra(BackUpDBKeys.BACKUPDB_KEY_SILVER_TIMERS_COLLECTED, String.valueOf(silverTimersCollected));
                assignmentsActivityIntent.putExtra(BackUpDBKeys.BACKUPDB_KEY_GOLD_TIMERS_COLLECTED, String.valueOf(goldTimersCollected));
                assignmentsActivityIntent.putExtra(BackUpDBKeys.BACKUPDB_KEY_EXTRA_LIVES_COLLECTED, String.valueOf(extraLivesCollected));
                assignmentsActivityIntent.putExtra(BackUpDBKeys.BACKUPDB_KEY_XP_TOTAL_POINTS, String.valueOf(xpPoints));
                startActivityForResult(assignmentsActivityIntent, 600);
            }
        });

        btnLeaderboard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent leaderboardActivityIntent = new Intent(MainActivity.this, LeaderboardActivity.class);
                leaderboardActivityIntent.putExtra(BackUpDBKeys.BACKUPDB_KEY_XP_TOTAL_POINTS, String.valueOf(xpPoints));
                startActivityForResult(leaderboardActivityIntent, 700);
            }
        });

        btnBeginnersLeaderboard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent leaderboardActivityIntent = new Intent(MainActivity.this, BeginnersLeaderboardActivity.class);
                leaderboardActivityIntent.putExtra(BackUpDBKeys.BACKUPDB_KEY_XP_TOTAL_POINTS, String.valueOf(xpPoints));
                startActivityForResult(leaderboardActivityIntent, 800);
            }
        });

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

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        dbAdapter.addLogEntry("Main", "Return to Main");
        if (requestCode == 100) {
            if (resultCode == RESULT_OK) {
                bronzeTimersBought = data.getIntExtra(BackUpDBKeys.BACKUPDB_KEY_BRONZE_TIMERS_BOUGHT, 0);
                silverTimersBought = data.getIntExtra(BackUpDBKeys.BACKUPDB_KEY_SILVER_TIMERS_BOUGHT, 0);
                goldTimersBought = data.getIntExtra(BackUpDBKeys.BACKUPDB_KEY_GOLD_TIMERS_BOUGHT, 0);
                totalBronzeTimers = data.getIntExtra("total_bronze_timers", 0);
                totalSilverTimers = data.getIntExtra("total_silver_timers", 0);
                totalGoldTimers = data.getIntExtra("total_gold_timers", 0);
                totalTimers = data.getIntExtra("total_timers", 0);
                coins = data.getIntExtra(BackUpDBKeys.BACKUPDB_KEY_COINS, 0);
                extraLivesBought = data.getIntExtra(BackUpDBKeys.BACKUPDB_KEY_EXTRA_LIVES_BOUGHT, 0);
                totalExtraLives = data.getIntExtra("total_extra_lives", 0);
                coinsTxtv.setText(String.valueOf(coins));
                timersTxtv.setText(String.valueOf(totalTimers));
                extraLivesTxtv.setText(String.valueOf(totalExtraLives));
            }
        } else if (requestCode == 200) {
            if (resultCode == RESULT_OK) {
                bronzeTimersCollected = data.getIntExtra(BackUpDBKeys.BACKUPDB_KEY_BRONZE_TIMERS_COLLECTED, 0);
                silverTimersCollected = data.getIntExtra(BackUpDBKeys.BACKUPDB_KEY_SILVER_TIMERS_COLLECTED, 0);
                goldTimersCollected = data.getIntExtra(BackUpDBKeys.BACKUPDB_KEY_GOLD_TIMERS_COLLECTED, 0);
                totalBronzeTimers = data.getIntExtra("total_bronze_timers", 0);
                totalSilverTimers = data.getIntExtra("total_silver_timers", 0);
                totalGoldTimers = data.getIntExtra("total_gold_timers", 0);
                totalTimers = data.getIntExtra("total_timers", 0);
                extraLivesCollected = data.getIntExtra(BackUpDBKeys.BACKUPDB_KEY_EXTRA_LIVES_COLLECTED, 0);
                totalExtraLives = data.getIntExtra("total_extra_lives", 0);
                timersTxtv.setText(String.valueOf(totalTimers));
                extraLivesTxtv.setText(String.valueOf(totalExtraLives));
            }
        } else if (requestCode == 400) {
            if (resultCode == RESULT_OK) {
                coins = data.getIntExtra(BackUpDBKeys.BACKUPDB_KEY_COINS, 0);
                xpLevel = data.getIntExtra(BackUpDBKeys.BACKUPDB_KEY_XP_LEVEL, 0);
                xpPoints = data.getIntExtra(BackUpDBKeys.BACKUPDB_KEY_XP_TOTAL_POINTS, 0);
                xpLevelPrevTarget = data.getIntExtra(BackUpDBKeys.BACKUPDB_KEY_XP_LEVEL_PREV_TARGET, 0);
                xpLevelNextTarget = data.getIntExtra(BackUpDBKeys.BACKUPDB_KEY_XP_LEVEL_NEXT_TARGET, 0);
                coinsTxtv.setText(String.valueOf(coins));
            }
        } else if (requestCode == 500) {
            if (resultCode == RESULT_OK) {
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
                coinsTxtv.setText(String.valueOf(coins));
                timersTxtv.setText(String.valueOf(totalTimers));
                extraLivesTxtv.setText(String.valueOf(totalExtraLives));
                xpLevelTxtv.setText(String.valueOf(xpLevel));
                xpLevelProgressPercentage = ((xpPoints - xpLevelPrevTarget) * 100) / (xpLevelNextTarget - xpLevelPrevTarget);
                xpLevelProgressTxtv.setText(String.valueOf(xpLevelProgressPercentage) + " %");
                xpLevelPercentageProgressBar.setProgress(xpLevelProgressPercentage);
            }
        } else if (requestCode == 600) {
            if (resultCode == RESULT_OK) {
                coins = data.getIntExtra(BackUpDBKeys.BACKUPDB_KEY_COINS, 0);
                coinsTxtv.setText(String.valueOf(coins));
            }
        }
    }

    @Override
    public void onBackPressed() {
        if (exit) {
            backupValues();
            dbAdapter.addLogEntry("Main", "Exit from App");
            finish();
        } else {
            Toast.makeText(this, "Press Back again to Backup & Exit.",
                    Toast.LENGTH_SHORT).show();
            exit = true;
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    exit = false;
                }
            }, 3 * 1000);
        }
    }

    private void backupValues() {

        boolean containKey = sharedPreferences.contains("exists");
        if (containKey) {
            ArrayList<BackUpDTO> backUpObjectsList = new ArrayList<>();
            BackUpDTO backUpObject;

            backUpObject = new BackUpDTO();
            backUpObject.setKey(BackUpDBKeys.BACKUPDB_KEY_COINS);
            backUpObject.setValue(String.valueOf(sharedPreferences.getInt(BackUpDBKeys.BACKUPDB_KEY_COINS, 0)));
            backUpObjectsList.add(backUpObject);

            backUpObject = new BackUpDTO();
            backUpObject.setKey(BackUpDBKeys.BACKUPDB_KEY_BRONZE_TIMERS_BOUGHT);
            backUpObject.setValue(String.valueOf(sharedPreferences.getInt(BackUpDBKeys.BACKUPDB_KEY_BRONZE_TIMERS_BOUGHT, 0)));
            backUpObjectsList.add(backUpObject);

            backUpObject = new BackUpDTO();
            backUpObject.setKey(BackUpDBKeys.BACKUPDB_KEY_BRONZE_TIMERS_COLLECTED);
            backUpObject.setValue(String.valueOf(sharedPreferences.getInt(BackUpDBKeys.BACKUPDB_KEY_BRONZE_TIMERS_COLLECTED, 0)));
            backUpObjectsList.add(backUpObject);

            backUpObject = new BackUpDTO();
            backUpObject.setKey(BackUpDBKeys.BACKUPDB_KEY_BRONZE_TIMERS_USED);
            backUpObject.setValue(String.valueOf(sharedPreferences.getInt(BackUpDBKeys.BACKUPDB_KEY_BRONZE_TIMERS_USED, 0)));
            backUpObjectsList.add(backUpObject);

            backUpObject = new BackUpDTO();
            backUpObject.setKey(BackUpDBKeys.BACKUPDB_KEY_SILVER_TIMERS_BOUGHT);
            backUpObject.setValue(String.valueOf(sharedPreferences.getInt(BackUpDBKeys.BACKUPDB_KEY_SILVER_TIMERS_BOUGHT, 0)));
            backUpObjectsList.add(backUpObject);

            backUpObject = new BackUpDTO();
            backUpObject.setKey(BackUpDBKeys.BACKUPDB_KEY_SILVER_TIMERS_COLLECTED);
            backUpObject.setValue(String.valueOf(sharedPreferences.getInt(BackUpDBKeys.BACKUPDB_KEY_SILVER_TIMERS_COLLECTED, 0)));
            backUpObjectsList.add(backUpObject);

            backUpObject = new BackUpDTO();
            backUpObject.setKey(BackUpDBKeys.BACKUPDB_KEY_SILVER_TIMERS_USED);
            backUpObject.setValue(String.valueOf(sharedPreferences.getInt(BackUpDBKeys.BACKUPDB_KEY_SILVER_TIMERS_USED, 0)));
            backUpObjectsList.add(backUpObject);

            backUpObject = new BackUpDTO();
            backUpObject.setKey(BackUpDBKeys.BACKUPDB_KEY_GOLD_TIMERS_BOUGHT);
            backUpObject.setValue(String.valueOf(sharedPreferences.getInt(BackUpDBKeys.BACKUPDB_KEY_GOLD_TIMERS_BOUGHT, 0)));
            backUpObjectsList.add(backUpObject);

            backUpObject = new BackUpDTO();
            backUpObject.setKey(BackUpDBKeys.BACKUPDB_KEY_GOLD_TIMERS_COLLECTED);
            backUpObject.setValue(String.valueOf(sharedPreferences.getInt(BackUpDBKeys.BACKUPDB_KEY_GOLD_TIMERS_COLLECTED, 0)));
            backUpObjectsList.add(backUpObject);

            backUpObject = new BackUpDTO();
            backUpObject.setKey(BackUpDBKeys.BACKUPDB_KEY_GOLD_TIMERS_USED);
            backUpObject.setValue(String.valueOf(sharedPreferences.getInt(BackUpDBKeys.BACKUPDB_KEY_GOLD_TIMERS_USED, 0)));
            backUpObjectsList.add(backUpObject);

            backUpObject = new BackUpDTO();
            backUpObject.setKey(BackUpDBKeys.BACKUPDB_KEY_EXTRA_LIVES_BOUGHT);
            backUpObject.setValue(String.valueOf(sharedPreferences.getInt(BackUpDBKeys.BACKUPDB_KEY_EXTRA_LIVES_BOUGHT, 0)));
            backUpObjectsList.add(backUpObject);

            backUpObject = new BackUpDTO();
            backUpObject.setKey(BackUpDBKeys.BACKUPDB_KEY_EXTRA_LIVES_COLLECTED);
            backUpObject.setValue(String.valueOf(sharedPreferences.getInt(BackUpDBKeys.BACKUPDB_KEY_EXTRA_LIVES_COLLECTED, 0)));
            backUpObjectsList.add(backUpObject);

            backUpObject = new BackUpDTO();
            backUpObject.setKey(BackUpDBKeys.BACKUPDB_KEY_EXTRA_LIVES_USED);
            backUpObject.setValue(String.valueOf(sharedPreferences.getInt(BackUpDBKeys.BACKUPDB_KEY_EXTRA_LIVES_USED, 0)));
            backUpObjectsList.add(backUpObject);

            backUpObject = new BackUpDTO();
            backUpObject.setKey(BackUpDBKeys.BACKUPDB_KEY_XP_LEVEL);
            backUpObject.setValue(String.valueOf(sharedPreferences.getInt(BackUpDBKeys.BACKUPDB_KEY_XP_LEVEL, 1)));
            backUpObjectsList.add(backUpObject);

            backUpObject = new BackUpDTO();
            backUpObject.setKey(BackUpDBKeys.BACKUPDB_KEY_XP_TOTAL_POINTS);
            backUpObject.setValue(String.valueOf(sharedPreferences.getInt(BackUpDBKeys.BACKUPDB_KEY_XP_TOTAL_POINTS, 0)));
            backUpObjectsList.add(backUpObject);

            backUpObject = new BackUpDTO();
            backUpObject.setKey(BackUpDBKeys.BACKUPDB_KEY_XP_LEVEL_PREV_TARGET);
            backUpObject.setValue(String.valueOf(sharedPreferences.getInt(BackUpDBKeys.BACKUPDB_KEY_XP_LEVEL_PREV_TARGET, 0)));
            backUpObjectsList.add(backUpObject);

            backUpObject = new BackUpDTO();
            backUpObject.setKey(BackUpDBKeys.BACKUPDB_KEY_XP_LEVEL_NEXT_TARGET);
            backUpObject.setValue(String.valueOf(sharedPreferences.getInt(BackUpDBKeys.BACKUPDB_KEY_XP_LEVEL_NEXT_TARGET, 10)));
            backUpObjectsList.add(backUpObject);

            backUpObject = new BackUpDTO();
            backUpObject.setKey(BackUpDBKeys.BACKUPDB_KEY_TIMERS_TARGET);
            backUpObject.setValue(String.valueOf(sharedPreferences.getInt(BackUpDBKeys.BACKUPDB_KEY_TIMERS_TARGET, 5)));
            backUpObjectsList.add(backUpObject);

            backUpObject = new BackUpDTO();
            backUpObject.setKey(BackUpDBKeys.BACKUPDB_KEY_TIMERS_REWARD);
            backUpObject.setValue(String.valueOf(sharedPreferences.getInt(BackUpDBKeys.BACKUPDB_KEY_TIMERS_REWARD, 100)));
            backUpObjectsList.add(backUpObject);

            backUpObject = new BackUpDTO();
            backUpObject.setKey(BackUpDBKeys.BACKUPDB_KEY_EXTRA_LIVES_TARGET);
            backUpObject.setValue(String.valueOf(sharedPreferences.getInt(BackUpDBKeys.BACKUPDB_KEY_EXTRA_LIVES_TARGET, 5)));
            backUpObjectsList.add(backUpObject);

            backUpObject = new BackUpDTO();
            backUpObject.setKey(BackUpDBKeys.BACKUPDB_KEY_EXTRA_LIVES_REWARD);
            backUpObject.setValue(String.valueOf(sharedPreferences.getInt(BackUpDBKeys.BACKUPDB_KEY_EXTRA_LIVES_REWARD, 250)));
            backUpObjectsList.add(backUpObject);

            backUpObject = new BackUpDTO();
            backUpObject.setKey(BackUpDBKeys.BACKUPDB_KEY_DAYS_STREAK);
            backUpObject.setValue(String.valueOf(sharedPreferences.getInt(BackUpDBKeys.BACKUPDB_KEY_DAYS_STREAK, 1)));
            backUpObjectsList.add(backUpObject);

            backUpObject = new BackUpDTO();
            backUpObject.setKey(BackUpDBKeys.BACKUPDB_KEY_DAYS_TARGET);
            backUpObject.setValue(String.valueOf(sharedPreferences.getInt(BackUpDBKeys.BACKUPDB_KEY_DAYS_TARGET, 5)));
            backUpObjectsList.add(backUpObject);

            backUpObject = new BackUpDTO();
            backUpObject.setKey(BackUpDBKeys.BACKUPDB_KEY_DAYS_REWARD);
            backUpObject.setValue(String.valueOf(sharedPreferences.getInt(BackUpDBKeys.BACKUPDB_KEY_DAYS_REWARD, 200)));
            backUpObjectsList.add(backUpObject);

            backUpObject = new BackUpDTO();
            backUpObject.setKey(BackUpDBKeys.BACKUPDB_KEY_ASSIGNMENTS_COMPLETED);
            backUpObject.setValue(String.valueOf(sharedPreferences.getInt(BackUpDBKeys.BACKUPDB_KEY_ASSIGNMENTS_COMPLETED, 0)));
            backUpObjectsList.add(backUpObject);

            backUpObject = new BackUpDTO();
            backUpObject.setKey(BackUpDBKeys.BACKUPDB_KEY_ASSIGNMENTS_TARGET);
            backUpObject.setValue(String.valueOf(sharedPreferences.getInt(BackUpDBKeys.BACKUPDB_KEY_ASSIGNMENTS_TARGET, 2)));
            backUpObjectsList.add(backUpObject);

            backUpObject = new BackUpDTO();
            backUpObject.setKey(BackUpDBKeys.BACKUPDB_KEY_ASSIGNMENTS_REWARD);
            backUpObject.setValue(String.valueOf(sharedPreferences.getInt(BackUpDBKeys.BACKUPDB_KEY_ASSIGNMENTS_REWARD, 500)));
            backUpObjectsList.add(backUpObject);

            backUpObject = new BackUpDTO();
            backUpObject.setKey(BackUpDBKeys.BACKUPDB_KEY_STAGES_TARGET);
            backUpObject.setValue(String.valueOf(sharedPreferences.getInt(BackUpDBKeys.BACKUPDB_KEY_STAGES_TARGET, 5)));
            backUpObjectsList.add(backUpObject);

            backUpObject = new BackUpDTO();
            backUpObject.setKey(BackUpDBKeys.BACKUPDB_KEY_STAGES_REWARD);
            backUpObject.setValue(String.valueOf(sharedPreferences.getInt(BackUpDBKeys.BACKUPDB_KEY_STAGES_REWARD, 100)));
            backUpObjectsList.add(backUpObject);

            backUpObject = new BackUpDTO();
            backUpObject.setKey(BackUpDBKeys.BACKUPDB_KEY_ALL_STAGES_ASSIGNMENTS_COMPLETED);
            backUpObject.setValue(String.valueOf(sharedPreferences.getBoolean(BackUpDBKeys.BACKUPDB_KEY_ALL_STAGES_ASSIGNMENTS_COMPLETED, false)));
            backUpObjectsList.add(backUpObject);

            DateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
            DateFormat sdfDate = new SimpleDateFormat("dd/MM/yyyy");
            Date current = new Date();

            backUpObject = new BackUpDTO();
            backUpObject.setKey(BackUpDBKeys.BACKUPDB_KEY_LAST_DATE_TIME_BRONZE_TIMER);
            backUpObject.setValue(sharedPreferences.getString(BackUpDBKeys.BACKUPDB_KEY_LAST_DATE_TIME_BRONZE_TIMER, sdf.format(current)));
            backUpObjectsList.add(backUpObject);

            backUpObject = new BackUpDTO();
            backUpObject.setKey(BackUpDBKeys.BACKUPDB_KEY_LAST_DATE_TIME_SILVER_TIMER);
            backUpObject.setValue(sharedPreferences.getString(BackUpDBKeys.BACKUPDB_KEY_LAST_DATE_TIME_SILVER_TIMER, sdf.format(current)));
            backUpObjectsList.add(backUpObject);

            backUpObject = new BackUpDTO();
            backUpObject.setKey(BackUpDBKeys.BACKUPDB_KEY_LAST_DATE_TIME_GOLD_TIMER);
            backUpObject.setValue(sharedPreferences.getString(BackUpDBKeys.BACKUPDB_KEY_LAST_DATE_TIME_GOLD_TIMER, sdf.format(current)));
            backUpObjectsList.add(backUpObject);

            backUpObject = new BackUpDTO();
            backUpObject.setKey(BackUpDBKeys.BACKUPDB_KEY_LAST_DATE_TIME_EXTRA_LIFE);
            backUpObject.setValue(sharedPreferences.getString(BackUpDBKeys.BACKUPDB_KEY_LAST_DATE_TIME_EXTRA_LIFE, sdf.format(current)));
            backUpObjectsList.add(backUpObject);

            backUpObject = new BackUpDTO();
            backUpObject.setKey(BackUpDBKeys.BACKUPDB_KEY_LAST_ACTIVE_DATE);
            backUpObject.setValue(sharedPreferences.getString(BackUpDBKeys.BACKUPDB_KEY_LAST_ACTIVE_DATE, sdfDate.format(current)));
            backUpObjectsList.add(backUpObject);

            dbAdapter.updateValues(backUpObjectsList);
            Log.d("Backup - ", "Success");
        }

    }

}