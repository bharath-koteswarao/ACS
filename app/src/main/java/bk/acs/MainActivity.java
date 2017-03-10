package bk.acs;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import bk.acs.AddingToDatabase.MyDialog;
import bk.acs.RecyclerView.Data;
import bk.acs.RecyclerView.ListItem;
import bk.acs.RecyclerView.MyAdapter;

public class MainActivity extends AppCompatActivity {
    FloatingActionButton fab;
    RecyclerView recview;
    MyAdapter adapter;
    Data d;
    Toolbar toolbar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        toolbar=(Toolbar)findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        fab=(FloatingActionButton)findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MyDialog dialog=new MyDialog();
                dialog.show(getFragmentManager(),"myDialog");
            }
        });
        d=new Data(this);
        recview=(RecyclerView)findViewById(R.id.recview);
        adapter=new MyAdapter(d.getList(),this);
        recview.setLayoutManager(new LinearLayoutManager(recview.getContext()));
        recview.setAdapter(adapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater=getMenuInflater();
        menuInflater.inflate(R.menu.menu_main,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int res_id=item.getItemId();
        if(res_id==R.id.refresh)
        {
            adapter.notifyDataSetChanged();
        }
        return true;
    }
    public void updateRecView(long num, String subname)
    {
        adapter.listdata.add(new ListItem(subname,num+""));
        adapter.notifyItemInserted(adapter.listdata.size());
    }
}
