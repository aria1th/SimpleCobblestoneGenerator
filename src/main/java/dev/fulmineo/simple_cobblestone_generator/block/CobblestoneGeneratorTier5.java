package dev.fulmineo.simple_cobblestone_generator.block;

import dev.fulmineo.simple_cobblestone_generator.block.entity.CobblestoneGeneratorTier5BlockEntity;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.world.BlockView;

public class CobblestoneGeneratorTier5 extends AbstractCobblestoneGenerator {
	public CobblestoneGeneratorTier5(){
		super(5);
	}

	public BlockEntity createBlockEntity(BlockView world) {
		return new CobblestoneGeneratorTier5BlockEntity();
	}
}
