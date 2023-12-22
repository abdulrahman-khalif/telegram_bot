package org.example;

/**
 * this class for to get data from my SQL database.
 *
 * @auther Abdulrahman Khalif Hashi Nur
 */

import java.sql.*;
public class DatabaseSqlite {

    private int id;
    private String name;
    private int room;
    // Method display  nearby Lectures data from database

    /**
     * this Method to display nearby Lectures data from database.
     *
     * @param getMessage the first name that I'll input by user
     * @return ID, name, and room for the 5 nearby lecture from the database.
     * @throws SQLException informing the caller of the method that they should be prepared to handle this exception
     */
    public StringBuilder displayNearbay(String getMessage) throws SQLException {
        Connection conn = null;
        conn = DriverManager.getConnection("jdbc:sqlite:C:/Sqlite_databases/lecturers_rooms.db");
        StringBuilder response = new StringBuilder();

        try {
            String sql = "SELECT * FROM LR WHERE Name LIKE '%" + getMessage + "%'";
            ResultSet rs = conn.createStatement().executeQuery(sql);
                name = rs.getString("Name");
                 room = rs.getInt("Room");

                 sql = "SELECT * FROM 'LR' WHERE Room != "+room+" ORDER BY ABS(Room - "+room+") LIMIT 5";
            ResultSet resultSet = conn.createStatement().executeQuery(sql);


                while (resultSet.next()) {
                     id = resultSet.getInt("StaffID");
                    String name = resultSet.getString("Name");
                    int room = resultSet.getInt("Room");
                    response.append("ID: " + id + "\n Name: Dr. " + name + "\n Room: " + room+"\n").append("\n");
                }



        } catch (SQLException e) {

            System.err.println(e.getMessage());
        }
        return response;
    }

    // display Lecture Name:

    /**
     * this class for display lecture name from database.
     *
     * @param getMessage the first name that I'll input by user.
     * @return give info for ID, Name, And Room for the lecture.
     * @throws SQLException informing the caller of the method that they should be prepared to handle this exception.
     */
    public StringBuilder Display_LectureName(String getMessage) throws SQLException {

        Connection conn;
        conn = DriverManager.getConnection("jdbc:sqlite:C:/Sqlite_databases/lecturers_rooms.db");
        StringBuilder response = new StringBuilder();


        String sql = "SELECT * FROM LR WHERE Name LIKE '%" + getMessage + "%'";
        ResultSet rs = conn.createStatement().executeQuery(sql);

        int id = rs.getInt("StaffID");
        name = rs.getString("Name");
        int room = rs.getInt("Room");

            response.append("ID: " + id + "\n Name: Dr. " + name + "\n Room: " + room+"\n").append("\n");

            return response;

        }

        // to return Lecture name

    /**
     *
     * @return name of the lecture.
     */
        public String getName (){
            return name;
        }

    }
