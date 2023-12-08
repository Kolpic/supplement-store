package org.example.gui;

import lombok.Getter;
import org.example.model.shoppingcart.ShoppingCart;
import org.example.model.protein.BaseProtein;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

@Getter
public class ShoppingCartWindow extends JFrame {

    private JTable table;

    public ShoppingCartWindow(ShoppingCart shoppingCart) {
        setTitle("Cart");
        setSize(400, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // Create table
        String[] columnName = {"Product name", "Number", "Price", "Overall"};
        DefaultTableModel model = new DefaultTableModel(columnName, 0);
        table = new JTable(model);

        // Populate the table with data from the shopping cart
        double sumOfAllProducts = 0.00;
        for (BaseProtein product : shoppingCart.getProducts()) { // Assuming ShoppingCart has a getProducts() method
            double overallPrice = product.getPrice() * shoppingCart.getQuantities().get(0);
            sumOfAllProducts += overallPrice;
            Object[] row = {product.getName(), shoppingCart.getQuantities().remove(0), product.getPrice(), overallPrice};
            model.addRow(row);
        }
        Object[] row = {"", "", "", Math.round(sumOfAllProducts)};
        model.addRow(row);

        // Add table to scroll pane
        JScrollPane scrollPane = new JScrollPane(table);
        add(scrollPane, BorderLayout.CENTER);

        // Buttons panel
        JPanel buttonsPanel = new JPanel();
        buttonsPanel.setLayout(new FlowLayout());
        add(buttonsPanel, BorderLayout.SOUTH);

        // Button for pay via Card
        JButton buttonCard = new JButton("Pay via card");
        buttonsPanel.add(buttonCard);

        // Button for pay via PayPal
        JButton buttonPayPal = new JButton("Pay via PayPal");
        buttonsPanel.add(buttonPayPal);

        pack(); // Adjusts window to the size of its components
        setVisible(true);
    }
}


