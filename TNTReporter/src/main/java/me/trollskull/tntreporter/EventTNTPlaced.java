package me.trollskull.tntreporter;

import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.entity.Player;
import org.bukkit.block.Block;
import org.bukkit.Material;

public class EventTNTPlaced implements Listener {

    public Main main;
    private WarnAdmin warnAdmin;

    // Constructor for the EventTNTPlaced class.
    public EventTNTPlaced(Main main, WarnAdmin warnAdmin) {
        this.main = main;
        this.warnAdmin = warnAdmin;
    }

    // Event handler for BlockPlaceEvent.
    // This method is called whenever a player places a block.
    @EventHandler(priority = EventPriority.HIGH)
    public void onBlockPlace(BlockPlaceEvent event) {
        Block block = event.getBlock();
        if (block.getType() != Material.TNT) 
            return;

        Player player = event.getPlayer();
        String playerName = player.getName();

        int x = block.getLocation().getBlockX();
        int y = block.getLocation().getBlockY();
        int z = block.getLocation().getBlockZ();

        int currentCount = Main.tntPlacedByPlayers.getOrDefault(playerName, 0);
        Main.tntPlacedByPlayers.put(playerName, currentCount + 1);
        
        // Send warning message to administrators about the placed TNT
        warnAdmin.sendMessage(playerName, player, "placed", x, y, z);
    }
}
