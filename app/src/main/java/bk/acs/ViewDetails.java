package bk.acs;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Environment;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.io.File;
import java.util.ArrayList;
import java.util.Date;

import bk.acs.AddingToDatabase.MyDialog;
import bk.acs.databases.DatesList;

public class ViewDetails extends AppCompatActivity {

    Toolbar toolbar;
    NavigationDrawer drawer;
    ListView listView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_details);
        ArrayList<String> arr=new ArrayList<>();
        listView=(ListView)findViewById(R.id.listview);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        DatesList list=new DatesList(this,1);
        SQLiteDatabase db=list.getReadableDatabase();
        String cols[]={"Date"};
        Cursor cursor=db.query("DatesTable",cols,null,null,null,null,null);
        while (cursor.moveToNext()){
            arr.add(cursor.getString(0));
        }
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        drawer = (NavigationDrawer) getSupportFragmentManager().findFragmentById(R.id.navDrawerRate);
        drawer.setUp((DrawerLayout) findViewById(R.id.drawer_layout), toolbar);
        ArrayAdapter<String> adapter=new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,arr);
        listView.setAdapter(adapter);
        final ArrayList<String> arrInner=arr;
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            final ArrayList<String> inner=new ArrayList<>();
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent=new Intent(ViewDetails.this,FinalDetails.class);
                intent.putExtra("date",arrInner.get(i));
                startActivity(intent);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.menu_main, menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int res_id = item.getItemId();
        if (res_id == R.id.refresh) {
            MyDialog dialog = new MyDialog();
            dialog.show(getFragmentManager(), "myDialog");
        }
        return true;
    }
}
