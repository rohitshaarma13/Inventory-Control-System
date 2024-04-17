package inventory.control.system;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;

public class ViewPRODUCT extends JFrame implements ActionListener {
    JTable table;
    JButton update, back;
    
    ViewPRODUCT() {
        setLayout(null);
        
        JLabel heading = new JLabel("View Product");
        heading.setBounds(350, 0, 400, 40);
        heading.setFont(new Font("Raleway", Font.BOLD, 25));
        add(heading);
        
        table = new JTable();
        table.setBounds(0, 50, 800, 300);
        add(table);
        
        update = new JButton("Update");
        update.setBounds(150, 400, 120, 30);
        update.addActionListener(this);
        add(update);
        
        back = new JButton("Back");
        back.setBounds(350, 400, 120, 30);
        back.addActionListener(this);
        add(back);
        
        setSize(800, 500);
        setLocation(250, 100);
        setVisible(true);
        
        // Fetch and display product data in the table
        fetchProductData();
    }
    
    private void fetchProductData() {
        try {
            // You need to replace the database connection and query according to your database structure.
            Conn conn = new Conn();
            String query = "SELECT * FROM products";
            ResultSet rs = conn.s.executeQuery(query);

            table.setModel(DbUtils.resultSetToTableModel(rs)); // Use DbUtils to convert ResultSet to TableModel
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "An error occurred while fetching product data.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == update) {
            // Get the selected row index
            int rowIndex = table.getSelectedRow();
            if (rowIndex == -1) {
                JOptionPane.showMessageDialog(this, "Please select a product to update.", "Error", JOptionPane.ERROR_MESSAGE);
            } else {
                // Get the product ID from the selected row
                String productId = (String) table.getModel().getValueAt(rowIndex, 0);
                setVisible(false);
                new UpdatePRODUCT(productId); // Pass the product ID to the UpdatePRODUCT constructor
            }
        } else if (ae.getSource() == back) {
            setVisible(false);
            new Home(); // Navigate back to the main menu
        }
    }

    public static void main(String[] args) {
        new ViewPRODUCT();
    }
}
