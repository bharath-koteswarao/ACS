package bk.acs.RecyclerView;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import bk.acs.R;

/**
 * Created by koteswarao on 01-03-2017.
 */

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.Holder>{
    List<ListItem> listdata;
    LayoutInflater inflater;
    public MyAdapter(List<ListItem> list, Context c)
    {
        this.inflater=LayoutInflater.from(c);
        listdata=list;
    }

    @Override
    public Holder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view=inflater.inflate(R.layout.subjects_li,parent,false);
        return new Holder(view);
    }

    @Override
    public void onBindViewHolder(Holder holder, int position) {
        ListItem item=listdata.get(position);
        holder.subjectName.setText(item.subName);
        holder.serialNo.setText(item.sno);
    }

    @Override
    public int getItemCount() {
        return listdata.size();
    }

    class Holder extends RecyclerView.ViewHolder
    {
        TextView subjectName;
        TextView serialNo;
        View container;
        public Holder(View itemView) {
            super(itemView);
            subjectName=(TextView)itemView.findViewById(R.id.subjectTitle);
            serialNo=(TextView)itemView.findViewById(R.id.sno);
            container=itemView.findViewById(R.id.cont_root);
        }
    }

}
