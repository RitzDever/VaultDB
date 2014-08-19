package me.ritzdever.currency.database;

public class ConnectionReaper
        implements Runnable {
    private ConnectionManager connectionManager;

    public ConnectionReaper(ConnectionManager connectionManager) {
        this.connectionManager = connectionManager;
    }

    public void run() {
        try {
            while (true) {
                Thread.sleep(300000L);
                this.connectionManager.reap();
            }
        } catch (InterruptedException localInterruptedException) {
        }
    }
}