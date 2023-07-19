package me.trollskull.tntreporter;

import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin {
    public void onEnable() {
        getLogger().info("TNT Reporter is enabled! [v0.0.3]");

        PluginManager manager = getServer().getPluginManager();
        manager.registerEvents(new Events(), (Plugin)this);
    }

    public static String prefix = "[TNT Reporter] ";
}