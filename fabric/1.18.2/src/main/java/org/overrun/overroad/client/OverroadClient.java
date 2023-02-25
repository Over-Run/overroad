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

package org.overrun.overroad.client;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.blockrenderlayer.v1.BlockRenderLayerMap;
import net.minecraft.client.gui.screens.MenuScreens;
import net.minecraft.client.renderer.RenderType;
import org.overrun.overroad.client.screen.TimerBlockScreen;
import org.overrun.overroad.menu.OverroadMenus;

import static org.overrun.overroad.block.OverroadBlocks.*;

/**
 * @author squid233
 * @since 0.1.0
 */
public final class OverroadClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        BlockRenderLayerMap.INSTANCE.putBlocks(RenderType.cutout(),
            LINE_STRAIGHT,
            LINE_CORNER,
            LINE_DIAGONAL,
            LINE_CROSS,
            LINE_T,
            LINE_FORK_LEFT,
            LINE_FORK_RIGHT,
            LINE_BEND_LEFT,
            LINE_BEND_RIGHT,
            THICK_LINE_STRAIGHT
        );
        MenuScreens.register(OverroadMenus.TIMER_BLOCK_MENU, TimerBlockScreen::new);
    }
}
