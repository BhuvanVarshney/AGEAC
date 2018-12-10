package com.research.ageac.gamified;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DaysStreakResolver{

    void resolveDaysStreak(Context context) {
        SharedPreferences sharedPreferences = context.getSharedPreferences("Extras", context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        Date todaysDate = null, lastDate = null;
        int daysStreak;
        String lastApplicationOpenDate;
        DateFormat sdfDate = new SimpleDateFormat("dd/MM/yyyy");
        daysStreak = sharedPreferences.getInt(BackUpDBKeys.BACKUPDB_KEY_DAYS_STREAK, 0);
        lastApplicationOpenDate = sharedPreferences.getString(BackUpDBKeys.BACKUPDB_KEY_LAST_ACTIVE_DATE, "01/01/0001");
        try {
            lastDate = sdfDate.parse(lastApplicationOpenDate);
            todaysDate = sdfDate.parse(sdfDate.format(new Date()));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        int days = (int) ((todaysDate.getTime() - lastDate.getTime()) / 86400000);
        Log.e("Time Diff - ", String.valueOf(todaysDate.getTime() - lastDate.getTime()));
        Log.e("Days Diff - ", String.valueOf(days));
        if (days >= 1) {
            if (days == 1) {
                daysStreak++;
                editor.putString(BackUpDBKeys.BACKUPDB_KEY_LAST_ACTIVE_DATE, sdfDate.format(todaysDate));
                editor.putInt(BackUpDBKeys.BACKUPDB_KEY_DAYS_STREAK, daysStreak);
                editor.apply();
            } else if (days > 1) {
                editor.putString(BackUpDBKeys.BACKUPDB_KEY_LAST_ACTIVE_DATE, sdfDate.format(todaysDate));
                editor.putInt(BackUpDBKeys.BACKUPDB_KEY_DAYS_STREAK, 1);
                editor.apply();
            }
        }
    }
}
