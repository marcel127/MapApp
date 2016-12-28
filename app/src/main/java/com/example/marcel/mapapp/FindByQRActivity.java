package com.example.marcel.mapapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import  com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

public class FindByQRActivity extends AppCompatActivity {

    TextView mContentsTextView;
    String userName;
    String QrName;
    LOCATION2D QrLocation;
    private FirebaseDatabase database;
    private FirebaseDatabase data;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_find_by_qr);
        userName=this.getIntent().getExtras().getString("userName");
        database=FirebaseDatabase.getInstance();
        mContentsTextView = (TextView)findViewById(R.id.mContentsTextView);
        startScan();
    }

    public void onActivityResult(int requestCode, int resultCode, Intent intent) {
        IntentResult scanResult = IntentIntegrator.parseActivityResult(requestCode, resultCode, intent);
        if (scanResult != null) {
            if (scanResult.getContents() != null) {
                Log.d("tag", scanResult.getContents());
                QrName=scanResult.getContents();
                mContentsTextView.setText(scanResult.getContents());

                DatabaseReference ref= database.getReference("QR").child(QrName);
                ref.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        QrLocation= dataSnapshot.getValue(LOCATION2D.class);
                        enterToDataBase();
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {
                        Log.i("DatabaseError", databaseError.toString());
                    }
                });
            }
        }

    }

    public void enterToDataBase(){
        DatabaseReference ref = data.getInstance().getReference("Users");

        ref .child(userName).setValue(QrLocation);
        ref.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Log.d("*******",dataSnapshot.getValue(LOCATION2D.class).toString());

                Toast.makeText(FindByQRActivity.this, "wait...", Toast.LENGTH_SHORT).show();
                Intent intent=new Intent(FindByQRActivity.this,QrMapsActivity.class);
                intent.putExtra("latitude", QrLocation.getLatitude());
                intent.putExtra("longitude", QrLocation.getLongitude());
                startActivity(intent);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }
    public void scanButtonPressed(View view) {
        startScan();
    }

    public void startScan() {
        IntentIntegrator integrator = new IntentIntegrator(this);
        integrator.initiateScan();
    }
}