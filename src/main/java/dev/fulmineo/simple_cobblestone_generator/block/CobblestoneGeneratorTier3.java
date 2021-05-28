package dev.fulmineo.simple_cobblestone_generator.block;

import dev.fulmineo.simple_cobblestone_generator.block.entity.CobblestoneGeneratorTier3BlockEntity;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.world.BlockView;

public class CobblestoneGeneratorTier3 extends AbstractCobblestoneGenerator {
	public CobblestoneGeneratorTier3(){
		super(3);
	}

	public BlockEntity createBlockEntity(BlockView world) {
		return new CobblestoneGeneratorTier3BlockEntity();
	}
}
