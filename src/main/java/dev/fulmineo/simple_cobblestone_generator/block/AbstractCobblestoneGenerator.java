package dev.fulmineo.simple_cobblestone_generator.block;

import java.util.List;

import org.jetbrains.annotations.Nullable;

import dev.fulmineo.simple_cobblestone_generator.SimpleCobblestoneGenerator;
import dev.fulmineo.simple_cobblestone_generator.block.entity.AbstractCobblestoneGeneratorBlockEntity;
import dev.fulmineo.simple_cobblestone_generator.screen.CobblestoneGeneratorScreenHandler;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.fabricmc.fabric.api.screenhandler.v1.ExtendedScreenHandlerFactory;
import net.fabricmc.fabric.api.tool.attribute.v1.FabricToolTags;
import net.minecraft.block.Block;
import net.minecraft.block.BlockRenderType;
import net.minecraft.block.BlockState;
import net.minecraft.block.BlockWithEntity;
import net.minecraft.block.Blocks;
import net.minecraft.block.HorizontalFacingBlock;
import net.minecraft.block.Material;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.BlockEntityTicker;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.client.item.TooltipContext;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.Inventory;
import net.minecraft.item.ItemPlacementContext;
import net.minecraft.item.ItemStack;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.screen.ScreenHandler;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.DirectionProperty;
import net.minecraft.text.Text;
import net.minecraft.text.TranslatableText;
import net.minecraft.util.ActionResult;
import net.minecraft.util.BlockMirror;
import net.minecraft.util.BlockRotation;
import net.minecraft.util.Formatting;
import net.minecraft.util.Hand;
import net.minecraft.util.Identifier;
import net.minecraft.util.ItemScatterer;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;

public abstract class AbstractCobblestoneGenerator extends BlockWithEntity {
	public static final DirectionProperty FACING;
	private int tier;
	private String name;

	public AbstractCobblestoneGenerator(int tier) {
		super(FabricBlockSettings.of(Material.METAL, Blocks.IRON_BLOCK.getDefaultMapColor()).strength(2.0f, 3.0f).sounds(BlockSoundGroup.METAL).breakByTool(FabricToolTags.PICKAXES, 2));
		this.tier = tier;
		this.name = "generator_tier" + tier;
		this.setDefaultState((BlockState)((BlockState)((BlockState)this.stateManager.getDefaultState()).with(FACING, Direction.NORTH)));
	}

	@Environment(EnvType.CLIENT)
	public void appendTooltip(ItemStack stack, @Nullable BlockView world, List<Text> tooltip, TooltipContext options) {
		tooltip.add(
			new TranslatableText("lang.simple_cobblestone_generator.generates_every", SimpleCobblestoneGenerator.TIERS[this.tier-1] == 0 ? 64 : 1)
			.append(" ")
			.append(SimpleCobblestoneGenerator.TIERS[this.tier-1] <= 1 ? new TranslatableText("lang.simple_cobblestone_generator.tick") : new TranslatableText("lang.simple_cobblestone_generator.ticks", SimpleCobblestoneGenerator.TIERS[this.tier-1]))
			.formatted(Formatting.GRAY)
		);
		tooltip.add(new TranslatableText("lang.simple_cobblestone_generator.tooltip").formatted(Formatting.DARK_GRAY));
	}

	protected void openScreen(World world, BlockPos pos, PlayerEntity player) {
		BlockEntity blockEntity = world.getBlockEntity(pos);
		if (blockEntity instanceof AbstractCobblestoneGeneratorBlockEntity) {
			AbstractCobblestoneGeneratorBlockEntity cobbleGenEntity = (AbstractCobblestoneGeneratorBlockEntity)blockEntity;
			player.openHandledScreen(new ExtendedScreenHandlerFactory() {
				@Override
				public void writeScreenOpeningData(ServerPlayerEntity serverPlayerEntity, PacketByteBuf packetByteBuf) {}

				@Override
				public Text getDisplayName() {
					return new TranslatableText("containers." + AbstractCobblestoneGenerator.this.name);
				}

				@Override
				public @Nullable ScreenHandler createMenu(int syncId, PlayerInventory inv, PlayerEntity player) {
					return new CobblestoneGeneratorScreenHandler(syncId, inv, cobbleGenEntity.getInventory());
				}
			});
		}
	}

	public ActionResult onUse(BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockHitResult hit) {
		if (world.isClient) {
		   	return ActionResult.SUCCESS;
		} else {
		   	this.openScreen(world, pos, player);
		   	return ActionResult.CONSUME;
		}
	}

	public BlockState getPlacementState(ItemPlacementContext ctx) {
		return (BlockState)this.getDefaultState().with(FACING, ctx.getPlayerFacing().getOpposite());
	}

	public void onPlaced(World world, BlockPos pos, BlockState state, LivingEntity placer, ItemStack itemStack) {
		if (itemStack.hasCustomName()) {
			BlockEntity blockEntity = world.getBlockEntity(pos);
			if (blockEntity instanceof AbstractCobblestoneGeneratorBlockEntity) {
				((AbstractCobblestoneGeneratorBlockEntity)blockEntity).setCustomName(itemStack.getName());
			}
		}
	}

	public void onStateReplaced(BlockState state, World world, BlockPos pos, BlockState newState, boolean moved) {
		if (!state.isOf(newState.getBlock())) {
			BlockEntity blockEntity = world.getBlockEntity(pos);
			if (blockEntity instanceof AbstractCobblestoneGeneratorBlockEntity) {
				ItemScatterer.spawn(world, (BlockPos)pos, (Inventory)((AbstractCobblestoneGeneratorBlockEntity)blockEntity));
				world.updateComparators(pos, this);
			}

		   	super.onStateReplaced(state, world, pos, newState, moved);
		}
	}

	public boolean hasComparatorOutput(BlockState state) {
		return true;
	}

	public int getComparatorOutput(BlockState state, World world, BlockPos pos) {
		return ScreenHandler.calculateComparatorOutput(world.getBlockEntity(pos));
	}

	public BlockRenderType getRenderType(BlockState state) {
		return BlockRenderType.MODEL;
	}

	public BlockState rotate(BlockState state, BlockRotation rotation) {
		return (BlockState)state.with(FACING, rotation.rotate((Direction)state.get(FACING)));
	}

	public BlockState mirror(BlockState state, BlockMirror mirror) {
		return state.rotate(mirror.getRotation((Direction)state.get(FACING)));
	}

	public Identifier getIdentifier() {
		return new Identifier(SimpleCobblestoneGenerator.MOD_ID, this.name);
	}

	protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
		builder.add(FACING);
	}

	@Nullable
	protected static <T extends BlockEntity> BlockEntityTicker<T> checkType(World world, BlockEntityType<T> givenType, BlockEntityType<? extends AbstractCobblestoneGeneratorBlockEntity> expectedType) {
	   return world.isClient ? null : checkType(givenType, expectedType, AbstractCobblestoneGeneratorBlockEntity::tick);
	}

	static {
		FACING = HorizontalFacingBlock.FACING;
	}
}
