package com.example.projectt;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

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
        Log.e("source",source);
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
                                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
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
                                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
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
            Toast.makeText(this, "Click a cargo if you want to take all", Toast.LENGTH_SHORT).show();

            int position = getIntent().getIntExtra("route", 0);
            ownCargoRecyclerView = findViewById(R.id.ownCargoRV);
            Log.e("cargosize",CargoItems.cargoItems.size()+"");

            CargoAdapter cargoAdapter;
            Log.e("route",position+"");
            if (position == 0){

                cargoAdapter = new CargoAdapter(getApplicationContext(), CargoItems.routeItems, new OnItemClickListener_RV() {
                    @Override
                    public void onItemClick(int position) {
                        CargoItems c = CargoItems.routeItems.get(position);
                        int CargoID = c.getID();
                        int NodeID = c.getNodeID();



                        String cargos_id = "";
                        for (int i = 0; i < CargoItems.routeItems.size(); i++) {
                            cargos_id += CargoItems.routeItems.get(i).getID()+"-";
                        }

                        API.postHashMap(new VolleyCallBack() {
                            @Override
                            public void onSuccess() {

                                Log.e("Cargo", "Cargo posted");
                                Toast.makeText(getApplicationContext(), "Cargos Added Your Bag", Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(getApplicationContext(), ProfileActivity.class);
                                // remove this activity from stack
                                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);

                                startActivity(intent);
                            }

                            @Override
                            public void onFail() {
                                Log.e("Cargo", "Cargo cant posted");
                            }
                        },cargos_id);

                    }
                });


            }
            else{

            cargoAdapter = new CargoAdapter(getApplicationContext(), CargoItems.getData(), new OnItemClickListener_RV() {
                @Override
                public void onItemClick(int position) {
                    CargoItems c = CargoItems.cargoItems.get(position);
                    int CargoID = c.getID();
                    int NodeID = c.getNodeID();

                    String cargos_id = "";
                    for (int i = 0; i < CargoItems.cargoItems.size(); i++) {
                        cargos_id += CargoItems.cargoItems.get(i).getID()+"-";
                    }

                    API.postHashMap(new VolleyCallBack() {
                        @Override
                        public void onSuccess() {
                            Toast.makeText(getApplicationContext(), "Cargos Added Your Bag", Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(getApplicationContext(), ProfileActivity.class);
                            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                            startActivity(intent);
                            Log.e("Cargo", "Cargo posted");
                        }

                        @Override
                        public void onFail() {
                            Log.e("Cargo", "Cargo cant posted");
                        }
                    },cargos_id);



                }
            });
            }


            for (int i = 0; i < CargoItems.cargoItems.size(); i++) {
                Log.e("item", CargoItems.cargoItems.get(i).getType());
            }
            ownCargoRecyclerView.setAdapter(cargoAdapter);
            progressBarDTS.setVisibility(View.GONE);
            LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getApplicationContext());
            linearLayoutManager.setOrientation(RecyclerView.VERTICAL);
            linearLayoutManager.scrollToPosition(0);
            ownCargoRecyclerView.setLayoutManager(linearLayoutManager);

                }

        else if(source.equals("DriverTakeCargo"))
        {


            API.listdriverscargo(new VolleyCallBack() {
                @Override
                public void onSuccess() {
                    CargoAdapter cargoAdapter;
                    ownCargoRecyclerView = findViewById(R.id.ownCargoRV);
                    cargoAdapter = new CargoAdapter(getApplicationContext(), CargoItems.getData(), new OnItemClickListener_RV() {
                        @Override
                        public void onItemClick(int position) {
                            CargoItems c = CargoItems.cargoItems.get(position);
                            int CargoID = c.getID();
                            int NodeID = c.getNodeID();

                            API.opendriverbox(new VolleyCallBack() {
                                @Override
                                public void onSuccess() {
                                    Intent intent = new Intent(getApplicationContext(), OpenBoxActivity.class);
                                    intent.putExtra("CargoID", CargoID);
                                    intent.putExtra("NodeID", NodeID);
                                    intent.putExtra("kind", "open");
                                    intent.putExtra("takeordelivery", "take");
                                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                                    startActivity(intent);
                                }

                                @Override
                                public void onFail() {
                                    Log.d("TAG", "onFail: ");
                                }
                            }, CargoID, NodeID,"open","take");


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
            },"take");



        }

        else if(source.equals("DriverDropCargo"))
        {


            API.listdriverscargo(new VolleyCallBack() {
                @Override
                public void onSuccess() {
                    CargoAdapter cargoAdapter;
                    ownCargoRecyclerView = findViewById(R.id.ownCargoRV);
                    cargoAdapter = new CargoAdapter(getApplicationContext(), CargoItems.getData(), new OnItemClickListener_RV() {
                        @Override
                        public void onItemClick(int position) {
                            CargoItems c = CargoItems.cargoItems.get(position);
                            int CargoID = c.getID();
                            int NodeID = c.getDestNodeID();

                            API.opendriverbox(new VolleyCallBack() {
                                @Override
                                public void onSuccess() {
                                    Intent intent = new Intent(getApplicationContext(), OpenBoxActivity.class);
                                    intent.putExtra("CargoID", CargoID);
                                    intent.putExtra("NodeID", NodeID);
                                    intent.putExtra("kind", "open");
                                    intent.putExtra("takeordelivery", "drop");
                                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);

                                    Log.e("Box Opened,destnode Id", ""+NodeID);
                                    startActivity(intent);

                                }

                                @Override
                                public void onFail() {
                                    Log.d("TAG", "onFail: ");
                                }
                            }, CargoID, NodeID,"open","drop");


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
            },"drop");












        }










        }



    }
