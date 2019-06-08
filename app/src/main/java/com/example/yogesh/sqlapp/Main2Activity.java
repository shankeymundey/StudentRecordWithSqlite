package com.example.yogesh.sqlapp;

import android.annotation.SuppressLint;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;

public class Main2Activity extends AppCompatActivity {
ListView lv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        lv=(ListView)findViewById(R.id.lv);

        try {
            ArrayList<HashMap<String,String>> ar=
                    new ArrayList <HashMap<String,String>>();
            @SuppressLint("WrongConstant")
            SQLiteDatabase db = openOrCreateDatabase("MyHpe.db",
                    SQLiteDatabase.OPEN_READONLY, null);
            String sql = "SELECT * FROM Student";
            Cursor c = db.rawQuery(sql, null);
                   c.moveToFirst();
            while (!c.isAfterLast()) {
                HashMap<String, String> hm = new HashMap<String, String>();
                hm.put("k1", c.getString(0));
                hm.put("k2", c.getString(1));
                hm.put("k3", c.getString(2));
                ar.add(hm);
                c.moveToNext();
            }
            ListAdapter listadapter=new SimpleAdapter(this,ar,
                    R.layout.showall,new String[]{"k1","k2","k3"},
                    new int[]{R.id.rollid,R.id.nameid,R.id.courseid});
            lv.setAdapter(listadapter);
        }
        catch (Exception ex) {
            Toast.makeText(this,"Error "+ex,Toast.LENGTH_LONG).show();
        }
    }
}

