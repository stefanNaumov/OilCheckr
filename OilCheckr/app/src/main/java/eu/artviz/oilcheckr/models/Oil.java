package eu.artviz.oilcheckr.models;

import android.os.Parcel;
import android.os.Parcelable;

import com.j256.ormlite.field.DatabaseField;

public class Oil implements Parcelable {
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

    public Oil(String name, int range){
        this.name = name;
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

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.id);
        dest.writeString(this.name);
        dest.writeParcelable(this.vehicle, 0);
        dest.writeInt(this.range);
    }

    private Oil(Parcel in) {
        this.id = in.readInt();
        this.name = in.readString();
        this.vehicle = in.readParcelable(Vehicle.class.getClassLoader());
        this.range = in.readInt();
    }

    public static final Parcelable.Creator<Oil> CREATOR = new Parcelable.Creator<Oil>() {
        public Oil createFromParcel(Parcel source) {
            return new Oil(source);
        }

        public Oil[] newArray(int size) {
            return new Oil[size];
        }
    };
}
