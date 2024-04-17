package inventory.control.system;

import java.awt.*;
import javax.swing.*;
import java.util.*;
import java.awt.event.*;

public class AddPRODUCT extends JFrame implements ActionListener{
    
    Random ran = new Random();
    int number = ran.nextInt(999999);
    
    JTextField tfproductName, tfproductCategory, tfproductPrice, tfproductQuantity;
    JLabel lblproductId;
    JButton add, back;
    
    AddPRODUCT() {
        getContentPane().setBackground(Color.WHITE);
        setLayout(null);
        
        JLabel heading = new JLabel("Add Product Detail");
        heading.setBounds(320, 30, 500, 50);
        heading.setFont(new Font("SAN_SERIF", Font.BOLD, 25));
        add(heading);
        
        JLabel labelProductName = new JLabel("Product Name");
        labelProductName.setBounds(50, 150, 150, 30);
        labelProductName.setFont(new Font("serif", Font.PLAIN, 20));
        add(labelProductName);
        
        tfproductName = new JTextField();
        tfproductName.setBounds(200, 150, 150, 30);
        add(tfproductName);
        
        JLabel labelProductCategory = new JLabel("Product Category");
        labelProductCategory.setBounds(400, 150, 150, 30);
        labelProductCategory.setFont(new Font("serif", Font.PLAIN, 20));
        add(labelProductCategory);
        
        tfproductCategory = new JTextField();
        tfproductCategory.setBounds(600, 150, 150, 30);
        add(tfproductCategory);
        
        JLabel labelProductPrice = new JLabel("Product Price");
        labelProductPrice.setBounds(50, 200, 150, 30);
        labelProductPrice.setFont(new Font("serif", Font.PLAIN, 20));
        add(labelProductPrice);
        
        tfproductPrice = new JTextField();
        tfproductPrice.setBounds(200, 200, 150, 30);
        add(tfproductPrice);
        
        JLabel labelProductQuantity = new JLabel("Product Quantity");
        labelProductQuantity.setBounds(400, 200, 150, 30);
        labelProductQuantity.setFont(new Font("serif", Font.PLAIN, 20));
        add(labelProductQuantity);
        
        tfproductQuantity = new JTextField();
        tfproductQuantity.setBounds(600, 200, 150, 30);
        add(tfproductQuantity);
        
        JLabel labelProductId = new JLabel("Product ID");
        labelProductId.setBounds(50, 250, 150, 30);
        labelProductId.setFont(new Font("serif", Font.PLAIN, 20));
        add(labelProductId);
        
        lblproductId = new JLabel("" + number);
        lblproductId.setBounds(200, 250, 150, 30);
        lblproductId.setFont(new Font("serif", Font.PLAIN, 20));
        add(lblproductId);
        
        add = new JButton("Add Product");
        add.setBounds(250, 300, 150, 40);
        add.addActionListener(this);
        add.setBackground(Color.BLACK);
        add.setForeground(Color.WHITE);
        add(add);
        
        back = new JButton("Back");
        back.setBounds(450, 300, 150, 40);
        back.addActionListener(this);
        back.setBackground(Color.BLACK);
        back.setForeground(Color.WHITE);
        add(back);
        
        setSize(900, 400);
        setLocation(300, 50);
        setVisible(true);
    }
    
    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == add) {
            String productName = tfproductName.getText();
            String productCategory = tfproductCategory.getText();
            String productPrice = tfproductPrice.getText();
            String productQuantity = tfproductQuantity.getText();
            String productId = lblproductId.getText();
            
            try {
                // You need to replace the database connection and query according to your database structure.
                Conn c = new Conn();
                String query = "INSERT INTO products (product_name, category, price, quantity, product_id) VALUES ('"+productName+"', '"+productCategory+"', '"+productPrice+"', '"+productQuantity+"', '"+productId+"')";
                c.s.executeUpdate(query);
                JOptionPane.showMessageDialog(null, "Product added successfully");
                setVisible(false);
                new Home(); // Assuming there's a Home class to navigate back to the main menu.
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            setVisible(false);
            new Home(); // Assuming there's a Home class to navigate back to the main menu.
        }
    }

    public static void main(String[] args) {
        new AddPRODUCT();
    }
}
