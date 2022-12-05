package com.example.projectt;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

public class CargoAdapter extends RecyclerView.Adapter<CargoAdapter.MyViewHolder>{
    private final OnItemClickListener listener;
    ArrayList<CargoItems> cargoArrayList;
    LayoutInflater inflater;
    //Context context;

    public CargoAdapter(Context context, ArrayList<CargoItems> cargos, OnItemClickListener listener) {
        this.cargoArrayList = cargos;
        inflater = LayoutInflater.from(context);
        this.listener = listener;
    }


    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.item_product_card, parent, false);
        MyViewHolder holder = new MyViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        CargoItems selectedCargo = cargoArrayList.get(position);
        holder.setData(selectedCargo, position);
        int pos = position;
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onItemClick(pos);
            }
        });


        //holder.ID.setText(cargoArrayList.get(position).getID());
        //holder.Type.setText(cargoArrayList.get(position).getType());
        //holder.Weight.setText((int) cargoArrayList.get(position).getWeight());
        //.Volume.setText((int) cargoArrayList.get(position).getVolume());
        //holder.Value.setText((int) cargoArrayList.get(position).getValue());
    }

    @Override
    public int getItemCount() {
        return cargoArrayList.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {

        TextView ID, Type, Weight, Volume, Value, NodeID, DestNodeID;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            //ID = itemView.findViewById(R.id.ID);
            Type = itemView.findViewById(R.id.Type);
            Weight = itemView.findViewById(R.id.Weight);
            Volume = itemView.findViewById(R.id.Volume);
            Value = itemView.findViewById(R.id.Value);
            NodeID = itemView.findViewById(R.id.NodeID);
            DestNodeID = itemView.findViewById(R.id.DestNodeID);
        }

        public void setData(CargoItems selectedCargo, int position){
            //this.ID.setText(String.valueOf(selectedCargo.getID()));
            this.Type.setText(String.valueOf(selectedCargo.getType()));
            this.Weight.setText(String.valueOf(selectedCargo.getWeight()));
            this.Volume.setText(String.valueOf(selectedCargo.getVolume()));
            this.Value.setText(String.valueOf(selectedCargo.getValue()));
            this.NodeID.setText(String.valueOf(selectedCargo.getNodeID()));
            this.DestNodeID.setText(String.valueOf(selectedCargo.getDestNodeID()));
        }
    }
}

