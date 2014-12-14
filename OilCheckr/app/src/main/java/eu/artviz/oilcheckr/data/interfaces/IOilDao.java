package eu.artviz.oilcheckr.data.interfaces;

import java.util.List;

import eu.artviz.oilcheckr.models.Oil;

public interface IOilDao {
    public Oil getById(int id);
    public List<Oil> getAll();
    public void create(Oil oil);
    public void remove(Oil oil);
    public void removeList(List<Oil> oilList);
    public void update(Oil oil);
    public void releaseDb();
}
