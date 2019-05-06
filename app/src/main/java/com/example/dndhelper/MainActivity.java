package com.example.dndhelper;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;

public class MainActivity extends AppCompatActivity {

    private Character _character;
    private String _filename = "character";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        LoadCharacter();
    }

    @Override
    protected void onPause() {
        super.onPause();
        SaveCharacter();
    }

    public boolean fileExists(Context context, String filename) {
        File file = context.getFileStreamPath(filename);
        return file != null && file.exists();
    }

    private void LoadCharacter() {
        try {
            if (fileExists(this, _filename)) {
                StringBuilder text = new StringBuilder();

                BufferedReader reader = new BufferedReader(new FileReader(this.getFileStreamPath(_filename)));
                String line;

                while ((line = reader.readLine()) != null) {
                    text.append(line);
                    text.append('\n');
                }
                reader.close();

                _character = new Gson().fromJson(text.toString(), Character.class);
            }
            else {
                _character = new Character(20);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void SaveCharacter() {
        FileOutputStream outputStream;
        try {
            if(_character != null) {
                outputStream = openFileOutput(_filename, Context.MODE_PRIVATE);
                outputStream.write(new Gson().toJson(_character).getBytes());
                outputStream.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
