package employeeManagment.repositories;

import employeeManagment.exceptions.DuplicateEntryError;
import employeeManagment.exceptions.MissingEntryError;

import java.util.Map;

public interface Repository<T> {
  Map<String, T> getCollection();

  void add(String id, T entity) throws DuplicateEntryError, DuplicateEntryError;

  T get(String id) throws MissingEntryError;

  boolean remove(String id);
}
