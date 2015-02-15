package net.imperialmc.TokenShop;

import net.imperialmc.TokenShop.CommandManager.TShopCommand;
import net.imperialmc.TokenShop.CommandManager.TokensCommand;

import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin implements Listener {

    //TODO: [Things to do]

    private static Main pl;

    public void onEnable() {

        saveDefaultConfig();

        pl = this;

        registerEvents(); // Registered events defined below
        registerCommands(); // Registers commands defined below

    }

    public void onDisable() {

        TokenManager.saveHashMap();

        pl = null; // Stop Memory Leaks, put everything above else you can cause errors on disabling!

        // PUT NOTHING HERE

    }

    // MASTER PLUGIN
    public static Main pl() {
        return pl;
    }

    public void registerEvents() {

        new TokenManager(this);

    }

    public void registerCommands() {

        new TShopCommand(this);
        new TokensCommand(this);

    }

}
