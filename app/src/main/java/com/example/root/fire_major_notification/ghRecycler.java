package com.example.root.fire_major_notification;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.database.FirebaseDatabase;

import java.util.List;

public class ghRecycler extends RecyclerView.Adapter<ghRecycler.ImageViewHolder> {

    private View view;

    List<hanghoaclass> Listdata;

    public ghRecycler(List<hanghoaclass> listdata) {
        this.Listdata = listdata;
    }

    @NonNull
    @Override
    public ImageViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        view = LayoutInflater.from(parent.getContext()).inflate(R.layout.itemhang2,parent,false);
        ghRecycler.ImageViewHolder myHolder = new ghRecycler.ImageViewHolder(view);

        return myHolder;
    }

    private Context context;

    public ghRecycler(Context context) {
        this.context = context;
    }

    @Override
    public void onBindViewHolder(@NonNull ImageViewHolder holder, int position) {

        hanghoaclass data = Listdata.get(position);

        holder.tenhang.setText( data.getTenhang());
        String x = Integer.toString(data.getSoluong());
        holder.soluong.setText(x);
        holder.gia.setText(data.getGia() + " VND") ;


        holder.relativeLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                AlertDialog.Builder builder = new AlertDialog.Builder(holder.tenhang.getContext());
                builder.setTitle("CẬP NHẬT SỐ LƯỢNG SẢN PHẨM");

                builder.setPositiveButton("XÁC NHẬN", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        String path = "products/" + data.getTenhang2() +"/soluong";
                        FirebaseDatabase.getInstance().getReference(path).setValue(100);
                    }
                });

                builder.setNegativeButton("HỦY", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        //Toast.makeText(view.getContext(), "KHÔNG XÓA" + data.getId(), Toast.LENGTH_SHORT).show();
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

    public class ImageViewHolder extends RecyclerView.ViewHolder {
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
