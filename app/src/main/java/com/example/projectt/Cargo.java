package com.example.projectt;

import java.util.ArrayList;

public class Cargo {
    /* "ID": 1,
            "Type": "yemek",
            "Kg": 2.0,
            "Volume": 2.0,
            "Price": 31.0
            to class
            */

    private int ID;
    private String Type;
    private double Weight;
    private double Volume;
    private double Value;
    public static ArrayList<Cargo> allCargos = new ArrayList<>();

    public Cargo(int ID, String Type, double Weight, double Volume, double Value) {
        this.ID = ID;
        this.Type = Type;
        this.Weight = Weight;
        this.Volume = Volume;
        this.Value = Value;
        allCargos.add(this);
    }

    public int getID() {
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
}
