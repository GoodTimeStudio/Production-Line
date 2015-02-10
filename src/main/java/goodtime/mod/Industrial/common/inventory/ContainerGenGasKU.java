package goodtime.mod.Industrial.common.inventory;

import goodtime.mod.Industrial.common.TileEntity.TileEntityGenGasKU;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;

public class ContainerGenGasKU extends Container {

	private TileEntityGenGasKU tileGenGasKU;
	
	public ContainerGenGasKU(InventoryPlayer invPlayer, TileEntityGenGasKU tile) {
		tileGenGasKU = tile;
	}

	@Override
	public boolean canInteractWith(EntityPlayer player) {
		return true;
	}

}
