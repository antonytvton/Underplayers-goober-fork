package com.hbm.items;

import java.util.List;
import java.util.Random;

import com.hbm.config.VersatileConfig;
import com.hbm.items.ModItems;
import com.hbm.main.MainRegistry;
import com.hbm.packet.PacketDispatcher;
import com.hbm.packet.toclient.AuxParticlePacketNT;
import com.hbm.potion.HbmPotion;

import cpw.mods.fml.common.network.NetworkRegistry.TargetPoint;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumAction;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.world.World;
import com.hbm.extprop.HbmLivingProps;

public class ItemPharmacy extends Item {
	public int cooldown = 0;
	public ItemPharmacy(int uses) {
		this.setMaxDamage(uses);
		this.maxStackSize = 1;
	}
	@Override
	public void onUpdate(ItemStack stack, World world, Entity entity, int i, boolean b) {
	    NBTTagCompound nbt = stack.getTagCompound();
		if (nbt == null) {
	        nbt = new NBTTagCompound();
	        nbt.setInteger("dmg", 0);
		}

		if (this.cooldown > 0){
			this.cooldown = this.cooldown-1;
			if (entity instanceof EntityPlayer) {
				EntityPlayer player = (EntityPlayer) entity;
				if(this.cooldown == 2) {
					if (this == ModItems.ibuprofen) {
						player.addPotionEffect(new PotionEffect(Potion.regeneration.id, 5 * 20, 1));
						HbmLivingProps.setHealthMult(player, 1);
						HbmLivingProps.addOverdose(player, 800);
					}
					if (this == ModItems.painkiller) {
						player.addPotionEffect(new PotionEffect(Potion.resistance.id, 3 * 60 * 20, 0));
						player.addPotionEffect(new PotionEffect(Potion.field_76444_x.id, 3 * 60 * 20, 0));
						HbmLivingProps.addOverdose(player, 1000);
					}
					if (this == ModItems.yadulin) {
						HbmLivingProps.setHealthMult(player, (HbmLivingProps.getHealthMult(player)+1)/2);
						player.addPotionEffect(new PotionEffect(Potion.regeneration.id, 2 * 60 * 20, 0));
						HbmLivingProps.addOverdose(player, 2000);
					}
					if (this == ModItems.antidote) {
						player.removePotionEffect(Potion.blindness.id);
						player.removePotionEffect(Potion.confusion.id);
						player.removePotionEffect(Potion.digSlowdown.id);
						player.removePotionEffect(Potion.hunger.id);
						player.removePotionEffect(Potion.moveSlowdown.id);
						player.removePotionEffect(Potion.poison.id);
						player.removePotionEffect(Potion.weakness.id);
						player.removePotionEffect(Potion.wither.id);
						player.removePotionEffect(HbmPotion.radiation.id);
						HbmLivingProps.setAntidote(player, 5000);
						HbmLivingProps.addOverdose(player, -3000);
					}
				}
			}
			if (this.cooldown == 78) {
				if (!world.isRemote) {
				    stack.setItemDamage(stack.getItemDamage() + 1);
					}
				}

		}
		if(stack.getItemDamage() == this.getMaxDamage()) {
			stack.stackSize --;
		}
	}
	
	@Override
	public ItemStack onItemRightClick(ItemStack stack, World world, EntityPlayer player) {
		if(this.cooldown == 0){
			if (stack.getItemDamage() != stack.getMaxDamage() && !VersatileConfig.hasPotionSickness(player)) {
				this.cooldown = 80;
				if((this == ModItems.antidote)||(this == ModItems.painkiller)) {
					player.playSound("hbm:player.pills_blister", 1F, 1F);
				}
				if((this == ModItems.yadulin)||(this == ModItems.ibuprofen)) {
					player.playSound("hbm:player.pills_bottle", 1F, 1F);
				}
			}
		}
		System.out.println(stack);
		return stack;
	}
    
    @Override
	@SideOnly(Side.CLIENT)
    public void addInformation(ItemStack p_77624_1_, EntityPlayer p_77624_2_, List list, boolean p_77624_4_)
    {
    	if(this == ModItems.ibuprofen)
    	{
    		list.add("Removes Hard Damage");
			list.add("Overdose: 8%");
    		list.add("How does Ibuprofen fix a gunshot wound? I have no idea!");
    	}
    	if(this == ModItems.painkiller)
    	{	
    		list.add("Decreases Damage by 20%");
			list.add("Overdose: 10%");
    	}
    	if(this == ModItems.yadulin)
    	{	
    		list.add("Halves Hard Damage");
    		list.add("Regenerate HP over time");
			list.add("Overdose: 20%");
			list.add("Side effects may include: Rapid Heart Rate, Sweating, Dry Mouth, Headaches, Nausea, Confusion, Disorientation, Dificulty Focusing, Dificulty Breathing, Death, Rapid Death, Slow Death. Ask your doctor if Yadulin is right for you!");
    	}
    	if(this == ModItems.antidote)
    	{
			list.add("Cures poison and overdose");
    	}
    	
    }

}
