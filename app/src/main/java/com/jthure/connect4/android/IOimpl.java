package com.jthure.connect4.android;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.jthure.connect4.model.IO;
import com.jthure.connect4.model.Player;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Jonas on 2016-11-24.
 */

public class IOimpl extends IO implements Serializable {
    private static final int DATBASE_VERSION = 1;
    private static final String DATBASE_NAME = "Connect4";

    private static final String USER_TABLE_NAME = "users";
    private static final String USER_COLUMN_NAME = "name";
    private static final String USER_COLUMN_WINS = "wins";

    private static final String[] USER_COLUMNS = {USER_COLUMN_NAME, USER_COLUMN_WINS};

    private static MyDB db;


    public IOimpl(Context context) {
        db = new MyDB(context);
    }

    @Override
    public List<Player> loadPlayers() {
        SQLiteDatabase sqlDb = db.getReadableDatabase();
        Cursor res = sqlDb.query(USER_TABLE_NAME, USER_COLUMNS, null, null, null, null, null);
        List<Player> players = new ArrayList<>();
        if (res.moveToFirst()) {
            do {
                players.add(new Player(res.getString(0), res.getInt(1)));
            } while (res.moveToNext());
        }
        res.close();
        return players;
    }

    @Override
    public int getScore(String playerName) {
        SQLiteDatabase sqlDb = db.getReadableDatabase();
        String[]columns = {USER_COLUMN_WINS};
        Cursor res=sqlDb.query(USER_TABLE_NAME,columns,USER_COLUMN_NAME+" = '"+playerName+"'",null,null,null,null);
        int score = -1;
        if(res.moveToFirst())
            score=res.getInt(0);
        res.close();
        return score;
    }

    @Override
    protected void writePlayerToStorage(Player player) {
        SQLiteDatabase sqlDb = db.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(USER_COLUMN_WINS,player.getWins());
        int rows = sqlDb.update(USER_TABLE_NAME, values, USER_COLUMN_NAME + " = " + "'"+player.getName()+"'", null);
        if (rows == 0) {
            values.put(USER_COLUMN_NAME,player.getName());
            sqlDb.insert(USER_TABLE_NAME, null, values);
        }
    }

    @Override
    public void log(String msg) {

    }

    private static class MyDB extends SQLiteOpenHelper{

        public MyDB(Context context) {
            super(context, DATBASE_NAME, null, DATBASE_VERSION);
        }

        @Override
        public void onCreate(SQLiteDatabase sqLiteDatabase) {
            String sql = "CREATE TABLE " + USER_TABLE_NAME +
                    "(" + USER_COLUMN_NAME + " TEXT, " + USER_COLUMN_WINS + " INT);";
            sqLiteDatabase.execSQL(sql);

        }

        @Override
        public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
            sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + USER_TABLE_NAME);
            onCreate(sqLiteDatabase);
        }
    }


}
