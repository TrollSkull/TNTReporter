package me.trollskull.tntreporter;

import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.event.EventHandler;
import org.bukkit.event.block.Action;
import org.bukkit.event.Listener;
import org.bukkit.entity.Player;
import org.bukkit.block.Block;
import org.bukkit.Material;

public class EventTNTActivated implements Listener {
    public Main main;
    private WarnAdmin warnAdmin;

    public EventTNTActivated(Main main, WarnAdmin warnAdmin) {
        this.main = main;
        this.warnAdmin = warnAdmin;
    }

    @EventHandler
    public void onPlayerInteract(PlayerInteractEvent event) {
        Block block = event.getClickedBlock();
        if (block == null || block.getType() != Material.TNT)
            return;

        Action action = event.getAction();
        ItemStack item = event.getItem();

        if (action != Action.RIGHT_CLICK_BLOCK || item == null || item.getType() != Material.FLINT_AND_STEEL)
            return;

        Player player = event.getPlayer();
        String playerName = player.getName();

        int x = block.getLocation().getBlockX();
        int y = block.getLocation().getBlockY();
        int z = block.getLocation().getBlockZ();

        int currentCount = Main.tntPlacedByPlayers.getOrDefault(playerName, 0);
        Main.tntPlacedByPlayers.put(playerName, currentCount + 1);

        warnAdmin.sendMessage(playerName, player, "broken", x, y, z);
    }
}