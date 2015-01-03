package eu.artviz.oilcheckr.data.interfaces;

import android.content.Context;

import java.util.List;
import java.util.Map;

public interface IDao<T> {
    public T getById(int id);
    public List<T> getAll();
    public void create(T object);
    public void delete(T object);
    public void deleteList(List<T>list);
    public void update(T object);
    public List<T> search(Map<String, Object> fieldValues);
    public void releaseDb();
    void setup(Context context);
}
