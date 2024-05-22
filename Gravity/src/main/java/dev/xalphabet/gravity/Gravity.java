package dev.xalphabet.gravity;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.HashMap;
import java.util.UUID;

public class Gravity extends JavaPlugin implements Listener {
    private final HashMap<UUID, Double> playerGravity = new HashMap<>();

    @Override
    public void onEnable() {
        Bukkit.getPluginManager().registerEvents(this, this);
        this.getCommand("setgravity").setExecutor(new GravityCommand(this));
    }

    @EventHandler
    public void onPlayerDeath(PlayerDeathEvent event) {
        Player player = event.getEntity();
        if (player.getLastDamageCause() != null && player.getLastDamageCause().getCause() == EntityDamageEvent.DamageCause.FALL) {
            double gravity = playerGravity.getOrDefault(player.getUniqueId(), 1.0);
            String deathMessage = String.format("%s fell to their demise with a gravity modifier of %.2f", player.getName(), gravity);
            event.setDeathMessage(deathMessage);
        }
    }

    public void setPlayerGravity(UUID playerId, double gravity) {
        playerGravity.put(playerId, gravity);
    }
}
