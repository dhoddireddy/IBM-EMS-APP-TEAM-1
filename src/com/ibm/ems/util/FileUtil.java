package com.ibm.ems.util;

import java.io.*;
import java.util.*;
import com.ibm.ems.model.*;

public class FileUtil {

    public static void save(List<Employee> list) throws Exception {
        BufferedWriter bw = new BufferedWriter(new FileWriter("employees.txt"));

        for (Employee e : list) {
            bw.write(e.getId() + "," + e.getName() + "," + e.getSalary() + "," + e.getClass().getSimpleName());
            bw.newLine();
        }

        bw.close();
    }

    public static List<Employee> load() throws Exception {

        List<Employee> list = new ArrayList<>();
        File file = new File("employees.txt");

        if (!file.exists()) return list;

        BufferedReader br = new BufferedReader(new FileReader(file));
        String line;

        while ((line = br.readLine()) != null) {

            String[] d = line.split(",");

            int id = Integer.parseInt(d[0]);
            String name = d[1];
            double sal = Double.parseDouble(d[2]);

            if (d[3].equals("PermanentEmployee"))
                list.add(new PermanentEmployee(id, name, sal));
            else
                list.add(new ContractEmployee(id, name, sal));
        }

        br.close();
        return list;
    }
}