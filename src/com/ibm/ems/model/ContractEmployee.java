package com.ibm.ems.model;

public class ContractEmployee extends Employee {

    public ContractEmployee(int id, String name, double salary) {
        super(id, name, salary);
    }

    @Override
    public double calculateSalary() {
        return getSalary();
    }
}