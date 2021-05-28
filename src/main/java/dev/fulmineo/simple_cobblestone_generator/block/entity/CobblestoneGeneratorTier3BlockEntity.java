package dev.fulmineo.simple_cobblestone_generator.block.entity;

import dev.fulmineo.simple_cobblestone_generator.SimpleCobblestoneGenerator;
import dev.fulmineo.simple_cobblestone_generator.init.CobblestoneGeneratorsInit;

public class CobblestoneGeneratorTier3BlockEntity extends AbstractCobblestoneGeneratorBlockEntity {
	public CobblestoneGeneratorTier3BlockEntity() {
		super(CobblestoneGeneratorsInit.COBBLE_GEN_TIER3_ENTITY, "cobble_gen_tier3", (short)SimpleCobblestoneGenerator.TIERS[2]);
	}
}
