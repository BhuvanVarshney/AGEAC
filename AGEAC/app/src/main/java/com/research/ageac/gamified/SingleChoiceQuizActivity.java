package com.research.ageac.gamified;

import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewTreeObserver;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.ScrollView;
import android.widget.TextView;

import com.research.ageac.gamified.quizlibrary.MCQBaseClass;

import org.w3c.dom.Text;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;

public class SingleChoiceQuizActivity extends AppCompatActivity {

    private TextView questionView;
    private Button btnChoice1, btnChoice2, btnChoice3, btnChoice4;
    private ImageView btnExpandedQuestionView;
    ScrollView scrollView;

    private TextView topicNameTxtv, levelNStageNameTxtv;
    private TextView timerTxtv, txtvEndQuiz;
    private TextView questionsCountTxtv;

    private ImageView backButton;

    private int answer;
    private int score = 0;
    private int coinsPerCorrectAnswer = 0;
    private int bonusCoins = 0;
    private int questionNumber = 0;
    private int quesCount = 0;

    DBAdapter dbAdapter;

    MCQBaseClass quiz;

    int attempts, failedAttempts;
    int level, stage, maxStages;
    String topic;
    int bonusScore = 0;

    int timersUsedDuringQuiz = 0, extraLivesUsedDuringQuiz = 0;
    long addedQuizTime = 0;

    Intent backIntent;

    CounterClass timer;
    EndQuizReminderClass endQuizTimer;

    int coins = 0, totalExtraLives = 0, totalBronzeTimers = 0, totalSilverTimers = 0, totalGoldTimers = 0, totalTimers = 0;
    int xpLevel = 1, xpPoints = 0, xpLevelPrevTarget = 0, xpLevelNextTarget = 10;
    int bronzeTimersUsed, silverTimersUsed, goldTimersUsed, extraLivesUsed;

    long quizTimeLeft, timeForQuiz;

    //testing
    //long bronzeTime = 6000, silverTime = 12000, goldTime = 30000;

    //original
    long bronzeTime = 60000, silverTime = 120000, goldTime = 300000;

    Dialog continueDialog;

    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;

    AppUsage appUsage, quizStart;

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        final int flags = View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                | View.SYSTEM_UI_FLAG_FULLSCREEN
                | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY;
        getWindow().getDecorView().setSystemUiVisibility(flags);
        setContentView(R.layout.activity_single_choice_quiz);

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

        attempts = getIntent().getExtras().getInt("attempts");
        failedAttempts = getIntent().getExtras().getInt("attemptsFailed");

        topic = getIntent().getExtras().getString("topic");
        level = getIntent().getExtras().getInt("level");
        stage = getIntent().getExtras().getInt("stage");
        maxStages = getIntent().getExtras().getInt("maxStages");

        questionView = findViewById(R.id.single_choice_quiz_question);
        btnChoice1 = findViewById(R.id.single_choice_quiz_choice1);
        btnChoice2 = findViewById(R.id.single_choice_quiz_choice2);
        btnChoice3 = findViewById(R.id.single_choice_quiz_choice3);
        btnChoice4 = findViewById(R.id.single_choice_quiz_choice4);

        scrollView = findViewById(R.id.question_scrollview);
        btnExpandedQuestionView = findViewById(R.id.question_dialog_opener);

        topicNameTxtv = findViewById(R.id.txtv_single_choice_quiz_topic_name);
        setTopicText();

        levelNStageNameTxtv = findViewById(R.id.txtv_single_choice_quiz_level_n_stage_name);
        levelNStageNameTxtv.setText("L-" + level + "/S-" + stage);

        questionsCountTxtv = findViewById(R.id.single_choice_quiz_question_count);
        timerTxtv = findViewById(R.id.single_choice_quiz_timer);

        dbAdapter = new DBAdapter(SingleChoiceQuizActivity.this);
        dbAdapter.addLogEntry(topic+" L - "+level+" / S - "+stage,"OnCreate");

        String quizName = "com.research.ageac.gamified.quizlibrary." + topic + ".level" + level + ".Stage" + stage;

        try {
            quiz = (MCQBaseClass) Class.forName(quizName).newInstance();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        }

        if (level == 2)
        {
            coinsPerCorrectAnswer = 5;
            bonusCoins = 10;
        }
        else
        {
            coinsPerCorrectAnswer = 10;
            bonusCoins = 20;
        }

