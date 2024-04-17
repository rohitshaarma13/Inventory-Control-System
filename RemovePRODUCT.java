package inventory.control.system;

import java.awt.*;
import javax.swing.*;
import java.awt.event.*;

public class RemovePRODUCT extends JFrame implements ActionListener {

    JTextField tfProductId;
    JButton remove, back;

   RemovePRODUCT() {
        getContentPane().setBackground(Color.WHITE);
        setLayout(null);

        JLabel heading = new JLabel("Remove Product");
        heading.setBounds(320, 30, 500, 50);
        heading.setFont(new Font("SAN_SERIF", Font.BOLD, 25));
        add(heading);

        JLabel labelProductId = new JLabel("Product ID");
        labelProductId.setBounds(50, 150, 150, 30);
        labelProductId.setFont(new Font("serif", Font.PLAIN, 20));
        add(labelProductId);

        tfProductId = new JTextField();
        tfProductId.setBounds(200, 150, 150, 30);
        add(tfProductId);

        remove = new JButton("Remove Product");
        remove.setBounds(250, 250, 200, 40);
        remove.addActionListener(this);
        remove.setBackground(Color.BLACK);
        remove.setForeground(Color.WHITE);
        add(remove);

        back = new JButton("Back");
        back.setBounds(480, 250, 150, 40);
        back.addActionListener(this);
        back.setBackground(Color.BLACK);
        back.setForeground(Color.WHITE);
        add(back);

        setSize(700, 400);
        setLocation(300, 50);
        setVisible(true);
    }

    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == remove) {
            String productId = tfProductId.getText();

            try {
                // You need to replace the database connection and query according to your database structure.
                Conn conn = new Conn();
                String query = "DELETE FROM products WHERE product_id = '" + productId + "'";
                int result = conn.s.executeUpdate(query);
                if (result > 0) {
                    JOptionPane.showMessageDialog(null, "Product removed successfully");
                } else {
                    JOptionPane.showMessageDialog(null, "Product not found");
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            setVisible(false);
            new Home(); // Assuming there's a Home class to navigate back to the main menu.
        }
    }

    public static void main(String[] args) {
        new RemovePRODUCT();
    }
}
