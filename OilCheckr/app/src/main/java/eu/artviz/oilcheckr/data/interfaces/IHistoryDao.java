package eu.artviz.oilcheckr.data.interfaces;

import java.util.List;

import eu.artviz.oilcheckr.models.History;

public interface IHistoryDao {
    public History getById(int id);
    public List<History> getAll();
    public void create(History history);
    public void delete(History history);
    public void deleteList(List<History> historyList);
    public void update(History history);
    public void releaseDb();
}
