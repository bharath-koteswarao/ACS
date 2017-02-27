package bk.acs.databases;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by koteswarao on 27-02-2017.
 */

public class Main extends SQLiteOpenHelper {
    String dbName;
    Context context;
    int newVersion,oldVersion;
    public Main(Context context , String dbName , int newVersion , int oldVersion)
    {
        super(context , dbName , null , newVersion);
        this.dbName=dbName;
        this.context=context;
        this.newVersion=newVersion;
        this.oldVersion=oldVersion;
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String createQry="CREATE TABLE SubjectsList(_id INTEGER PRIMARY KEY AUTOINCREMENT , SubjectName VARCHAR(100) , FileName VARCHAR(100));";
        sqLiteDatabase.execSQL(createQry);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
