package eu.artviz.oilcheckr.data.dao;

import android.content.Context;

import com.j256.ormlite.android.apptools.OpenHelperManager;
import com.j256.ormlite.dao.RuntimeExceptionDao;

import java.util.List;
import java.util.Map;

import eu.artviz.oilcheckr.data.DataBaseHelper;
import eu.artviz.oilcheckr.data.interfaces.IDao;
import eu.artviz.oilcheckr.models.Vehicle;

public class VehicleDao implements IDao<Vehicle> {

    private DataBaseHelper dbHelper;
    private RuntimeExceptionDao<Vehicle, Integer> vehicleDao;

    public VehicleDao(Context context){
        setup(context);
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

    @Override
    public List<Vehicle> search(Map<String, Object> fieldValues) {
        return vehicleDao.queryForFieldValues(fieldValues);
    }

    @Override
    public void setup(Context context) {
        this.dbHelper = OpenHelperManager.getHelper(context, DataBaseHelper.class);
        this.vehicleDao = this.dbHelper.getVehicleRuntimeDao();
    }
}