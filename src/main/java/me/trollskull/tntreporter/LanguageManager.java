package me.trollskull.tntreporter;

import org.bukkit.ChatColor;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.Plugin;

public class LanguageManager {

    private final Plugin plugin;
    private FileConfiguration languageConfig;

    public LanguageManager(Plugin plugin) {
        this.plugin = plugin;
    }

    public void loadLanguage() {
        plugin.saveDefaultConfig();
        languageConfig = plugin.getConfig();
    }

    public String getMessage(String key) {
        if (languageConfig.contains(key)) {
            return colorize(languageConfig.getString(key));
        }
        return "Missing message for key: " + key;
    }

    private String colorize(String message) {
        return ChatColor.translateAlternateColorCodes('&', message);
    }
}
