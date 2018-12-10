package com.research.ageac.gamified;

import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.content.pm.ActivityInfo;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.charts.CombinedChart;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.data.CombinedData;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.interfaces.datasets.IBarDataSet;
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet;
import com.research.ageac.gamified.quizlibrary.QuizCounts;

import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class ProgressStatisticsActivity extends AppCompatActivity {

    ImageView backBtn, btnCompletionChartFullscreen, btnQuizAttemptsChartFullscreen, btnAccuracyChartFullscreen, btnXPChartFullscreen, btnTimeSpentChartFullscreen;
    TextView progressPercentageTxtv, progressPercentageDecimalPartTxtv;
    TextView totalQuizzesTxtv, quizzesCompletedTxtv, totalAttemptsTxtv, attemptsFailedTxtv, questionsAttemptedTxtv, wronglyAnsweredTxtv, accuracyTxtv;
    TextView experiencePointsTxtv, xpLevelTxtv, xpLevelCompletionTxtv, levelUpAtTxtv;
    TextView totalDaysSpentTxtv, totalTimeSpentTxtv, maxTimeActiveTxtv, dateAtWhichMaxTimeSpentTxtv, inactiveDaysTxtv, daysStreakTxtv, maxDaysStreakTxtv;
    TextView coinsTxtv, totalExtraLivesTxtv, totalTimersTxtv, totalbronzeTimersTxtv, totalSilverTimersTxtv, totalGoldTimersTxtv;
    TextView bronzeTimersBoughtTxtv, bronzeTimersCollectedTxtv, bronzeTimersUsedTxtv;
    TextView silverTimersBoughtTxtv, silverTimersCollectedTxtv, silverTimersUsedTxtv;
    TextView goldTimersBoughtTxtv, goldTimersCollectedTxtv, goldTimersUsedTxtv;
    TextView extraLivesBoughtTxtv, extraLivesCollectedTxtv, extraLivesUsedTxtv;
    ProgressBar progressBar;

    TextView txtvUsername;

    int xpPoints;

    double progressPercentage, accuracy;

    DBAdapter dbAdapter;
    StageAttemptsDTO stageAttemptsDTO;

    BarChart chartAttempts;
    LineChart chartCompletion, chartXP, chartTimeSpent, chartAccuracy;

    ArrayList<ProgressDTO> progressDTO;
    AppUsage appUsage;

    LineData lineChartDataTimeSpent, lineChartDataXP, lineChartDataAccuracy, lineChartDataCompletionPercentage;
    BarData barChartDataAttempts;

    int maxDaysStreak, inactiveDays;
    long totalTimeSpent, maxTimeSpentInADay;
    String dateAtWhichMaxTimeSpent;

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
        setContentView(R.layout.activity_progress_statistics);

        final View decorView = getWindow().getDecorView();
        decorView.setOnSystemUiVisibilityChangeListener(new View.OnSystemUiVisibilityChangeListener() {

            @Override
            public void onSystemUiVisibilityChange(int visibility) {
                if ((visibility & View.SYSTEM_UI_FLAG_FULLSCREEN) == 0) {
                    decorView.setSystemUiVisibility(flags);
                }
            }
        });

        appUsage = new AppUsage();

        dbAdapter = new DBAdapter(ProgressStatisticsActivity.this);
        dbAdapter.addLogEntry("ProgressStats", "OnCreate");

        DecimalFormat df = new DecimalFormat("#.00");

        int coins, totalExtraLives, totalBronzeTimers, totalSilverTimers, totalGoldTimers, totalTimers;
        int xpLevel, xpLevelPrevTarget, xpLevelNextTarget, xpLevelProgressPercentage;
        int bronzeTimersBought, bronzeTimersCollected, bronzeTimersUsed;
        int silverTimersBought, silverTimersCollected, silverTimersUsed;
        int goldTimersBought, goldTimersCollected, goldTimersUsed;
        int extraLivesBought, extraLivesCollected, extraLivesUsed;
        int daysStreak;
        int quizzesCompleted, totalQuizzes;

        coins = Integer.parseInt(getIntent().getExtras().getString(BackUpDBKeys.BACKUPDB_KEY_COINS));
        extraLivesBought = Integer.parseInt(getIntent().getExtras().getString(BackUpDBKeys.BACKUPDB_KEY_EXTRA_LIVES_BOUGHT));
        extraLivesCollected = Integer.parseInt(getIntent().getExtras().getString(BackUpDBKeys.BACKUPDB_KEY_EXTRA_LIVES_COLLECTED));
        extraLivesUsed = Integer.parseInt(getIntent().getExtras().getString(BackUpDBKeys.BACKUPDB_KEY_EXTRA_LIVES_USED));
        totalExtraLives = Integer.parseInt(getIntent().getExtras().getString("total_extra_lives"));
        bronzeTimersBought = Integer.parseInt(getIntent().getExtras().getString(BackUpDBKeys.BACKUPDB_KEY_BRONZE_TIMERS_BOUGHT));
        silverTimersBought = Integer.parseInt(getIntent().getExtras().getString(BackUpDBKeys.BACKUPDB_KEY_SILVER_TIMERS_BOUGHT));
        goldTimersBought = Integer.parseInt(getIntent().getExtras().getString(BackUpDBKeys.BACKUPDB_KEY_GOLD_TIMERS_BOUGHT));
        bronzeTimersCollected = Integer.parseInt(getIntent().getExtras().getString(BackUpDBKeys.BACKUPDB_KEY_BRONZE_TIMERS_COLLECTED));
        silverTimersCollected = Integer.parseInt(getIntent().getExtras().getString(BackUpDBKeys.BACKUPDB_KEY_SILVER_TIMERS_COLLECTED));
        goldTimersCollected = Integer.parseInt(getIntent().getExtras().getString(BackUpDBKeys.BACKUPDB_KEY_GOLD_TIMERS_COLLECTED));
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

        SharedPreferences sharedPreferences = getSharedPreferences("Extras", MODE_PRIVATE);
        daysStreak = sharedPreferences.getInt(BackUpDBKeys.BACKUPDB_KEY_DAYS_STREAK, 0);


        txtvUsername = findViewById(R.id.txtv_stats_username);
        txtvUsername.setText("Statistics for " + dbAdapter.getUserName());

        progressDTO = dbAdapter.getProgressDTO();
        /*for(int i =0;i<progressDTO.size();i++)
        {
            Log.e("Before - ", String.valueOf(progressDTO.get(i).getExperienceGained()));
        }*/
        refineData();
        getUsageStatsDataFromRefinedData();
        /*for(int i =0;i<progressDTO.size();i++)
        {
            Log.e("After - ", String.valueOf(progressDTO.get(i).getExperienceGained()));
        }*/

        //Order - Top to Bottom
        //Back Button
        backBtn = findViewById(R.id.stats_back_btn);
        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        //Progress of the application
        progressPercentageTxtv = findViewById(R.id.txtv_progress_percentage_indicator);
        progressPercentageDecimalPartTxtv = findViewById(R.id.txtv_progress_percentage_indicator_decimal);
        progressBar = findViewById(R.id.overall_progressBar);
        quizzesCompleted = dbAdapter.getCompletedStagesNumber();
        totalQuizzes = QuizCounts.totalQuizzes;
        progressPercentage = (quizzesCompleted * 100.0) / totalQuizzes;
        String decimalPart = null;
        double decPrt = progressPercentage % 1.0;
        if (decPrt ==0)
            decimalPart ="0000";
        else
            decimalPart = String.valueOf((progressPercentage % 1.0) * 10000);
        progressPercentageTxtv.setText(String.valueOf((int) progressPercentage));
        progressPercentageDecimalPartTxtv.setText(decimalPart.substring(0, 2));
        progressBar.setProgress((int) progressPercentage);

        //Completion Line Chart
        chartCompletion = findViewById(R.id.line_chart_completion);
        //chartCompletionAndXP.setOnChartGestureListener(this);
        //chartCompletionAndXP.setOnChartValueSelectedListener(this);
        chartCompletion.setDragEnabled(true);
        chartCompletion.setScaleEnabled(true);
        btnCompletionChartFullscreen = findViewById(R.id.line_chart_completion_fullscreen);
        btnCompletionChartFullscreen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showFullScreenChart(null, lineChartDataCompletionPercentage, 20f);
            }
        });


        //Quiz Statistics
        stageAttemptsDTO = dbAdapter.getQuizAttempts();

        totalQuizzesTxtv = findViewById(R.id.txtv_stats_total_quizzes);
        quizzesCompletedTxtv = findViewById(R.id.txtv_stats_quizzes_completed);
        totalAttemptsTxtv = findViewById(R.id.txtv_stats_quiz_attempts);
        attemptsFailedTxtv = findViewById(R.id.txtv_stats_quiz_failed);

        totalQuizzesTxtv.setText(String.valueOf(QuizCounts.totalQuizzes));
        quizzesCompletedTxtv.setText(String.valueOf(quizzesCompleted));
        totalAttemptsTxtv.setText(String.valueOf(stageAttemptsDTO.getTotalAttempts()));
        attemptsFailedTxtv.setText(String.valueOf(stageAttemptsDTO.getFailedAttempts()));

        chartAttempts = findViewById(R.id.bar_chart_attempts);
        //chartQuizAttempts.setOnChartGestureListener(this);
        //chartQuizAttempts.setOnChartValueSelectedListener(this);
        chartAttempts.setDragEnabled(true);
        chartAttempts.setScaleEnabled(true);
        btnQuizAttemptsChartFullscreen = findViewById(R.id.bar_chart_attempts_fullscreen);
        btnQuizAttemptsChartFullscreen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showFullScreenChart(barChartDataAttempts, null, 10f);
            }
        });


        //Accuracy
        int totalQuestionsAttempted = stageAttemptsDTO.getQuestionsAttempted();
        int questionsAnsweredWrong = stageAttemptsDTO.getQuestionsAnsweredWrong();

        questionsAttemptedTxtv = findViewById(R.id.txtv_stats_questions_attempted);
        wronglyAnsweredTxtv = findViewById(R.id.txtv_stats_answered_wrong);
        accuracyTxtv = findViewById(R.id.txtv_stats_accuracy);

        questionsAttemptedTxtv.setText(String.valueOf(totalQuestionsAttempted));
        wronglyAnsweredTxtv.setText(String.valueOf(questionsAnsweredWrong));

        if (totalQuestionsAttempted == 0)
            accuracy = 0;
        else
            accuracy = ((totalQuestionsAttempted - questionsAnsweredWrong) * 100) / (double) totalQuestionsAttempted;
        accuracyTxtv.setText(df.format(accuracy) + " %");

        chartAccuracy = findViewById(R.id.line_chart_accuracy);
        //chartAccuracy.setOnChartGestureListener(this);
        //chartAccuracy.setOnChartValueSelectedListener(this);
        chartAccuracy.setDragEnabled(true);
        chartAccuracy.setScaleEnabled(true);
        btnAccuracyChartFullscreen = findViewById(R.id.line_chart_accuracy_fullscreen);
        btnAccuracyChartFullscreen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showFullScreenChart(null, lineChartDataAccuracy, 20f);
            }
        });


        //Experience
        experiencePointsTxtv = findViewById(R.id.txtv_stats_total_xp_points);
        xpLevelTxtv = findViewById(R.id.txtv_stats_xp_level);
        xpLevelCompletionTxtv = findViewById(R.id.txtv_stats_xp_level_completion);
        levelUpAtTxtv = findViewById(R.id.txtv_stats_xp_target);

        experiencePointsTxtv.setText(String.valueOf(xpPoints));
        xpLevelTxtv.setText(String.valueOf(xpLevel));
        xpLevelProgressPercentage = ((xpPoints - xpLevelPrevTarget) * 100) / (xpLevelNextTarget - xpLevelPrevTarget);
        xpLevelCompletionTxtv.setText(String.valueOf(xpLevelProgressPercentage) + " %");
        levelUpAtTxtv.setText(String.valueOf(xpLevelNextTarget));

        chartXP = findViewById(R.id.line_chart_xp);
        //chartXP.setOnChartGestureListener(this);
        //chartXP.setOnChartValueSelectedListener(this);
        chartXP.setDragEnabled(true);
        chartXP.setScaleEnabled(true);
        btnXPChartFullscreen = findViewById(R.id.line_chart_xp_fullscreen);
        btnXPChartFullscreen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showFullScreenChart(null, lineChartDataXP, 10f);
            }
        });


        //Usage Statistics
        totalDaysSpentTxtv = findViewById(R.id.txtv_stats_days_spent);
        totalTimeSpentTxtv = findViewById(R.id.txtv_stats_time_active);
        maxTimeActiveTxtv = findViewById(R.id.txtv_stats_max_time_active);
        dateAtWhichMaxTimeSpentTxtv = findViewById(R.id.txtv_stats_max_time_active_date);
        inactiveDaysTxtv = findViewById(R.id.txtv_stats_days_inactive);
        daysStreakTxtv = findViewById(R.id.txtv_stats_current_days_streak);
        maxDaysStreakTxtv = findViewById(R.id.txtv_stats_max_days_streak);

        totalDaysSpentTxtv.setText(String.valueOf(progressDTO.size()));
        long seconds = totalTimeSpent;
        long minutes = seconds / 60;
        long hours = minutes / 60;
        String time = (hours % 24) + "h " + (minutes % 60) + "m " + (seconds % 60) +"s";
        totalTimeSpentTxtv.setText(time);
        seconds = maxTimeSpentInADay;
        minutes = seconds / 60;
        hours = minutes / 60;
        time = (hours % 24) + "h " + (minutes % 60) + "m " + (seconds % 60) +"s";
        maxTimeActiveTxtv.setText(time);
        dateAtWhichMaxTimeSpentTxtv.setText(dateAtWhichMaxTimeSpent);
        inactiveDaysTxtv.setText(String.valueOf(inactiveDays));
        daysStreakTxtv.setText(String.valueOf(daysStreak));
        maxDaysStreakTxtv.setText(String.valueOf(maxDaysStreak));

        chartTimeSpent = findViewById(R.id.line_chart_time_spent);
        //chartTimeSpent.setOnChartGestureListener(this);
        //chartTimeSpent.setOnChartValueSelectedListener(this);
        chartTimeSpent.setDragEnabled(true);
        chartTimeSpent.setScaleEnabled(true);
        btnTimeSpentChartFullscreen = findViewById(R.id.line_chart_time_spent_fullscreen);
        btnTimeSpentChartFullscreen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showFullScreenChart(null, lineChartDataTimeSpent, 20f);
            }
        });


        //Other Statistics
        coinsTxtv = findViewById(R.id.txtv_statistics_coins);
        totalExtraLivesTxtv = findViewById(R.id.txtv_stats_total_extra_lives);
        totalTimersTxtv = findViewById(R.id.txtv_stats_total_timers);
        totalbronzeTimersTxtv = findViewById(R.id.txtv_stats_total_bronze_timers);
        totalSilverTimersTxtv = findViewById(R.id.txtv_stats_total_silver_timers);
        totalGoldTimersTxtv = findViewById(R.id.txtv_stats_total_gold_timers);
        extraLivesBoughtTxtv = findViewById(R.id.txtv_stats_extra_lives_bought);
        extraLivesCollectedTxtv = findViewById(R.id.txtv_stats_extra_lives_collected);
        extraLivesUsedTxtv = findViewById(R.id.txtv_stats_extra_lives_used);
        bronzeTimersBoughtTxtv = findViewById(R.id.txtv_stats_bronze_timer_bought);
        bronzeTimersCollectedTxtv = findViewById(R.id.txtv_stats_bronze_timer_collected);
        bronzeTimersUsedTxtv = findViewById(R.id.txtv_stats_bronze_timer_used);
        silverTimersBoughtTxtv = findViewById(R.id.txtv_stats_silver_timer_bought);
        silverTimersCollectedTxtv = findViewById(R.id.txtv_stats_silver_timer_collected);
        silverTimersUsedTxtv = findViewById(R.id.txtv_stats_silver_timer_used);
        goldTimersBoughtTxtv = findViewById(R.id.txtv_stats_gold_timer_bought);
        goldTimersCollectedTxtv = findViewById(R.id.txtv_stats_gold_timer_collected);
        goldTimersUsedTxtv = findViewById(R.id.txtv_stats_gold_timer_used);


        coinsTxtv.setText(String.valueOf(coins));
        totalExtraLivesTxtv.setText(String.valueOf(totalExtraLives));
        extraLivesBoughtTxtv.setText(String.valueOf(extraLivesBought));
        extraLivesCollectedTxtv.setText(String.valueOf(extraLivesCollected));
        extraLivesUsedTxtv.setText(String.valueOf(extraLivesUsed));
        totalTimersTxtv.setText(String.valueOf(totalTimers));
        totalbronzeTimersTxtv.setText(String.valueOf(totalBronzeTimers));
        totalSilverTimersTxtv.setText(String.valueOf(totalSilverTimers));
        totalGoldTimersTxtv.setText(String.valueOf(totalGoldTimers));
        bronzeTimersBoughtTxtv.setText(String.valueOf(bronzeTimersBought));
        bronzeTimersCollectedTxtv.setText(String.valueOf(bronzeTimersCollected));
        bronzeTimersUsedTxtv.setText(String.valueOf(bronzeTimersUsed));
        silverTimersBoughtTxtv.setText(String.valueOf(silverTimersBought));
        silverTimersCollectedTxtv.setText(String.valueOf(silverTimersCollected));
        silverTimersUsedTxtv.setText(String.valueOf(silverTimersUsed));
        goldTimersBoughtTxtv.setText(String.valueOf(goldTimersBought));
        goldTimersCollectedTxtv.setText(String.valueOf(goldTimersCollected));
        goldTimersUsedTxtv.setText(String.valueOf(goldTimersUsed));


        //Generating expandable lists
        ArrayList<String> dates;
        dates = getDates();

        setGraphForCompletion();
        setGraphForAttempts();
        setGraphForAccuracy();
        setGraphForXP();
        setGraphForTimeSpent();

    }


    private void showFullScreenChart(BarData barData, LineData lineChartData, float rangeDifference) {
        ProgressStatisticsActivity.this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        AlertDialog.Builder fullScreenChartBuilder = new AlertDialog.Builder(ProgressStatisticsActivity.this, android.R.style.Theme_Light_NoTitleBar_Fullscreen);
        View view = LayoutInflater.from(ProgressStatisticsActivity.this).inflate(R.layout.fullscreen_chart_dialog_layout, null);
        CombinedChart fullscreenChart = view.findViewById(R.id.fullscreen_chart);
        fullscreenChart.setDragEnabled(true);
        fullscreenChart.setScaleEnabled(true);

        CombinedData combinedData = new CombinedData();

        fullscreenChart.setDrawOrder(new CombinedChart.DrawOrder[]{CombinedChart.DrawOrder.BAR, CombinedChart.DrawOrder.LINE});
        if (barData == null)
            fullscreenChart.setDrawOrder(new CombinedChart.DrawOrder[]{CombinedChart.DrawOrder.LINE});

        if (barData == null) {
            combinedData.setData(lineChartData);
        } else {
            combinedData.setData(barData);
        }
        fullscreenChart.setData(combinedData);
        fullscreenChart.getDescription().setEnabled(false);
        fullscreenChart.getXAxis().setPosition(XAxis.XAxisPosition.BOTTOM);
        fullscreenChart.getAxisLeft().setAxisMinimum(0f);
        fullscreenChart.getAxisRight().setEnabled(false);
        fullscreenChart.getAxisLeft().setGranularity(20f);
        fullscreenChart.getXAxis().setGranularity(1f);
        fullscreenChart.getAxisLeft().setAxisMaximum((float) Math.ceil(combinedData.getYMax() / rangeDifference) * rangeDifference);

        fullScreenChartBuilder.setView(view);
        final AlertDialog fullscreenChartDialog = fullScreenChartBuilder.create();
        fullscreenChartDialog.setCanceledOnTouchOutside(false);
        fullscreenChartDialog.show();

        fullscreenChartDialog.setOnCancelListener(new DialogInterface.OnCancelListener() {
            @Override
            public void onCancel(DialogInterface dialog) {
                ProgressStatisticsActivity.this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
                fullscreenChartDialog.dismiss();
            }
        });
    }

    private void refineData() {
        int currentStreak;
        String thisDateString, prevDateString;
        Date thisDate = null, prevDate = null;
        DateFormat sdfDate = new SimpleDateFormat("dd/MM/yyyy");
        prevDateString = progressDTO.get(0).getStartDate();
        try {
            prevDate = sdfDate.parse(prevDateString);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        maxDaysStreak = 1;
        currentStreak = 1;
        for (int i = 1; i < progressDTO.size(); i++) {
            thisDateString = progressDTO.get(i).getStartDate();
            try {
                thisDate = sdfDate.parse(thisDateString);
            } catch (ParseException e) {
                e.printStackTrace();
            }
            if (thisDateString.equalsIgnoreCase(prevDateString)) {
                progressDTO.get(i).setTimeDiff(progressDTO.get(i).getTimeDiff() + progressDTO.get(i - 1).getTimeDiff());
                progressDTO.remove(i - 1);
                i--;
            } else {
                int daysDiff = (int) ((thisDate.getTime() - prevDate.getTime()) / 86400000);
                if (daysDiff == 1) {
                    ++currentStreak;
                } else if (daysDiff > 1) {
                    if (currentStreak > maxDaysStreak) {
                        maxDaysStreak = currentStreak;
                    }
                    currentStreak = 1;
                    Calendar calendar = Calendar.getInstance();
                    calendar.setTime(prevDate);
                    for (int j = 1; j < daysDiff; j++) {
                        calendar.add(Calendar.DATE, 1);
                        Date dateInBetween = calendar.getTime();
                        ProgressDTO obj = new ProgressDTO();
                        obj.setStartDate(sdfDate.format(dateInBetween));
                        obj.setStartTime("00:00:00");
                        obj.setEndTime("00:00:00");
                        obj.setTimeDiff(0);
                        obj.setActivityName("Inactive");
                        obj.setExperienceGained(progressDTO.get(i - 1).getExperienceGained());
                        obj.setQuizAttempts(progressDTO.get(i - 1).getQuizAttempts());
                        obj.setFailedAttempts(progressDTO.get(i - 1).getFailedAttempts());
                        obj.setQuestionAttempts(progressDTO.get(i - 1).getQuestionAttempts());
                        obj.setWronglyAnswered(progressDTO.get(i - 1).getWronglyAnswered());
                        obj.setCompletionPercentage(progressDTO.get(i - 1).getCompletionPercentage());
                        progressDTO.add(i, obj);
                        i++;
                    }
                }
            }
            prevDateString = thisDateString;
            prevDate = thisDate;
        }
        if (currentStreak > maxDaysStreak)
            maxDaysStreak = currentStreak;
    }


    private void getUsageStatsDataFromRefinedData() {
        inactiveDays = 0;
        totalTimeSpent = progressDTO.get(0).getTimeDiff();
        maxTimeSpentInADay = totalTimeSpent;
        dateAtWhichMaxTimeSpent = progressDTO.get(0).getStartDate();
        if (totalTimeSpent == 0)
            ++inactiveDays;
        for (int i = 1; i < progressDTO.size(); i++) {
            if (progressDTO.get(i).getTimeDiff() == 0)
                ++inactiveDays;
            if (progressDTO.get(i).getTimeDiff() > maxTimeSpentInADay) {
                maxTimeSpentInADay = progressDTO.get(i).getTimeDiff();
                dateAtWhichMaxTimeSpent = progressDTO.get(i).getStartDate();
            }
            totalTimeSpent = totalTimeSpent + progressDTO.get(i).getTimeDiff();
        }
    }

    private void setGraphForTimeSpent() {
        ArrayList<Double> timeSpent;
        timeSpent = getTimeSpent();

        ArrayList<Entry> tsY = new ArrayList<>();
        for (int i = 0; i < progressDTO.size(); i++) {
            tsY.add(new Entry(i + 1, Float.parseFloat(String.valueOf(timeSpent.get(i)))));
        }

        LineDataSet tsDataSet = new LineDataSet(tsY, "TS");
        tsDataSet.setFillAlpha(110);
        tsDataSet.setColor(Color.BLUE);

        ArrayList<ILineDataSet> tsDataSets = new ArrayList<>();
        tsDataSets.add(tsDataSet);
        lineChartDataTimeSpent = new LineData(tsDataSets);
        chartTimeSpent.setData(lineChartDataTimeSpent);
        chartTimeSpent.getDescription().setEnabled(false);
        chartTimeSpent.getXAxis().setPosition(XAxis.XAxisPosition.BOTTOM);
        chartTimeSpent.getAxisLeft().setAxisMinimum(0f);
        chartTimeSpent.getAxisRight().setEnabled(false);
        chartTimeSpent.getAxisLeft().setGranularity(20f);
        chartTimeSpent.getXAxis().setGranularity(1f);
        chartTimeSpent.getAxisLeft().setAxisMaximum((float) Math.ceil(lineChartDataTimeSpent.getYMax() / 20) * 20);
    }

    private void setGraphForXP() {
        ArrayList<Integer> xpCumulative, xp;
        xpCumulative = getXpCumulative();
        xp = getXp();

        ArrayList<Entry> xpcY = new ArrayList<>();
        for (int i = 0; i < progressDTO.size(); i++) {
            xpcY.add(new Entry(i + 1, xpCumulative.get(i)));
        }

        ArrayList<Entry> xpY = new ArrayList<>();
        for (int i = 0; i < progressDTO.size(); i++) {
            xpY.add(new Entry(i + 1, xp.get(i)));
        }


        /*LineDataSet xpcDataSet = new LineDataSet(xpcY, "XPC");
        xpcDataSet.setFillAlpha(110);
        xpcDataSet.setColor(Color.BLUE);*/

        LineDataSet xpDataSet = new LineDataSet(xpY, "XP");
        xpDataSet.setFillAlpha(110);
        xpDataSet.setColor(Color.BLUE);

        ArrayList<ILineDataSet> xpcDataSets = new ArrayList<>();
        //xpcDataSets.add(xpcDataSet);
        xpcDataSets.add(xpDataSet);
        lineChartDataXP = new LineData(xpcDataSets);
        chartXP.setData(lineChartDataXP);
        chartXP.getDescription().setEnabled(false);
        chartXP.getXAxis().setPosition(XAxis.XAxisPosition.BOTTOM);
        chartXP.getAxisLeft().setAxisMinimum(0f);
        chartXP.getAxisRight().setEnabled(false);
        chartXP.getAxisLeft().setGranularity(100f);
        chartXP.getXAxis().setGranularity(1f);
        chartXP.getAxisLeft().setAxisMaximum((float) Math.ceil(lineChartDataXP.getYMax() / 100) * 100);
    }

    private void setGraphForAccuracy() {
        ArrayList<Double> accuracyCumulativeList, individualDayAccuracyList;
        accuracyCumulativeList = getCumulativeAccuracy();
        individualDayAccuracyList = getIndividualDayAccuracy();

        ArrayList<Entry> accuracyCumulativeYValues = new ArrayList<>();
        for (int i = 0; i < progressDTO.size(); i++) {
            accuracyCumulativeYValues.add(new Entry(i + 1, Float.parseFloat(String.valueOf(accuracyCumulativeList.get(i)))));
        }

        ArrayList<Entry> individualDayAccuracyYValues = new ArrayList<>();
        for (int i = 0; i < progressDTO.size(); i++) {
            individualDayAccuracyYValues.add(new Entry(i + 1, Float.parseFloat(String.valueOf(individualDayAccuracyList.get(i)))));
        }

        LineDataSet accuracyCumulativeDataSet = new LineDataSet(accuracyCumulativeYValues, "ACC");
        accuracyCumulativeDataSet.setFillAlpha(110);
        accuracyCumulativeDataSet.setColor(Color.BLUE);

        LineDataSet individualDayAccuracyDataSet = new LineDataSet(individualDayAccuracyYValues, "AC");
        individualDayAccuracyDataSet.setFillAlpha(110);
        individualDayAccuracyDataSet.setColor(Color.RED);

        ArrayList<ILineDataSet> accuracyDataSets = new ArrayList<>();
        accuracyDataSets.add(accuracyCumulativeDataSet);
        accuracyDataSets.add(individualDayAccuracyDataSet);

        lineChartDataAccuracy = new LineData(accuracyDataSets);
        chartAccuracy.setData(lineChartDataAccuracy);
        chartAccuracy.getDescription().setEnabled(false);
        chartAccuracy.getXAxis().setPosition(XAxis.XAxisPosition.BOTTOM);
        chartAccuracy.getAxisLeft().setAxisMinimum(0f);
        chartAccuracy.getAxisRight().setEnabled(false);
        chartAccuracy.getAxisLeft().setGranularity(20f);
        chartAccuracy.getXAxis().setGranularity(1f);
        chartAccuracy.getAxisLeft().setAxisMaximum((float) Math.ceil(lineChartDataAccuracy.getYMax() / 20) * 20);
    }

    private void setGraphForAttempts() {
        ArrayList<Integer> individualDayQuizAttemptsList, individualDayFailedAttemptsList;

        individualDayQuizAttemptsList = getIndividualDayQuizAttemptsList();
        individualDayFailedAttemptsList = getIndividualDayFailedAttemptsList();

        ArrayList<BarEntry> individualDayQuizAttemptsYValues = new ArrayList<>();
        for (int i = 0; i < progressDTO.size(); i++) {
            individualDayQuizAttemptsYValues.add(new BarEntry(i + 1, individualDayQuizAttemptsList.get(i)));
        }

        ArrayList<BarEntry> individualDayFailedAttemptsYValues = new ArrayList<>();
        for (int i = 0; i < progressDTO.size(); i++) {
            individualDayFailedAttemptsYValues.add(new BarEntry(i + 1, individualDayFailedAttemptsList.get(i)));
        }


        BarDataSet individualDayQuizAttemptsDataSet = new BarDataSet(individualDayQuizAttemptsYValues, "QA");
        individualDayQuizAttemptsDataSet.setColor(Color.CYAN);

        BarDataSet individualDayFailedAttemptsDataSet = new BarDataSet(individualDayFailedAttemptsYValues, "QF");
        individualDayFailedAttemptsDataSet.setColor(Color.RED);

        ArrayList<IBarDataSet> attemptsDataSets = new ArrayList<>();
        attemptsDataSets.add(individualDayQuizAttemptsDataSet);
        attemptsDataSets.add(individualDayFailedAttemptsDataSet);

        barChartDataAttempts = new BarData(attemptsDataSets);
        barChartDataAttempts.setBarWidth(0.8f);
        chartAttempts.setData(barChartDataAttempts);
        chartAttempts.getDescription().setEnabled(false);
        chartAttempts.getXAxis().setPosition(XAxis.XAxisPosition.BOTTOM);
        chartAttempts.getAxisLeft().setAxisMinimum(0f);
        chartAttempts.getAxisRight().setEnabled(false);
        chartAttempts.getAxisLeft().setGranularity(10f);
        chartAttempts.getXAxis().setGranularity(1f);
        chartAttempts.getAxisLeft().setAxisMaximum((float) Math.ceil(barChartDataAttempts.getYMax() / 10) * 10);
    }

    private void setGraphForCompletion() {
        ArrayList<Double> completionPercentageCumulativeList, individualDayCompletionPercentageList;
        completionPercentageCumulativeList = getCompletionPercentageCumulativeList();
        individualDayCompletionPercentageList = getIndividualDayCompletionPercentageList();

        ArrayList<Entry> completionPercentageCumulativeYValues = new ArrayList<>();
        for (int i = 0; i < progressDTO.size(); i++) {
            completionPercentageCumulativeYValues.add(new Entry(i + 1, Float.parseFloat(String.valueOf(completionPercentageCumulativeList.get(i)))));
        }

        ArrayList<Entry> individualDayCompletionPercentageYValues = new ArrayList<>();
        for (int i = 0; i < progressDTO.size(); i++) {
            individualDayCompletionPercentageYValues.add(new Entry(i + 1, Float.parseFloat(String.valueOf(individualDayCompletionPercentageList.get(i)))));
        }

        LineDataSet completionPercentageCumulativeDataSet = new LineDataSet(completionPercentageCumulativeYValues, "CPC");
        completionPercentageCumulativeDataSet.setFillAlpha(110);
        completionPercentageCumulativeDataSet.setColor(Color.GREEN);

        LineDataSet individualDayCompletionPercentageDataSet = new LineDataSet(individualDayCompletionPercentageYValues, "CP");
        individualDayCompletionPercentageDataSet.setFillAlpha(110);

        ArrayList<ILineDataSet> completionPercentageCumulativeDataSets = new ArrayList<>();
        completionPercentageCumulativeDataSets.add(completionPercentageCumulativeDataSet);
        completionPercentageCumulativeDataSets.add(individualDayCompletionPercentageDataSet);
        lineChartDataCompletionPercentage = new LineData(completionPercentageCumulativeDataSets);
        chartCompletion.setData(lineChartDataCompletionPercentage);
        chartCompletion.getDescription().setEnabled(false);
        chartCompletion.getXAxis().setPosition(XAxis.XAxisPosition.BOTTOM);
        chartCompletion.getAxisLeft().setAxisMinimum(0f);
        chartCompletion.getAxisRight().setEnabled(false);
        chartCompletion.getAxisLeft().setGranularity(20f);
        chartCompletion.getXAxis().setGranularity(1f);
        chartCompletion.getAxisLeft().setAxisMaximum((float) Math.ceil(lineChartDataCompletionPercentage.getYMax() / 20) * 20);
    }

    private ArrayList<Double> getTimeSpent() {
        ArrayList<Double> time = new ArrayList<>();
        int l = progressDTO.size();
        for (int i = 0; i < l; i++) {
            time.add((double) progressDTO.get(i).getTimeDiff() / 60);
        }
        return time;
    }

    private ArrayList<Integer> getXpCumulative() {
        ArrayList<Integer> xpC = new ArrayList<>();
        int l = progressDTO.size();
        for (int i = 0; i < l; i++) {
            xpC.add(progressDTO.get(i).getExperienceGained());
        }
        return xpC;
    }

    private ArrayList<Integer> getIndividualDayFailedAttemptsList() {
        ArrayList<Integer> individualDayFailedAttemptsList = new ArrayList<>();
        int l = progressDTO.size();
        individualDayFailedAttemptsList.add(progressDTO.get(0).getFailedAttempts());
        for (int i = 1; i < l; i++) {
            individualDayFailedAttemptsList.add(progressDTO.get(i).getFailedAttempts() - progressDTO.get(i - 1).getFailedAttempts());
        }
        return individualDayFailedAttemptsList;
    }

    private ArrayList<Integer> getIndividualDayQuizAttemptsList() {
        ArrayList<Integer> individualDayQuizAttemptsList = new ArrayList<>();
        int l = progressDTO.size();
        individualDayQuizAttemptsList.add(progressDTO.get(0).getQuizAttempts());
        for (int i = 1; i < l; i++) {
            individualDayQuizAttemptsList.add(progressDTO.get(i).getQuizAttempts() - progressDTO.get(i - 1).getQuizAttempts());
        }
        return individualDayQuizAttemptsList;
    }

    private ArrayList<Double> getIndividualDayCompletionPercentageList() {
        ArrayList<Double> individualDayCompletionPercentageList = new ArrayList<>();
        int l = progressDTO.size();
        individualDayCompletionPercentageList.add(progressDTO.get(0).getCompletionPercentage());
        for (int i = 1; i < l; i++) {
            individualDayCompletionPercentageList.add(progressDTO.get(i).getCompletionPercentage() - progressDTO.get(i - 1).getCompletionPercentage());
        }
        return individualDayCompletionPercentageList;
    }

    private ArrayList<Double> getCompletionPercentageCumulativeList() {
        ArrayList<Double> CompletionPercentageCumulativeList = new ArrayList<>();
        int l = progressDTO.size();
        for (int i = 0; i < l; i++) {
            CompletionPercentageCumulativeList.add(progressDTO.get(i).getCompletionPercentage());
        }
        return CompletionPercentageCumulativeList;
    }

    private ArrayList<Integer> getXp() {
        ArrayList<Integer> xp = new ArrayList<>();
        int l = progressDTO.size();
        xp.add(progressDTO.get(0).getExperienceGained());
        for (int i = 1; i < l; i++) {
            xp.add(progressDTO.get(i).getExperienceGained() - progressDTO.get(i - 1).getExperienceGained());
        }
        return xp;
    }

    private ArrayList<String> getDates() {
        ArrayList<String> dates = new ArrayList<>();
        int l = progressDTO.size();
        for (int i = 0; i < l; i++) {
            dates.add(progressDTO.get(i).getStartDate());
        }
        return dates;
    }

    private ArrayList<Double> getIndividualDayAccuracy() {
        ArrayList<Double> individualDayAccuracyList = new ArrayList<>();
        int l = progressDTO.size();
        double questionsAttempted, wronglyAnswered;
        questionsAttempted = progressDTO.get(0).getQuestionAttempts();
        wronglyAnswered = progressDTO.get(0).getWronglyAnswered();
        if (questionsAttempted == 0)
            individualDayAccuracyList.add(0.0);
        else
            individualDayAccuracyList.add(((questionsAttempted - wronglyAnswered) * 100) / questionsAttempted);
        for (int i = 1; i < l; i++) {
            questionsAttempted = progressDTO.get(i).getQuestionAttempts() - progressDTO.get(i - 1).getQuestionAttempts();
            wronglyAnswered = progressDTO.get(i).getWronglyAnswered() - progressDTO.get(i - 1).getWronglyAnswered();
            if (questionsAttempted == 0)
                individualDayAccuracyList.add(0.0);
            else
                individualDayAccuracyList.add(((questionsAttempted - wronglyAnswered) * 100) / questionsAttempted);
        }
        return individualDayAccuracyList;
    }

    private ArrayList<Double> getCumulativeAccuracy() {
        ArrayList<Double> cumulativeAccuracyList = new ArrayList<>();
        int l = progressDTO.size();
        for (int i = 0; i < l; i++) {
            if (progressDTO.get(i).getQuestionAttempts() == 0)
                cumulativeAccuracyList.add(0.0);
            else
                cumulativeAccuracyList.add(((progressDTO.get(i).getQuestionAttempts() - progressDTO.get(i).getWronglyAnswered()) * 100) / (double) progressDTO.get(i).getQuestionAttempts());
        }
        return cumulativeAccuracyList;
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
    protected void onResume() {
        super.onResume();
        Date startDate = new Date();
        appUsage.setDateTimeActivityStart(startDate);
    }

    @Override
    protected void onPause() {
        super.onPause();
        DateFormat sdfDate = new SimpleDateFormat("dd/MM/yyyy");
        DateFormat sdfTime = new SimpleDateFormat("HH:mm:ss");
        Date endDate = new Date();
        Date startDate;
        startDate = appUsage.getDateTimeActivityStart();
        long diff = appUsage.getTimeDifference(startDate, endDate);
        if (diff > 5) {
            Log.e("On Stop - ", "Stats Activity");
            new DaysStreakResolver().resolveDaysStreak(this.getApplicationContext());
            if (sdfDate.format(startDate).equalsIgnoreCase(sdfDate.format(endDate))) {
                dbAdapter.addProgressEntry(sdfDate.format(startDate), sdfTime.format(startDate), sdfDate.format(endDate), sdfTime.format(endDate), diff, xpPoints, "Statistics");
            } else {
                long postDiff = 0;
                try {
                    postDiff = appUsage.getTimeDifference(sdfDate.parse(sdfDate.format(endDate) + " 00:00:00"), endDate);
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                dbAdapter.addProgressEntry(sdfDate.format(startDate), sdfTime.format(startDate), sdfDate.format(endDate), "23:59:59", diff - postDiff, xpPoints, "Statistics");
                dbAdapter.addProgressEntry(sdfDate.format(endDate), "00:00:00", sdfDate.format(endDate), sdfTime.format(endDate), postDiff, xpPoints, "Statistics");
            }
        }
    }
}