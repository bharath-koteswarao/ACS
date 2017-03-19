package bk.acs;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;

public class FinalDetails extends Activity {
    ListView lv2;
    LinearLayout lv,totalPresent;
    TextView count;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_final_details);
        lv2 = (ListView) findViewById(R.id.listView2);
        count=(TextView)findViewById(R.id.count);
        ArrayList<String> arr2 = new ArrayList<>();
        String date = getIntent().getExtras().getString("date");
        String path = getFilesDir() + "/" + date + ".csv";
        lv=(LinearLayout)findViewById(R.id.lv);
        totalPresent=(LinearLayout)findViewById(R.id.totalPresent);
        String line;
        try {
            FileReader reader = new FileReader(path);
            BufferedReader br = new BufferedReader(reader);
            while ((line = br.readLine()) != null) {
                arr2.add(line);
            }
        } catch (Exception e) {}
        count.setText(arr2.size()+" ABSENTEES");
        if(arr2.size()==0 || arr2.get(0).equals("Full present"))
        {
            lv.setVisibility(View.GONE);
        }
        else {
            totalPresent.setVisibility(View.GONE);
        }
        ArrayAdapter<String> adapter2 = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, arr2);
        lv2.setAdapter(adapter2);
    }
}
