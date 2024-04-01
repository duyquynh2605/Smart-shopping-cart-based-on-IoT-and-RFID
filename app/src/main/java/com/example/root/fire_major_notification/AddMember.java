package com.example.root.fire_major_notification;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;

public class AddMember extends AppCompatActivity {

    FirebaseDatabase db;
    DatabaseReference reference;
    EditText mname;
    EditText mphone;
    Button dk, back;
    String name, phone;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_member);
        mname = (EditText) findViewById(R.id.user);
        mphone = (EditText) findViewById(R.id.pss);
        dk  = (Button) findViewById(R.id.dkk);
        back  = (Button) findViewById(R.id.back);
        String sdata = getIntent().getStringExtra("user");

        dk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                name = mname.getText().toString();
                phone = mphone.getText().toString();
                // = db.getReference("member");
                if(!name.isEmpty() && !phone.isEmpty()){
                    member members = new member(name,phone);
                    Log.d("app",name + " "  + phone);
                    FirebaseDatabase.getInstance().getReference().child("member").push().setValue(members).addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {

                                    mname.setText("");
                                    mphone.setText("");
                                    Toast.makeText(AddMember.this,"ĐĂNG KÍ THÀNH CÔNG",Toast.LENGTH_SHORT).show();
                                }
                            }).addOnFailureListener(new OnFailureListener() {
                                @Override
                                public void onFailure(@NonNull Exception e) {
                                    Log.d("app","failed to inserted: "+e.getMessage());
                                }
                            });

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