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

import net.minecraft.core.Registry;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import org.overrun.overroad.block.OverroadBlocks;

/**
 * @author squid233
 * @since 0.1.0
 */
public final class OverroadItems {
    public static final Item ROAD_BLOCK = register(OverroadBlocks.ROAD_BLOCK);
    public static final Item CONCRETE_ROAD_BLOCK = register(OverroadBlocks.CONCRETE_ROAD_BLOCK);

    public static void init() {
    }

    private static Item register(Block block) {
        return Registry.register(Registry.ITEM, Registry.BLOCK.getKey(block), new BlockItem(block, new Item.Properties()));
    }
}
