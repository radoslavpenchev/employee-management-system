package employeeManagment.core;

import employeeManagment.exceptions.DuplicateEntryError;
import employeeManagment.exceptions.MissingEntryError;
import employeeManagment.services.EmployeeService;
import java.io.*;

public class ControllerImpl implements Controller {
  private final EmployeeService employeeService;

  public ControllerImpl() {
    this.employeeService = new EmployeeService();
  }

  @Override
  public String create(String id, String name, int age, double salary) throws DuplicateEntryError {
    return employeeService.create(id, name, age, salary);
  }

  @Override
  public String get(String id) throws MissingEntryError {
    return employeeService.get(id);
  }

  @Override
  public String delete(String id) throws MissingEntryError {
    return employeeService.delete(id);
  }

  @Override
  public String update(String id, String name, int age, double salary) throws MissingEntryError {
    return employeeService.update(id, name, age, salary);
  }

  @Override
  public String getEmployees() {
    return employeeService.getEmployees();
  }

  @Override
  public String load(String fileName) throws IOException, DuplicateEntryError {
    return employeeService.load(fileName);
  }

  @Override
  public String save(String fileName) throws IOException {
    return employeeService.save(fileName);
  }
}
