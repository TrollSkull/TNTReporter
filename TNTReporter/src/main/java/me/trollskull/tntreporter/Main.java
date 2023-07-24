package me.trollskull.tntreporter;

import org.bukkit.configuration.file.FileConfiguration;
import net.md_5.bungee.api.chat.BaseComponent;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.command.CommandSender;
import org.bukkit.command.Command;
import org.bukkit.entity.Player;
import java.util.HashMap;
import org.bukkit.Bukkit;
import java.util.Map;

public class Main extends JavaPlugin {
    public static Map<String, Integer> tntBrokenByPlayers = new HashMap<>();
    public static Map<String, Integer> tntPlacedByPlayers = new HashMap<>();

    public FileConfiguration config;
    private LanguageManager languageManager;
    private WarnAdmin warnAdmin;

    @Override
    public void onEnable() {
        getLogger().info("TNT Reporter is enabled! [v0.1]");

        // Initialize the LanguageManager and load language files.
        languageManager = new LanguageManager(this);
        languageManager.loadLanguage();

        // Initialize the WarnAdmin class to handle warning messages to administrators.
        warnAdmin = new WarnAdmin(this, languageManager);

        // Register events for TNT-related interactions.
        getServer().getPluginManager().registerEvents(new EventTNTActivated(this, warnAdmin), this);
        getServer().getPluginManager().registerEvents(new EventTNTBroken(this, warnAdmin), this);
        getServer().getPluginManager().registerEvents(new EventTNTPlaced(this, warnAdmin), this);
        getServer().getPluginManager().registerEvents(new EventPrimedTNT(), this);

        // Save the default configuration file if it does not exist.
        saveDefaultConfig();
        config = getConfig();
    }

    // Send warning messages to administrators.
    @Override
    public void onDisable() {
        getLogger().info("TNT Reporter is disabled.");
    }

    // Send warning messages to administrators.
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (command.getName().equalsIgnoreCase("tntreporter report")) {
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
        } else if (command.getName().equalsIgnoreCase("tntreporter reload") && sender.hasPermission("tntreporter.reload")) {
            // Reload the configuration file.
            reloadConfig();
            config = getConfig();
            sender.sendMessage("TNT Reporter configuration reloaded.");
            return true;
        }
        return false;
    }

    // Send warning messages to administrators.
    public void sendWarnToAdmin(BaseComponent... components) {
        for (Player player : Bukkit.getOnlinePlayers()) {
            if (player.hasPermission("ServerOperator")) {
                player.spigot().sendMessage(components);
            }
        }
    }
}