package eu.artviz.oilcheckr.data.interfaces;

import java.util.List;


import eu.artviz.oilcheckr.models.Vehicle;

public interface IVehicleDao {
    public Vehicle getById(int id);
    public List<Vehicle> getAll();
    public void create(Vehicle vehicle);
    public void remove(Vehicle vehicle);
    public void removeList(List<Vehicle> vehicleList);
    public void update(Vehicle vehicle);
    public void releaseDb();
}
