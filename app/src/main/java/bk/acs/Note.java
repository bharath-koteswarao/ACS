package bk.acs;

import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import bk.acs.AddingToDatabase.MyDialog;

public class Note extends AppCompatActivity {

    Toolbar toolbar;
    NavigationDrawer drawer;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_note);
        toolbar=(Toolbar)findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        drawer=(NavigationDrawer)getSupportFragmentManager().findFragmentById(R.id.navDrawerNote);
        drawer.setUp((DrawerLayout)findViewById(R.id.drawer_layout),toolbar);
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater=getMenuInflater();
        menuInflater.inflate(R.menu.menu_main,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int res_id=item.getItemId();
        if(res_id==R.id.refresh)
        {
            MyDialog dialog=new MyDialog();
            dialog.show(getFragmentManager(),"myDialog");
        }
        return true;
    }
}
