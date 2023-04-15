package employeeManagment.repositories;

import employeeManagment.employee.Employee;

import java.util.LinkedHashMap;
import java.util.Map;

public class EmployeeRepository implements Repository<Employee> {
    private Map<String, Employee> employees;

    public EmployeeRepository(){
        this.employees=new LinkedHashMap<>();
    }


    @Override
    public Map<String, Employee> getCollection() {
        return this.employees;
    }

    @Override
    public void add(String id, Employee employee) {
        if (this.employees.containsKey(id)){
            throw new IllegalStateException("There is an employee with the same ID in the system!");
        }

        this.employees.put(id,employee);
    }


    @Override
    public Employee get(String id) {
        if (!this.employees.containsKey(id)){
            throw new IllegalStateException("There is no employee matching the ID in the system!");
        }
        return this.employees.get(id);
    }

    @Override
    public boolean remove(String id) {
        for (String employeeId : this.employees.keySet()) {
            if (employeeId.equals(id)){
                this.employees.remove(employeeId);
                return true;
            }
        }
        return false;
    }


}
