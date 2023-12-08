package org.example.gui;

import org.example.helper.FileReaderSupplements;
import org.example.model.shoppingcart.ShoppingCart;
import org.example.model.aminoasid.BaseAminoAcid;
import org.example.model.protein.BaseProtein;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.Map;

public class SwingApp extends JFrame {

    private ShoppingCart shoppingCart;
    private JPanel cartPanel;
    private JPanel rightPanel;
    private JLabel welcomeLabel1;
    private JLabel welcomeLabel2;
    private Border border;

    public SwingApp() {
        // Reading file to make objects for the shop
        FileReaderSupplements supplements = new FileReaderSupplements();
        supplements.readSupplementsFromFile("online_shop_database.txt");

//        List<BaseProtein> proteinList = supplements.getProteins();
//        List<BaseAminoAcid> aminoAcidList = supplements.getAminoAcids();

        Map<String, List<BaseProtein>> mapProtein = supplements.getProteinz();
        Map<String, List<BaseAminoAcid>> mapAmino = supplements.getAminos();

        // Set up the main frame
        setTitle("Supplement Store App");
        setSize(815, 640);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(null);
        border = BorderFactory.createLineBorder(Color.black, 2);

        // leftPanel
        JPanel leftPanel = new JPanel();
//        leftPanel.setBackground(Color.red);
        leftPanel.setBorder(border);
        leftPanel.setBounds(0,0,250,600);
        leftPanel.setLayout(null);
        add(leftPanel);

        // rightPanel
        rightPanel = new JPanel();
//        rightPanel.setBackground(Color.blue);
        rightPanel.setBorder(border);
        rightPanel.setBounds(250,0,550,600);
        rightPanel.setLayout(null);
        add(rightPanel);

        // Configure cart
        shoppingCart = new ShoppingCart();
//        showShoppingCart();

        // Welcome label
        welcomeLabel1 = new JLabel("WELCOME !!!");
        welcomeLabel1.setFont(new Font("WELCOME !!!", Font.BOLD, 30));
        welcomeLabel1.setBounds(200,0,200,150);
        rightPanel.add(welcomeLabel1);

        welcomeLabel2 = new JLabel("Please, select a category.");
        welcomeLabel2.setFont(new Font("Please, select a category.", Font.BOLD, 17));
        welcomeLabel2.setBounds(200,20,250,250);
        rightPanel.add(welcomeLabel2);

        // Category Proteins
        JLabel categoryLabelProteins = new JLabel("Proteins");
        categoryLabelProteins.setBounds(5,20,100,20);
        leftPanel.add(categoryLabelProteins);

        // Subcategories for Proteins
        createButtonsForProteins(leftPanel, mapProtein);

        // Category AminoAcids
        JLabel categoryLabelAminoAcids = new JLabel("Amino Acids");
        categoryLabelAminoAcids.setBounds(5,125,100,20);
        leftPanel.add(categoryLabelAminoAcids);

        // Subcategories for AminoAcids
        createButtonsForAminoAcids(leftPanel, mapAmino);
    }

    private void showShoppingCart() {
        shoppingCart = new ShoppingCart();
        ImageIcon icon = shoppingCart.getIcon();
        JLabel labelIcon = new JLabel(icon);
        labelIcon.setLayout(null);
        labelIcon.setBounds(20, 450, 100, 100);
        rightPanel.add(labelIcon);
        Button buttonCart = new Button("View Cart");
        buttonCart.setBounds(25, 550, 100, 25);
        buttonCart.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ShoppingCartWindow window = new ShoppingCartWindow(shoppingCart);
            }
        });
        rightPanel.add(buttonCart);
    }

    private void createButtonsForAminoAcids(Container container, Map<String, List<BaseAminoAcid>> amino) {
        int yPosition = 150;
        for (Map.Entry<String, List<BaseAminoAcid>> entry : amino.entrySet()) {
            JButton button = new JButton(entry.getKey()); // Use the actual property of the protein
            button.setBounds(20, yPosition, 150, 20);
            container.add(button);

            // Add ActionListener to handle button clicks
            button.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    // Handle the click event for the protein
                    displayProducts(null, entry.getValue()); // Use the actual property of the protein
                }
            });

            yPosition += 23; // Adjust the vertical spacing
        }
    }

    private void createButtonsForProteins(Container container, Map<String,List<BaseProtein>> proteins) {
        int yPosition = 50;
        for (Map.Entry<String, List<BaseProtein>> entry : proteins.entrySet()) {
            JButton button = new JButton(entry.getKey()); // Use the actual property of the protein
            button.setBounds(20, yPosition, 150, 20);
            container.add(button);

            // Add ActionListener to handle button clicks
            button.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    // Handle the click event for the protein
                    displayProducts(entry.getValue(), null); // Use the actual property of the protein
                }
            });

            yPosition += 23; // Adjust the vertical spacing
        }
    }
    // Method to display products based on the selected category
    private void displayProducts(List<BaseProtein> proteins, List<BaseAminoAcid> aminoAcids) {
        rightPanel.removeAll();
        revalidate();
        repaint();
        showShoppingCart();
        if (proteins != null) {
            int x = 30;
            for (BaseProtein currentProtein : proteins) {
                JLabel product = new JLabel();
                ImageIcon icon = currentProtein.getImageIcon();

                // Set layout to BorderLayout for proper alignment
                product.setLayout(new BorderLayout());
                product.setBorder(border);

                // Add components to product label with proper alignment
                product.add(new JLabel(currentProtein.getName()), BorderLayout.NORTH);
                product.add(new JLabel(icon), BorderLayout.CENTER);
                product.add(new JLabel("Price: " + currentProtein.getPrice() + " lv."), BorderLayout.SOUTH);

                // Set bounds for the product label
                product.setBounds(x, 10, 200, 200);

                JButton addToCartButton = new JButton("Add to Cart");
                addToCartButton.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        shoppingCart.addProduct(currentProtein, 1);
                        System.out.println("Added in the cart");
                    }
                });
                product.add(addToCartButton, BorderLayout.SOUTH);

                rightPanel.add(product);
                x = x + 210; // Adjust the horizontal spacing
            }
        } else {
            int x = 30;
            for (BaseAminoAcid currentAmino : aminoAcids) {
                JLabel product = new JLabel();
                ImageIcon icon = currentAmino.getImageIcon();

                // Set layout to BorderLayout for proper alignment
                product.setLayout(new BorderLayout());
                product.setBorder(border);

                // Add components to product label with proper alignment
                product.add(new JLabel(currentAmino.getName()), BorderLayout.NORTH);
                product.add(new JLabel(icon), BorderLayout.CENTER);
                product.add(new JLabel("Price: " + currentAmino.getPrice() + " lv."), BorderLayout.SOUTH);

                // Set bounds for the product label
                product.setBounds(x, 10, 200, 200);

                rightPanel.add(product);
                x = x + 210; // Adjust the horizontal spacing
            }
        }
        rightPanel.revalidate();
        rightPanel.repaint();
//        JOptionPane.showMessageDialog(this, "Displaying products for: " + category);
    }

}


