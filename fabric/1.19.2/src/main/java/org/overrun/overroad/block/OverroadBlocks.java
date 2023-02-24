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

package org.overrun.overroad.block;

import net.minecraft.core.Registry;
import net.minecraft.world.item.DyeColor;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.Material;
import net.minecraft.world.level.material.MaterialColor;
import org.overrun.overroad.Overroad;

import java.util.function.Function;

/**
 * @author squid233
 * @since 0.1.0
 */
public final class OverroadBlocks {
    private static final Material LINE = new Material.Builder(MaterialColor.SNOW).noCollider().nonSolid().build();
    public static final Block ROAD_BLOCK = register("road_block",
        new Block(BlockBehaviour.Properties.of(Material.STONE, DyeColor.BLACK)));
    public static final Block CONCRETE_ROAD_BLOCK = register("concrete_road_block",
        new Block(BlockBehaviour.Properties.of(Material.STONE, DyeColor.LIGHT_GRAY)));
    public static final Block LINE_STRAIGHT = lineBlock("line_straight", StraightLineBlock::new);
    public static final Block LINE_CORNER = lineBlock("line_corner");
    public static final Block LINE_DIAGONAL = lineBlock("line_diagonal");
    public static final Block LINE_CROSS = lineBlock("line_cross", LineBlock::new);
    public static final Block LINE_T = lineBlock("line_t");
    public static final Block LINE_FORK_LEFT = lineBlock("line_fork_left");
    public static final Block LINE_FORK_RIGHT = lineBlock("line_fork_right");
    public static final Block LINE_BEND_LEFT = lineBlock("line_bend_left");
    public static final Block LINE_BEND_RIGHT = lineBlock("line_bend_right");
    public static final Block THICK_LINE_STRAIGHT = lineBlock("thick_line_straight", StraightLineBlock::new);

    public static void init() {
    }

    private static Block lineBlock(String name, Function<BlockBehaviour.Properties, Block> block) {
        return register(name, block.apply(BlockBehaviour.Properties.of(LINE,
                state -> state.getValue(LineBlock.YELLOW)
                    ? MaterialColor.COLOR_YELLOW
                    : MaterialColor.SNOW)
            .noCollission()));
    }

    private static Block lineBlock(String name) {
        return lineBlock(name, FacingLineBlock::new);
    }

    private static Block register(String name, Block block) {
        return Registry.register(Registry.BLOCK, Overroad.identifier(name), block);
    }
}