        backButton = findViewById(R.id.single_choice_quiz_back_btn);
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        btnExpandedQuestionView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showExpandedQuestionViewDialog();
            }
        });

        startQuiz();


        btnChoice1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (answer == 0) {
                    score = score + coinsPerCorrectAnswer;
                    updateQuestion();
                } else {
                    dbAdapter.increaseWrongAnswersCount(topic, level, stage);
                    showUseExtraLifeDialog();
                }
            }
        });

        btnChoice2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (answer == 1) {
                    score = score + coinsPerCorrectAnswer;
                    updateQuestion();
                } else {
                    dbAdapter.increaseWrongAnswersCount(topic, level, stage);
                    showUseExtraLifeDialog();
                }
            }
        });

        btnChoice3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (answer == 2) {
                    score = score + coinsPerCorrectAnswer;
                    updateQuestion();
                } else {
                    dbAdapter.increaseWrongAnswersCount(topic, level, stage);
                    showUseExtraLifeDialog();
                }
            }
        });

        btnChoice4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (answer == 3) {
                    score = score + coinsPerCorrectAnswer;
                    updateQuestion();
                } else {
                    dbAdapter.increaseWrongAnswersCount(topic, level, stage);
                    showUseExtraLifeDialog();
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


    private void startQuiz() {
        quizStart = new AppUsage();
        quizStart.setDateTimeActivityStart(new Date());
        questionNumber = 0;
        score = 0;
        timersUsedDuringQuiz = 0;
        extraLivesUsedDuringQuiz = 0;
        attempts++;
        quesCount = quiz.getQuestionsCount();

        //testing
        /*timeForQuiz = quesCount * 7000;
        if (level == 3)
            timeForQuiz = quesCount * 7000;*/

        //original
        timeForQuiz = quesCount * 96000;
        if (level == 3)
            timeForQuiz = quesCount * 180000;

        addedQuizTime = timeForQuiz;
        bonusScore = bonusCoins / attempts;
        updateQuestion();

        timerTxtv.setTextColor(Color.GREEN);
        timer = new CounterClass(timeForQuiz, 1000);
        timer.start();
    }

    private void updateQuestion() {
        if (questionNumber < quesCount) {
            scrollView.scrollTo(0,0);
            questionView.setText(quiz.getQuestion(questionNumber));

            btnChoice1.setText(quiz.getChoice1(questionNumber));
            btnChoice2.setText(quiz.getChoice2(questionNumber));
            btnChoice3.setText(quiz.getChoice3(questionNumber));
            btnChoice4.setText(quiz.getChoice4(questionNumber));

            setScrollbarVisibility();

            answer = quiz.getCorrectAnswer(questionNumber);
            questionNumber++;
            questionsCountTxtv.setText("" + questionNumber + "/" + quesCount);
        } else {
            showSuccessDialog();
        }
    }

    private void setScrollbarVisibility() {
        ViewTreeObserver vto = scrollView.getViewTreeObserver();
        vto.addOnGlobalLayoutListener (new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                scrollView.getViewTreeObserver().removeOnGlobalLayoutListener(this);
                int scrollViewHeight = scrollView.getHeight();
                int childHeight = scrollView.getChildAt(0).getHeight();
                if(childHeight<=scrollViewHeight)
                {
                    btnExpandedQuestionView.setVisibility(View.GONE);
                }
                else
                    btnExpandedQuestionView.setVisibility(View.VISIBLE);
            }
        });
    }


    private void showExpandedQuestionViewDialog() {
        AlertDialog.Builder expandedQuestionViewDialogBuilder = new AlertDialog.Builder(SingleChoiceQuizActivity.this);
        View view = LayoutInflater.from(SingleChoiceQuizActivity.this).inflate(R.layout.question_expanded_view_layout, null);

        TextView question = view.findViewById(R.id.single_choice_quiz_question_expanded_view);
        question.setText(quiz.getQuestion(questionNumber - 1));

        expandedQuestionViewDialogBuilder.setView(view);
        final AlertDialog expandedQuestionViewDialog = expandedQuestionViewDialogBuilder.create();
        expandedQuestionViewDialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        expandedQuestionViewDialog.setCanceledOnTouchOutside(false);
        expandedQuestionViewDialog.show();
    }

    private void showSuccessDialog() {
        timer.cancel();

        addEntryInStatsDB("Success");
        dbAdapter.addLogEntry(topic+" L - "+level+" / S - "+stage,"Success Dialog Shown");

        AlertDialog.Builder successDialogBuilder = new AlertDialog.Builder(SingleChoiceQuizActivity.this);
        View view = LayoutInflater.from(SingleChoiceQuizActivity.this).inflate(R.layout.stage_success_dialog_layout, null);

        final TextView xpLevelTxtv = view.findViewById(R.id.txtv_success_dialog_xp_level);
        final TextView xpLevelPercentageTxtv = view.findViewById(R.id.txtv_success_dialog_xp_level_percentage);
        TextView xpLevelProgressTxtv = view.findViewById(R.id.txtv_success_dialog_xp_level_progress);

        final ProgressBar xpLevelProgressBar = view.findViewById(R.id.prgbar_success_dialog_xp_level);

        TextView attemptsTxtv = view.findViewById(R.id.txtv_success_dialog_attempts_made);
        TextView timersUsedTxtv = view.findViewById(R.id.txtv_success_dialog_timers_used);
        TextView extraLivesUsedTxtv = view.findViewById(R.id.txtv_success_dialog_extra_lives_used);
        TextView questionsScoreTxtv = view.findViewById(R.id.txtv_success_dialog_coins_earned_by_questions);
        TextView bonusScoreTxtv = view.findViewById(R.id.txtv_success_dialog_bonus_coins);
        TextView totalScoreTxtv = view.findViewById(R.id.txtv_success_dialog_total_earned_coins);

        Button btnHome = view.findViewById(R.id.btn_success_dialog_return_home);
        Button btnRetry = view.findViewById(R.id.btn_success_dialog_try_again);
        Button btnNextStage = view.findViewById(R.id.btn_success_dialog_next_stage);

        dbAdapter.updateAttempts(topic, level, stage, attempts, quesCount);
        if (attempts - failedAttempts == 1) {
            dbAdapter.updateStageStatus(topic, level, stage);
        }

        if (stage == maxStages) {
            btnNextStage.setEnabled(false);
            btnNextStage.setClickable(false);
            btnNextStage.setVisibility(View.GONE);
        }

        attemptsTxtv.setText(String.valueOf(attempts));
        timersUsedTxtv.setText(String.valueOf(timersUsedDuringQuiz));
        extraLivesUsedTxtv.setText(String.valueOf(extraLivesUsedDuringQuiz));

        questionsScoreTxtv.setText(String.valueOf(score));
        bonusScoreTxtv.setText(String.valueOf(bonusScore));
        totalScoreTxtv.setText(String.valueOf(score + bonusScore));

        xpPoints = xpPoints + score / (attempts - failedAttempts);
        int finalPercentage = ((xpPoints - xpLevelPrevTarget) * 100) / (xpLevelNextTarget - xpLevelPrevTarget);
        while (finalPercentage >= 100) {
            xpLevel = xpLevel + 1;
            xpLevelPrevTarget = xpLevelNextTarget;
            xpLevelNextTarget = xpLevelNextTarget * 2;
            finalPercentage = ((xpPoints - xpLevelPrevTarget) * 100) / (xpLevelNextTarget - xpLevelPrevTarget);
        }

        xpLevelTxtv.setText(String.valueOf(xpLevel));
        xpLevelPercentageTxtv.setText(String.valueOf(finalPercentage) + " %");
        xpLevelProgressBar.setProgress(finalPercentage);
        xpLevelProgressTxtv.setText(String.valueOf(score / (attempts - failedAttempts)));

        coins = coins + score + bonusScore;
        editor.putInt(BackUpDBKeys.BACKUPDB_KEY_COINS, coins);
        editor.putInt(BackUpDBKeys.BACKUPDB_KEY_XP_LEVEL, xpLevel);
        editor.putInt(BackUpDBKeys.BACKUPDB_KEY_XP_TOTAL_POINTS, xpPoints);
        editor.putInt(BackUpDBKeys.BACKUPDB_KEY_XP_LEVEL_NEXT_TARGET, xpLevelNextTarget);
        editor.putInt(BackUpDBKeys.BACKUPDB_KEY_XP_LEVEL_PREV_TARGET, xpLevelPrevTarget);
        editor.commit();

        successDialogBuilder.setView(view);
        final AlertDialog successDialog = successDialogBuilder.create();
        successDialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        successDialog.setCanceledOnTouchOutside(false);
        successDialog.show();

        successDialog.setOnCancelListener(new DialogInterface.OnCancelListener() {
            @Override
            public void onCancel(DialogInterface dialog) {
                successDialog.dismiss();

                dbAdapter.addLogEntry(topic+" L - "+level+" / S - "+stage,"Exit Quiz After Success Back Pressed");

                backIntent = new Intent();

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
                SingleChoiceQuizActivity.this.finish();
            }
        });

        btnHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                successDialog.dismiss();
                dbAdapter.addLogEntry(topic+" L - "+level+" / S - "+stage,"Exit Quiz After Success Home Pressed");

                Intent intent = new Intent(SingleChoiceQuizActivity.this, MainActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
                finish();
            }
        });

        btnRetry.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                successDialog.dismiss();
                dbAdapter.addLogEntry(topic+" L - "+level+" / S - "+stage,"Retry Quiz After Success");
                startQuiz();
            }
        });

        btnNextStage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                successDialog.dismiss();

                dbAdapter.addLogEntry(topic+" L - "+level+" / S - "+stage,"Exit Quiz After Success Next Pressed");

                Intent stageContentViewIntent = new Intent(SingleChoiceQuizActivity.this, StageInstructionsActivity.class);
                stageContentViewIntent.putExtra("topic", topic);
                stageContentViewIntent.putExtra("level", level);
                stageContentViewIntent.putExtra("stage", stage + 1);
                stageContentViewIntent.putExtra("maxStages", maxStages);

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
        });

    }

    private void showContinueDialog() {

        if(level==2)
            dbAdapter.addLogEntry(topic+" L - "+level+" / S - "+stage,"Timers Dialog Shown | "+"B-"+totalBronzeTimers+"/S-"+totalSilverTimers);
        else
            dbAdapter.addLogEntry(topic+" L - "+level+" / S - "+stage,"Timers Dialog Shown | "+"B-"+totalBronzeTimers+"/S-"+totalSilverTimers+"/G-"+totalGoldTimers);

        AlertDialog.Builder continueDialogBuilder = new AlertDialog.Builder(SingleChoiceQuizActivity.this);
        View view = LayoutInflater.from(SingleChoiceQuizActivity.this).inflate(R.layout.stage_continue_dialog_layout, null);

        View viewGoldTimersAvailable;
        viewGoldTimersAvailable = view.findViewById(R.id.view_dialog_continue_gold_timers_left);

        ImageButton btnResumeBronze, btnResumeSilver, btnResumeGold;
        btnResumeBronze = view.findViewById(R.id.btn_continue_bronze_timer);
        btnResumeSilver = view.findViewById(R.id.btn_continue_silver_timer);
        btnResumeGold = view.findViewById(R.id.btn_continue_gold_timer);

        TextView txtvContinueDialogBronzeTimersLeft, txtvContinueDialogSilverTimersLeft, txtvContinueDialogGoldTimersLeft;
        txtvContinueDialogBronzeTimersLeft = view.findViewById(R.id.dialog_continue_bronze_timers_left_txtv);
        txtvContinueDialogSilverTimersLeft = view.findViewById(R.id.dialog_continue_silver_timers_left_txtv);
        txtvContinueDialogGoldTimersLeft = view.findViewById(R.id.dialog_continue_gold_timers_left_txtv);

        txtvEndQuiz = view.findViewById(R.id.txtv_quiz_end_reminder);

        txtvContinueDialogBronzeTimersLeft.setText("Avl. : " + String.valueOf(totalBronzeTimers));
        txtvContinueDialogSilverTimersLeft.setText("Avl. : " + String.valueOf(totalSilverTimers));
        txtvContinueDialogGoldTimersLeft.setText("Avl. : " + String.valueOf(totalGoldTimers));


        if (level == 2) {
            viewGoldTimersAvailable.setVisibility(View.GONE);
        }

        continueDialogBuilder.setView(view);
        continueDialog = continueDialogBuilder.create();
        continueDialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        continueDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        continueDialog.setCanceledOnTouchOutside(false);
        continueDialog.setCancelable(false);
        continueDialog.show();

        endQuizTimer = new EndQuizReminderClass(4000, 1000);
        endQuizTimer.start();

        continueDialog.setOnCancelListener(new DialogInterface.OnCancelListener() {
            @Override
            public void onCancel(DialogInterface dialog) {
                /*dbAdapter.updateAttempts(level, stage, attempts);
                backIntent = new Intent();
                backIntent.putExtra("levelComplete", levelComplete);
                backIntent.putExtra("timers", timers);
                backIntent.putExtra(BackUpDBKeys.BACKUPDB_KEY_COINS, coins);
                setResult(RESULT_OK, backIntent);
                TrueFalseQuizActivity.this.finish();*/
            }
        });

        if (totalBronzeTimers < 1) {
            btnResumeBronze.setEnabled(false);
            btnResumeBronze.setClickable(false);
            btnResumeBronze.setAlpha(0.3f);
        }

        if (totalSilverTimers < 1) {
            btnResumeSilver.setEnabled(false);
            btnResumeSilver.setClickable(false);
            btnResumeSilver.setAlpha(0.3f);
        }

        if (totalGoldTimers < 1) {
            btnResumeGold.setEnabled(false);
            btnResumeGold.setClickable(false);
            btnResumeGold.setAlpha(0.3f);
        }

        btnResumeBronze.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                endQuizTimer.cancel();
                continueDialog.dismiss();
                dbAdapter.addLogEntry(topic+" L - "+level+" / S - "+stage,"Used Bronze Timer");
                timer = new CounterClass(bronzeTime, 1000);
                timer.start();
                ++bronzeTimersUsed;
                --totalBronzeTimers;
                --totalTimers;
                editor.putInt(BackUpDBKeys.BACKUPDB_KEY_BRONZE_TIMERS_USED, bronzeTimersUsed);
                editor.commit();
                timersUsedDuringQuiz++;
                addedQuizTime = addedQuizTime + bronzeTime;
            }
        });

        btnResumeSilver.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                endQuizTimer.cancel();
                continueDialog.dismiss();
                dbAdapter.addLogEntry(topic+" L - "+level+" / S - "+stage,"Used Silver Timer");
                timer = new CounterClass(silverTime, 1000);
                timerTxtv.setTextColor(Color.GREEN);
                timer.start();
                ++silverTimersUsed;
                --totalSilverTimers;
                --totalTimers;
                editor.putInt(BackUpDBKeys.BACKUPDB_KEY_SILVER_TIMERS_USED, silverTimersUsed);
                editor.commit();
                timersUsedDuringQuiz++;
                addedQuizTime = addedQuizTime + silverTime;
            }
        });

        btnResumeGold.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                endQuizTimer.cancel();
                continueDialog.dismiss();
                dbAdapter.addLogEntry(topic+" L - "+level+" / S - "+stage,"Used Gold Timer");
                timer = new CounterClass(goldTime, 1000);
                timerTxtv.setTextColor(Color.GREEN);
                timer.start();
                ++goldTimersUsed;
                --totalGoldTimers;
                --totalTimers;
                editor.putInt(BackUpDBKeys.BACKUPDB_KEY_GOLD_TIMERS_USED, goldTimersUsed);
                editor.commit();
                timersUsedDuringQuiz++;
                addedQuizTime = addedQuizTime + goldTime;
            }
        });

    }

    private void showUseExtraLifeDialog() {
        timer.cancel();

        dbAdapter.addLogEntry(topic+" L - "+level+" / S - "+stage,"Extra Lives Dialog Shown | XL-"+totalExtraLives);

        AlertDialog.Builder useXLDialogBuilder = new AlertDialog.Builder(SingleChoiceQuizActivity.this);
        View view = LayoutInflater.from(SingleChoiceQuizActivity.this).inflate(R.layout.stage_continue_use_extra_life_dialog_layout, null);

        ImageButton btnUseExtraLife = view.findViewById(R.id.btn_use_xl_extra_life);
        TextView txtvUseXLDialogExtraLivesLeft = view.findViewById(R.id.dialog_use_xl_extra_lives_left);
        txtvUseXLDialogExtraLivesLeft.setText("Avl. : " + String.valueOf(totalExtraLives));
        txtvEndQuiz = view.findViewById(R.id.txtv_quiz_use_extra_life_end_reminder);

        if (totalExtraLives < 1) {
            btnUseExtraLife.setEnabled(false);
            btnUseExtraLife.setClickable(false);
            btnUseExtraLife.setAlpha(0.3f);
        }

        useXLDialogBuilder.setView(view);
        continueDialog = useXLDialogBuilder.create();
        continueDialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        continueDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        continueDialog.setCanceledOnTouchOutside(false);
        continueDialog.setCancelable(false);
        continueDialog.show();

        endQuizTimer = new EndQuizReminderClass(4000, 1000);
        endQuizTimer.start();

        continueDialog.setOnCancelListener(new DialogInterface.OnCancelListener() {
            @Override
            public void onCancel(DialogInterface dialog) {
                /*dbAdapter.updateAttempts(level, stage, attempts);
                backIntent = new Intent();
                backIntent.putExtra("levelComplete", levelComplete);
                backIntent.putExtra("timers", timers);
                backIntent.putExtra(BackUpDBKeys.BACKUPDB_KEY_COINS, coins);
                setResult(RESULT_OK, backIntent);
                TrueFalseQuizActivity.this.finish();*/
            }
        });

        btnUseExtraLife.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                endQuizTimer.cancel();
                continueDialog.dismiss();
                dbAdapter.addLogEntry(topic+" L - "+level+" / S - "+stage,"Used Extra Life");
                timer = new CounterClass(quizTimeLeft, 1000);
                timer.start();
                ++extraLivesUsed;
                --totalExtraLives;
                editor.putInt(BackUpDBKeys.BACKUPDB_KEY_EXTRA_LIVES_USED, extraLivesUsed);
                editor.commit();
                extraLivesUsedDuringQuiz++;
            }
        });

    }

    private void showFailureDialog() {

        addEntryInStatsDB("Failure");
        dbAdapter.addLogEntry(topic+" L - "+level+" / S - "+stage,"Failure Dialog Shown");

        AlertDialog.Builder failureDialogBuilder = new AlertDialog.Builder(SingleChoiceQuizActivity.this);
        View view = LayoutInflater.from(SingleChoiceQuizActivity.this).inflate(R.layout.stage_failure_dialog_layout, null);

        TextView questionsAttemptedTxtv = view.findViewById(R.id.txtv_failure_dialog_questions_answered);
        TextView timersUsedTxtv = view.findViewById(R.id.txtv_failure_dialog_timers_used);
        TextView extraLivesUsedTxtv = view.findViewById(R.id.txtv_failure_dialog_extra_lives_used);
        TextView attemptsTxtv = view.findViewById(R.id.txtv_failure_dialog_attempts_made);

        Button btnHome = view.findViewById(R.id.btn_failure_dialog_return_home);
        Button btnRetry = view.findViewById(R.id.btn_failure_dialog_try_again);

        questionsAttemptedTxtv.setText(String.valueOf(quesCount - questionNumber + 1));
        attemptsTxtv.setText(String.valueOf(attempts));
        timersUsedTxtv.setText(String.valueOf(timersUsedDuringQuiz));
        extraLivesUsedTxtv.setText(String.valueOf(extraLivesUsedDuringQuiz));

        dbAdapter.updateAttempts(topic, level, stage, attempts, quesCount - questionNumber + 1);
        ++failedAttempts;
        dbAdapter.increaseFailedAttempts(topic, level, stage);

        failureDialogBuilder.setView(view);
        final AlertDialog failureDialog = failureDialogBuilder.create();
        failureDialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        failureDialog.setCanceledOnTouchOutside(false);
        failureDialog.show();

        failureDialog.setOnCancelListener(new DialogInterface.OnCancelListener() {
            @Override
            public void onCancel(DialogInterface dialog) {
                failureDialog.dismiss();

                dbAdapter.addLogEntry(topic+" L - "+level+" / S - "+stage,"Exit Quiz After Failure Back Pressed");

                backIntent = new Intent();

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
                SingleChoiceQuizActivity.this.finish();
            }
        });

        btnHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                failureDialog.dismiss();
                dbAdapter.addLogEntry(topic+" L - "+level+" / S - "+stage,"Exit Quiz After Failure Home Pressed");
                Intent intent = new Intent(SingleChoiceQuizActivity.this, MainActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
                finish();
            }
        });

        btnRetry.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                failureDialog.dismiss();
                dbAdapter.addLogEntry(topic+" L - "+level+" / S - "+stage,"Retry Quiz After Failure");
                startQuiz();
            }
        });
    }

    private void addEntryInStatsDB(String result) {
        DateFormat sdfDate = new SimpleDateFormat("dd/MM/yyyy");
        DateFormat sdfTime = new SimpleDateFormat("HH:mm:ss");
        Date endDate = new Date();
        Date startDate;
        startDate = quizStart.getDateTimeActivityStart();
        long diff = quizStart.getTimeDifference(startDate, endDate);
        if (diff > addedQuizTime + 60000)
            diff = addedQuizTime;
        dbAdapter.insertQuizStatus(topic, level, stage, sdfDate.format(startDate), sdfTime.format(startDate), sdfDate.format(endDate), sdfTime.format(endDate), diff, timersUsedDuringQuiz, extraLivesUsedDuringQuiz, result);
    }

    @Override
    public void onBackPressed() {
        AlertDialog.Builder builder = new AlertDialog.Builder(SingleChoiceQuizActivity.this);
        builder.setMessage("You will lose all your progress.\nAn attempt will be added which will cause losing bonus coins.\nAre you sure you still want to quit this quiz now?")
                .setCancelable(false)
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        timer.cancel();

                        addEntryInStatsDB("Left");
                        dbAdapter.addLogEntry(topic+" L - "+level+" / S - "+stage,"Left Quiz");

                        dbAdapter.updateAttempts(topic, level, stage, attempts, questionNumber);
                        dbAdapter.increaseWrongAnswersCount(topic, level, stage);
                        dbAdapter.increaseFailedAttempts(topic, level, stage);
                        backIntent = new Intent();

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
                })
                .setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });
        AlertDialog alert = builder.create();
        alert.setTitle("End Quiz?");
        alert.show();
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
            if (resultCode == RESULT_OK) {
                backIntent = new Intent();
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
    protected void onStop() {
        super.onStop();
        DateFormat sdfDate = new SimpleDateFormat("dd/MM/yyyy");
        DateFormat sdfTime = new SimpleDateFormat("HH:mm:ss");
        Date endDate = new Date();
        Date startDate;
        startDate = appUsage.getDateTimeActivityStart();
        long diff = appUsage.getTimeDifference(startDate, endDate);
        if (diff > 5) {
            Log.e("On Stop - ", "SCQ Activity");
            new DaysStreakResolver().resolveDaysStreak(this.getApplicationContext());
            if (sdfDate.format(startDate).equalsIgnoreCase(sdfDate.format(endDate))) {
                dbAdapter.addProgressEntry(sdfDate.format(startDate), sdfTime.format(startDate), sdfDate.format(endDate), sdfTime.format(endDate), diff, xpPoints, "SCQA");
            } else {
                long postDiff = 0;
                try {
                    postDiff = appUsage.getTimeDifference(sdfDate.parse(sdfDate.format(endDate) + " 00:00:00"), endDate);
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                dbAdapter.addProgressEntry(sdfDate.format(startDate), sdfTime.format(startDate), sdfDate.format(endDate), "23:59:59", diff - postDiff, xpPoints, "SCQA");
                dbAdapter.addProgressEntry(sdfDate.format(endDate), "00:00:00", sdfDate.format(endDate), sdfTime.format(endDate), postDiff, xpPoints, "SCQA");
            }
        }
    }


    public class CounterClass extends CountDownTimer {

        public CounterClass(long millisInFuture, long countDownInterval) {
            super(millisInFuture, countDownInterval);
        }

        @Override
        public void onTick(long millisUntilFinished) {

            long millis = millisUntilFinished;
            quizTimeLeft = millis;
            if (quizTimeLeft <= 30000)
                timerTxtv.setTextColor(Color.RED);
            else if (quizTimeLeft <= 60000)
                timerTxtv.setTextColor(Color.parseColor("#FF8C00"));
            String hms = String.format("%02d:%02d",
                    TimeUnit.MILLISECONDS.toMinutes(millis) - TimeUnit.HOURS.toMinutes(TimeUnit.MILLISECONDS.toHours(millis)),
                    TimeUnit.MILLISECONDS.toSeconds(millis) - TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes(millis)));
            timerTxtv.setText(hms);
        }

        @Override
        public void onFinish() {
            timer.cancel();
            showContinueDialog();
            timerTxtv.setText("00:00");
        }
    }

    public class EndQuizReminderClass extends CountDownTimer {

        public EndQuizReminderClass(long millisInFuture, long countDownInterval) {
            super(millisInFuture, countDownInterval);
        }

        @Override
        public void onTick(long millisUntilFinished) {
            txtvEndQuiz.setText("Ends in " + (millisUntilFinished / 1000) + " s");
        }

        @Override
        public void onFinish() {
            endQuizTimer.cancel();
            continueDialog.dismiss();
            showFailureDialog();
        }
    }


}