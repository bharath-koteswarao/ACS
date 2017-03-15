package bk.acs;

import android.Manifest;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Environment;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import bk.acs.RecyclerView.ListItem;
import bk.acs.RecyclerView2.Data2;
import bk.acs.RecyclerView2.ListItem2;
import bk.acs.RecyclerView2.MyAdapter2;
import bk.acs.RecyclerView3.Data3;
import bk.acs.RecyclerView3.ListItem3;
import bk.acs.RecyclerView3.MyAdapter3;
import jp.wasabeef.recyclerview.animators.OvershootInRightAnimator;
import jp.wasabeef.recyclerview.animators.SlideInLeftAnimator;

import static android.os.Environment.DIRECTORY_PICTURES;
import static android.os.Environment.getExternalStorageDirectory;
import static android.os.Environment.getExternalStoragePublicDirectory;
import static bk.acs.R.styleable.View;

public class SubActivity1 extends AppCompatActivity {
    TextView subNameHeader,presentCount,absentCount;
    RecyclerView recyclerView2;//,recyclerView3;
    //MyAdapter3 adapter3;
    String tag="See this";
    int count=0;
    int strength;
    MyAdapter2 adapter2;
    ArrayList<Integer> abL;
    ArrayList<ListItem2> send;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sub1);
        abL=new ArrayList<>();
        send=new ArrayList<>();
        String name=getIntent().getExtras().getString("tv");
        subNameHeader=(TextView)findViewById(R.id.subNameHeader);
        presentCount=(TextView)findViewById(R.id.presentCount);
        absentCount=(TextView)findViewById(R.id.absentCount);
        recyclerView2=(RecyclerView)findViewById(R.id.recview2);
        //recyclerView3=(RecyclerView)findViewById(R.id.recview3) ;
        recyclerView2.setLayoutManager(new GridLayoutManager(recyclerView2.getContext(),6));
        //recyclerView3.setLayoutManager(new LinearLayoutManager(recyclerView3.getContext()));
        Data2 data2=new Data2(this,name);
        String path=Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOCUMENTS).getAbsolutePath()+"/ATTENDANCE CSV/Inputs/"+data2.getFileName();
        Toast.makeText(this, data2.getFileName()+" "+(new File(path)).exists()+"", Toast.LENGTH_SHORT).show();
        try {
            FileReader f=new FileReader(path);
            BufferedReader br=new BufferedReader(f);
            String line;
            while((line=br.readLine())!=null){
                send.add(new ListItem2(line.substring(5,line.length()),0));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        adapter2=new MyAdapter2(send,this);
        //adapter3=new MyAdapter3(data3.getList(),this);
        strength=adapter2.getItemCount();
        absentCount.setText("ABSENT = "+adapter2.getItemCount()+"");
        //absentCount.setText("ABSENT = "+adapter3.getItemCount()+"");
        recyclerView2.setAdapter(adapter2);
        //recyclerView3.setAdapter(adapter3);
        subNameHeader.setText(name);
        DefaultItemAnimator animator=new DefaultItemAnimator();
        animator.setRemoveDuration(200);
        animator.setAddDuration(200);
        recyclerView2.setItemAnimator(animator);
    }
    public void add(int position, int status){
        if(!abL.contains(position)){
            if(status==1){
                abL.add(position);
                Log.d("size is",abL.size()+"");
            }
        }
        if(abL.contains(position)){
            if(status==0){
                abL.remove(abL.indexOf(position));
                Log.d("Size is ",abL.size()+"");
            }
        }
        Collections.sort(abL);
    }
    public void createFile(View view) {
        for(int i=0;i<abL.size();i++){
            Log.d("Removed is ",(abL.get(i)-count)+"");
            adapter2.listdata2.remove(abL.get(i)-count);
            adapter2.notifyItemRemoved(abL.get(i)-count);
            count++;
        }
        abL.clear();
        count=0;
        adapter2.status=new int[adapter2.listdata2.size()];
        Arrays.fill(adapter2.status,0);
    }
}
