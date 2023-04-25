package employeeManagment.core;

import employeeManagment.employee.Employee;
import employeeManagment.repositories.EmployeeRepository;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class ControllerImpl implements Controller {
  private EmployeeRepository employeeRepository;
  private static final String EMPLOYEE_CREATED_MASSAGE = "Employee with ID %s successfully created!";
  private static final String EMPLOYEE_DELETED_MASSAGE = "Employee with ID %s successfully deleted!";
  private static final String EMPLOYEE_NOT_FOUND_MASSAGE = "Employee with ID %s was not found!";
  private static final String EMPLOYEE_UPDATED_MASSAGE = "Employee with ID %s was updated successfully!";
  private static final String EMPLOYEES_SAVED_MASSAGE = "Employees data saved successfully in %s!";
  private static final String EMPLOYEES_LOADED_MASSAGE = "Employees successfully loaded from %s!";

  public ControllerImpl() {
    this.employeeRepository = new EmployeeRepository();
  }

  @Override
  public String create(String id, String name, int age, double salary) {
    Employee employee = new Employee(id, name, age, salary);
    this.employeeRepository.add(id, employee);
    return String.format(EMPLOYEE_CREATED_MASSAGE, id);
  }

  @Override
  public String get(String id) {
    Employee employee = employeeRepository.get(id);
    return employee.toString();
  }

  @Override
  public String delete(String id) {
    if (employeeRepository.remove(id)) {
      return String.format(EMPLOYEE_DELETED_MASSAGE, id);
    } else {
      throw new IllegalArgumentException(String.format(EMPLOYEE_NOT_FOUND_MASSAGE, id));
    }
  }

  @Override
  public String update(String id, String name, int age, double salary) {
    for (String employeeId : this.employeeRepository.getCollection().keySet()) {
      if (employeeId.equals(id)) {
        this.employeeRepository.getCollection().get(id).update(name, age, salary);
        return String.format(EMPLOYEE_UPDATED_MASSAGE, id);
      }
    }
    return String.format(EMPLOYEE_NOT_FOUND_MASSAGE, id);
  }

  @Override
  public String getEmployees() {
    StringBuilder sb = new StringBuilder();
    this.employeeRepository
        .getCollection()
        .forEach((key, value) -> sb.append(value.toString()).append(System.lineSeparator()));
    return sb.toString();
  }

  @Override
  public String load(String fileName) throws IOException {
    BufferedReader reader = new BufferedReader(new FileReader(fileName));
    List<Employee> employeesToAdd = processEmployees(reader);
    parseEmployees(employeesToAdd);
    reader.close();
    return String.format(EMPLOYEES_LOADED_MASSAGE,fileName);
  }

  private void parseEmployees(List<Employee> employeesToAdd) {
    for (Employee employee : employeesToAdd) {
      this.employeeRepository.add(employee.getId(), employee);
    }
  }

  private List<Employee> processEmployees(BufferedReader reader) throws IOException {
    List<Employee> result = new ArrayList<>();
    String[] header = reader.readLine().split(",");
    String line ="";
    while ((line=reader.readLine()) != null){
      String[] employeeArguments = line.split(",");
      Employee employeeToAdd = new Employee(employeeArguments[0], employeeArguments[1], Integer.parseInt(employeeArguments[2]), Double.parseDouble(employeeArguments[3]));
      result.add(employeeToAdd);
    }
    reader.close();
    return result;
  }

  @Override
  public String save(String fileName) throws IOException {
    File csvFile = new File(fileName);
    PrintWriter out = new PrintWriter(csvFile);
    List<Employee> employeesOutputData = getData();
    writeHeader(out);
    for (Employee employee : employeesOutputData) {
      writeLine(out, employee);
    }
    out.close();
    return String.format(EMPLOYEES_SAVED_MASSAGE, csvFile);
  }

  private List<Employee> getData() {
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
