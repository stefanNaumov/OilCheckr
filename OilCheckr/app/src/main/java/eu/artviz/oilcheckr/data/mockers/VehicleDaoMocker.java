package eu.artviz.oilcheckr.data.mockers;

import android.content.Context;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import eu.artviz.oilcheckr.data.interfaces.IDao;
import eu.artviz.oilcheckr.models.MileageUnit;
import eu.artviz.oilcheckr.models.Oil;
import eu.artviz.oilcheckr.models.Vehicle;


public class VehicleDaoMocker implements IDao<Vehicle>{
    private List<Vehicle> vehicles;

    public VehicleDaoMocker(Context context){
        this.vehicles = generateData(20);
    }

    @Override
    public Vehicle getById(int id) {
        Vehicle vehicle = generateData(1).get(0);
        return vehicle;
    }

    @Override
    public List<Vehicle> getAll() {
        return vehicles;
    }

    @Override
    public void create(Vehicle vehicle) {
        vehicles.add(vehicle);
    }

    @Override
    public void delete(Vehicle vehicle) {
        vehicles.remove(vehicle);
    }

    @Override
    public void deleteList(List<Vehicle> vehicleList) {
        vehicles.removeAll(vehicleList);
    }

    @Override
    public void update(Vehicle vehicle) {
        for (Vehicle vehicleObj : vehicles){
            if (vehicleObj.getName() == vehicle.getName()){
                vehicles.set(vehicles.indexOf(vehicleObj),vehicle);
                break;
            }
        }
    }

    @Override
    public void releaseDb() {

    }

    @Override
    public void setup(Context context) {

    }

    @Override
    public List<Vehicle> search(Map<String, Object> fieldValues) {
        return generateData(1);
    }

    private List<Vehicle> generateData(int size){
        List<Vehicle> vehiclesList = new ArrayList<Vehicle>();
        String vehicleName = "Vehicle";
        String oilName = "OilName";
        int mileage = 10000;
        int averageDayMileage = 20;
        Oil oil = new Oil();
        Vehicle vehicle;

        for (int i =1; i <= size; i++) {
            oil.setName(oilName + String.valueOf(i));
            vehicle = new Vehicle(vehicleName + String.valueOf(i),i * 2);
            vehicle.setMileageUnit(MileageUnit.KM);
            vehicle.setCurrentMileage(mileage * i);
            vehicle.setAverageDayMileage(averageDayMileage * i);
            vehicle.setOil(oil);

            vehiclesList.add(vehicle);
        }

        return vehiclesList;
    }
}
