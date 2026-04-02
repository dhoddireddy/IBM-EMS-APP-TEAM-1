package com.ibm.ems;

import java.util.*;
import com.ibm.ems.model.*;
import com.ibm.ems.service.EmployeeService;
import com.ibm.ems.util.FileUtil;

public class EMSApp {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        EmployeeService service = new EmployeeService();

        try {
            List<Employee> loaded = FileUtil.load();
            if (loaded.isEmpty()) {
                service.loadDefaultEmployees();
            } else {
                service.getAll().addAll(loaded);
            }
        } catch (Exception e) {}

        System.out.println("==================================");
        System.out.println("     WELCOME TO IBM EMS SYSTEM    ");
        System.out.println("==================================");

        while (true) {

            System.out.println("\n==================================");
            System.out.println("1. Add Employee");
            System.out.println("2. View Employees");
            System.out.println("3. Update Employee");
            System.out.println("4. Delete Employee");
            System.out.println("5. Search Employee");
            System.out.println("6. Process Salary");
            System.out.println("7. Save Data");
            System.out.println("8. Exit");
            System.out.println("==================================");
            System.out.print("Enter choice: ");

            String input = sc.next();
            int ch;

            try {
                ch = Integer.parseInt(input);
            } catch (Exception e) {
                System.out.println("Enter valid number!");
                continue;
            }

            try {

                switch (ch) {

                    case 1:
                        System.out.print("ID: ");
                        int id = sc.nextInt();

                        System.out.print("Name: ");
                        sc.nextLine();
                        String name = sc.nextLine();

                        System.out.print("Salary: ");
                        double sal = sc.nextDouble();

                        System.out.print("Type (1-Permanent, 2-Contract): ");
                        int type = sc.nextInt();

                        if (type == 1)
                            service.addEmployee(new PermanentEmployee(id, name, sal));
                        else
                            service.addEmployee(new ContractEmployee(id, name, sal));
                        break;

                    case 2:
                        System.out.println("\n----- EMPLOYEE LIST -----");
                        for (Employee e : service.getAll()) {
                            System.out.println(e);
                        }
                        break;

                    case 3:
                        System.out.print("ID: ");
                        id = sc.nextInt();

                        System.out.print("Name: ");
                        sc.nextLine();
                        name = sc.nextLine();

                        System.out.print("Salary: ");
                        sal = sc.nextDouble();

                        service.updateEmployee(id, name, sal);
                        break;

                    case 4:
                        System.out.print("ID: ");
                        id = sc.nextInt();
                        service.deleteEmployee(id);
                        break;

                    case 5:
                        System.out.print("Name: ");
                        sc.nextLine();
                        name = sc.nextLine();
                        service.searchByName(name).forEach(System.out::println);
                        break;

                    case 6:
                        System.out.println("Processing Salaries...");
                        service.processSalary();
                        break;

                    case 7:
                        FileUtil.save(service.getAll());
                        System.out.println("Data Saved!");
                        break;

                    case 8:
                        System.out.println("Thank you for using IBM EMS 😊");
                        System.exit(0);
                }

            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
    }
}