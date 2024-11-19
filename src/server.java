class Server {
    private String name;
    private double load;
    private boolean available;
    private double cpuUsage;
    private double memoryUsage;

    public Server(String name) {
        this.name = name;
        updateStatus(); // Initialize with random values
    }

    public String getName() {
        return name;
    }

    public double getLoad() {
        return load;
    }

    public boolean isAvailable() {
        return available;
    }

    public double getCpuUsage() {
        return cpuUsage;
    }

    public double getMemoryUsage() {
        return memoryUsage;
    }

    public void updateStatus() {
        this.load = Math.random();
        this.available = Math.random() > 0.1;
        this.cpuUsage = Math.random() * 100;
        this.memoryUsage = Math.random() * 100;
    }
}
