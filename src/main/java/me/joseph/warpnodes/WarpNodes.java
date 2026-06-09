package me.joseph.warpnodes;

import co.aikar.commands.PaperCommandManager;
import lombok.Getter;
import me.joseph.warpnodes.command.WarpCommand;
import me.joseph.warpnodes.listener.InteractListener;
import me.joseph.warpnodes.listener.PlaceListener;
import me.joseph.warpnodes.manager.pad.PadManager;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

@Getter
public final class WarpNodes extends JavaPlugin {

    private PadManager padManager;

    @Override
    public void onEnable() {

        this.padManager = new PadManager(this);

        PaperCommandManager paperCommandManager = new PaperCommandManager(this);
        paperCommandManager.registerCommand(new WarpCommand(this));

        PluginManager pluginManager = this.getServer().getPluginManager();
        pluginManager.registerEvents(new PlaceListener(this), this);
        pluginManager.registerEvents(new InteractListener(this), this);
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }

}
