package org.gamingtime.nodespawn;

import org.bukkit.event.Listener;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.entity.Item;
import org.bukkit.event.entity.EntityDeathEvent;
import de.tr7zw.changeme.nbtapi.NBTEntity;

public class EventListener implements Listener {
    private Main mainRef;
    // If the Age NBT tag of the dropped items is set to this (minimum value), it
    // never changes.
    private short startingAge;
    private Boolean disableDespawns;
    private short noDespawnAge = -32768;

    @EventHandler(priority = EventPriority.HIGHEST)
    public void entityDeathEvent(EntityDeathEvent event) {
        // Return if it's not a player who died.
        if (!(event.getEntity().getType().equals(org.bukkit.entity.EntityType.PLAYER))) {
            return;
        }
        // If inventory is empty, no need to waste power on all of this mess *gestures
        // to code below*
        if (event.getDrops().isEmpty()) {
            return;
        }
        // For each intended drop item, spawn it in ourselves
        event.getDrops().forEach(element -> {
            Item droppedItem = event.getEntity().getWorld().dropItem(event.getEntity().getLocation(), element);
            NBTEntity newDropNBT = new NBTEntity(droppedItem);
            if (disableDespawns.equals(true)) {
                newDropNBT.setShort("Age", noDespawnAge);
            } else {
                newDropNBT.setShort("Age", startingAge);
            }
        });
        event.getDrops().clear();
    }

    // Pass main class to event listener thru here.
    public void setMainRef(Main mainR) {
        mainRef = mainR;
    }

    public void setConfigs() {
        // Loads config values from config.yml
        int despawnTimeC = mainRef.getConfig().getInt("time-to-despawn");
        disableDespawns = mainRef.getConfig().getBoolean("disable-despawns");
        // If the values in our config are invalid, just default to normal behavior.
        if ((despawnTimeC < 0) || (despawnTimeC > 38767)) {
            startingAge = 6000;
        } else {
            startingAge = (short) (-1 * (despawnTimeC - 6000));
        }
    }
}