package bk.acs.RecyclerView;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import bk.acs.MainActivity;
import bk.acs.databases.Main;

/**
 * Created by bk on 01-03-2017.
 */

public class Data {
    public static ArrayList<String> sample = new ArrayList<>();
    public static List<ListItem> list = new ArrayList<>();
    String[] cols = {"SubjectName"};
    SQLiteDatabase db;
    Context ctx;

    public Data(Context context) {
        this.ctx = context;
        Main main = new Main(context, "subjects_db", 1, 1);
        db = main.getReadableDatabase();
        Cursor cursor = db.query("SubjectsTable", cols, null, null, null, null, null);
        while (cursor.moveToNext()) {
            sample.add(cursor.getString(0));
        }
        if (sample.size() != 0) {
            ((MainActivity) context).disableRecView();
        }
    }

    public static List getList() {

        for (int i = 0; i < sample.size(); i++) {
            ListItem listItem = new ListItem(sample.get(i), (i + 1) + ""); //Fixed crash
            list.add(listItem);
        }
        return list;
    }
}
