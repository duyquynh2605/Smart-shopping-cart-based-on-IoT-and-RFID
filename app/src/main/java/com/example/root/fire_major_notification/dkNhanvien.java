package com.example.root.fire_major_notification;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class dkNhanvien extends AppCompatActivity {
    FirebaseDatabase db;
    DatabaseReference reference;
    EditText mname;
    EditText mk, mk2;
    Button dkk, back;
    String name, pass, pass2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dk_nhanvien);
        mname = (EditText) findViewById(R.id.user);
        mk = (EditText) findViewById(R.id.pss);
        mk2 = (EditText) findViewById(R.id.pssthem);
        dkk  = (Button) findViewById(R.id.dkkk);
        back  = (Button) findViewById(R.id.back);
        String sdata = getIntent().getStringExtra("user");

        dkk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                name = mname.getText().toString();
                pass = mk.getText().toString();
                pass2 = mk2.getText().toString();
                // = db.getReference("member");
                if(!name.isEmpty() && !pass.isEmpty() && !pass2.isEmpty()){
                    if(pass.equals(pass2)){
                        nvClass members = new nvClass();
                        members.setName(name);
                        members.setPass(pass);
                        Log.d("app",name + " "  + pass);
                        FirebaseDatabase.getInstance().getReference().child("nhanvien").push().setValue(members).addOnCompleteListener(new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {

                                mname.setText("");
                                mk.setText("");
                                mk2.setText("");
                                Toast.makeText(dkNhanvien.this,"ĐĂNG KÍ THÀNH CÔNG",Toast.LENGTH_SHORT).show();
                            }
                        });
                    }else{
                        mname.setText("");
                        mk.setText("");
                        mk2.setText("");
                        Toast.makeText(dkNhanvien.this,"MẬT KHẨU KHÔNG TRÙNG KHỚP",Toast.LENGTH_SHORT).show();
                    }


                }else{
                    Toast.makeText(dkNhanvien.this,"CHƯA NHẬP ĐỦ THÔNG TIN",Toast.LENGTH_SHORT).show();
                }


            }
        });

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();

            }
        });
    }
}