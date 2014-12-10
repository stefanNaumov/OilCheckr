package eu.artviz.oilcheckr.models;

import com.j256.ormlite.field.DatabaseField;

public class Vehicle {
    @DatabaseField(generatedId = true)
    private int id;
    @DatabaseField
    private String name;
    @DatabaseField
    private int currentMileage;
    @DatabaseField(foreign = true,foreignAutoRefresh = true)
    private Oil oil;
    @DatabaseField
    private MileageUnit mileageUnit;
    @DatabaseField
    private int averageDayMileage;

    public Vehicle(){

    }

    public Vehicle(String name, int currentMileage, Oil oil,MileageUnit mileageUnit, int averageDayMileage) {
        this.name = name;
        this.currentMileage = currentMileage;
        this.oil = oil;
        this.mileageUnit = mileageUnit;
        this.averageDayMileage = averageDayMileage;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getCurrentMileage() {
        return currentMileage;
    }

    public void setCurrentMileage(int currentMileage) {
        this.currentMileage = currentMileage;
    }

    public Oil getOil() {
        return oil;
    }

    public void setOil(Oil oil) {
        this.oil = oil;
    }

    public MileageUnit getMileageUnit() {
        return mileageUnit;
    }

    public int getAverageDayMileage() {
        return averageDayMileage;
    }

    public void setAverageDayMileage(int averageDayMileage) {
        this.averageDayMileage = averageDayMileage;
    }
}
