package dev.xalphabet.gravity;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class GravityCommand implements CommandExecutor {
    private final Gravity plugin;

    public GravityCommand(Gravity plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof Player) {
            Player player = (Player) sender;
            if (args.length == 1) {
                try {
                    double gravity = Double.parseDouble(args[0]);
                    plugin.setPlayerGravity(player.getUniqueId(), gravity);
                    player.sendMessage("Your gravity has been set to " + gravity);
                    return true;
                } catch (NumberFormatException e) {
                    player.sendMessage("Please enter a valid number.");
                    return false;
                }
            } else {
                player.sendMessage("Usage: /setgravity <gravity>");
                return false;
            }
        }
        return false;
    }
}
