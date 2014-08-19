package me.ritzdever.currency;

import java.text.DecimalFormat;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import me.ritzdever.currency.Currency;
import me.ritzdever.currency.account.AccountCreationListener;
import me.ritzdever.currency.account.AccountManager;
import me.ritzdever.currency.database.Database;
import net.milkbowl.vault.economy.Economy;
import org.bukkit.ChatColor;
import org.bukkit.plugin.ServicePriority;
import org.bukkit.plugin.java.JavaPlugin;

public class CurrencyPlugin extends JavaPlugin implements Currency {

    private static final DecimalFormat decimalFormat = new DecimalFormat("#,###.##");
    private Database database;
    private AccountManager accountManager;
    private String format;
    private static long age;

    public void onLoad() {
        super.getConfig().options().copyDefaults(true);
        super.saveConfig();
        super.reloadConfig();
    }

    public void onEnable() {
        try {
            this.database = new Database(super.getConfig().getString("database.address"), super.getConfig().getString("database.username"), super.getConfig().getString("database.password"), super.getConfig().getString("database.name"), super.getConfig().getString("database.table"));
        } catch (Exception exception) {
            exception.printStackTrace();
            return;
        }
        this.accountManager = new AccountManager(this.database);
        this.format = ChatColor.translateAlternateColorCodes('&', super.getConfig().getString("format"));
        this.age = lifeToSeconds(getConfig().getString("prune-age")) * 1000;
        super.getServer().getPluginManager().registerEvents(new AccountCreationListener(this.accountManager, super.getConfig().getDouble("initial-balance")), this);
        super.getServer().getServicesManager().register(Currency.class, this, this, ServicePriority.Normal);
        super.getServer().getServicesManager().register(Economy.class, new CurrencyVault(this), this, ServicePriority.High);
    }

    public void onDisable() {
        try {
            if (this.database != null)
                this.database.close();
        } catch (Exception exception) {
            exception.printStackTrace();
        } finally {
            this.database = null;
            this.accountManager = null;
            this.format = null;
        }
    }

    public AccountManager getAccountManager() {
        return this.accountManager;
    }

    public String format(double currency) {
        return this.format.replace("{}", decimalFormat.format(currency));
    }

    public static long getAge() {
        return age;
    }

    private long lifeToSeconds(String string) {
        if (string.equals("0") || string.equals("")) return 0;
        String[] lifeMatch = new String[]{ "d", "h", "m", "s" };
        int[] lifeInterval = new int[]{ 86400, 3600, 60, 1 };
        long seconds = 0L;

        for (int i=0;i<lifeMatch.length;i++) {
            Matcher matcher = Pattern.compile("([0-9]*)" + lifeMatch[i]).matcher(string);
            while (matcher.find()) {
                seconds += Integer.parseInt(matcher.group(1)) * lifeInterval[i];
            }

        }
        return seconds;
    }
}