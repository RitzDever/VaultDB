package me.ritzdever.currency.database;

import java.io.Closeable;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Enumeration;
import java.util.Vector;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ConnectionManager
        implements Closeable {
    private static final int poolsize = 10;
    private String url;
    private String user;
    private String pass;
    private Lock lock;
    private Vector<Connection> connections;
    private Thread reaper;

    public ConnectionManager(String mysqlAddress, String mysqlUsername, String mysqlPassword, String mysqlDatabase)
            throws Exception {
        this("jdbc:mysql://" + mysqlAddress + "/" + mysqlDatabase, mysqlUsername, mysqlPassword);
    }

    public ConnectionManager(String url, String user, String pass) throws ClassNotFoundException {

        Class.forName("com.mysql.jdbc.Driver");
        this.url = url;
        this.user = user;
        this.pass = pass;
        this.lock = new ReentrantLock();
        this.connections = new Vector(10);
        this.reaper = new Thread(new ConnectionReaper(this));
        this.reaper.setName("data-connection-reaper");
        this.reaper.start();
    }

    public Connection getConnection() throws SQLException {
        this.lock.lock();
        try {
            Connection localConnection1;
            for (int i = 0; i < this.connections.size(); i++) {
                Connection connection = (Connection) this.connections.get(i);
                if (connection.lease()) {
                    if (connection.isValid()) {
                        return connection;
                    }
                    connection.terminate();
                    this.connections.remove(connection);
                }
            }
            Connection connection = new Connection(DriverManager.getConnection(this.url, this.user, this.pass));
            connection.lease();
            if (!connection.isValid()) {
                connection.terminate();
                throw new SQLException("Could not create new connection");
            }
            this.connections.add(connection);
            return connection;
        } finally {
            this.lock.unlock();
        }
    }

    public void reap() {
        this.lock.lock();
        try {
            Enumeration connections = this.connections.elements();
            while (connections.hasMoreElements()) {
                Connection connection = (Connection) connections.nextElement();
                if (!connection.isValid()) {
                    connection.terminate();
                    this.connections.remove(connection);
                }
            }
        } finally {
            this.lock.unlock();
        }
    }

    public void close() {
        if (this.reaper.isAlive()) {
            this.reaper.interrupt();
        }
        this.lock.lock();
        try {
            Enumeration connections = this.connections.elements();
            while (connections.hasMoreElements()) {
                Connection connection = (Connection) connections.nextElement();
                connection.terminate();
                this.connections.remove(connection);
            }
        } finally {
            this.lock.unlock();
        }
    }
}