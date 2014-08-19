package me.ritzdever.currency;


import me.ritzdever.currency.account.AccountManager;

public abstract interface Currency {
    public abstract AccountManager getAccountManager();

    public abstract String format(double paramDouble);
}