package net.imperialmc.TokenShop.CommandManager;

import net.imperialmc.TokenShop.Main;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class TShopCommand implements CommandExecutor {

    private Main plugin;

    public TShopCommand(Main Main) {

        // PLUGIN RUNS BACK TO MAIN CLASS
        plugin = Main;

        // COMMAND
        plugin.getCommand("tshop").setExecutor(this);

    }
  
  @Override
  public boolean onCommand(CommandSender sender, Command cmd, String StringLabel,
      String[] args) {
    if (cmd.getName().equalsIgnoreCase("tshop")) {

        if (!(sender instanceof Player)) {
          sender.sendMessage(ChatColor.RESET
              + "Only players can open the tokenshop!");
          return true;
        }
        
        Player player = (Player) sender;
        
        if(!(player.hasPermission("millenium.tokenshop.tshop"))) {
          player.sendMessage("You do not have enough swag.");
          return true;
        }
        
        player.openInventory(plugin.getGUI().getShop());
        return true;
      }
    return false;
  }
}
