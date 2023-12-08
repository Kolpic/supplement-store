package org.example;

import org.example.gui.SwingApp;

import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            SwingApp app = new SwingApp();
            app.setVisible(true);
        });
    }
}