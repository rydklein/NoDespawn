package org.gamingtime.nodespawn;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.event.Listener;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.entity.Item;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.event.entity.EntityDeathEvent;
import de.tr7zw.changeme.nbtapi.NBTEntity;
import org.bukkit.World;
public class EventListener implements Listener {
    private Main mainRef;
    private short idealAge = -32768;
    @EventHandler(priority = EventPriority.HIGHEST)
    public void entityDeathEvent(EntityDeathEvent event) {
        if (!(event.getEntity().getType().equals(org.bukkit.entity.EntityType.PLAYER))) {
            return;
        }
        mainRef.getLogger().info("Player Died!");

        event.getDrops().forEach(element -> {
            Item droppedItem = event.getEntity().getWorld().dropItem(event.getEntity().getLocation(), element);
            NBTEntity newDropNBT = new NBTEntity(droppedItem);
            // mainRef.getLogger().info(newDropNBT.getShort("Age").toString());
            newDropNBT.setShort("Age", idealAge);
        });
        event.getDrops().clear();
    }

    public void setMainRef(Main mainR) {
        mainRef = mainR;
    }
}