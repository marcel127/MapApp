package com.example.marcel.mapapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.Toast;

import java.util.ArrayList;

public class Main2Activity extends AppCompatActivity {
    private Button GPS;
    private Button BLE;
    private Button QR;
    private  Button findFriends;
    private Switch change;
    private String userName;
    private boolean LocationChange;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        userName= this.getIntent().getExtras().getString("userName");

        GPS =(Button)findViewById(R.id.GPS);
        BLE =(Button)findViewById(R.id.BLE);
        QR =(Button)findViewById(R.id.QR);

        findFriends=(Button)findViewById(R.id.findFriends);
        LocationChange=false;

        change=(Switch)findViewById(R.id.change);

        change.setOnClickListener(new View.OnClickListener(){

            public void onClick(View v) {

                switch (v.getId()) {

                    case R.id.change:
                        if(!LocationChange){
                            LocationChange=true;
                        }
                        else{
                            LocationChange=false;
                        }
                        break;
                    default:
                        break;
                }
            }


        });

        GPS.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Toast.makeText(Main2Activity.this, "geting location by gps", Toast.LENGTH_SHORT).show();
                Intent intent=new Intent(Main2Activity.this,MapsActivity.class);
                intent.putExtra("userName", userName);
                intent.putExtra("change", LocationChange);
                startActivity(intent);
            }
        });


        BLE.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Toast.makeText(Main2Activity.this, "Searching BLE Point", Toast.LENGTH_SHORT).show();
                Intent intent=new Intent(Main2Activity.this,BLEActivity.class);
                intent.putExtra("userName", userName);
                startActivity(intent);
            }
        });

        QR.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Toast.makeText(Main2Activity.this, "geting location by QR", Toast.LENGTH_SHORT).show();
                Intent intent=new Intent(Main2Activity.this,FindByQRActivity.class);
                intent.putExtra("userName", userName);
                startActivity(intent);
            }
        });
//

        findFriends.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Toast.makeText(Main2Activity.this, "search your friends", Toast.LENGTH_SHORT).show();
                Intent intent=new Intent(Main2Activity.this,findFriendsActivity.class);
                intent.putExtra("userName", userName);
                startActivity(intent);
            }
        });




    }


}
