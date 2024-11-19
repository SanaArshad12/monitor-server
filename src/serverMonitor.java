import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

class ServerMonitor {
    private List<Server> servers;
    private DashboardController controller;
    private Timer timer;
    private boolean isMonitoring;

    public ServerMonitor(List<Server> servers, DashboardController controller) {
        this.servers = servers;
        this.controller = controller;
        this.isMonitoring = true;
    }

    public void startMonitoring() {
        if (timer != null) {
            timer.cancel();
        }
        timer = new Timer(true);
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                for (Server server : servers) {
                    server.updateStatus();
                    checkForAlerts(server);
                    controller.updateDisplay(server);
                }
            }
        }, 0, 5000); // Update every 5 seconds

        isMonitoring = true;
    }

    public void stopMonitoring() {
        if (timer != null) {
            timer.cancel();
        }
        isMonitoring = false;
    }

    private void checkForAlerts(Server server) {
        if (server.getLoad() > 0.8 || !server.isAvailable()) {
            controller.showAlert("Alert: " + server.getName() + " has issues!");
        }
    }

    public boolean isMonitoring() {
        return isMonitoring;
    }
}
