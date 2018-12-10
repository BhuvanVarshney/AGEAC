package com.research.ageac.gamified;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;

public class LifeAndTimerGeneratorActivity extends AppCompatActivity {

    TextView bronzeTimerTimerTxtv, silverTimerTimerTxtv, goldTimerTimerTxtv, extraLifeTimerTxtv;
    Button bronzeTimerCollectBtn, silverTimerCollectBtn, goldTimerCollectBtn, extraLifeCollectBtn;

    TextView txtvBronzeTimersAlreadyAvailable, txtvSilverTimersAlreadyAvailable, txtvGoldTimersAlreadyAvailable, txtvExtraLivesAlreadyAvailable;

    ImageView backButton;

    BronzeTimerCounterClass bronzeTimerCounter;
    SilverTimerCounterClass silverTimerCounter;
    GoldTimerCounterClass goldTimerCounter;
    ExtraLifeCounterClass extraLifeCounter;

    long bronzeTimerTimeLeft, silverTimerTimeLeft, goldTimerTimeLeft, extraLifeTimeLeft;

    //testing
    //long bronzeTimerGenerationTime = 9000, silverTimerGenerationTime = 18000, goldTimerGenerationTime = 36000, extraLifeGenerationTime = 72000;

    //original
    long bronzeTimerGenerationTime = 900000, silverTimerGenerationTime = 1800000, goldTimerGenerationTime = 3600000, extraLifeGenerationTime = 7200000;

    Date bronzeTimerLastCollectDate, silverTimerLastCollectDate, goldTimerLastCollectDate, extraLifeLastCollectDate;

    int totalExtraLives = 0, totalBronzeTimers = 0, totalSilverTimers = 0, totalGoldTimers = 0, totalTimers = 0;
    int bronzeTimersCollected, silverTimersCollected, goldTimersCollected, extraLivesCollected;
    int xpPoints;

    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;

    DBAdapter dbAdapter;

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
        setContentView(R.layout.activity_life_and_timer_generator);

        final View decorView = getWindow().getDecorView();
        decorView.setOnSystemUiVisibilityChangeListener(new View.OnSystemUiVisibilityChangeListener() {

            @Override
            public void onSystemUiVisibilityChange(int visibility) {
                if ((visibility & View.SYSTEM_UI_FLAG_FULLSCREEN) == 0) {
                    decorView.setSystemUiVisibility(flags);
                }
            }
        });

        dbAdapter = new DBAdapter(LifeAndTimerGeneratorActivity.this);
        dbAdapter.addLogEntry("Regenerator","OnCreate");

        appUsage = new AppUsage();

        sharedPreferences = getSharedPreferences("Extras", MODE_PRIVATE);
        editor = sharedPreferences.edit();

        extraLivesCollected = Integer.parseInt(getIntent().getExtras().getString(BackUpDBKeys.BACKUPDB_KEY_EXTRA_LIVES_COLLECTED));
        totalExtraLives = Integer.parseInt(getIntent().getExtras().getString("total_extra_lives"));
        bronzeTimersCollected = Integer.parseInt(getIntent().getExtras().getString(BackUpDBKeys.BACKUPDB_KEY_BRONZE_TIMERS_COLLECTED));
        silverTimersCollected = Integer.parseInt(getIntent().getExtras().getString(BackUpDBKeys.BACKUPDB_KEY_SILVER_TIMERS_COLLECTED));
        goldTimersCollected = Integer.parseInt(getIntent().getExtras().getString(BackUpDBKeys.BACKUPDB_KEY_GOLD_TIMERS_COLLECTED));
        totalBronzeTimers = Integer.parseInt(getIntent().getExtras().getString("total_bronze_timers"));
        totalSilverTimers = Integer.parseInt(getIntent().getExtras().getString("total_silver_timers"));
        totalGoldTimers = Integer.parseInt(getIntent().getExtras().getString("total_gold_timers"));
        totalTimers = Integer.parseInt(getIntent().getExtras().getString("total_timers"));
        xpPoints = Integer.parseInt(getIntent().getExtras().getString(BackUpDBKeys.BACKUPDB_KEY_XP_TOTAL_POINTS));

