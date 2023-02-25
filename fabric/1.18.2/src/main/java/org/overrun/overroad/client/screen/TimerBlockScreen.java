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

package org.overrun.overroad.client.screen;

import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.components.Button;
import net.minecraft.client.gui.components.EditBox;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.world.entity.player.Inventory;
import org.overrun.overroad.Overroad;
import org.overrun.overroad.block.entity.TimerBlockEntity;
import org.overrun.overroad.client.network.SetTimerMaxPacket;
import org.overrun.overroad.client.network.SetTimerMinPacket;
import org.overrun.overroad.menu.TimerBlockMenu;
import org.overrun.overroad.network.NetworkHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.function.Predicate;
import java.util.regex.Pattern;

/**
 * @author squid233
 * @since 0.1.0
 */
public final class TimerBlockScreen extends AbstractContainerScreen<TimerBlockMenu> {
    private static final Logger LOGGER = LoggerFactory.getLogger(Overroad.NAMESPACE);
    private static final Predicate<String> NUMBER = Pattern.compile("\\d+").asMatchPredicate();
    private final TimerBlockEntity blockEntity;
    private EditBox inputMin;
    private EditBox inputMax;

    public TimerBlockScreen(TimerBlockMenu abstractContainerMenu, Inventory inventory, Component component) {
        super(abstractContainerMenu, inventory, component);
        blockEntity = abstractContainerMenu.blockEntity();
    }

    @Override
    protected void renderBg(PoseStack poseStack, float partialTick, int mouseX, int mouseY) {
    }

    @Override
    public void render(PoseStack poseStack, int mouseX, int mouseY, float partialTick) {
        renderBackground(poseStack);
        super.render(poseStack, mouseX, mouseY, partialTick);
        drawCenteredString(poseStack,
            font,
            new TranslatableComponent(Overroad.narratorKey("timer_block_screen.timer_value"), blockEntity.timerValue()),
            width / 2,
            (height - 20) / 2 - 30,
            0xffffff);
    }

    @Override
    protected void init() {
        super.init();
        titleLabelX = (width - font.width(title)) / 2;
        titleLabelY = (height - 20) / 2 - 60;
        inputMin = new EditBox(font,
            (width - 200) / 2,
            (height - 20) / 2,
            200,
            20,
            new TranslatableComponent(Overroad.narratorKey("timer_block_screen.timer_min")));
        inputMax = new EditBox(font,
            (width - 200) / 2,
            (height - 20) / 2 + 30,
            200,
            20,
            new TranslatableComponent(Overroad.narratorKey("timer_block_screen.timer_max")));
        inputMin.setValue(String.valueOf(blockEntity.timerMin()));
        inputMin.setFilter(NUMBER);
        inputMax.setValue(String.valueOf(blockEntity.timerMax()));
        inputMax.setFilter(NUMBER);
        addRenderableWidget(inputMin);
        addRenderableWidget(inputMax);
        addRenderableWidget(new Button(width / 2 - 100,
            (height - 20) / 2 + 60,
            100,
            20,
            new TranslatableComponent("gui.ok"),
            button -> {
                try {
                    NetworkHandler.sendToServer(NetworkHandler.SET_TIMER_MIN_ID,
                        new SetTimerMinPacket(Integer.parseInt(inputMin.getValue()), blockEntity.getBlockPos()),
                        SetTimerMinPacket::encode);
                    NetworkHandler.sendToServer(NetworkHandler.SET_TIMER_MAX_ID,
                        new SetTimerMaxPacket(Integer.parseInt(inputMax.getValue()), blockEntity.getBlockPos()),
                        SetTimerMaxPacket::encode);
                    onClose();
                } catch (NumberFormatException e) {
                    LOGGER.error("Failed to parse int", e);
                }
            }));
        addRenderableWidget(new Button(width / 2,
            (height - 20) / 2 + 60,
            100,
            20,
            new TranslatableComponent("gui.cancel"),
            button -> onClose()));
    }

    @Override
    public void resize(Minecraft minecraft, int width, int height) {
        final String min = inputMin.getValue();
        final String max = inputMax.getValue();
        super.resize(minecraft, width, height);
        inputMin.setValue(min);
        inputMax.setValue(max);
    }
}
