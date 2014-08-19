package me.ritzdever.currency.database;



import me.ritzdever.currency.CurrencyPlugin;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class AccountModel {
    public static boolean createAccount(Database database, String account, double currency) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            connection = database.getConnection();
            preparedStatement = connection.prepareStatement("INSERT INTO " + database.getTable() + " (account, balance, last_update) VALUES (?, ?, ?);");
            preparedStatement.setString(1, account);
            preparedStatement.setDouble(2, currency);
            preparedStatement.setLong(3, System.currentTimeMillis());
            return preparedStatement.executeUpdate() > 0;
        } catch (Exception exception) {
            exception.printStackTrace();
        } finally {
            if (connection != null) {
                connection.release();
            }
            if (preparedStatement != null)
                try {
                    preparedStatement.close();
                } catch (Exception localException3) {
                }
        }
        return false;
    }

    public static boolean deleteAccount(Database database, String account) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            connection = database.getConnection();
            preparedStatement = connection.prepareStatement("DELETE FROM " + database.getTable() + " WHERE account = ?");
            preparedStatement.setString(1, account);
            return preparedStatement.executeUpdate() > 0;
        } catch (Exception exception) {
            exception.printStackTrace();
        } finally {
            if (connection != null) {
                connection.release();
            }
            if (preparedStatement != null)
                try {
                    preparedStatement.close();
                } catch (Exception localException3) {
                }
        }
        return false;
    }

    public static boolean hasAccount(Database database, String account) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            connection = database.getConnection();
            preparedStatement = connection.prepareStatement("SELECT COUNT(*) as count FROM " + database.getTable() + " WHERE account = ?");
            preparedStatement.setString(1, account);
            resultSet = preparedStatement.executeQuery();
            if (resultSet.next())
                return resultSet.getInt("count") > 0;
        } catch (Exception exception) {
            exception.printStackTrace();
        } finally {
            if (connection != null) {
                connection.release();
            }
            if (preparedStatement != null)
                try {
                    preparedStatement.close();
                } catch (Exception localException5) {
                }
            if (resultSet != null)
                try {
                    resultSet.close();
                } catch (Exception localException6) {
                }
        }
        if (connection != null) {
            connection.release();
        }
        if (preparedStatement != null)
            try {
                preparedStatement.close();
            } catch (Exception localException7) {
            }
        if (resultSet != null) {
            try {
                resultSet.close();
            } catch (Exception localException8) {
            }
        }
        return false;
    }

    public static double getAccountBalance(Database database, String account) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            connection = database.getConnection();
            preparedStatement = connection.prepareStatement("SELECT balance FROM " + database.getTable() + " WHERE account = ?");
            preparedStatement.setString(1, account);
            resultSet = preparedStatement.executeQuery();
            if (resultSet.next())
                return resultSet.getDouble("balance");
        } catch (Exception exception) {
            exception.printStackTrace();
        } finally {
            if (connection != null) {
                connection.release();
            }
            if (preparedStatement != null)
                try {
                    preparedStatement.close();
                } catch (Exception localException5) {
                }
            if (resultSet != null)
                try {
                    resultSet.close();
                } catch (Exception localException6) {
                }
        }
        if (connection != null) {
            connection.release();
        }
        if (preparedStatement != null)
            try {
                preparedStatement.close();
            } catch (Exception localException7) {
            }
        if (resultSet != null) {
            try {
                resultSet.close();
            } catch (Exception localException8) {
            }
        }
        return 0.0D;
    }

    public static boolean setAccountBalance(Database database, String account, double currency) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            connection = database.getConnection();
            preparedStatement = connection.prepareStatement("UPDATE " + database.getTable() + " SET balance = ?, last_update = ? WHERE account = ?");
            preparedStatement.setDouble(1, currency);
            preparedStatement.setLong(2, System.currentTimeMillis());
            preparedStatement.setString(3, account);
            return preparedStatement.executeUpdate() > 0;
        } catch (Exception exception) {
            exception.printStackTrace();
        } finally {
            if (connection != null) {
                connection.release();
            }
            if (preparedStatement != null)
                try {
                    preparedStatement.close();
                } catch (Exception localException3) {
                }
        }
        return false;
    }

    public static boolean addAccountBalance(Database database, String account, double currency) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            connection = database.getConnection();
            preparedStatement = connection.prepareStatement("UPDATE " + database.getTable() + " SET balance = balance + ?, last_update = ? WHERE account = ?");
            preparedStatement.setDouble(1, currency);
            preparedStatement.setLong(2, System.currentTimeMillis());
            preparedStatement.setString(3, account);
            return preparedStatement.executeUpdate() > 0;
        } catch (Exception exception) {
            exception.printStackTrace();
        } finally {
            if (connection != null) {
                connection.release();
            }
            if (preparedStatement != null)
                try {
                    preparedStatement.close();
                } catch (Exception localException3) {
                }
        }
        return false;
    }

    public static boolean subtractAccountBalance(Database database, String account, double currency) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            connection = database.getConnection();
            preparedStatement = connection.prepareStatement("UPDATE " + database.getTable() + " SET balance = balance - ?, last_update = ? WHERE account = ?");
            preparedStatement.setDouble(1, currency);
            preparedStatement.setLong(2, System.currentTimeMillis());
            preparedStatement.setString(3, account);
            return preparedStatement.executeUpdate() > 0;
        } catch (Exception exception) {
            exception.printStackTrace();
        } finally {
            if (connection != null) {
                connection.release();
            }
            if (preparedStatement != null)
                try {
                    preparedStatement.close();
                } catch (Exception localException3) {
                }
        }
        return false;
    }

    public static double getBalance(Database database) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            connection = database.getConnection();
            preparedStatement = connection.prepareStatement("SELECT SUM(balance) as balance FROM " + database.getTable());
            resultSet = preparedStatement.executeQuery();
            if (resultSet.next())
                return resultSet.getDouble("balance");
        } catch (Exception exception) {
            exception.printStackTrace();
        } finally {
            if (connection != null) {
                connection.release();
            }
            if (preparedStatement != null)
                try {
                    preparedStatement.close();
                } catch (Exception localException5) {
                }
            if (resultSet != null)
                try {
                    resultSet.close();
                } catch (Exception localException6) {
                }
        }
        if (connection != null) {
            connection.release();
        }
        if (preparedStatement != null)
            try {
                preparedStatement.close();
            } catch (Exception localException7) {
            }
        if (resultSet != null) {
            try {
                resultSet.close();
            } catch (Exception localException8) {
            }
        }
        return 0.0D;
    }

    public static List<String> getAccounts(Database database) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            connection = database.getConnection();
            preparedStatement = connection.prepareStatement("SELECT account FROM " + database.getTable());
            resultSet = preparedStatement.executeQuery();
            List accounts = new ArrayList();
            int removed = 0;
            while (resultSet.next()) {
                long age = System.currentTimeMillis() - resultSet.getLong("last_update");
                if (age > CurrencyPlugin.getAge()) {
                    removed++;
                    deleteAccount(database, resultSet.getString("account"));
                } else {
                    accounts.add(resultSet.getString("account"));
                }
            }
            if (removed > 0) {
                System.out.println("Pruned " + removed + " old accounts due to age");
            }
            return accounts;
        } catch (Exception exception) {
            exception.printStackTrace();
        } finally {
            if (connection != null) {
                connection.release();
            }
            if (preparedStatement != null)
                try {
                    preparedStatement.close();
                } catch (Exception localException5) {
                }
            if (resultSet != null)
                try {
                    resultSet.close();
                } catch (Exception localException6) {
                }
        }
        return Collections.emptyList();
    }

    public static Map<String, Double> getAccountBalances(Database database) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            connection = database.getConnection();
            preparedStatement = connection.prepareStatement("SELECT account, balance FROM " + database.getTable());
            resultSet = preparedStatement.executeQuery();
            Map accounts = new HashMap();
            while (resultSet.next()) {
                accounts.put(resultSet.getString("account"), Double.valueOf(resultSet.getDouble("balance")));
            }
            return accounts;
        } catch (Exception exception) {
            exception.printStackTrace();
        } finally {
            if (connection != null) {
                connection.release();
            }
            if (preparedStatement != null)
                try {
                    preparedStatement.close();
                } catch (Exception localException5) {
                }
            if (resultSet != null)
                try {
                    resultSet.close();
                } catch (Exception localException6) {
                }
        }
        return Collections.emptyMap();
    }

    public static Map<String, Double> getTopAccountBalances(Database database, int offset, int count) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            connection = database.getConnection();
            preparedStatement = connection.prepareStatement("SELECT account, balance, last_update FROM " + database.getTable() + " ORDER BY balance DESC LIMIT " + offset + ", " + count);
            resultSet = preparedStatement.executeQuery();
            Map accounts = new LinkedHashMap();
            int removed = 0;
            while (resultSet.next()) {
                // Prune old results
                long age = System.currentTimeMillis() - resultSet.getLong("last_update");
                if (age > CurrencyPlugin.getAge()) {
                    removed++;
                    deleteAccount(database, resultSet.getString("account"));
                } else {
                    accounts.put(resultSet.getString("account"), Double.valueOf(resultSet.getDouble("balance")));
                }
            }
            return accounts;
        } catch (Exception exception) {
            exception.printStackTrace();
        } finally {
            if (connection != null) {
                connection.release();
            }
            if (preparedStatement != null)
                try {
                    preparedStatement.close();
                } catch (Exception localException5) {
                }
            if (resultSet != null)
                try {
                    resultSet.close();
                } catch (Exception localException6) {
                }
        }
        return Collections.emptyMap();
    }

    public static int getAccountCount(Database database) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            connection = database.getConnection();
            preparedStatement = connection.prepareStatement("SELECT COUNT(*) as count FROM " + database.getTable());
            resultSet = preparedStatement.executeQuery();
            if (resultSet.next())
                return resultSet.getInt("count");
        } catch (Exception exception) {
            exception.printStackTrace();
        } finally {
            if (connection != null) {
                connection.release();
            }
            if (preparedStatement != null)
                try {
                    preparedStatement.close();
                } catch (Exception localException5) {
                }
            if (resultSet != null)
                try {
                    resultSet.close();
                } catch (Exception localException6) {
                }
        }
        if (connection != null) {
            connection.release();
        }
        if (preparedStatement != null)
            try {
                preparedStatement.close();
            } catch (Exception localException7) {
            }
        if (resultSet != null) {
            try {
                resultSet.close();
            } catch (Exception localException8) {
            }
        }
        return 0;
    }
}