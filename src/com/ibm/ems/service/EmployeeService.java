package com.ibm.ems.service;

import java.util.*;
import com.ibm.ems.model.*;

public class EmployeeService {

    private List<Employee> employees = new ArrayList<>();

    public void loadDefaultEmployees() {
        employees.add(new PermanentEmployee(1, "Raju", 50000));
        employees.add(new ContractEmployee(2, "Anil", 40000));
        employees.add(new PermanentEmployee(3, "Sita", 60000));
        employees.add(new ContractEmployee(4, "Ravi", 35000));
        employees.add(new PermanentEmployee(5, "Kiran", 70000));
        employees.add(new ContractEmployee(6, "Arjun", 30000));
        employees.add(new PermanentEmployee(7, "Meena", 80000));
        employees.add(new ContractEmployee(8, "Divya", 45000));
        employees.add(new PermanentEmployee(9, "Rahul", 90000));
        employees.add(new ContractEmployee(10, "Sneha", 38000));
        employees.add(new PermanentEmployee(11, "Vijay", 75000));
        employees.add(new ContractEmployee(12, "Pooja", 42000));
        employees.add(new PermanentEmployee(13, "Manoj", 65000));
        employees.add(new ContractEmployee(14, "Neha", 37000));
        employees.add(new PermanentEmployee(15, "Priya", 75000));
        employees.add(new ContractEmployee(16, "Amit", 32000));
        employees.add(new PermanentEmployee(17, "Suresh", 55000));
        employees.add(new ContractEmployee(18, "Anita", 40000));
        employees.add(new PermanentEmployee(19, "Kumar", 70000));
    }

    public void addEmployee(Employee e) {
        for (Employee emp : employees) {
            if (emp.getId() == e.getId()) {
                System.out.println("ID already exists!");
                return;
            }
        }
        employees.add(e);
    }

    public List<Employee> getAll() {
        return employees;
    }

    public Employee findById(int id) throws Exception {
        for (Employee e : employees) {
            if (e.getId() == id) return e;
        }
        throw new Exception("Employee not found");
    }

    public void updateEmployee(int id, String name, double salary) throws Exception {
        Employee e = findById(id);
        e.setName(name);
        e.setSalary(salary);
    }

    public void deleteEmployee(int id) throws Exception {
        Employee e = findById(id);
        employees.remove(e);
    }

    public List<Employee> searchByName(String name) {
        List<Employee> list = new ArrayList<>();
        for (Employee e : employees) {
            if (e.getName().equalsIgnoreCase(name)) {
                list.add(e);
            }
        }
        return list;
    }

    public void processSalary() {
        for (Employee e : employees) {
            new Thread(() -> {
                System.out.println(e.getName() + " Salary: " + e.calculateSalary());
            }).start();
        }
    }
}