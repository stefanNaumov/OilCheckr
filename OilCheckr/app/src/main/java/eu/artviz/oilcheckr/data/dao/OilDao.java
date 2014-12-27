package eu.artviz.oilcheckr.data.dao;

import android.content.Context;

import com.j256.ormlite.android.apptools.OpenHelperManager;
import com.j256.ormlite.dao.RuntimeExceptionDao;

import java.util.List;
import java.util.Map;

import eu.artviz.oilcheckr.data.DataBaseHelper;
import eu.artviz.oilcheckr.data.interfaces.IDao;
import eu.artviz.oilcheckr.models.Oil;

public class OilDao implements IDao<Oil> {

    private DataBaseHelper dbHelper;
    private RuntimeExceptionDao<Oil,Integer> oilDao;

    public OilDao(Context context){
        this.dbHelper = OpenHelperManager.getHelper(context, DataBaseHelper.class);
        this.oilDao = dbHelper.getOilRuntimeDao();
    }

    @Override
    public Oil getById(int id) {
        return oilDao.queryForId(id);
    }

    @Override
    public List<Oil> getAll() {
        return oilDao.queryForAll();
    }

    @Override
    public void create(Oil oil) {
        oilDao.create(oil);
    }

    @Override
    public void delete(Oil oil) {
        oilDao.delete(oil);
    }

    @Override
    public void deleteList(List<Oil> oilList) {
        oilDao.delete(oilList);
    }

    @Override
    public void update(Oil oil) {
        oilDao.update(oil);
    }

    @Override
    public void releaseDb() {
        OpenHelperManager.releaseHelper();
    }

    @Override
    public List<Oil> search(Map<String, Object> fieldValues) {
        return oilDao.queryForFieldValues(fieldValues);
    }
}
