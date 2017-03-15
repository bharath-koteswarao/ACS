package bk.acs;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;

public class FinalDetails extends Activity {
    ListView lv2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_final_details);
        lv2=(ListView)findViewById(R.id.listView2);
        ArrayList<String> arr2=new ArrayList<>();
        String date=getIntent().getExtras().getString("date");
        String path=getFilesDir()+"/"+date+".csv";
        String line;
        try{
            FileReader reader=new FileReader(path);
            BufferedReader br=new BufferedReader(reader);
            while ((line=br.readLine())!=null){
                arr2.add("15BEC"+line);
            }
        }
        catch (Exception e){}
        ArrayAdapter<String> adapter2=new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,arr2);
        lv2.setAdapter(adapter2);
    }
}
