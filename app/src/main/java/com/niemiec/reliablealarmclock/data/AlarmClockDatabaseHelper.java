package com.niemiec.reliablealarmclock.data;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class AlarmClockDatabaseHelper extends SQLiteOpenHelper {
    private static final String DB_NAME = "AlarmClockDB";
    private static final int DB_VERSION = 1;

    public AlarmClockDatabaseHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String createAlarmTable =   "CREATE TABLE ALARM (" +
                                    "_id INTEGER PRIMARY KEY AUTOINCREMENT," +
                                    "ALARM_CLOCK NUMERIC," +
                                    "BATTERY_PERCENTAGE INTEGER," +
                                    "TIME_TO_DISCHARGE INTEGER," +
                                    "ACTIVATED NUMERIC);";

        db.execSQL(createAlarmTable);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
