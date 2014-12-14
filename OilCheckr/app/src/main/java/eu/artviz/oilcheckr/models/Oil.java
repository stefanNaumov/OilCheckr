package eu.artviz.oilcheckr.models;

import com.j256.ormlite.field.DatabaseField;

public class Oil{
    @DatabaseField(generatedId = true)
    private int id;
    @DatabaseField
    private String name;
    @DatabaseField(foreign = true,foreignAutoRefresh = true)
    private Vehicle vehicle;
    @DatabaseField
    private int range;

    public Oil(){

    }

    public Oil(String name,Vehicle vehicle, int range){
        this.name = name;
        this.vehicle = vehicle;
        this.range = range;
    }

    public int getId() {
        return id;
    }

    public Vehicle getVehicle() {
        return vehicle;
    }

    public void setVehicle(Vehicle vehicle) {
        this.vehicle = vehicle;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getRange() {
        return range;
    }

    public void setRange(int range) {
        this.range = range;
    }
}
