package eu.artviz.oilcheckr.data;

import android.content.Context;

import eu.artviz.oilcheckr.data.interfaces.IDao;
import eu.artviz.oilcheckr.models.History;
import eu.artviz.oilcheckr.models.Oil;
import eu.artviz.oilcheckr.models.Vehicle;

public class DataManager {
    private static DataManager instance;
    private IDao<Vehicle> vehicleDao;
    private IDao<Oil> oilDao;
    private IDao<History> historyDao;

    public static DataManager getInstance(Context context) {
        if (instance == null) {
            instance = new DataManager(context);
        }
        else {
            instance.setContext(context);
        }

        return instance;
    }

    private DataManager(Context context) {
        setContext(context);
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

    private void setContext(Context context) {
        this.vehicleDao.setup(context);
        this.oilDao.setup(context);
        this.historyDao.setup(context);
    }
}
