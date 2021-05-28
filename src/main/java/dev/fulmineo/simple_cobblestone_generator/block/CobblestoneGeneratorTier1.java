package dev.fulmineo.simple_cobblestone_generator.block;

import dev.fulmineo.simple_cobblestone_generator.block.entity.CobblestoneGeneratorTier1BlockEntity;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.world.BlockView;

public class CobblestoneGeneratorTier1 extends AbstractCobblestoneGenerator {
	public CobblestoneGeneratorTier1(){
		super(1);
	}

	public BlockEntity createBlockEntity(BlockView world) {
		return new CobblestoneGeneratorTier1BlockEntity();
	}
}
