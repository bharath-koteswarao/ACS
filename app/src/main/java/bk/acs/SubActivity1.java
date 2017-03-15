package bk.acs;

import android.content.ContentValues;
import android.os.Bundle;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
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
    TextView subNameHeader, presentCount, absentCount;
    RecyclerView recyclerView2;
    int count = 0, strength, pcount = 0;
    MyAdapter2 adapter2;
    ArrayList<Integer> abL;
    ArrayList<ListItem2> send;
    ContentValues cot;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sub1);
        cot = new ContentValues();
        abL = new ArrayList<>();
        send = new ArrayList<>();
        String name = getIntent().getExtras().getString("tv");
        subNameHeader = (TextView) findViewById(R.id.subNameHeader);
        presentCount = (TextView) findViewById(R.id.presentCount);
        absentCount = (TextView) findViewById(R.id.absentCount);
        recyclerView2 = (RecyclerView) findViewById(R.id.recview2);
        recyclerView2.setLayoutManager(new GridLayoutManager(recyclerView2.getContext(), 6));
        Data2 data2 = new Data2(this, name);
        String path = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOCUMENTS).getAbsolutePath() + "/ATTENDANCE CSV/Inputs/" + data2.getFileName();
        Toast.makeText(this, data2.getFileName() + " " + (new File(path)).exists() + "", Toast.LENGTH_SHORT).show();
        try {
            FileReader f = new FileReader(path);
            BufferedReader br = new BufferedReader(f);
            String line;
            while ((line = br.readLine()) != null) {
                send.add(new ListItem2(line.substring(5, line.length()), 0));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        adapter2 = new MyAdapter2(send, this);
        strength = adapter2.getItemCount();
        absentCount.setText("ABSENT = " + adapter2.getItemCount() + "");
        recyclerView2.setAdapter(adapter2);
        subNameHeader.setText(name);
        DefaultItemAnimator animator = new DefaultItemAnimator();
        animator.setRemoveDuration(200);
        animator.setAddDuration(200);
        recyclerView2.setItemAnimator(animator);
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

    public void createFile(View view) {
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