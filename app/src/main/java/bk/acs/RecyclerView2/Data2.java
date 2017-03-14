package bk.acs.RecyclerView2;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Environment;
import android.util.Log;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

import bk.acs.databases.Main;

/**
 * Created by bk on 07-03-2017.
 */

public class Data2 {
    Context context;
    public List<ListItem2> list=new ArrayList<>();
    String cols[]={"FileName"};
    SQLiteDatabase database;
    int count;
    String subName,fileName;
    public Data2(Context ctx,String subName)
    {
        context=ctx;
        this.subName=subName;
        String selArgs[]={subName};
        Main db=new Main(context,"subjects_db",1,1);
        database=db.getReadableDatabase();
        Cursor cursor=database.query("SubjectsTable",cols,"SubjectName = ?",selArgs,null,null,null);
        cursor.moveToNext();
        fileName=cursor.getString(0);
    }
    public List getList() {
        for(int i=500;i<561;i++)
        {
            list.add(new ListItem2("0"+i,0));
        }
        return list;
    }
}
