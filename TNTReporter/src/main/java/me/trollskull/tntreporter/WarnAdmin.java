package me.trollskull.tntreporter;

import net.md_5.bungee.api.chat.TextComponent;
import net.md_5.bungee.api.chat.ClickEvent;
import org.bukkit.entity.Player;
import org.bukkit.ChatColor;
import org.bukkit.Bukkit;

public class WarnAdmin {
    private LanguageManager languageManager;
    private Main main;

    // Constructor for the WarnAdmin class.
    public WarnAdmin(Main main, LanguageManager languageManager) {
        this.main = main;
        this.languageManager = languageManager;
    }

    private static final String ON_PLACE_MESSAGE_KEY = "on-place-message";
    private static final String ON_CLICK_MESSAGE_KEY = "on-click-message";
    private static final String ON_BREAK_MESSAGE_KEY = "on-break-message";

    // Sends warning messages to administrators when TNT-related events occur.
    public void sendMessage(String playerName, Player player, String action, int x, int y, int z) {
        String messageKey = action;

        if (action.equalsIgnoreCase("activated")) {
            messageKey = ON_CLICK_MESSAGE_KEY;
        } else if (action.equalsIgnoreCase("placed")) {
            messageKey = ON_PLACE_MESSAGE_KEY;
        } else if (action.equalsIgnoreCase("broken")) {
            messageKey = ON_BREAK_MESSAGE_KEY;
        } else {
            messageKey = "on-player-interact-message";
        }

        String customMessage = ChatColor.RED + "[TNT Reporter] " + ChatColor.RESET + " " + languageManager.getMessage(messageKey)
                .replace("%player%", playerName);

        TextComponent clickableMessage = new TextComponent(customMessage);
        TextComponent coordsComponent = new TextComponent(ChatColor.GREEN + " [" + x + ", " + y + ", " + z + "]");
        coordsComponent.setClickEvent(new ClickEvent(ClickEvent.Action.RUN_COMMAND, "/tp @s " + x + " " + y + " " + z));
        clickableMessage.addExtra(coordsComponent);
        clickableMessage.addExtra(ChatColor.WHITE + " !");

        customMessage += ChatColor.GREEN + " [" + x + ", " + y + ", " + z + "]" + ChatColor.WHITE + " !";
        Bukkit.getServer().getConsoleSender().sendMessage(customMessage);

        main.sendWarnToAdmin(new TextComponent(clickableMessage));
    }
}