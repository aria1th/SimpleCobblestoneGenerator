package dev.fulmineo.simple_cobblestone_generator.block;

import dev.fulmineo.simple_cobblestone_generator.block.entity.CobblestoneGeneratorTier2BlockEntity;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.world.BlockView;

public class CobblestoneGeneratorTier2 extends AbstractCobblestoneGenerator {
	public CobblestoneGeneratorTier2(){
		super(2);
	}

	public BlockEntity createBlockEntity(BlockView world) {
		return new CobblestoneGeneratorTier2BlockEntity();
	}
}
