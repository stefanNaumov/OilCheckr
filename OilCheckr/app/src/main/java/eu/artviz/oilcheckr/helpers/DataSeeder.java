package eu.artviz.oilcheckr.helpers;


import android.content.Context;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import eu.artviz.oilcheckr.data.DataManager;
import eu.artviz.oilcheckr.models.History;
import eu.artviz.oilcheckr.models.MileageUnit;
import eu.artviz.oilcheckr.models.Oil;
import eu.artviz.oilcheckr.models.Vehicle;

public class DataSeeder {

    private static DataManager mDataManager;

    private static List<Vehicle> vehicles;
    private static List<Oil> oils;
    private static List<History> histories;

    public DataSeeder(Context context){
        mDataManager = DataManager.getInstance(context);

        vehicles = new ArrayList<Vehicle>();
        oils = new ArrayList<Oil>();
        histories = new ArrayList<History>();
    }

    public static void seed(){
        //first
        Vehicle vehicle1 = new Vehicle("TestVehicle1", 5);
        vehicle1.setCurrentMileage(100000);
        vehicle1.setMileageUnit(MileageUnit.KM);

        Oil oil1 = new Oil("TestOil1",20000);

        saveRelations(vehicle1,oil1);

        vehicles.add(vehicle1);
        oils.add(oil1);

        History history1 = new History(vehicle1,oil1,new Date());
        history1.setMileageChanged(vehicle1.getCurrentMileage());

        histories.add(history1);

        //second
        Vehicle vehicle2 = new Vehicle("TestVehicle2", 6);
        vehicle2.setCurrentMileage(110000);
        vehicle2.setMileageUnit(MileageUnit.MI);

        Oil oil2 = new Oil("TestOil2",21000);

        saveRelations(vehicle2,oil2);

        vehicles.add(vehicle2);
        oils.add(oil2);

        History history2 = new History(vehicle2,oil2,new Date());
        history2.setMileageChanged(vehicle2.getCurrentMileage());

        histories.add(history2);

        //third
        Vehicle vehicle3 = new Vehicle("TestVehicle3", 7);
        vehicle3.setCurrentMileage(120000);
        vehicle3.setMileageUnit(MileageUnit.KM);

        Oil oil3 = new Oil("TestOil3",22000);

        saveRelations(vehicle3,oil3);

        vehicles.add(vehicle3);
        oils.add(oil3);

        History history3 = new History(vehicle3,oil3,new Date());
        history3.setMileageChanged(vehicle3.getCurrentMileage());

        histories.add(history3);

        //fourth
        Vehicle vehicle4 = new Vehicle("TestVehicle4", 8);
        vehicle4.setCurrentMileage(130000);
        vehicle4.setMileageUnit(MileageUnit.KM);

        Oil oil4 = new Oil("TestOil4",23000);

        saveRelations(vehicle4,oil4);

        vehicles.add(vehicle4);
        oils.add(oil4);

        History history4 = new History(vehicle4,oil4,new Date());
        history4.setMileageChanged(vehicle4.getCurrentMileage());

        histories.add(history4);

        //fifth
        Vehicle vehicle5 = new Vehicle("TestVehicle5", 9);
        vehicle5.setCurrentMileage(140000);
        vehicle5.setMileageUnit(MileageUnit.KM);

        Oil oil5 = new Oil("TestOil5",24000);

        saveRelations(vehicle5,oil5);

        vehicles.add(vehicle5);
        oils.add(oil5);

        History history5 = new History(vehicle5,oil5,new Date());
        history5.setMileageChanged(vehicle5.getCurrentMileage());

        histories.add(history5);

        for (int i = 0; i < vehicles.size(); i++){
            saveVehicle(vehicles.get(i));
            saveOil(oils.get(i));
            saveHistory(histories.get(i));
        }
    }

    private static void saveRelations(Vehicle vehicle, Oil oil){
        vehicle.setOil(oil);
    }

    private static void saveVehicle(Vehicle vehicle){
        mDataManager.vehicles().create(vehicle);
    }

    private static void saveOil(Oil oil){
        mDataManager.oils().create(oil);
    }

    private static void saveHistory(History history){
        mDataManager.histories().create(history);
    }
}
