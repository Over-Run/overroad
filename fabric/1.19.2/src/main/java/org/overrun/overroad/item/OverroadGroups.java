/*
 * MIT License
 *
 * Copyright (c) 2023 Overrun Organization
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 */

package org.overrun.overroad.item;

import net.fabricmc.fabric.api.client.itemgroup.FabricItemGroupBuilder;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.ItemLike;
import org.overrun.overroad.Overroad;
import org.overrun.overroad.block.OverroadBlocks;

import java.util.List;

/**
 * @author squid233
 * @since 0.1.0
 */
public final class OverroadGroups {
    public static final CreativeModeTab ROAD_DECORATIONS = FabricItemGroupBuilder.create(Overroad.identifier("road_decorations"))
        .icon(() -> new ItemStack(OverroadBlocks.ROAD_BLOCK))
        .appendItems(stacks -> appendAll(stacks,
            OverroadBlocks.ROAD_BLOCK,
            OverroadBlocks.CONCRETE_ROAD_BLOCK))
        .build();

    public static void init() {
    }

    private static void appendAll(List<ItemStack> stacks, ItemLike... items) {
        for (ItemLike item : items) {
            stacks.add(new ItemStack(item));
        }
    }
}
