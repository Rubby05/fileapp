package com.example.fileapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;

public class MainActivity extends AppCompatActivity  {
    String FILE_NAME = "skpsu.txt";
    EditText editText;
    Button savebtn,loadbtn,crt;
    int count;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        editText=findViewById(R.id.edit_text);
        savebtn=(Button)findViewById(R.id.btn_save);
        savebtn.setOnClickListener(this::save);
        loadbtn=(Button)findViewById(R.id.btn_save2);
        loadbtn.setOnClickListener(this::load);

        crt=findViewById(R.id.btn_create);
        crt.setOnClickListener(this::create);
    }

    public void save(View v){
        if(count==0){
            Toast.makeText(getApplicationContext(),"create file",Toast.LENGTH_LONG).show();

        }
        else{
            String text = editText.getText().toString();
            FileOutputStream fos = null;

            try {
                fos = openFileOutput(FILE_NAME,MODE_PRIVATE);
                fos.write(text.getBytes());
                editText.getText().clear();
                Toast.makeText(this,"Data is Saved in"+getFilesDir(),Toast.LENGTH_LONG).show();
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                if(fos!=null){
                    try {
                        fos.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }
    public void load(View v){
        if(count==0){
            Toast.makeText(this,"create file",Toast.LENGTH_LONG).show();

        }
        FileInputStream fis = null;
        try {
            fis = openFileInput(FILE_NAME);
            InputStreamReader isr = new InputStreamReader(fis);
            BufferedReader br = new BufferedReader(isr);
            StringBuilder sb = new StringBuilder();
            String text;

            while ((text = br.readLine()) != null){
                sb.append(text).append("\n");
                editText.setText(sb.toString());
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }finally{
            if(fis!=null){
                try {
                    fis.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
    public void create(View v){
        count = 1;
        Toast.makeText(getApplicationContext(),"file created successfully",Toast.LENGTH_LONG).show();
    }


}