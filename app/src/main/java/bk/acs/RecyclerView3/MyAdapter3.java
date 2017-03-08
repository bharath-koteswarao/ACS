package bk.acs.RecyclerView3;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import bk.acs.R;

/**
 * Created by bk on 08-03-2017.
 */

public class MyAdapter3 extends RecyclerView.Adapter<MyAdapter3.Holder> {
    List<ListItem3> listdata3;
    LayoutInflater layoutInflater;
    Context context;
    public MyAdapter3(List<ListItem3> list , Context c)
    {
        listdata3=list;
        context=c;
        this.layoutInflater=LayoutInflater.from(context);
    }

    @Override
    public Holder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v=layoutInflater.inflate(R.layout.students_li,parent,false);
        return new Holder(v);
    }

    @Override
    public void onBindViewHolder(Holder holder, int position) {
        ListItem3 item3=listdata3.get(position);
        holder.absentNo.setText(item3.regNo);
    }

    @Override
    public int getItemCount() {
        return listdata3.size();
    }

    class Holder extends RecyclerView.ViewHolder
    {
        TextView absentNo;

        public Holder(View itemView3) {
            super(itemView3);
            absentNo=(TextView)itemView3.findViewById(R.id.regNo);
        }
    }
}
