package me.joseph.warpnodes.listener;

import me.joseph.warpnodes.WarpNodes;
import net.kyori.adventure.text.Component;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.block.Block;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataType;

public class PlaceListener implements Listener {

    private WarpNodes plugin;

    public PlaceListener(WarpNodes plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onPlace(BlockPlaceEvent event) {
        ItemMeta meta = event.getItemInHand().getItemMeta();

        if (meta == null) return;

        if (!meta.getPersistentDataContainer().has(
                new NamespacedKey(plugin, "warp_item"),
                PersistentDataType.INTEGER
        )) return;

        Block block = event.getBlockPlaced();

        plugin.getPadManager().createPad(event.getPlayer(), block.getLocation());

        event.getPlayer().sendMessage(Component.text("Warp pad created!"));
    }
}
