package com.rohit.store;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import static android.Manifest.permission.READ_EXTERNAL_STORAGE;
import static android.Manifest.permission.WRITE_EXTERNAL_STORAGE;

public class MainActivity extends AppCompatActivity {

    Button btn_store;
    EditText edit_txt;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            requestPermissions(new String[] {WRITE_EXTERNAL_STORAGE,READ_EXTERNAL_STORAGE}, 1);
        }
        init();

        btn_store.setOnClickListener(v -> writeToFile(edit_txt.getText().toString()));
    }

    void  init(){
        btn_store = findViewById(R.id.btn_store);
        edit_txt = findViewById(R.id.edit_txt);
    }
    void writeToFile(String text) {
        File dir = new File(Environment.getExternalStorageDirectory(), "Files");
        if(!dir.exists()) {
          dir.mkdir();
        }
        try {
            File file = new File(dir, "location.txt");
            FileWriter writer = new FileWriter(file);
            writer.append(text);
            writer.flush();
            writer.close();
            Toast.makeText(this,"Success",Toast.LENGTH_SHORT).show();
        } catch (IOException e) {
            e.printStackTrace();
        }


    }
}