        bronzeTimerTimerTxtv = findViewById(R.id.txtv_bronze_timer_time_left);
        silverTimerTimerTxtv = findViewById(R.id.txtv_silver_timer_time_left);
        goldTimerTimerTxtv = findViewById(R.id.txtv_gold_timer_time_left);
        extraLifeTimerTxtv = findViewById(R.id.txtv_extra_life_time_left);

        txtvBronzeTimersAlreadyAvailable = findViewById(R.id.txtv_generator_bronze_timer_already_available);
        txtvSilverTimersAlreadyAvailable = findViewById(R.id.txtv_generator_silver_timer_already_available);
        txtvGoldTimersAlreadyAvailable = findViewById(R.id.txtv_generator_gold_timer_already_available);
        txtvExtraLivesAlreadyAvailable = findViewById(R.id.txtv_generator_extra_life_already_available);

        txtvBronzeTimersAlreadyAvailable.setText(String.valueOf(totalBronzeTimers));
        txtvSilverTimersAlreadyAvailable.setText(String.valueOf(totalSilverTimers));
        txtvGoldTimersAlreadyAvailable.setText(String.valueOf(totalGoldTimers));
        txtvExtraLivesAlreadyAvailable.setText(String.valueOf(totalExtraLives));

        bronzeTimerCollectBtn = findViewById(R.id.btn_bronze_timer_collect);
        silverTimerCollectBtn = findViewById(R.id.btn_silver_timer_collect);
        goldTimerCollectBtn = findViewById(R.id.btn_gold_timer_collect);
        extraLifeCollectBtn = findViewById(R.id.btn_extra_life_collect);

        timerResolver();

        bronzeTimerCollectBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dbAdapter.addLogEntry("Regenerator","Collected Bronze Timer");
                ++bronzeTimersCollected;
                ++totalBronzeTimers;
                ++totalTimers;
                txtvBronzeTimersAlreadyAvailable.setText(String.valueOf(totalBronzeTimers));
                DateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
                Date current = new Date();
                editor.putString(BackUpDBKeys.BACKUPDB_KEY_LAST_DATE_TIME_BRONZE_TIMER, sdf.format(current));
                editor.putInt(BackUpDBKeys.BACKUPDB_KEY_BRONZE_TIMERS_COLLECTED, bronzeTimersCollected);
                editor.commit();
                bronzeTimerCollectBtn.setEnabled(false);
                bronzeTimerCollectBtn.setVisibility(View.INVISIBLE);
                bronzeTimerTimerTxtv.setVisibility(View.VISIBLE);
                bronzeTimerCounter = new BronzeTimerCounterClass(bronzeTimerGenerationTime, 1000);
                bronzeTimerCounter.start();
            }
        });

        silverTimerCollectBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dbAdapter.addLogEntry("Regenerator","Collected Silver Timer");
                ++silverTimersCollected;
                ++totalSilverTimers;
                ++totalTimers;
                txtvSilverTimersAlreadyAvailable.setText(String.valueOf(totalSilverTimers));
                DateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
                Date current = new Date();
                editor.putString(BackUpDBKeys.BACKUPDB_KEY_LAST_DATE_TIME_SILVER_TIMER, sdf.format(current));
                editor.putInt(BackUpDBKeys.BACKUPDB_KEY_SILVER_TIMERS_COLLECTED, silverTimersCollected);
                editor.commit();
                silverTimerCollectBtn.setEnabled(false);
                silverTimerCollectBtn.setVisibility(View.INVISIBLE);
                silverTimerTimerTxtv.setVisibility(View.VISIBLE);
                silverTimerCounter = new SilverTimerCounterClass(silverTimerGenerationTime, 1000);
                silverTimerCounter.start();
            }
        });

        goldTimerCollectBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dbAdapter.addLogEntry("Regenerator","Collected Gold Timer");
                ++goldTimersCollected;
                ++totalGoldTimers;
                ++totalTimers;
                txtvGoldTimersAlreadyAvailable.setText(String.valueOf(totalGoldTimers));
                DateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
                Date current = new Date();
                editor.putString(BackUpDBKeys.BACKUPDB_KEY_LAST_DATE_TIME_GOLD_TIMER, sdf.format(current));
                editor.putInt(BackUpDBKeys.BACKUPDB_KEY_GOLD_TIMERS_COLLECTED, goldTimersCollected);
                editor.commit();
                goldTimerCollectBtn.setEnabled(false);
                goldTimerCollectBtn.setVisibility(View.INVISIBLE);
                goldTimerTimerTxtv.setVisibility(View.VISIBLE);
                goldTimerCounter = new GoldTimerCounterClass(goldTimerGenerationTime, 1000);
                goldTimerCounter.start();
            }
        });

        extraLifeCollectBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dbAdapter.addLogEntry("Regenerator","Collected Extra Life");
                ++extraLivesCollected;
                ++totalExtraLives;
                txtvExtraLivesAlreadyAvailable.setText(String.valueOf(totalExtraLives));
                DateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
                Date current = new Date();
                editor.putString(BackUpDBKeys.BACKUPDB_KEY_LAST_DATE_TIME_EXTRA_LIFE, sdf.format(current));
                editor.putInt(BackUpDBKeys.BACKUPDB_KEY_EXTRA_LIVES_COLLECTED, extraLivesCollected);
                editor.commit();
                extraLifeCollectBtn.setEnabled(false);
                extraLifeCollectBtn.setVisibility(View.INVISIBLE);
                extraLifeTimerTxtv.setVisibility(View.VISIBLE);
                extraLifeCounter = new ExtraLifeCounterClass(extraLifeGenerationTime, 1000);
                extraLifeCounter.start();
            }
        });

        backButton = findViewById(R.id.generator_back_btn);
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
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


    public class BronzeTimerCounterClass extends CountDownTimer {

        public BronzeTimerCounterClass(long millisInFuture, long countDownInterval) {
            super(millisInFuture, countDownInterval);
        }

        @Override
        public void onTick(long millisUntilFinished) {

            long millis = millisUntilFinished;
            String hms = String.format("%02d:%02d:%02d", TimeUnit.MILLISECONDS.toHours(millis),
                    TimeUnit.MILLISECONDS.toMinutes(millis) - TimeUnit.HOURS.toMinutes(TimeUnit.MILLISECONDS.toHours(millis)),
                    TimeUnit.MILLISECONDS.toSeconds(millis) - TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes(millis)));
            bronzeTimerTimerTxtv.setText(hms);
        }

        @Override
        public void onFinish() {
            bronzeTimerCounter.cancel();
            bronzeTimerCollectBtn.setEnabled(true);
            bronzeTimerCollectBtn.setVisibility(View.VISIBLE);
            bronzeTimerTimerTxtv.setVisibility(View.INVISIBLE);
        }
    }

    public class SilverTimerCounterClass extends CountDownTimer {

        public SilverTimerCounterClass(long millisInFuture, long countDownInterval) {
            super(millisInFuture, countDownInterval);
        }

        @Override
        public void onTick(long millisUntilFinished) {

            long millis = millisUntilFinished;
            String hms = String.format("%02d:%02d:%02d", TimeUnit.MILLISECONDS.toHours(millis),
                    TimeUnit.MILLISECONDS.toMinutes(millis) - TimeUnit.HOURS.toMinutes(TimeUnit.MILLISECONDS.toHours(millis)),
                    TimeUnit.MILLISECONDS.toSeconds(millis) - TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes(millis)));
            silverTimerTimerTxtv.setText(hms);
        }

        @Override
        public void onFinish() {
            silverTimerCounter.cancel();
            silverTimerCollectBtn.setEnabled(true);
            silverTimerCollectBtn.setVisibility(View.VISIBLE);
            silverTimerTimerTxtv.setVisibility(View.INVISIBLE);
        }
    }

    public class GoldTimerCounterClass extends CountDownTimer {

        public GoldTimerCounterClass(long millisInFuture, long countDownInterval) {
            super(millisInFuture, countDownInterval);
        }

        @Override
        public void onTick(long millisUntilFinished) {

            long millis = millisUntilFinished;
            String hms = String.format("%02d:%02d:%02d", TimeUnit.MILLISECONDS.toHours(millis),
                    TimeUnit.MILLISECONDS.toMinutes(millis) - TimeUnit.HOURS.toMinutes(TimeUnit.MILLISECONDS.toHours(millis)),
                    TimeUnit.MILLISECONDS.toSeconds(millis) - TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes(millis)));
            goldTimerTimerTxtv.setText(hms);
        }

        @Override
        public void onFinish() {
            goldTimerCounter.cancel();
            goldTimerCollectBtn.setEnabled(true);
            goldTimerCollectBtn.setVisibility(View.VISIBLE);
            goldTimerTimerTxtv.setVisibility(View.INVISIBLE);
        }
    }

    public class ExtraLifeCounterClass extends CountDownTimer {

        public ExtraLifeCounterClass(long millisInFuture, long countDownInterval) {
            super(millisInFuture, countDownInterval);
        }

        @Override
        public void onTick(long millisUntilFinished) {

            long millis = millisUntilFinished;
            String hms = String.format("%02d:%02d:%02d", TimeUnit.MILLISECONDS.toHours(millis),
                    TimeUnit.MILLISECONDS.toMinutes(millis) - TimeUnit.HOURS.toMinutes(TimeUnit.MILLISECONDS.toHours(millis)),
                    TimeUnit.MILLISECONDS.toSeconds(millis) - TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes(millis)));
            extraLifeTimerTxtv.setText(hms);
        }

        @Override
        public void onFinish() {
            extraLifeCounter.cancel();
            extraLifeCollectBtn.setEnabled(true);
            extraLifeCollectBtn.setVisibility(View.VISIBLE);
            extraLifeTimerTxtv.setVisibility(View.INVISIBLE);
        }
    }

    void timerResolver() {
        DateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        Date current = new Date();

        String currentDateTimeString = sdf.format(current);
        try {
            current = sdf.parse(currentDateTimeString);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        try {
            extraLifeLastCollectDate = sdf.parse(sharedPreferences.getString(BackUpDBKeys.BACKUPDB_KEY_LAST_DATE_TIME_EXTRA_LIFE, ""));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        extraLifeTimeLeft = current.getTime() - extraLifeLastCollectDate.getTime();
        if (extraLifeTimeLeft > extraLifeGenerationTime) {
            extraLifeCollectBtn.setEnabled(true);
            extraLifeCollectBtn.setVisibility(View.VISIBLE);
            extraLifeTimerTxtv.setVisibility(View.INVISIBLE);
        } else {
            extraLifeCollectBtn.setEnabled(false);
            extraLifeCollectBtn.setVisibility(View.INVISIBLE);
            extraLifeTimerTxtv.setVisibility(View.VISIBLE);
            extraLifeCounter = new ExtraLifeCounterClass(extraLifeGenerationTime - extraLifeTimeLeft, 1000);
            extraLifeCounter.start();
        }

        try {
            bronzeTimerLastCollectDate = sdf.parse(sharedPreferences.getString(BackUpDBKeys.BACKUPDB_KEY_LAST_DATE_TIME_BRONZE_TIMER, ""));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        bronzeTimerTimeLeft = current.getTime() - bronzeTimerLastCollectDate.getTime();
        if (bronzeTimerTimeLeft > bronzeTimerGenerationTime) {
            bronzeTimerCollectBtn.setEnabled(true);
            bronzeTimerCollectBtn.setVisibility(View.VISIBLE);
            bronzeTimerTimerTxtv.setVisibility(View.INVISIBLE);
        } else {
            bronzeTimerCollectBtn.setEnabled(false);
            bronzeTimerCollectBtn.setVisibility(View.INVISIBLE);
            bronzeTimerTimerTxtv.setVisibility(View.VISIBLE);
            bronzeTimerCounter = new BronzeTimerCounterClass(bronzeTimerGenerationTime - bronzeTimerTimeLeft, 1000);
            bronzeTimerCounter.start();
        }

        try {
            silverTimerLastCollectDate = sdf.parse(sharedPreferences.getString(BackUpDBKeys.BACKUPDB_KEY_LAST_DATE_TIME_SILVER_TIMER, ""));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        silverTimerTimeLeft = current.getTime() - silverTimerLastCollectDate.getTime();
        if (silverTimerTimeLeft > silverTimerGenerationTime) {
            silverTimerCollectBtn.setEnabled(true);
            silverTimerCollectBtn.setVisibility(View.VISIBLE);
            silverTimerTimerTxtv.setVisibility(View.INVISIBLE);
        } else {
            silverTimerCollectBtn.setEnabled(false);
            silverTimerCollectBtn.setVisibility(View.INVISIBLE);
            silverTimerTimerTxtv.setVisibility(View.VISIBLE);
            silverTimerCounter = new SilverTimerCounterClass(silverTimerGenerationTime - silverTimerTimeLeft, 1000);
            silverTimerCounter.start();
        }

        try {
            goldTimerLastCollectDate = sdf.parse(sharedPreferences.getString(BackUpDBKeys.BACKUPDB_KEY_LAST_DATE_TIME_GOLD_TIMER, ""));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        goldTimerTimeLeft = current.getTime() - goldTimerLastCollectDate.getTime();
        if (goldTimerTimeLeft > goldTimerGenerationTime) {
            goldTimerCollectBtn.setEnabled(true);
            goldTimerCollectBtn.setVisibility(View.VISIBLE);
            goldTimerTimerTxtv.setVisibility(View.INVISIBLE);
        } else {
            goldTimerCollectBtn.setEnabled(false);
            goldTimerCollectBtn.setVisibility(View.INVISIBLE);
            goldTimerTimerTxtv.setVisibility(View.VISIBLE);
            goldTimerCounter = new GoldTimerCounterClass(goldTimerGenerationTime - goldTimerTimeLeft, 1000);
            goldTimerCounter.start();
        }
    }

    @Override
    public void onBackPressed() {
        Intent backIntent = new Intent();

        backIntent.putExtra(BackUpDBKeys.BACKUPDB_KEY_EXTRA_LIVES_COLLECTED, extraLivesCollected);
        backIntent.putExtra("total_extra_lives", totalExtraLives);
        backIntent.putExtra(BackUpDBKeys.BACKUPDB_KEY_BRONZE_TIMERS_COLLECTED, bronzeTimersCollected);
        backIntent.putExtra(BackUpDBKeys.BACKUPDB_KEY_SILVER_TIMERS_COLLECTED, silverTimersCollected);
        backIntent.putExtra(BackUpDBKeys.BACKUPDB_KEY_GOLD_TIMERS_COLLECTED, goldTimersCollected);
        backIntent.putExtra("total_bronze_timers", totalBronzeTimers);
        backIntent.putExtra("total_silver_timers", totalSilverTimers);
        backIntent.putExtra("total_gold_timers", totalGoldTimers);
        backIntent.putExtra("total_timers", totalTimers);

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
            Log.e("On Stop - ", "Generator Activity");
            new DaysStreakResolver().resolveDaysStreak(this.getApplicationContext());
            if (sdfDate.format(startDate).equalsIgnoreCase(sdfDate.format(endDate))) {
                dbAdapter.addProgressEntry(sdfDate.format(startDate), sdfTime.format(startDate), sdfDate.format(endDate), sdfTime.format(endDate), diff, xpPoints, "Generator");
            } else {
                long postDiff = 0;
                try {
                    postDiff = appUsage.getTimeDifference(sdfDate.parse(sdfDate.format(endDate) + " 00:00:00"), endDate);
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                dbAdapter.addProgressEntry(sdfDate.format(startDate), sdfTime.format(startDate), sdfDate.format(endDate), "23:59:59", diff - postDiff, xpPoints, "Generator");
                dbAdapter.addProgressEntry(sdfDate.format(endDate), "00:00:00", sdfDate.format(endDate), sdfTime.format(endDate), postDiff, xpPoints, "Generator");
            }
        }
    }
}