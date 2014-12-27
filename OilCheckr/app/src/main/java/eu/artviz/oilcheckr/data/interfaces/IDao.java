package eu.artviz.oilcheckr.data.interfaces;

import java.util.List;
import java.util.Map;


public interface IDao<T> {
    public T getById(int id);
    public List<T> getAll();
    public void create(T object);
    public void delete(T object);
    public void deleteList(List<T>list);
    public void update(T object);
    public void releaseDb();
    public List<T> search(Map<String, Object> fieldValues);
}
