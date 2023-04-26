package employeeManagment.core;

import employeeManagment.services.EmployeeService;
import java.io.*;

public class ControllerImpl implements Controller {
  private EmployeeService employeeService;

  public ControllerImpl() {
    this.employeeService = new EmployeeService();
  }

  @Override
  public String create(String id, String name, int age, double salary) {
    return employeeService.create(id, name, age, salary);
  }

  @Override
  public String get(String id) {
    return employeeService.get(id);
  }

  @Override
  public String delete(String id) {
    return employeeService.delete(id);
  }

  @Override
  public String update(String id, String name, int age, double salary) {
    return employeeService.update(id, name, age, salary);
  }

  @Override
  public String getEmployees() {
    return employeeService.getEmployees();
  }

  @Override
  public String load(String fileName) throws IOException {
    return employeeService.load(fileName);
  }

  @Override
  public String save(String fileName) throws IOException {
    return employeeService.save(fileName);
  }
}
