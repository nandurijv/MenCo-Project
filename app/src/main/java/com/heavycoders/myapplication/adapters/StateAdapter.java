package com.heavycoders.myapplication.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.heavycoders.myapplication.R;
import com.heavycoders.myapplication.models.StateModel;

import java.util.ArrayList;
import java.util.List;

public class StateAdapter extends RecyclerView.Adapter<StateAdapter.MyViewHolder>{
    Context context;
    ArrayList<StateModel> list = new ArrayList<>();
    public StateAdapter(Context context, ArrayList<StateModel> list) {
        this.context = context;
        this.list = list;
    }


    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.state_layout, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        StateModel model = list.get(position);
        holder.stateName.setText(model.getState());
        holder.stateActive.setText(model.getActive());
        holder.stateDeath.setText(model.getDeath());
        holder.stateRecovered.setText(model.getRecovered());
        holder.stateConfirmed.setText(model.getConfirmed());
        holder.stateLastUpdate.setText("Last updated on " + model.getLastUpdated());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{
        TextView stateName;
        TextView stateActive;
        TextView stateDeath ;
        TextView stateRecovered;
        TextView stateConfirmed;
        TextView stateLastUpdate;
        public MyViewHolder(@NonNull View view) {
            super(view);
            stateName = view.findViewById(R.id.stateName);
            stateActive = view.findViewById(R.id.stateActive);
            stateDeath = view.findViewById(R.id.stateDeath);
            stateRecovered = view.findViewById(R.id.stateRecovered);
            stateConfirmed = view.findViewById(R.id.stateConfirmed);
            stateLastUpdate = view.findViewById(R.id.stateLastUpdate);
        }
    }
}
