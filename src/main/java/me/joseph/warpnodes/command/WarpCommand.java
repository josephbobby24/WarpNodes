package me.joseph.warpnodes.command;

import co.aikar.commands.BaseCommand;
import co.aikar.commands.annotation.CommandAlias;
import co.aikar.commands.annotation.Subcommand;
import me.joseph.warpnodes.WarpNodes;
import me.joseph.warpnodes.manager.pad.Pad;
import org.bukkit.entity.Player;

@CommandAlias("warp")
public class WarpCommand extends BaseCommand {

    private final WarpNodes plugin;

    public WarpCommand(WarpNodes plugin) {
        this.plugin = plugin;
    }

    @Subcommand("item")
    public void item(Player player) {
        player.getInventory().addItem(
                this.plugin.getPadManager().getItem()
        );
    }

    @Subcommand("link")
    public void linkPads(Player player, int padId, int targetPadId) {
        Pad pad = this.plugin.getPadManager().getPad(padId);
        Pad target = this.plugin.getPadManager().getPad(targetPadId);

        if (pad == null) return;
        if (target == null) return;
        if (!pad.getOwnerId().toString().equals(target.getOwnerId().toString())) return;

        this.plugin.getPadManager().addTargetPad(pad, target);
        this.plugin.getPadManager().addTargetPad(target, pad);
        player.sendMessage("Linked pads " + pad.getPadId() + " and " + target.getPadId());
    }

}
