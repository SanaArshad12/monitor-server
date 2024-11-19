import javax.swing.*;
import java.awt.*;
import java.util.Arrays;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        // Create the DashboardController and JFrame to display the GUI
        DashboardController controller = new DashboardController();

        // Server list
        List<Server> servers = Arrays.asList(
                new Server("Server A"),
                new Server("Server B"),
                new Server("Server C")
        );

        // Create ServerMonitor
        ServerMonitor monitor = new ServerMonitor(servers, controller);
        monitor.startMonitoring();

        // Create the main frame
        JFrame frame = new JFrame("Server Monitoring Dashboard");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1000, 700);
        frame.setLayout(new BorderLayout());

        // Create and add the server status table and alerts area
        JTable table = controller.getServerTableView();
        JScrollPane tableScroll = new JScrollPane(table);

        JTextArea alertsArea = controller.createAlertsTextArea();
        JScrollPane alertsScroll = new JScrollPane(alertsArea);

        JPanel controlPanel = controller.createControlPanel(monitor);

        // Add table, alerts, and control panel to the frame
        frame.add(tableScroll, BorderLayout.CENTER);
        frame.add(alertsScroll, BorderLayout.SOUTH);
        frame.add(controlPanel, BorderLayout.NORTH);

        // Display the window
        frame.setVisible(true);
    }
}
