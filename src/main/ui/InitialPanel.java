package ui;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

// represents the initial panel that will greet the users
public class InitialPanel extends JPanel {

    // EFFECTS: Initializes the buttons and displays the initial panel
    public InitialPanel(ItineraryAppUI appUI, ItineraryController controller) {
        setLayout(new BorderLayout());
        ImageIcon logo = new ImageIcon(new ImageIcon("./data/logo.png").getImage());
        JLabel logoLabel = new JLabel(logo);

        add(logoLabel, BorderLayout.CENTER);

        JPanel buttonPanel = new JPanel(new GridLayout(2, 1));
        JButton loadButton = new JButton("Load Itinerary");
        JButton newButton = new JButton("New Itinerary");


        loadButton.addActionListener(e -> {
            controller.loadItinerary();
            appUI.showDashboard();
        });

        newButton.addActionListener(e -> {
            String name = JOptionPane.showInputDialog(this, "Enter Itinerary Name: ");
            int numOfDays = Integer.parseInt(JOptionPane.showInputDialog(this, "Enter Number of Days: "));
            JOptionPane.showMessageDialog(this, "New Itinerary Created.");
            controller.createNewItinerary(name, numOfDays);
            appUI.showDashboard();
        });

        buttonPanel.add(loadButton);
        buttonPanel.add(newButton);
        JPanel wrapperPanel = new JPanel(new FlowLayout());
        wrapperPanel.add(buttonPanel);
        add(wrapperPanel, BorderLayout.SOUTH);
    }

}

