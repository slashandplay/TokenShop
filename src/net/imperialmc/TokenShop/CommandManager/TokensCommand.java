package net.imperialmc.TokenShop.CommandManager;

import net.imperialmc.TokenShop.Main;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.event.Listener;

public class TokensCommand implements CommandExecutor, Listener {

    private Main plugin;

    public TokensCommand(Main Main) {

        // PLUGIN RUNS BACK TO MAIN CLASS
        plugin = Main;

        // COMMAND
        plugin.getCommand("PUT COMMAND IN HERE").setExecutor(this);

    }

  @Override
  public boolean onCommand(CommandSender sender, Command cmd, String StringLabel,
      String[] args) {
    String announcePrefix = ChatColor.BLUE + "[Tokens]";
    
    if (cmd.getName().equalsIgnoreCase("tokens")) {

      if (args.length == 0) {
        sender.sendMessage(announcePrefix + ChatColor.BOLD
            + "Imperial Networks Tokens!");
        sender.sendMessage(announcePrefix
            + "Earn tokens through voting, winning events, and more!");

        if (!(sender instanceof Player)) {
          sender.sendMessage(ChatColor.RESET
              + "Only players can see their checkpoints!");
          return true;
        }

        Player player = (Player) sender;
        int playerTokens = TokenManager.getTokens(player.getUniqueId()
            .toString());
        sender.sendMessage(announcePrefix + "You have " + playerTokens
            + "tokens!");
        sender
            .sendMessage(announcePrefix
                + "Use /tshop to use your tokens!, Use /tokens help for more info!");
        return true;
      }
      else if (args.length == 1) {
        sender.sendMessage(announcePrefix + ChatColor.BOLD 
            + "Imperial Networks Tokens!");
        String playerName = args[0];
        String playerID = UUIDFetcher.getUUIDof(playerName).toString();
        int playerTokens = TokenManager.getTokens(playerID);
      }

    }
    return false;
  }

}
