package eu.artviz.oilcheckr.data.mockers;

import java.util.ArrayList;
import java.util.List;

import eu.artviz.oilcheckr.data.interfaces.IOilDao;
import eu.artviz.oilcheckr.models.Oil;
import eu.artviz.oilcheckr.models.Vehicle;


public class OilDaoMocker implements IOilDao {
    private List<Oil> oils;

    public OilDaoMocker(){
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
    public void remove(Oil oil) {
        oils.remove(oil);
    }

    @Override
    public void removeList(List<Oil> oilList) {
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

    private List<Oil> generateData(int size){
        List<Oil> oilsList = new ArrayList<Oil>();
        String oilName = "Oil";
        int range = 50000;
        Vehicle vehicle = new Vehicle();
        Oil oil;

        for (int i = 1; i < size; i++) {
            oil = new Oil(oilName + String.valueOf(i),vehicle,range);
            oilsList.add(oil);
        }

        return oilsList;
    }
}
