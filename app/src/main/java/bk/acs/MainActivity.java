package bk.acs;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import bk.acs.AddingToDatabase.MyDialog;
import bk.acs.RecyclerView.Data;
import bk.acs.RecyclerView.MyAdapter;
import bk.acs.databases.Main;

public class MainActivity extends AppCompatActivity {
    FloatingActionButton fab;
    RecyclerView recview;
    MyAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        fab=(FloatingActionButton)findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MyDialog dialog=new MyDialog();
                dialog.show(getFragmentManager(),"myDialog");
            }
        });
        recview=(RecyclerView)findViewById(R.id.recview);
        adapter=new MyAdapter(Data.getList(),this);
        recview.setLayoutManager(new LinearLayoutManager(recview.getContext()));
        recview.setAdapter(adapter);
    }
}
