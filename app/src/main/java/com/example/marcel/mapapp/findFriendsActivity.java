package com.example.marcel.mapapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;


import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class findFriendsActivity extends AppCompatActivity  {


    private FirebaseDatabase database;
    private DatabaseReference ref;
    private ArrayList<String> list;
    private Button find;
    private TextView friendsList;
    private EditText editText;
    private LOCATION2D location;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_find_friends);
        find = (Button) findViewById(R.id.find);
        database = FirebaseDatabase.getInstance();
        editText=(EditText)findViewById(R.id.editText);
        friendsList=(TextView)findViewById(R.id.friendsList);
        list=new ArrayList<String>();
        load();




        find.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name= editText.getText().toString();
                ref= database.getReference("Users").child(name);
                ref.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        location= dataSnapshot.getValue(LOCATION2D.class);
                        Intent intent = new Intent(findFriendsActivity.this, friendsMapsActivity.class);
                        intent.putExtra("latitude", location.getLatitude());
                        intent.putExtra("longitude", location.getLongitude());
                        startActivity(intent);
                    }
                    @Override
                    public void onCancelled(DatabaseError databaseError) {
                        Log.i("DatabaseError", databaseError.toString());
                    }
                });

            }
        });



    }

    public void load() {
        ref = database.getReference();
        ref.addListenerForSingleValueEvent(new ValueEventListener() {

            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                DataSnapshot usersSnapshot = dataSnapshot.child("Users");
                Iterable<DataSnapshot> userChildren = usersSnapshot.getChildren();
                for (DataSnapshot usersNamesList : userChildren) {
                    String st = usersNamesList.getKey();
                    list.add(st);
                    friendsList.append(st+"\n");

                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }



}
