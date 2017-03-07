package bk.acs;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;

public class SubActivity1 extends AppCompatActivity {

    RecyclerView recyclerView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sub1);
        recyclerView=(RecyclerView)findViewById(R.id.recview2);
        recyclerView.setLayoutManager(new LinearLayoutManager(recyclerView.getContext()));
    }
}
