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

package org.overrun.overroad.menu;

import net.fabricmc.fabric.api.screenhandler.v1.ExtendedScreenHandlerType;
import net.minecraft.core.Registry;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.MenuType;
import org.overrun.overroad.Overroad;

/**
 * @author squid233
 * @since 0.1.0
 */
public final class OverroadMenus {
    public static final MenuType<TimerBlockMenu> TIMER_BLOCK_MENU = register("timer_block_menu", TimerBlockMenu::new);

    public static void init() {
    }

    private static <T extends AbstractContainerMenu> MenuType<T> register(String name, ExtendedScreenHandlerType.ExtendedFactory<T> supplier) {
        return Registry.register(Registry.MENU, Overroad.identifier(name), new ExtendedScreenHandlerType<>(supplier));
    }
}
