package eu.artviz.oilcheckr.models;

import android.os.Parcel;
import android.os.Parcelable;

import com.j256.ormlite.field.DatabaseField;

public class Vehicle implements Parcelable {
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
    @DatabaseField
    private int oilCapacity;

    public Vehicle(){

    }

    public Vehicle(String name, int oilCapacity) {
        this.name = name;
        this.oilCapacity = oilCapacity;
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

    public void setMileageUnit(MileageUnit mileageUnit) {
        this.mileageUnit = mileageUnit;
    }

    public int getAverageDayMileage() {
        return averageDayMileage;
    }

    public void setAverageDayMileage(int averageDayMileage) {
        this.averageDayMileage = averageDayMileage;
    }

    public int getOilCapacity() {
        return oilCapacity;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.id);
        dest.writeString(this.name);
        dest.writeInt(this.currentMileage);
        dest.writeParcelable(this.oil, flags);
        dest.writeInt(this.mileageUnit == null ? -1 : this.mileageUnit.ordinal());
        dest.writeInt(this.averageDayMileage);
    }

    private Vehicle(Parcel in) {
        this.id = in.readInt();
        this.name = in.readString();
        this.currentMileage = in.readInt();
        this.oil = in.readParcelable(Oil.class.getClassLoader());
        int tmpMileageUnit = in.readInt();
        this.mileageUnit = tmpMileageUnit == -1 ? null : MileageUnit.values()[tmpMileageUnit];
        this.averageDayMileage = in.readInt();
    }

    public static final Parcelable.Creator<Vehicle> CREATOR = new Parcelable.Creator<Vehicle>() {
        public Vehicle createFromParcel(Parcel source) {
            return new Vehicle(source);
        }

        public Vehicle[] newArray(int size) {
            return new Vehicle[size];
        }
    };
}
