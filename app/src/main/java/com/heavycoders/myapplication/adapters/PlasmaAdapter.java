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

public class PlasmaAdapter extends RecyclerView.Adapter<PlasmaAdapter.MyViewHolder>{
    ArrayList<Model> list = new ArrayList<>();
    Context context;
    public PlasmaAdapter(ArrayList<Model> list, Context context) {
        this.list = list;
        this.context = context;
    }
    @NonNull
    @Override
    public PlasmaAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.layout_plasma, parent, false);
        return new PlasmaAdapter.MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PlasmaAdapter.MyViewHolder holder, int position) {
        Model model = list.get(position);
        holder.pName.setText(model.getName());
        holder.pCity.setText(model.getCity());
        holder.pState.setText(model.getState());
        String number = model.getNumber();
        holder.pCall.setOnClickListener(v -> {
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
        TextView pName, pCity, pState;
        Button pCall;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            pName = itemView.findViewById(R.id.pName);
            pCity = itemView.findViewById(R.id.pCity);
            pState = itemView.findViewById(R.id.pState);
            pCall = itemView.findViewById(R.id.pCall);
        }
    }
}




