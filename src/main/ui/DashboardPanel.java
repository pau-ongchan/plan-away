package ui;

import javax.swing.*;
import java.awt.*;

// represents the dashboard panel
public class DashboardPanel extends JPanel {
    private JTextArea itineraryDisplay;
    private ItineraryAppUI itineraryAppUI;

    // EFFECTS: Initializes the dashboard menu and display
    public DashboardPanel(ItineraryAppUI appUI, ItineraryController controller) {
        setLayout(new BorderLayout());
        initializeButtonPanel(controller);
        initializeDisplayPanel();
        itineraryAppUI = appUI;
    }

    //EFFECTS: Initializes and adds the button panel
    private void initializeButtonPanel(ItineraryController controller) {
        JPanel buttonPanel = createButtonPanel();
        addButtonListeners(buttonPanel, controller); // Add action listeners to buttons
        add(buttonPanel, BorderLayout.WEST);
    }

    // EFFECTS: Creates the buttons in the panel
    private JPanel createButtonPanel() {
        // Creates the panel for buttons with a GridLayout
        JPanel buttonPanel = new JPanel(new GridLayout(5, 1));
        buttonPanel.add(new JButton("Add Plan"));
        buttonPanel.add(new JButton("Delete Plan"));
        buttonPanel.add(new JButton("Reset Itinerary"));
        buttonPanel.add(new JButton("Save Itinerary"));
        ImageIcon backButtonIcon = new ImageIcon("./data/logo.png");
        Image backButtonImage = backButtonIcon.getImage();
        Image scaledBackButtonImage = backButtonImage.getScaledInstance(100, 100, Image.SCALE_SMOOTH);
        ImageIcon scaledBackButton = new ImageIcon(scaledBackButtonImage);
        buttonPanel.add(new JButton(scaledBackButton));
        return buttonPanel;
    }

    // EFFECTS: initializes action listeners for each buttons
    private void addButtonListeners(JPanel buttonPanel, ItineraryController controller) {
        Component[] components = buttonPanel.getComponents();

        JButton addButton = (JButton) components[0];
        JButton deleteButton = (JButton) components[1];
        JButton resetButton = (JButton) components[2];
        JButton saveButton = (JButton) components[3];
        JButton homeButton = (JButton) components [4];

        homeButton.addActionListener(e -> {
            itineraryAppUI.showInitial();
        });

        addButton.addActionListener(e -> {
            String location = JOptionPane.showInputDialog(this, "Enter Location: ");
            int day = Integer.parseInt(JOptionPane.showInputDialog(this, "Enter Day: "));
            String description = JOptionPane.showInputDialog(this, "Enter Description: ");
            JOptionPane.showMessageDialog(this, location + " added.");
            controller.addPlan(location, day, description);
        });

        deleteButton.addActionListener(e -> {
            String location = JOptionPane.showInputDialog(this, "Enter Location: ");
            controller.deletePlan(location);
        });

        resetButton.addActionListener(e -> controller.resetItinerary());
        saveButton.addActionListener(e -> controller.saveItinerary());
    }

    // EFFECTS: Initializes and adds the display panel for the itinerary
    private void initializeDisplayPanel() {
        itineraryDisplay = new JTextArea();
        add(new JScrollPane(itineraryDisplay), BorderLayout.CENTER);
    }

    // MODIFIES: this
    // EFFECTS: updates the itinerary display
    public void updateItineraryDisplay(String itineraryInfo) {
        itineraryDisplay.setText(itineraryInfo);
        itineraryDisplay.repaint();
    }
}
