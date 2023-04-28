package employeeManagment.repositories;

import employeeManagment.additional.Constants;
import employeeManagment.employee.Employee;
import employeeManagment.exceptions.DuplicateEntryError;
import employeeManagment.exceptions.MissingEntryError;

import java.util.LinkedHashMap;
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
      throw new DuplicateEntryError(String.format(Constants.EMPLOYEE_ALREADY_CREATED_MASSAGE,id));
    }
    this.employees.put(id, employee);
  }

  @Override
  public Employee get(String id) throws MissingEntryError {
    if (!this.employees.containsKey(id)) {
      throw new MissingEntryError(String.format(Constants.EMPLOYEE_NOT_FOUND_MASSAGE,id));
    }
    return this.employees.get(id);
  }

  @Override
  public boolean remove(String id) {
    for (String employeeId : this.employees.keySet()) {
      if (employeeId.equals(id)) {
        this.employees.remove(employeeId);
        return true;
      }
    }
    return false;
  }
}
