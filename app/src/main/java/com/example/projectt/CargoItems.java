package com.example.projectt;

import java.util.ArrayList;

public class CargoItems {

    private String Type,Status;
    private double Weight, Volume, Value;
    private int ID,NodeID, DestNodeID, BoxID,BoxStatus;
    public static ArrayList<CargoItems> cargoItems = new ArrayList<>();

   /*
                                *
                                  {
            "ID": 23,
            "OwnerID": 72,
            "DriverID": null,
            "ReceiverID": 2,
            "Type": "food",
            "Weight": 22.0,
            "Volume": 15.0,
            "NodeID": 3,
            "destNodeID": 5,
            "BoxID": 3,
            "BoxStatus": 1,
            "Status": "readyforDTS",
            "Value": 55.0
        }
                                */

    public CargoItems(int ID, String type, double weight, double volume, double value, int nodeID, int destNodeID, int boxID, int boxStatus, String status) {
        this.ID = ID;
        this.Type = type;
        this.Weight = weight;
        this.Volume = volume;
        this.Value = value;
        this.NodeID = nodeID;
        this.DestNodeID = destNodeID;
        this.BoxID = boxID;
        this.BoxStatus = boxStatus;
        this.Status = status;

        cargoItems.add(this);
    }

    public int getID(){
        return ID;
    }

    public String getType() {
        return Type;
    }

    public double getWeight() {
        return Weight;
    }

    public double getVolume() {
        return Volume;
    }

    public double getValue() {
        return Value;
    }

    public int getNodeID() {
        return NodeID;
    }

    public int getDestNodeID() {
        return DestNodeID;
    }


    public static ArrayList<CargoItems> getData(){
        return cargoItems;
    }
}
