package me.ritzdever.currency.database;

import java.sql.Statement;

public class Database {
    private ConnectionManager connectionManager;
    private String table;

    public Database(String address, String username, String password, String name, String table)
            throws Exception {
        this.connectionManager = new ConnectionManager(address, username, password, name);
        connectionManager.getConnection();

        this.table = table;



    }

    public void close() {
        this.connectionManager.close();
    }

    public Connection getConnection() throws Exception {
        return this.connectionManager.getConnection();
    }

    public String getTable() {
        return this.table;
    }
}