package com.research.ageac.gamified;


import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;


import com.research.ageac.gamified.quizlibrary.QuizCounts;

import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class DBAdapter {
    DBHelper dbHelper;
    SQLiteDatabase sqLiteDatabase;
    Context context;

    public DBAdapter(Context context) {
        dbHelper = new DBHelper(context);
        this.context = context;
    }

    class DBHelper extends SQLiteOpenHelper {
        private static final String DATABASE_NAME = "AGEACver2DB";
        private static final int DATABASE_VERSION = 1;

        private static final String TABLE_PROGRESS = "progress";
        private static final String KEY_START_DATE = "start_date";
        private static final String KEY_START_TIME = "start_time";
        private static final String KEY_END_DATE = "end_date";
        private static final String KEY_END_TIME = "end_time";
        private static final String KEY_TIME_SPENT = "time_spent";
        private static final String KEY_COMPLETION_PERCENTAGE = "completion_percentage";
        private static final String KEY_EXPERIENCE_GAINED = "experience_gained";
        private static final String KEY_ACTIVITY = "activity";

        private static final String TABLE_QUIZ_STATS = "quiz_stats";
        private static final String KEY_TIMERS_USED = "timers_used";
        private static final String KEY_EXTRA_LIVES_USED = "extra_lives_used";
        private static final String KEY_TIME_TAKEN = "time_taken";
        private static final String KEY_RESULT = "result";

        private static final String TABLE_MAIN = "main";
        private static final String KEY_TOPIC = "topic";
        private static final String KEY_LEVEL = "level";
        private static final String KEY_STAGE = "stage";
        private static final String KEY_AVAILABLE = "available";
        private static final String KEY_COMPLETED = "completed";
        private static final String KEY_ATTEMPTS = "attempts";
        private static final String KEY_ATTEMPTS_FAILED = "attempts_failed";
        private static final String KEY_QUESTIONS_ATTEMPTED = "questions_attempted";
        private static final String KEY_QUESTIONS_ANSWERED_WRONG = "question_answered_wrong";

        private static final String TABLE_ACTIVITY_LOG = "activity_log";
        private static final String KEY_ACTION = "action_performed";

        private static final String TABLE_BACKUP = "backup";

        private static final String CREATE_TABLE_MAIN = "CREATE TABLE " + TABLE_MAIN + " (" + KEY_TOPIC + " VARCHAR(12)," + KEY_LEVEL + " INTEGER, " + KEY_STAGE + " INTEGER, " + KEY_AVAILABLE + " VARCHAR(1), " + KEY_COMPLETED + " VARCHAR(1), " + KEY_ATTEMPTS + " INTEGER, " + KEY_ATTEMPTS_FAILED + " INTEGER, " + KEY_QUESTIONS_ATTEMPTED + " INTEGER, " + KEY_QUESTIONS_ANSWERED_WRONG + " INTEGER );";
        private static final String INSERT_INTO_TABLE_MAIN = "INSERT INTO " + TABLE_MAIN + " (" + KEY_TOPIC + "," + KEY_LEVEL + ", " + KEY_STAGE + ", " + KEY_AVAILABLE + ", " + KEY_COMPLETED + ", " + KEY_ATTEMPTS + ", " + KEY_ATTEMPTS_FAILED + ", " + KEY_QUESTIONS_ATTEMPTED + "," + KEY_QUESTIONS_ANSWERED_WRONG + " )";

        private static final String CREATE_TABLE_PROGRESS = "CREATE TABLE " + TABLE_PROGRESS + " (" + KEY_START_DATE + " VARCHAR(15), " + KEY_START_TIME + " VARCHAR(10), " + KEY_END_DATE + " VARCHAR(15), " + KEY_END_TIME + " VARCHAR(10), " + KEY_TIME_SPENT + " INTEGER, " + KEY_COMPLETION_PERCENTAGE + " REAL, " + KEY_ATTEMPTS + " INTEGER, " + KEY_ATTEMPTS_FAILED + " INTEGER, " + KEY_QUESTIONS_ATTEMPTED + " INTEGER, " + KEY_QUESTIONS_ANSWERED_WRONG + " INTEGER, " + KEY_EXPERIENCE_GAINED + " INTEGER," + KEY_ACTIVITY + " VARCHAR(20) );";
        private static final String INSERT_INTO_TABLE_PROGRESS = "INSERT INTO " + TABLE_PROGRESS + " (" + KEY_START_DATE + ", " + KEY_START_TIME + ", " + KEY_END_DATE + ", " + KEY_END_TIME + ", " + KEY_TIME_SPENT + ", " + KEY_COMPLETION_PERCENTAGE + ", " + KEY_ATTEMPTS + ", " + KEY_ATTEMPTS_FAILED + ", " + KEY_QUESTIONS_ATTEMPTED + ", " + KEY_QUESTIONS_ANSWERED_WRONG + ", " + KEY_EXPERIENCE_GAINED + ", " + KEY_ACTIVITY + " )";

        private static final String CREATE_TABLE_QUIZ_STATS = "CREATE TABLE " + TABLE_QUIZ_STATS + " (" + KEY_TOPIC + " VARCHAR(12)," + KEY_LEVEL + " INTEGER, " + KEY_STAGE + " INTEGER, " + KEY_START_DATE + " VARCHAR(15), " + KEY_START_TIME + " VARCHAR(10), " + KEY_END_DATE + " VARCHAR(15), " + KEY_END_TIME + " VARCHAR(10), " + KEY_TIME_TAKEN + " INTEGER, " + KEY_TIMERS_USED + " INTEGER, " + KEY_EXTRA_LIVES_USED + " INTEGER, " + KEY_RESULT + " VARCHAR(10) );";
        private static final String INSERT_INTO_TABLE_QUIZ_STATS = "INSERT INTO " + TABLE_QUIZ_STATS + " (" + KEY_TOPIC + "," + KEY_LEVEL + ", " + KEY_STAGE + ", " + KEY_START_DATE + ", " + KEY_START_TIME + ", " + KEY_END_DATE + ", " + KEY_END_TIME + ", " + KEY_TIME_TAKEN + ", " + KEY_TIMERS_USED + ", " + KEY_EXTRA_LIVES_USED + ", " + KEY_RESULT + " )";

        private static final String CREATE_TABLE_ACTIVITY_LOG = "CREATE TABLE " + TABLE_ACTIVITY_LOG + " (" + KEY_ACTIVITY + " VARCHAR(30), " + KEY_ACTION + " VARCHAR(50), " + KEY_START_DATE + " VARCHAR(15), " + KEY_START_TIME + " VARCHAR(10));";
        private static final String INSERT_INTO_TABLE_ACTIVITY_LOG = "INSERT INTO " + TABLE_ACTIVITY_LOG + " (" + KEY_ACTIVITY + ", " + KEY_ACTION + ", " + KEY_START_DATE + ", " + KEY_START_TIME + " )";

        private Context context;

        public DBHelper(Context context) {
            super(context, DATABASE_NAME, null, DATABASE_VERSION);
            this.context = context;
        }

        @Override
        public void onCreate(SQLiteDatabase db) {
            try {
                db.execSQL(CREATE_TABLE_MAIN);
            } catch (SQLException e) {
                e.printStackTrace();
            }

            try {
                db.execSQL(CREATE_TABLE_PROGRESS);
            } catch (SQLException e) {
                e.printStackTrace();
            }

            try {
                db.execSQL(CREATE_TABLE_QUIZ_STATS);
            } catch (SQLException e) {
                e.printStackTrace();
            }

            try {
                db.execSQL(CREATE_TABLE_ACTIVITY_LOG);
            } catch (SQLException e) {
                e.printStackTrace();
            }

            addMain(db);
        }

        private void addMain(SQLiteDatabase db) {
            String topic, available, completed;
            int level, stage, attempts;
            String valuesPart;

            for (int i = 1; i <= QuizCounts.arrayLevel1Quizzes; i++) {
                topic = "array";
                level = 1;
                stage = i;
                available = "N";
                completed = "N";
                if (i == 1)
                    available = "Y";
                attempts = 0;
                valuesPart = "VALUES ('" + topic + "', " + level + ", " + stage + ",'" + available + "','" + completed + "'," + attempts + "," + attempts + "," + attempts + "," + attempts + " );";
                db.execSQL(INSERT_INTO_TABLE_MAIN + valuesPart);
            }

            for (int i = 1; i <= QuizCounts.arrayLevel2Quizzes; i++) {
                topic = "array";
                level = 2;
                stage = i;
                available = "N";
                completed = "N";
                if (i == 1)
                    available = "Y";
                attempts = 0;
                valuesPart = "VALUES ('" + topic + "', " + level + ", " + stage + ",'" + available + "','" + completed + "'," + attempts + "," + attempts + "," + attempts + "," + attempts + " );";
                db.execSQL(INSERT_INTO_TABLE_MAIN + valuesPart);
            }

            for (int i = 1; i <= QuizCounts.arrayLevel3Quizzes; i++) {
                topic = "array";
                level = 3;
                stage = i;
                available = "N";
                completed = "N";
                if (i == 1)
                    available = "Y";
                attempts = 0;
                valuesPart = "VALUES ('" + topic + "', " + level + ", " + stage + ",'" + available + "','" + completed + "'," + attempts + "," + attempts + "," + attempts + "," + attempts + " );";
                db.execSQL(INSERT_INTO_TABLE_MAIN + valuesPart);
            }

            for (int i = 1; i <= QuizCounts.linkedListLevel1Quizzes; i++) {
                topic = "linkedlist";
                level = 1;
                stage = i;
                available = "N";
                completed = "N";
                if (i == 1)
                    available = "Y";
                attempts = 0;
                valuesPart = "VALUES ('" + topic + "', " + level + ", " + stage + ",'" + available + "','" + completed + "'," + attempts + "," + attempts + "," + attempts + "," + attempts + " );";
                db.execSQL(INSERT_INTO_TABLE_MAIN + valuesPart);
            }

            for (int i = 1; i <= QuizCounts.linkedListLevel2Quizzes; i++) {
                topic = "linkedlist";
                level = 2;
                stage = i;
                available = "N";
                completed = "N";
                if (i == 1)
                    available = "Y";
                attempts = 0;
                valuesPart = "VALUES ('" + topic + "', " + level + ", " + stage + ",'" + available + "','" + completed + "'," + attempts + "," + attempts + "," + attempts + "," + attempts + " );";
                db.execSQL(INSERT_INTO_TABLE_MAIN + valuesPart);
            }

            for (int i = 1; i <= QuizCounts.linkedListLevel3Quizzes; i++) {
                topic = "linkedlist";
                level = 3;
                stage = i;
                available = "N";
                completed = "N";
                if (i == 1)
                    available = "Y";
                attempts = 0;
                valuesPart = "VALUES ('" + topic + "', " + level + ", " + stage + ",'" + available + "','" + completed + "'," + attempts + "," + attempts + "," + attempts + "," + attempts + " );";
                db.execSQL(INSERT_INTO_TABLE_MAIN + valuesPart);
            }

            for (int i = 1; i <= QuizCounts.stackLevel1Quizzes; i++) {
                topic = "stack";
                level = 1;
                stage = i;
                available = "N";
                completed = "N";
                if (i == 1)
                    available = "Y";
                attempts = 0;
                valuesPart = "VALUES ('" + topic + "', " + level + ", " + stage + ",'" + available + "','" + completed + "'," + attempts + "," + attempts + "," + attempts + "," + attempts + " );";
                db.execSQL(INSERT_INTO_TABLE_MAIN + valuesPart);
            }

            for (int i = 1; i <= QuizCounts.stackLevel2Quizzes; i++) {
                topic = "stack";
                level = 2;
                stage = i;
                available = "N";
                completed = "N";
                if (i == 1)
                    available = "Y";
                attempts = 0;
                valuesPart = "VALUES ('" + topic + "', " + level + ", " + stage + ",'" + available + "','" + completed + "'," + attempts + "," + attempts + "," + attempts + "," + attempts + " );";
                db.execSQL(INSERT_INTO_TABLE_MAIN + valuesPart);
            }

            for (int i = 1; i <= QuizCounts.stackLevel3Quizzes; i++) {
                topic = "stack";
                level = 3;
                stage = i;
                available = "N";
                completed = "N";
                if (i == 1)
                    available = "Y";
                attempts = 0;
                valuesPart = "VALUES ('" + topic + "', " + level + ", " + stage + ",'" + available + "','" + completed + "'," + attempts + "," + attempts + "," + attempts + "," + attempts + " );";
                db.execSQL(INSERT_INTO_TABLE_MAIN + valuesPart);
            }

            for (int i = 1; i <= QuizCounts.queueLevel1Quizzes; i++) {
                topic = "queue";
                level = 1;
                stage = i;
                available = "N";
                completed = "N";
                if (i == 1)
                    available = "Y";
                attempts = 0;
                valuesPart = "VALUES ('" + topic + "', " + level + ", " + stage + ",'" + available + "','" + completed + "'," + attempts + "," + attempts + "," + attempts + "," + attempts + " );";
                db.execSQL(INSERT_INTO_TABLE_MAIN + valuesPart);
            }

            for (int i = 1; i <= QuizCounts.queueLevel2Quizzes; i++) {
                topic = "queue";
                level = 2;
                stage = i;
                available = "N";
                completed = "N";
                if (i == 1)
                    available = "Y";
                attempts = 0;
                valuesPart = "VALUES ('" + topic + "', " + level + ", " + stage + ",'" + available + "','" + completed + "'," + attempts + "," + attempts + "," + attempts + "," + attempts + " );";
                db.execSQL(INSERT_INTO_TABLE_MAIN + valuesPart);
            }

            for (int i = 1; i <= QuizCounts.queueLevel3Quizzes; i++) {
                topic = "queue";
                level = 3;
                stage = i;
                available = "N";
                completed = "N";
                if (i == 1)
                    available = "Y";
                attempts = 0;
                valuesPart = "VALUES ('" + topic + "', " + level + ", " + stage + ",'" + available + "','" + completed + "'," + attempts + "," + attempts + "," + attempts + "," + attempts + " );";
                db.execSQL(INSERT_INTO_TABLE_MAIN + valuesPart);
            }

            for (int i = 1; i <= QuizCounts.treeLevel1Quizzes; i++) {
                topic = "tree";
                level = 1;
                stage = i;
                available = "N";
                completed = "N";
                if (i == 1)
                    available = "Y";
                attempts = 0;
                valuesPart = "VALUES ('" + topic + "', " + level + ", " + stage + ",'" + available + "','" + completed + "'," + attempts + "," + attempts + "," + attempts + "," + attempts + " );";
                db.execSQL(INSERT_INTO_TABLE_MAIN + valuesPart);
            }

            for (int i = 1; i <= QuizCounts.treeLevel2Quizzes; i++) {
                topic = "tree";
                level = 2;
                stage = i;
                available = "N";
                completed = "N";
                if (i == 1)
                    available = "Y";
                attempts = 0;
                valuesPart = "VALUES ('" + topic + "', " + level + ", " + stage + ",'" + available + "','" + completed + "'," + attempts + "," + attempts + "," + attempts + "," + attempts + " );";
                db.execSQL(INSERT_INTO_TABLE_MAIN + valuesPart);
            }

            for (int i = 1; i <= QuizCounts.treeLevel3Quizzes; i++) {
                topic = "tree";
                level = 3;
                stage = i;
                available = "N";
                completed = "N";
                if (i == 1)
                    available = "Y";
                attempts = 0;
                valuesPart = "VALUES ('" + topic + "', " + level + ", " + stage + ",'" + available + "','" + completed + "'," + attempts + "," + attempts + "," + attempts + "," + attempts + " );";
                db.execSQL(INSERT_INTO_TABLE_MAIN + valuesPart);
            }

            for (int i = 1; i <= QuizCounts.bstLevel1Quizzes; i++) {
                topic = "bst";
                level = 1;
                stage = i;
                available = "N";
                completed = "N";
                if (i == 1)
                    available = "Y";
                attempts = 0;
                valuesPart = "VALUES ('" + topic + "', " + level + ", " + stage + ",'" + available + "','" + completed + "'," + attempts + "," + attempts + "," + attempts + "," + attempts + " );";
                db.execSQL(INSERT_INTO_TABLE_MAIN + valuesPart);
            }

            for (int i = 1; i <= QuizCounts.bstLevel2Quizzes; i++) {
                topic = "bst";
                level = 2;
                stage = i;
                available = "N";
                completed = "N";
                if (i == 1)
                    available = "Y";
                attempts = 0;
                valuesPart = "VALUES ('" + topic + "', " + level + ", " + stage + ",'" + available + "','" + completed + "'," + attempts + "," + attempts + "," + attempts + "," + attempts + " );";
                db.execSQL(INSERT_INTO_TABLE_MAIN + valuesPart);
            }

            for (int i = 1; i <= QuizCounts.bstLevel3Quizzes; i++) {
                topic = "bst";
                level = 3;
                stage = i;
                available = "N";
                completed = "N";
                if (i == 1)
                    available = "Y";
                attempts = 0;
                valuesPart = "VALUES ('" + topic + "', " + level + ", " + stage + ",'" + available + "','" + completed + "'," + attempts + "," + attempts + "," + attempts + "," + attempts + " );";
                db.execSQL(INSERT_INTO_TABLE_MAIN + valuesPart);
            }

            for (int i = 1; i <= QuizCounts.heapLevel1Quizzes; i++) {
                topic = "heap";
                level = 1;
                stage = i;
                available = "N";
                completed = "N";
                if (i == 1)
                    available = "Y";
                attempts = 0;
                valuesPart = "VALUES ('" + topic + "', " + level + ", " + stage + ",'" + available + "','" + completed + "'," + attempts + "," + attempts + "," + attempts + "," + attempts + " );";
                db.execSQL(INSERT_INTO_TABLE_MAIN + valuesPart);
            }

            for (int i = 1; i <= QuizCounts.heapLevel2Quizzes; i++) {
                topic = "heap";
                level = 2;
                stage = i;
                available = "N";
                completed = "N";
                if (i == 1)
                    available = "Y";
                attempts = 0;
                valuesPart = "VALUES ('" + topic + "', " + level + ", " + stage + ",'" + available + "','" + completed + "'," + attempts + "," + attempts + "," + attempts + "," + attempts + " );";
                db.execSQL(INSERT_INTO_TABLE_MAIN + valuesPart);
            }

            for (int i = 1; i <= QuizCounts.heapLevel3Quizzes; i++) {
                topic = "heap";
                level = 3;
                stage = i;
                available = "N";
                completed = "N";
                if (i == 1)
                    available = "Y";
                attempts = 0;
                valuesPart = "VALUES ('" + topic + "', " + level + ", " + stage + ",'" + available + "','" + completed + "'," + attempts + "," + attempts + "," + attempts + "," + attempts + " );";
                db.execSQL(INSERT_INTO_TABLE_MAIN + valuesPart);
            }

            for (int i = 1; i <= QuizCounts.graphLevel1Quizzes; i++) {
                topic = "graph";
                level = 1;
                stage = i;
                available = "N";
                completed = "N";
                if (i == 1)
                    available = "Y";
                attempts = 0;
                valuesPart = "VALUES ('" + topic + "', " + level + ", " + stage + ",'" + available + "','" + completed + "'," + attempts + "," + attempts + "," + attempts + "," + attempts + " );";
                db.execSQL(INSERT_INTO_TABLE_MAIN + valuesPart);
            }

            for (int i = 1; i <= QuizCounts.graphLevel2Quizzes; i++) {
                topic = "graph";
                level = 2;
                stage = i;
                available = "N";
                completed = "N";
                if (i == 1)
                    available = "Y";
                attempts = 0;
                valuesPart = "VALUES ('" + topic + "', " + level + ", " + stage + ",'" + available + "','" + completed + "'," + attempts + "," + attempts + "," + attempts + "," + attempts + " );";
                db.execSQL(INSERT_INTO_TABLE_MAIN + valuesPart);
            }

            for (int i = 1; i <= QuizCounts.graphLevel3Quizzes; i++) {
                topic = "graph";
                level = 3;
                stage = i;
                available = "N";
                completed = "N";
                if (i == 1)
                    available = "Y";
                attempts = 0;
                valuesPart = "VALUES ('" + topic + "', " + level + ", " + stage + ",'" + available + "','" + completed + "'," + attempts + "," + attempts + "," + attempts + "," + attempts + " );";
                db.execSQL(INSERT_INTO_TABLE_MAIN + valuesPart);
            }

            for (int i = 1; i <= QuizCounts.hashingLevel1Quizzes; i++) {
                topic = "hashing";
                level = 1;
                stage = i;
                available = "N";
                completed = "N";
                if (i == 1)
                    available = "Y";
                attempts = 0;
                valuesPart = "VALUES ('" + topic + "', " + level + ", " + stage + ",'" + available + "','" + completed + "'," + attempts + "," + attempts + "," + attempts + "," + attempts + " );";
                db.execSQL(INSERT_INTO_TABLE_MAIN + valuesPart);
            }

            for (int i = 1; i <= QuizCounts.hashingLevel2Quizzes; i++) {
                topic = "hashing";
                level = 2;
                stage = i;
                available = "N";
                completed = "N";
                if (i == 1)
                    available = "Y";
                attempts = 0;
                valuesPart = "VALUES ('" + topic + "', " + level + ", " + stage + ",'" + available + "','" + completed + "'," + attempts + "," + attempts + "," + attempts + "," + attempts + " );";
                db.execSQL(INSERT_INTO_TABLE_MAIN + valuesPart);
            }

            for (int i = 1; i <= QuizCounts.hashingLevel3Quizzes; i++) {
                topic = "hashing";
                level = 3;
                stage = i;
                available = "N";
                completed = "N";
                if (i == 1)
                    available = "Y";
                attempts = 0;
                valuesPart = "VALUES ('" + topic + "', " + level + ", " + stage + ",'" + available + "','" + completed + "'," + attempts + "," + attempts + "," + attempts + "," + attempts + " );";
                db.execSQL(INSERT_INTO_TABLE_MAIN + valuesPart);
            }

        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        }

    }


    public void addLogEntry(String activityName, String actionPerformed) {
        sqLiteDatabase = dbHelper.getWritableDatabase();
        Date current = new Date();
        DateFormat sdfDate = new SimpleDateFormat("dd/MM/yyyy");
        DateFormat sdfTime = new SimpleDateFormat("HH:mm:ss");
        String valuesPart = " VALUES ('" + activityName + "', '" + actionPerformed + "', '" + sdfDate.format(current) + "', '" + sdfTime.format(current) + "' );";
        sqLiteDatabase.execSQL(DBHelper.INSERT_INTO_TABLE_ACTIVITY_LOG + valuesPart);
        dbHelper.close();
    }

    public String getUserName() {
        sqLiteDatabase = dbHelper.getReadableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery("SELECT " + BackUpDBKeys.BACKUPDB_KEY_USERNAME + " FROM " + DBHelper.TABLE_BACKUP, null);
        cursor.moveToNext();
        String username = cursor.getString(0);
        cursor.close();
        dbHelper.close();
        return username;
    }

    public String getUserType() {
        sqLiteDatabase = dbHelper.getReadableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery("SELECT " + BackUpDBKeys.BACKUPDB_KEY_USER_TYPE + " FROM " + DBHelper.TABLE_BACKUP, null);
        cursor.moveToNext();
        String username = cursor.getString(0);
        cursor.close();
        dbHelper.close();
        return username;
    }

    public void insertQuizStatus(String topic, int level, int stage, String startDate, String startTime, String endDate, String endTime, long timeTakenDuringQuiz, int timersUsedDuringQuiz, int extraLivesUsedDuringQuiz, String result) {
        sqLiteDatabase = dbHelper.getWritableDatabase();
        String valuesPart = " VALUES ('" + topic + "', " + level + ", " + stage + ", '" + startDate + "', '" + startTime + "', '" + endDate + "', '" + endTime + "', " + timeTakenDuringQuiz + ", " + timersUsedDuringQuiz + ", " + extraLivesUsedDuringQuiz + ",'" + result + "' );";
        sqLiteDatabase.execSQL(DBHelper.INSERT_INTO_TABLE_QUIZ_STATS + valuesPart);
        dbHelper.close();
    }

    public void createBackupTable(ArrayList<BackUpDTO> backUpValuesList) {
        sqLiteDatabase = dbHelper.getWritableDatabase();
        StringBuilder rawString = new StringBuilder("");
        rawString.append("CREATE TABLE " + DBHelper.TABLE_BACKUP + "( app_type VARCHAR(10), ");
        for (int i = 0; i < backUpValuesList.size() - 1; i++) {
            rawString.append(backUpValuesList.get(i).getKey() + " VARCHAR (20), ");
        }
        rawString.append(backUpValuesList.get(backUpValuesList.size() - 1).getKey() + " VARCHAR (20) );");
        sqLiteDatabase.execSQL(String.valueOf(rawString));
        rawString = new StringBuilder("");
        rawString.append("INSERT INTO " + DBHelper.TABLE_BACKUP + " VALUES( 'GAMIFIED', ");
        for (int i = 0; i < backUpValuesList.size() - 1; i++) {
            rawString.append("'" + backUpValuesList.get(i).getValue() + "', ");
        }
        rawString.append("'" + backUpValuesList.get(backUpValuesList.size() - 1).getValue() + "' );");
        sqLiteDatabase.execSQL(String.valueOf(rawString));
        dbHelper.close();
    }

    public BackUpValuesDTO getBackUpData() {
        BackUpValuesDTO backUpValuesDTO = new BackUpValuesDTO();

        sqLiteDatabase = dbHelper.getReadableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery("SELECT * FROM " + DBHelper.TABLE_BACKUP, null);
        cursor.moveToNext();

        backUpValuesDTO.setCoins(Integer.parseInt(cursor.getString(cursor.getColumnIndex(BackUpDBKeys.BACKUPDB_KEY_COINS))));

        backUpValuesDTO.setBronzeTimersBought(Integer.parseInt(cursor.getString(cursor.getColumnIndex(BackUpDBKeys.BACKUPDB_KEY_BRONZE_TIMERS_BOUGHT))));
        backUpValuesDTO.setBronzeTimersCollected(Integer.parseInt(cursor.getString(cursor.getColumnIndex(BackUpDBKeys.BACKUPDB_KEY_BRONZE_TIMERS_COLLECTED))));
        backUpValuesDTO.setBronzeTimersUsed(Integer.parseInt(cursor.getString(cursor.getColumnIndex(BackUpDBKeys.BACKUPDB_KEY_BRONZE_TIMERS_USED))));

        backUpValuesDTO.setSilverTimersBought(Integer.parseInt(cursor.getString(cursor.getColumnIndex(BackUpDBKeys.BACKUPDB_KEY_SILVER_TIMERS_BOUGHT))));
        backUpValuesDTO.setSilverTimersCollected(Integer.parseInt(cursor.getString(cursor.getColumnIndex(BackUpDBKeys.BACKUPDB_KEY_SILVER_TIMERS_COLLECTED))));
        backUpValuesDTO.setSilverTimersUsed(Integer.parseInt(cursor.getString(cursor.getColumnIndex(BackUpDBKeys.BACKUPDB_KEY_SILVER_TIMERS_USED))));

        backUpValuesDTO.setGoldTimersBought(Integer.parseInt(cursor.getString(cursor.getColumnIndex(BackUpDBKeys.BACKUPDB_KEY_GOLD_TIMERS_BOUGHT))));
        backUpValuesDTO.setGoldTimersCollected(Integer.parseInt(cursor.getString(cursor.getColumnIndex(BackUpDBKeys.BACKUPDB_KEY_GOLD_TIMERS_COLLECTED))));
        backUpValuesDTO.setGoldTimersUsed(Integer.parseInt(cursor.getString(cursor.getColumnIndex(BackUpDBKeys.BACKUPDB_KEY_GOLD_TIMERS_USED))));

        backUpValuesDTO.setExtraLivesBought(Integer.parseInt(cursor.getString(cursor.getColumnIndex(BackUpDBKeys.BACKUPDB_KEY_EXTRA_LIVES_BOUGHT))));
        backUpValuesDTO.setExtraLivesCollected(Integer.parseInt(cursor.getString(cursor.getColumnIndex(BackUpDBKeys.BACKUPDB_KEY_EXTRA_LIVES_COLLECTED))));
        backUpValuesDTO.setExtraLivesUsed(Integer.parseInt(cursor.getString(cursor.getColumnIndex(BackUpDBKeys.BACKUPDB_KEY_EXTRA_LIVES_USED))));

        backUpValuesDTO.setXpLevel(Integer.parseInt(cursor.getString(cursor.getColumnIndex(BackUpDBKeys.BACKUPDB_KEY_XP_LEVEL))));
        backUpValuesDTO.setXpTotalPoints(Integer.parseInt(cursor.getString(cursor.getColumnIndex(BackUpDBKeys.BACKUPDB_KEY_XP_TOTAL_POINTS))));
        backUpValuesDTO.setXpLevelPrevTarget(Integer.parseInt(cursor.getString(cursor.getColumnIndex(BackUpDBKeys.BACKUPDB_KEY_XP_LEVEL_PREV_TARGET))));
        backUpValuesDTO.setXpLevelNextTarget(Integer.parseInt(cursor.getString(cursor.getColumnIndex(BackUpDBKeys.BACKUPDB_KEY_XP_LEVEL_NEXT_TARGET))));

        backUpValuesDTO.setTimersTarget(Integer.parseInt(cursor.getString(cursor.getColumnIndex(BackUpDBKeys.BACKUPDB_KEY_TIMERS_TARGET))));
        backUpValuesDTO.setTimersReward(Integer.parseInt(cursor.getString(cursor.getColumnIndex(BackUpDBKeys.BACKUPDB_KEY_TIMERS_REWARD))));

        backUpValuesDTO.setExtraLivesTarget(Integer.parseInt(cursor.getString(cursor.getColumnIndex(BackUpDBKeys.BACKUPDB_KEY_EXTRA_LIVES_TARGET))));
        backUpValuesDTO.setExtraLivesReward(Integer.parseInt(cursor.getString(cursor.getColumnIndex(BackUpDBKeys.BACKUPDB_KEY_EXTRA_LIVES_REWARD))));

        backUpValuesDTO.setDaysStreak(Integer.parseInt(cursor.getString(cursor.getColumnIndex(BackUpDBKeys.BACKUPDB_KEY_DAYS_STREAK))));
        backUpValuesDTO.setDaysTarget(Integer.parseInt(cursor.getString(cursor.getColumnIndex(BackUpDBKeys.BACKUPDB_KEY_DAYS_TARGET))));
        backUpValuesDTO.setDaysReward(Integer.parseInt(cursor.getString(cursor.getColumnIndex(BackUpDBKeys.BACKUPDB_KEY_DAYS_REWARD))));

        backUpValuesDTO.setAssignmentsCompleted(Integer.parseInt(cursor.getString(cursor.getColumnIndex(BackUpDBKeys.BACKUPDB_KEY_ASSIGNMENTS_COMPLETED))));
        backUpValuesDTO.setAssignmentsTarget(Integer.parseInt(cursor.getString(cursor.getColumnIndex(BackUpDBKeys.BACKUPDB_KEY_ASSIGNMENTS_TARGET))));
        backUpValuesDTO.setAssignmentsReward(Integer.parseInt(cursor.getString(cursor.getColumnIndex(BackUpDBKeys.BACKUPDB_KEY_ASSIGNMENTS_REWARD))));

        backUpValuesDTO.setStagesTarget(Integer.parseInt(cursor.getString(cursor.getColumnIndex(BackUpDBKeys.BACKUPDB_KEY_STAGES_TARGET))));
        backUpValuesDTO.setStagesReward(Integer.parseInt(cursor.getString(cursor.getColumnIndex(BackUpDBKeys.BACKUPDB_KEY_STAGES_REWARD))));

        backUpValuesDTO.setAllStagesAssignmentsCompleted(Boolean.parseBoolean(cursor.getString(cursor.getColumnIndex(BackUpDBKeys.BACKUPDB_KEY_ALL_STAGES_ASSIGNMENTS_COMPLETED))));

        backUpValuesDTO.setLastDateTimeExtraLife(cursor.getString(cursor.getColumnIndex(BackUpDBKeys.BACKUPDB_KEY_LAST_DATE_TIME_EXTRA_LIFE)));
        backUpValuesDTO.setLastDateTimeBronzeTimer(cursor.getString(cursor.getColumnIndex(BackUpDBKeys.BACKUPDB_KEY_LAST_DATE_TIME_BRONZE_TIMER)));
        backUpValuesDTO.setLastDateTimeSilverTimer(cursor.getString(cursor.getColumnIndex(BackUpDBKeys.BACKUPDB_KEY_LAST_DATE_TIME_SILVER_TIMER)));
        backUpValuesDTO.setLastDateTimeGoldTimer(cursor.getString(cursor.getColumnIndex(BackUpDBKeys.BACKUPDB_KEY_LAST_DATE_TIME_GOLD_TIMER)));

        backUpValuesDTO.setLastActiveDate(cursor.getString(cursor.getColumnIndex(BackUpDBKeys.BACKUPDB_KEY_LAST_ACTIVE_DATE)));

        cursor.close();
        dbHelper.close();

        return backUpValuesDTO;
    }

    public void updateValues(ArrayList<BackUpDTO> backUpValuesList) {
        sqLiteDatabase = dbHelper.getWritableDatabase();
        StringBuilder rawString = new StringBuilder("");
        rawString.append(" SET " + backUpValuesList.get(0).getKey() + " = '" + backUpValuesList.get(0).getValue() + "', ");
        for (int i = 1; i < backUpValuesList.size() - 1; i++) {
            rawString.append(backUpValuesList.get(i).getKey() + " = '" + backUpValuesList.get(i).getValue() + "', ");
        }
        rawString.append(backUpValuesList.get(backUpValuesList.size() - 1).getKey() + " = '" + backUpValuesList.get(backUpValuesList.size() - 1).getValue() + "'");
        String updateString = "UPDATE " + DBHelper.TABLE_BACKUP + rawString;
        sqLiteDatabase.execSQL(updateString);
        dbHelper.close();
    }

    public ArrayList<StageContentDTO> getStagesStatus(String topic, int level) {
        ArrayList<StageContentDTO> list = new ArrayList<StageContentDTO>();
        sqLiteDatabase = dbHelper.getReadableDatabase();

        Cursor cursor = sqLiteDatabase.rawQuery("SELECT " + DBHelper.KEY_STAGE + ", " + DBHelper.KEY_AVAILABLE + ", " + DBHelper.KEY_COMPLETED + " FROM " + DBHelper.TABLE_MAIN + " WHERE " + DBHelper.KEY_TOPIC + " = '" + topic + "' AND " + DBHelper.KEY_LEVEL + " = " + level + " ORDER BY " + DBHelper.KEY_STAGE, null);
        StageContentDTO obj;
        while (cursor.moveToNext()) {
            obj = new StageContentDTO();
            obj.setStageNo(cursor.getInt(cursor.getColumnIndex(DBHelper.KEY_STAGE)));
            obj.setAvailable(cursor.getString(cursor.getColumnIndex(DBHelper.KEY_AVAILABLE)));
            obj.setCompleted(cursor.getString(cursor.getColumnIndex(DBHelper.KEY_COMPLETED)));
            list.add(obj);
        }
        cursor.close();
        dbHelper.close();
        return list;
    }

    public StageAttemptsDTO getStageAttempts(String topic, int level, int stage) {
        sqLiteDatabase = dbHelper.getReadableDatabase();
        StageAttemptsDTO stageAttemptsDTO = new StageAttemptsDTO();

        Cursor cursor = sqLiteDatabase.rawQuery("SELECT " + DBHelper.KEY_ATTEMPTS + ", " + DBHelper.KEY_ATTEMPTS_FAILED + ", " + DBHelper.KEY_QUESTIONS_ATTEMPTED + ", " + DBHelper.KEY_QUESTIONS_ANSWERED_WRONG + " FROM " + DBHelper.TABLE_MAIN + " WHERE " + DBHelper.KEY_TOPIC + " = '" + topic + "' AND " + DBHelper.KEY_LEVEL + " = " + level + "  AND " + DBHelper.KEY_STAGE + " = " + stage, null);
        cursor.moveToNext();
        stageAttemptsDTO.setTotalAttempts(cursor.getInt(cursor.getColumnIndex(DBHelper.KEY_ATTEMPTS)));
        stageAttemptsDTO.setFailedAttempts(cursor.getInt(cursor.getColumnIndex(DBHelper.KEY_ATTEMPTS_FAILED)));
        stageAttemptsDTO.setQuestionsAttempted(cursor.getInt(cursor.getColumnIndex(DBHelper.KEY_QUESTIONS_ATTEMPTED)));
        stageAttemptsDTO.setQuestionsAnsweredWrong(cursor.getInt(cursor.getColumnIndex(DBHelper.KEY_QUESTIONS_ANSWERED_WRONG)));
        cursor.close();
        dbHelper.close();
        return stageAttemptsDTO;
    }

    public void updateAttempts(String topic, int level, int stage, int attempts, int questionsCount) {
        sqLiteDatabase = dbHelper.getWritableDatabase();
        sqLiteDatabase.execSQL("UPDATE " + DBHelper.TABLE_MAIN + " SET " + DBHelper.KEY_ATTEMPTS + " = " + attempts + ", " + DBHelper.KEY_QUESTIONS_ATTEMPTED + " = " + DBHelper.KEY_QUESTIONS_ATTEMPTED + " + " + questionsCount + " WHERE " + DBHelper.KEY_TOPIC + " = '" + topic + "'  AND " + DBHelper.KEY_LEVEL + " = " + level + " AND " + DBHelper.KEY_STAGE + " = " + stage);
        dbHelper.close();
    }

    public void increaseFailedAttempts(String topic, int level, int stage) {
        sqLiteDatabase = dbHelper.getWritableDatabase();
        sqLiteDatabase.execSQL("UPDATE " + DBHelper.TABLE_MAIN + " SET " + DBHelper.KEY_ATTEMPTS_FAILED + " = " + DBHelper.KEY_ATTEMPTS_FAILED + " +1 WHERE " + DBHelper.KEY_TOPIC + " = '" + topic + "'  AND " + DBHelper.KEY_LEVEL + " = " + level + " AND " + DBHelper.KEY_STAGE + " = " + stage);
        dbHelper.close();
    }

    public void increaseWrongAnswersCount(String topic, int level, int stage) {
        sqLiteDatabase = dbHelper.getWritableDatabase();
        sqLiteDatabase.execSQL("UPDATE " + DBHelper.TABLE_MAIN + " SET " + DBHelper.KEY_QUESTIONS_ANSWERED_WRONG + " = " + DBHelper.KEY_QUESTIONS_ANSWERED_WRONG + " +1 WHERE " + DBHelper.KEY_TOPIC + " = '" + topic + "'  AND " + DBHelper.KEY_LEVEL + " = " + level + " AND " + DBHelper.KEY_STAGE + " = " + stage);
        dbHelper.close();
    }

    public void updateStageStatus(String topic, int level, int stage) {
        sqLiteDatabase = dbHelper.getWritableDatabase();
        sqLiteDatabase.execSQL("UPDATE " + DBHelper.TABLE_MAIN + " SET " + DBHelper.KEY_COMPLETED + " = 'Y' WHERE " + DBHelper.KEY_TOPIC + " = '" + topic + "'  AND " + DBHelper.KEY_LEVEL + " = " + level + " AND " + DBHelper.KEY_STAGE + " = " + stage);
        sqLiteDatabase.execSQL("UPDATE " + DBHelper.TABLE_MAIN + " SET " + DBHelper.KEY_AVAILABLE + " = 'Y' WHERE " + DBHelper.KEY_TOPIC + " = '" + topic + "'  AND " + DBHelper.KEY_LEVEL + " = " + level + " AND " + DBHelper.KEY_STAGE + " = " + (stage + 1));
        dbHelper.close();
    }

    public int getCompletedStagesNumber() {
        sqLiteDatabase = dbHelper.getReadableDatabase();
        int num = -1;
        Cursor cursor = sqLiteDatabase.rawQuery("SELECT COUNT(" + DBHelper.KEY_COMPLETED + ") FROM " + DBHelper.TABLE_MAIN + " WHERE " + DBHelper.KEY_COMPLETED + " = 'Y'", null);
        cursor.moveToFirst();
        num = cursor.getInt(0);
        cursor.close();
        dbHelper.close();
        return num;
    }

    public int getStageCompletedCountForLevel(String level) {
        sqLiteDatabase = dbHelper.getReadableDatabase();
        int count = 0;
        Cursor cursor = sqLiteDatabase.rawQuery("SELECT COUNT(" + DBHelper.KEY_COMPLETED + ") FROM " + DBHelper.TABLE_MAIN + " WHERE " + DBHelper.KEY_COMPLETED + " = 'Y' AND " + DBHelper.KEY_LEVEL + " = '" + level + "'", null);
        cursor.moveToFirst();
        count = cursor.getInt(0);
        cursor.close();
        dbHelper.close();
        return count;
    }

    public StageAttemptsDTO getQuizAttempts() {
        sqLiteDatabase = dbHelper.getReadableDatabase();
        StageAttemptsDTO stageAttemptsDTO = new StageAttemptsDTO();

        Cursor cursor = sqLiteDatabase.rawQuery("SELECT SUM(" + DBHelper.KEY_ATTEMPTS + "), SUM(" + DBHelper.KEY_ATTEMPTS_FAILED + "), SUM(" + DBHelper.KEY_QUESTIONS_ATTEMPTED + "), SUM(" + DBHelper.KEY_QUESTIONS_ANSWERED_WRONG + ") FROM " + DBHelper.TABLE_MAIN, null);
        cursor.moveToNext();
        stageAttemptsDTO.setTotalAttempts(cursor.getInt(0));
        stageAttemptsDTO.setFailedAttempts(cursor.getInt(1));
        stageAttemptsDTO.setQuestionsAttempted(cursor.getInt(2));
        stageAttemptsDTO.setQuestionsAnsweredWrong(cursor.getInt(3));
        cursor.close();
        dbHelper.close();
        return stageAttemptsDTO;
    }

    public void addProgressEntry(String startDate, String startTime, String endDate, String endTime, long timeDiff, int experienceGained, String activityName) {
        sqLiteDatabase = dbHelper.getWritableDatabase();

        Cursor cursor = sqLiteDatabase.rawQuery("SELECT SUM(" + DBHelper.KEY_ATTEMPTS + "), SUM(" + DBHelper.KEY_ATTEMPTS_FAILED + "), SUM(" + DBHelper.KEY_QUESTIONS_ATTEMPTED + "), SUM(" + DBHelper.KEY_QUESTIONS_ANSWERED_WRONG + ") FROM " + DBHelper.TABLE_MAIN, null);
        cursor.moveToNext();
        int totalQuizAttempts = cursor.getInt(0);
        int totalFailedAttempts = cursor.getInt(1);
        int totalQuestionsAttempts = cursor.getInt(2);
        int totalQuestionsWrongAnswered = cursor.getInt(3);
        cursor.close();

        int totalCompletedQuizzes = 0;
        cursor = sqLiteDatabase.rawQuery("SELECT COUNT(" + DBHelper.KEY_COMPLETED + ") FROM " + DBHelper.TABLE_MAIN + " WHERE " + DBHelper.KEY_COMPLETED + " = 'Y'", null);
        cursor.moveToFirst();
        totalCompletedQuizzes = cursor.getInt(0);
        cursor.close();

        DecimalFormat df = new DecimalFormat("#.00");
        double cmpltn = (totalCompletedQuizzes * 100) / (double) QuizCounts.totalQuizzes;
        double completion = Double.parseDouble(df.format(cmpltn));
        String valuesPart = "VALUES ('" + startDate + "', '" + startTime + "', '" + endDate + "', '" + endTime + "', " + timeDiff + ", " + completion + ", " + totalQuizAttempts + ", " + totalFailedAttempts + "," + totalQuestionsAttempts + ", " + totalQuestionsWrongAnswered + ", " + experienceGained + ", '" + activityName + "' );";

        sqLiteDatabase.execSQL(DBHelper.INSERT_INTO_TABLE_PROGRESS + valuesPart);
        dbHelper.close();
    }

    public ArrayList<ProgressDTO> getProgressDTO() {
        sqLiteDatabase = dbHelper.getReadableDatabase();
        ArrayList<ProgressDTO> list = new ArrayList<>();

        Cursor cursor = sqLiteDatabase.rawQuery("SELECT * FROM " + DBHelper.TABLE_PROGRESS, null);
        ProgressDTO obj;
        while (cursor.moveToNext()) {
            obj = new ProgressDTO();
            obj.setStartDate(cursor.getString(cursor.getColumnIndex(DBHelper.KEY_START_DATE)));
            obj.setStartTime(cursor.getString(cursor.getColumnIndex(DBHelper.KEY_START_TIME)));
            obj.setEndDate(cursor.getString(cursor.getColumnIndex(DBHelper.KEY_END_DATE)));
            obj.setEndTime(cursor.getString(cursor.getColumnIndex(DBHelper.KEY_END_TIME)));
            obj.setTimeDiff(cursor.getInt(cursor.getColumnIndex(DBHelper.KEY_TIME_SPENT)));
            obj.setCompletionPercentage(cursor.getDouble(cursor.getColumnIndex(DBHelper.KEY_COMPLETION_PERCENTAGE)));
            obj.setQuizAttempts(cursor.getInt(cursor.getColumnIndex(DBHelper.KEY_ATTEMPTS)));
            obj.setFailedAttempts(cursor.getInt(cursor.getColumnIndex(DBHelper.KEY_ATTEMPTS_FAILED)));
            obj.setQuestionAttempts(cursor.getInt(cursor.getColumnIndex(DBHelper.KEY_QUESTIONS_ATTEMPTED)));
            obj.setWronglyAnswered(cursor.getInt(cursor.getColumnIndex(DBHelper.KEY_QUESTIONS_ANSWERED_WRONG)));
            obj.setExperienceGained(cursor.getInt(cursor.getColumnIndex(DBHelper.KEY_EXPERIENCE_GAINED)));
            obj.setActivityName(cursor.getString(cursor.getColumnIndex(DBHelper.KEY_ACTIVITY)));
            list.add(obj);
        }
        cursor.close();
        dbHelper.close();
        return list;
    }
}
