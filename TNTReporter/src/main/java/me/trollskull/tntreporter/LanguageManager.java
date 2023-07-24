package me.trollskull.tntreporter;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import java.nio.charset.StandardCharsets;
import java.io.InputStreamReader;
import org.bukkit.plugin.Plugin;

public class LanguageManager {

    private final Plugin plugin;
    private FileConfiguration languageConfig;

    // Constructor for the LanguageManager class.
    public LanguageManager(Plugin plugin) {
        this.plugin = plugin;
    }

    public void loadLanguage() {
        plugin.saveDefaultConfig();
        plugin.saveResource("language.yml", false);

        // Load the language configuration as a FileConfiguration
        languageConfig = YamlConfiguration.loadConfiguration(new InputStreamReader(
            plugin.getResource("language.yml"), StandardCharsets.UTF_8));        
    }

    // Get the message associated with the specified key from the language configuration.
    public String getMessage(String key) {
        if (languageConfig.contains(key)) {
            return languageConfig.getString(key);
        }
        return "Missing message for key: " + key;
    }
}