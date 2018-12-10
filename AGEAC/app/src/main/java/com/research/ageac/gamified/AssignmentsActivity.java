package com.research.ageac.gamified;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.research.ageac.gamified.quizlibrary.QuizCounts;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class AssignmentsActivity extends AppCompatActivity {

    int stagesCompleted, timersCollected, daysStreak, extraLivesCollected, assignmentsCompleted;
    int stagesCompletedTarget, timersCollectedTarget, daysStreakTarget, extraLivesCollectedTarget, assignmentsCompletedTarget;
    int stagesCompletedTargetCompletionRewardAmount, timersCollectedTargetCompletionRewardAmount, daysStreakTargetCompletionRewardAmount, extraLivesCollectedTargetCompletionRewardAmount, assignmentsCompletedTargetCompletionRewardAmount;

    TextView stagesCompletedTargetCompletionRewardAmountTxtv, timersCollectedTargetCompletionRewardAmountTxtv, daysStreakTargetCompletionRewardAmountTxtv, extraLivesCollectedTargetCompletionRewardAmountTxtv, assignmentsCompletedTargetCompletionRewardAmountTxtv;
    Button stagesCompletedTargetCompletionRewardClaimBtn, timersCollectedTargetCompletionRewardClaimBtn, daysStreakTargetCompletionRewardClaimBtn, extraLivesCollectedTargetCompletionRewardClaimBtn, assignmentsCompletedTargetCompletionRewardClaimBtn;
    ProgressBar stagesCompletedTargetProgressBar, timersCollectedTargetProgressBar, daysStreakTargetProgressBar, extraLivesCollectedTargetProgressBar, assignmentsCompletedTargetProgressBar;
    TextView coinsTxtv, vNtstagesCompleted, vNttimersCollected, vNtdaysStreak, vNtextraLivesCollected, vNtassignmentsCompleted;
    View stagesCard;

    int coins, maxStages;
    int bronzeTimersCollected, silverTimersCollected, goldTimersCollected, xpPoints;

    boolean allStagesAssignmentsCompleted;

    ImageView backButton;

    DBAdapter dbAdapter;

    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;

    AppUsage appUsage;

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
        setContentView(R.layout.activity_assignments);

        final View decorView = getWindow().getDecorView();
        decorView.setOnSystemUiVisibilityChangeListener(new View.OnSystemUiVisibilityChangeListener() {

            @Override
            public void onSystemUiVisibilityChange(int visibility) {
                if ((visibility & View.SYSTEM_UI_FLAG_FULLSCREEN) == 0) {
                    decorView.setSystemUiVisibility(flags);
                }
            }
        });

        sharedPreferences = getSharedPreferences("Extras", MODE_PRIVATE);
        editor = sharedPreferences.edit();

        appUsage = new AppUsage();

        stagesCard = findViewById(R.id.cardview_assignments_stages);

        coins = Integer.parseInt(getIntent().getExtras().getString(BackUpDBKeys.BACKUPDB_KEY_COINS));
        extraLivesCollected = Integer.parseInt(getIntent().getExtras().getString(BackUpDBKeys.BACKUPDB_KEY_EXTRA_LIVES_COLLECTED));
        bronzeTimersCollected = Integer.parseInt(getIntent().getExtras().getString(BackUpDBKeys.BACKUPDB_KEY_BRONZE_TIMERS_COLLECTED));
        silverTimersCollected = Integer.parseInt(getIntent().getExtras().getString(BackUpDBKeys.BACKUPDB_KEY_SILVER_TIMERS_COLLECTED));
        goldTimersCollected = Integer.parseInt(getIntent().getExtras().getString(BackUpDBKeys.BACKUPDB_KEY_GOLD_TIMERS_COLLECTED));
        xpPoints = Integer.parseInt(getIntent().getExtras().getString(BackUpDBKeys.BACKUPDB_KEY_XP_TOTAL_POINTS));

        timersCollected = bronzeTimersCollected + silverTimersCollected + goldTimersCollected;

        coinsTxtv = findViewById(R.id.txtv_assignments_coins);
        coinsTxtv.setText(String.valueOf(coins));

        backButton = findViewById(R.id.assignments_back_btn);
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        stagesCompletedTargetCompletionRewardAmountTxtv = findViewById(R.id.stage_completion_next_reward_amount);
        timersCollectedTargetCompletionRewardAmountTxtv = findViewById(R.id.timers_collected_next_reward_amount);
        daysStreakTargetCompletionRewardAmountTxtv = findViewById(R.id.days_streak_next_reward_amount);
        extraLivesCollectedTargetCompletionRewardAmountTxtv = findViewById(R.id.extra_lives_collected_next_reward_amount);
        assignmentsCompletedTargetCompletionRewardAmountTxtv = findViewById(R.id.assignments_completion_next_reward_amount);

        stagesCompletedTargetCompletionRewardClaimBtn = findViewById(R.id.btn_claim_stage_completion_reward);
        timersCollectedTargetCompletionRewardClaimBtn = findViewById(R.id.btn_claim_timers_collected_reward);
        daysStreakTargetCompletionRewardClaimBtn = findViewById(R.id.btn_claim_days_streak_reward);
        extraLivesCollectedTargetCompletionRewardClaimBtn = findViewById(R.id.btn_claim_extra_lives_collected_reward);
        assignmentsCompletedTargetCompletionRewardClaimBtn = findViewById(R.id.btn_claim_assignments_completion_reward);

        stagesCompletedTargetProgressBar = findViewById(R.id.stage_completed_progress);
        timersCollectedTargetProgressBar = findViewById(R.id.timers_collected_progress);
        daysStreakTargetProgressBar = findViewById(R.id.days_streak_progress);
        extraLivesCollectedTargetProgressBar = findViewById(R.id.extra_lives_collected_progress);
        assignmentsCompletedTargetProgressBar = findViewById(R.id.assignments_completed_progress);

        vNtstagesCompleted = findViewById(R.id.txtv_assignments_stage_completion_vNt);
        vNttimersCollected = findViewById(R.id.txtv_assignments_timers_collected_vNt);
        vNtdaysStreak = findViewById(R.id.txtv_assignments_days_streak_vNt);
        vNtextraLivesCollected = findViewById(R.id.txtv_assignments_extra_lives_collected_vNt);
        vNtassignmentsCompleted = findViewById(R.id.txtv_assignments_assignments_completion_vNt);

        dbAdapter = new DBAdapter(AssignmentsActivity.this);
        dbAdapter.addLogEntry("Assignments","OnCreate");

        stagesCompletedTargetCompletionRewardAmount = sharedPreferences.getInt(BackUpDBKeys.BACKUPDB_KEY_STAGES_REWARD, 0);
        timersCollectedTargetCompletionRewardAmount = sharedPreferences.getInt(BackUpDBKeys.BACKUPDB_KEY_TIMERS_REWARD, 0);
        daysStreakTargetCompletionRewardAmount = sharedPreferences.getInt(BackUpDBKeys.BACKUPDB_KEY_DAYS_REWARD, 0);
        extraLivesCollectedTargetCompletionRewardAmount = sharedPreferences.getInt(BackUpDBKeys.BACKUPDB_KEY_EXTRA_LIVES_REWARD, 0);
        assignmentsCompletedTargetCompletionRewardAmount = sharedPreferences.getInt(BackUpDBKeys.BACKUPDB_KEY_ASSIGNMENTS_REWARD, 0);

        stagesCompletedTargetCompletionRewardAmountTxtv.setText(String.valueOf(stagesCompletedTargetCompletionRewardAmount));
        timersCollectedTargetCompletionRewardAmountTxtv.setText(String.valueOf(timersCollectedTargetCompletionRewardAmount));
        daysStreakTargetCompletionRewardAmountTxtv.setText(String.valueOf(daysStreakTargetCompletionRewardAmount));
        extraLivesCollectedTargetCompletionRewardAmountTxtv.setText(String.valueOf(extraLivesCollectedTargetCompletionRewardAmount));
        assignmentsCompletedTargetCompletionRewardAmountTxtv.setText(String.valueOf(assignmentsCompletedTargetCompletionRewardAmount));

        stagesCompleted = dbAdapter.getCompletedStagesNumber();
        daysStreak = sharedPreferences.getInt(BackUpDBKeys.BACKUPDB_KEY_DAYS_STREAK, 0);
        assignmentsCompleted = sharedPreferences.getInt(BackUpDBKeys.BACKUPDB_KEY_ASSIGNMENTS_COMPLETED, 0);

        stagesCompletedTarget = sharedPreferences.getInt(BackUpDBKeys.BACKUPDB_KEY_STAGES_TARGET, 0);
        timersCollectedTarget = sharedPreferences.getInt(BackUpDBKeys.BACKUPDB_KEY_TIMERS_TARGET, 0);
        daysStreakTarget = sharedPreferences.getInt(BackUpDBKeys.BACKUPDB_KEY_DAYS_TARGET, 0);
        extraLivesCollectedTarget = sharedPreferences.getInt(BackUpDBKeys.BACKUPDB_KEY_EXTRA_LIVES_TARGET, 0);
        assignmentsCompletedTarget = sharedPreferences.getInt(BackUpDBKeys.BACKUPDB_KEY_ASSIGNMENTS_TARGET, 0);

        vNtstagesCompleted.setText(String.valueOf(stagesCompleted).concat(" / ").concat(String.valueOf(stagesCompletedTarget)));
        vNttimersCollected.setText(String.valueOf(timersCollected).concat(" / ").concat(String.valueOf(timersCollectedTarget)));
        vNtdaysStreak.setText(String.valueOf(daysStreak).concat(" / ").concat(String.valueOf(daysStreakTarget)));
        vNtextraLivesCollected.setText(String.valueOf(extraLivesCollected).concat(" / ").concat(String.valueOf(extraLivesCollectedTarget)));
        vNtassignmentsCompleted.setText(String.valueOf(assignmentsCompleted).concat(" / ").concat(String.valueOf(assignmentsCompletedTarget)));

        stagesCompletedTargetProgressBar.setProgress((stagesCompleted * 100) / stagesCompletedTarget);
        timersCollectedTargetProgressBar.setProgress((timersCollected * 100) / timersCollectedTarget);
        daysStreakTargetProgressBar.setProgress((daysStreak * 100) / daysStreakTarget);
        extraLivesCollectedTargetProgressBar.setProgress((extraLivesCollected * 100) / extraLivesCollectedTarget);
        assignmentsCompletedTargetProgressBar.setProgress((assignmentsCompleted * 100) / assignmentsCompletedTarget);

        allStagesAssignmentsCompleted = sharedPreferences.getBoolean(BackUpDBKeys.BACKUPDB_KEY_ALL_STAGES_ASSIGNMENTS_COMPLETED, false);

        if (allStagesAssignmentsCompleted) {
            stagesCard.setEnabled(false);
            stagesCard.setVisibility(View.GONE);
        }

        if (stagesCompleted < stagesCompletedTarget) {
            stagesCompletedTargetCompletionRewardClaimBtn.setEnabled(false);
            stagesCompletedTargetCompletionRewardClaimBtn.setClickable(false);
            stagesCompletedTargetCompletionRewardClaimBtn.setVisibility(View.INVISIBLE);
        }

        if (timersCollected < timersCollectedTarget) {
            timersCollectedTargetCompletionRewardClaimBtn.setEnabled(false);
            timersCollectedTargetCompletionRewardClaimBtn.setClickable(false);
            timersCollectedTargetCompletionRewardClaimBtn.setVisibility(View.INVISIBLE);
        }

        if (daysStreak < daysStreakTarget) {
            daysStreakTargetCompletionRewardClaimBtn.setEnabled(false);
            daysStreakTargetCompletionRewardClaimBtn.setClickable(false);
            daysStreakTargetCompletionRewardClaimBtn.setVisibility(View.INVISIBLE);
        }

        if (extraLivesCollected < extraLivesCollectedTarget) {
            extraLivesCollectedTargetCompletionRewardClaimBtn.setEnabled(false);
            extraLivesCollectedTargetCompletionRewardClaimBtn.setClickable(false);
            extraLivesCollectedTargetCompletionRewardClaimBtn.setVisibility(View.INVISIBLE);
        }

        if (assignmentsCompleted < assignmentsCompletedTarget) {
            assignmentsCompletedTargetCompletionRewardClaimBtn.setEnabled(false);
            assignmentsCompletedTargetCompletionRewardClaimBtn.setClickable(false);
            assignmentsCompletedTargetCompletionRewardClaimBtn.setVisibility(View.INVISIBLE);
        }

        maxStages = QuizCounts.totalQuizzes;

        stagesCompletedTargetCompletionRewardClaimBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dbAdapter.addLogEntry("Assignments","Claimed Stage Target Reward");
                resolveAssignmentsClaimBtnVisibility();
                if (!allStagesAssignmentsCompleted && stagesCompleted == maxStages && stagesCompleted == stagesCompletedTarget) {
                    coins += stagesCompletedTargetCompletionRewardAmount;
                    coinsTxtv.setText(String.valueOf(coins));
                    editor.putBoolean(BackUpDBKeys.BACKUPDB_KEY_ALL_STAGES_ASSIGNMENTS_COMPLETED, true);
                    editor.putInt(BackUpDBKeys.BACKUPDB_KEY_COINS, coins);
                    editor.putInt(BackUpDBKeys.BACKUPDB_KEY_ASSIGNMENTS_COMPLETED, assignmentsCompleted);
                    editor.commit();
                    stagesCard.setEnabled(false);
                    stagesCard.setVisibility(View.GONE);
                } else {
                    if (stagesCompleted >= stagesCompletedTarget) {
                        stagesCompletedTarget *= 2;
                        if (stagesCompletedTarget > maxStages)
                            stagesCompletedTarget = maxStages;
                        coins += stagesCompletedTargetCompletionRewardAmount;
                        stagesCompletedTargetCompletionRewardAmount *= 2;
                        stagesCompletedTargetCompletionRewardAmountTxtv.setText(String.valueOf(stagesCompletedTargetCompletionRewardAmount));
                    }
                    editor.putInt(BackUpDBKeys.BACKUPDB_KEY_COINS, coins);
                    editor.putInt(BackUpDBKeys.BACKUPDB_KEY_STAGES_TARGET, stagesCompletedTarget);
                    editor.putInt(BackUpDBKeys.BACKUPDB_KEY_STAGES_REWARD, stagesCompletedTargetCompletionRewardAmount);
                    editor.putInt(BackUpDBKeys.BACKUPDB_KEY_ASSIGNMENTS_COMPLETED, assignmentsCompleted);
                    editor.commit();
                    vNtstagesCompleted.setText(String.valueOf(stagesCompleted).concat(" / ").concat(String.valueOf(stagesCompletedTarget)));
                    stagesCompletedTargetProgressBar.setProgress((stagesCompleted * 100) / stagesCompletedTarget);
                    coinsTxtv.setText(String.valueOf(coins));
                    if (stagesCompleted < stagesCompletedTarget) {
                        stagesCompletedTargetCompletionRewardClaimBtn.setEnabled(false);
                        stagesCompletedTargetCompletionRewardClaimBtn.setClickable(false);
                        stagesCompletedTargetCompletionRewardClaimBtn.setVisibility(View.INVISIBLE);
                    }
                }
            }
        });


        timersCollectedTargetCompletionRewardClaimBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dbAdapter.addLogEntry("Assignments","Claimed Timers Target Reward");
                resolveAssignmentsClaimBtnVisibility();
                if (timersCollected >= timersCollectedTarget) {
                    timersCollectedTarget *= 2;
                    coins += timersCollectedTargetCompletionRewardAmount;
                    timersCollectedTargetCompletionRewardAmount *= 2;
                    timersCollectedTargetCompletionRewardAmountTxtv.setText(String.valueOf(timersCollectedTargetCompletionRewardAmount));
                }
                editor.putInt(BackUpDBKeys.BACKUPDB_KEY_COINS, coins);
                editor.putInt(BackUpDBKeys.BACKUPDB_KEY_TIMERS_TARGET, timersCollectedTarget);
                editor.putInt(BackUpDBKeys.BACKUPDB_KEY_TIMERS_REWARD, timersCollectedTargetCompletionRewardAmount);
                editor.putInt(BackUpDBKeys.BACKUPDB_KEY_ASSIGNMENTS_COMPLETED, assignmentsCompleted);
                editor.commit();
                vNttimersCollected.setText(String.valueOf(timersCollected).concat(" / ").concat(String.valueOf(timersCollectedTarget)));
                timersCollectedTargetProgressBar.setProgress((timersCollected * 100) / timersCollectedTarget);
                coinsTxtv.setText(String.valueOf(coins));
                if (timersCollected < timersCollectedTarget) {
                    timersCollectedTargetCompletionRewardClaimBtn.setEnabled(false);
                    timersCollectedTargetCompletionRewardClaimBtn.setClickable(false);
                    timersCollectedTargetCompletionRewardClaimBtn.setVisibility(View.INVISIBLE);
                }
            }
        });

        daysStreakTargetCompletionRewardClaimBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dbAdapter.addLogEntry("Assignments","Claimed Days Streak Target Reward");
                resolveAssignmentsClaimBtnVisibility();
                if (daysStreak >= daysStreakTarget) {
                    daysStreakTarget *= 2;
                    coins += daysStreakTargetCompletionRewardAmount;
                    daysStreakTargetCompletionRewardAmount *= 2;
                    daysStreakTargetCompletionRewardAmountTxtv.setText(String.valueOf(daysStreakTargetCompletionRewardAmount));
                }
                editor.putInt(BackUpDBKeys.BACKUPDB_KEY_COINS, coins);
                editor.putInt(BackUpDBKeys.BACKUPDB_KEY_DAYS_TARGET, daysStreakTarget);
                editor.putInt(BackUpDBKeys.BACKUPDB_KEY_DAYS_REWARD, daysStreakTargetCompletionRewardAmount);
                editor.putInt(BackUpDBKeys.BACKUPDB_KEY_ASSIGNMENTS_COMPLETED, assignmentsCompleted);
                editor.commit();
                vNtdaysStreak.setText(String.valueOf(daysStreak).concat(" / ").concat(String.valueOf(daysStreakTarget)));
                daysStreakTargetProgressBar.setProgress((daysStreak * 100) / daysStreakTarget);
                coinsTxtv.setText(String.valueOf(coins));
                if (daysStreak < daysStreakTarget) {
                    daysStreakTargetCompletionRewardClaimBtn.setEnabled(false);
                    daysStreakTargetCompletionRewardClaimBtn.setClickable(false);
                    daysStreakTargetCompletionRewardClaimBtn.setVisibility(View.INVISIBLE);
                }
            }
        });

        extraLivesCollectedTargetCompletionRewardClaimBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dbAdapter.addLogEntry("Assignments","Claimed Extra Lives Target Reward");
                resolveAssignmentsClaimBtnVisibility();
                if (extraLivesCollected >= extraLivesCollectedTarget) {
                    extraLivesCollectedTarget *= 2;
                    coins += extraLivesCollectedTargetCompletionRewardAmount;
                    extraLivesCollectedTargetCompletionRewardAmount *= 2;
                    extraLivesCollectedTargetCompletionRewardAmountTxtv.setText(String.valueOf(extraLivesCollectedTargetCompletionRewardAmount));
                }
                editor.putInt(BackUpDBKeys.BACKUPDB_KEY_COINS, coins);
                editor.putInt(BackUpDBKeys.BACKUPDB_KEY_EXTRA_LIVES_TARGET, extraLivesCollectedTarget);
                editor.putInt(BackUpDBKeys.BACKUPDB_KEY_EXTRA_LIVES_REWARD, extraLivesCollectedTargetCompletionRewardAmount);
                editor.putInt(BackUpDBKeys.BACKUPDB_KEY_ASSIGNMENTS_COMPLETED, assignmentsCompleted);
                editor.commit();
                vNtextraLivesCollected.setText(String.valueOf(extraLivesCollected).concat(" / ").concat(String.valueOf(extraLivesCollectedTarget)));
                extraLivesCollectedTargetProgressBar.setProgress((extraLivesCollected * 100) / extraLivesCollectedTarget);
                coinsTxtv.setText(String.valueOf(coins));
                if (extraLivesCollected < extraLivesCollectedTarget) {
                    extraLivesCollectedTargetCompletionRewardClaimBtn.setEnabled(false);
                    extraLivesCollectedTargetCompletionRewardClaimBtn.setClickable(false);
                    extraLivesCollectedTargetCompletionRewardClaimBtn.setVisibility(View.INVISIBLE);
                }
            }
        });

        assignmentsCompletedTargetCompletionRewardClaimBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dbAdapter.addLogEntry("Assignments","Claimed Assignments Target Reward");
                if (assignmentsCompleted >= assignmentsCompletedTarget) {
                    assignmentsCompletedTarget *= 2;
                    coins += assignmentsCompletedTargetCompletionRewardAmount;
                    assignmentsCompletedTargetCompletionRewardAmount *= 2;
                    assignmentsCompletedTargetCompletionRewardAmountTxtv.setText(String.valueOf(assignmentsCompletedTargetCompletionRewardAmount));
                }
                editor.putInt(BackUpDBKeys.BACKUPDB_KEY_COINS, coins);
                editor.putInt(BackUpDBKeys.BACKUPDB_KEY_ASSIGNMENTS_TARGET, assignmentsCompletedTarget);
                editor.putInt(BackUpDBKeys.BACKUPDB_KEY_ASSIGNMENTS_REWARD, assignmentsCompletedTargetCompletionRewardAmount);
                editor.commit();
                vNtassignmentsCompleted.setText(String.valueOf(assignmentsCompleted).concat(" / ").concat(String.valueOf(assignmentsCompletedTarget)));
                assignmentsCompletedTargetProgressBar.setProgress((assignmentsCompleted * 100) / assignmentsCompletedTarget);
                coinsTxtv.setText(String.valueOf(coins));
                if (assignmentsCompleted < assignmentsCompletedTarget) {
                    assignmentsCompletedTargetCompletionRewardClaimBtn.setEnabled(false);
                    assignmentsCompletedTargetCompletionRewardClaimBtn.setClickable(false);
                    assignmentsCompletedTargetCompletionRewardClaimBtn.setVisibility(View.INVISIBLE);
                }
            }
        });

    }

    private void resolveAssignmentsClaimBtnVisibility() {
        ++assignmentsCompleted;
        vNtassignmentsCompleted.setText(String.valueOf(assignmentsCompleted).concat(" / ").concat(String.valueOf(assignmentsCompletedTarget)));
        assignmentsCompletedTargetProgressBar.setProgress((assignmentsCompleted * 100) / assignmentsCompletedTarget);
        if (assignmentsCompleted >= assignmentsCompletedTarget) {
            assignmentsCompletedTargetCompletionRewardClaimBtn.setEnabled(true);
            assignmentsCompletedTargetCompletionRewardClaimBtn.setClickable(true);
            assignmentsCompletedTargetCompletionRewardClaimBtn.setVisibility(View.VISIBLE);
        }
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
    public void onBackPressed() {
        Intent backIntent = new Intent();
        backIntent.putExtra(BackUpDBKeys.BACKUPDB_KEY_COINS, coins);
        setResult(RESULT_OK, backIntent);
        finish();
    }

    @Override
    protected void onResume() {
        super.onResume();
        Date startDate = new Date();
        appUsage.setDateTimeActivityStart(startDate);
    }

    @Override
    protected void onStop() {
        super.onStop();
        DateFormat sdfDate = new SimpleDateFormat("dd/MM/yyyy");
        DateFormat sdfTime = new SimpleDateFormat("HH:mm:ss");
        Date endDate = new Date();
        Date startDate;
        startDate = appUsage.getDateTimeActivityStart();
        long diff = appUsage.getTimeDifference(startDate, endDate);
        if (diff > 5) {
            Log.e("On Stop - ", "Assignments Activity");
            new DaysStreakResolver().resolveDaysStreak(this.getApplicationContext());
            if (sdfDate.format(startDate).equalsIgnoreCase(sdfDate.format(endDate))) {
                dbAdapter.addProgressEntry(sdfDate.format(startDate), sdfTime.format(startDate), sdfDate.format(endDate), sdfTime.format(endDate), diff, xpPoints, "Assignments");
            } else {
                long postDiff = 0;
                try {
                    postDiff = appUsage.getTimeDifference(sdfDate.parse(sdfDate.format(endDate) + " 00:00:00"), endDate);
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                dbAdapter.addProgressEntry(sdfDate.format(startDate), sdfTime.format(startDate), sdfDate.format(endDate), "23:59:59", diff - postDiff, xpPoints, "Assignments");
                dbAdapter.addProgressEntry(sdfDate.format(endDate), "00:00:00", sdfDate.format(endDate), sdfTime.format(endDate), postDiff, xpPoints, "Assignments");
            }
        }
    }
}
