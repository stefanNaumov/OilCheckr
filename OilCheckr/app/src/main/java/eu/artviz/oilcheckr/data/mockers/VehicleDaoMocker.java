package eu.artviz.oilcheckr.data.mockers;

import java.util.ArrayList;
import java.util.List;

import eu.artviz.oilcheckr.data.interfaces.IVehicleDao;
import eu.artviz.oilcheckr.models.MileageUnit;
import eu.artviz.oilcheckr.models.Oil;
import eu.artviz.oilcheckr.models.Vehicle;


public class VehicleDaoMocker implements IVehicleDao{
    private List<Vehicle> vehicles;

    public VehicleDaoMocker(){
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
    public void remove(Vehicle vehicle) {
        vehicles.remove(vehicle);
    }

    @Override
    public void removeList(List<Vehicle> vehicleList) {
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

    private List<Vehicle> generateData(int size){
        List<Vehicle> vehiclesList = new ArrayList<Vehicle>();
        String vehicleName = "Vehicle";
        int mileage = 10000;
        Oil oil = new Oil();
        Vehicle vehicle;

        for (int i = 1; i < size; i++) {
            vehicle = new Vehicle(vehicleName + String.valueOf(i),mileage * i,oil, MileageUnit.Kilometres,100/i);
            vehiclesList.add(vehicle);
        }

        return vehiclesList;
    }
}
