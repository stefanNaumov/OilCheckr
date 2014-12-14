package eu.artviz.oilcheckr.data.dao;


import android.content.Context;

import com.j256.ormlite.android.apptools.OpenHelperManager;
import com.j256.ormlite.dao.RuntimeExceptionDao;

import java.util.List;

import eu.artviz.oilcheckr.data.DataBaseHelper;
import eu.artviz.oilcheckr.data.interfaces.IVehicleDao;
import eu.artviz.oilcheckr.models.Vehicle;

public class VehicleDao implements IVehicleDao {

    private DataBaseHelper dbHelper;
    private RuntimeExceptionDao<Vehicle, Integer> vehicleDao;

    public VehicleDao(Context context){
        this.dbHelper = OpenHelperManager.getHelper(context, DataBaseHelper.class);
        this.vehicleDao = this.dbHelper.getVehicleRuntimeDao();
    }
    @Override
    public Vehicle getById(int id) {
        return vehicleDao.queryForId(id);
    }

    @Override
    public List<Vehicle> getAll() {
        return vehicleDao.queryForAll();
    }

    @Override
    public void create(Vehicle vehicle) {
        vehicleDao.create(vehicle);
    }

    @Override
    public void delete(Vehicle vehicle) {
        vehicleDao.delete(vehicle);
    }

    @Override
    public void deleteList(List<Vehicle> vehicleList) {
        vehicleDao.delete(vehicleList);
    }

    @Override
    public void update(Vehicle vehicle) {
        vehicleDao.update(vehicle);
    }

    @Override
    public void releaseDb() {
        OpenHelperManager.releaseHelper();
    }
}