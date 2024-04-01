package com.example.root.fire_major_notification;


import android.app.AlertDialog;
import android.content.Context;
//import android.support.v7.widget.RecyclerView;
import android.content.DialogInterface;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

//import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.List;


public class Recyclercua extends RecyclerView.Adapter<Recyclercua.ImageViewHolder>{

    private View view;

    List<Listdata_cua> Listdata;
    
    public Recyclercua(List<Listdata_cua> listdata) {
        this.Listdata = listdata;
    }

    @Override
    public ImageViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        view = LayoutInflater.from(parent.getContext()).inflate(R.layout.itemhang,parent,false);
        ImageViewHolder myHolder = new ImageViewHolder(view);

        return myHolder;
    }



    private Context context;

    public Recyclercua(Context context) {
        this.context = context;
    }


    public void onBindViewHolder(@NonNull final ImageViewHolder holder, int position) {
        Listdata_cua data = Listdata.get(position);

        holder.tenhang.setText( data.getTenhang());
        holder.soluong.setText(data.getSoluong());
        holder.gia.setText(data.getGia() + " VND") ;


        holder.relativeLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                AlertDialog.Builder builder = new AlertDialog.Builder(holder.tenhang.getContext());
                builder.setTitle("BẠN CÓ CHẮC CHẮN XÓA 1 SẢN PHẨM");
                builder.setMessage("❏ " + data.getTenhang());
                
                builder.setPositiveButton("XÓA", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        String spath = "thanhtoan/user/" + data.getkey();
                        FirebaseDatabase.getInstance().getReference(spath).removeValue();
                        //FirebaseDatabase.getInstance().getReference(spath).child(data.getUs()).removeValue();
                        Toast.makeText(view.getContext(), "ĐÃ XÓA! ", Toast.LENGTH_SHORT).show();
                    }
                });
                
                builder.setNegativeButton("HỦY", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(view.getContext(), "KHÔNG XÓA" + data.getId(), Toast.LENGTH_SHORT).show();
                    }
                });
                builder.show();
            }
        });
        
        
    }

    @Override
    public int getItemCount() {
        return Listdata.size();
    }


    public class ImageViewHolder extends RecyclerView.ViewHolder
    {
        TextView tenhang, soluong, gia;
        RelativeLayout relativeLayout;
        


        public ImageViewHolder(@NonNull View itemView) {
            super(itemView);
            tenhang = (TextView) itemView.findViewById(R.id.txttenhang);
            soluong = (TextView) itemView.findViewById(R.id.txtsl);
            gia = (TextView) itemView.findViewById(R.id.txtgia);
            relativeLayout = (RelativeLayout) itemView.findViewById(R.id.rls);
            
        }


    }


}
