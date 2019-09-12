package com.example.volleyjsondemo.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.volleyjsondemo.R;
import com.example.volleyjsondemo.pojo.Flat;

import java.util.ArrayList;

public class FlatDataAdapter extends RecyclerView.Adapter<FlatDataAdapter.FlatDataViewHolder> {

    public Context context;
    public ArrayList<Flat> data;

    public FlatDataAdapter(Context context, ArrayList<Flat> data) {
        this.context = context;
        this.data = data;
    }

    @NonNull
    @Override
    public FlatDataViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater=LayoutInflater.from(parent.getContext());
        View view=inflater.inflate(R.layout.row,parent,false);
        return new FlatDataViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull FlatDataViewHolder holder, int position) {
       Flat flat=data.get(position);
       holder.tvFlatno.setText(flat.getVisitor_type_id());
       holder.tvOwnerName.setText(flat.getVisitor_type());
       holder.tvOwnerEmail.setText(flat.getStatus());
      // holder.tv_OwnerContactNo.setText(flat.getOwner_contact_no());
    }

    @Override
    public int getItemCount() {
        return data.size();
    }




    public class FlatDataViewHolder extends RecyclerView.ViewHolder{

        public TextView tvFlatno,tvOwnerName,tvOwnerEmail,tv_OwnerContactNo;

        public FlatDataViewHolder(@NonNull View itemView) {
            super(itemView);
            tvFlatno=itemView.findViewById(R.id.tv1);
            tvOwnerName=itemView.findViewById(R.id.tv2);
            tvOwnerEmail=itemView.findViewById(R.id.tv3);
            tv_OwnerContactNo=itemView.findViewById(R.id.tv4);
        }
    }

    public void updateData(Context context,ArrayList<Flat> data) {
        this.context=context;
        this.data = data;
        notifyDataSetChanged();
    }

}
