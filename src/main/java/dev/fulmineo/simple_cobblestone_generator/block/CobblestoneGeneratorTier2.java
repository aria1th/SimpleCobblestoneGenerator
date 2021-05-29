package dev.fulmineo.simple_cobblestone_generator.block;

import org.jetbrains.annotations.Nullable;

import dev.fulmineo.simple_cobblestone_generator.block.entity.CobblestoneGeneratorTier2BlockEntity;
import dev.fulmineo.simple_cobblestone_generator.init.CobblestoneGeneratorsInit;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.BlockEntityTicker;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class CobblestoneGeneratorTier2 extends AbstractCobblestoneGenerator {
	public CobblestoneGeneratorTier2(){
		super(2);
	}

	public BlockEntity createBlockEntity(BlockPos pos, BlockState state) {
		return new CobblestoneGeneratorTier2BlockEntity(pos, state);
	}

	@Nullable
	public <T extends BlockEntity> BlockEntityTicker<T> getTicker(World world, BlockState state, BlockEntityType<T> type) {
	   return checkType(world, type, CobblestoneGeneratorsInit.COBBLE_GEN_TIER2_ENTITY);
	}
}
