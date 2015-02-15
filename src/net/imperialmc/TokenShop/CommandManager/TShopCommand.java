package net.imperialmc.TokenShop.CommandManager;

import net.imperialmc.TokenShop.Main;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class TShopCommand implements CommandExecutor {

    private Main plugin;

    public TShopCommand(Main Main) {

        // PLUGIN RUNS BACK TO MAIN CLASS
        plugin = Main;

        // COMMAND
        plugin.getCommand("PUT COMMAND IN HERE").setExecutor(this);

    }

  @Override
  public boolean onCommand(CommandSender arg0, Command arg1, String arg2,
      String[] arg3) {
    // TODO Auto-generated method stub
    return false;
  }
  
  
}
