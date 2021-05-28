package dev.fulmineo.simple_cobblestone_generator.block.entity;

import dev.fulmineo.simple_cobblestone_generator.SimpleCobblestoneGenerator;
import dev.fulmineo.simple_cobblestone_generator.init.CobblestoneGeneratorsInit;

public class CobblestoneGeneratorTier5BlockEntity extends AbstractCobblestoneGeneratorBlockEntity {
	public CobblestoneGeneratorTier5BlockEntity() {
		super(CobblestoneGeneratorsInit.COBBLE_GEN_TIER5_ENTITY, "cobble_gen_tier5", (short)SimpleCobblestoneGenerator.TIERS[4]);
	}
}
