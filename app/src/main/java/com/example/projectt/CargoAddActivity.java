package com.example.projectt;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;


import com.google.android.material.textfield.TextInputLayout;

public class CargoAddActivity extends AppCompatActivity {
    TextInputLayout receiver_txtInput, volume_txtInput, weight_txtInput;
    Spinner cargoType_spinner, destination_spinner,source_spinner;
    Button addCargo_btn;
    private ArrayAdapter<String> cargoTypeAdapter, destinationAdapter;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_cargo_screen);
        init();
        //remove later
        String pass="";
        try {
            pass = Encriptions.encText("1234");
        } catch (Exception e) {
            e.printStackTrace();
        }

        //TODO remove later
        API api = new API("mail60",pass,this);


        API.getNodes(new VolleyCallBack() {
            @Override
            public void onSuccess() {
                String[] nodeNames = new String[Node.nodes.size()];
                for(int i = 0; i < Node.nodes.size(); i++){
                    nodeNames[i] = Node.nodes.get(i).getID()+"."+Node.nodes.get(i).getNodeName();
                }
                destinationAdapter = new ArrayAdapter<String>(CargoAddActivity.this, android.R.layout.simple_spinner_item, nodeNames);
                destinationAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                destination_spinner.setAdapter(destinationAdapter);
                source_spinner.setAdapter(destinationAdapter);
            }

            @Override
            public void onFail() {

            }
        });

        String[] cargoType = {"Food", "Medicine", "Clothes","Electronics", "Other"};
        cargoTypeAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, cargoType);
        cargoTypeAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        cargoType_spinner.setAdapter(cargoTypeAdapter);



        addCargo_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String receiver = receiver_txtInput.getEditText().getText().toString();
                String volume = volume_txtInput.getEditText().getText().toString();
                String weight = weight_txtInput.getEditText().getText().toString();
                String cargoType = cargoType_spinner.getSelectedItem().toString();
                String destination = destination_spinner.getSelectedItem().toString();
                String source = source_spinner.getSelectedItem().toString();
                if(receiver.isEmpty() || volume.isEmpty() || weight.isEmpty() || cargoType.isEmpty() || destination.isEmpty()){
                    return;
                }
                String[] dest = destination.split("\\.");
                String[] sour = source.split("\\.");
                int destID = Integer.parseInt(dest[0]);
                int sourID = Integer.parseInt(sour[0]);

                API.addCargo(new VolleyCallBack() {
                    @Override
                    public void onSuccess() {
                        Toast.makeText(CargoAddActivity.this, "Cargo added", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onFail() {
                        Toast.makeText(CargoAddActivity.this, "Check Info, We cannot add Your Cargo", Toast.LENGTH_SHORT).show();
                    }
                },receiver,cargoType,weight,volume,sourID,destID,"readyforDTS");



            }
        });




    }

    public void init(){
        receiver_txtInput = findViewById(R.id.receiver_txtinput);
        volume_txtInput = findViewById(R.id.capacityOfCargo_txtinput);
        weight_txtInput = findViewById(R.id.cargoWeight_txtinput);
        cargoType_spinner = findViewById(R.id.cargoType);
        destination_spinner = findViewById(R.id.destination_spinner);
        addCargo_btn = findViewById(R.id.addCargo_btn);
        source_spinner = findViewById(R.id.source_spinner);
    }
}
