package eu.artviz.oilcheckr.data;

import eu.artviz.oilcheckr.data.interfaces.IDao;
import eu.artviz.oilcheckr.data.mockers.HistoryDaoMocker;
import eu.artviz.oilcheckr.data.mockers.OilDaoMocker;
import eu.artviz.oilcheckr.data.mockers.VehicleDaoMocker;
import eu.artviz.oilcheckr.models.History;
import eu.artviz.oilcheckr.models.Oil;
import eu.artviz.oilcheckr.models.Vehicle;

public class DataManager {
    private static DataManager instance;
    private IDao<Vehicle> vehicleDao;
    private IDao<Oil> oilDao;
    private IDao<History> historyDao;

    public static DataManager getInstance(){
        if (instance == null){
            instance = new DataManager();
        }

        return instance;
    }

    public static void init(IDao<Vehicle> vehicleDao, IDao<Oil> oilDao, IDao<History> historyDao){
        instance = new DataManager(vehicleDao,oilDao,historyDao);
    }

    private DataManager() {
        vehicleDao = new VehicleDaoMocker();
        oilDao = new OilDaoMocker();
        historyDao = new HistoryDaoMocker();
    }

    private DataManager(IDao<Vehicle> vehicleDao, IDao<Oil> oilDao, IDao<History> historyDao) {
        this.vehicleDao = vehicleDao;
        this.oilDao = oilDao;
        this.historyDao = historyDao;
    }

    public IDao<Vehicle> vehicles() {
        return vehicleDao;
    }

    public IDao<Oil> oils() {
        return oilDao;
    }

    public IDao<History> histories() {
        return historyDao;
    }

    public void releaseDb() {
        this.vehicleDao.releaseDb();
        this.oilDao.releaseDb();
        this.historyDao.releaseDb();
    }
}
