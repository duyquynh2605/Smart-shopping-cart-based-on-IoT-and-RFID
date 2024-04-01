package com.example.root.fire_major_notification;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class LsHoadon extends AppCompatActivity {

    FirebaseDatabase database;
    DatabaseReference myRef;
    RecyclerView recyclerview;
    private TextView tenkhach;
    private String sdata, sname;
    private String spath;
    List<hoadonClass> listhd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ls_hoadon);
        sdata = getIntent().getStringExtra("user");
        sname = getIntent().getStringExtra("name");
        recyclerview = (RecyclerView) findViewById(R.id.abc);
        tenkhach = findViewById(R.id.txtname);
        tenkhach.setText(sname);



        //////////////////////////////////////////////////////////////////////////////////////////
        spath = "hoadon/user" + sdata;
        database = FirebaseDatabase.getInstance();
        myRef = database.getReference(spath);
        myRef.addValueEventListener(new ValueEventListener(){

            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                listhd = new ArrayList<>();
                for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                    hoadonClass listdata = new hoadonClass();
                    hoadonClass hd = dataSnapshot.getValue(hoadonClass.class);
                    listdata.setKey(dataSnapshot.getKey());
                    listdata.setTient(hd.getTient());
                    listdata.setMota(hd.getMota());
                    listdata.setNgay(hd.getNgay());
                    listdata.setThang(hd.getThang());
                    listdata.setNam(hd.getNam());
                    listdata.setGio(hd.getGio());
                    listdata.setPhut(hd.getPhut());
                    listdata.setUs(sdata);
                    listhd.add(listdata);
                }

                hoadonAdapter recycler = new hoadonAdapter(listhd);
                RecyclerView.LayoutManager layoutmanager = new LinearLayoutManager(LsHoadon.this);
                recyclerview.setLayoutManager(layoutmanager);
                recyclerview.setItemAnimator( new DefaultItemAnimator());
                recyclerview.setAdapter(recycler);


            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {
            }
        });
        /////////////////////////////////////////////////////////////////////////////////////////
    }
}