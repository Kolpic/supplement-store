package org.example.model.shoppingcart;

import lombok.Getter;
import org.example.model.BaseModel;
import org.example.model.protein.BaseProtein;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

@Getter
public class ShoppingCart {
    private ImageIcon icon;
    private List<BaseModel> products;
    private List<Integer> quantities;

    public ShoppingCart() {
        products = new ArrayList<>();
        quantities = new ArrayList<>();
        icon = new ImageIcon("src\\main\\resources\\images/shoppingCart.jpg");
    }

    public void addProduct(BaseModel product, int quantity) {
        products.add(product);
        quantities.add(quantity);
    }

    public int getItemCount() {
        int count = 0;
        for (int quantity : quantities) {
            count += quantity;
        }
        return count;
    }

    public void removeProduct(BaseModel product) {
        int index = products.indexOf(product);
        if (index != -1) {
            products.remove(index);
            quantities.remove(index);
        }
    }

    public double calculateTotalPrice() {
        double totalPrice = 0;
        for (int i = 0; i < products.size(); i++) {
            totalPrice += products.get(i).getPrice() * quantities.get(i);
        }
        return totalPrice;
    }

}


