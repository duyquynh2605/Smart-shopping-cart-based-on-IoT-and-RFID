package com.example.root.fire_major_notification;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;
import java.util.Calendar;
import java.util.Date;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.FirebaseDatabase;

public class qrcodeActivity extends AppCompatActivity {
    Button back;
    hoadonClass2 hd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_qrcode);
        String sdata = getIntent().getStringExtra("user");
        String tien = getIntent().getStringExtra("tien");
        String mota = getIntent().getStringExtra("mota");
        back = (Button) findViewById(R.id.button3);

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Date c = Calendar.getInstance().getTime();
                hoadonClass2 hd = new hoadonClass2(
                        mota,
                        tien,
                        Integer.toString(c.getDate()),
                        Integer.toString(c.getMonth()),
                        Integer.toString(c.getYear()),
                        Integer.toString(c.getHours()),
                        Integer.toString(c.getMinutes()));
                FirebaseDatabase.getInstance().getReference().child("hoadon/user"+ sdata).push().setValue(hd).addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        finish();
                    }
                });

            }
        });
    }
}