package com.example.root.fire_major_notification;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class lichsucua extends AppCompatActivity {

    private String TAG = "test";
    private ImageView quaylai;
    TextView ename,eemail,eaddress;
    private  TextView ngayhienthi;
    Button save,view;
    FirebaseDatabase database;
    DatabaseReference myRef;
    List<Listdata_cua> list;
    List<hanghoaclass> listck;
    RecyclerView recyclerview;
    DatabaseReference dref;
    private ImageView chitiet, lich;
    private EditText t;
    private int nhuan = 0, demkv = 1;
    private  int kngay=0, kthang=0, knam;
    private String []i;
    private  int T, T2;
    private TextView lylon, lynho, tongsoly;
    private int namht, thanght, ngayht;
    private  String chuoi, sngay, sthang;
    private int en = 0;
    private ImageView kc, ql, them;
    private TextView tongsotien, tenkhach;
    private TextView uudai, tongcuoi;
    private int tongtien = 0;
    private int ud = 0;
    private int tc = 0, tcc = 0;
    private String spath;
    private boolean check = false;
    Button thanhtoan;
    private String sdata, sname;
    


    private int flatShow = 0, dist1 = 0, dist2 = 0, dist3 = 0, dist4 = 0, dist5 = 0,
            dist6 = 0, dist7 = 0, dist8 = 0, dist9 = 0, dist10 = 0,
            dist11 = 0, dist12 = 0, distBT = 0, distBTh = 0, distGV = 0,
            distPN = 0, distTB = 0, distTP = 0, distTD = 0, distBC = 0,
            distCG = 0, distCC = 0, distHM = 0, distNB = 0;
    private int sl1 = 0, sl2 = 0, sl3 = 0, sl4 = 0, sl5 = 5;
    private String s1, s2, s3, s4, s5;
    private int e1 = 0, e2 = 0, e3 = 0, e4 = 0, e5 = 0;
    private int gt1 = 0, gt2 = 0, gt3 = 0, gt4 = 0, gt5 = 0;
    private int[] ck = new int[5];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lichsucua);
        sdata = getIntent().getStringExtra("user");
        sname = getIntent().getStringExtra("name");
        String memck = getIntent().getStringExtra("member");
        String phock = getIntent().getStringExtra("phone");
        Log.d(TAG,memck + "  "  + phock);
        //Toast.makeText(this, ""+sdata, Toast.LENGTH_SHORT).show();
        recyclerview = (RecyclerView) findViewById(R.id.abc);
        tongsotien = findViewById(R.id.txttong);
        uudai = findViewById(R.id.txttong2);
        tongcuoi = findViewById(R.id.txttong3);
        tenkhach = findViewById(R.id.txtname);
        thanhtoan = (Button) findViewById(R.id.button);
        tenkhach.setText(sname);
        AlertDialog.Builder builder = new AlertDialog.Builder(this);

        //////////////////////////////////////////////////////////////////////////////////////////
        spath = "member";
        database = FirebaseDatabase.getInstance();
        myRef = database.getReference(spath);
        myRef.addValueEventListener(new ValueEventListener(){

            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {


                for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                    memberClass mcl = dataSnapshot.getValue(memberClass.class);
                    String xx = mcl.getName();
                    String yy = mcl.getNumber();
                    Log.d(TAG,xx + "  "  + yy);
                    if(xx.equals(memck) && yy.equals(phock)){
                        check = true;
                        Log.d(TAG,"BugAAAAAA");
                    }
                }
                CheckData();

            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {
            }
        });
        /////////////////////////////////////////////////////////////////////////////////////////


        //////////////////////////////////////////////////////////////////////////////////////////
        spath = "products";
        database = FirebaseDatabase.getInstance();
        myRef = database.getReference(spath);
        myRef.addValueEventListener(new ValueEventListener(){

            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                Log.d(TAG,"Bug");
                listck = new ArrayList<>();
                for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                    hanghoaclass cl = dataSnapshot.getValue(hanghoaclass.class);
                    String check = dataSnapshot.getKey();
                    Log.d(TAG,check);
                    switch (dataSnapshot.getKey()){
                        case "001":
                            ck[0] = cl.getSoluong();
                        case "002":
                            ck[1] = cl.getSoluong();
                        case "003":
                            ck[2] = cl.getSoluong();
                        case "004":
                            ck[3] = cl.getSoluong();
                        case "005":
                            ck[4] = cl.getSoluong();
                    }
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {
            }
        });
        /////////////////////////////////////////////////////////////////////////////////////////



        thanhtoan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(TAG,Integer.toString(sl5) + " " + ck[4]);
                if(sl1 > ck[0] || sl2 >ck[1] || sl3 > ck[2] || sl4 > ck[3] || sl5 > ck[4]){
                    builder.setTitle("LỖI SỐ LƯỢNG");

                    builder.setPositiveButton("HỦY", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                        }
                    });
                    builder.show();
                }else{
                    builder.setTitle("XÁC NHẬN THANH TOÁN");

                    builder.setPositiveButton("THANH TOÁN", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            String mota = "";
                            for(int i = 1; i <= 5; i++){

                                int hieu = 0;
                                switch (i){
                                    case 1:
                                        hieu =sl1;
                                        if(hieu != 0){
                                            mota += "Pepsi x" + hieu + "; ";
                                        }
                                        break;
                                    case 2:
                                        hieu =sl2;
                                        if(hieu != 0){
                                            mota += "Bánh ngọt x" + hieu + "; ";
                                        }
                                        break;
                                    case 3:
                                        hieu =sl3;
                                        if(hieu != 0){
                                            mota += "Dầu gội đầu x" + hieu + "; ";
                                        }
                                        break;
                                    case 4:
                                        hieu =sl4;
                                        if(hieu != 0){
                                            mota += "Cà phê đá x" + hieu + "; ";
                                        }
                                        break;
                                    case 5:
                                        hieu =sl5;
                                        if(hieu != 0){
                                            mota += "Rượu vang x" + hieu + "; ";
                                        }
                                        break;

                                }
                                String update =  "products/00" + i + "/soluong";
                                FirebaseDatabase.getInstance().getReference(update).setValue(ck[i-1]-hieu);

                            }

                            String spath = "thanhtoan/user";
                            FirebaseDatabase.getInstance().getReference(spath).removeValue();
                            Intent i = new Intent(lichsucua.this, qrcodeActivity.class);
                            i.putExtra("user", sdata);
                            i.putExtra("tien", tcc + "" );
                            i.putExtra("mota", mota);
                            startActivity(i);
                        }
                    });

                    builder.setNegativeButton("HỦY", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            Toast.makeText(view.getContext(), "DA HUY" , Toast.LENGTH_SHORT).show();
                        }
                    });
                    builder.show();
                }


            }
        });
        

        

    }

    private void CheckData() {
        spath = "thanhtoan/user";
        database = FirebaseDatabase.getInstance();
        myRef = database.getReference(spath);
        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot)
            {
                try {
                    Log.d(TAG,check + "");
                    sl1 = 0; sl2 = 0; sl3 = 0; sl4 = 0; sl5 = 0;
                    e1 = 0; e2 = 0; e3 = 0; e4 = 0; e5 = 0;
                    gt1 = 0; gt2 = 0; gt3 = 0; gt4 = 0; gt5 = 0;
                    list = new ArrayList<>();
                    for (DataSnapshot dataSnapshot1 : dataSnapshot.getChildren()) {
                        GetCua ui = dataSnapshot1.getValue(GetCua.class);
                        Listdata_cua listdata = new Listdata_cua();
                        String shanghoa = ui.getTenhang();
                        if(shanghoa.equals("001"))
                        {
                            sl1++;
                        }
                        if(shanghoa.equals("002"))
                        {
                            sl2++;
                        }
                        if(shanghoa.equals("003"))
                        {
                            sl3++;
                        }
                        if(shanghoa.equals("004"))
                        {
                            sl4++;
                        }
                        if(shanghoa.equals("005"))
                        {
                            sl5++;
                        }
                    }

                    gt1 = sl1; gt2 = sl2; gt3 = sl3; gt4 = sl4; gt5 = sl5;

                    if(gt1==0 &&gt2==0&&gt3==0&&gt4==0&&gt5==0)
                    {
                        tongsotien.setText("" + 0 + " VND");
                        uudai.setText("" + 0 + " VND");
                        tongcuoi.setText("" + 0 + " VND");
                    }

                    for (DataSnapshot dataSnapshot1 : dataSnapshot.getChildren())
                    {

                        GetCua ui = dataSnapshot1.getValue(GetCua.class);
                        Listdata_cua listdata = new Listdata_cua();

                        String shanghoa = ui.getTenhang();
                        String sgia = ui.getGia();
                        int sid = ui.getId();
                        String sus = ui.getUs();
                        String key = dataSnapshot1.getKey();
                        Log.d(TAG,sus);



                        if(shanghoa.equals("001"))
                        {
                            if(sl1 == gt1 &&e1==0)
                            {
                                tongtien += Integer.parseInt(sgia) * sl1;
                                tongsotien.setText("" + tongtien + " VND");
                                if(check){
                                    ud = -tongtien/10;
                                }else{
                                    ud =0;
                                }
                                tc = tongtien + ud;
                                tcc = tc;
                                uudai.setText("" + ud + " VND");
                                tongcuoi.setText("" + tc + " VND");
                                listdata.setTenhang("Pepsi");
                                listdata.setSoluong("" + sl1);
                                listdata.setGia(sgia);
                                listdata.setId(sid);
                                listdata.setUs(sus);
                                listdata.setkey(key);
                                list.add(listdata);
                                e1 = 1;
                            }
                        }


                        else if(shanghoa.equals("002"))
                        {
                            if(sl2 == gt2 &&e2==0)
                            {
                                tongtien += Integer.parseInt(sgia) * sl2;
                                tongsotien.setText("" + tongtien + " VND");
                                if(check){
                                    ud = -tongtien/10;
                                }else{
                                    ud =0;
                                }
                                tc = tongtien + ud;
                                tcc = tc;
                                uudai.setText("" + ud + " VND");
                                tongcuoi.setText("" + tc + " VND");
                                listdata.setTenhang("Bánh ngọt");
                                listdata.setSoluong("" + sl2);
                                listdata.setGia(sgia);
                                listdata.setId(sid);
                                listdata.setUs(sus);
                                list.add(listdata);
                                listdata.setkey(key);
                                e2 = 1;
                            }
                        }

                        else  if(shanghoa.equals("003"))
                        {
                            if(sl3 == gt3 &&e3==0)
                            {
                                tongtien += Integer.parseInt(sgia) * sl3;
                                tongsotien.setText("" + tongtien + " VND");
                                if(check){
                                    ud = -tongtien/10;
                                }else{
                                    ud =0;
                                }
                                tc = tongtien + ud;
                                tcc = tc;
                                uudai.setText("" + ud + " VND");
                                tongcuoi.setText("" + tc + " VND");
                                listdata.setTenhang("Dầu gội đầu");
                                listdata.setSoluong("" + sl3);
                                listdata.setGia(sgia);
                                listdata.setId(sid);
                                listdata.setUs(sus);
                                list.add(listdata);
                                listdata.setkey(key);
                                e3 = 1;
                            }
                        }

                        else if(shanghoa.equals("004"))
                        {
                            if(sl4 == gt4 &&e4==0)
                            {
                                tongtien += Integer.parseInt(sgia) * sl4;
                                tongsotien.setText("" + tongtien + " VND");
                                tongsotien.setText("" + tongtien + " VND");
                                if(check){
                                    ud = -tongtien/10;
                                }else{
                                    ud =0;
                                }
                                tc = tongtien + ud;
                                tcc = tc;
                                uudai.setText("" + ud + " VND");
                                tongcuoi.setText("" + tc + " VND");
                                listdata.setTenhang("Cà phê đá");
                                listdata.setSoluong("" + sl4);
                                listdata.setGia(sgia);
                                listdata.setId(sid);
                                listdata.setUs(sus);
                                list.add(listdata);
                                listdata.setkey(key);
                                e4 = 1;
                            }
                        }

                        else if(shanghoa.equals("005"))
                        {
                            if(sl5 == gt5 &&e5==0)
                            {
                                tongtien += Integer.parseInt(sgia) * sl5;
                                tongsotien.setText("" + tongtien + " VND");
                                if(check){
                                    ud = -tongtien/10;
                                }else{
                                    ud =0;
                                }
                                tc = tongtien + ud;
                                tcc = tc;
                                uudai.setText("" + ud + " VND");
                                tongcuoi.setText("" + tc + " VND");
                                listdata.setTenhang("Rượu vang");
                                listdata.setSoluong("" + sl5);
                                listdata.setGia(sgia);
                                listdata.setId(sid);
                                listdata.setUs(sus);
                                list.add(listdata);
                                listdata.setkey(key);
                                e5 = 1;
                            }

                        }
                        else
                        {

                        }




                    }

                    //Toast.makeText(lichsucua.this, ""+ sl1, Toast.LENGTH_SHORT).show();



                    tongtien = 0;
                    ud = 0; tc =0;
                    Recyclercua recycler = new Recyclercua( list);
                    RecyclerView.LayoutManager layoutmanager = new LinearLayoutManager(lichsucua.this);
                    recyclerview.setLayoutManager(layoutmanager);
                    recyclerview.setItemAnimator( new DefaultItemAnimator());
                    recyclerview.setAdapter(recycler);

                }
                catch (Exception ex) {}

            }



            @Override
            public void onCancelled(DatabaseError error) {
            }
        });

    }


}