package net.imperialmc.TokenShop;

import net.imperialmc.TokenShop.CommandManager.TShopCommand;
import net.imperialmc.TokenShop.CommandManager.TokensCommand;
import io.netty.util.ResourceLeakDetector.Level;

import org.bukkit.Bukkit;
import org.bukkit.event.Listener;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin implements Listener {
  
	public static Main plugin;
  
	protected static Main getPlugin() {
		return plugin;
	}
	
	protected void initPlugin(Main plugin) {
		Main.plugin = plugin;
	}
  
  public void onEnable() {
    this.saveDefaultConfig();
    initPlugin(this); // This is where we register our events/commands
    ResourceLeakDetector.setLevel(Level.DISABLED); //This is for netty optimizations
    
    /** Register Events & Commands */
    getServer().getPluginManager().registerEvents(this, this);
    new TokenManager().Initialize();
    getCommand("tokens").setExecutor(new TokensCommand());
    getCommand("tshop").setExecutor(new TShopCommand());
  }
  
  public void onDisable() {
    TokenManager.saveHashMap();
  }
 }
}
