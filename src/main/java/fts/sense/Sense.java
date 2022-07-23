package fts.sense;

import fts.sense.listeners.entchantingListener;
import fts.sense.util.Recipes;
import org.bukkit.Bukkit;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import fts.sense.listeners.blockBreakListener;
public final class Sense extends JavaPlugin {
    private static Sense plugin;
    @Override
    public void onEnable() {
        plugin = this;
        PluginManager pluginManager = Bukkit.getPluginManager();
        (new Recipes()).registerRecipies();
        pluginManager.registerEvents(new blockBreakListener(), getPlugin());
        pluginManager.registerEvents(new entchantingListener(), getPlugin());

    }

    public static Sense getPlugin() {
        return plugin;
    }
}
