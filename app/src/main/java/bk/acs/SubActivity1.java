package bk.acs;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import bk.acs.RecyclerView2.Data2;
import bk.acs.RecyclerView2.MyAdapter2;
import bk.acs.RecyclerView3.Data3;
import bk.acs.RecyclerView3.MyAdapter3;
import jp.wasabeef.recyclerview.animators.OvershootInRightAnimator;

import static bk.acs.R.styleable.View;

public class SubActivity1 extends AppCompatActivity {
    TextView subNameHeader,presentCount,absentCount;
    RecyclerView recyclerView2,recyclerView3;
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
        MyAdapter2 adapter2=new MyAdapter2(data2.getList(),this);
        MyAdapter3 adapter3=new MyAdapter3(data3.getList(),this);
        recyclerView2.setAdapter(adapter2);
        recyclerView3.setAdapter(adapter3);
        String name=getIntent().getExtras().getString("tv");
        subNameHeader.setText(name);
    }
    public void setPresentCount(View view)
    {

    }
}
