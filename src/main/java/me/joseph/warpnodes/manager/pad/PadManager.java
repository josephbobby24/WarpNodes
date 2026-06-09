package me.joseph.warpnodes.manager.pad;

import dev.triumphteam.gui.guis.Gui;
import dev.triumphteam.gui.guis.GuiItem;
import me.joseph.warpnodes.WarpNodes;
import me.joseph.warpnodes.manager.util.WarpUtil;
import net.kyori.adventure.text.Component;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.HashMap;
import java.util.List;

public class PadManager {

    private WarpNodes plugin;
    private final HashMap<Integer, Pad> pads = new HashMap<>();

    public PadManager(WarpNodes plugin) {
        this.plugin = plugin;
    }

    public int createPad(Player player, Location location) {
        Pad pad = new Pad();

        pad.setX(location.getX());
        pad.setY(location.getY());
        pad.setZ(location.getZ());
        pad.setPadId(this.pads.size() + 1);
        pad.setOwnerId(player.getUniqueId());

        this.pads.put(pad.getPadId(), pad);
        return pad.getPadId();
    }

    public void addTargetPad(Pad pad, Pad target) {
        pad.getTargetPads().add(target.getPadId());
    }

    public Pad getPad(int id) {
        return this.pads.get(id);
    }

    public void showGui(Player player, Pad pad) {
        List<Pad> targets = pad
                .getTargetPads()
                .stream()
                .map(r -> this.plugin.getPadManager().getPad(r))
                .toList();

        Gui gui = Gui.gui()
                .title(
                        Component.text("Choose your Destination")
                )
                .rows(6)
                .create();

        targets.forEach(r -> {
            Location targetLocation = new Location(player.getWorld(), r.getX(), r.getY(), r.getZ());

            ItemStack itemStack = new ItemStack(Material.GRAY_WOOL);
            ItemMeta meta = itemStack.getItemMeta();

            meta.displayName(Component.text("Pad " + r.getPadId()));

            itemStack.setItemMeta(meta);

            GuiItem item = new GuiItem(itemStack);
            item.setAction(e -> {
                if (pad.isWarping()) {
                    return;
                }
                e.getClickedInventory().close();
                this.setWarping(pad, true);
                WarpUtil.warp(
                        this.plugin,
                        e.getWhoClicked().getLocation(),
                        5,
                        3,
                        targetLocation,
                        3
                );
            });

            gui.setDefaultClickAction(e -> e.setCancelled(true));
            gui.addItem(
                    item
            );
        });

        gui.open(player);
    }

    public void setWarping(Pad pad, boolean warping) {
        pad.setWarping(warping);
    }
}
