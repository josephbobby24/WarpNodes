package me.joseph.warpnodes.listener;

import me.joseph.warpnodes.WarpNodes;
import org.bukkit.event.Listener;

public class InteractListener implements Listener {

    private WarpNodes plugin;

    public InteractListener(WarpNodes plugin) {
        this.plugin = plugin;
    }

//
//    @EventHandler
//    public void onInteract(PlayerInteractEvent event) {
//        if (!event.getAction().isRightClick()) return;
//        if (event.getClickedBlock() == null) return;
//        if (!event.getClickedBlock().getType().equals(Material.STONE_BUTTON)) return;
//
//        Location location = event.getClickedBlock().getLocation();
//
//        Optional<Warp> warpOptional = this.warps.stream().filter(r -> r.getPadLocationX() == location.getX() && r.getPadLocationY() == location.getY() && r.getPadLocationZ() == location.getZ()).findFirst();
//
//        if (warpOptional.isEmpty()) return;
//
//        Warp warp = warpOptional.get();
//
//        Location toTeleport = new Location(
//                location.getWorld(),
//                warp.getTargetLocationX(),
//                warp.getTargetLocationY(),
//                warp.getTargetLocationZ()
//        );
//
//        WarpUtil.warp(
//                this,
//                event.getClickedBlock().getLocation(),
//                5,
//                3,
//                toTeleport,
//                3
//
//        );
//    }

}
