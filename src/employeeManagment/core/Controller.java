package employeeManagment.core;

import employeeManagment.exceptions.DuplicateEntryError;
import employeeManagment.exceptions.MissingEntryError;

import java.io.IOException;

public interface Controller {
  String create(String id, String name, int age, double salary) throws DuplicateEntryError;

  String get(String id) throws MissingEntryError;

  String delete(String id) throws MissingEntryError;

  String update(String id, String name, int age, double salary) throws MissingEntryError;

  String getEmployees();

  String load(String fileName) throws IOException, DuplicateEntryError;

  String save(String fileName) throws IOException;
}
