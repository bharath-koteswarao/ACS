package bk.acs.databases;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.widget.Toast;

import java.io.FileOutputStream;
import java.util.List;

import bk.acs.RecyclerView2.ListItem2;

/**
 * Created by bk on 15-03-2017.
 */

public class DatesList extends SQLiteOpenHelper {

    Context context;
    public DatesList(Context context, int version) {
        super(context, "dates_db", null, version);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase)
    {
        String dateQry = "CREATE TABLE DatesTable(_id INTEGER PRIMARY KEY AUTOINCREMENT , Date VARCHAR(100));";
        sqLiteDatabase.execSQL(dateQry);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }

    public long execute(ContentValues values, List<ListItem2> list) throws Exception {
        String path = context.getFilesDir() + "/" + values.get("Date") + ".csv";
        String data = "";
        FileOutputStream fos = new FileOutputStream(path, false);
        for (int i = 0; i < list.size(); i++) {
            data = data + list.get(i).regno + "\n";
        }
        if (list.size()==0)
        {
            data="Full present";
        }
        fos.write(data.getBytes());
        long ret= getWritableDatabase().insert("DatesTable", null, values);
        return ret;
    }
}
