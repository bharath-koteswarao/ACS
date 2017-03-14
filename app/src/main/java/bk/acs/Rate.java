package bk.acs;

import android.os.Environment;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import java.io.File;

import bk.acs.AddingToDatabase.MyDialog;

public class Rate extends AppCompatActivity {

    Toolbar toolbar;
    NavigationDrawer drawer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rate);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        drawer = (NavigationDrawer) getSupportFragmentManager().findFragmentById(R.id.navDrawerRate);
        drawer.setUp((DrawerLayout) findViewById(R.id.drawer_layout), toolbar);
        String filepath= Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOCUMENTS).getAbsolutePath()+"/ATTENDANCE CSV/Inputs/a.csv";
        File f=new File(filepath);
        Log.d("File existence",f.exists()+"");
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
