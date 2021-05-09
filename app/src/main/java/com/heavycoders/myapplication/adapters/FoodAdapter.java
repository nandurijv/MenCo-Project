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

public class FoodAdapter extends RecyclerView.Adapter<FoodAdapter.MyViewHolder> {

    ArrayList<Model> list = new ArrayList<>();
    Context context;

    public FoodAdapter(ArrayList<Model> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.layout_food, parent, false);
        return new FoodAdapter.MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        Model model = list.get(position);
        holder.fName.setText(model.getName());
        holder.fCity.setText(model.getCity());
        holder.fState.setText(model.getState());
        String number = model.getNumber();
        holder.fCall.setOnClickListener(v -> {
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
        TextView fName, fCity, fState;
        Button fCall;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            fName = itemView.findViewById(R.id.fName);
            fCity = itemView.findViewById(R.id.fCity);
            fState = itemView.findViewById(R.id.fState);
            fCall = itemView.findViewById(R.id.fCall);
        }
    }
}
