package net.imperialmc.TokenShop;

import java.util.HashMap;
import java.util.Map;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.event.player.PlayerJoinEvent;

public class TokenManager {
  
  static Map<String, Integer> tokens = new HashMap<>();
  FileConfiguration config = Main.getPlugin().getConfig();
  
  public TokenManager() {
    
  }
  
  public static void onJoinAddToHashMap(PlayerJoinEvent e) {
    if (tokens.get(e.getPlayer().getName()) == null) {
      
    }
  }
  
  public static void removeTokens(String UUID, int tokens) {
    
  }
  
  public static void addTokens(String UUID, int tokens) {
    
  }
  
  public static int getTokens(String UUID) {
    int tokens = 0;
    
    
    
    return tokens;
  }
  
  
}
