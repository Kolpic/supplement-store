package org.example.gui;

import org.example.helper.FileReaderSupplements;
import org.example.model.BaseModel;
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
    private JLabel cartItemCountLabel;
    private JPanel cartPanel;
    private JPanel rightPanel;
    private JLabel welcomeLabel1;
    private JLabel welcomeLabel2;
    private Border border;

    public SwingApp() {
        // Reading file to make objects for the shop
        FileReaderSupplements supplements = new FileReaderSupplements();
        supplements.readSupplementsFromFile("online_shop_database.txt");

//        Map<String, List<BaseProtein>> mapProtein = supplements.getProteinz();
//        Map<String, List<BaseAminoAcid>> mapAmino = supplements.getAminos();
        Map<String, List<BaseModel>> mapProducts = supplements.getBaseModel();

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
        leftPanel.setLayout(new BoxLayout(leftPanel, BoxLayout.Y_AXIS));
//        leftPanel.setLayout(null);
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

        // Create buttons for all categories
        createButtons(leftPanel, mapProducts);
    }

    private void showShoppingCart() {
        ImageIcon icon = shoppingCart.getIcon();
        JLabel labelIcon = new JLabel(icon);
        labelIcon.setLayout(null);
        labelIcon.setBounds(20, 450, 100, 100);
        rightPanel.add(labelIcon);

        cartItemCountLabel = new JLabel();
        cartItemCountLabel.setBounds(130, 500, 200, 20); // Adjust as needed
        rightPanel.add(cartItemCountLabel);

        cartItemCountLabel.setText("You have " + shoppingCart.getItemCount() + " items in the cart.");
        updateCartItemCountLabel();

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
    // Call this method whenever you add or remove a product from the cart
    public void updateCartItemCountLabel() {
        cartItemCountLabel.setText("You have " + shoppingCart.getItemCount() + " items in the cart.");
        rightPanel.revalidate();
        rightPanel.repaint();
    }

    private void createButtons(Container container, Map<String, List<BaseModel>> model) {
        int counterLabels = 0;
        Insets buttonMargin = new Insets(5, 10, 5, 10); // Top, Left, Bottom, Right margins inside button
        int verticalSpacing = 5; // Space between buttons

        for (Map.Entry<String, List<BaseModel>> entry : model.entrySet()) {
            String key = entry.getKey();
            if (key.contains("protein")) {

                JButton button = new JButton(entry.getKey());
                button.setMargin(buttonMargin);
                button.setAlignmentX(0.05f);
                container.add(button);
                container.add(Box.createVerticalStrut(verticalSpacing));

                // Add ActionListener to handle button clicks
                button.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        // Handle the click event for the protein
                        displayBaseModelProducts(entry.getValue());
                    }
                });
            } else if (key.contains("supplements")) {
                if (counterLabels != 1) {
                    JLabel categoryLabelAminoAcids = new JLabel("Amino Acids");
                    container.add(categoryLabelAminoAcids);
                    counterLabels++;
                }

                JButton button = new JButton(entry.getKey());
                button.setMargin(buttonMargin);
                button.setAlignmentX(0.05f);
                container.add(button);
                container.add(Box.createVerticalStrut(verticalSpacing));

                // Add ActionListener to handle button clicks
                button.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        // Handle the click event for the protein
                        displayBaseModelProducts(entry.getValue());
                    }
                });
            }
        }
    }

    private void displayBaseModelProducts(List<BaseModel> modelList) {
        rightPanel.removeAll();
        revalidate();
        repaint();
        showShoppingCart();
        int x = 30;
        for (BaseModel currentProduct : modelList) {
            JLabel product = new JLabel();
            ImageIcon icon = currentProduct.getImageIcon();

            // Set layout to BorderLayout for proper alignment
            product.setLayout(new BorderLayout());
            product.setBorder(border);

            // Add components to product label with proper alignment
            product.add(new JLabel(currentProduct.getName()), BorderLayout.NORTH);
            product.add(new JLabel(icon), BorderLayout.CENTER);
            product.add(new JLabel("Price: " + currentProduct.getPrice() + " lv."), BorderLayout.SOUTH);

            // Set bounds for the product label
            product.setBounds(x, 10, 200, 200);

            JButton addToCartButton = new JButton("Add to Cart");
            addToCartButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    shoppingCart.addProduct(currentProduct, 1);
                    updateCartItemCountLabel();
                    System.out.println("Added in the cart");
                }
            });
            product.add(addToCartButton, BorderLayout.SOUTH);

            rightPanel.add(product);
            x = x + 210; // Adjust the horizontal spacing
        }
        rightPanel.revalidate();
        rightPanel.repaint();
    }
}




