package org.gamingtime.nodespawn;

import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin {
    public EventListener publicListener = new EventListener();

    @Override
    public void onEnable() {
        getServer().getPluginManager().registerEvents(publicListener, this);

        // I have no idea if this is the right way to access the main class from another
        // class, but it works.
        publicListener.setMainRef(this);
        // Put config there if it doesn't exist.
        saveDefaultConfig();
        // Tells EventHandler to load config options.
        // It's in a function so that I can easily add a reload
        // Config command later without having to refactor stuff.
        publicListener.setConfigs();

        getLogger().info("NoDespawn Loaded!");
    }
    /*
     * @Override public void onDisable() { }
     */
}