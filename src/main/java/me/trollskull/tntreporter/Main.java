package me.trollskull.tntreporter;

import org.bukkit.configuration.file.FileConfiguration;
import net.md_5.bungee.api.chat.BaseComponent;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.command.CommandSender;
import org.bukkit.command.Command;
import org.bukkit.entity.Player;
import org.bukkit.ChatColor;
import java.util.HashMap;
import org.bukkit.Bukkit;
import java.util.Map;

public class Main extends JavaPlugin {
    public static Map<String, Integer> tntBrokenByPlayers = new HashMap<>();
    public static Map<String, Integer> tntPlacedByPlayers = new HashMap<>();
    public static String PREFIX = ChatColor.RED + "[TNT Reporter] " + ChatColor.RESET;
    public FileConfiguration config;

    @Override
    public void onEnable() {
        getLogger().info("TNT Reporter is enabled! [v0.0.24]");
        getServer().getPluginManager().registerEvents(new Events(), this);

        saveDefaultConfig();
        config = getConfig();
    }

    @Override
    public void onDisable() {
        getLogger().info("TNT Reporter is disabled.");
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (command.getName().equalsIgnoreCase("tntreport")) {
            sender.sendMessage("TNT Report:");
            sender.sendMessage("TNT broken by players:");
            for (Map.Entry<String, Integer> entry : tntBrokenByPlayers.entrySet()) {
                String playerName = entry.getKey();
                int tntBroken = entry.getValue();
                sender.sendMessage(playerName + ": " + tntBroken);
            }
            
            sender.sendMessage("TNT placed by players:");
            for (Map.Entry<String, Integer> entry : tntPlacedByPlayers.entrySet()) {
                String playerName = entry.getKey();
                int tntPlaced = entry.getValue();
                sender.sendMessage(playerName + ": " + tntPlaced);
            }

            return true;

        } else if (command.getName().equalsIgnoreCase("tntreload") && sender.hasPermission("tntreporter.reload")) {
            reloadConfig();
            config = getConfig();
            sender.sendMessage("TNT Reporter configuration reloaded.");
            return true;
        }
        return false;
    }

    public static void sendWarnToAdmin(BaseComponent... components) {
        for (Player player : Bukkit.getOnlinePlayers()) {
            if (player.hasPermission("ServerOperator")) {
                player.spigot().sendMessage(components);
            }
        }
    }
}