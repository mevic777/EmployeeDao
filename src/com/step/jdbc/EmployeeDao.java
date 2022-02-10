package com.step.jdbc;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class EmployeeDao { // Data acces object

    private Connection getConnection() throws SQLException {
        Connection connection = DriverManager.getConnection("jdbc:postgresql://127.0.0.1:5432/employee", "postgres", "4334");

        return connection;
    }

    //  creare
    public Employee create(Employee employee) throws SQLException {
//        cod lung care nu este foarte bun
//        Connection connection = getConnection();
//
//        String insert = "INSERT INTO app.emplyee(name, surname) values(?, ?)";  //  inlocuim cu setString() de mai jos
//        PreparedStatement statement = connection.prepareStatement(insert);
//        statement.setString(1, employee.getName()); //  inlocuirea semnelor de intrebare de mai sus
//        statement.setString(2, employee.getSurname());
//        int row = statement.executeUpdate();
//
//        if (row == 0) {
//            System.out.println("Eroare: " + row + " randuri au fost modificate");
//        }
//
//        statement.close();
//        connection.close();

//        cod scurt care este mai bun, folosim clasa AutoClosable
        String insert = "INSERT INTO app.emplyee(name, surname) values(?, ?)";
        
        try (Connection connection = getConnection(); PreparedStatement statement = connection.prepareStatement(insert, Statement.RETURN_GENERATED_KEYS)) {
            statement.setString(1, employee.getName()); //  inlocuirea semnelor de intrebare de mai sus
            statement.setString(2, employee.getSurname());

            int row = statement.executeUpdate();
            if (row == 0) {
                System.out.println("Eroare: " + row + " randuri au fost modificate.");
            } else {
                ResultSet result = statement.getGeneratedKeys();

                while (result.next()) {
                    int idEmployee = result.getInt(1);
                    employee.setId(idEmployee);
                    System.out.println("Generated key: " + idEmployee);
                }
            }
        }

        return employee;
    }

    public List<Employee> readAll() throws SQLException {
        List<Employee> employees = new ArrayList<>();
        Connection connection = getConnection();

        String select = "SELECT id, name, surname FROM app.emplyee order by id asc";
        PreparedStatement statement = connection.prepareStatement(select);
        ResultSet result = statement.executeQuery();

        while (result.next()) {
            int id = result.getInt("id");
            String name = result.getString("name");
            String surname = result.getString("surname");
            employees.add(new Employee(id, name, surname));
        }

        statement.close();
        connection.close();

        return employees;
    }

    //  update
    public void update(int id, String name, String surname) throws SQLException {

        Connection connection = getConnection();
        String update = "UPDATE app.emplyee set name = ?, surname = ? where id = ?";
        PreparedStatement statement = connection.prepareStatement(update);

        statement.setString(1, name);
        statement.setString(2, surname);
        statement.setInt(3, id);
        statement.executeUpdate();

        statement.close();
        connection.close();
    }

    public void delete(int id) throws SQLException {

        Connection connection = getConnection();
        String delete = "DELETE from app.emplyee where id=?";
        PreparedStatement statement = connection.prepareStatement(delete);

        statement.setInt(1, id);
        statement.executeUpdate();

        statement.close();
        connection.close();
    }

    public List<Employee> readWithResources() throws SQLException {
        List<Employee> employees = new ArrayList<>();
        String select = "SELECT id, name, surname FROM app.emplyee order by id asc";

        try (Connection connection = getConnection();
             Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery(select);
        }

        return null;
    }


}


























