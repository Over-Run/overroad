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

package org.overrun.overroad.data;

import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricModelProvider;
import net.minecraft.data.models.BlockModelGenerators;
import net.minecraft.data.models.ItemModelGenerators;
import net.minecraft.data.models.model.ModelTemplates;
import org.overrun.overroad.block.OverroadBlocks;

import static org.overrun.overroad.item.OverroadItems.*;
import static org.overrun.overroad.item.OverroadItems.THICK_LINE_STRAIGHT;

/**
 * @author squid233
 * @since 0.1.0
 */
public final class OverroadModelGen extends FabricModelProvider {
    public OverroadModelGen(FabricDataGenerator generator) {
        super(generator);
    }

    @Override
    public void generateBlockStateModels(BlockModelGenerators blockStateModelGenerator) {
        blockStateModelGenerator.createTrivialCube(OverroadBlocks.ROAD_BLOCK);
        blockStateModelGenerator.createTrivialCube(OverroadBlocks.CONCRETE_ROAD_BLOCK);
        blockStateModelGenerator.createTrivialCube(OverroadBlocks.TIMER_BLOCK);
    }

    @Override
    public void generateItemModels(ItemModelGenerators itemModelGenerator) {
        itemModelGenerator.generateFlatItem(ROAD_TOOL, ModelTemplates.FLAT_ITEM);
        itemModelGenerator.generateFlatItem(LINE_STRAIGHT, ModelTemplates.FLAT_ITEM);
        itemModelGenerator.generateFlatItem(LINE_CORNER, ModelTemplates.FLAT_ITEM);
        itemModelGenerator.generateFlatItem(LINE_DIAGONAL, ModelTemplates.FLAT_ITEM);
        itemModelGenerator.generateFlatItem(LINE_CROSS, ModelTemplates.FLAT_ITEM);
        itemModelGenerator.generateFlatItem(LINE_T, ModelTemplates.FLAT_ITEM);
        itemModelGenerator.generateFlatItem(LINE_FORK_LEFT, ModelTemplates.FLAT_ITEM);
        itemModelGenerator.generateFlatItem(LINE_FORK_RIGHT, ModelTemplates.FLAT_ITEM);
        itemModelGenerator.generateFlatItem(LINE_BEND_LEFT, ModelTemplates.FLAT_ITEM);
        itemModelGenerator.generateFlatItem(LINE_BEND_RIGHT, ModelTemplates.FLAT_ITEM);
        itemModelGenerator.generateFlatItem(THICK_LINE_STRAIGHT, ModelTemplates.FLAT_ITEM);
    }
}
