package dev.fulmineo.simple_cobblestone_generator.block.entity;

import dev.fulmineo.simple_cobblestone_generator.SimpleCobblestoneGenerator;
import dev.fulmineo.simple_cobblestone_generator.init.CobblestoneGeneratorsInit;

public class CobblestoneGeneratorTier1BlockEntity extends AbstractCobblestoneGeneratorBlockEntity {
	public CobblestoneGeneratorTier1BlockEntity() {
		super(CobblestoneGeneratorsInit.COBBLE_GEN_TIER1_ENTITY, "cobble_gen_tier1", (short)SimpleCobblestoneGenerator.TIERS[0]);
	}
}
