package net.imperialmc.TokenShop;

//import java.util.logging.Level;

import net.imperialmc.TokenShop.CommandManager.TShopCommand;
import net.imperialmc.TokenShop.CommandManager.TokensCommand;
import net.imperialmc.TokenShop.ShopManager.GUIListener;
import net.imperialmc.TokenShop.ShopManager.GUIManager;
//import net.minecraft.util.io.netty.util.ResourceLeakDetector;

import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin implements Listener {

  // TODO: [Things to do]

  private static Main plugin;
  private static GUIManager gui;

  public void onEnable() {

    this.saveDefaultConfig();
    //ResourceLeakDetector.setlevel(Level.DISABLED); // This is for netty
                                                   // optimizations
    plugin = this;
    gui = new GUIManager();

    registerEvents(); // Registered events defined below
    registerCommands(); // Registers commands defined below

  }

  public void onDisable() {
    TokenManager.saveHashMap();
  }

  // MASTER PLUGIN
  public static Main pl() {
    return plugin;
  }

  public void registerEvents() {

    new TokenManager(this);
    new GUIListener(this);

  }

  public void registerCommands() {

    new TShopCommand(this);
    new TokensCommand(this);

  }
  
  public GUIManager getGUI() {
    return gui;
  }

  public static Main getPlugin() {
    return plugin;
  }

}
