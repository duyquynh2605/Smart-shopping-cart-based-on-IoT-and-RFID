package com.example.root.fire_major_notification;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.database.FirebaseDatabase;

import java.util.List;

public class hoadonAdapter extends RecyclerView.Adapter<hoadonAdapter.ImageViewHolder> {

    private View view;

    List<hoadonClass> Listdata;

    public hoadonAdapter(List<hoadonClass> listdata) {
        this.Listdata = listdata;
    }

    @NonNull
    @Override
    public hoadonAdapter.ImageViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        view = LayoutInflater.from(parent.getContext()).inflate(R.layout.itemhoadon,parent,false);
        hoadonAdapter.ImageViewHolder myHolder = new hoadonAdapter.ImageViewHolder(view);

        return myHolder;
    }

    private Context context;

    public hoadonAdapter(Context context) {
        this.context = context;
    }

    @Override
    public void onBindViewHolder(@NonNull hoadonAdapter.ImageViewHolder holder, int position) {
        hoadonClass data = Listdata.get(position);

        holder.madon.setText( data.getKey());
        String x = data.getNgay() + "/" + data.getThang() + "/" + data.getNam() + "     " + data.getGio() + ":" + data.getPhut();
        holder.time.setText(x);
        holder.sotien.setText(data.getTient() + " VND") ;
        holder.motadon.setText(data.getMota()) ;


        holder.relativeLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                AlertDialog.Builder builder = new AlertDialog.Builder(holder.madon.getContext());
                builder.setTitle("XÓA LỊCH SỬ HÓA ĐƠN");

                builder.setPositiveButton("XÓA", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        String path = "hoadon/user" + data.getUs() +"/"+ data.getKey();
                        FirebaseDatabase.getInstance().getReference(path).removeValue();
                    }
                });

                builder.setNegativeButton("HỦY", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

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
        TextView motadon, madon, time, sotien;
        RelativeLayout relativeLayout;

        public ImageViewHolder(@NonNull View itemView) {
            super(itemView);
            motadon = (TextView) itemView.findViewById(R.id.textView2);
            sotien = (TextView) itemView.findViewById(R.id.textView3);
            madon = (TextView) itemView.findViewById(R.id.textView);
            time = (TextView) itemView.findViewById(R.id.textView4);
            relativeLayout = (RelativeLayout) itemView.findViewById(R.id.rls);
        }
    }
}
