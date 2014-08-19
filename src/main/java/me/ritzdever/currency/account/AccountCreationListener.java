package me.ritzdever.currency.account;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class AccountCreationListener
        implements Listener {
    private AccountManager accountManager;
    private double initialBalance;

    public AccountCreationListener(AccountManager accountManager, double initialBalance) {
        this.accountManager = accountManager;
        this.initialBalance = initialBalance;
    }

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {
        String account = event.getPlayer().getName();
        if (!this.accountManager.hasAccount(account))
            this.accountManager.createAccount(account, this.initialBalance);
    }
}