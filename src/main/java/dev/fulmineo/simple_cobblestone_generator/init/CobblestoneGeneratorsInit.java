package dev.fulmineo.simple_cobblestone_generator.init;

import dev.fulmineo.simple_cobblestone_generator.SimpleCobblestoneGenerator;
import dev.fulmineo.simple_cobblestone_generator.block.AbstractCobblestoneGenerator;
import dev.fulmineo.simple_cobblestone_generator.block.CobblestoneGeneratorTier1;
import dev.fulmineo.simple_cobblestone_generator.block.CobblestoneGeneratorTier2;
import dev.fulmineo.simple_cobblestone_generator.block.CobblestoneGeneratorTier3;
import dev.fulmineo.simple_cobblestone_generator.block.CobblestoneGeneratorTier4;
import dev.fulmineo.simple_cobblestone_generator.block.CobblestoneGeneratorTier5;
import dev.fulmineo.simple_cobblestone_generator.block.entity.CobblestoneGeneratorTier1BlockEntity;
import dev.fulmineo.simple_cobblestone_generator.block.entity.CobblestoneGeneratorTier2BlockEntity;
import dev.fulmineo.simple_cobblestone_generator.block.entity.CobblestoneGeneratorTier3BlockEntity;
import dev.fulmineo.simple_cobblestone_generator.block.entity.CobblestoneGeneratorTier4BlockEntity;
import dev.fulmineo.simple_cobblestone_generator.block.entity.CobblestoneGeneratorTier5BlockEntity;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.util.registry.Registry;

public class CobblestoneGeneratorsInit {

	public static final AbstractCobblestoneGenerator COBBLE_GEN_TIER1 = new CobblestoneGeneratorTier1();
	public static final AbstractCobblestoneGenerator COBBLE_GEN_TIER2 = new CobblestoneGeneratorTier2();
	public static final AbstractCobblestoneGenerator COBBLE_GEN_TIER3 = new CobblestoneGeneratorTier3();
	public static final AbstractCobblestoneGenerator COBBLE_GEN_TIER4 = new CobblestoneGeneratorTier4();
	public static final AbstractCobblestoneGenerator COBBLE_GEN_TIER5 = new CobblestoneGeneratorTier5();

	public static final BlockEntityType<?> COBBLE_GEN_TIER1_ENTITY = BlockEntityType.Builder.create(CobblestoneGeneratorTier1BlockEntity::new, COBBLE_GEN_TIER1).build(null);
	public static final BlockEntityType<?> COBBLE_GEN_TIER2_ENTITY = BlockEntityType.Builder.create(CobblestoneGeneratorTier2BlockEntity::new, COBBLE_GEN_TIER2).build(null);
	public static final BlockEntityType<?> COBBLE_GEN_TIER3_ENTITY = BlockEntityType.Builder.create(CobblestoneGeneratorTier3BlockEntity::new, COBBLE_GEN_TIER3).build(null);
	public static final BlockEntityType<?> COBBLE_GEN_TIER4_ENTITY = BlockEntityType.Builder.create(CobblestoneGeneratorTier4BlockEntity::new, COBBLE_GEN_TIER4).build(null);
	public static final BlockEntityType<?> COBBLE_GEN_TIER5_ENTITY = BlockEntityType.Builder.create(CobblestoneGeneratorTier5BlockEntity::new, COBBLE_GEN_TIER5).build(null);

	public static void init(){
		register(COBBLE_GEN_TIER1, COBBLE_GEN_TIER1_ENTITY);
		register(COBBLE_GEN_TIER2, COBBLE_GEN_TIER2_ENTITY);
		register(COBBLE_GEN_TIER3, COBBLE_GEN_TIER3_ENTITY);
		register(COBBLE_GEN_TIER4, COBBLE_GEN_TIER4_ENTITY);
		register(COBBLE_GEN_TIER5, COBBLE_GEN_TIER5_ENTITY);
	}

	private static void register(AbstractCobblestoneGenerator generator, BlockEntityType<?> entityType){
		Registry.register(Registry.BLOCK, generator.getIdentifier(), generator);
		Registry.register(Registry.BLOCK_ENTITY_TYPE, generator.getIdentifier(), entityType);
		Registry.register(Registry.ITEM, generator.getIdentifier(), new BlockItem(generator, new Item.Settings().maxCount(64).group(SimpleCobblestoneGenerator.GROUP)));
	}
}
