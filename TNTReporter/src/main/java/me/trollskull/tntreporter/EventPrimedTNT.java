package me.trollskull.tntreporter;

import org.bukkit.event.entity.EntityChangeBlockEvent;
import org.bukkit.event.entity.EntityExplodeEvent;
import org.bukkit.event.EventHandler;
import org.bukkit.entity.EntityType;
import org.bukkit.event.Listener;

public class EventPrimedTNT implements Listener {
    public Main main;
    // private WarnAdmin warnAdmin;

    // Constructor for the EventPrimedTNT class.
    public EventPrimedTNT(Main main, WarnAdmin warnAdmin) {
        this.main = main;
        // this.warnAdmin = warnAdmin;
    }

    // Cancel entity-to-block conversion. (Avoid explosion)
    @EventHandler
    public void onEntityChangeBlock(EntityChangeBlockEvent event) {
        if (event.getEntityType() == EntityType.PRIMED_TNT) {
            event.setCancelled(true);
        }
    }

    // Check if the exploded entity is a PrimedTNT and cancel the explosion.
    @EventHandler
    public void onEntityExplode(EntityExplodeEvent event) {
        if (event.getEntityType() == EntityType.PRIMED_TNT) {
            event.setCancelled(true);
        }
    }
}