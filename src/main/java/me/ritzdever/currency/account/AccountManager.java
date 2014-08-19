package me.ritzdever.currency.account;



import me.ritzdever.currency.database.AccountModel;
import me.ritzdever.currency.database.Database;

import java.util.List;
import java.util.Map;

public class AccountManager {
    private Database database;

    public AccountManager(Database database) {
        this.database = database;
    }

    public boolean createAccount(String account) {
        return createAccount(account, 0.0D);
    }

    public boolean createAccount(String account, double currency) {
        return AccountModel.createAccount(this.database, account, currency);
    }

    public boolean deleteAccount(String account) {
        return AccountModel.deleteAccount(this.database, account);
    }

    public boolean hasAccount(String account) {
        return AccountModel.hasAccount(this.database, account);
    }

    public double getAccountBalance(String account) {
        return AccountModel.getAccountBalance(this.database, account);
    }

    public boolean setAccountBalance(String account, double currency) {
        return AccountModel.setAccountBalance(this.database, account, currency);
    }

    public boolean addAccountBalance(String account, double currency) {
        return AccountModel.addAccountBalance(this.database, account, currency);
    }

    public boolean subtractAccountBalance(String account, double currency) {
        return AccountModel.subtractAccountBalance(this.database, account, currency);
    }

    public boolean hasAccountBalance(String account, double currency) {
        return getAccountBalance(account) >= currency;
    }

    public double getBalance() {
        return AccountModel.getBalance(this.database);
    }

    public List<String> getAccounts() {
        return AccountModel.getAccounts(this.database);
    }

    public Map<String, Double> getAccountBalances() {
        return AccountModel.getAccountBalances(this.database);
    }

    public Map<String, Double> getTopAccountBalances(int offset, int count) {
        return AccountModel.getTopAccountBalances(this.database, offset, count);
    }

    public int getAccountCount() {
        return AccountModel.getAccountCount(this.database);
    }
}