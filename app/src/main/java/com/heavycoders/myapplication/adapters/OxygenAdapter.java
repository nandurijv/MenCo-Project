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

public class OxygenAdapter extends RecyclerView.Adapter<OxygenAdapter.MyViewHolder> {

    ArrayList<Model> list = new ArrayList<>();
    Context context;
    public OxygenAdapter(ArrayList<Model> list, Context context) {
        this.list = list;
        this.context = context;
    }
    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.layout_oxygen, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        Model model = list.get(position);
        holder.oName.setText(model.getName());
        holder.oCity.setText(model.getCity());
        holder.oState.setText(model.getState());
        String number = model.getNumber();
        holder.oCall.setOnClickListener(v -> {
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
        TextView oName, oCity, oState;
        Button oCall;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            oName = itemView.findViewById(R.id.oName);
            oCity = itemView.findViewById(R.id.oCity);
            oState = itemView.findViewById(R.id.oState);
            oCall = itemView.findViewById(R.id.oCall);
        }
    }
}
