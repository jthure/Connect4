package com.jthure.connect4.android;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Environment;
import android.util.Log;

import com.jthure.connect4.util.IO;
import com.jthure.connect4.model.Player;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

/**
 * Created by Jonas on 2016-11-24.
 */

public class IOimpl extends IO implements Serializable {
    private static final String TAG=IOimpl.class.getSimpleName();

    private static final int DATBASE_VERSION = 1;
    private static final String DATBASE_NAME = "Connect4";

    private static final String USER_TABLE_NAME = "users";
    private static final String USER_COLUMN_NAME = "name";
    private static final String USER_COLUMN_WINS = "wins";

    private static final String[] USER_COLUMNS = {USER_COLUMN_NAME, USER_COLUMN_WINS};

    private static MyDB db;
    private Context context;


    public IOimpl(Context context) {
        this.context=context;
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
            log("New player saved: "+ player.getName());
        }else{
            log("Existing player updated: Name: "+ player.getName()+" Score: "+player.getWins());
        }
    }

    @Override
    public void log(String msg) {
        if(!isExternalStorageWritable())return;

        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss",Locale.getDefault());
        File file= new File(context.getExternalFilesDir(null),LOG_FILE);
        try {
            FileOutputStream fos = new FileOutputStream(file,true);
            String logMsg = df.format(new Date(System.currentTimeMillis()))+": "+msg;
            fos.write(logMsg.getBytes());
            Log.d(TAG,logMsg);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Checks if there are external storage available to write to
     */
    private boolean isExternalStorageWritable() {
        String state = Environment.getExternalStorageState();
        if (Environment.MEDIA_MOUNTED.equals(state)) {
            return true;
        }
        return false;
    }

    /**
     * Database containing records of the players. One single table with two columns, player name
     * and player score
     */
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
