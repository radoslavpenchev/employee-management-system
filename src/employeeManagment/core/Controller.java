package employeeManagment.core;

public interface Controller {
String create(String id, String name, int age, double salary);

String get(String id);

String delete(String id);

String update(String id, String name, int age, double salary);

String getEmployees();

String load(String fileName);

String save(String fileName);
}
