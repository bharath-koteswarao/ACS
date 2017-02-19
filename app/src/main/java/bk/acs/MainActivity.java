package bk.acs;

import android.content.Context;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Arrays;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        ListView listView;
        final String chars[] = new String[10];
        final int arr[]=new int[10];
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        for(int i=0;i<10;i++)
        {
            chars[i]="15BEC058"+i;
        }
        Arrays.fill(arr,1);
        listView=(ListView)findViewById(R.id.row);
        MyAdapter adapter =new MyAdapter(this,chars);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener()
        {

            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                arr[i]=(arr[i]==0)?(1):(0);
             String res=(arr[i]==0)?("Absent"):("Present");
                if(arr[i]==0)
                    view.setBackgroundColor(getResources().getColor(R.color.item_red));
                if(arr[i]==1)
                {
                    view.setBackgroundColor(getResources().getColor(R.color.item_green));
                }
                Toast.makeText(MainActivity.this, "Marked "+chars[i]+" as "+res, Toast.LENGTH_SHORT).show();
            }
        });
    }
}
class MyAdapter extends ArrayAdapter
{
    String titles[];
    public MyAdapter(Context context , String[] titles )
    {
        super(context,R.layout.reg_li,R.id.reg_no,titles);
        this.titles=titles;
    }
    @NonNull
    @Override
    public View getView(int position , View controlView , ViewGroup parent)
    {
        LayoutInflater inflater=(LayoutInflater)getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View row=inflater.inflate(R.layout.reg_li,parent,false);
        TextView tv=(TextView)row.findViewById(R.id.reg_no);
        tv.setText(titles[position]);
        return row;
    }
}