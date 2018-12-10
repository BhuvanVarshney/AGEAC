package com.research.ageac.gamified;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class StagesActivity extends AppCompatActivity {

    ExpandableHeightGridView level1Grid, level2Grid, level3Grid;
    ArrayList<StageContentDTO> level1StageList = new ArrayList<StageContentDTO>(), level2StageList = new ArrayList<StageContentDTO>(), level3StageList = new ArrayList<StageContentDTO>();
    StageAdapter level1GridAdaptor, level2GridAdaptor, level3GridAdaptor;

    DBAdapter dbAdapter;

    String topic;
    TextView timersTxtv, coinsTxtv, topicNameTxtv, extraLivesTxtv;
    int coins, totalExtraLives, totalBronzeTimers, totalSilverTimers, totalGoldTimers, totalTimers;
    int xpLevel, xpPoints, xpLevelPrevTarget, xpLevelNextTarget;
    int bronzeTimersUsed, silverTimersUsed, goldTimersUsed, extraLivesUsed;

    ImageView backButton;

    int levelSelected = 0;

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
        setContentView(R.layout.activity_stages);

        final View decorView = getWindow().getDecorView();
        decorView.setOnSystemUiVisibilityChangeListener(new View.OnSystemUiVisibilityChangeListener() {

            @Override
            public void onSystemUiVisibilityChange(int visibility) {
                if ((visibility & View.SYSTEM_UI_FLAG_FULLSCREEN) == 0) {
                    decorView.setSystemUiVisibility(flags);
                }
            }
        });

        topic = getIntent().getExtras().getString("topic");

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

        topicNameTxtv = findViewById(R.id.txtv_topic_name);
        setTopicText();

        timersTxtv = findViewById(R.id.txtv_stage_timers);
        coinsTxtv = findViewById(R.id.txtv_stage_coins);
        extraLivesTxtv = findViewById(R.id.txtv_stage_extra_lives);

        timersTxtv.setText(String.valueOf(totalTimers));
        coinsTxtv.setText(String.valueOf(coins));
        extraLivesTxtv.setText(String.valueOf(totalExtraLives));

        backButton = findViewById(R.id.stage_back_btn);
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        level1Grid = findViewById(R.id.stage_grid_level_1);
        level2Grid = findViewById(R.id.stage_grid_level_2);
        level3Grid = findViewById(R.id.stage_grid_level_3);

        dbAdapter = new DBAdapter(StagesActivity.this);

        level1StageList = dbAdapter.getStagesStatus(topic, 1);
        level2StageList = dbAdapter.getStagesStatus(topic, 2);
        level3StageList = dbAdapter.getStagesStatus(topic, 3);

        /*StageContentDTO fakeObj = new StageContentDTO();
        fakeObj.setStageNo(-1);
        fakeObj.setAvailable("N");
        fakeObj.setCompleted("N");
        for(int i =0;i<100;i++)
        {
            level1StageList.add(fakeObj);
            level2StageList.add(fakeObj);
            level3StageList.add(fakeObj);
        }*/

        level1GridAdaptor = new StageAdapter(this, level1StageList);
        level2GridAdaptor = new StageAdapter(this, level2StageList);
        level3GridAdaptor = new StageAdapter(this, level3StageList);

        level1Grid.setAdapter(level1GridAdaptor);
        level2Grid.setAdapter(level2GridAdaptor);
        level3Grid.setAdapter(level3GridAdaptor);

        level1Grid.setExpanded(true);
        level2Grid.setExpanded(true);
        level3Grid.setExpanded(true);

        level1Grid.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                if (level1StageList.get(position).getAvailable().equals("Y")) {
                    levelSelected = 1;
                    Intent stageContentViewIntent = new Intent(StagesActivity.this, StageInstructionsActivity.class);
                    stageContentViewIntent.putExtra("topic", topic);
                    stageContentViewIntent.putExtra("level", 1);
                    stageContentViewIntent.putExtra("stage", level1StageList.get(position).getStageNo());
                    stageContentViewIntent.putExtra("maxStages", level1StageList.size());
                    stageContentViewIntent.putExtra(BackUpDBKeys.BACKUPDB_KEY_COINS, String.valueOf(coins));
                    stageContentViewIntent.putExtra(BackUpDBKeys.BACKUPDB_KEY_EXTRA_LIVES_USED, String.valueOf(extraLivesUsed));
                    stageContentViewIntent.putExtra("total_extra_lives", String.valueOf(totalExtraLives));
                    stageContentViewIntent.putExtra(BackUpDBKeys.BACKUPDB_KEY_BRONZE_TIMERS_USED, String.valueOf(bronzeTimersUsed));
                    stageContentViewIntent.putExtra(BackUpDBKeys.BACKUPDB_KEY_SILVER_TIMERS_USED, String.valueOf(silverTimersUsed));
                    stageContentViewIntent.putExtra(BackUpDBKeys.BACKUPDB_KEY_GOLD_TIMERS_USED, String.valueOf(goldTimersUsed));
                    stageContentViewIntent.putExtra("total_bronze_timers", String.valueOf(totalBronzeTimers));
                    stageContentViewIntent.putExtra("total_silver_timers", String.valueOf(totalSilverTimers));
                    stageContentViewIntent.putExtra("total_gold_timers", String.valueOf(totalGoldTimers));
                    stageContentViewIntent.putExtra("total_timers", String.valueOf(totalTimers));
                    stageContentViewIntent.putExtra(BackUpDBKeys.BACKUPDB_KEY_XP_LEVEL, String.valueOf(xpLevel));
                    stageContentViewIntent.putExtra(BackUpDBKeys.BACKUPDB_KEY_XP_TOTAL_POINTS, String.valueOf(xpPoints));
                    stageContentViewIntent.putExtra(BackUpDBKeys.BACKUPDB_KEY_XP_LEVEL_PREV_TARGET, String.valueOf(xpLevelPrevTarget));
                    stageContentViewIntent.putExtra(BackUpDBKeys.BACKUPDB_KEY_XP_LEVEL_NEXT_TARGET, String.valueOf(xpLevelNextTarget));
                    startActivityForResult(stageContentViewIntent, 111);
                }
            }
        });

        level2Grid.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                if (level2StageList.get(position).getAvailable().equals("Y")) {
                    levelSelected = 2;
                    Intent stageContentViewIntent = new Intent(StagesActivity.this, StageInstructionsActivity.class);
                    stageContentViewIntent.putExtra("topic", topic);
                    stageContentViewIntent.putExtra("level", 2);
                    stageContentViewIntent.putExtra("stage", level2StageList.get(position).getStageNo());
                    stageContentViewIntent.putExtra("maxStages", level2StageList.size());
                    stageContentViewIntent.putExtra(BackUpDBKeys.BACKUPDB_KEY_COINS, String.valueOf(coins));
                    stageContentViewIntent.putExtra(BackUpDBKeys.BACKUPDB_KEY_EXTRA_LIVES_USED, String.valueOf(extraLivesUsed));
                    stageContentViewIntent.putExtra("total_extra_lives", String.valueOf(totalExtraLives));
                    stageContentViewIntent.putExtra(BackUpDBKeys.BACKUPDB_KEY_BRONZE_TIMERS_USED, String.valueOf(bronzeTimersUsed));
                    stageContentViewIntent.putExtra(BackUpDBKeys.BACKUPDB_KEY_SILVER_TIMERS_USED, String.valueOf(silverTimersUsed));
                    stageContentViewIntent.putExtra(BackUpDBKeys.BACKUPDB_KEY_GOLD_TIMERS_USED, String.valueOf(goldTimersUsed));
                    stageContentViewIntent.putExtra("total_bronze_timers", String.valueOf(totalBronzeTimers));
                    stageContentViewIntent.putExtra("total_silver_timers", String.valueOf(totalSilverTimers));
                    stageContentViewIntent.putExtra("total_gold_timers", String.valueOf(totalGoldTimers));
                    stageContentViewIntent.putExtra("total_timers", String.valueOf(totalTimers));
                    stageContentViewIntent.putExtra(BackUpDBKeys.BACKUPDB_KEY_XP_LEVEL, String.valueOf(xpLevel));
                    stageContentViewIntent.putExtra(BackUpDBKeys.BACKUPDB_KEY_XP_TOTAL_POINTS, String.valueOf(xpPoints));
                    stageContentViewIntent.putExtra(BackUpDBKeys.BACKUPDB_KEY_XP_LEVEL_PREV_TARGET, String.valueOf(xpLevelPrevTarget));
                    stageContentViewIntent.putExtra(BackUpDBKeys.BACKUPDB_KEY_XP_LEVEL_NEXT_TARGET, String.valueOf(xpLevelNextTarget));
                    startActivityForResult(stageContentViewIntent, 111);
                }
            }
        });

        level3Grid.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                if (level3StageList.get(position).getAvailable().equals("Y")) {
                    levelSelected = 3;
                    Intent stageContentViewIntent = new Intent(StagesActivity.this, StageInstructionsActivity.class);
                    stageContentViewIntent.putExtra("topic", topic);
                    stageContentViewIntent.putExtra("level", 3);
                    stageContentViewIntent.putExtra("stage", level3StageList.get(position).getStageNo());
                    stageContentViewIntent.putExtra("maxStages", level3StageList.size());
                    stageContentViewIntent.putExtra(BackUpDBKeys.BACKUPDB_KEY_COINS, String.valueOf(coins));
                    stageContentViewIntent.putExtra(BackUpDBKeys.BACKUPDB_KEY_EXTRA_LIVES_USED, String.valueOf(extraLivesUsed));
                    stageContentViewIntent.putExtra("total_extra_lives", String.valueOf(totalExtraLives));
                    stageContentViewIntent.putExtra(BackUpDBKeys.BACKUPDB_KEY_BRONZE_TIMERS_USED, String.valueOf(bronzeTimersUsed));
                    stageContentViewIntent.putExtra(BackUpDBKeys.BACKUPDB_KEY_SILVER_TIMERS_USED, String.valueOf(silverTimersUsed));
                    stageContentViewIntent.putExtra(BackUpDBKeys.BACKUPDB_KEY_GOLD_TIMERS_USED, String.valueOf(goldTimersUsed));
                    stageContentViewIntent.putExtra("total_bronze_timers", String.valueOf(totalBronzeTimers));
                    stageContentViewIntent.putExtra("total_silver_timers", String.valueOf(totalSilverTimers));
                    stageContentViewIntent.putExtra("total_gold_timers", String.valueOf(totalGoldTimers));
                    stageContentViewIntent.putExtra("total_timers", String.valueOf(totalTimers));
                    stageContentViewIntent.putExtra(BackUpDBKeys.BACKUPDB_KEY_XP_LEVEL, String.valueOf(xpLevel));
                    stageContentViewIntent.putExtra(BackUpDBKeys.BACKUPDB_KEY_XP_TOTAL_POINTS, String.valueOf(xpPoints));
                    stageContentViewIntent.putExtra(BackUpDBKeys.BACKUPDB_KEY_XP_LEVEL_PREV_TARGET, String.valueOf(xpLevelPrevTarget));
                    stageContentViewIntent.putExtra(BackUpDBKeys.BACKUPDB_KEY_XP_LEVEL_NEXT_TARGET, String.valueOf(xpLevelNextTarget));
                    startActivityForResult(stageContentViewIntent, 111);
                }
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

        timersTxtv.setText(String.valueOf(totalTimers));
        coinsTxtv.setText(String.valueOf(coins));
        extraLivesTxtv.setText(String.valueOf(totalExtraLives));

        if (levelSelected == 1) {
            level1StageList = dbAdapter.getStagesStatus(topic, 1);
            level1GridAdaptor = new StageAdapter(this, level1StageList);
            level1Grid.setAdapter(level1GridAdaptor);
            level1Grid.setExpanded(true);
            level2Grid.setExpanded(true);
            level3Grid.setExpanded(true);
        } else if (levelSelected == 2) {
            level2StageList = dbAdapter.getStagesStatus(topic, 2);
            level2GridAdaptor = new StageAdapter(this, level2StageList);
            level2Grid.setAdapter(level2GridAdaptor);
            level1Grid.setExpanded(true);
            level2Grid.setExpanded(true);
            level3Grid.setExpanded(true);
        } else if (levelSelected == 3) {
            level3StageList = dbAdapter.getStagesStatus(topic, 3);
            level3GridAdaptor = new StageAdapter(this, level3StageList);
            level3Grid.setAdapter(level3GridAdaptor);
            level1Grid.setExpanded(true);
            level2Grid.setExpanded(true);
            level3Grid.setExpanded(true);
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
