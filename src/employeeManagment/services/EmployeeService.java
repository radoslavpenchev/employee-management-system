package employeeManagment.services;

import employeeManagment.additional.Constants;
import employeeManagment.core.Controller;
import employeeManagment.employee.Employee;
import employeeManagment.exceptions.DuplicateEntryError;
import employeeManagment.exceptions.MissingEntryError;
import employeeManagment.repositories.EmployeeRepository;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class EmployeeService implements Controller {
  private EmployeeRepository employeeRepository;

  public EmployeeService() {
    this.employeeRepository = new EmployeeRepository();
  }

  @Override
  public String create(String id, String name, int age, double salary) throws DuplicateEntryError {
    Employee employee = new Employee(id, name, age, salary);
    this.employeeRepository.add(id, employee);
    return String.format(Constants.EMPLOYEE_CREATED_MASSAGE, id);
  }

  @Override
  public String get(String id) throws MissingEntryError {
    Employee employee = employeeRepository.get(id);
    return employee.toString();
  }

  @Override
  public String delete(String id) throws MissingEntryError {
    if (!employeeRepository.remove(id)) {
      throw new MissingEntryError(String.format(Constants.EMPLOYEE_NOT_FOUND_MASSAGE, id));
    }
    return String.format(Constants.EMPLOYEE_DELETED_MASSAGE, id);
  }

  @Override
  public String update(String id, String name, int age, double salary) {
    return this.employeeRepository.update(id, name, age, salary);
  }

  @Override
  public String getEmployees() {
    StringBuilder sb = new StringBuilder();
    this.employeeRepository.getCollection().forEach((key, value) -> sb.append(value.toString()));
    return sb.toString();
  }

  @Override
  public String load(String fileName) throws IOException, DuplicateEntryError {
    BufferedReader reader = new BufferedReader(new FileReader(fileName));
    List<Employee> employeesToAdd = processEmployees(reader);
    parseEmployees(employeesToAdd);
    reader.close();
    return String.format(Constants.EMPLOYEES_LOADED_MASSAGE, fileName);
  }

  private void parseEmployees(List<Employee> employeesToAdd) throws DuplicateEntryError {
    for (Employee employee : employeesToAdd) {
      this.employeeRepository.add(employee.getId(), employee);
    }
  }

  private List<Employee> processEmployees(BufferedReader reader) throws IOException {
    List<Employee> result = new ArrayList<>();
    String[] header = reader.readLine().split(",");
    String line = "";
    while ((line = reader.readLine()) != null) {
      String[] employeeArguments = line.split(",");
      Employee employeeToAdd =
          new Employee(
              employeeArguments[0],
              employeeArguments[1],
              Integer.parseInt(employeeArguments[2]),
              Double.parseDouble(employeeArguments[3]));
      result.add(employeeToAdd);
    }
    reader.close();
    return result;
  }

  @Override
  public String save(String fileName) throws IOException, MissingEntryError {
    File csvFile = new File(fileName);
    PrintWriter out = new PrintWriter(csvFile);
    List<Employee> employeesOutputData = getData();
    writeHeader(out);
    for (Employee employee : employeesOutputData) {
      writeLine(out, employee);
    }
    out.close();
    return String.format(Constants.EMPLOYEES_SAVED_MASSAGE, csvFile);
  }

  private List<Employee> getData() throws MissingEntryError {
    List<Employee> employeesData = new ArrayList<>();
    for (String id : this.employeeRepository.getCollection().keySet()) {
      employeesData.add(this.employeeRepository.get(id));
    }
    return employeesData;
  }

  private void writeHeader(PrintWriter writer) throws IOException {
    writer.println("ID,Name,Age,Salary");
  }

  private void writeLine(PrintWriter writer, Employee employee) throws IOException {
    StringBuilder sb = new StringBuilder();
    sb.append(employee.getId())
        .append(",")
        .append(employee.getName())
        .append(",")
        .append(employee.getId())
        .append(",")
        .append(employee.getSalary());
    writer.println(sb);
  }
}
