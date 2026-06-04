package me.joseph.warpnodes.command;

import co.aikar.commands.BaseCommand;
import co.aikar.commands.annotation.CommandAlias;
import co.aikar.commands.annotation.Subcommand;
import me.joseph.warpnodes.WarpNodes;
import me.joseph.warpnodes.warp.Warp;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

@CommandAlias("warp")
public class WarpCommand extends BaseCommand {

    private WarpNodes plugin;

    public WarpCommand(WarpNodes plugin) {
        this.plugin = plugin;
    }

    @Subcommand("setup")
    public void setup(Player player) {

        this.plugin.setSetup(player.getUniqueId(), new Warp());
        player.sendMessage("Started warp setup");
        player.sendMessage("Place a stone button for platform");
        player.sendMessage("/warp set target to set the target location");
        player.sendMessage("/warp finish to finish");
    }

    @Subcommand("set target")
    public void setTarget(Player player) {
        Warp warp = this.plugin.getSetup(player.getUniqueId());

        if (warp == null) {
            player.sendMessage("Error!");
            return;
        }

        Location location = player.getLocation();

        warp.setTargetLocationX(location.getX());
        warp.setTargetLocationY(location.getY());
        warp.setTargetLocationZ(location.getZ());

        this.plugin.setSetup(player.getUniqueId(), warp);
        player.sendMessage("Set!");
    }

    @Subcommand("finish")
    public void finish(Player player) {
        Warp warp = this.plugin.getSetup(player.getUniqueId());

        if (warp == null) {
            player.sendMessage("Error!");
            return;
        }

        this.plugin.removeSetup(player.getUniqueId());
        this.plugin.addWarp(warp);
        player.sendMessage("Done!");
        this.plugin.getLogger().info("P: " + warp.getPadLocationX() + ":" + warp.getPadLocationY() + ":" + warp.getPadLocationZ());
        this.plugin.getLogger().info("T: " + warp.getTargetLocationX() + ":" + warp.getTargetLocationY() + ":" + warp.getTargetLocationZ());
    }
}
