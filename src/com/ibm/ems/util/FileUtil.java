package com.ibm.ems.util;

import java.io.*;
import java.util.*;
import com.ibm.ems.model.*;

public class FileUtil {

    private static final String FILE_NAME = "employees.txt";

    public static void saveEmployees(List<Employee> employees) throws IOException {

        try (PrintWriter writer = new PrintWriter(new FileWriter(FILE_NAME))) {

            for (Employee emp : employees) {

                String type;

                if (emp instanceof PermanentEmployee) {
                    type = "P";
                } else {
                    type = "C";
                }

                String data = emp.getId() + "|"
                            + emp.getName() + "|"
                            + emp.getSalary() + "|"
                            + type;

                writer.println(data);
            }
        }
    }

    public static List<Employee> loadEmployees() throws IOException {

        List<Employee> employees = new LinkedList<>();

        File file = new File(FILE_NAME);

        if (!file.exists()) {
            return employees;
        }

        try (Scanner sc = new Scanner(file)) {

            while (sc.hasNextLine()) {

                String row = sc.nextLine();

                StringTokenizer st = new StringTokenizer(row, "|");

                int id = Integer.parseInt(st.nextToken());
                String name = st.nextToken();
                double salary = Double.parseDouble(st.nextToken());
                String type = st.nextToken();

                Employee emp;

                if (type.equals("P")) {
                    emp = new PermanentEmployee(id, name, salary);
                } else {
                    emp = new ContractEmployee(id, name, salary);
                }

                employees.add(emp);
            }
        }

        return employees;
    }
}