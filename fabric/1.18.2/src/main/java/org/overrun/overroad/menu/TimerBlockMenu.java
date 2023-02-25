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

import net.minecraft.core.BlockPos;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.ContainerLevelAccess;
import net.minecraft.world.item.ItemStack;
import org.jetbrains.annotations.NotNull;
import org.overrun.overroad.block.entity.TimerBlockEntity;

/**
 * @author squid233
 * @since 0.1.0
 */
public final class TimerBlockMenu extends AbstractContainerMenu {
    private final BlockPos pos;
    private TimerBlockEntity blockEntity;

    public TimerBlockMenu(int i, Inventory inventory, FriendlyByteBuf buf) {
        this(i, buf.readBlockPos(), inventory.player);
    }

    public TimerBlockMenu(int i, BlockPos pos, Player player) {
        super(OverroadMenus.TIMER_BLOCK_MENU, i);
        this.pos = pos;
        if (player.getLevel().getBlockEntity(pos) instanceof TimerBlockEntity entity) {
            this.blockEntity = entity;
        }
    }

    public TimerBlockEntity blockEntity() {
        return blockEntity;
    }

    public BlockPos pos() {
        return pos;
    }

    @Override
    public @NotNull ItemStack quickMoveStack(Player player, int index) {
        return ItemStack.EMPTY;
    }

    @Override
    public boolean stillValid(Player player) {
        return stillValid(ContainerLevelAccess.create(player.getLevel(), blockEntity.getBlockPos()),
            player,
            blockEntity.getBlockState().getBlock());
    }
}
