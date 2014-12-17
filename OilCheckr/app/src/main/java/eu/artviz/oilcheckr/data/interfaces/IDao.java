package eu.artviz.oilcheckr.data.interfaces;

import java.util.List;


public interface IDao<T> {
    public T getById(int id);
    public List<T> getAll();
    public void create(T object);
    public void delete(T object);
    public void deleteList(List<T>list);
    public void update(T object);
    public void releaseDb();
}
