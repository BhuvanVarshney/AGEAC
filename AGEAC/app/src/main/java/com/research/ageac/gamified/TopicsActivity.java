package com.research.ageac.gamified;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Window;
import android.widget.ImageView;
import android.widget.TextView;

public class TopicsActivity extends AppCompatActivity {

    TextView timersTxtv, coinsTxtv, extraLivesTxtv;
    int coins, totalExtraLives, totalBronzeTimers, totalSilverTimers, totalGoldTimers, totalTimers;
    int xpLevel, xpPoints, xpLevelPrevTarget, xpLevelNextTarget;
    int bronzeTimersUsed, silverTimersUsed, goldTimersUsed, extraLivesUsed;
    ImageView backBtnImgv;

    View btnTopicArray, btnTopicLinkedList, btnTopicStack, btnTopicQueue, btnTopicTrees, btnTopicBST, btnTopicHeap, btnTopicGraph, btnTopicHashing;

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
        setContentView(R.layout.activity_topics);

        final View decorView = getWindow().getDecorView();
        decorView.setOnSystemUiVisibilityChangeListener(new View.OnSystemUiVisibilityChangeListener() {

            @Override
            public void onSystemUiVisibilityChange(int visibility) {
                if ((visibility & View.SYSTEM_UI_FLAG_FULLSCREEN) == 0) {
                    decorView.setSystemUiVisibility(flags);
                }
            }
        });

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

        btnTopicArray = findViewById(R.id.btn_topic_array);
        btnTopicLinkedList = findViewById(R.id.btn_topic_linkedlist);
        btnTopicStack = findViewById(R.id.btn_topic_stack);
        btnTopicQueue = findViewById(R.id.btn_topic_queue);
        btnTopicTrees = findViewById(R.id.btn_topic_tree);
        btnTopicBST = findViewById(R.id.btn_topic_bst);
        btnTopicHeap = findViewById(R.id.btn_topic_heap);
        btnTopicGraph = findViewById(R.id.btn_topic_graph);
        btnTopicHashing = findViewById(R.id.btn_topic_hashing);

        timersTxtv = findViewById(R.id.txtv_topic_timers);
        coinsTxtv = findViewById(R.id.txtv_topic_coins);
        extraLivesTxtv = findViewById(R.id.txtv_topic_extra_lives);

        timersTxtv.setText(String.valueOf(totalTimers));
        coinsTxtv.setText(String.valueOf(coins));
        extraLivesTxtv.setText(String.valueOf(totalExtraLives));

        backBtnImgv = findViewById(R.id.topic_back_btn);
        backBtnImgv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        btnTopicArray.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                initializeIntent("array");
            }
        });

        btnTopicLinkedList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                initializeIntent("linkedlist");
            }
        });

        btnTopicStack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                initializeIntent("stack");
            }
        });

        btnTopicQueue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                initializeIntent("queue");
            }
        });

        btnTopicTrees.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                initializeIntent("tree");
            }
        });

        btnTopicBST.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                initializeIntent("bst");
            }
        });

        btnTopicHeap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                initializeIntent("heap");
            }
        });

        btnTopicGraph.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                initializeIntent("graph");
            }
        });

        btnTopicHashing.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                initializeIntent("hashing");
            }
        });

    }

    private void initializeIntent(String topicSelected) {
        Intent stagesActivityIntent = new Intent(TopicsActivity.this, StagesActivity.class);
        stagesActivityIntent.putExtra("topic", topicSelected);
        stagesActivityIntent.putExtra(BackUpDBKeys.BACKUPDB_KEY_COINS, String.valueOf(coins));
        stagesActivityIntent.putExtra(BackUpDBKeys.BACKUPDB_KEY_EXTRA_LIVES_USED, String.valueOf(extraLivesUsed));
        stagesActivityIntent.putExtra("total_extra_lives", String.valueOf(totalExtraLives));
        stagesActivityIntent.putExtra(BackUpDBKeys.BACKUPDB_KEY_BRONZE_TIMERS_USED, String.valueOf(bronzeTimersUsed));
        stagesActivityIntent.putExtra(BackUpDBKeys.BACKUPDB_KEY_SILVER_TIMERS_USED, String.valueOf(silverTimersUsed));
        stagesActivityIntent.putExtra(BackUpDBKeys.BACKUPDB_KEY_GOLD_TIMERS_USED, String.valueOf(goldTimersUsed));
        stagesActivityIntent.putExtra("total_bronze_timers", String.valueOf(totalBronzeTimers));
        stagesActivityIntent.putExtra("total_silver_timers", String.valueOf(totalSilverTimers));
        stagesActivityIntent.putExtra("total_gold_timers", String.valueOf(totalGoldTimers));
        stagesActivityIntent.putExtra("total_timers", String.valueOf(totalTimers));
        stagesActivityIntent.putExtra(BackUpDBKeys.BACKUPDB_KEY_XP_LEVEL, String.valueOf(xpLevel));
        stagesActivityIntent.putExtra(BackUpDBKeys.BACKUPDB_KEY_XP_TOTAL_POINTS, String.valueOf(xpPoints));
        stagesActivityIntent.putExtra(BackUpDBKeys.BACKUPDB_KEY_XP_LEVEL_PREV_TARGET, String.valueOf(xpLevelPrevTarget));
        stagesActivityIntent.putExtra(BackUpDBKeys.BACKUPDB_KEY_XP_LEVEL_NEXT_TARGET, String.valueOf(xpLevelNextTarget));
        startActivityForResult(stagesActivityIntent, 111);
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

        timersTxtv.setText(String.valueOf(totalTimers));
        coinsTxtv.setText(String.valueOf(coins));
        extraLivesTxtv.setText(String.valueOf(totalExtraLives));
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

}
