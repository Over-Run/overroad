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

package org.overrun.overroad;

import net.fabricmc.api.ModInitializer;
import net.minecraft.resources.ResourceLocation;
import org.overrun.overroad.block.OverroadBlocks;
import org.overrun.overroad.block.entity.OverroadBlockEntities;
import org.overrun.overroad.item.OverroadGroups;
import org.overrun.overroad.item.OverroadItems;
import org.overrun.overroad.menu.OverroadMenus;
import org.overrun.overroad.network.NetworkHandler;

/**
 * @author squid233
 * @since 0.1.0
 */
public final class Overroad implements ModInitializer {
    public static final String NAMESPACE = "overroad";

    public static ResourceLocation identifier(String path) {
        return new ResourceLocation(NAMESPACE, path);
    }

    public static String narratorKey(String path) {
        return "narration." + NAMESPACE + '.' + path;
    }

    @Override
    public void onInitialize() {
        OverroadBlocks.init();
        OverroadBlockEntities.init();
        OverroadItems.init();
        OverroadGroups.init();
        OverroadMenus.init();
        NetworkHandler.init();
    }
}
