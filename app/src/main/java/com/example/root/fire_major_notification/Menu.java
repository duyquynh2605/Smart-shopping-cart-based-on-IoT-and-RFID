package com.example.root.fire_major_notification;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Menu extends AppCompatActivity implements checkMem.ExampleDialogListener{

    private TextView tenkhach;
    ImageButton thanhtoan, khachhang, gianhang, lichsu;
    Button logout;
    String sdata, sname;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        sdata = getIntent().getStringExtra("user");
        sname= getIntent().getStringExtra("name");
        tenkhach = findViewById(R.id.txtname);
        thanhtoan = (ImageButton) findViewById(R.id.imageButton2);
        khachhang = (ImageButton) findViewById(R.id.imageButton3);
        gianhang = (ImageButton) findViewById(R.id.imageButton5);
        lichsu = (ImageButton) findViewById(R.id.imageButton4);
        logout = findViewById(R.id.button15);
        tenkhach.setText(sname);
        Log.d("TAG",sdata);

        thanhtoan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkMem exampleDialog = new checkMem();
                exampleDialog.show(getSupportFragmentManager(), "example dialog");
            }
        });

        gianhang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Menu.this, gianhang.class);
                i.putExtra("user", sdata);
                i.putExtra("name", sname);
                startActivity(i);
            }
        });

        khachhang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i = new Intent(Menu.this, AddMember.class);
                i.putExtra("user", sdata);
                i.putExtra("name", sname);
                startActivity(i);

            }
        });

        lichsu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i = new Intent(Menu.this, LsHoadon.class);
                i.putExtra("user", sdata);
                i.putExtra("name", sname);
                startActivity(i);

            }
        });

        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                finish();

            }
        });
    }
    @Override
    public void applyTexts(String username, String password) {
        Intent i = new Intent(Menu.this, lichsucua.class);
        i.putExtra("user", sdata);
        i.putExtra("member", username);
        i.putExtra("phone", password);
        i.putExtra("name", sname);
        startActivity(i);
    }
}