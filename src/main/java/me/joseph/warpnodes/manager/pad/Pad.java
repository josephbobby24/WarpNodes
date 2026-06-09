package me.joseph.warpnodes.manager.pad;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Getter @Setter
public class Pad {

    private int padId;
    final List<Integer> targetPads = new ArrayList<>();

    private UUID ownerId;
    private double x;
    private double y;
    private double z;
}
