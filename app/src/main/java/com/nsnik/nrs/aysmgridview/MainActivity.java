package com.nsnik.nrs.aysmgridview;

import android.content.Intent;
import android.database.Cursor;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.View;

import com.nsnik.nrs.aysmgridview.data.tablenames;

public class MainActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    FloatingActionButton addItem;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initilize();
        addList();
    }

    private void initilize() {
        recyclerView = (RecyclerView)findViewById(R.id.recyclerView);
        addItem = (FloatingActionButton)findViewById(R.id.addItem);
        recyclerView.setLayoutManager(new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL));
        addItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this,AddActivity.class));
            }
        });
    }

    private void addList(){
        Cursor c = getContentResolver().query(tablenames.mContentUri,null,null,null,null);
        Adapter adapter = new Adapter(getApplicationContext(),c);
        recyclerView.setAdapter(adapter);
    }

}
