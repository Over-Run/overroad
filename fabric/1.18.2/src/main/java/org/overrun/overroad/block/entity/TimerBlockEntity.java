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

import net.fabricmc.fabric.api.screenhandler.v1.ExtendedScreenHandlerFactory;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import org.overrun.overroad.menu.TimerBlockMenu;

/**
 * @author squid233
 * @since 0.1.0
 */
public final class TimerBlockEntity extends BlockEntity implements ExtendedScreenHandlerFactory {
    private int timerTick = 0;
    private int timerMin = 0;
    private int timerMax = 400;

    public TimerBlockEntity(BlockPos blockPos, BlockState blockState) {
        super(OverroadBlockEntities.TIMER_BLOCK_ENTITY, blockPos, blockState);
    }

    public void setTimerValue(int timerTick) {
        this.timerTick = timerTick;
        setChanged();
    }

    public void setTimerMin(int timerMin) {
        this.timerMin = timerMin;
        setChanged();
    }

    public void setTimerMax(int timerMax) {
        this.timerMax = timerMax;
        setChanged();
    }

    public int timerValue() {
        return timerTick;
    }

    public int timerMin() {
        return timerMin;
    }

    public int timerMax() {
        return timerMax;
    }

    @Override
    public void writeScreenOpeningData(ServerPlayer player, FriendlyByteBuf buf) {
        buf.writeBlockPos(getBlockPos());
    }

    @Override
    public Component getDisplayName() {
        return new TranslatableComponent(getBlockState().getBlock().getDescriptionId());
    }

    @Override
    public AbstractContainerMenu createMenu(int i, Inventory inventory, Player player) {
        return new TimerBlockMenu(i, getBlockPos(), player);
    }

    @Override
    public void load(CompoundTag tag) {
        super.load(tag);
        timerTick = tag.getInt("timerValue");
        timerMin = tag.getInt("timerMin");
        timerMax = tag.getInt("timerMax");
    }

    @Override
    protected void saveAdditional(CompoundTag tag) {
        tag.putInt("timerValue", timerTick);
        tag.putInt("timerMin", timerMin);
        tag.putInt("timerMax", timerMax);
        super.saveAdditional(tag);
    }

    public static void tick(Level level, BlockPos blockPos, BlockState blockState, TimerBlockEntity blockEntity) {
        if (!level.isClientSide()) {
            blockEntity.timerTick++;
            if (blockEntity.timerTick > blockEntity.timerMax) {
                blockEntity.timerTick = blockEntity.timerMin;
            }
            blockEntity.setChanged();
        }
    }
}
