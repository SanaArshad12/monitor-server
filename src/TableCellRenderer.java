import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import java.awt.*;

public class TableCellRenderer extends DefaultTableCellRenderer {
    private final DashboardController controller;

    public TableCellRenderer(DashboardController controller) {
        this.controller = controller;
    }

    @Override
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
        Component comp = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);

        if (controller.isDarkMode()) {
            comp.setBackground(Color.DARK_GRAY);
            comp.setForeground(Color.WHITE);
        } else {
            comp.setBackground(Color.WHITE);
            comp.setForeground(Color.BLACK);
        }

        // Color code based on server status
        if (column == 2) { // Status column
            String status = (String) value;
            if ("Available".equals(status)) {
                comp.setBackground(controller.isDarkMode() ? new Color(0, 100, 0) : Color.GREEN);
            } else {
                comp.setBackground(controller.isDarkMode() ? new Color(100, 0, 0) : Color.RED);
            }
        } else if (column == 1) { // Load column
            double load = (double) value;
            if (load < 0.3) comp.setBackground(controller.isDarkMode() ? new Color(34, 139, 34) : new Color(144, 238, 144));
            else if (load < 0.6) comp.setBackground(Color.YELLOW);
            else comp.setBackground(controller.isDarkMode() ? new Color(139, 0, 0) : new Color(255, 102, 102));
        }

        return comp;
    }
}
