package inventory.control.system;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;

public class UpdatePRODUCT extends JFrame implements ActionListener {

    JTextField tfProductName, tfCategory, tfPrice, tfQuantity;
    JLabel lblProductId;
    JButton update, back;
    String productId;

    UpdatePRODUCT(String productId) {
        this.productId = productId;
        getContentPane().setBackground(Color.WHITE);
        setLayout(null);

        JLabel heading = new JLabel("Update Product Detail");
        heading.setBounds(320, 30, 500, 50);
        heading.setFont(new Font("SAN_SERIF", Font.BOLD, 25));
        add(heading);

        JLabel labelProductName = new JLabel("Product Name");
        labelProductName.setBounds(50, 150, 150, 30);
        labelProductName.setFont(new Font("serif", Font.PLAIN, 20));
        add(labelProductName);

        tfProductName = new JTextField();
        tfProductName.setBounds(200, 150, 150, 30);
        add(tfProductName);

        JLabel labelCategory = new JLabel("Category");
        labelCategory.setBounds(400, 150, 150, 30);
        labelCategory.setFont(new Font("serif", Font.PLAIN, 20));
        add(labelCategory);

        tfCategory = new JTextField();
        tfCategory.setBounds(600, 150, 150, 30);
        add(tfCategory);

        JLabel labelPrice = new JLabel("Price");
        labelPrice.setBounds(50, 200, 150, 30);
        labelPrice.setFont(new Font("serif", Font.PLAIN, 20));
        add(labelPrice);

        tfPrice = new JTextField();
        tfPrice.setBounds(200, 200, 150, 30);
        add(tfPrice);

        JLabel labelQuantity = new JLabel("Quantity");
        labelQuantity.setBounds(400, 200, 150, 30);
        labelQuantity.setFont(new Font("serif", Font.PLAIN, 20));
        add(labelQuantity);

        tfQuantity = new JTextField();
        tfQuantity.setBounds(600, 200, 150, 30);
        add(tfQuantity);

        lblProductId = new JLabel("Product ID");
        lblProductId.setBounds(50, 250, 150, 30);
        lblProductId.setFont(new Font("serif", Font.PLAIN, 20));
        add(lblProductId);

        JLabel lblProductIdValue = new JLabel(productId);
        lblProductIdValue.setBounds(200, 250, 150, 30);
        lblProductIdValue.setFont(new Font("serif", Font.PLAIN, 20));
        add(lblProductIdValue);

        update = new JButton("Update Details");
        update.setBounds(250, 300, 150, 40);
        update.addActionListener(this);
        update.setBackground(Color.BLACK);
        update.setForeground(Color.WHITE);
        add(update);

        back = new JButton("Back");
        back.setBounds(450, 300, 150, 40);
        back.addActionListener(this);
        back.setBackground(Color.BLACK);
        back.setForeground(Color.WHITE);
        add(back);

        setSize(900, 400);
        setLocation(300, 50);
        setVisible(true);

        // Fetch and display product details
        fetchProductDetails(productId);
    }

    private void fetchProductDetails(String productId) {
        try {
            // You need to replace the database connection and query according to your database structure.
            Conn conn = new Conn();
            String query = "SELECT * FROM products WHERE product_id = ?";
            PreparedStatement pstmt = conn.prepareStatement(query);
            pstmt.setString(1, productId);
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                tfProductName.setText(rs.getString("product_name"));
                tfCategory.setText(rs.getString("category"));
                tfPrice.setText(String.valueOf(rs.getDouble("price")));
                tfQuantity.setText(String.valueOf(rs.getInt("quantity")));
            }
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "An error occurred while fetching product details.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == update) {
            // Update product in the database
            String productName = tfProductName.getText();
            String category = tfCategory.getText();
            double price = Double.parseDouble(tfPrice.getText());
            int quantity = Integer.parseInt(tfQuantity.getText());

            try {
                // You need to replace the database connection and query according to your database structure.
                Conn conn = new Conn();
                String query = "UPDATE products SET product_name = ?, category = ?, price = ?, quantity = ? WHERE product_id = ?";
                PreparedStatement pstmt = conn.prepareStatement(query);
                pstmt.setString(1, productName);
                pstmt.setString(2, category);
                pstmt.setDouble(3, price);
                pstmt.setInt(4, quantity);
                pstmt.setString(5, productId);

                int rowsAffected = pstmt.executeUpdate();
                if (rowsAffected > 0) {
                    JOptionPane.showMessageDialog(this, "Product updated successfully.");
                    setVisible(false);
                    new Home(); // Navigate back to the main menu
                } else {
                    JOptionPane.showMessageDialog(this, "Failed to update product.", "Error", JOptionPane.ERROR_MESSAGE);
                }
            } catch (Exception e) {
                e.printStackTrace();
                JOptionPane.showMessageDialog(this, "An error occurred while updating product.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        } else if (ae.getSource() == back) {
            setVisible(false);
            new Home(); // Navigate back to the main menu
        }
    }

    public static void main(String[] args) {
         new UpdatePRODUCT("");
    }
}
