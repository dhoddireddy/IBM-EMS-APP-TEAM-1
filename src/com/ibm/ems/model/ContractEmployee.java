package com.ibm.ems.model;

public class ContractEmployee extends Employee {

    public ContractEmployee(int id, String name, double salary) {
        super(id, name, salary);
    }
private double deductionRate = 0.05;// 5% deduction for contract employees  

@Override
public double calculateSalary() {
    double salary = getSalary();
    double deduction = salary * deductionRate;
    return salary - deduction;
}