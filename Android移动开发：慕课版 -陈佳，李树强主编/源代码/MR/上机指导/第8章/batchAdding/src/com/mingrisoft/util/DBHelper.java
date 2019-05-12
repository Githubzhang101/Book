package com.mingrisoft.util;

import java.util.ArrayList;
import java.util.List;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBHelper {
    private static final String DATABASE_NAME = "datastorage";// �������ݿ�����
    private static final int DATABASE_VERSION = 1;// �������ݿ�汾��
    private static final String TABLE_NAME = "numbers";// ���������
    private static final String[] COLUMNS = { "_id", "number", "square", "cube" };
    private DBOpenHelper helper;
    private SQLiteDatabase db;

    private static class DBOpenHelper extends SQLiteOpenHelper {
        private static final String CREATE_TABLE = "create table " + TABLE_NAME + " ( " + COLUMNS[0] + " integer primary key autoincrement, " + COLUMNS[1]
                + " integer, " + COLUMNS[2] + " integer, " + COLUMNS[3] + " integer);";// ���崴������SQL���

        public DBOpenHelper(Context context) {
            super(context, DATABASE_NAME, null, DATABASE_VERSION);
        }

        @Override
        public void onCreate(SQLiteDatabase db) {
            db.execSQL(CREATE_TABLE);// �������
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            db.execSQL("drop table if exists " + TABLE_NAME);// ɾ���ɰ���
            onCreate(db);// �������
        }
    }

    public DBHelper(Context context) {
        helper = new DBOpenHelper(context);// ����SQLiteOpenHelper����
        db = helper.getWritableDatabase();// ��ÿ�д�����ݿ�
    }

    public void insert(DataBean data) {// �����в�������
        ContentValues values = new ContentValues();
        values.put(COLUMNS[1], data.getNumber());
        values.put(COLUMNS[2], data.getSquare());
        values.put(COLUMNS[3], data.getCube());
        db.insert(TABLE_NAME, null, values);
    }

    public List<String> queryAll() {
        List<String> result = new ArrayList<String>();
        Cursor cursor = db.query(TABLE_NAME, COLUMNS, null, null, null, null, null);
        while (cursor.moveToNext()) {
            result.add(cursor.getInt(1) + " " + cursor.getInt(2) + " " + cursor.getInt(3));
        }
        return result;
    }

}
