package dev.fulmineo.simple_cobblestone_generator;

import dev.fulmineo.simple_cobblestone_generator.screen.CobblestoneGeneratorScreen;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.client.screenhandler.v1.ScreenRegistry;
import net.fabricmc.api.EnvType;

@Environment(EnvType.CLIENT)
public class SimpleCobblestoneGeneratorClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        ScreenRegistry.register(SimpleCobblestoneGenerator.SCREEN_HANDLER, CobblestoneGeneratorScreen::new);
    }
}