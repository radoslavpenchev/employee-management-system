package employeeManagment.repositories;

import java.util.Map;

public interface Repository<T> {
Map<String, T> getCollection();
    void add(String id,T entity);

    T get(String id);

    boolean remove (String id);

}
