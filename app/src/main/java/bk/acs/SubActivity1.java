package bk.acs;

import android.Manifest;
import android.content.ContentValues;
import android.content.pm.PackageManager;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Environment;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Date;
import bk.acs.RecyclerView2.Data2;
import bk.acs.RecyclerView2.ListItem2;
import bk.acs.RecyclerView2.MyAdapter2;
import bk.acs.databases.DatesList;

public class SubActivity1 extends AppCompatActivity {
    private static final int STORAGE_PERMISSION = 10;
    TextView subNameHeader, presentCount, absentCount;
    RecyclerView recyclerView2;
    int count = 0, strength, pcount = 0;
    MyAdapter2 adapter2;
    ArrayList<Integer> abL;
    ArrayList<ListItem2> send;
    ContentValues cot;
    String name,path;
    Data2 data2;
    AlertDialog.Builder builder;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sub1);
        if(ActivityCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE)!= PackageManager.PERMISSION_GRANTED)
        {
            ActivityCompat.requestPermissions(this,new String[]{android.Manifest.permission.WRITE_EXTERNAL_STORAGE}, STORAGE_PERMISSION);
        }
        cot = new ContentValues();
        abL = new ArrayList<>();
        send = new ArrayList<>();
        name = getIntent().getExtras().getString("tv");
        subNameHeader = (TextView) findViewById(R.id.subNameHeader);
        presentCount = (TextView) findViewById(R.id.presentCount);
        absentCount = (TextView) findViewById(R.id.absentCount);
        recyclerView2 = (RecyclerView) findViewById(R.id.recview2);
        recyclerView2.setLayoutManager(new GridLayoutManager(recyclerView2.getContext(), 6));
        data2= new Data2(this, name);
        path= Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOCUMENTS).getAbsolutePath()+"/"+getResources().getString(R.string.app_name)+"/Inputs/" + data2.getFileName();
        Toast.makeText(this, data2.getFileName() + " " + (new File(path)).exists() + "", Toast.LENGTH_SHORT).show();
        adapter2 = new MyAdapter2(send, this);
        strength = adapter2.getItemCount();
        absentCount.setText("ABSENT = " + adapter2.getItemCount() + "");
        recyclerView2.setAdapter(adapter2);
        subNameHeader.setText(name);
        (new FileTask()).execute();
    }
    class FileTask extends AsyncTask<Void, String, Void>
    {

        @Override
        protected void onPreExecute() {

        }

        @Override
        protected Void doInBackground(Void... voids) {
            try {
                FileReader f = new FileReader(path);
                BufferedReader br = new BufferedReader(f);
                String line;
                while ((line = br.readLine()) != null) {
                    line=line.replace("15BEC","");
                    line=line.replace("15bec","");
                    publishProgress(line);
                    Thread.sleep(30);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }

            return null;
        }

        @Override
        protected void onProgressUpdate(String... values) {
            adapter2.listdata2.add(new ListItem2(values[0],0));
            adapter2.notifyItemInserted(adapter2.listdata2.size());
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            adapter2.status=new int[adapter2.listdata2.size()];
            Arrays.fill(adapter2.status,0);
        }
    }
    public void add(int position, int status) {
        if (!abL.contains(position)) {
            if (status == 1) {
                abL.add(position);
                pcount++;
                strength--;
            }
            presentCount.setText("PRESENT = " + pcount);
            absentCount.setText("ABSENT = " + strength);
        }
        if (abL.contains(position)) {
            if (status == 0) {
                abL.remove(abL.indexOf(position));
                strength++;
                pcount--;
            }
            presentCount.setText("PRESENT = " + pcount);
            absentCount.setText("ABSENT = " + strength);
        }
        Collections.sort(abL);
    }

    public void refresh(View view) {
        for (int i = 0; i < abL.size(); i++) {
            Log.d("Removed is ", (abL.get(i) - count) + "");
            adapter2.listdata2.remove(abL.get(i) - count);
            adapter2.notifyItemRemoved(abL.get(i) - count);
            count++;
        }
        abL.clear();
        count = 0;
        adapter2.status = new int[adapter2.listdata2.size()];
        Arrays.fill(adapter2.status, 0);
    }

    public void submit(View view) {
        for (int i = 0; i < abL.size(); i++) {
            Log.d("Removed is ", (abL.get(i) - count) + "");
            adapter2.listdata2.remove(abL.get(i) - count);
            adapter2.notifyItemRemoved(abL.get(i) - count);
            count++;
        }
        abL.clear();
        count = 0;
        adapter2.status = new int[adapter2.listdata2.size()];
        Arrays.fill(adapter2.status, 0);
        DatesList dates = new DatesList(this, 1);
        cot.put("Date", (new Date()).toString());
        try {
            long res = dates.execute(cot, adapter2.listdata2);
            Toast.makeText(this, res + "", Toast.LENGTH_SHORT).show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}