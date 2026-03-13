import java.sql.*;

public class EmployeeJDBC {
    public static void main(String[] args) {

        String url = "jdbc:mysql://localhost:3306/TEST";
        String username = "root";
        String password = "root";

        try {
            // Connect to database
            Connection con = DriverManager.getConnection(url, username, password);
            System.out.println("Connected to database");

            // Insert employees (duplicate avoid करने के लिए INSERT IGNORE)
            String insertQuery = "INSERT IGNORE INTO employee VALUES (?, ?, ?, ?)";
            PreparedStatement ps = con.prepareStatement(insertQuery);

            // Employee 1
            ps.setInt(1, 1);
            ps.setString(2, "E1");
            ps.setString(3, "IT");
            ps.setInt(4, 30000);
            ps.executeUpdate();

            // Employee 2
            ps.setInt(1, 2);
            ps.setString(2, "E2");
            ps.setString(3, "HR");
            ps.setInt(4, 25000);
            ps.executeUpdate();

            System.out.println("Records Inserted");

            // Update salary
            String updateQuery = "UPDATE employee SET salary=? WHERE id=?";
            PreparedStatement ps2 = con.prepareStatement(updateQuery);

            ps2.setInt(1, 40000);
            ps2.setInt(2, 1);
            ps2.executeUpdate();

            System.out.println("Salary Updated");

            // Retrieve data
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery("SELECT * FROM employee");

            System.out.println("Employee Records:");

            while (rs.next()) {
                System.out.println(
                        rs.getInt("id") + " " +
                                rs.getString("name") + " " +
                                rs.getString("department") + " " +
                                rs.getInt("salary")
                );
            }

            // Close connections
            rs.close();
            st.close();
            ps.close();
            ps2.close();
            con.close();

            System.out.println("Connection Closed");

        } catch (Exception e) {
            System.out.println(e);
        }
    }
}