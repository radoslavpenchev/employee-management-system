package employeeManagment.repositories;

import employeeManagment.additional.Constants;
import employeeManagment.employee.Employee;
import employeeManagment.exceptions.DuplicateEntryError;
import employeeManagment.exceptions.MissingEntryError;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class EmployeeRepository implements Repository<Employee> {
  private Map<String, Employee> employees;

  public EmployeeRepository() {
    this.employees = new LinkedHashMap<>();
  }

  @Override
  public Map<String, Employee> getCollection() {
    return this.employees;
  }

  @Override
  public void add(String id, Employee employee) throws DuplicateEntryError {
    if (this.employees.containsKey(id)) {
      throw new DuplicateEntryError(String.format(Constants.EMPLOYEE_ALREADY_CREATED_MASSAGE, id));
    }
    this.employees.put(id, employee);
  }

  @Override
  public Employee get(String id) throws MissingEntryError {
    if (!this.employees.containsKey(id)) {
      throw new MissingEntryError(String.format(Constants.EMPLOYEE_NOT_FOUND_MASSAGE, id));
    }
    return this.employees.get(id);
  }

  @Override
  public String remove(String id) throws MissingEntryError {
    for (String employeeId : this.employees.keySet()) {
      if (employeeId.equals(id)) {
        this.employees.remove(employeeId);
        return String.format(Constants.EMPLOYEE_DELETED_MASSAGE, id);
      }
    }
    throw new MissingEntryError(String.format(Constants.EMPLOYEE_NOT_FOUND_MASSAGE, id));
  }

  public String update(String id, String name, int age, double salary) {
    for (String employeeId : this.employees.keySet()) {
      if (employeeId.equals(id)) {
        this.employees.get(id).update(name, age, salary);
        return String.format(Constants.EMPLOYEE_UPDATED_MASSAGE, id);
      }
    }
    return String.format(Constants.EMPLOYEE_NOT_FOUND_MASSAGE, id);
  }

  public List<Employee> getAll(){
    List<Employee> employeesList= new ArrayList<>(this.employees.values());
    return employeesList;
  }
}
