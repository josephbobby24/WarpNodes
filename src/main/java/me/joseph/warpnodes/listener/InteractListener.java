package me.joseph.warpnodes.listener;

import me.joseph.warpnodes.WarpNodes;
import me.joseph.warpnodes.manager.pad.Pad;
import me.joseph.warpnodes.manager.util.WarpUtil;
import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;

import java.util.Optional;

public class InteractListener implements Listener {

    private WarpNodes plugin;

    public InteractListener(WarpNodes plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onInteract(PlayerInteractEvent event) {
        if (!event.getAction().isRightClick()) return;

        if (!event.getClickedBlock().getType().equals(Material.STONE_BUTTON)) return;

        int x = event.getClickedBlock().getX();
        int y = event.getClickedBlock().getY();
        int z = event.getClickedBlock().getZ();

        Optional<Pad> pad = this.plugin.getPadManager().getPad(x, y, z);

        if (pad.isEmpty()) return;

        this.plugin.getPadManager().showGui(event.getPlayer(), pad.get());
    }
}
