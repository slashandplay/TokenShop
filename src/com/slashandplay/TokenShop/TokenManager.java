package com.slashandplay.TokenShop;

import java.util.HashMap;
import java.util.Map;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

public class TokenManager implements Listener {

  protected static HashMap<String, Integer> tokens = new HashMap<String, Integer>();
  protected static FileConfiguration config = Main.getPlugin().getConfig();
  private static Main plugin = Main.getPlugin(); // Might as well have a global
                                                 // variable for this, no harm

  public TokenManager(Main Main) {
    plugin.getServer().getPluginManager().registerEvents(this, Main);
  }

  @EventHandler
  public static void onJoinAddToHashMap(PlayerJoinEvent e) {
    String playerID = e.getPlayer().getUniqueId().toString();
    if (tokens.get(playerID) == null) {
      if (config.get("tokens." + playerID) == null) {
        tokens.put(playerID, 0);
      } else
        tokens.put(playerID, config.getInt("tokens." + playerID));
    }
  }

  @EventHandler
  public void onQuit(PlayerQuitEvent e) {
    final Player p = e.getPlayer();
    /** Run scheduler to remove them from the hashmap and update the config */
    plugin.getServer().getScheduler().runTaskLater(plugin, new Runnable() {
      public void run() {
        if (!p.isOnline()) {
          String playerID = p.getUniqueId().toString();
          config.set("tokens." + playerID, tokens.get(playerID));
          tokens.remove(playerID);
        }
      }
    }, 140L);
  }

  public static void saveHashMap() {
    for (Map.Entry<String, Integer> entry : tokens.entrySet()) {
      config.set("tokens." + entry.getKey(), (int) entry.getValue());
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
      numTokens = (int) (tokens.get(playerID));
    } else if (config.get("tokens." + playerID) == null) {
      return numTokens;
    } else {
      numTokens = config.getInt("tokens." + playerID);
    }

    return numTokens;
  }
}
