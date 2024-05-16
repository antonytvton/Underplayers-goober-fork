package com.hbm.tileentity.machine;

import com.hbm.blocks.ModBlocks;
import com.hbm.blocks.machine.MachineArcFurnace;
import com.hbm.inventory.container.ContainerMachineArcFurnace;
import com.hbm.inventory.gui.GUIMachineArcFurnace;
import com.hbm.inventory.recipes.ArcFurnaceRecipes;
import com.hbm.inventory.recipes.BlastFurnaceRecipes;
import com.hbm.items.ModItems;
import com.hbm.lib.Library;
import com.hbm.packet.AuxElectricityPacket;
import com.hbm.packet.AuxGaugePacket;
import com.hbm.packet.PacketDispatcher;
import com.hbm.tileentity.IGUIProvider;
import com.hbm.tileentity.TileEntityLoadedBase;
import com.hbm.util.CompatEnergyControl;

import api.hbm.energymk2.IEnergyReceiverMK2;
import api.hbm.tile.IInfoProviderEC;
import cpw.mods.fml.common.network.NetworkRegistry.TargetPoint;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.ISidedInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.FurnaceRecipes;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.world.World;
import net.minecraftforge.common.util.ForgeDirection;

public class TileEntityMachineArcFurnace extends TileEntityLoadedBase implements ISidedInventory, IEnergyReceiverMK2, IGUIProvider, IInfoProviderEC {

	private ItemStack slots[];
	
	public int dualCookTime;
	public long power;
	public static final long maxPower = 50000;
	public static final int processingSpeed = 20;
	
	//0: i
	//1: o
	//2: 1
	//3: 2
	//4: 3
	//5: b
	//6: i2
	private static final int[] slots_io = new int[] {0, 1, 2, 3, 4, 5, 6};
	
	private String customName;
	
	public TileEntityMachineArcFurnace() {
		slots = new ItemStack[7];
	}

	@Override
	public int getSizeInventory() {
		return slots.length;
	}

	@Override
	public ItemStack getStackInSlot(int i) {
		return slots[i];
	}

	@Override
	public ItemStack getStackInSlotOnClosing(int i) {
		if(slots[i] != null)
		{
			ItemStack itemStack = slots[i];
			slots[i] = null;
			return itemStack;
		} else {
		return null;
		}
	}

	@Override
	public void setInventorySlotContents(int i, ItemStack itemStack) {
		slots[i] = itemStack;
		if(itemStack != null && itemStack.stackSize > getInventoryStackLimit())
		{
			itemStack.stackSize = getInventoryStackLimit();
		}
	}

	@Override
	public String getInventoryName() {
		return this.hasCustomInventoryName() ? this.customName : "container.arcFurnace";
	}

	@Override
	public boolean hasCustomInventoryName() {
		return this.customName != null && this.customName.length() > 0;
	}
	
	public void setCustomName(String name) {
		this.customName = name;
	}

	@Override
	public int getInventoryStackLimit() {
		return 64;
	}

	@Override
	public boolean isUseableByPlayer(EntityPlayer player) {
		if(worldObj.getTileEntity(xCoord, yCoord, zCoord) != this)
		{
			return false;
		}else{
			return player.getDistanceSq(xCoord + 0.5D, yCoord + 0.5D, zCoord + 0.5D) <=64;
		}
	}
	
	//You scrubs aren't needed for anything (right now)
	@Override
	public void openInventory() {}
	@Override
	public void closeInventory() {}

	@Override
	public boolean isItemValidForSlot(int i, ItemStack itemStack) {
		
		if(i == 2 || i == 3 || i == 4)
			return itemStack.getItem() == ModItems.arc_electrode || itemStack.getItem() == ModItems.arc_electrode_desh;
		
		if(i == 0)
			return FurnaceRecipes.smelting().getSmeltingResult(itemStack) != null;
		return false;
	}
	
	@Override
	public ItemStack decrStackSize(int i, int j) {
		if(slots[i] != null)
		{
			if(slots[i].stackSize <= j)
			{
				ItemStack itemStack = slots[i];
				slots[i] = null;
				return itemStack;
			}
			ItemStack itemStack1 = slots[i].splitStack(j);
			if (slots[i].stackSize == 0)
			{
				slots[i] = null;
			}
			
			return itemStack1;
		} else {
			return null;
		}
	}
	
