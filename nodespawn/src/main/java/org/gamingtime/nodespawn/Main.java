package org.gamingtime.nodespawn;
import org.bukkit.plugin.java.JavaPlugin;
public class Main extends JavaPlugin {
    public EventListener publicListener = new EventListener();
    @Override
    public void onEnable() {
        getServer().getPluginManager().registerEvents(publicListener, this);
        publicListener.setMainRef(this);
        getLogger().info("NoDespawn Loaded!");
    }
    @Override
    public void onDisable() {
        getLogger().info("NoDespawn Unloaded.");
    }
}