package net.imperialmc.TokenShop;

import java.util.HashMap;
import java.util.Map;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class TokenManager implements Listener {
  
  private static Map<String, Integer> tokens = new HashMap<>();
  private static FileConfiguration config = Main.getPlugin().getConfig();
  
  public static void onJoinAddToHashMap(PlayerJoinEvent e) {
    String playerID = e.getPlayer().getUniqueId().toString();
    if (tokens.get(playerID) == null) {
      if (config.get("tokens." + playerID) == null) {
        tokens.put(playerID, 0);
      }
      else {
        tokens.put(playerID, config.getInt("tokens." + playerID));
      }
    }
  }
  
  public static void saveHashMap() {
    for (Map.Entry<String, Integer> entry : tokens.entrySet()) {
      config.set("tokens." + entry.getKey(), (int)entry.getValue());
    }
  }
  
  public static void setTokens(String playerID, int numTokens) {
    tokens.put(playerID, new Integer(numTokens));
  }
  
  public static void removeTokens(String playerID, int tokens) {
    setTokens(playerID, getTokens(playerID) - tokens);
  }
  
  public static void addTokens(String playerID, int tokens) {
    setTokens(playerID, tokens + getTokens(playerID));
  }
  
  public static int getTokens(String playerID) {
    int numTokens = 0;
    
    if (tokens.get(playerID) != null) {
      numTokens = (int)(tokens.get(playerID));
    }
    else if (config.get("tokens." + playerID) == null) {
      return numTokens;
    }
    else {
      numTokens = config.getInt("tokens." + playerID);
    }
    
    return numTokens;
  }
}
