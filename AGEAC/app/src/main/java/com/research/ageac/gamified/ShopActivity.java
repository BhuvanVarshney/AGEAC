package com.research.ageac.gamified;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.ImageView;
import android.widget.TextView;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ShopActivity extends AppCompatActivity {

    TextView txtvCoinsCount, txtvBronzeTimersAlreadyAvailable, txtvSilverTimersAlreadyAvailable, txtvGoldTimersAlreadyAvailable, txtvExtraLivesAlreadyAvailable;

    private ImageView backButton;
    int coins = 0, totalExtraLives = 0, totalBronzeTimers = 0, totalSilverTimers = 0, totalGoldTimers = 0, totalTimers = 0;
    int bronzeTimersBought, silverTimersBought, goldTimersBought, extraLivesBought;
    int bronzeTimerCost = 25, silverTimerCost = 50, goldTimerCost = 100, extraLifeCost = 200;
    int xpPoints;

    View btnBuyBronzeTimer, btnBuySilverTimer, btnBuyGoldTimer, btnBuyExtraLife;

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
        setContentView(R.layout.activity_shop);

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

        dbAdapter = new DBAdapter(ShopActivity.this);
        dbAdapter.addLogEntry("Shop", "OnCreate");

        appUsage = new AppUsage();

        coins = Integer.parseInt(getIntent().getExtras().getString(BackUpDBKeys.BACKUPDB_KEY_COINS));
        extraLivesBought = Integer.parseInt(getIntent().getExtras().getString(BackUpDBKeys.BACKUPDB_KEY_EXTRA_LIVES_BOUGHT));
        totalExtraLives = Integer.parseInt(getIntent().getExtras().getString("total_extra_lives"));
        bronzeTimersBought = Integer.parseInt(getIntent().getExtras().getString(BackUpDBKeys.BACKUPDB_KEY_BRONZE_TIMERS_BOUGHT));
        silverTimersBought = Integer.parseInt(getIntent().getExtras().getString(BackUpDBKeys.BACKUPDB_KEY_SILVER_TIMERS_BOUGHT));
        goldTimersBought = Integer.parseInt(getIntent().getExtras().getString(BackUpDBKeys.BACKUPDB_KEY_GOLD_TIMERS_BOUGHT));
        totalBronzeTimers = Integer.parseInt(getIntent().getExtras().getString("total_bronze_timers"));
        totalSilverTimers = Integer.parseInt(getIntent().getExtras().getString("total_silver_timers"));
        totalGoldTimers = Integer.parseInt(getIntent().getExtras().getString("total_gold_timers"));
        totalTimers = Integer.parseInt(getIntent().getExtras().getString("total_timers"));
        xpPoints = Integer.parseInt(getIntent().getExtras().getString(BackUpDBKeys.BACKUPDB_KEY_XP_TOTAL_POINTS));

        txtvCoinsCount = findViewById(R.id.txtv_shop_coins);
        txtvCoinsCount.setText(String.valueOf(coins));

        txtvBronzeTimersAlreadyAvailable = findViewById(R.id.txtv_shop_bronze_timers_already_available);
        txtvSilverTimersAlreadyAvailable = findViewById(R.id.txtv_shop_silver_timers_already_available);
        txtvGoldTimersAlreadyAvailable = findViewById(R.id.txtv_shop_gold_timers_already_available);
        txtvExtraLivesAlreadyAvailable = findViewById(R.id.txtv_shop_extra_lives_already_available);


        txtvBronzeTimersAlreadyAvailable.setText(String.valueOf(totalBronzeTimers));
        txtvSilverTimersAlreadyAvailable.setText(String.valueOf(totalSilverTimers));
        txtvGoldTimersAlreadyAvailable.setText(String.valueOf(totalGoldTimers));
        txtvExtraLivesAlreadyAvailable.setText(String.valueOf(totalExtraLives));

        backButton = findViewById(R.id.shop_back_btn);

        btnBuyBronzeTimer = findViewById(R.id.btn_shop_buy_bronze_timer);
        btnBuySilverTimer = findViewById(R.id.btn_shop_buy_silver_timer);
        btnBuyGoldTimer = findViewById(R.id.btn_shop_buy_gold_timer);
        btnBuyExtraLife = findViewById(R.id.btn_shop_buy_extra_life);

        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        btnBuyBronzeTimer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (coins < bronzeTimerCost) {
                    AlertDialog.Builder builder = new AlertDialog.Builder(ShopActivity.this);
                    builder.setMessage("Not enough coins to buy this timer.")
                            .setCancelable(true)
                            .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    dialog.cancel();
                                }
                            });
                    AlertDialog alert = builder.create();
                    alert.setTitle("Ran out of coins...");
                    alert.show();
                } else {
                    dbAdapter.addLogEntry("Shop", "Bought Bronze Timer");
                    coins = coins - bronzeTimerCost;
                    txtvCoinsCount.setText(String.valueOf(coins));
                    ++bronzeTimersBought;
                    ++totalBronzeTimers;
                    ++totalTimers;
                    txtvBronzeTimersAlreadyAvailable.setText(String.valueOf(totalBronzeTimers));
                    editor.putInt(BackUpDBKeys.BACKUPDB_KEY_BRONZE_TIMERS_BOUGHT, bronzeTimersBought);
                    editor.putInt(BackUpDBKeys.BACKUPDB_KEY_COINS, coins);
                    editor.commit();
                }
            }
        });

        btnBuySilverTimer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (coins < silverTimerCost) {
                    AlertDialog.Builder builder = new AlertDialog.Builder(ShopActivity.this);
                    builder.setMessage("Not enough coins to buy this timer.")
                            .setCancelable(true)
                            .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    dialog.cancel();
                                }
                            });
                    AlertDialog alert = builder.create();
                    alert.setTitle("Ran out of coins...");
                    alert.show();
                } else {
                    dbAdapter.addLogEntry("Shop", "Bought Silver Timer");
                    coins = coins - silverTimerCost;
                    txtvCoinsCount.setText(String.valueOf(coins));
                    ++silverTimersBought;
                    ++totalSilverTimers;
                    ++totalTimers;
                    txtvSilverTimersAlreadyAvailable.setText(String.valueOf(totalSilverTimers));
                    editor.putInt(BackUpDBKeys.BACKUPDB_KEY_SILVER_TIMERS_BOUGHT, silverTimersBought);
                    editor.putInt(BackUpDBKeys.BACKUPDB_KEY_COINS, coins);
                    editor.commit();
                }
            }
        });

        btnBuyGoldTimer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (coins < goldTimerCost) {
                    AlertDialog.Builder builder = new AlertDialog.Builder(ShopActivity.this);
                    builder.setMessage("Not enough coins to buy this timer.")
                            .setCancelable(true)
                            .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    dialog.cancel();
                                }
                            });
                    AlertDialog alert = builder.create();
                    alert.setTitle("Ran out of coins...");
                    alert.show();
                } else {
                    dbAdapter.addLogEntry("Shop", "Bought Gold Timer");
                    coins = coins - goldTimerCost;
                    txtvCoinsCount.setText(String.valueOf(coins));
                    ++goldTimersBought;
                    ++totalGoldTimers;
                    ++totalTimers;
                    txtvGoldTimersAlreadyAvailable.setText(String.valueOf(totalGoldTimers));
                    editor.putInt(BackUpDBKeys.BACKUPDB_KEY_GOLD_TIMERS_BOUGHT, goldTimersBought);
                    editor.putInt(BackUpDBKeys.BACKUPDB_KEY_COINS, coins);
                    editor.commit();
                }
            }
        });


        btnBuyExtraLife.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (coins < extraLifeCost) {
                    AlertDialog.Builder builder = new AlertDialog.Builder(ShopActivity.this);
                    builder.setMessage("Not enough coins to buy extra life.")
                            .setCancelable(true)
                            .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    dialog.cancel();
                                }
                            });
                    AlertDialog alert = builder.create();
                    alert.setTitle("Ran out of coins...");
                    alert.show();
                } else {
                    dbAdapter.addLogEntry("Shop", "Bought Extra Life");
                    coins = coins - extraLifeCost;
                    txtvCoinsCount.setText(String.valueOf(coins));
                    ++extraLivesBought;
                    ++totalExtraLives;
                    txtvExtraLivesAlreadyAvailable.setText(String.valueOf(totalExtraLives));
                    editor.putInt(BackUpDBKeys.BACKUPDB_KEY_EXTRA_LIVES_BOUGHT, extraLivesBought);
                    editor.putInt(BackUpDBKeys.BACKUPDB_KEY_COINS, coins);
                    editor.commit();
                }
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
    public void onBackPressed() {
        Intent backIntent = new Intent();

        backIntent.putExtra(BackUpDBKeys.BACKUPDB_KEY_COINS, coins);
        backIntent.putExtra(BackUpDBKeys.BACKUPDB_KEY_EXTRA_LIVES_BOUGHT, extraLivesBought);
        backIntent.putExtra("total_extra_lives", totalExtraLives);
        backIntent.putExtra(BackUpDBKeys.BACKUPDB_KEY_BRONZE_TIMERS_BOUGHT, bronzeTimersBought);
        backIntent.putExtra(BackUpDBKeys.BACKUPDB_KEY_SILVER_TIMERS_BOUGHT, silverTimersBought);
        backIntent.putExtra(BackUpDBKeys.BACKUPDB_KEY_GOLD_TIMERS_BOUGHT, goldTimersBought);
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
            Log.e("On Stop - ", "Shop Activity");
            new DaysStreakResolver().resolveDaysStreak(this.getApplicationContext());
            if (sdfDate.format(startDate).equalsIgnoreCase(sdfDate.format(endDate))) {
                dbAdapter.addProgressEntry(sdfDate.format(startDate), sdfTime.format(startDate), sdfDate.format(endDate), sdfTime.format(endDate), diff, xpPoints, "Shop");
            } else {
                long postDiff = 0;
                try {
                    postDiff = appUsage.getTimeDifference(sdfDate.parse(sdfDate.format(endDate) + " 00:00:00"), endDate);
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                dbAdapter.addProgressEntry(sdfDate.format(startDate), sdfTime.format(startDate), sdfDate.format(endDate), "23:59:59", diff - postDiff, xpPoints, "Shop");
                dbAdapter.addProgressEntry(sdfDate.format(endDate), "00:00:00", sdfDate.format(endDate), sdfTime.format(endDate), postDiff, xpPoints, "Shop");
            }
        }
    }
}