	@Override
	public void readFromNBT(NBTTagCompound nbt) {
		super.readFromNBT(nbt);
		NBTTagList list = nbt.getTagList("items", 10);
		
		this.power = nbt.getLong("powerTime");
		this.dualCookTime = nbt.getInteger("cookTime");
		slots = new ItemStack[getSizeInventory()];
		
		for(int i = 0; i < list.tagCount(); i++)
		{
			NBTTagCompound nbt1 = list.getCompoundTagAt(i);
			byte b0 = nbt1.getByte("slot");
			if(b0 >= 0 && b0 < slots.length)
			{
				slots[b0] = ItemStack.loadItemStackFromNBT(nbt1);
			}
		}
	}
	
	@Override
	public void writeToNBT(NBTTagCompound nbt) {
		super.writeToNBT(nbt);
		nbt.setLong("powerTime", power);
		nbt.setInteger("cookTime", dualCookTime);
		NBTTagList list = new NBTTagList();
		
		for(int i = 0; i < slots.length; i++)
		{
			if(slots[i] != null)
			{
				NBTTagCompound nbt1 = new NBTTagCompound();
				nbt1.setByte("slot", (byte)i);
				slots[i].writeToNBT(nbt1);
				list.appendTag(nbt1);
			}
		}
		nbt.setTag("items", list);
	}
	
	@Override
	public int[] getAccessibleSlotsFromSide(int side) {
		return slots_io;
	}

	@Override
	public boolean canInsertItem(int i, ItemStack itemStack, int j) {
		return this.isItemValidForSlot(i, itemStack);
	}

	@Override
	public boolean canExtractItem(int i, ItemStack itemStack, int j) {
		
		if(i == 1)
			return true;
		
		if(i == 2 || i == 3 || i == 4)
			return itemStack.getItem() == ModItems.arc_electrode_burnt;
		
		return false;
	}
	
	public int getDiFurnaceProgressScaled(int i) {
		return (dualCookTime * i) / processingSpeed;
	}
	
	public long getPowerRemainingScaled(long i) {
		return (power * i) / maxPower;
	}
	
	public boolean hasPower() {
		return power >= 250;
	}
	
	public boolean isProcessing() {
		return this.dualCookTime > 0;
	}
	
	private boolean hasElectrodes() {
		
		if(slots[2] != null && slots[3] != null && slots[4] != null) {
			if((slots[2].getItem() == ModItems.arc_electrode || slots[2].getItem() == ModItems.arc_electrode_desh) &&
					(slots[3].getItem() == ModItems.arc_electrode || slots[3].getItem() == ModItems.arc_electrode_desh) &&
					(slots[4].getItem() == ModItems.arc_electrode || slots[4].getItem() == ModItems.arc_electrode_desh))
				return true;
		}
		
		return false;
	}
	
	public boolean canProcess() {
		
		if(!hasElectrodes())
			return false;
		
		if(slots[0] == null)
		{
			return false;
		}
		if(slots[6] == null)
		{
			return false;
		}
		ItemStack output = ArcFurnaceRecipes.getOutput(slots[0], slots[6]);
		if(output == null)
		{
			System.out.println(output);
			return false;
		}
		
		if(slots[1] == null)
		{
			return true;
		}
		
		if(!slots[1].isItemEqual(output)) {
			return false;
		}
		
		if(slots[1].stackSize < getInventoryStackLimit() && slots[1].stackSize < slots[1].getMaxStackSize()) {
			return true;
		}else{
			return slots[1].stackSize < output.getMaxStackSize();
		}
	}
	
