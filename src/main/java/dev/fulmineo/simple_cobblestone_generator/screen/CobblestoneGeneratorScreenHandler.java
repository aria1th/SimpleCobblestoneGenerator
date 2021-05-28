package dev.fulmineo.simple_cobblestone_generator.screen;

import dev.fulmineo.simple_cobblestone_generator.SimpleCobblestoneGenerator;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.Inventory;
import net.minecraft.inventory.SimpleInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.screen.ScreenHandler;
import net.minecraft.screen.slot.Slot;

public class CobblestoneGeneratorScreenHandler extends ScreenHandler {
    private final Inventory inventory;

    public CobblestoneGeneratorScreenHandler(int syncId, PlayerInventory playerInventory, PacketByteBuf buf){
        this(syncId, playerInventory, new SimpleInventory(1));
    }

    public CobblestoneGeneratorScreenHandler(int syncId, PlayerInventory playerInventory, Inventory inventory) {
        super(SimpleCobblestoneGenerator.SCREEN_HANDLER, syncId);
        this.inventory = inventory;
		inventory.onOpen(playerInventory.player);

		this.addSlot(new Slot(inventory, 0, 80, 36) {
            public boolean canInsert(ItemStack stack) {
				return false;
            }

            @Environment(EnvType.CLIENT)
            public boolean doDrawHoveringEffect() {
                return true;
            }
		});

        int o;
        int n;

        for(o = 0; o < 3; ++o) {
            for(n = 0; n < 9; ++n) {
            	this.addSlot(new Slot(playerInventory, n + o * 9 + 9, 8 + n * 18, 102 + o * 18 + -18));
            }
        }

        for(o = 0; o < 9; ++o) {
			this.addSlot(new Slot(playerInventory, o, 8 + o * 18, 142));
        }
    }

    public boolean canUse(PlayerEntity player) {
        return true;
    }

    public ItemStack transferSlot(PlayerEntity player, int index) {
		ItemStack itemStack = ItemStack.EMPTY;
		Slot slot = (Slot)this.slots.get(index);
		if (slot != null && slot.hasStack()) {
		   	ItemStack itemStack2 = slot.getStack();
		   	itemStack = itemStack2.copy();
		   	int invSize = this.inventory.size();
			if (index < invSize) {
				if (!this.insertItem(itemStack2, invSize, this.slots.size(), true)) {
					return ItemStack.EMPTY;
				}
			} else if (index >= invSize && index < 27 + invSize) {
				if (!this.insertItem(itemStack2, 27 + invSize, 36 + invSize, false)) {
				   return ItemStack.EMPTY;
				}
			} else if (index >= (27 + invSize) && index < (36 + invSize) && !this.insertItem(itemStack2, invSize, 27 + invSize, false)) {
				return ItemStack.EMPTY;
			}
			if (itemStack2.isEmpty()) {
				slot.setStack(ItemStack.EMPTY);
			} else {
				slot.markDirty();
			}
		}
		return itemStack;
	}

    public void close(PlayerEntity player) {
        super.close(player);
        this.inventory.onClose(player);
    }
}
