package me.trollskull.tntreporter;

import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.block.BlockBreakEvent;
import net.md_5.bungee.api.chat.TextComponent;
import net.md_5.bungee.api.chat.ClickEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.event.EventHandler;
import org.bukkit.event.block.Action;
import org.bukkit.event.Listener;
import org.bukkit.entity.Player;
import org.bukkit.block.Block;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.Location;
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
        // logCoordinates(block, "has broken", player);
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

        String playerName = player.getName();
        int currentCount = Main.tntPlacedByPlayers.getOrDefault(playerName, 0);
        Main.tntPlacedByPlayers.put(playerName, currentCount + 1);

        logCoordinates(block, "activated", player);
    }

    @EventHandler
    public void onPlace(BlockPlaceEvent event) {
        Block block = event.getBlock();
        Player player = event.getPlayer();

        if (block.getType() == Material.TNT) {
            int x = block.getLocation().getBlockX();
            int y = block.getLocation().getBlockY();
            int z = block.getLocation().getBlockZ();

            String playerName = player.getName();
            int currentCount = Main.tntPlacedByPlayers.getOrDefault(playerName, 0);
            Main.tntPlacedByPlayers.put(playerName, currentCount + 1);

            TextComponent consoleMessage = new TextComponent(ChatColor.RED + Main.PREFIX + ChatColor.WHITE + " Player ");
            consoleMessage.addExtra(ChatColor.WHITE + player.getName() + " has placed TNT on ");
            TextComponent coordsComponent = new TextComponent(ChatColor.GREEN + "[" + x + ", " + y + ", " + z + "]");
            consoleMessage.addExtra(coordsComponent);
            consoleMessage.addExtra(ChatColor.WHITE + " !");

            String legacyText = consoleMessage.toLegacyText();
            // player.sendMessage(legacyText);
            Bukkit.getServer().getConsoleSender().sendMessage(legacyText);
            // logCoordinates(block, "has placed", player);
        }
    }

    @EventHandler
    public void onPlayerInteract(PlayerInteractEvent event) {
        Action action = event.getAction();
        Player player = event.getPlayer();

        if (action == Action.RIGHT_CLICK_BLOCK || action == Action.RIGHT_CLICK_AIR) {
            if (player.hasPermission("ServerOperator")) {
                String message = event.getItem() != null ? event.getItem().getItemMeta().getDisplayName() : null;
                
                if (message != null && message.startsWith(Main.PREFIX)) {
                    String[] parts = message.split(":");
                    
                    if (parts.length == 2) {
                        String[] coords = parts[1].split(",");
                        
                        if (coords.length == 3) {
                            try {
                                int x = Integer.parseInt(coords[0].trim());
                                int y = Integer.parseInt(coords[1].trim());
                                int z = Integer.parseInt(coords[2].trim());

                                Location tpLocation = new Location(player.getWorld(), x + 0.5, y, z + 0.5);
                                player.teleport(tpLocation);

                                TextComponent clickableMessage = new TextComponent(ChatColor.RED + Main.PREFIX + ChatColor.WHITE + " Player ");
                                clickableMessage.addExtra(ChatColor.WHITE + player.getName() + " activated TNT on ");
                                TextComponent coordsComponent = new TextComponent(ChatColor.GREEN + "[" + x + ", " + y + ", " + z + "]");
                                coordsComponent.setClickEvent(new ClickEvent(ClickEvent.Action.RUN_COMMAND, "/tp @s " + x + " " + y + " " + z));
                                clickableMessage.addExtra(coordsComponent);
                                clickableMessage.addExtra(ChatColor.WHITE + " !");

                                player.spigot().sendMessage(clickableMessage);
                            } catch (NumberFormatException ignored) {
                                return;
                            }
                        }
                    }
                }
            }
        }
    }

    private void logCoordinates(Block block, String action, Player player) {
        int x = block.getLocation().getBlockX();
        int y = block.getLocation().getBlockY();
        int z = block.getLocation().getBlockZ();

        String playerName = player.getName();
        String coordinates = ChatColor.GREEN + "[" + x + ", " + y + ", " + z + "]";

        TextComponent message = new TextComponent(ChatColor.RED + Main.PREFIX + ChatColor.WHITE + " Player ");
        message.addExtra(ChatColor.WHITE + playerName + " " + action + " TNT on ");
        TextComponent coordsComponent = new TextComponent(coordinates);
        coordsComponent.setClickEvent(new ClickEvent(ClickEvent.Action.RUN_COMMAND, "/tp @s " + x + " " + y + " " + z));
        message.addExtra(coordsComponent);
        message.addExtra(ChatColor.RED + " !");

        Bukkit.getServer().getConsoleSender().sendMessage(message.toLegacyText());
        Main.sendWarnToAdmin(message);
    }
}