package dev.fulmineo.simple_cobblestone_generator.screen;

import com.mojang.blaze3d.systems.RenderSystem;

import dev.fulmineo.simple_cobblestone_generator.SimpleCobblestoneGenerator;
import net.minecraft.client.gui.screen.ingame.HandledScreen;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;

public class CobblestoneGeneratorScreen extends HandledScreen<CobblestoneGeneratorScreenHandler> {
	private static final Identifier TEXTURE = new Identifier(SimpleCobblestoneGenerator.MOD_ID, "textures/gui/container/generator.png");

   	public CobblestoneGeneratorScreen(CobblestoneGeneratorScreenHandler handler, PlayerInventory inventory, Text title) {
		super(handler, inventory, title);

		this.passEvents = false;
   	}

	protected void drawBackground(MatrixStack matrices, float delta, int mouseX, int mouseY) {
		RenderSystem.color4f(1.0F, 1.0F, 1.0F, 1.0F);
		this.client.getTextureManager().bindTexture(TEXTURE);
		int i = (this.width - this.backgroundWidth) / 2;
		int j = (this.height - this.backgroundHeight) / 2;

		// Draws the background
		this.drawTexture(matrices, i, j, 0, 0, this.backgroundWidth, this.backgroundHeight);
	}

	public void render(MatrixStack matrices, int mouseX, int mouseY, float delta) {
		this.renderBackground(matrices);
		super.render(matrices, mouseX, mouseY, delta);
		this.drawMouseoverTooltip(matrices, mouseX, mouseY);
	}
}
