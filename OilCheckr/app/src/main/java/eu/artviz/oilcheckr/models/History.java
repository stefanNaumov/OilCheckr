package eu.artviz.oilcheckr.models;

import android.os.Parcel;
import android.os.Parcelable;

import com.j256.ormlite.field.DatabaseField;

import java.util.Date;

public class History implements Parcelable {
    @DatabaseField(generatedId = true)
    private int id;
    @DatabaseField(foreign = true,foreignAutoRefresh = true)
    private Vehicle vehicle;
    @DatabaseField(foreign = true, foreignAutoRefresh = true)
    private Oil oil;
    @DatabaseField
    private Date dateChanged;
    @DatabaseField
    private int mileageChanged;
    @DatabaseField
    private Date lastMileageUpdateDate;

    public History(){

    }

    public History(Vehicle vehicle, Oil oil, Date dateChanged){
        this.vehicle = vehicle;
        this.oil = oil;
        this.dateChanged = dateChanged;
        this.lastMileageUpdateDate = new Date();
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

    public Date getLastMileageUpdateDate() {
        return lastMileageUpdateDate;
    }

    public void setLastMileageUpdateDate(Date lastMileageUpdateDate) {
        this.lastMileageUpdateDate = lastMileageUpdateDate;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.id);
        dest.writeParcelable(this.vehicle, 0);
        dest.writeParcelable(this.oil, 0);
        dest.writeLong(dateChanged != null ? dateChanged.getTime() : -1);
        dest.writeInt(this.mileageChanged);
        dest.writeLong(lastMileageUpdateDate != null ? lastMileageUpdateDate.getTime() : -1);
    }

    private History(Parcel in) {
        this.id = in.readInt();
        this.vehicle = in.readParcelable(Vehicle.class.getClassLoader());
        this.oil = in.readParcelable(Oil.class.getClassLoader());
        long tmpDateChanged = in.readLong();
        this.dateChanged = tmpDateChanged == -1 ? null : new Date(tmpDateChanged);
        this.mileageChanged = in.readInt();
        long tmpLastMileageUpdateDate = in.readLong();
        this.lastMileageUpdateDate = tmpLastMileageUpdateDate == -1 ? null : new Date(tmpLastMileageUpdateDate);
    }

    public static final Parcelable.Creator<History> CREATOR = new Parcelable.Creator<History>() {
        public History createFromParcel(Parcel source) {
            return new History(source);
        }

        public History[] newArray(int size) {
            return new History[size];
        }
    };
}
