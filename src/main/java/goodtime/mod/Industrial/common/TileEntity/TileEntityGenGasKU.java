package goodtime.mod.Industrial.common.TileEntity;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.ISidedInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;

public class TileEntityGenGasKU extends TileEntity implements ISidedInventory {
	private static final String GuiGenGasKUName = null;
	private ItemStack[] GenGasKUItemStacks = new ItemStack[3];

	@Override
	public void updateEntity() {
		super.updateEntity();
	}

	@Override
	public int getSizeInventory() {
		return this.GenGasKUItemStacks.length;
	}

	@Override
	public ItemStack getStackInSlot(int slot) {
		return this.GenGasKUItemStacks[slot];
	}

	@Override
	public ItemStack decrStackSize(int var1, int var2) {
		return null;
	}

	@Override
	public ItemStack getStackInSlotOnClosing(int p_70304_1_) {
		return null;
	}

	@Override
	public void setInventorySlotContents(int p_70299_1_, ItemStack p_70299_2_) {
		
	}

	@Override
	public String getInventoryName() {
		return "gti.GenGasKU";
	}

	@Override
	public boolean hasCustomInventoryName() {
		return this.GuiGenGasKUName != null && this.GuiGenGasKUName.length() > 0;
	}

	@Override
	public int getInventoryStackLimit() {
		return 0;
	}

	@Override
	public boolean isUseableByPlayer(EntityPlayer p_70300_1_) {
		return false;
	}

	@Override
	public void openInventory() {
		
	}

	@Override
	public void closeInventory() {
		
	}

	@Override
	public boolean isItemValidForSlot(int p_94041_1_, ItemStack p_94041_2_) {
		return false;
	}

	@Override
	public int[] getAccessibleSlotsFromSide(int p_94128_1_) {
		return null;
	}

	@Override
	public boolean canInsertItem(int p_102007_1_, ItemStack p_102007_2_, int p_102007_3_) {
		return false;
	}

	@Override
	public boolean canExtractItem(int p_102008_1_, ItemStack p_102008_2_, int p_102008_3_) {
		return false;
	}
}
