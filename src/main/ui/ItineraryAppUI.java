package ui;

import javax.swing.*;
import java.awt.*;

// code inspired by AlarmController.java
// Main class - represents application's main window frame
public class ItineraryAppUI {
    private JFrame frame;
    private CardLayout cardLayout;
    private InitialPanel initialPanel;
    private DashboardPanel dashboardPanel;
    private ItineraryController controller;

    // EFFECTS: Initializes the UI and its controller
    public ItineraryAppUI() {
        controller = new ItineraryController();
        initializeUI();
    }

    //EFFECT: sets up the frame and the initial screen and dashboard
    private void initializeUI() {
        frame = new JFrame("Itinerary Application");
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setSize(800, 600);

        cardLayout = new CardLayout();
        frame.setLayout(cardLayout);

        initialPanel = new InitialPanel(this, controller);
        dashboardPanel = new DashboardPanel(this, controller);
        controller.setDashboardPanel(dashboardPanel);

        frame.add(initialPanel, "InitialPage");
        frame.add(dashboardPanel, "Dashboard");

        frame.setVisible(true);

    }

    //EFFECT: Displays Dashboard Pane
    public void showDashboard() {
        cardLayout.show(frame.getContentPane(), "Dashboard");
    }

    //EFFECT: Displays Initial Pane
    public void showInitial() {
        cardLayout.show(frame.getContentPane(),"InitialPage");
    }

}
