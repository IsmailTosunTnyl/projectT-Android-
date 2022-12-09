package com.example.projectt;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
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

        String source = getIntent().getStringExtra("source");

        if (source.equals("DTS")) {
            CargoItems.cargoItems = new ArrayList<>();

            API.getOwnCargoDTS(new VolleyCallBack() {
                @Override
                public void onSuccess() {
                    ownCargoRecyclerView = findViewById(R.id.ownCargoRV);
                    CargoAdapter cargoAdapter = new CargoAdapter(getApplicationContext(), CargoItems.getData(), new OnItemClickListener_RV() {
                        @Override
                        public void onItemClick(int position) {
                            CargoItems c = CargoItems.cargoItems.get(position);
                            int CargoID = c.getID();
                            int NodeID = c.getNodeID();

                            // OpenBox
                            API.cargoDTS(new VolleyCallBack() {
                                @Override
                                public void onSuccess() {
                                    Intent intent = new Intent(getApplicationContext(), OpenBoxActivity.class);
                                    intent.putExtra("CargoID", CargoID);
                                    intent.putExtra("NodeID", NodeID);
                                    intent.putExtra("kind", "node");
                                    startActivity(intent);
                                }

                                @Override
                                public void onFail() {
                                    Log.d("TAG", "onFail: ");
                                }
                            }, CargoID, NodeID);


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
                    Log.e("TAG", "onFail: ");
                }
            });
        }else if (source.equals("TFS")){
            CargoItems.cargoItems = new ArrayList<>();
            API.getReceiverCargoTFS(new VolleyCallBack() {
                @Override
                public void onSuccess() {

                    ownCargoRecyclerView = findViewById(R.id.ownCargoRV);
                    CargoAdapter cargoAdapter = new CargoAdapter(getApplicationContext(), CargoItems.getData(), new OnItemClickListener_RV() {
                        @Override
                        public void onItemClick(int position) {
                            CargoItems c = CargoItems.cargoItems.get(position);
                            int CargoID = c.getID();
                            int NodeID = c.getDestNodeID();


                            // OpenBox
                            API.cargoTFS(new VolleyCallBack() {
                                @Override
                                public void onSuccess() {
                                    Intent intent = new Intent(getApplicationContext(), OpenBoxActivity.class);
                                    intent.putExtra("CargoID", CargoID);
                                    intent.putExtra("NodeID", NodeID);
                                    intent.putExtra("kind", "destination");
                                    startActivity(intent);
                                }

                                @Override
                                public void onFail() {
                                    Log.d("TAG", "onFail: ");
                                }
                            }, CargoID, NodeID);


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

        } else if (source.equals("SelectRouteActivity")){

            ownCargoRecyclerView = findViewById(R.id.ownCargoRV);
            Log.e("cargosize",CargoItems.cargoItems.size()+"");
            for (int i = 0; i < CargoItems.cargoItems.size(); i++) {
                Log.e("item", CargoItems.cargoItems.get(i).getType());
            }
            CargoAdapter cargoAdapter = new CargoAdapter(getApplicationContext(), CargoItems.getData(), new OnItemClickListener_RV() {
                @Override
                public void onItemClick(int position) {
                    CargoItems c = CargoItems.cargoItems.get(position);
                    int CargoID = c.getID();
                    int NodeID = c.getNodeID();

                    // OpenBox



                }
            });

            ownCargoRecyclerView.setAdapter(cargoAdapter);
            progressBarDTS.setVisibility(View.GONE);
            LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getApplicationContext());
            linearLayoutManager.setOrientation(RecyclerView.VERTICAL);
            linearLayoutManager.scrollToPosition(0);
            ownCargoRecyclerView.setLayoutManager(linearLayoutManager);

                }










        }



    }
