package eu.artviz.oilcheckr.data.dao;

import android.content.Context;

import com.j256.ormlite.android.apptools.OpenHelperManager;
import com.j256.ormlite.dao.RuntimeExceptionDao;

import java.util.List;
import java.util.Map;

import eu.artviz.oilcheckr.data.DataBaseHelper;
import eu.artviz.oilcheckr.data.interfaces.IDao;
import eu.artviz.oilcheckr.models.History;

public class HistoryDao implements IDao<History> {

    private DataBaseHelper dbHelper;
    private RuntimeExceptionDao<History,Integer> historyDao;

    public HistoryDao(Context context){
        setup(context);
    }

    @Override
    public History getById(int id) {
        return historyDao.queryForId(id);
    }

    @Override
    public List<History> getAll() {
        return historyDao.queryForAll();
    }

    @Override
    public void create(History history) {
        historyDao.create(history);
    }

    @Override
    public void delete(History history) {
        historyDao.delete(history);
    }

    @Override
    public void deleteList(List<History> historyList) {
        historyDao.delete(historyList);
    }

    @Override
    public void update(History history) {
        historyDao.update(history);
    }

    @Override
    public void releaseDb() {
        OpenHelperManager.releaseHelper();
    }

    @Override
    public List<History> search(Map<String, Object> fieldValues) {
        List<History> histories = historyDao.queryForFieldValues(fieldValues);

        return histories;
    }

    @Override
    public void setup(Context context) {
        this.dbHelper = OpenHelperManager.getHelper(context, DataBaseHelper.class);
        this.historyDao = dbHelper.getHistoryRuntimeDao();
    }
}
