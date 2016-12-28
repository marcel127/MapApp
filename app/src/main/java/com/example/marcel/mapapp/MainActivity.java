package com.example.marcel.mapapp;

import android.icu.text.LocaleDisplayNames;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.EditText;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;


import android.content.Intent;

import android.view.View;

import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private Button login;
    private EditText name;
    private FirebaseDatabase data;
    private DatabaseReference mDatabase;
    private  String user;


    DatabaseReference ref;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        name = (EditText) findViewById(R.id.name);
        login =(Button)findViewById(R.id.login);
        data = FirebaseDatabase.getInstance();
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(MainActivity.this, "Entering", Toast.LENGTH_SHORT).show();
                        Intent intent=new Intent(MainActivity.this,Main2Activity.class);
                         user=name.getText().toString();
                         intent.putExtra("userName", user);
                         startActivity(intent);




//                user=name.getText().toString();
//
//                        Toast.makeText(MainActivity.this, "Entering", Toast.LENGTH_SHORT).show();
//                        Intent intent=new Intent(MainActivity.this,MapsActivity.class);
//                        intent.putExtra("userName", user);
//                        startActivity(intent);




            }
        });






    }







}