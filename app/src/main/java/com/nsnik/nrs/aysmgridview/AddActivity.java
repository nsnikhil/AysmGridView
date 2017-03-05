package com.nsnik.nrs.aysmgridview;

import android.content.ContentValues;
import android.net.Uri;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;
import com.nsnik.nrs.aysmgridview.data.tablenames;
import com.nsnik.nrs.aysmgridview.data.tablenames.table1;

public class AddActivity extends AppCompatActivity {

    EditText name,surname;
    FloatingActionButton save;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);
        initilize();
    }

    private void initilize() {
        name = (EditText)findViewById(R.id.addName);
        surname = (EditText)findViewById(R.id.addSurName);
        save = (FloatingActionButton) findViewById(R.id.saveName);
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(verify()){
                    ContentValues cv = new ContentValues();
                    cv.put(table1.mName,name.getText().toString());
                    if(surname.getText().toString()!=null&&!name.getText().toString().isEmpty()){
                        cv.put(table1.mSurname,surname.getText().toString());
                    }
                    Uri u = getContentResolver().insert(tablenames.mContentUri,cv);
                    if(u==null){
                        Toast.makeText(getApplicationContext(),"Error while adding new name",Toast.LENGTH_SHORT).show();
                    }else {
                        Toast.makeText(getApplicationContext(),"Name added to database",Toast.LENGTH_SHORT).show();
                        finish();
                    }
                }
            }
        });
    }

    private boolean verify(){
        if(name.getText().toString()==null||name.getText().toString().isEmpty()||name.getText().toString().length()==0){
            Toast.makeText(getApplicationContext(),"Enter the first name atleast",Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }
}
