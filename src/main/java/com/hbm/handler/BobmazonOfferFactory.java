package com.hbm.handler;

import java.util.ArrayList;
import java.util.List;

import com.hbm.blocks.ModBlocks;
import com.hbm.inventory.fluid.Fluids;
import com.hbm.inventory.gui.GUIScreenBobmazon.Offer;
import com.hbm.inventory.gui.GUIScreenBobmazon.Requirement;
import com.hbm.items.ItemAmmoEnums.*;
import com.hbm.items.ModItems;
import com.hbm.items.food.ItemConserve.EnumFoodType;
import com.hbm.items.machine.ItemBattery;
import com.hbm.items.special.ItemKitCustom;
import com.hbm.items.special.ItemKitNBT;

import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;

public class BobmazonOfferFactory {

	public static List<Offer> materials = new ArrayList();
	public static List<Offer> machines = new ArrayList();
	public static List<Offer> weapons = new ArrayList();
	public static List<Offer> tools = new ArrayList();
	public static List<Offer> special = new ArrayList();
	
	public static void init() {
		
		materials.clear();
		machines.clear();
		weapons.clear();
		tools.clear();
		special.clear();

		float inflation = 1;
		
		// for referance 8 iron blocks is 8 caps
		materials.add(new Offer(new ItemStack(Blocks.iron_block, 16), Requirement.STEEL, Math.round(16 * inflation)));
		materials.add(new Offer(new ItemStack(Blocks.coal_block, 24), Requirement.STEEL, Math.round(16 * inflation)));
		materials.add(new Offer(new ItemStack(Items.diamond, 18), Requirement.STEEL, Math.round(16 * inflation)));
		materials.add(new Offer(new ItemStack(Items.gold_ingot, 36), Requirement.STEEL, Math.round(16 * inflation)));
		materials.add(new Offer(new ItemStack(Blocks.redstone_block, 32), Requirement.STEEL, Math.round(16 * inflation)));
		materials.add(new Offer(new ItemStack(ModBlocks.block_steel, 12), Requirement.STEEL, Math.round(16 * inflation)));
		materials.add(new Offer(new ItemStack(ModBlocks.block_copper, 12), Requirement.STEEL, Math.round(16 * inflation)));
		materials.add(new Offer(new ItemStack(ModBlocks.block_aluminium, 12), Requirement.STEEL, Math.round(16 * inflation)));
		materials.add(new Offer(new ItemStack(ModBlocks.block_lead, 12), Requirement.STEEL, Math.round(16 * inflation)));
		materials.add(new Offer(new ItemStack(ModBlocks.block_titanium, 12), Requirement.STEEL, Math.round(16 * inflation)));
		materials.add(new Offer(new ItemStack(ModBlocks.block_tungsten, 12), Requirement.STEEL, Math.round(16 * inflation)));
		materials.add(new Offer(new ItemStack(ModBlocks.block_advanced_alloy, 8), Requirement.STEEL, Math.round(16 * inflation)));


		
		

		machines.add(new Offer(new ItemStack(ModBlocks.decon), Requirement.ASSEMBLY, Math.round(2 * inflation)));
		tools.add(new Offer(ItemKitNBT.create(
				new ItemStack(ModItems.ammo_5mm, 64),
				new ItemStack(ModItems.ammo_5mm, 64),
				new ItemStack(ModItems.ammo_5mm, 64),
				new ItemStack(ModItems.turret_chip),
				new ItemStack(ModItems.battery_sc_uranium),
				new ItemStack(ModBlocks.turret_sentry)
				).setStackDisplayName("Brown Turret Kit"), Requirement.ASSEMBLY, 16));


		weapons.add(new Offer(new ItemStack(Items.gunpowder, 64), Requirement.ASSEMBLY, Math.round(8 * inflation)));
		weapons.add(new Offer(new ItemStack(ModItems.syringe_metal_stimpak, 16), Requirement.ASSEMBLY, Math.round(8 * inflation)));
		weapons.add(new Offer(new ItemStack(ModItems.syringe_metal_psycho, 1), Requirement.ASSEMBLY, Math.round(128 * inflation)));
		weapons.add(new Offer(new ItemStack(ModItems.syringe_metal_medx, 8), Requirement.ASSEMBLY, Math.round(48 * inflation)));
		weapons.add(new Offer(new ItemStack(ModItems.satchel_charge, 1), Requirement.ASSEMBLY, Math.round(48 * inflation)));

		
		
		materials.add(new Offer(new ItemStack(ModItems.drill_titanium), Requirement.CHEMICS, Math.round(32)));
		
		tools.add(new Offer(ItemKitNBT.create(
				new ItemStack(ModItems.hazmat_boots),
				new ItemStack(ModItems.hazmat_legs),
				new ItemStack(ModItems.hazmat_plate),
				new ItemStack(ModItems.hazmat_helmet),
				new ItemStack(ModItems.gas_mask_filter)
				).setStackDisplayName("Basic Hazmat"), Requirement.ASSEMBLY, 16));
		

		tools.add(new Offer(ItemKitNBT.create(
				new ItemStack(ModItems.hazmat_boots_red),
				new ItemStack(ModItems.hazmat_legs_red),
				new ItemStack(ModItems.hazmat_plate_red),
				new ItemStack(ModItems.hazmat_helmet_red),
				new ItemStack(ModItems.gas_mask_filter)
				).setStackDisplayName("Advanced Hazmat"), Requirement.ASSEMBLY, 32));
		
		tools.add(new Offer(ItemKitNBT.create(
				new ItemStack(ModItems.hazmat_boots_red),
				new ItemStack(ModItems.hazmat_legs_red),
				new ItemStack(ModItems.hazmat_plate_red),
				new ItemStack(ModItems.hazmat_helmet_red),
				new ItemStack(ModItems.gas_mask_filter)
				).setStackDisplayName("High-Performance Hazmat"), Requirement.CHEMICS, 64));
		
		
		
		/*special.add(new Offer(ItemKitNBT.create(
				new ItemStack(ModItems.rpa_helmet),
				new ItemStack(ModItems.rpa_plate),
				new ItemStack(ModItems.rpa_legs),
				new ItemStack(ModItems.rpa_boots),
				new ItemStack(ModItems.gun_lacunae),
				ModItems.ammo_5mm.stackFromEnum(64, Ammo5mm.STAR),
				ModItems.ammo_5mm.stackFromEnum(64, Ammo5mm.STAR),
				ModItems.ammo_5mm.stackFromEnum(64, Ammo5mm.STAR),
				ModItems.ammo_5mm.stackFromEnum(64, Ammo5mm.STAR),
				ModItems.ammo_5mm.stackFromEnum(64, Ammo5mm.STAR),
				ModItems.ammo_5mm.stackFromEnum(64, Ammo5mm.STAR),
				ModItems.ammo_5mm.stackFromEnum(64, Ammo5mm.STAR)
				).setStackDisplayName("Frenchman's Reward"), Requirement.HIDDEN, 32));*/
	}
	
	public static List<Offer> getOffers(ItemStack stack) {
		
		if(stack != null) {

			if(stack.getItem() == ModItems.bobmazon_materials) return materials;
			if(stack.getItem() == ModItems.bobmazon_machines) return machines;
			if(stack.getItem() == ModItems.bobmazon_weapons) return weapons;
			if(stack.getItem() == ModItems.bobmazon_tools) return tools;
			if(stack.getItem() == ModItems.bobmazon_hidden) return special;
		}
		
		return null;
	}

}
