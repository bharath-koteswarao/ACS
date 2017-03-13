package bk.acs.RecyclerView2;

import android.content.Context;
import android.graphics.Color;
import android.os.Environment;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import java.util.List;
import bk.acs.R;
import bk.acs.SubActivity1;

/**
 * Created by bk on 07-03-2017.
 */

public class MyAdapter2 extends RecyclerView.Adapter<MyAdapter2.Holder>{
    public List<ListItem2> listdata2;
    LayoutInflater layoutInflater;
    Context c;
    SubActivity1 activity1=new SubActivity1();
    public MyAdapter2(List<ListItem2> listdata, Context ctx)
    {
        this.listdata2=listdata;
        c=ctx;
        this.layoutInflater=LayoutInflater.from(c);
    }

    @Override
    public Holder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v=layoutInflater.inflate(R.layout.students_li,parent,false);
        return new Holder(v);
    }

    @Override
    public void onBindViewHolder(Holder holder, int position) {
        ListItem2 item=listdata2.get(position);
        holder.studentNo.setText(item.regno);
        if(item.color>0){
            holder.studentNo.setTextColor(Color.parseColor("#303F9F"));
        }
    }

    @Override
    public int getItemCount() {
        return listdata2.size();
    }

    class Holder extends RecyclerView.ViewHolder implements View.OnClickListener
    {
        TextView studentNo;
        public Holder(View itemview2)
        {
            super(itemview2);
            studentNo=(TextView)itemview2.findViewById(R.id.regNo);
            itemview2.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            ((SubActivity1)c).onClickForPresent(getPosition(),listdata2.get(getPosition()));
        }
    }
}
