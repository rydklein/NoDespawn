package org.gamingtime.nodespawn;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.event.Listener;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.entity.LivingEntity;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.event.entity.EntityDeathEvent;
import de.tr7zw.changeme.nbtapi.NBTItem;

public class EventListener implements Listener {
    private Main mainRef;
    private short idealAge = -32768;
    @EventHandler(priority = EventPriority.HIGHEST)
    public void entityDeathEvent(EntityDeathEvent event) {
        if (!(event.getEntity().getType().equals(org.bukkit.entity.EntityType.PLAYER))) {
            return;
        }
        mainRef.getLogger().info("Player Died!");
        List<ItemStack> newItems = new ArrayList<ItemStack>();

        event.getDrops().forEach(element -> {
            NBTItem newDropNBT = new NBTItem(element);
            mainRef.getLogger().info(newDropNBT.getShort("Age").toString());
            newDropNBT.setShort("Age", idealAge);
            newItems.add(newDropNBT.getItem());
        });
        event.getDrops().clear();
        event.getDrops().addAll(newItems);
        event.getDrops().forEach(element -> {
            NBTItem newDropNBT2 = new NBTItem(element);
            mainRef.getLogger().info(newDropNBT2.getShort("Age").toString());
        });
    }

    public void setMainRef(Main mainR) {
        mainRef = mainR;
    }
}