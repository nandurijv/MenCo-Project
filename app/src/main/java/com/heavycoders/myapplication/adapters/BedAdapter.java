package com.heavycoders.myapplication.adapters;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.heavycoders.myapplication.R;
import com.heavycoders.myapplication.models.Model;

import java.util.ArrayList;

public class BedAdapter extends RecyclerView.Adapter<BedAdapter.MyViewHolder> {
    ArrayList<Model> list = new ArrayList<>();
    Context context;

    public BedAdapter(ArrayList<Model> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.layout_bed, parent, false);
        return new BedAdapter.MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        Model model = list.get(position);
        holder.bName.setText(model.getName());
        holder.bCity.setText(model.getCity());
        holder.bState.setText(model.getState());
        String number = model.getNumber();
        holder.bCall.setOnClickListener(v -> {
            Intent call = new Intent(Intent.ACTION_DIAL);
            call.setData(Uri.parse("tel:+91"+number));
            context.startActivity(call);
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{
        TextView bName, bCity, bState;
        Button bCall;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            bName = itemView.findViewById(R.id.bName);
            bCity = itemView.findViewById(R.id.bCity);
            bState = itemView.findViewById(R.id.bState);
            bCall = itemView.findViewById(R.id.bCall);
        }
    }
}
