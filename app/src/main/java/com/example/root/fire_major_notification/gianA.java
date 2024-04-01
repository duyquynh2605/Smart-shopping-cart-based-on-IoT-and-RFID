package com.example.root.fire_major_notification;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;


public class gianA extends Fragment {

    private String TAG = "test";
    FirebaseDatabase database;
    DatabaseReference myRef;
    List<hanghoaclass> list;
    RecyclerView recyclerview;
    private String spath;

    Context mContext;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(TAG,"Bug222");
        //list = new ArrayList<>();
        spath = "products";
        database = FirebaseDatabase.getInstance();
        myRef = database.getReference(spath);
        myRef.addValueEventListener(new ValueEventListener(){

            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                Log.d(TAG,"Bug");
                list = new ArrayList<>();
                for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                    hanghoaclass cl = dataSnapshot.getValue(hanghoaclass.class);
                    if(cl.getType() == 0){
                    int a = cl.getType();
                    list.add(cl);
                    Log.d(TAG,Integer.toString(a));
                    }
                }
                ghRecycler recycler = new ghRecycler(list);
                recyclerview.setAdapter(recycler);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
            }
        });
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view=inflater.inflate(R.layout.fragment_gian_a, container, false);
        recyclerview = view.findViewById(R.id.gianA);
        Log.d(TAG,"BugBBBB");
        myRef.addValueEventListener(new ValueEventListener(){

            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                Log.d(TAG,"Bug");
                list = new ArrayList<>();
                for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                    hanghoaclass cl = dataSnapshot.getValue(hanghoaclass.class);
                    if(cl.getType() == 0){
                        int a = cl.getType();
                        list.add(cl);
                        Log.d(TAG,Integer.toString(a));
                    }
                }
                ghRecycler recycler = new ghRecycler(list);
                recyclerview.setAdapter(recycler);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
            }
        });
        RecyclerView.LayoutManager layoutmanager = new LinearLayoutManager(getContext());
        recyclerview.setLayoutManager(layoutmanager);
        recyclerview.setItemAnimator( new DefaultItemAnimator());

        return view;
    }
}