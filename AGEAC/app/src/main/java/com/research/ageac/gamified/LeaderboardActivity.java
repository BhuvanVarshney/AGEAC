package com.research.ageac.gamified;

import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FieldValue;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.research.ageac.gamified.quizlibrary.QuizCounts;

import java.io.File;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class LeaderboardActivity extends AppCompatActivity {

    View viewLeaderOverall, viewLeaderCompletion, viewLeaderAccuracy, viewLeaderXP, viewLeaderDaysStreak, viewLeaderAssignmentsCompleted;

    TextView txtvUserPositionOverall, txtvUserPositionCompletion, txtvUserPositionXP, txtvUserPositionAccuracy, txtvUserPositionDaysStreak, txtvUserPositionAssignmentsCompleted;
    TextView txtvUserPointsCompletion, txtvUserPointsXP, txtvUserPointsAccuracy, txtvUserPointsDaysStreak, txtvUserPointsAssignmentsCompleted;
    TextView txtvLeaderNameOverall, txtvLeaderNameCompletion, txtvLeaderNameXP, txtvLeaderNameAccuracy, txtvLeaderNameDaysStreak, txtvLeaderNameAssignmentsCompleted;
    TextView txtvLeaderPointsCompletion, txtvLeaderPointsXP, txtvLeaderPointsAccuracy, txtvLeaderPointsDaysStreak, txtvLeaderPointsAssignmentsCompleted;
    TextView txtvOverallLeaderCompletionValue, txtvOverallLeaderAccuracyValue, txtvOverallLeaderXPValue, txtvOverallLeaderDaysStreak, txtvOverallLeaderAssignmentsCompleted;
    TextView txtvTotalUsers;

    ImageView backBtn;

    String username;
    int xpPoints;

    String KEY_COMPLETION = "completion";
    String KEY_XP = "xp";
    String KEY_TOTAL_ACCURACY = "total_accuracy";
    String KEY_DAYS_STREAK = "days_streak";
    String KEY_ASSIGNMENTS_COMPLETED = "assignments_completed";

    ArrayList<LeaderboardDTO> usersList;

    ProgressBar progressBar;

    AppUsage appUsage;
    DBAdapter dbAdapter;

    DecimalFormat df = new DecimalFormat("#.00");

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
        setContentView(R.layout.activity_leaderboard);

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

        dbAdapter = new DBAdapter(this);
        dbAdapter.addLogEntry("Leaderboard", "OnCreate");

        xpPoints = Integer.parseInt(getIntent().getExtras().getString(BackUpDBKeys.BACKUPDB_KEY_XP_TOTAL_POINTS));

        username = dbAdapter.getUserName();

        viewLeaderOverall = findViewById(R.id.view_leader_overall);
        viewLeaderCompletion = findViewById(R.id.view_leader_completion);
        viewLeaderAccuracy = findViewById(R.id.view_leader_accuracy);
        viewLeaderXP = findViewById(R.id.view_leader_xp);
        viewLeaderDaysStreak = findViewById(R.id.view_leader_days_streak);
        viewLeaderAssignmentsCompleted = findViewById(R.id.view_leader_assignments_completed);

        txtvUserPositionOverall = findViewById(R.id.txtv_leaderboard_user_overall_position);
        txtvLeaderNameOverall = findViewById(R.id.txtv_leaderboard_overall_leader_name);
        txtvOverallLeaderCompletionValue = findViewById(R.id.txtv_leaderboard_overall_leader_completion);
        txtvOverallLeaderAccuracyValue = findViewById(R.id.txtv_leaderboard_overall_leader_accuracy);
        txtvOverallLeaderXPValue = findViewById(R.id.txtv_leaderboard_overall_leader_xp);
        txtvOverallLeaderDaysStreak = findViewById(R.id.txtv_leaderboard_overall_leader_days_streak);
        txtvOverallLeaderAssignmentsCompleted = findViewById(R.id.txtv_leaderboard_overall_leader_assignments_completed);

        txtvUserPositionCompletion = findViewById(R.id.txtv_leaderboard_user_completion_position);
        txtvUserPointsCompletion = findViewById(R.id.txtv_leaderboard_user_completion_points);
        txtvLeaderNameCompletion = findViewById(R.id.txtv_leaderboard_completion_leader_name);
        txtvLeaderPointsCompletion = findViewById(R.id.txtv_leaderboard_completion_leader_points);

        txtvUserPositionAccuracy = findViewById(R.id.txtv_leaderboard_user_accuracy_position);
        txtvUserPointsAccuracy = findViewById(R.id.txtv_leaderboard_user_accuracy_points);
        txtvLeaderNameAccuracy = findViewById(R.id.txtv_leaderboard_accuracy_leader_name);
        txtvLeaderPointsAccuracy = findViewById(R.id.txtv_leaderboard_accuracy_leader_points);

        txtvUserPositionXP = findViewById(R.id.txtv_leaderboard_user_xp_position);
        txtvUserPointsXP = findViewById(R.id.txtv_leaderboard_user_xp_points);
        txtvLeaderNameXP = findViewById(R.id.txtv_leaderboard_xp_leader_name);
        txtvLeaderPointsXP = findViewById(R.id.txtv_leaderboard_xp_leader_points);

        txtvUserPositionDaysStreak = findViewById(R.id.txtv_leaderboard_user_days_streak_position);
        txtvUserPointsDaysStreak = findViewById(R.id.txtv_leaderboard_user_days_streak_points);
        txtvLeaderNameDaysStreak = findViewById(R.id.txtv_leaderboard_days_streak_leader_name);
        txtvLeaderPointsDaysStreak = findViewById(R.id.txtv_leaderboard_days_streak_leader_points);

        txtvUserPositionAssignmentsCompleted = findViewById(R.id.txtv_leaderboard_user_assignments_completed_position);
        txtvUserPointsAssignmentsCompleted = findViewById(R.id.txtv_leaderboard_user_assignments_completed_points);
        txtvLeaderNameAssignmentsCompleted = findViewById(R.id.txtv_leaderboard_assignments_completed_leader_name);
        txtvLeaderPointsAssignmentsCompleted = findViewById(R.id.txtv_leaderboard_assignments_completed_leader_points);

        txtvTotalUsers = findViewById(R.id.txtv_total_users);

        progressBar = findViewById(R.id.leaderboard_progressbar);
        progressBar.setVisibility(View.INVISIBLE);

        backBtn = findViewById(R.id.leaderboard_back_btn);
        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        ConnectionChecker connectionChecker = new ConnectionChecker(LeaderboardActivity.this);
        boolean isConnectionAvailable = connectionChecker.isInternetConnected();
        if (isConnectionAvailable)
            prepareLeaderBoard();
        else {
            AlertDialog.Builder builder = new AlertDialog.Builder(LeaderboardActivity.this);
            builder.setMessage("Internet connection not available");
            builder.setCancelable(false);
            builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    dialog.dismiss();
                }
            });
            AlertDialog alert = builder.create();
            alert.show();
        }

    }


    private void prepareLeaderBoard() {
        disableUserInteraction();
        usersList = new ArrayList<>();
        uploadDB();
    }

    private void enableUserInteraction() {
        progressBar.setVisibility(View.GONE);
    }

    private void disableUserInteraction() {
        progressBar.setVisibility(View.VISIBLE);
    }

    private void updateProgress() {
        DBAdapter dbAdapter = new DBAdapter(this);
        int quizzesCompleted = dbAdapter.getCompletedStagesNumber();
        int questionsAttempted;
        int questionsFailed;
        double completion, accuracy;
        completion = (quizzesCompleted * 100) / (double) QuizCounts.totalQuizzes;
        StageAttemptsDTO stageAttemptsDTO = dbAdapter.getQuizAttempts();
        questionsAttempted = stageAttemptsDTO.getQuestionsAttempted();
        questionsFailed = stageAttemptsDTO.getQuestionsAnsweredWrong();
        if (questionsAttempted == 0)
            accuracy = 0;
        else
            accuracy = ((questionsAttempted - questionsFailed) * 100) / (double) questionsAttempted;

        SharedPreferences sharedPreferences = getSharedPreferences("Extras", MODE_PRIVATE);
        int daysStreak = sharedPreferences.getInt(BackUpDBKeys.BACKUPDB_KEY_DAYS_STREAK, 1);
        int assignmentsCompleted = sharedPreferences.getInt(BackUpDBKeys.BACKUPDB_KEY_ASSIGNMENTS_COMPLETED, 0);
        DocumentReference docRef = FirebaseFirestore.getInstance().collection("gamified").document(username);
        Map<String, Object> updates = new HashMap<>();
        updates.put(KEY_COMPLETION, completion);
        updates.put(KEY_XP, xpPoints);
        updates.put(KEY_TOTAL_ACCURACY, accuracy);
        updates.put(KEY_DAYS_STREAK, daysStreak);
        updates.put(KEY_ASSIGNMENTS_COMPLETED, assignmentsCompleted);
        updates.put("updatedOn", FieldValue.serverTimestamp());
        docRef.update(updates).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if (task.isSuccessful()) {
                    retrieveDocsAndPrepareResult();
                } else {
                    Log.e("Update Error - ", String.valueOf(task.getException()));
                    enableUserInteraction();
                    Toast.makeText(LeaderboardActivity.this, "Something went wrong. Try again later.", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void retrieveDocsAndPrepareResult() {

        CollectionReference collectionReference = FirebaseFirestore.getInstance().collection("gamified");
        collectionReference.get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                if (task.isSuccessful()) {
                    for (DocumentSnapshot document : task.getResult()) {
                        LeaderboardDTO leaderboardDTO = new LeaderboardDTO();
                        leaderboardDTO.setUsername(document.getId());
                        leaderboardDTO.setCompletion(document.getDouble(KEY_COMPLETION));
                        leaderboardDTO.setXp(document.getDouble(KEY_XP).intValue());
                        leaderboardDTO.setTotalAccuracy(document.getDouble(KEY_TOTAL_ACCURACY));
                        leaderboardDTO.setDaysStreak(document.getDouble(KEY_DAYS_STREAK).intValue());
                        leaderboardDTO.setAssignmentsCompleted(document.getDouble(KEY_ASSIGNMENTS_COMPLETED).intValue());
                        usersList.add(leaderboardDTO);
                    }
                    resolveRank(usersList);
                } else {
                    Log.d("Error getting docs :", String.valueOf(task.getException()));
                    enableUserInteraction();
                    Toast.makeText(LeaderboardActivity.this, "Something went wrong. Try again later.", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void uploadDB() {
        FirebaseStorage firebaseStorage = FirebaseStorage.getInstance();
        StorageReference storageReference = firebaseStorage.getReference("progress/" + username);
        File currentDB = getApplicationContext().getDatabasePath("AGEACver2DB");
        storageReference.putFile(Uri.fromFile(currentDB))
                .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                    @Override
                    public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                        updateProgress();
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.e("Upload DB Error - ", String.valueOf(e));
                        enableUserInteraction();
                        Toast.makeText(LeaderboardActivity.this, "Something went wrong. Try again later.", Toast.LENGTH_SHORT).show();
                    }
                });
    }


    private void resolveRank(ArrayList<LeaderboardDTO> usersList) {

        txtvTotalUsers.setText(String.valueOf(usersList.size()));

        overallRankResolver(usersList);
        completionRankResolver(usersList);
        totalAccuracyResolver(usersList);
        xpRankResolver(usersList);
        assignmentsCompletedRankResolver(usersList);
        daysStreakRankResolver(usersList);

        enableUserInteraction();
    }

    private void overallRankResolver(ArrayList<LeaderboardDTO> usersList) {
        int userPosition = 0;
        Double leaderCompletion, leaderAccuracy;
        int leaderXP, leaderDaysStreak, leaderAssignmentsCompleted;

        Collections.sort(usersList, new Comparator<LeaderboardDTO>() {
            public int compare(LeaderboardDTO o1, LeaderboardDTO o2) {
                Double object1CompletionValue = o1.getCompletion();
                Double object2CompletionValue = o2.getCompletion();
                int completionDiff = object2CompletionValue.compareTo(object1CompletionValue);
                if (completionDiff == 0) {
                    Double object1AccuracyValue = o1.getTotalAccuracy();
                    Double object2AccuracyValue = o2.getTotalAccuracy();
                    int accuracyDiff = object2AccuracyValue.compareTo(object1AccuracyValue);
                    if (accuracyDiff == 0) {
                        Double object1XPValue = (double) o1.getXp();
                        Double object2XPValue = (double) o2.getXp();
                        int xpDiff = object2XPValue.compareTo(object1XPValue);
                        if (xpDiff == 0) {
                            Double object1AssignmentsCompletedValue = (double) o1.getAssignmentsCompleted();
                            Double object2AssignmentsCompletedValue = (double) o2.getAssignmentsCompleted();
                            int assignmentsCompletedDiff = object2AssignmentsCompletedValue.compareTo(object1AssignmentsCompletedValue);
                            if (assignmentsCompletedDiff == 0) {
                                Double object1DaysStreakValue = (double) o1.getDaysStreak();
                                Double object2DaysStreakValue = (double) o2.getDaysStreak();
                                return object2DaysStreakValue.compareTo(object1DaysStreakValue);
                            }
                            return assignmentsCompletedDiff;
                        }
                        return xpDiff;
                    }
                    return accuracyDiff;
                }
                return completionDiff;
            }
        });

        int rank = 1;
        usersList.get(0).setRank(1);
        for (int i = 1; i < usersList.size(); i++) {
            if ((usersList.get(i).getCompletion() != usersList.get(i - 1).getCompletion()) || (usersList.get(i).getTotalAccuracy() != usersList.get(i - 1).getTotalAccuracy()) || (usersList.get(i).getXp() != usersList.get(i - 1).getXp()))
                ++rank;
            usersList.get(i).setRank(rank);
        }

        leaderCompletion = Double.valueOf(df.format(usersList.get(0).getCompletion()));
        leaderAccuracy = Double.valueOf(df.format(usersList.get(0).getTotalAccuracy()));
        leaderXP = usersList.get(0).getXp();
        leaderDaysStreak = usersList.get(0).getDaysStreak();
        leaderAssignmentsCompleted = usersList.get(0).getAssignmentsCompleted();

        for (int i = 0; i < usersList.size(); i++) {
            if (usersList.get(i).getUsername().equals(username)) {
                userPosition = usersList.get(i).getRank();
                if (userPosition == 1) {
                    viewLeaderOverall.setVisibility(View.GONE);
                }
                break;
            }
        }

        txtvUserPositionOverall.setText(String.valueOf(userPosition));
        if (userPosition == 1) {
            txtvLeaderNameOverall.setText("You(" + username + ")");
        } else {
            txtvLeaderNameOverall.setText(usersList.get(0).getUsername());
            txtvOverallLeaderCompletionValue.setText(String.valueOf(leaderCompletion) + " %");
            txtvOverallLeaderAccuracyValue.setText(String.valueOf(leaderAccuracy) + " %");
            txtvOverallLeaderXPValue.setText(String.valueOf(leaderXP));
            txtvOverallLeaderDaysStreak.setText(String.valueOf(leaderDaysStreak));
            txtvOverallLeaderAssignmentsCompleted.setText(String.valueOf(leaderAssignmentsCompleted));
        }

    }


    private void completionRankResolver(ArrayList<LeaderboardDTO> usersList) {

        int userPosition = 0;
        double userPoints = 0;
        Collections.sort(usersList, new Comparator<LeaderboardDTO>() {
            public int compare(LeaderboardDTO o1, LeaderboardDTO o2) {
                Double object1Value = o1.getCompletion();
                Double object2Value = o2.getCompletion();
                return (object2Value.compareTo(object1Value));
            }
        });

        int rank = 1;
        usersList.get(0).setRank(1);
        for (int i = 1; i < usersList.size(); i++) {
            if (usersList.get(i).getCompletion() != usersList.get(i - 1).getCompletion())
                ++rank;
            usersList.get(i).setRank(rank);
        }
        for (int i = 0; i < usersList.size(); i++) {
            if (usersList.get(i).getUsername().equals(username)) {
                userPosition = usersList.get(i).getRank();
                userPoints = usersList.get(i).getCompletion();
                if (userPosition == 1) {
                    viewLeaderCompletion.setVisibility(View.GONE);
                }
                break;
            }
        }

        txtvUserPositionCompletion.setText(String.valueOf(userPosition));
        txtvUserPointsCompletion.setText(String.valueOf(df.format(userPoints)) + " %");
        if (userPosition == 1) {
            txtvLeaderNameCompletion.setText("You(" + username + ")");
        } else {
            txtvLeaderNameCompletion.setText(usersList.get(0).getUsername());
            txtvLeaderPointsCompletion.setText(String.valueOf(df.format(usersList.get(0).getCompletion())) + " %");
        }

    }

    private void totalAccuracyResolver(ArrayList<LeaderboardDTO> usersList) {

        int userPosition = 0;
        double userPoints = 0;
        Collections.sort(usersList, new Comparator<LeaderboardDTO>() {
            public int compare(LeaderboardDTO o1, LeaderboardDTO o2) {
                Double object1Value = o1.getTotalAccuracy();
                Double object2Value = o2.getTotalAccuracy();
                return (object2Value.compareTo(object1Value));
            }
        });

        int rank = 1;
        usersList.get(0).setRank(1);
        for (int i = 1; i < usersList.size(); i++) {
            if (usersList.get(i).getTotalAccuracy() != usersList.get(i - 1).getTotalAccuracy())
                ++rank;
            usersList.get(i).setRank(rank);
        }
        for (int i = 0; i < usersList.size(); i++) {
            if (usersList.get(i).getUsername().equals(username)) {
                userPosition = usersList.get(i).getRank();
                userPoints = usersList.get(i).getTotalAccuracy();
                if (userPosition == 1) {
                    viewLeaderAccuracy.setVisibility(View.GONE);
                }
                break;
            }
        }

        txtvUserPositionAccuracy.setText(String.valueOf(userPosition));
        txtvUserPointsAccuracy.setText(String.valueOf(df.format(userPoints)) + " %");
        if (userPosition == 1) {
            txtvLeaderNameAccuracy.setText("You(" + username + ")");
        } else {
            txtvLeaderNameAccuracy.setText(usersList.get(0).getUsername());
            txtvLeaderPointsAccuracy.setText(String.valueOf(df.format(usersList.get(0).getTotalAccuracy())) + " %");
        }
    }

    private void xpRankResolver(ArrayList<LeaderboardDTO> usersList) {

        int userPosition = 0;
        int userPoints = 0;

        Collections.sort(usersList, new Comparator<LeaderboardDTO>() {
            public int compare(LeaderboardDTO o1, LeaderboardDTO o2) {
                Integer object1Value = o1.getXp();
                Integer object2Value = o2.getXp();
                return (object2Value.compareTo(object1Value));
            }
        });

        int rank = 1;
        usersList.get(0).setRank(1);
        for (int i = 1; i < usersList.size(); i++) {
            if (usersList.get(i).getXp() != usersList.get(i - 1).getXp())
                ++rank;
            usersList.get(i).setRank(rank);
        }
        for (int i = 0; i < usersList.size(); i++) {
            if (usersList.get(i).getUsername().equals(username)) {
                userPosition = usersList.get(i).getRank();
                userPoints = usersList.get(i).getXp();
                if (userPosition == 1) {
                    viewLeaderXP.setVisibility(View.GONE);
                }
                break;
            }
        }

        txtvUserPositionXP.setText(String.valueOf(userPosition));
        txtvUserPointsXP.setText(String.valueOf(userPoints));
        if (userPosition == 1) {
            txtvLeaderNameXP.setText("You(" + username + ")");
        } else {
            txtvLeaderNameXP.setText(usersList.get(0).getUsername());
            txtvLeaderPointsXP.setText(String.valueOf(usersList.get(0).getXp()));
        }
    }

    private void assignmentsCompletedRankResolver(ArrayList<LeaderboardDTO> usersList) {

        int userPosition = 0;
        int userPoints = 0;

        Collections.sort(usersList, new Comparator<LeaderboardDTO>() {
            public int compare(LeaderboardDTO o1, LeaderboardDTO o2) {
                Integer object1Value = o1.getAssignmentsCompleted();
                Integer object2Value = o2.getAssignmentsCompleted();
                return (object2Value.compareTo(object1Value));
            }
        });

        int rank = 1;
        usersList.get(0).setRank(1);
        for (int i = 1; i < usersList.size(); i++) {
            if (usersList.get(i).getAssignmentsCompleted() != usersList.get(i - 1).getAssignmentsCompleted())
                ++rank;
            usersList.get(i).setRank(rank);
        }
        for (int i = 0; i < usersList.size(); i++) {
            if (usersList.get(i).getUsername().equals(username)) {
                userPosition = usersList.get(i).getRank();
                userPoints = usersList.get(i).getAssignmentsCompleted();
                if (userPosition == 1) {
                    viewLeaderAssignmentsCompleted.setVisibility(View.GONE);
                }
                break;
            }
        }

        txtvUserPositionAssignmentsCompleted.setText(String.valueOf(userPosition));
        txtvUserPointsAssignmentsCompleted.setText(String.valueOf(userPoints));
        if (userPosition == 1) {
            txtvLeaderNameAssignmentsCompleted.setText("You(" + username + ")");
        } else {
            txtvLeaderNameAssignmentsCompleted.setText(usersList.get(0).getUsername());
            txtvLeaderPointsAssignmentsCompleted.setText(String.valueOf(usersList.get(0).getAssignmentsCompleted()));
        }
    }

    private void daysStreakRankResolver(ArrayList<LeaderboardDTO> usersList) {

        int userPosition = 0;
        int userPoints = 0;

        Collections.sort(usersList, new Comparator<LeaderboardDTO>() {
            public int compare(LeaderboardDTO o1, LeaderboardDTO o2) {
                Integer object1Value = o1.getDaysStreak();
                Integer object2Value = o2.getDaysStreak();
                return (object2Value.compareTo(object1Value));
            }
        });

        int rank = 1;
        usersList.get(0).setRank(1);
        for (int i = 1; i < usersList.size(); i++) {
            if (usersList.get(i).getDaysStreak() != usersList.get(i - 1).getDaysStreak())
                ++rank;
            usersList.get(i).setRank(rank);
        }
        for (int i = 0; i < usersList.size(); i++) {
            if (usersList.get(i).getUsername().equals(username)) {
                userPosition = usersList.get(i).getRank();
                userPoints = usersList.get(i).getDaysStreak();
                if (userPosition == 1) {
                    viewLeaderDaysStreak.setVisibility(View.GONE);
                }
                break;
            }
        }

        txtvUserPositionDaysStreak.setText(String.valueOf(userPosition));
        txtvUserPointsDaysStreak.setText(String.valueOf(userPoints));
        if (userPosition == 1) {
            txtvLeaderNameDaysStreak.setText("You(" + username + ")");
        } else {
            txtvLeaderNameDaysStreak.setText(usersList.get(0).getUsername());
            txtvLeaderPointsDaysStreak.setText(String.valueOf(usersList.get(0).getDaysStreak()));
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
    protected void onPause() {
        super.onPause();
        DateFormat sdfDate = new SimpleDateFormat("dd/MM/yyyy");
        DateFormat sdfTime = new SimpleDateFormat("HH:mm:ss");
        Date endDate = new Date();
        Date startDate;
        startDate = appUsage.getDateTimeActivityStart();
        long diff = appUsage.getTimeDifference(startDate, endDate);
        if (diff > 5) {
            Log.e("On Stop - ", "Leaderboard");
            new DaysStreakResolver().resolveDaysStreak(this.getApplicationContext());
            if (sdfDate.format(startDate).equalsIgnoreCase(sdfDate.format(endDate))) {
                dbAdapter.addProgressEntry(sdfDate.format(startDate), sdfTime.format(startDate), sdfDate.format(endDate), sdfTime.format(endDate), diff, xpPoints, "LeaderBoard");
            } else {
                long postDiff = 0;
                try {
                    postDiff = appUsage.getTimeDifference(sdfDate.parse(sdfDate.format(endDate) + " 00:00:00"), endDate);
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                dbAdapter.addProgressEntry(sdfDate.format(startDate), sdfTime.format(startDate), sdfDate.format(endDate), "23:59:59", diff - postDiff, xpPoints, "LeaderBoard");
                dbAdapter.addProgressEntry(sdfDate.format(endDate), "00:00:00", sdfDate.format(endDate), sdfTime.format(endDate), postDiff, xpPoints, "LeaderBoard");
            }
        }
    }

    @Override
    public void onBackPressed() {
        finish();
    }
}