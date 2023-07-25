package me.trollskull.tntreporter;

import org.bukkit.configuration.file.FileConfiguration;
import net.md_5.bungee.api.chat.BaseComponent;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.entity.Player;
import java.util.HashMap;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;

import java.util.Map;

public class Main extends JavaPlugin {
    public static Map<String, Integer> tntBrokenByPlayers = new HashMap<>();
    public static Map<String, Integer> tntPlacedByPlayers = new HashMap<>();

    public FileConfiguration config;
    private LanguageManager languageManager;
    public CommandTNTReporter commandTNTReporter;
    private WarnAdmin warnAdmin;

    @Override
    public void onEnable() {
        getLogger().info("TNT Reporter is enabled! [v0.1]");

        saveDefaultConfig();
        config = getConfig();

        // Initialize the LanguageManager and load language files.
        languageManager = new LanguageManager(this);
        languageManager.loadLanguage();

        // Initialize the WarnAdmin class to handle warning messages to administrators.
        warnAdmin = new WarnAdmin(this, languageManager);

        if (config.getBoolean("detectBroken")) {
            getServer().getPluginManager().registerEvents(new EventTNTBroken(this, warnAdmin), this);
        }

        if (config.getBoolean("detectPlaced")) {
            getServer().getPluginManager().registerEvents(new EventTNTPlaced(this, warnAdmin), this);
        }

        if (config.getBoolean("detectActivated")) {
            getServer().getPluginManager().registerEvents(new EventTNTActivated(this, warnAdmin), this);
        }

        if (config.getBoolean("interruptExplosion")) {
            getServer().getPluginManager().registerEvents(new EventPrimedTNT(this, warnAdmin), this);
        }

        commandTNTReporter = new CommandTNTReporter(this);
        getCommand("tntreporter").setExecutor(commandTNTReporter);
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (args.length == 1 && args[0].equalsIgnoreCase("reload")) {
            reloadConfig();
            config = getConfig();
            sender.sendMessage("TNT Reporter configuration reloaded.");
            return true;
        }
        return false;
    }

    @Override
    public void onDisable() {
        getLogger().info("TNT Reporter is disabled.");
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
