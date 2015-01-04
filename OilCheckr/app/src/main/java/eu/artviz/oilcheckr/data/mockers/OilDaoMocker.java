package eu.artviz.oilcheckr.data.mockers;

import android.content.Context;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import eu.artviz.oilcheckr.data.interfaces.IDao;
import eu.artviz.oilcheckr.models.Oil;
import eu.artviz.oilcheckr.models.Vehicle;


public class OilDaoMocker implements IDao<Oil> {
    private List<Oil> oils;

    public OilDaoMocker(Context context){
        this.oils = generateData(20);
    }
    @Override
    public Oil getById(int id) {
        Oil oil = generateData(1).get(0);
        return oil;
    }

    @Override
    public List<Oil> getAll() {
        return oils;
    }

    @Override
    public void create(Oil oil) {
        oils.add(oil);
    }

    @Override
    public void delete(Oil oil) {
        oils.remove(oil);
    }

    @Override
    public void deleteList(List<Oil> oilList) {
        oils.removeAll(oilList);
    }

    @Override
    public void update(Oil oil) {
       for (Oil oilObj : oils){
           if (oilObj.getName() == oil.getName()){
               oils.set(oils.indexOf(oilObj),oil);
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
    public List<Oil> search(Map<String, Object> fieldValues) {
        return generateData(1);
    }

    private List<Oil> generateData(int size){
        List<Oil> oilsList = new ArrayList<Oil>();
        String oilName = "Oil";
        String vehicleName = "VehicleName";
        int range = 50000;
        Vehicle vehicle = new Vehicle();
        Oil oil;

        for (int i = 1; i <= size; i++) {
            vehicle.setName(vehicleName + String.valueOf(i));
            oil = new Oil(oilName + String.valueOf(i), range);
            oilsList.add(oil);
        }

        return oilsList;
    }
}
