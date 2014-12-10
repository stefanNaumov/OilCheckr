package eu.artviz.oilcheckr.models;

public class Oil {
    private int id;

    private String oilName;

    private Vehicle vehicle;

    private int range;

    public Oil(){

    }

    public Oil(String oilName,Vehicle vehicle, int range){
        this.oilName = oilName;
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

    public String getOilName() {
        return oilName;
    }

    public void setOilName(String oilName) {
        this.oilName = oilName;
    }

    public int getRange() {
        return range;
    }

    public void setRange(int range) {
        this.range = range;
    }
}
