package dev.fulmineo.simple_cobblestone_generator.block.entity;

import dev.fulmineo.simple_cobblestone_generator.SimpleCobblestoneGenerator;
import dev.fulmineo.simple_cobblestone_generator.init.CobblestoneGeneratorsInit;

public class CobblestoneGeneratorTier4BlockEntity extends AbstractCobblestoneGeneratorBlockEntity {
	public CobblestoneGeneratorTier4BlockEntity() {
		super(CobblestoneGeneratorsInit.COBBLE_GEN_TIER4_ENTITY, "cobble_gen_tier4", (short)SimpleCobblestoneGenerator.TIERS[3]);
	}
}
