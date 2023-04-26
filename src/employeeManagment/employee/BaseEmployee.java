package employeeManagment.employee;

public abstract class BaseEmployee implements EmployeeInterface {

  private String id;
  private String name;
  private int age;
  private double salary;

  public BaseEmployee(String id, String name, int age, double salary) {
    setId(id);
    setName(name);
    setAge(age);
    setSalary(salary);
  }

  @Override
  public String getName() {
    return this.name;
  }

  private void setName(String name) {
    if (name == null || name.trim().isEmpty()) {
      throw new IllegalArgumentException("Name cannot be null or empty!");
    }
    this.name = name;
  }

  @Override
  public int getAge() {
    return this.age;
  }

  private void setAge(int age) {
    if (age <= 0) {
      throw new IllegalArgumentException("Age cannot be negative or zero!");
    }
    this.age = age;
  }

  @Override
  public double getSalary() {
    return this.salary;
  }

  private void setSalary(double salary) {
    if (salary <= 0) {
      throw new IllegalArgumentException("Salary cannot be negative or zero!");
    }
    this.salary = salary;
  }

  @Override
  public String getId() {
    return this.id;
  }

  private void setId(String id) {
    if (Integer.parseInt(id) < 0) {
      throw new IllegalArgumentException("ID cannot be negative!");
    }
    this.id = id;
  }

  @Override
  public String toString() {
    return String.format(
        "Employee ID: %s, Name: %s, Age: %d, Salary: %.2f\n",
        getId(), getName(), getAge(), getSalary());
  }

  @Override
  public void update(String name, int age, double salary) {
    setName(name);
    setAge(age);
    setSalary(salary);
  }
}
