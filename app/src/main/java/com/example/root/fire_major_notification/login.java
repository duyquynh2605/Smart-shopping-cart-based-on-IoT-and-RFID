package com.example.root.fire_major_notification;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
//import android.support.v7.app.AppCompatActivity;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class login extends AppCompatActivity {

    Button login, dk;
    EditText x, y;
    private CheckBox save;
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;
    FirebaseDatabase database;
    DatabaseReference myRef;
    private String spath;

    boolean checkk = false;
    private String user,pass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.login);
        x = (EditText) findViewById(R.id.user);
        y = (EditText) findViewById(R.id.pss);
        login  = (Button) findViewById(R.id.dkk);
        dk  = (Button) findViewById(R.id.lgdk);
        save = findViewById(R.id.checkBox);

        sharedPreferences = getSharedPreferences("USER_PASS_PREFS", Context.MODE_PRIVATE);
        editor = sharedPreferences.edit();

        x.setText(sharedPreferences.getString("PREF_USER", ""));
        y.setText(sharedPreferences.getString("PREF_PASS", ""));


        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                user = x.getText().toString();
                pass = y.getText().toString();
                spath = "nhanvien";
                database = FirebaseDatabase.getInstance();
                myRef = database.getReference(spath);
                myRef.addValueEventListener(new ValueEventListener(){

                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {


                        for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                            nvClass mcl = dataSnapshot.getValue(nvClass.class);
                            String xx = mcl.getName();
                            String yy = mcl.getPass();
                            Log.d("TAG",xx + "  "  + yy);
                            if(xx.equals(user) && yy.equals(pass)){
                                checkk = true;
                                x.setText(sharedPreferences.getString("PREF_USER", ""));
                                y.setText(sharedPreferences.getString("PREF_PASS", ""));
                                Intent i = new Intent(login.this, Menu.class);
                                i.putExtra("user", dataSnapshot.getKey());
                                i.putExtra("name", xx);
                                startActivity(i);
                                Log.d("TAG","BugAAAAAA");
                                user = "";  pass ="";
                            }
                        }

                    }
                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {
                    }
                });
            }
        });
        dk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(login.this, dkNhanvien.class);
                startActivity(i);
            }
        });
    }


    protected void onPause() {
        super.onPause();

        if (!save.isChecked())
        {
            editor.clear();
        }
        else
        {
            editor.putString("PREF_USER",x.getText().toString().trim());
            editor.putString("PREF_PASS",y.getText().toString().trim());
            editor.putBoolean("PREF_CHECKED",save.isChecked());
        }
        editor.commit();
    }

    protected void onResume()
    {
        super.onResume();

        boolean ischk = sharedPreferences.getBoolean("PREF_CHECKED", false);
        if (ischk)
        {
            String userp = sharedPreferences.getString("PREF_USER","");
            String password = sharedPreferences.getString("PREF_PASS","");
            x.setText(userp);
            y.setText(password);
        }
        save.setChecked(ischk);
    }
}
