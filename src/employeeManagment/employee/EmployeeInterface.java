package employeeManagment.employee;

public interface EmployeeInterface {
  String getName();

  int getAge();

  double getSalary();

  String getId();

  void update(String name, int age, double salary);
}
