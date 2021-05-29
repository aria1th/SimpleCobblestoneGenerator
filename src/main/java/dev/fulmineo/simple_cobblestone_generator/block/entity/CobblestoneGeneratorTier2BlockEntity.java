package dev.fulmineo.simple_cobblestone_generator.block.entity;

import dev.fulmineo.simple_cobblestone_generator.SimpleCobblestoneGenerator;
import dev.fulmineo.simple_cobblestone_generator.init.CobblestoneGeneratorsInit;
import net.minecraft.block.BlockState;
import net.minecraft.util.math.BlockPos;

public class CobblestoneGeneratorTier2BlockEntity extends AbstractCobblestoneGeneratorBlockEntity {
	public CobblestoneGeneratorTier2BlockEntity(BlockPos pos, BlockState state) {
		super(CobblestoneGeneratorsInit.COBBLE_GEN_TIER2_ENTITY, pos, state, "cobble_gen_tier2", (short)SimpleCobblestoneGenerator.TIERS[1]);
	}
}
