package com.step.jdbc;

import java.sql.SQLException;
import java.util.List;

public class App {

    public static void main(String[] args) throws SQLException {
        new  Employee("john", "smith");
        EmployeeDao dao = new EmployeeDao();

//        create
        System.out.println(dao.create(new Employee("Maria", "Popescu")));

//        show
//        List<Employee> employeeList = dao.readAll();
//        for(Employee emp: dao.readAll()) {
//            System.out.println(emp);
//        }
//        update
//        dao.update(1, "Marius", "Covali");
//        dao.delete(1);
    }
}
