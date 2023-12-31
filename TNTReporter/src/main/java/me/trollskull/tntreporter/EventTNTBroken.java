package me.trollskull.tntreporter;

import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.entity.Player;
import org.bukkit.block.Block;
import org.bukkit.Material;

public class EventTNTBroken implements Listener {

    public Main main;
    private WarnAdmin warnAdmin;

    // Constructor for the EventTNTBroken class.
    public EventTNTBroken(Main main, WarnAdmin warnAdmin) {
        this.main = main;
        this.warnAdmin = warnAdmin;
    }

    // Event handler for BlockBreakEvent.
    // This method is called whenever a player breaks a block.
    @EventHandler(priority = EventPriority.HIGH)
    public void onBlockBreak(BlockBreakEvent event) {
        Block block = event.getBlock();
        if (block == null || block.getType() != Material.TNT) 
            return;

        Player player = event.getPlayer();
        String playerName = player.getName();

        int x = block.getLocation().getBlockX();
        int y = block.getLocation().getBlockY();
        int z = block.getLocation().getBlockZ();

        int currentCount = Main.tntBrokenByPlayers.getOrDefault(playerName, 0);
        Main.tntBrokenByPlayers.put(playerName, currentCount + 1);
        
        // Send warning message to administrators about the broken TNT
        warnAdmin.sendMessage(playerName, player, "broken", x, y, z);
    }
}
