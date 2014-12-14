package eu.artviz.oilcheckr.data.mockers;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import eu.artviz.oilcheckr.data.interfaces.IHistoryDao;
import eu.artviz.oilcheckr.models.History;
import eu.artviz.oilcheckr.models.Oil;
import eu.artviz.oilcheckr.models.Vehicle;

public class HistoryDaoMocker implements IHistoryDao {
    private List<History> historyList;

    public HistoryDaoMocker(){
        this.historyList = generateData(20);
    }

    @Override
    public History getById(int id) {
        History history = generateData(1).get(0);
        return history;
    }

    @Override
    public List<History> getAll() {
        return historyList;
    }

    @Override
    public void create(History history) {
        historyList.add(history);
    }

    @Override
    public void remove(History history) {

        historyList.remove(history);
    }

    @Override
    public void removeList(List<History> historyList) {
        historyList.removeAll(historyList);
    }

    @Override
    public void update(History history) {
        for (History historyObj : historyList){
            if (historyObj.getDateChanged() == history.getDateChanged()){
                historyList.set(historyList.indexOf(historyObj),history);
                break;
            }
        }
    }

    @Override
    public void releaseDb() {

    }

    private List<History> generateData(int size){
        List<History> historyList = new ArrayList<History>();
        int mileage = 150000;
        Vehicle vehicle = new Vehicle();
        Oil oil = new Oil();
        Date date = new Date();
        History history;

        for (int i = 1; i < size; i++) {
            history = new History(vehicle,oil,date,mileage);
            historyList.add(history);
        }

        return historyList;
    }
}
