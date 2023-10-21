package net.phoenix.uninvisibleentities;

import com.mojang.brigadier.builder.LiteralArgumentBuilder;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.command.v2.ClientCommandRegistrationCallback;
import net.fabricmc.fabric.api.client.command.v2.FabricClientCommandSource;

import static net.fabricmc.fabric.api.client.command.v2.ClientCommandManager.literal;

public class InvisMod implements ClientModInitializer {
    public static boolean invis = true;
    public static boolean glow = true;
    public static LiteralArgumentBuilder<FabricClientCommandSource> build(){return literal("invis").then(literal("glow").executes(context -> {glow = !glow; return 0;})).executes(context -> {invis = !invis; return 0;});}
    @Override public void onInitializeClient() {ClientCommandRegistrationCallback.EVENT.register((dispatcher, registryAccess) -> dispatcher.register(build()));}
}
