package com.mystickersapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import com.example.samplestickerapp.EntryActivity;



public class MainActivity extends AppCompatActivity {

    private EntryActivity entryActivity;
    private final String TAG = "MainActivity";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        try {
            Log.i(TAG, "...Intent myIntent =" );
            Intent myIntent = new Intent(this,Class.forName("com.example.samplestickerapp.EntryActivity"));
           startActivity(myIntent );
            Log.i(TAG, " ...startActivity(myIntent );" );
        } catch (ClassNotFoundException e) {
            Log.i(TAG, " catch (ClassNotFoundException e)" );
            e.printStackTrace();
        }

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.i("MainActivity", "...setContentView(R.layout.activity_main);" );

    }
}