package bk.acs;

import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import bk.acs.AddingToDatabase.MyDialog;
import bk.acs.RecyclerView.Data;
import bk.acs.RecyclerView.ListItem;
import bk.acs.RecyclerView.MyAdapter;

import static android.os.Environment.DIRECTORY_DOCUMENTS;
import static android.os.Environment.getExternalStoragePublicDirectory;

public class MainActivity extends AppCompatActivity {
    RecyclerView recview;
    MyAdapter adapter;
    Data d;
    Toolbar toolbar;
    NavigationDrawer drawer;
    String appName;
    LinearLayout main;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        appName = getResources().getString(R.string.app_name);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        d = new Data(this);
        recview = (RecyclerView) findViewById(R.id.recview);
        drawer = (NavigationDrawer) getSupportFragmentManager().findFragmentById(R.id.navDrawer);
        drawer.setUp((DrawerLayout) findViewById(R.id.drawer_layout), toolbar);
        adapter = new MyAdapter(d.getList(), this);
        recview.setLayoutManager(new LinearLayoutManager(recview.getContext()));
        recview.setAdapter(adapter);
        main = (LinearLayout) findViewById(R.id.MainLayout);
        try {
            createFolders();
        } catch (Exception e) {
            e.printStackTrace();
        }
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

    public void updateRecView(long num, String subname) {
        disableRecView();
        adapter.listdata.add(new ListItem(subname, num + ""));
        adapter.notifyItemInserted(adapter.listdata.size());
    }

    public void disableRecView() {
        main = (LinearLayout) findViewById(R.id.MainLayout);
        main.setVisibility(View.GONE);
    }

    public void createFolders() throws IOException {
        File mainFolder = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOCUMENTS).getAbsolutePath(), appName);
        File inputs = new File(mainFolder, "Inputs");
        File outputs = new File(mainFolder, "Outputs");
        if (mainFolder.exists()) {
            Toast.makeText(this, "Welcome", Toast.LENGTH_SHORT).show();
            File temp = new File(inputs, "temp.txt");
            temp.createNewFile();
        } else {
            Toast.makeText(this, "Creating Folders", Toast.LENGTH_SHORT).show();
            mainFolder.mkdir();
            inputs.mkdir();
            outputs.mkdir();
            File temp = new File(inputs, "temp.txt");
            temp.createNewFile();
        }
    }

    public void fetchDataForSub(String s) {
        Intent intent = new Intent(this, SubActivity1.class);
        intent.putExtra("tv", s);
        startActivity(intent);
    }
}
