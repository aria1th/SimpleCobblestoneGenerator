package dev.fulmineo.simple_cobblestone_generator.block;

import dev.fulmineo.simple_cobblestone_generator.block.entity.CobblestoneGeneratorTier4BlockEntity;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.world.BlockView;

public class CobblestoneGeneratorTier4 extends AbstractCobblestoneGenerator {
	public CobblestoneGeneratorTier4(){
		super(4);
	}

	public BlockEntity createBlockEntity(BlockView world) {
		return new CobblestoneGeneratorTier4BlockEntity();
	}
}
