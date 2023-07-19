package me.trollskull.tntreporter;

import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.event.EventHandler;
import org.bukkit.event.block.Action;
import org.bukkit.event.Listener;
import org.bukkit.entity.Player;
import java.text.MessageFormat;
import org.bukkit.block.Block;
import org.bukkit.Material;
import org.bukkit.Bukkit;

public class Events implements Listener {

    @EventHandler
    public void blockBreak(BlockBreakEvent event) {
        Block block = event.getBlock();

        if (block == null || block.getType() != Material.TNT) 
            return;

        Player player = event.getPlayer();
        String playerName = player.getName();
 
        int currentCount = Main.tntBrokenByPlayers.getOrDefault(playerName, 0);
        Main.tntBrokenByPlayers.put(playerName, currentCount + 1);

        logCoordinates(block, "has broken", player);
    }

    @EventHandler
    public void onClick(PlayerInteractEvent event) {
        Block block = event.getClickedBlock();
        Player player = event.getPlayer();

        if (player == null || block == null || block.getType() != Material.TNT)
            return;

        Action action = event.getAction();
        ItemStack item = event.getItem();

        if (action == null || action != Action.RIGHT_CLICK_BLOCK || item == null || item.getType() != Material.FLINT_AND_STEEL)
            return;

        logCoordinates(block, "activated", player);
    }

    @EventHandler
    public void onPlace(BlockPlaceEvent event) {
        Block block = event.getBlock();
        Player player = event.getPlayer();

        if (block == null || block.getType() != Material.TNT)
            return;

        logCoordinates(block, "has placed", player);
    }

    private void logCoordinates(Block block, String action, Player player) {
        int x = block.getLocation().getBlockX();
        int y = block.getLocation().getBlockY();
        int z = block.getLocation().getBlockZ();

        String pattern = "{0}Player '{1}' {2} TNT on [{3}, {4}, {5}] !";
        String message = MessageFormat.format(
            pattern, Main.prefix, player.getName(), action, x, y, z);

        Bukkit.getServer().getConsoleSender().sendMessage(message);
        Main.sendWarnToAdmin(message);
    }
}