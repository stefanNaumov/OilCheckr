package eu.artviz.oilcheckr.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

import eu.artviz.oilcheckr.R;
import eu.artviz.oilcheckr.models.Vehicle;

public class VehicleAdapter extends BaseAdapter {

    private Context mContext;
    private List<Vehicle> mVehicles;

    public VehicleAdapter(Context context, List<Vehicle> vehicles) {
        mContext = context;
        mVehicles = vehicles;
    }

    @Override
    public int getCount() {
        return mVehicles.size();
    }

    @Override
    public Vehicle getItem(int position) {
        return mVehicles.get(position);
    }

    @Override
    public long getItemId(int position) {
        return mVehicles.get(position).getId();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View row = convertView;
        VehicleHolder holder;

        if (row == null) {
            row = LayoutInflater.from(mContext).inflate(R.layout.vehicle_list_row, parent, false);

            holder = new VehicleHolder();
            holder.name = (TextView) row.findViewById(R.id.tvVehicleName);
            holder.mileage = (TextView) row.findViewById(R.id.tvMileage);

            row.setTag(holder);
        }
        else {
            holder = (VehicleHolder) row.getTag();
        }

        Vehicle vehicle = this.mVehicles.get(position);

        if (vehicle != null) {
            holder.name.setText(vehicle.getName());
            holder.mileage.setText(String.valueOf(vehicle.getCurrentMileage()));
        }

        return row;
    }

    static class VehicleHolder {
        private TextView name;
        private TextView mileage;
    }
}
