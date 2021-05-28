package dev.fulmineo.simple_cobblestone_generator.block.entity;

import dev.fulmineo.simple_cobblestone_generator.SimpleCobblestoneGenerator;
import dev.fulmineo.simple_cobblestone_generator.init.CobblestoneGeneratorsInit;

public class CobblestoneGeneratorTier2BlockEntity extends AbstractCobblestoneGeneratorBlockEntity {
	public CobblestoneGeneratorTier2BlockEntity() {
		super(CobblestoneGeneratorsInit.COBBLE_GEN_TIER2_ENTITY, "cobble_gen_tier2", (short)SimpleCobblestoneGenerator.TIERS[1]);
	}
}
