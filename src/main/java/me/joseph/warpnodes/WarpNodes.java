package me.joseph.warpnodes;

import co.aikar.commands.PaperCommandManager;
import me.joseph.warpnodes.command.WarpCommand;
import me.joseph.warpnodes.util.WarpUtil;
import me.joseph.warpnodes.warp.Warp;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Optional;
import java.util.UUID;

public final class WarpNodes extends JavaPlugin implements Listener {

    private final ArrayList<Warp> warps = new ArrayList<>();
    private final HashMap<UUID, Warp> warpSetup = new HashMap<>();

    @Override
    public void onEnable() {
        // Plugin startup logic
        PaperCommandManager pm = new PaperCommandManager(this);

        pm.registerCommand(new WarpCommand(this));

        PluginManager pluginManager = this.getServer().getPluginManager();
        pluginManager.registerEvents(this, this);
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }

    public void setSetup(UUID uuid, Warp warp) {
        this.warpSetup.put(uuid, warp);
    }

    public void removeSetup(UUID uuid) {
        this.warpSetup.remove(uuid);
    }

    public Warp getSetup(UUID uuid) {
        return this.warpSetup.get(uuid);
    }

    public void addWarp(Warp warp) {
        this.warps.add(warp);
    }

    @EventHandler
    public void onPlace(BlockPlaceEvent event) {
        if (!event.getBlockPlaced().getType().equals(Material.STONE_BUTTON)) return;
        Warp warp = this.warpSetup.get(event.getPlayer().getUniqueId());

        if (warp == null) return;

        warp.setPadLocationX(event.getBlockPlaced().getX());
        warp.setPadLocationY(event.getBlockPlaced().getY());
        warp.setPadLocationZ(event.getBlockPlaced().getZ());

        this.warpSetup.put(event.getPlayer().getUniqueId(), warp);
        event.getPlayer().sendMessage("Set!");
    }

    @EventHandler
    public void onInteract(PlayerInteractEvent event) {
        if (!event.getAction().isRightClick()) return;
        if (event.getClickedBlock() == null) return;
        if (!event.getClickedBlock().getType().equals(Material.STONE_BUTTON)) return;

        Location location = event.getClickedBlock().getLocation();

        Optional<Warp> warpOptional = this.warps.stream().filter(r -> r.getPadLocationX() == location.getX() && r.getPadLocationY() == location.getY() && r.getPadLocationZ() == location.getZ()).findFirst();

        if (warpOptional.isEmpty()) return;

        Warp warp = warpOptional.get();

        Location toTeleport = new Location(
                location.getWorld(),
                warp.getTargetLocationX(),
                warp.getTargetLocationY(),
                warp.getTargetLocationZ()
        );

        WarpUtil.warp(
                this,
                event.getClickedBlock().getLocation(),
                5,
                3,
                toTeleport,
                3

        );
    }
}
