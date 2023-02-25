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

package org.overrun.overroad.network;

import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.fabricmc.fabric.api.networking.v1.PacketByteBufs;
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import net.minecraft.core.BlockPos;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import org.overrun.overroad.Overroad;
import org.overrun.overroad.client.network.SetTimerMaxPacket;
import org.overrun.overroad.client.network.SetTimerMinPacket;
import org.overrun.overroad.menu.TimerBlockMenu;

import java.util.function.BiConsumer;
import java.util.function.Function;

/**
 * @author squid233
 * @since 0.1.0
 */
public final class NetworkHandler {
    public static final ResourceLocation SET_TIMER_MIN_ID = Overroad.identifier("set_timer_min");
    public static final ResourceLocation SET_TIMER_MAX_ID = Overroad.identifier("set_timer_max");

    public static void init() {
        register(SET_TIMER_MIN_ID,
            SetTimerMinPacket::decode,
            SetTimerMinPacket::handle);
        register(SET_TIMER_MAX_ID,
            SetTimerMaxPacket::decode,
            SetTimerMaxPacket::handle);
    }

    public static <T> void sendToServer(ResourceLocation id,
                                        T msg,
                                        BiConsumer<T, FriendlyByteBuf> encoding) {
        final FriendlyByteBuf buf = PacketByteBufs.create();
        encoding.accept(msg, buf);
        ClientPlayNetworking.send(id, buf);
    }

    public static boolean isBadClientPacket(ServerPlayer player, BlockPos pos) {
        if (player == null || player.isDeadOrDying() || player.isRemoved()) {
            return true;
        }
        final ServerLevel level = player.getLevel();
        if (!level.isLoaded(pos)) {
            return true;
        }
        if (!(player.containerMenu instanceof TimerBlockMenu menu)) {
            return true;
        }
        return !menu.pos().equals(pos);
    }

    private static <T> void register(ResourceLocation id,
                                     Function<FriendlyByteBuf, T> decoding,
                                     BiConsumer<T, ServerPlayer> handling) {
        ServerPlayNetworking.registerGlobalReceiver(
            id,
            (server, player, handler, buf, responseSender) -> {
                final T msg = decoding.apply(buf);
                server.execute(() -> handling.accept(msg, player));
            }
        );
    }
}
