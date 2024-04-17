package inventory.control.system;

import java.sql.*;

public class Conn {
    
    Connection c;
    Statement s;

    public Conn () {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            c = DriverManager.getConnection("jdbc:mysql:///Inventorycontrolsystem", "root", "ishatv@12");
            s = c.createStatement();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Proper implementation of prepareStatement method
    public PreparedStatement prepareStatement(String query) throws SQLException {
        return c.prepareStatement(query);
    }
}
