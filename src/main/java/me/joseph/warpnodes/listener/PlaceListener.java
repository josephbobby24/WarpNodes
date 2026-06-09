package me.joseph.warpnodes.listener;

import me.joseph.warpnodes.WarpNodes;
import me.joseph.warpnodes.manager.util.WarpUtil;
import net.kyori.adventure.text.Component;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPlaceEvent;

public class PlaceListener implements Listener {

    private WarpNodes plugin;

    public PlaceListener(WarpNodes plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onBlockPlace(BlockPlaceEvent event) {
        Block block = event.getBlockPlaced();

        if (block.getType().equals(Material.BEDROCK)) {
            int id = this.plugin.getPadManager().createPad(event.getPlayer(), block.getLocation());

            event.getPlayer().sendMessage(Component.text("Successfully created a pad with the ID " + id));
        }
     }
}
