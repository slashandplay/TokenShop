package net.imperialmc.TokenShop;

import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin {
  
  private static Plugin plugin;
  TokenManager tokenm = new TokenManager();
  
  public void onEnable() {
    plugin = this; // This is where we register our events/commands
    
  }
  
  public TokenManager getTokenManager() {
    return tokenm;
  }
  
  public void onDisable() {
    TokenManager.saveHashMap();
    plugin = null; // To stop memory leaks
  }
  
  public static Plugin getPlugin() {
    return plugin;
  }
  
  
  
}
