package eu.artviz.oilcheckr.preferences;

import com.j256.ormlite.android.apptools.OrmLiteConfigUtil;

import java.io.IOException;
import java.sql.SQLException;

import eu.artviz.oilcheckr.models.History;
import eu.artviz.oilcheckr.models.Oil;
import eu.artviz.oilcheckr.models.Vehicle;



public class DatabaseConfigUtil extends OrmLiteConfigUtil {
    static Class[] classes = new Class[]{Oil.class, Vehicle.class, History.class};

    public static void main(String[] args) throws IOException, SQLException {

        writeConfigFile("ormlite_config.txt",classes);
    }
}

