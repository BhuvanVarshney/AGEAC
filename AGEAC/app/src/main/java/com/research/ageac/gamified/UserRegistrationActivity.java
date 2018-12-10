package com.research.ageac.gamified;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.RadioButton;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class UserRegistrationActivity extends AppCompatActivity {

    String gender = "Male", usageType = "Beginner";
    TextInputLayout textInputLayoutUsername;
    EditText edtUsername;
    ProgressBar progressBar;

    String username;

    String KEY_DATE_JOINED = "date_joined";
    String KEY_GENDER = "gender";
    String KEY_USER_TYPE = "user_type";
    String KEY_COMPLETION = "completion";
    String KEY_XP = "xp";
    String KEY_TOTAL_ACCURACY = "total_accuracy";
    String KEY_DAYS_STREAK = "days_streak";
    String KEY_ASSIGNMENTS_COMPLETED = "assignments_completed";

    private DocumentReference documentReference;

    boolean isTouchEnabled = true;

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
        setContentView(R.layout.activity_user_registration);

        final View decorView = getWindow().getDecorView();
        decorView.setOnSystemUiVisibilityChangeListener(new View.OnSystemUiVisibilityChangeListener() {

            @Override
            public void onSystemUiVisibilityChange(int visibility) {
                if ((visibility & View.SYSTEM_UI_FLAG_FULLSCREEN) == 0) {
                    decorView.setSystemUiVisibility(flags);
                }
            }
        });

        progressBar = findViewById(R.id.registration_progressbar);
        progressBar.setVisibility(View.INVISIBLE);

        edtUsername = findViewById(R.id.editTextUsername);

        textInputLayoutUsername = findViewById(R.id.input_layout_username);
        Button btnRegister = findViewById(R.id.btnRegister);
        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ConnectionChecker connectionChecker = new ConnectionChecker(UserRegistrationActivity.this);
                boolean isConnectionAvailable = connectionChecker.isInternetConnected();
                if (isConnectionAvailable)
                    registerUser();
                else {
                    AlertDialog.Builder builder = new AlertDialog.Builder(UserRegistrationActivity.this);
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
        });
    }

    private void registerUser() {

        boolean validUsernameEntered;
        username = edtUsername.getText().toString();
        validUsernameEntered = isValidUserName(username);

        if (validUsernameEntered) {
            disableUserInteraction();
            textInputLayoutUsername.setErrorEnabled(false);
            documentReference = FirebaseFirestore.getInstance().collection("gamified").document(username);
            documentReference.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                @Override
                public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                    if (task.isSuccessful()) {
                        DocumentSnapshot document = task.getResult();
                        if (document.exists()) {
                            enableUserInteraction();
                            AlertDialog.Builder builder = new AlertDialog.Builder(UserRegistrationActivity.this);
                            builder.setMessage("Username already taken !");
                            builder.setCancelable(false);
                            builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    dialog.dismiss();
                                }
                            });
                            AlertDialog alert = builder.create();
                            alert.show();
                        } else {
                            Date current = new Date();
                            DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
                            Map<String, Object> newUserData = new HashMap<String, Object>();
                            newUserData.put(KEY_DATE_JOINED, df.format(current));
                            newUserData.put(KEY_GENDER, gender);
                            newUserData.put(KEY_USER_TYPE, usageType);
                            newUserData.put(KEY_COMPLETION, 0);
                            newUserData.put(KEY_XP, 0);
                            newUserData.put(KEY_TOTAL_ACCURACY, 0.0);
                            newUserData.put(KEY_DAYS_STREAK,1);
                            newUserData.put(KEY_ASSIGNMENTS_COMPLETED,0);
                            documentReference.set(newUserData).addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {
                                    if (task.isSuccessful()) {

                                        SharedPreferences sharedPreferences = getSharedPreferences("UserData", MODE_PRIVATE);
                                        SharedPreferences.Editor editor = sharedPreferences.edit();
                                        editor.putString("username", username);
                                        editor.putBoolean("registered", true);
                                        editor.commit();

                                        createBackUpDB(username);

                                        enableUserInteraction();

                                        Toast.makeText(UserRegistrationActivity.this, "User registered", Toast.LENGTH_SHORT).show();

                                        Intent openMainMenu = new Intent(UserRegistrationActivity.this, MainActivity.class);
                                        startActivity(openMainMenu);

                                        UserRegistrationActivity.this.finish();

                                    } else {
                                        enableUserInteraction();
                                        Toast.makeText(UserRegistrationActivity.this, "Something went wrong. Try again later.", Toast.LENGTH_SHORT).show();
                                        Log.e("Error - ", String.valueOf(task.getException()));
                                    }
                                }
                            });
                        }
                    } else {
                        enableUserInteraction();
                        Toast.makeText(UserRegistrationActivity.this, "Something went wrong. Try again later.", Toast.LENGTH_SHORT).show();
                        Log.d("Document Check Error - ", String.valueOf(task.getException()));
                    }
                }
            });
        }

    }


    private void enableUserInteraction() {
        progressBar.setVisibility(View.GONE);
        getWindow().clearFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);
        isTouchEnabled = true;
    }

    private void disableUserInteraction() {
        isTouchEnabled = false;
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE,
                WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);
        progressBar.setVisibility(View.VISIBLE);
    }

    private void createBackUpDB(String username) {

        DateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        DateFormat sdfDate = new SimpleDateFormat("dd/MM/yyyy");
        DateFormat sdfTime = new SimpleDateFormat("HH:mm:ss");
        Date current = new Date();

        DBAdapter dbAdapter = new DBAdapter(UserRegistrationActivity.this);
        dbAdapter.addLogEntry("Launcher", "App Started");

        ArrayList<BackUpDTO> backUpObjectsList = new ArrayList<>();
        BackUpDTO backUpObject;

        backUpObject = new BackUpDTO();
        backUpObject.setKey(BackUpDBKeys.BACKUPDB_KEY_USERNAME);
        backUpObject.setValue(username);
        backUpObjectsList.add(backUpObject);

        backUpObject = new BackUpDTO();
        backUpObject.setKey(BackUpDBKeys.BACKUPDB_KEY_USER_TYPE);
        backUpObject.setValue(usageType);
        backUpObjectsList.add(backUpObject);

        backUpObject = new BackUpDTO();
        backUpObject.setKey(BackUpDBKeys.BACKUPDB_KEY_COINS);
        backUpObject.setValue("0");
        backUpObjectsList.add(backUpObject);

        backUpObject = new BackUpDTO();
        backUpObject.setKey(BackUpDBKeys.BACKUPDB_KEY_BRONZE_TIMERS_BOUGHT);
        backUpObject.setValue("0");
        backUpObjectsList.add(backUpObject);

        backUpObject = new BackUpDTO();
        backUpObject.setKey(BackUpDBKeys.BACKUPDB_KEY_BRONZE_TIMERS_COLLECTED);
        backUpObject.setValue("0");
        backUpObjectsList.add(backUpObject);

        backUpObject = new BackUpDTO();
        backUpObject.setKey(BackUpDBKeys.BACKUPDB_KEY_BRONZE_TIMERS_USED);
        backUpObject.setValue("0");
        backUpObjectsList.add(backUpObject);

        backUpObject = new BackUpDTO();
        backUpObject.setKey(BackUpDBKeys.BACKUPDB_KEY_SILVER_TIMERS_BOUGHT);
        backUpObject.setValue("0");
        backUpObjectsList.add(backUpObject);

        backUpObject = new BackUpDTO();
        backUpObject.setKey(BackUpDBKeys.BACKUPDB_KEY_SILVER_TIMERS_COLLECTED);
        backUpObject.setValue("0");
        backUpObjectsList.add(backUpObject);

        backUpObject = new BackUpDTO();
        backUpObject.setKey(BackUpDBKeys.BACKUPDB_KEY_SILVER_TIMERS_USED);
        backUpObject.setValue("0");
        backUpObjectsList.add(backUpObject);

        backUpObject = new BackUpDTO();
        backUpObject.setKey(BackUpDBKeys.BACKUPDB_KEY_GOLD_TIMERS_BOUGHT);
        backUpObject.setValue("0");
        backUpObjectsList.add(backUpObject);

        backUpObject = new BackUpDTO();
        backUpObject.setKey(BackUpDBKeys.BACKUPDB_KEY_GOLD_TIMERS_COLLECTED);
        backUpObject.setValue("0");
        backUpObjectsList.add(backUpObject);

        backUpObject = new BackUpDTO();
        backUpObject.setKey(BackUpDBKeys.BACKUPDB_KEY_GOLD_TIMERS_USED);
        backUpObject.setValue("0");
        backUpObjectsList.add(backUpObject);

        backUpObject = new BackUpDTO();
        backUpObject.setKey(BackUpDBKeys.BACKUPDB_KEY_EXTRA_LIVES_BOUGHT);
        backUpObject.setValue("0");
        backUpObjectsList.add(backUpObject);

        backUpObject = new BackUpDTO();
        backUpObject.setKey(BackUpDBKeys.BACKUPDB_KEY_EXTRA_LIVES_COLLECTED);
        backUpObject.setValue("0");
        backUpObjectsList.add(backUpObject);

        backUpObject = new BackUpDTO();
        backUpObject.setKey(BackUpDBKeys.BACKUPDB_KEY_EXTRA_LIVES_USED);
        backUpObject.setValue("0");
        backUpObjectsList.add(backUpObject);

        backUpObject = new BackUpDTO();
        backUpObject.setKey(BackUpDBKeys.BACKUPDB_KEY_XP_LEVEL);
        backUpObject.setValue("1");
        backUpObjectsList.add(backUpObject);

        backUpObject = new BackUpDTO();
        backUpObject.setKey(BackUpDBKeys.BACKUPDB_KEY_XP_TOTAL_POINTS);
        backUpObject.setValue("0");
        backUpObjectsList.add(backUpObject);

        backUpObject = new BackUpDTO();
        backUpObject.setKey(BackUpDBKeys.BACKUPDB_KEY_XP_LEVEL_PREV_TARGET);
        backUpObject.setValue("0");
        backUpObjectsList.add(backUpObject);

        backUpObject = new BackUpDTO();
        backUpObject.setKey(BackUpDBKeys.BACKUPDB_KEY_XP_LEVEL_NEXT_TARGET);
        backUpObject.setValue("10");
        backUpObjectsList.add(backUpObject);

        backUpObject = new BackUpDTO();
        backUpObject.setKey(BackUpDBKeys.BACKUPDB_KEY_TIMERS_TARGET);
        backUpObject.setValue("5");
        backUpObjectsList.add(backUpObject);

        backUpObject = new BackUpDTO();
        backUpObject.setKey(BackUpDBKeys.BACKUPDB_KEY_TIMERS_REWARD);
        backUpObject.setValue("100");
        backUpObjectsList.add(backUpObject);

        backUpObject = new BackUpDTO();
        backUpObject.setKey(BackUpDBKeys.BACKUPDB_KEY_EXTRA_LIVES_TARGET);
        backUpObject.setValue("5");
        backUpObjectsList.add(backUpObject);

        backUpObject = new BackUpDTO();
        backUpObject.setKey(BackUpDBKeys.BACKUPDB_KEY_EXTRA_LIVES_REWARD);
        backUpObject.setValue("250");
        backUpObjectsList.add(backUpObject);

        backUpObject = new BackUpDTO();
        backUpObject.setKey(BackUpDBKeys.BACKUPDB_KEY_DAYS_STREAK);
        backUpObject.setValue("1");
        backUpObjectsList.add(backUpObject);

        backUpObject = new BackUpDTO();
        backUpObject.setKey(BackUpDBKeys.BACKUPDB_KEY_DAYS_TARGET);
        backUpObject.setValue("5");
        backUpObjectsList.add(backUpObject);

        backUpObject = new BackUpDTO();
        backUpObject.setKey(BackUpDBKeys.BACKUPDB_KEY_DAYS_REWARD);
        backUpObject.setValue("200");
        backUpObjectsList.add(backUpObject);

        backUpObject = new BackUpDTO();
        backUpObject.setKey(BackUpDBKeys.BACKUPDB_KEY_ASSIGNMENTS_COMPLETED);
        backUpObject.setValue("0");
        backUpObjectsList.add(backUpObject);

        backUpObject = new BackUpDTO();
        backUpObject.setKey(BackUpDBKeys.BACKUPDB_KEY_ASSIGNMENTS_TARGET);
        backUpObject.setValue("2");
        backUpObjectsList.add(backUpObject);

        backUpObject = new BackUpDTO();
        backUpObject.setKey(BackUpDBKeys.BACKUPDB_KEY_ASSIGNMENTS_REWARD);
        backUpObject.setValue("500");
        backUpObjectsList.add(backUpObject);

        backUpObject = new BackUpDTO();
        backUpObject.setKey(BackUpDBKeys.BACKUPDB_KEY_STAGES_TARGET);
        backUpObject.setValue("5");
        backUpObjectsList.add(backUpObject);

        backUpObject = new BackUpDTO();
        backUpObject.setKey(BackUpDBKeys.BACKUPDB_KEY_STAGES_REWARD);
        backUpObject.setValue("100");
        backUpObjectsList.add(backUpObject);

        backUpObject = new BackUpDTO();
        backUpObject.setKey(BackUpDBKeys.BACKUPDB_KEY_ALL_STAGES_ASSIGNMENTS_COMPLETED);
        backUpObject.setValue("false");
        backUpObjectsList.add(backUpObject);

        backUpObject = new BackUpDTO();
        backUpObject.setKey(BackUpDBKeys.BACKUPDB_KEY_LAST_DATE_TIME_BRONZE_TIMER);
        backUpObject.setValue(sdf.format(current));
        backUpObjectsList.add(backUpObject);

        backUpObject = new BackUpDTO();
        backUpObject.setKey(BackUpDBKeys.BACKUPDB_KEY_LAST_DATE_TIME_SILVER_TIMER);
        backUpObject.setValue(sdf.format(current));
        backUpObjectsList.add(backUpObject);

        backUpObject = new BackUpDTO();
        backUpObject.setKey(BackUpDBKeys.BACKUPDB_KEY_LAST_DATE_TIME_GOLD_TIMER);
        backUpObject.setValue(sdf.format(current));
        backUpObjectsList.add(backUpObject);

        backUpObject = new BackUpDTO();
        backUpObject.setKey(BackUpDBKeys.BACKUPDB_KEY_LAST_DATE_TIME_EXTRA_LIFE);
        backUpObject.setValue(sdf.format(current));
        backUpObjectsList.add(backUpObject);

        backUpObject = new BackUpDTO();
        backUpObject.setKey(BackUpDBKeys.BACKUPDB_KEY_LAST_ACTIVE_DATE);
        backUpObject.setValue(sdfDate.format(current));
        backUpObjectsList.add(backUpObject);

        dbAdapter.createBackupTable(backUpObjectsList);
        dbAdapter.addProgressEntry(sdfDate.format(current), sdfTime.format(current), sdfDate.format(current), sdfTime.format(current), 0, 0, "First Run");
    }

    private boolean isValidUserName(String username) {
        if (username.isEmpty()) {
            textInputLayoutUsername.setError("Username is required");
            return false;
        }
        Pattern pattern = Pattern.compile("[^a-z0-9]");
        Matcher matcher = pattern.matcher(username);
        boolean invalidCharacterFound = matcher.find();
        if (invalidCharacterFound) {
            textInputLayoutUsername.setError("Use a-z & 0-9 only");
            return false;
        }
        if (username.length() < 10) {
            textInputLayoutUsername.setError("Minimum 10 characters required");
            return false;
        }
        if (Character.isDigit(username.charAt(0))) {
            textInputLayoutUsername.setError("Begin with an alphabet only");
            return false;
        }
        return true;
    }

    public void setGender(View view) {
        boolean isChecked = ((RadioButton) view).isChecked();
        switch (view.getId()) {
            case R.id.radioButtonMale:
                if (isChecked)
                    gender = "Male";
                break;
            case R.id.radioButtonFemale:
                if (isChecked)
                    gender = "Female";
                break;
        }
    }

    public void setUsageType(View view) {
        boolean isChecked = ((RadioButton) view).isChecked();
        switch (view.getId()) {
            case R.id.radioButtonBeginner:
                if (isChecked)
                    usageType = "Beginner";
                break;
            case R.id.radioButtonExperienced:
                if (isChecked)
                    usageType = "Experienced";
                break;
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
        if (isTouchEnabled) {
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setTitle("Exit App?");
            builder.setMessage("Are you sure you want to exit the application?");
            builder.setCancelable(false);
            builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    finish();
                }
            });
            builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    dialog.cancel();
                }
            });
            AlertDialog alert = builder.create();
            alert.show();
        }
    }

}
