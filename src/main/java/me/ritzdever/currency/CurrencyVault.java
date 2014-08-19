package me.ritzdever.currency;



import java.util.ArrayList;
import java.util.List;

import me.ritzdever.currency.account.AccountManager;
import net.milkbowl.vault.economy.Economy;
import net.milkbowl.vault.economy.EconomyResponse;
import net.milkbowl.vault.economy.EconomyResponse.ResponseType;

public class CurrencyVault
        implements Economy {
    private static final String EMPTY_STRING = "";
    private Currency currency;

    public CurrencyVault(Currency currency) {
        this.currency = currency;
    }

    public boolean isEnabled() {
        return true;
    }

    public String getName() {
        return "ZawmgCurrency";
    }

    public String format(double amount) {
        return this.currency.format(amount);
    }

    public String currencyNamePlural() {
        return "";
    }

    public String currencyNameSingular() {
        return "";
    }

    public double getBalance(String playerName) {
        return this.currency.getAccountManager().getAccountBalance(playerName);
    }

    public boolean has(String playerName, double amount) {
        return this.currency.getAccountManager().hasAccountBalance(playerName, amount);
    }

    public EconomyResponse withdrawPlayer(String playerName, double amount) {
        if (amount < 0.0D) {
            return new EconomyResponse(0.0D, 0.0D, EconomyResponse.ResponseType.FAILURE, "Cannot withdraw negative funds");
        }
        AccountManager accountManager = this.currency.getAccountManager();
        if (!accountManager.hasAccount(playerName)) {
            return new EconomyResponse(0.0D, 0.0D, EconomyResponse.ResponseType.FAILURE, "That account does not exist");
        }
        double balance = accountManager.getAccountBalance(playerName);
        if (balance < amount) {
            return new EconomyResponse(0.0D, balance, EconomyResponse.ResponseType.FAILURE, "Insufficient funds");
        }
        this.currency.getAccountManager().subtractAccountBalance(playerName, amount);
        return new EconomyResponse(amount, balance - amount, EconomyResponse.ResponseType.SUCCESS, "");
    }

    public EconomyResponse depositPlayer(String playerName, double amount) {
        if (amount < 0.0D) {
            return new EconomyResponse(0.0D, 0.0D, EconomyResponse.ResponseType.FAILURE, "Cannot desposit negative funds");
        }
        AccountManager accountManager = this.currency.getAccountManager();
        if (!accountManager.hasAccount(playerName)) {
            return new EconomyResponse(0.0D, 0.0D, EconomyResponse.ResponseType.FAILURE, "That account does not exist");
        }
        accountManager.addAccountBalance(playerName, amount);
        return new EconomyResponse(amount, accountManager.getAccountBalance(playerName), EconomyResponse.ResponseType.SUCCESS, "");
    }

    public EconomyResponse createBank(String name, String player) {
        name = "(" + name + ")";
        AccountManager accountManager = this.currency.getAccountManager();
        if (!accountManager.hasAccount(name)) {
            return new EconomyResponse(0.0D, this.currency.getAccountManager().getAccountBalance(name), EconomyResponse.ResponseType.FAILURE, "That account already exists.");
        }
        if (accountManager.createAccount(name)) {
            return new EconomyResponse(0.0D, 0.0D, EconomyResponse.ResponseType.SUCCESS, "");
        }
        return new EconomyResponse(0.0D, 0.0D, EconomyResponse.ResponseType.FAILURE, "");
    }

    public EconomyResponse deleteBank(String name) {
        name = "(" + name + ")";
        AccountManager accountManager = this.currency.getAccountManager();
        if (!accountManager.hasAccount(name)) {
            return new EconomyResponse(0.0D, 0.0D, EconomyResponse.ResponseType.FAILURE, "That account does not exist!");
        }
        accountManager.deleteAccount(name);
        return new EconomyResponse(0.0D, 0.0D, EconomyResponse.ResponseType.SUCCESS, "");
    }

    public EconomyResponse bankBalance(String name) {
        name = "(" + name + ")";
        AccountManager accountManager = this.currency.getAccountManager();
        if (!accountManager.hasAccount(name)) {
            return new EconomyResponse(0.0D, 0.0D, EconomyResponse.ResponseType.FAILURE, "That account does not exists.");
        }
        return new EconomyResponse(0.0D, accountManager.getAccountBalance(name), EconomyResponse.ResponseType.SUCCESS, "");
    }

    public EconomyResponse bankHas(String name, double amount) {
        name = "(" + name + ")";
        AccountManager accountManager = this.currency.getAccountManager();
        if (!accountManager.hasAccount(name)) {
            return new EconomyResponse(0.0D, 0.0D, EconomyResponse.ResponseType.FAILURE, "That account does not exist!");
        }
        double balance = accountManager.getAccountBalance(name);
        if (balance < amount) {
            return new EconomyResponse(0.0D, balance, EconomyResponse.ResponseType.FAILURE, "That account does not have enough!");
        }
        return new EconomyResponse(0.0D, balance, EconomyResponse.ResponseType.SUCCESS, "");
    }

    public EconomyResponse bankWithdraw(String name, double amount) {
        name = "(" + name + ")";
        if (amount < 0.0D) {
            return new EconomyResponse(0.0D, 0.0D, EconomyResponse.ResponseType.FAILURE, "Cannot withdraw negative funds");
        }
        AccountManager accountManager = this.currency.getAccountManager();
        if (!accountManager.hasAccount(name)) {
            return new EconomyResponse(0.0D, 0.0D, EconomyResponse.ResponseType.FAILURE, "That account does not exist");
        }
        double balance = accountManager.getAccountBalance(name);
        if (balance < amount) {
            return new EconomyResponse(0.0D, balance, EconomyResponse.ResponseType.FAILURE, "Insufficient funds");
        }
        this.currency.getAccountManager().subtractAccountBalance(name, amount);
        return new EconomyResponse(amount, balance - amount, EconomyResponse.ResponseType.SUCCESS, "");
    }

    public EconomyResponse bankDeposit(String name, double amount) {
        name = "(" + name + ")";
        if (amount < 0.0D) {
            return new EconomyResponse(0.0D, 0.0D, EconomyResponse.ResponseType.FAILURE, "Cannot desposit negative funds");
        }
        AccountManager accountManager = this.currency.getAccountManager();
        if (!accountManager.hasAccount(name)) {
            return new EconomyResponse(0.0D, 0.0D, EconomyResponse.ResponseType.FAILURE, "That account does not exist");
        }
        accountManager.addAccountBalance(name, amount);
        return new EconomyResponse(amount, accountManager.getAccountBalance(name), EconomyResponse.ResponseType.SUCCESS, "");
    }

    public EconomyResponse isBankOwner(String name, String playerName) {
        return new EconomyResponse(0.0D, 0.0D, EconomyResponse.ResponseType.NOT_IMPLEMENTED, getName() + " does not support bank owners.");
    }

    public EconomyResponse isBankMember(String name, String playerName) {
        return new EconomyResponse(0.0D, 0.0D, EconomyResponse.ResponseType.NOT_IMPLEMENTED, getName() + " does not support bank members.");
    }

    public List<String> getBanks() {
        List<String> accounts = this.currency.getAccountManager().getAccounts();
        List<String> banks = new ArrayList<String>();
        for (String account : accounts)
            if (account.startsWith("(")) {
                banks.add(account.substring(1, account.length() - 2));
            }
        return banks;
    }

    public boolean hasBankSupport() {
        return true;
    }

    public boolean hasAccount(String playerName) {
        return this.currency.getAccountManager().hasAccount(playerName);
    }

    public boolean createPlayerAccount(String playerName) {
        AccountManager accountManager = this.currency.getAccountManager();
        if (accountManager.hasAccount(playerName)) {
            return false;
        }
        return accountManager.createAccount(playerName);
    }

    public int fractionalDigits() {
        return -1;
    }

    public boolean hasAccount(String playerName, String worldName) {
        return hasAccount(playerName);
    }

    public double getBalance(String playerName, String world) {
        return getBalance(playerName);
    }

    public boolean has(String playerName, String worldName, double amount) {
        return has(playerName, amount);
    }

    public EconomyResponse withdrawPlayer(String playerName, String worldName, double amount) {
        return withdrawPlayer(playerName, amount);
    }

    public EconomyResponse depositPlayer(String playerName, String worldName, double amount) {
        return depositPlayer(playerName, amount);
    }

    public boolean createPlayerAccount(String playerName, String worldName) {
        return createPlayerAccount(playerName);
    }
}