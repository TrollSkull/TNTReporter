package me.trollskull.tntreporter;

import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.entity.Player;
import org.bukkit.Bukkit;
import java.util.HashMap;
import java.util.Map;

public class Main extends JavaPlugin {
    public static Map<String, Integer> tntBrokenByPlayers = new HashMap<>();
    public static Object prefix;

    @Override
    public void onEnable() {
        getLogger().info("TNT Reporter is enabled! [v0.0.5]");
        getServer().getPluginManager().registerEvents(new Events(), this);
    }

    @Override
    public void onDisable() {
        getLogger().info("TNT Reporter is disabled.");
    }

    public static void sendWarnToAdmin(String message) {
        for (Player player : Bukkit.getOnlinePlayers()) {
            if (player.hasPermission("ServerOperator")) {
                player.sendMessage(message);
            }
        }
    }
}