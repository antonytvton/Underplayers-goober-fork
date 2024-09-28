package com.hbm.items.weapon;

import java.util.List;

import com.hbm.entity.grenade.*;
import com.hbm.items.ModItems;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.world.World;

public class ItemGrenade extends Item {
	
	public int fuse = 4;

	public ItemGrenade(int fuse) {
		this.maxStackSize = 1;
		this.fuse = fuse;
	}

	@Override
	public ItemStack onItemRightClick(ItemStack p_77659_1_, World p_77659_2_, EntityPlayer p_77659_3_) {
		if (!p_77659_3_.capabilities.isCreativeMode) {
			--p_77659_1_.stackSize;
		}

		p_77659_2_.playSoundAtEntity(p_77659_3_, "random.bow", 0.5F, 0.4F / (itemRand.nextFloat() * 0.4F + 0.8F));

		//TODO:
		/*
		 * kill all this stupid bullshit
		 * make a PROPER grenade entity base class
		 * have all the grenade items be an NBT stat in the entity instead of having new entities for every fucking grenade type
		 * register explosion effects with some lambdas to save on LOC
		 * jesus christ why do i keep doing this
		 */
		if (!p_77659_2_.isRemote) {
			
			if (this == ModItems.grenade_mk2) {
				p_77659_2_.spawnEntityInWorld(new EntityGrenadeMk2(p_77659_2_, p_77659_3_));
			}
			if (this == ModItems.stick_dynamite) {
				p_77659_2_.spawnEntityInWorld(new EntityGrenadeDynamite(p_77659_2_, p_77659_3_));
			}
		}

		return p_77659_1_;
	}

	@Override
	public EnumRarity getRarity(ItemStack p_77613_1_) {

		if (this == ModItems.grenade_schrabidium || this == ModItems.grenade_aschrab || this == ModItems.grenade_cloud) {
			return EnumRarity.rare;
		}

		if (this == ModItems.grenade_plasma || this == ModItems.grenade_zomg || this == ModItems.grenade_black_hole || this == ModItems.grenade_pink_cloud) {
			return EnumRarity.epic;
		}

		if (this == ModItems.grenade_nuke || this == ModItems.grenade_nuclear || this == ModItems.grenade_tau || this == ModItems.grenade_lemon || this == ModItems.grenade_mk2 || this == ModItems.grenade_pulse || this == ModItems.grenade_gascan) {
			return EnumRarity.uncommon;
		}

		return EnumRarity.common;
	}
	
	private String translateFuse() {
		if(fuse == -1)
			return "Impact";
		
		if(fuse == 0)
			return "Instant";
		
		return fuse + "s";
	}

	@Override
	public void addInformation(ItemStack itemstack, EntityPlayer player, List list, boolean bool) {

		list.add("Fuse: " + translateFuse());

		if (this == ModItems.grenade_smart) {
			list.add("");
			list.add("\"Why did it not blow up????\"");
			list.add(EnumChatFormatting.ITALIC + "If it didn't blow up it means it worked.");
		}

		if (this == ModItems.grenade_if_generic) {
			list.add("");
			list.add(EnumChatFormatting.ITALIC + "\"How do you like " + EnumChatFormatting.RESET + EnumChatFormatting.GRAY + "them" + EnumChatFormatting.ITALIC + " apples?\"");
		}
		if (this == ModItems.grenade_if_he) {
			list.add("");
			list.add(EnumChatFormatting.ITALIC + "\"You better run, you better take cover!\"");
		}
		if (this == ModItems.grenade_if_bouncy) {
			list.add("");
			list.add(EnumChatFormatting.ITALIC + "\"Boing!\"");
		}
		if (this == ModItems.grenade_if_sticky) {
			list.add("");
			list.add(EnumChatFormatting.ITALIC + "\"This one is the booger grenade.\"");
		}
		if (this == ModItems.grenade_if_impact) {
			list.add("");
			list.add(EnumChatFormatting.ITALIC + "\"Tossable boom.\"");
		}
		if (this == ModItems.grenade_if_incendiary) {
			list.add("");
			list.add(EnumChatFormatting.ITALIC + "\"Flaming wheel of destruction!\"");
		}
		if (this == ModItems.grenade_if_toxic) {
			list.add("");
			list.add(EnumChatFormatting.ITALIC + "\"TOXIC SHOCK\"");
		}
		if (this == ModItems.grenade_if_concussion) {
			list.add("");
			list.add(EnumChatFormatting.ITALIC + "\"Oof ouch owie, my bones!\"");
		}
		if (this == ModItems.grenade_if_brimstone) {
			list.add("");
			list.add(EnumChatFormatting.ITALIC + "\"Zoop!\"");
		}
		if (this == ModItems.grenade_if_mystery) {
			list.add("");
			list.add(EnumChatFormatting.ITALIC + "\"It's a mystery!\"");
		}
		if (this == ModItems.grenade_if_spark) {
			list.add("");
			//list.add(EnumChatFormatting.ITALIC + "\"31-31-31-31-31-31-31-31-31-31-31-31-31\"");
			list.add(EnumChatFormatting.ITALIC + "\"We can't rewind, we've gone too far.\"");
		}
		if (this == ModItems.grenade_if_hopwire) {
			list.add("");
			list.add(EnumChatFormatting.ITALIC + "\"All I ever wished for was a bike that didn't fall over.\"");
		}
		if (this == ModItems.grenade_if_null) {
			list.add("");
			list.add(EnumChatFormatting.ITALIC + "java.lang.NullPointerException");
		}
	}
	
	public static int getFuseTicks(Item grenade) {
		return ((ItemGrenade)grenade).fuse * 20;
	}
}
