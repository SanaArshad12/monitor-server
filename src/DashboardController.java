import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class DashboardController {
    private JTextArea alertsArea;
    private DefaultTableModel tableModel;
    private JTable table;
    private boolean isDarkMode = false;

    public DashboardController() {
        // Initialize table model with columns
        tableModel = new DefaultTableModel(new Object[][]{}, new String[]{"Server Name", "Load", "Status", "CPU Usage", "Memory Usage"});
        table = new JTable(tableModel);
        table.setRowHeight(30);
        table.setDefaultRenderer(Object.class, new TableCellRenderer(this));
    }

    public JTable getServerTableView() {
        return table;
    }

    public JTextArea createAlertsTextArea() {
        alertsArea = new JTextArea();
        alertsArea.setEditable(false);
        alertsArea.setLineWrap(true);
        alertsArea.setWrapStyleWord(true);
        return alertsArea;
    }

    public void updateDisplay(Server server) {
        Object[] row = {server.getName(), server.getLoad(), server.isAvailable() ? "Available" : "Unavailable",
                server.getCpuUsage(), server.getMemoryUsage()};
        tableModel.addRow(row);
    }

    public void showAlert(String message) {
        String timestamp = new java.text.SimpleDateFormat("HH:mm:ss").format(new java.util.Date());
        alertsArea.append("[" + timestamp + "] " + message + "\n");
    }

    public JPanel createControlPanel(ServerMonitor monitor) {
        JPanel panel = new JPanel();
        JButton toggleButton = new JButton("Pause Monitoring");

        toggleButton.addActionListener(e -> {
            if (monitor.isMonitoring()) {
                monitor.stopMonitoring();
                toggleButton.setText("Resume Monitoring");
            } else {
                monitor.startMonitoring();
                toggleButton.setText("Pause Monitoring");
            }
        });

        JCheckBox darkModeToggle = new JCheckBox("Dark Mode");
        darkModeToggle.addItemListener(e -> {
            isDarkMode = darkModeToggle.isSelected();
            table.repaint(); // Refresh table to apply new colors
        });

        panel.add(toggleButton);
        panel.add(darkModeToggle);
        return panel;
    }

    public boolean isDarkMode() {
        return isDarkMode;
    }
}
