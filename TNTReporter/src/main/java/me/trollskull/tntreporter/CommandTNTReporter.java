package me.trollskull.tntreporter;

import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.Command;

import java.util.Map;

import org.bukkit.ChatColor;

public class CommandTNTReporter implements CommandExecutor {
    public Main main;

    public CommandTNTReporter(Main main) {
        this.main = main;
    }

    // + && sender.hasPermission("tntreporter.reload")
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (args.length == 1 && args[0].equalsIgnoreCase("help")) {
            showHelpMenu(sender);
            return true;
        }

        if (args.length == 1 && args[0].equalsIgnoreCase("report")) {
            sender.sendMessage("TNT Report:");
            sender.sendMessage("TNT broken by players:");
            for (Map.Entry<String, Integer> entry : Main.tntBrokenByPlayers.entrySet()) {
                String playerName = entry.getKey();
                int tntBroken = entry.getValue();
                sender.sendMessage(playerName + ": " + tntBroken);
            }

            sender.sendMessage("TNT placed by players:");
            for (Map.Entry<String, Integer> entry : Main.tntPlacedByPlayers.entrySet()) {
                String playerName = entry.getKey();
                int tntPlaced = entry.getValue();
                sender.sendMessage(playerName + ": " + tntPlaced);
            }
            return true;
        }
        return false;
    }

    private void showHelpMenu(CommandSender sender) {
        sender.sendMessage(ChatColor.YELLOW + "----- TNT Reporter Help -----");
        sender.sendMessage(ChatColor.GOLD + "/tntreporter help" + ChatColor.WHITE + " - Display a help menu");
        sender.sendMessage(ChatColor.GOLD + "/tntreporter reload" + ChatColor.WHITE + " - Reload plugin");
        sender.sendMessage(ChatColor.GOLD + "/tntreporter report" + ChatColor.WHITE + " - Show players TNT stats");

        if (sender.hasPermission("tntreporter.reload")) {
            sender.sendMessage(ChatColor.YELLOW + "Note: You have permission to use /tntreporter reload");
        }
    }
}