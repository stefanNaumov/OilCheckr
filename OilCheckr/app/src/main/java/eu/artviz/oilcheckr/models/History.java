package eu.artviz.oilcheckr.models;


import java.util.Date;


public class History {
    private int id;

    private Vehicle vehicle;

    private Oil oil;

    private Date dateChanged;

    private int mileageChanged;

    public History(){

    }

    public History(Vehicle vehicle, Oil oil, Date dateChanged, int mileageChanged){
        super();
        this.vehicle = vehicle;
        this.oil = oil;
        this.dateChanged = dateChanged;
        this.mileageChanged = mileageChanged;
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

    public Oil getOil() {
        return oil;
    }

    public void setOil(Oil oil) {
        this.oil = oil;
    }

    public Date getDateChanged() {
        return dateChanged;
    }

    public void setDateChanged(Date dateChanged) {
        this.dateChanged = dateChanged;
    }

    public int getMileageChanged() {
        return mileageChanged;
    }

    public void setMileageChanged(int mileageChanged) {
        this.mileageChanged = mileageChanged;
    }
}
