package me.trollskull.tntreporter;

import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.block.Action;
import org.bukkit.event.Listener;
import org.bukkit.entity.Player;
import org.bukkit.block.Block;
import org.bukkit.Material;

public class EventTNTActivated implements Listener {
    public Main main;
    private WarnAdmin warnAdmin;

    // Constructor for the EventTNTActivated class.
    public EventTNTActivated(Main main, WarnAdmin warnAdmin) {
        this.main = main;
        this.warnAdmin = warnAdmin;
    }

    // Handles the PlayerInteractEvent for TNT blocks being activated.
    @EventHandler(priority = EventPriority.HIGH)
    public void onPlayerInteract(PlayerInteractEvent event) {
        Block block = event.getClickedBlock();

        // Check if the clicked block is a TNT block.
        if (block == null || block.getType() != Material.TNT)
            return;

        Action action = event.getAction();
        ItemStack item = event.getItem();

        // Check if the player right-clicked the TNT block with flint and steel.
        if (action != Action.RIGHT_CLICK_BLOCK || item == null || item.getType() != Material.FLINT_AND_STEEL)
            return;

        Player player = event.getPlayer();
        String playerName = player.getName();

        // Get the coordinates of the TNT block.
        int x = block.getLocation().getBlockX();
        int y = block.getLocation().getBlockY();
        int z = block.getLocation().getBlockZ();

        // Increment the TNT activation count for the player.
        int currentCount = Main.tntPlacedByPlayers.getOrDefault(playerName, 0);
        Main.tntPlacedByPlayers.put(playerName, currentCount + 1);

        // Send warning messages to admins.
        warnAdmin.sendMessage(playerName, player, "activated", x, y, z);
    }
}
