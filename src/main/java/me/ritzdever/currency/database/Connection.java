package me.ritzdever.currency.database;

import java.sql.Array;
import java.sql.Blob;
import java.sql.CallableStatement;
import java.sql.Clob;
import java.sql.DatabaseMetaData;
import java.sql.NClob;
import java.sql.PreparedStatement;
import java.sql.SQLClientInfoException;
import java.sql.SQLException;
import java.sql.SQLWarning;
import java.sql.SQLXML;
import java.sql.Savepoint;
import java.sql.Statement;
import java.sql.Struct;
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.Executor;

public class Connection
        implements java.sql.Connection {
    private static final long timeToLive = 300000L;
    private java.sql.Connection connection;
    private Thread inuse;
    private long lastuse;

    public Connection(java.sql.Connection connection) {
        this.connection = connection;
        this.inuse = null;
        this.lastuse = 0L;
    }

    public synchronized boolean lease() {
        if (this.inuse != null) {
            return false;
        }
        this.inuse = Thread.currentThread();
        this.lastuse = System.currentTimeMillis();
        return true;
    }

    public synchronized boolean release() {
        if (this.inuse != Thread.currentThread()) {
            throw new IllegalStateException("Not yet leased");
        }
        this.inuse = null;
        return true;
    }

    public void terminate() {
        try {
            close();
        } catch (SQLException localSQLException) {
        }
    }

    public boolean isValid() {
        if (System.currentTimeMillis() - this.lastuse > 300000L)
            return false;
        try {
            return isValid(1);
        } catch (SQLException exception) {
        }
        return false;
    }

    public void commit() throws SQLException {
        this.connection.commit();
    }

    public Array createArrayOf(String typeName, Object[] elements) throws SQLException {
        return this.connection.createArrayOf(typeName, elements);
    }

    public Blob createBlob() throws SQLException {
        return this.connection.createBlob();
    }

    public Clob createClob() throws SQLException {
        return this.connection.createClob();
    }

    public NClob createNClob() throws SQLException {
        return this.connection.createNClob();
    }

    public SQLXML createSQLXML() throws SQLException {
        return this.connection.createSQLXML();
    }

    public Statement createStatement() throws SQLException {
        return this.connection.createStatement();
    }

    public Statement createStatement(int resultSetType, int resultSetConcurrency) throws SQLException {
        return this.connection.createStatement(resultSetType, resultSetConcurrency);
    }

    public Statement createStatement(int resultSetType, int resultSetConcurrency, int resultSetHoldability) throws SQLException {
        return this.connection.createStatement(resultSetType, resultSetConcurrency, resultSetHoldability);
    }

    public Struct createStruct(String typeName, Object[] attributes) throws SQLException {
        return this.connection.createStruct(typeName, attributes);
    }

    public boolean getAutoCommit() throws SQLException {
        return this.connection.getAutoCommit();
    }

    public String getCatalog() throws SQLException {
        return this.connection.getCatalog();
    }

    public Properties getClientInfo() throws SQLException {
        return this.connection.getClientInfo();
    }

    public String getClientInfo(String name) throws SQLException {
        return this.connection.getClientInfo(name);
    }

    public int getHoldability() throws SQLException {
        return this.connection.getHoldability();
    }

    public DatabaseMetaData getMetaData() throws SQLException {
        return this.connection.getMetaData();
    }

    public int getTransactionIsolation() throws SQLException {
        return this.connection.getTransactionIsolation();
    }

    public Map<String, Class<?>> getTypeMap() throws SQLException {
        return this.connection.getTypeMap();
    }

    public SQLWarning getWarnings() throws SQLException {
        return this.connection.getWarnings();
    }

    public boolean isClosed() throws SQLException {
        return this.connection.isClosed();
    }

    public boolean isReadOnly() throws SQLException {
        return this.connection.isReadOnly();
    }

    public boolean isValid(int timeout) throws SQLException {
        return this.connection.isValid(timeout);
    }

    public boolean isWrapperFor(Class<?> iface) throws SQLException {
        return this.connection.isWrapperFor(iface);
    }

    public String nativeSQL(String sql) throws SQLException {
        return this.connection.nativeSQL(sql);
    }

    public CallableStatement prepareCall(String sql) throws SQLException {
        return this.connection.prepareCall(sql);
    }

    public CallableStatement prepareCall(String sql, int resultSetType, int resultSetConcurrency) throws SQLException {
        return this.connection.prepareCall(sql, resultSetType, resultSetConcurrency);
    }

    public CallableStatement prepareCall(String sql, int resultSetType, int resultSetConcurrency, int resultSetHoldability) throws SQLException {
        return this.connection.prepareCall(sql, resultSetType, resultSetConcurrency, resultSetHoldability);
    }

    public PreparedStatement prepareStatement(String sql) throws SQLException {
        return this.connection.prepareStatement(sql);
    }

    public PreparedStatement prepareStatement(String sql, int autoGeneratedKeys) throws SQLException {
        return this.connection.prepareStatement(sql, autoGeneratedKeys);
    }

    public PreparedStatement prepareStatement(String sql, int resultSetType, int resultSetConcurrency) throws SQLException {
        return this.connection.prepareStatement(sql, resultSetType, resultSetConcurrency);
    }

    public PreparedStatement prepareStatement(String sql, int resultSetType, int resultSetConcurrency, int resultSetHoldability) throws SQLException {
        return this.connection.prepareStatement(sql, resultSetType, resultSetConcurrency, resultSetHoldability);
    }

    public PreparedStatement prepareStatement(String sql, int[] columnIndexes) throws SQLException {
        return this.connection.prepareStatement(sql, columnIndexes);
    }

    public PreparedStatement prepareStatement(String sql, String[] columnNames) throws SQLException {
        return this.connection.prepareStatement(sql, columnNames);
    }

    public void releaseSavepoint(Savepoint savepoint) throws SQLException {
        this.connection.releaseSavepoint(savepoint);
    }

    public void rollback() throws SQLException {
        this.connection.rollback();
    }

    public void rollback(Savepoint savepoint) throws SQLException {
        this.connection.rollback(savepoint);
    }

    public void setAutoCommit(boolean autoCommit) throws SQLException {
        this.connection.setAutoCommit(autoCommit);
    }

    public void setCatalog(String catalog) throws SQLException {
        this.connection.setCatalog(catalog);
    }

    public void setClientInfo(Properties properties) throws SQLClientInfoException {
        this.connection.setClientInfo(properties);
    }

    public void setClientInfo(String name, String value) throws SQLClientInfoException {
        this.connection.setClientInfo(name, value);
    }

    public void setHoldability(int holdability) throws SQLException {
        this.connection.setHoldability(holdability);
    }

    public void setReadOnly(boolean readOnly) throws SQLException {
        this.connection.setReadOnly(readOnly);
    }

    public Savepoint setSavepoint() throws SQLException {
        return this.connection.setSavepoint();
    }

    public Savepoint setSavepoint(String name) throws SQLException {
        return this.connection.setSavepoint(name);
    }

    public void setTransactionIsolation(int level) throws SQLException {
        this.connection.setTransactionIsolation(level);
    }

    public void setTypeMap(Map<String, Class<?>> map) throws SQLException {
        this.connection.setTypeMap(map);
    }

    public <T> T unwrap(Class<T> iface) throws SQLException {
        return this.connection.unwrap(iface);
    }

    public void clearWarnings() throws SQLException {
        this.connection.clearWarnings();
    }

    public void close() throws SQLException {
        this.connection.close();
    }

    public void abort(Executor arg0) throws SQLException {
        this.connection.abort(arg0);
    }

    public int getNetworkTimeout() throws SQLException {
        return this.connection.getNetworkTimeout();
    }

    public String getSchema() throws SQLException {
        return this.connection.getSchema();
    }

    public void setNetworkTimeout(Executor arg0, int arg1) throws SQLException {
        this.connection.setNetworkTimeout(arg0, arg1);
    }

    public void setSchema(String arg0) throws SQLException {
        this.connection.setSchema(arg0);
    }
}