	private void processItem() {
		if(canProcess()) {
			ItemStack output = ArcFurnaceRecipes.getOutput(slots[0], slots[6]);
			
			if(slots[1] == null)
			{
				slots[1] = output.copy();
			}else if(slots[1].isItemEqual(output)) {
				slots[1].stackSize += output.stackSize;
			}
			
			if(slots[0].stackSize <= 0)
			{
				slots[0] = new ItemStack(slots[0].getItem().setFull3D());
			}else{
				slots[0].stackSize--;
			}
			if(slots[0].stackSize <= 0)
			{
				slots[0] = null;
			}

			if(slots[6].stackSize <= 0)
			{
				slots[6] = new ItemStack(slots[6].getItem().setFull3D());
			}else{
				slots[6].stackSize--;
			}
			if(slots[6].stackSize <= 6)
			{
				slots[6] = null;
			}
			
			
			for(int i = 2; i < 5; i++) {
				if(slots[i] != null && slots[i].getItem() == ModItems.arc_electrode) {
					if(slots[i].getItemDamage() < slots[i].getMaxDamage())
						slots[i].setItemDamage(slots[i].getItemDamage() + 1);
					else
						slots[i] = new ItemStack(ModItems.arc_electrode_burnt);
				}
			}
		}
	}
	
	//TODO: fix this punjabi trash
	@Override
	public void updateEntity() {
		boolean flag1 = false;
		
		if(!worldObj.isRemote) {

			for(ForgeDirection dir : ForgeDirection.VALID_DIRECTIONS)
				this.trySubscribe(worldObj, xCoord + dir.offsetX, yCoord + dir.offsetY, zCoord + dir.offsetZ, dir);
			
			if(hasPower() && canProcess())
			{
				dualCookTime++;
				
				power -= 250;
				
				if(power < 0)
					power = 0;
				
				if(this.dualCookTime == processingSpeed)
				{
					this.dualCookTime = 0;
					this.processItem();
					flag1 = true;
				}
			}else{
				dualCookTime = 0;
			}
			
			boolean trigger = true;
			
			if(hasPower() && canProcess() && this.dualCookTime == 0)
			{
				trigger = false;
			}
				
			if(trigger)
            {
                flag1 = true;
                MachineArcFurnace.updateBlockState(this.dualCookTime > 0, this.worldObj, this.xCoord, this.yCoord, this.zCoord);
            }
			
			if(worldObj.getBlock(xCoord, yCoord, zCoord) == ModBlocks.machine_arc_furnace_off) {
				
				int meta = worldObj.getBlockMetadata(xCoord, yCoord, zCoord);

				if(hasElectrodes() && meta <= 5) {
					worldObj.setBlockMetadataWithNotify(xCoord, yCoord, zCoord, meta + 4, 2);
				}
				if(!hasElectrodes() && meta > 5) {
					worldObj.setBlockMetadataWithNotify(xCoord, yCoord, zCoord, meta - 4, 2);
				}
			}
			
			power = Library.chargeTEFromItems(slots, 5, power, maxPower);

			PacketDispatcher.wrapper.sendToAllAround(new AuxElectricityPacket(xCoord, yCoord, zCoord, power), new TargetPoint(worldObj.provider.dimensionId, xCoord, yCoord, zCoord, 50));
			PacketDispatcher.wrapper.sendToAllAround(new AuxGaugePacket(xCoord, yCoord, zCoord, dualCookTime, 0), new TargetPoint(worldObj.provider.dimensionId, xCoord, yCoord, zCoord, 50));
		}
		
		
		if(flag1)
		{
			this.markDirty();
		}
	}

	@Override
	public void setPower(long i) {
		power = i;
		
	}

	@Override
	public long getPower() {
		return power;
		
	}

	@Override
	public long getMaxPower() {
		return maxPower;
	}

	@Override
	public Container provideContainer(int ID, EntityPlayer player, World world, int x, int y, int z) {
		return new ContainerMachineArcFurnace(player.inventory, this);
	}

	@Override
	@SideOnly(Side.CLIENT)
	public GuiScreen provideGUI(int ID, EntityPlayer player, World world, int x, int y, int z) {
		return new GUIMachineArcFurnace(player.inventory, this);
	}

	@Override
	public void provideExtraInfo(NBTTagCompound data) {
		data.setBoolean(CompatEnergyControl.B_ACTIVE, this.hasPower() && this.canProcess());
		data.setInteger(CompatEnergyControl.I_PROGRESS, this.dualCookTime);
	}
}
