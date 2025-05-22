package Model;

import java.util.List;

public interface GenericInterfaceDAO<T, ID>{
    public void insert(T obj);
    public void update(T obj);
    public void delete(ID id);
    public List<T> getAll();
}
