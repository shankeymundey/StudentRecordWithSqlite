package com.example.yogesh.sqlapp;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
EditText t1,t2,t3;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        t1=(EditText)findViewById(R.id.t1);
        t2=(EditText)findViewById(R.id.t2);
        t3=(EditText)findViewById(R.id.t3);
    }

    public void insertbtn(View view) {
        try{
            int r=Integer.parseInt(t1.getText().toString().trim());
             String n=t2.getText().toString().trim();
            String c=t3.getText().toString().trim();
            @SuppressLint("WrongConstant")
            SQLiteDatabase db=openOrCreateDatabase("MyHpe.db",SQLiteDatabase.CREATE_IF_NECESSARY,null);
            String sql="CREATE TABLE IF NOT EXISTS Student ( roll INTEGER PRIMARY KEY , name TEXT , course TEXT )";
            db.execSQL(sql);
            ContentValues values=new ContentValues();
            values.put("roll",r);
            values.put("name",n);
            values.put("course",c);
            Long re=db.insert("Student",null,values);
            if(re>0){
                Toast.makeText(this,"Insert sucess",Toast.LENGTH_LONG).show();
            }
            else{
                Toast.makeText(this,"not insert",Toast.LENGTH_LONG).show();
            }

           }
    catch (Exception ex){
        Toast.makeText(this,"error"+ex.toString(),Toast.LENGTH_LONG).show();

        }
    }


    public void searchbtn(View view) {
        int r=Integer.parseInt(t1.getText().toString().trim());
        try{
         @SuppressLint("WrongConstant")
         SQLiteDatabase db= openOrCreateDatabase("MyHpe.db",SQLiteDatabase.CREATE_IF_NECESSARY,null);
        String sql="SELECT * FROM Student where roll="+r;
            Cursor c=db.rawQuery(sql,null);
            c.moveToFirst();
            if(!c.isAfterLast()){
               t2.setText(c.getString(1));
                t3.setText(c.getString(2));
            }
            else{
                Toast.makeText(this,"record not found",Toast.LENGTH_LONG).show();
            }

        }
        catch (Exception ex){

            Toast.makeText(this,"error"+ex.toString(),Toast.LENGTH_LONG).show();
        }
    }



    public void updatebtn(View view) {
     try {
         int roll = Integer.parseInt(t1.getText().toString().trim());
         String name = t2.getText().toString().trim();
         String course = t3.getText().toString().trim();
         ContentValues values=new ContentValues();
         values.put("roll", roll);
         values.put("name", name);
         values.put("course", course);
         @SuppressLint("WrongConstant")
         SQLiteDatabase db=openOrCreateDatabase("MyHpe.db",SQLiteDatabase.CREATE_IF_NECESSARY,null);
         int re=db.update("Student", values, "roll=" + roll, null);

         if(re>0){
             Toast.makeText(this,"update sucess",Toast.LENGTH_LONG).show();

         }
         else{
             Toast.makeText(this,"not updated",Toast.LENGTH_LONG).show();

         }

     }
     catch (Exception ex){


         Toast.makeText(this,"error"+ex.toString(),Toast.LENGTH_LONG).show();

     }

     }

      public void deletebtn(View view) {
        try
        {
            int roll = Integer.parseInt(t1.getText().toString().trim());
            @SuppressLint("WrongConstant")
            SQLiteDatabase 	db=openOrCreateDatabase("MyHpe.db",
                    SQLiteDatabase.CREATE_IF_NECESSARY, null);
            int re=db.delete("Student", "roll=" + roll, null);
            if(re>0)
                Toast.makeText(getApplicationContext(), " record deleted ",
                        Toast.LENGTH_SHORT).show();
            else
                Toast.makeText(getApplicationContext(), "delete not sucess, ",
                        Toast.LENGTH_SHORT).show();

        }catch(Exception ex){
            Toast.makeText(getApplicationContext(), "Error "+ex.toString(),
                    Toast.LENGTH_LONG).show();

        }


    }


    public void showallbtn(View view) {
        Intent ob= new Intent(this,Main2Activity.class);
        startActivity(ob);


    }
}
