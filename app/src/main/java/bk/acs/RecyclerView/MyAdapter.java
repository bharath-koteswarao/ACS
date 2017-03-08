package bk.acs.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import bk.acs.R;
import bk.acs.SubActivity1;

/**
 * Created by bk on 01-03-2017.
 */

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.Holder>{
    List<ListItem> listdata;
    LayoutInflater inflater;
    Context c;
    public MyAdapter(List<ListItem> list, Context c)
    {
        this.inflater=LayoutInflater.from(c);
        listdata=list;
        this.c=c;
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

    class Holder extends RecyclerView.ViewHolder implements View.OnClickListener
    {
        TextView subjectName;
        TextView serialNo;
        View container;
        public Holder(View itemView) {
            super(itemView);
            subjectName=(TextView)itemView.findViewById(R.id.subjectTitle);
            serialNo=(TextView)itemView.findViewById(R.id.sno);
            container=itemView.findViewById(R.id.cont_root);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            Intent intent=new Intent(c,SubActivity1.class);
            intent.putExtra("tv",subjectName.getText());
            c.startActivity(intent);
        }
    }

}
