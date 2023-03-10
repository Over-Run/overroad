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

package org.overrun.overroad.block.entity;

import net.fabricmc.fabric.api.object.builder.v1.block.entity.FabricBlockEntityTypeBuilder;
import net.minecraft.core.Registry;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import org.overrun.overroad.Overroad;
import org.overrun.overroad.block.OverroadBlocks;

/**
 * @author squid233
 * @since 0.1.0
 */
public final class OverroadBlockEntities {
    public static final BlockEntityType<TimerBlockEntity> TIMER_BLOCK_ENTITY =
        register("timer_block", TimerBlockEntity::new, OverroadBlocks.TIMER_BLOCK);

    public static void init() {
    }

    private static <T extends BlockEntity> BlockEntityType<T> register(String name,
                                                                       FabricBlockEntityTypeBuilder.Factory<T> factory,
                                                                       Block... validBlocks) {
        return Registry.register(Registry.BLOCK_ENTITY_TYPE,
            Overroad.identifier(name),
            FabricBlockEntityTypeBuilder.create(factory, validBlocks).build(null));
    }
}
