package com.example.javatutorial;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.sql.*;
import java.util.logging.Logger;

@SpringBootApplication
public class JavaTutorialApplication {

    private static final String DB_USERNAME = "root";
    private static final String DB_PASSWORD = "root";
    private static final String DB_URL = "jdbc:mysql://localhost:3306/javatutorial";

    public static void main(String[] args) {
        SpringApplication.run(JavaTutorialApplication.class, args);
//        insert();
//        select();
//        update();
        delete();
    }

    private static void select() {
        String query = "SELECT * FROM PERSON";

        try (Connection connection = DriverManager.getConnection(DB_URL, DB_USERNAME, DB_PASSWORD);
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(query)
        ) {
            Class.forName("com.mysql.cj.jdbc.Driver");
            long id;
            String name;
            String family;
            while (resultSet.next()) {
                id = resultSet.getLong("id");
                name = resultSet.getString("NAME");
                family = resultSet.getString("FAMILY");
                System.out.println("id= " + id + " name= " + name + " family= " + family);
            }
        } catch (Exception ex) {
            Logger.getLogger(ex.getMessage());
        }
    }

    private static void insert() {
        String query = "INSERT INTO PERSON (ID , NAME , FAMILY) VALUES (? , ? , ?)";
        try (Connection connection = DriverManager.getConnection(DB_URL, DB_USERNAME, DB_PASSWORD);
             PreparedStatement preparedStatement = connection.prepareStatement(query);
        ) {
            Class.forName("com.mysql.cj.jdbc.Driver");
            preparedStatement.setLong(1, 3L);
            preparedStatement.setString(2, "mohsen");
            preparedStatement.setString(3, "ri");
            int insertedRows = preparedStatement.executeUpdate();
            System.out.println(insertedRows + " rows added");
        } catch (Exception ex) {
            Logger.getLogger(ex.getMessage());
        }
    }

    private static void update() {
        String query = "UPDATE PERSON SET FAMILY=? WHERE NAME LIKE ?";
        try (Connection connection = DriverManager.getConnection(DB_URL, DB_USERNAME, DB_PASSWORD);
             PreparedStatement preparedStatement = connection.prepareStatement(query);
        ) {
            Class.forName("com.mysql.cj.jdbc.Driver");
            preparedStatement.setString(1, "jhonson");
            preparedStatement.setString(2, "mohsen");
            int rowsEffected = preparedStatement.executeUpdate();
            System.out.println(rowsEffected + " rows effected");
        } catch (Exception ex) {
            Logger.getLogger(ex.getMessage());
        }
    }

    private static void delete() {
        String query = "DELETE FROM PERSON WHERE NAME LIKE ?";
        try (Connection connection = DriverManager.getConnection(DB_URL, DB_USERNAME, DB_PASSWORD);
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            Class.forName("com.mysql.cj.jdbc.Driver");
            preparedStatement.setString(1, "mohsen");
            int deletedRows = preparedStatement.executeUpdate();
            System.out.println(deletedRows + " rows deleted");
        } catch (Exception ex) {
            System.out.println(ex);
        }
    }
}
