package com.example.projectt;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;

import java.util.ArrayList;

public class OwnCargoActivity extends AppCompatActivity {
    RecyclerView ownCargoRecyclerView;
    ProgressBar progressBarDTS;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_own_cargo);
        progressBarDTS = findViewById(R.id.progressBarDTS);
        CargoItems.cargoItems = new ArrayList<>();
        API.getOwnCargoDTS(new VolleyCallBack() {
            @Override
            public void onSuccess() {
                ownCargoRecyclerView = findViewById(R.id.ownCargoRV);
                CargoAdapter cargoAdapter = new CargoAdapter(getApplicationContext(), CargoItems.getData(), new OnItemClickListener() {
                    @Override
                    public void onItemClick(int position) {
                        CargoItems c = CargoItems.cargoItems.get(position);
                        int CargoID = c.getID();
                        int NodeID = c.getNodeID();

                    }
                });

                ownCargoRecyclerView.setAdapter(cargoAdapter);
                progressBarDTS.setVisibility(View.GONE);
                LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getApplicationContext());
                linearLayoutManager.setOrientation(RecyclerView.VERTICAL);
                linearLayoutManager.scrollToPosition(0);
                ownCargoRecyclerView.setLayoutManager(linearLayoutManager);
            }

            @Override
            public void onFail() {

            }
        });



    }
}