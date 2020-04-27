package com.codersbay.databases;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class TeacherDAO {
    public TeacherDAO() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            System.out.println("Konnte den Treiber nicht laden!");
            e.printStackTrace();
        }
        System.out.println("Treiber  ist geladen worden :)");
    }

    public List<Teacher> getAllTeachers() {
        List<Teacher> allTeachers = new ArrayList<>();
        try {
            Connection connection = openConnection();
            PreparedStatement selectTeachers = connection.prepareStatement("SELECT * From teacher");
            ResultSet resultSet = selectTeachers.executeQuery();

            while (resultSet.next()) {

                Teacher teacher = getTeacherFromResultset(resultSet);
                allTeachers.add(teacher);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return allTeachers;
    }

    private Teacher getTeacherFromResultset(ResultSet resultSet) throws SQLException {
        int id = resultSet.getInt("teacher_id");
        String firstName = resultSet.getString("first_name");
        String lastName = resultSet.getString("last_name");
        int employeeNumber = resultSet.getInt("employee_number");
        return new Teacher(id, firstName, lastName, employeeNumber);
    }

    public Teacher getTeacher(int employeeNumber) {

        try {
            Connection connection = openConnection();
            // ? ist der Platzhalter für Parameter
            PreparedStatement selectOne = connection.prepareStatement("SELECT * FROM TEACHER WHERE EMPLOYEE_NUMBER = ?");
            selectOne.setInt(1, employeeNumber);
            ResultSet resultSet = selectOne.executeQuery();
            if (resultSet.next()) {
                return getTeacherFromResultset(resultSet);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public boolean deleteTeacher(int employeeNumber) {
        try {
            Connection connection = openConnection();
            PreparedStatement deleteStatement =
                    connection.prepareStatement(
                            "DELETE FROM TEACHER WHERE employee_number=?"
                    );
            deleteStatement.setInt(1, employeeNumber);

            return deleteStatement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean createTeacher(Teacher teacher) {
        // INSERT INTO TEACHER (first_name, last_name, employee_number) VALUES (:firstName, : lastName, :employeeNumber)

        try {
            Connection connection = openConnection();
            PreparedStatement insertStatement =
                    connection.prepareStatement(
                            "INSERT INTO TEACHER (first_name, last_name, employee_number) VALUES(?, ?, ?)"
                    );

            insertStatement.setString(1, teacher.getFirstName());
            insertStatement.setString(2, teacher.getLastName());
            insertStatement.setInt(3, teacher.getEmployeeNumber());

            return insertStatement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    private Connection openConnection() throws SQLException {
        String database = "school";
        String username = "root";
        String password = "";
        String connectionUrl = "jdbc:mysql://localhost:3306/"
                + database + "?user="
                + username + "&password=" + password;
        return DriverManager.getConnection(connectionUrl);
    }
}
