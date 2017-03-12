package bk.acs;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.Environment;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import bk.acs.RecyclerView2.Data2;
import bk.acs.RecyclerView2.ListItem2;
import bk.acs.RecyclerView2.MyAdapter2;
import bk.acs.RecyclerView3.Data3;
import bk.acs.RecyclerView3.ListItem3;
import bk.acs.RecyclerView3.MyAdapter3;
import jp.wasabeef.recyclerview.animators.OvershootInRightAnimator;
import jp.wasabeef.recyclerview.animators.SlideInLeftAnimator;

import static android.os.Environment.getExternalStorageDirectory;
import static android.os.Environment.getExternalStoragePublicDirectory;
import static bk.acs.R.styleable.View;

public class SubActivity1 extends AppCompatActivity {
    TextView subNameHeader,presentCount,absentCount;
    RecyclerView recyclerView2,recyclerView3;
    MyAdapter3 adapter3;
    String tag="See this";
    MyAdapter2 adapter2;
    boolean res;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sub1);
        subNameHeader=(TextView)findViewById(R.id.subNameHeader);
        presentCount=(TextView)findViewById(R.id.presentCount);
        absentCount=(TextView)findViewById(R.id.absentCount);
        recyclerView2=(RecyclerView)findViewById(R.id.recview2);
        recyclerView3=(RecyclerView)findViewById(R.id.recview3) ;
        recyclerView2.setLayoutManager(new LinearLayoutManager(recyclerView2.getContext()));
        recyclerView3.setLayoutManager(new LinearLayoutManager(recyclerView3.getContext()));
        Data2 data2=new Data2(this);
        Data3 data3=new Data3(this);
        adapter2=new MyAdapter2(data2.getList(),this);
        adapter3=new MyAdapter3(data3.getList(),this);
        presentCount.setText("PRESENT = "+adapter2.getItemCount()+"");
        absentCount.setText("ABSENT = "+adapter3.getItemCount()+"");
        recyclerView2.setAdapter(adapter2);
        recyclerView3.setAdapter(adapter3);
        String name=getIntent().getExtras().getString("tv");
        subNameHeader.setText(name);
        DefaultItemAnimator animator=new DefaultItemAnimator();
        animator.setRemoveDuration(200);
        animator.setAddDuration(200);
        recyclerView2.setItemAnimator(animator);
    }
    public void createFile(View view) throws IOException {
        String path=Environment.getExternalStorageDirectory().getAbsolutePath()+"/bk.txt";
        File f=new File(path);
        Log.d(tag,f.exists()+"");
        Log.d(tag,f.isDirectory()+"");
        Log.d(tag,f.createNewFile()+"");
    }
    public void onClickForAbsent(int position, ListItem3 item3)
    {
        adapter3.listdata3.remove(position);
        adapter3.notifyItemRemoved(position);
        adapter2.listdata2.add(adapter2.listdata2.size(),(new ListItem2(item3.regNo,1)));
        adapter2.notifyItemInserted(adapter2.listdata2.size());
        presentCount.setText("PRESENT = "+adapter2.getItemCount()+"");
        absentCount.setText("ABSENT = "+adapter3.getItemCount()+"");
    }
    public void onClickForPresent(int position, ListItem2 item2)
    {
        adapter2.listdata2.remove(position);
        adapter2.notifyItemRemoved(position);
        adapter3.listdata3.add(new ListItem3(item2.regno));
        adapter3.notifyItemInserted(adapter3.listdata3.size());
        presentCount.setText("PRESENT = "+adapter2.getItemCount()+"");
        absentCount.setText("ABSENT = "+adapter3.getItemCount()+"");
    }
}
