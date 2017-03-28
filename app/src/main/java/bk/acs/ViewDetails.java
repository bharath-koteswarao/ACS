package bk.acs;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

import bk.acs.AddingToDatabase.MyDialog;
import bk.acs.databases.DatesList;

public class ViewDetails extends AppCompatActivity {

    Toolbar toolbar;
    NavigationDrawer drawer;
    ListView listView;
    ArrayAdapter<String> adapter;
    ArrayList<String> arr;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_details);
        arr = new ArrayList<>();
        listView = (ListView) findViewById(R.id.listview);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        DatesList list = new DatesList(this, 1);
        SQLiteDatabase db = list.getReadableDatabase();
        String cols[] = {"Date"};
        Cursor cursor = db.query("DatesTable", cols, null, null, null, null, null);
        while (cursor.moveToNext()) {
            arr.add(cursor.getString(0));
        }
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        drawer = (NavigationDrawer) getSupportFragmentManager().findFragmentById(R.id.navDrawerRate);
        drawer.setUp((DrawerLayout) findViewById(R.id.drawer_layout), toolbar);
        adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, arr);
        listView.setAdapter(adapter);
        final ArrayList<String> arrInner = arr;
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(ViewDetails.this, FinalDetails.class);
                intent.putExtra("date", arrInner.get(i));
                startActivity(intent);
            }
        });
        registerForContextMenu(listView);
    }
    void delete()
    {
        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
                Toast.makeText(ViewDetails.this, i+"", Toast.LENGTH_SHORT).show();
                return true;
            }
        });
    }
    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo)
    {
        super.onCreateContextMenu(menu,v,menuInfo);
        menu.setHeaderTitle("Click to delete");
        menu.add(0,v.getId(),0,"Delete");
    }
    @Override
    public boolean onContextItemSelected(MenuItem item)
    {
        if(item.getTitle()=="Delete")
        {
            delete();
        }
        return true;
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
