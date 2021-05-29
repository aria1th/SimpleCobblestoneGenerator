package dev.fulmineo.simple_cobblestone_generator.block.entity;

import org.jetbrains.annotations.Nullable;

import dev.fulmineo.simple_cobblestone_generator.SimpleCobblestoneGenerator;
import dev.fulmineo.simple_cobblestone_generator.screen.CobblestoneGeneratorScreenHandler;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.block.entity.LockableContainerBlockEntity;
import net.minecraft.block.entity.LootableContainerBlockEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.SidedInventory;
import net.minecraft.inventory.SimpleInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.nbt.NbtList;
import net.minecraft.screen.ScreenHandler;
import net.minecraft.text.Text;
import net.minecraft.text.TranslatableText;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.world.World;

public abstract class AbstractCobblestoneGeneratorBlockEntity extends LockableContainerBlockEntity implements SidedInventory {
	private SimpleInventory inventory;
	private short genTicks;
	private short ticksToGenerate;
	private String name;

	public AbstractCobblestoneGeneratorBlockEntity(BlockEntityType<?> blockEntityType, BlockPos pos, BlockState state) {
		super(blockEntityType, pos, state);
	}

	public AbstractCobblestoneGeneratorBlockEntity(BlockEntityType<?> blockEntityType, BlockPos pos, BlockState state, String name, short ticksToGenerate) {
		super(blockEntityType, pos, state);
		this.name = name;
		this.ticksToGenerate = ticksToGenerate;
		this.genTicks = this.ticksToGenerate;
		this.inventory = new SimpleInventory(1);
	}

	protected Text getContainerName() {
		return new TranslatableText("container." + this.name);
	}

	protected ScreenHandler createScreenHandler(int syncId, PlayerInventory playerInventory) {
		return new CobblestoneGeneratorScreenHandler(syncId, playerInventory, this);
	}

	public void readNbt(NbtCompound tag) {
		super.readNbt(tag);
		this.inventory = new SimpleInventory(1);
		NbtList listTag = tag.getList("Items", 10);
		this.inventory.setStack(0, listTag.size() > 0 ? ItemStack.fromNbt((NbtCompound)listTag.get(0))  : new ItemStack(Items.AIR));
		this.genTicks = tag.getShort("GenTime");
	}

	public NbtCompound writeNbt(NbtCompound tag) {
		super.writeNbt(tag);
		tag.putShort("GenTime", (short)this.genTicks);
		NbtList listTag = new NbtList();
		listTag.add(this.inventory.getStack(0).writeNbt(new NbtCompound()));
		tag.put("Items", listTag);
		return tag;
	}

	public static void tick(World world, BlockPos pos, BlockState state, AbstractCobblestoneGeneratorBlockEntity blockEntity) {
		SimpleCobblestoneGenerator.info("ciao");
		if (blockEntity.genTicks > 0) blockEntity.genTicks--;
		if (blockEntity.genTicks == 0) {
			blockEntity.genTicks = blockEntity.ticksToGenerate;

			ItemStack itemStack = blockEntity.inventory.getStack(0);
			if (itemStack.getItem() != Items.COBBLESTONE){
				itemStack = new ItemStack(Items.COBBLESTONE);
			} else {
				itemStack.increment(1);
			}
			blockEntity.inventory.setStack(0, itemStack);

			BlockEntity upperBlockEntity = world.getBlockEntity(pos.up());
			if (upperBlockEntity != null && upperBlockEntity instanceof LootableContainerBlockEntity) {
				LootableContainerBlockEntity inventory = (LootableContainerBlockEntity) upperBlockEntity;
				for (int i = 0; i < inventory.size(); i++) {
					ItemStack stack = inventory.getStack(i);
					if (stack.isEmpty()){
						stack = new ItemStack(itemStack.getItem());
						inventory.setStack(i, stack);
					}
					if (stack.getItem() == itemStack.getItem()) {
						int toInsert = stack.getMaxCount() - stack.getCount();
						if (toInsert > 0) {
							if (toInsert > itemStack.getCount()) toInsert = itemStack.getCount();
							itemStack.decrement(toInsert);
							stack.increment(toInsert);
							if (itemStack.getCount() == 0) break;
						}
					}
				}
			}
		}
	}

	public int[] getAvailableSlots(Direction side) {
		if (side == Direction.DOWN) {
		   return new int[]{0};
		} else {
		   return new int[]{};
		}
	 }

	public boolean canInsert(int slot, ItemStack stack, @Nullable Direction dir) {
		return false;
	}

	public boolean canExtract(int slot, ItemStack stack, Direction dir) {
		return true;
	}

	public int size() {
		return this.inventory.size();
	}

	public boolean isEmpty() {
		return this.inventory.getStack(0).isEmpty();
	}

	public ItemStack getStack(int slot) {
		return (ItemStack)this.inventory.getStack(slot);
	}

	public ItemStack removeStack(int slot, int amount) {
		return slot >= 0 && slot < this.inventory.size() && !((ItemStack)this.inventory.getStack(slot)).isEmpty() && amount > 0 ? ((ItemStack)this.inventory.getStack(slot)).split(amount) : ItemStack.EMPTY;
	}

	public ItemStack removeStack(int slot) {
		return this.inventory.removeStack(slot);
	}

	public void setStack(int slot, ItemStack stack) {}

	public boolean canPlayerUse(PlayerEntity player) {
		if (this.world.getBlockEntity(this.pos) != this) {
		   return false;
		} else {
		   return player.squaredDistanceTo((double)this.pos.getX() + 0.5D, (double)this.pos.getY() + 0.5D, (double)this.pos.getZ() + 0.5D) <= 64.0D;
		}
	}

	public SimpleInventory getInventory(){
		return this.inventory;
	}

	public void clear() {
		this.inventory.clear();
	}

}
