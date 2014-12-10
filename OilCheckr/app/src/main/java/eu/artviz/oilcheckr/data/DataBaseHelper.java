package eu.artviz.oilcheckr.data;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.j256.ormlite.android.apptools.OrmLiteSqliteOpenHelper;
import com.j256.ormlite.dao.RuntimeExceptionDao;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;

import java.sql.SQLException;

import eu.artviz.oilcheckr.R;
import eu.artviz.oilcheckr.models.History;
import eu.artviz.oilcheckr.models.Oil;
import eu.artviz.oilcheckr.models.Vehicle;


public class DataBaseHelper extends OrmLiteSqliteOpenHelper {
    private static final String DATABASE_NAME = "oilcheckr.db";
    private static final int DATABASE_VERSION = 1;

    private RuntimeExceptionDao<Oil,Integer> oilRunTimeDAO = null;
    private RuntimeExceptionDao<Vehicle,Integer> vehicleRunTimeDAO = null;
    private RuntimeExceptionDao<History,Integer> historyRunTimeDAO = null;

    public DataBaseHelper(Context context){
        super(context,DATABASE_NAME,null,DATABASE_VERSION, R.raw.ormlite_config);
    }
    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase, ConnectionSource connectionSource) {
        try {
            TableUtils.createTable(connectionSource, Oil.class);
            TableUtils.createTable(connectionSource,Vehicle.class);
            TableUtils.createTable(connectionSource,History.class);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, ConnectionSource connectionSource, int i, int i2) {
        try {
            TableUtils.dropTable(connectionSource, Oil.class, true);
            TableUtils.dropTable(connectionSource, Vehicle.class, true);
            TableUtils.dropTable(connectionSource,History.class,true);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        onCreate(sqLiteDatabase,connectionSource);
    }

    public RuntimeExceptionDao<Oil,Integer> getOilRuntimeDao() {
        if (oilRunTimeDAO == null){
            oilRunTimeDAO = getRuntimeExceptionDao(Oil.class);
        }

        return oilRunTimeDAO;
    }

    public RuntimeExceptionDao<Vehicle,Integer> getVehicleRuntimeDao() {
        if (vehicleRunTimeDAO == null){
            vehicleRunTimeDAO = getRuntimeExceptionDao(Vehicle.class);
        }

        return vehicleRunTimeDAO;
    }

    public RuntimeExceptionDao<History,Integer> getHistoryRuntimeDao() {
        if (historyRunTimeDAO == null){
            historyRunTimeDAO = getRuntimeExceptionDao(History.class);
        }

        return historyRunTimeDAO;
    }
}
