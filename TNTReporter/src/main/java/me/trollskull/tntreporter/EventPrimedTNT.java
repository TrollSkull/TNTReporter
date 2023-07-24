package me.trollskull.tntreporter;

import org.bukkit.event.entity.EntityChangeBlockEvent;
import org.bukkit.event.entity.EntityExplodeEvent;
import org.bukkit.inventory.meta.FireworkMeta;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.FireworkEffect.Type;
import org.bukkit.event.EventHandler;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Firework;
import org.bukkit.event.Listener;
import org.bukkit.FireworkEffect;
import org.bukkit.Location;
import org.bukkit.Color;

public class EventPrimedTNT implements Listener {
    public Main main;
    // private WarnAdmin warnAdmin;

    public EventPrimedTNT(Main main, WarnAdmin warnAdmin) {
        this.main = main;
        // this.warnAdmin = warnAdmin;
    }

    // Cancel entity-to-block conversion. (Avoid explosion)
    @EventHandler
    public void onEntityChangeBlock(EntityChangeBlockEvent event) {
        if (event.getEntityType() == EntityType.PRIMED_TNT) {
            event.setCancelled(true);
            spawnComedyFirework(event.getEntity().getLocation());
        }
    }

    // Check if the exploded entity is a PrimedTNT and cancel the explosion.
    @EventHandler
    public void onEntityExplode(EntityExplodeEvent event) {
        if (event.getEntityType() == EntityType.PRIMED_TNT) {
            event.setCancelled(true);
            spawnComedyFirework(event.getLocation());
        }
    }

    // Method to generate the comic rocket explosion.
    private void spawnComedyFirework(Location location) {
        Firework firework = location.getWorld().spawn(location, Firework.class);
        FireworkMeta fireworkMeta = firework.getFireworkMeta();
        fireworkMeta.addEffect(FireworkEffect.builder()
            .with(Type.BALL)
            .withColor(Color.RED)
            .trail(true)
            .build());
        fireworkMeta.setPower(0);
        firework.setFireworkMeta(fireworkMeta);

        new BukkitRunnable() {
            @Override
            public void run() {
                firework.detonate();
            }
        }.runTaskLater(main, 1L);
    }
}
