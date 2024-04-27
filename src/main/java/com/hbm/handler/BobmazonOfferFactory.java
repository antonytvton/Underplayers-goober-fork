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

		int inflation = 5;
		materials.add(new Offer(new ItemStack(ModItems.ingot_copper, 64), Requirement.STEEL, 12));
		materials.add(new Offer(new ItemStack(Items.iron_ingot, 64), Requirement.STEEL, 12));
		materials.add(new Offer(new ItemStack(ModItems.ingot_lead, 64), Requirement.STEEL, 12));
		materials.add(new Offer(new ItemStack(ModItems.ingot_steel, 64), Requirement.STEEL, 16));
		
		
		
		
		
		
		
		special.add(new Offer(new ItemStack(ModItems.sat_laser, 1), Requirement.HIDDEN, 8));
		special.add(new Offer(new ItemStack(ModItems.sat_gerald, 1), Requirement.HIDDEN, 32));
		special.add(new Offer(new ItemStack(ModItems.billet_yharonite, 4), Requirement.HIDDEN, 16));
		special.add(new Offer(new ItemStack(ModItems.ingot_chainsteel, 1), Requirement.HIDDEN, 16));
		special.add(new Offer(new ItemStack(ModItems.ingot_electronium, 1), Requirement.HIDDEN, 16));
		special.add(new Offer(new ItemStack(ModItems.book_of_, 1), Requirement.HIDDEN, 16));
		special.add(new Offer(new ItemStack(ModItems.mese_pickaxe, 1), Requirement.HIDDEN, 16));
		special.add(new Offer(new ItemStack(ModItems.mysteryshovel, 1), Requirement.HIDDEN, 16));
		special.add(new Offer(new ItemStack(ModBlocks.ntm_dirt, 1), Requirement.HIDDEN, 16));
		special.add(new Offer(new ItemStack(ModItems.euphemium_kit, 1), Requirement.HIDDEN, 64));
		
		special.add(new Offer(ItemKitCustom.create("Fusion Man", "For the nuclear physicist on the go", 0xff00ff, 0x800080,
				new ItemStack(ModBlocks.iter),
				new ItemStack(ModBlocks.plasma_heater),
				new ItemStack(ModItems.fusion_shield_vaporwave),
				ItemBattery.getFullBattery(ModItems.battery_spark),
				new ItemStack(ModBlocks.machine_chemplant, 10),
				new ItemStack(ModBlocks.machine_fluidtank, 8),
				new ItemStack(ModBlocks.red_wire_coated, 64),
				new ItemStack(ModBlocks.red_cable, 64),
				new ItemStack(ModItems.fluid_barrel_full, 64, Fluids.DEUTERIUM.getID()),
				new ItemStack(ModItems.fluid_barrel_full, 64, Fluids.TRITIUM.getID()),
				new ItemStack(ModItems.fluid_barrel_full, 64, Fluids.XENON.getID()),
				new ItemStack(ModItems.fluid_barrel_full, 64, Fluids.MERCURY.getID()),
				new ItemStack(ModBlocks.red_pylon_large, 8),
				new ItemStack(ModBlocks.substation, 4),
				new ItemStack(ModBlocks.red_pylon, 16),
				new ItemStack(ModBlocks.red_connector, 64),
				new ItemStack(ModItems.wiring_red_copper, 1),
				new ItemStack(ModBlocks.machine_chungus, 1),
				new ItemStack(ModBlocks.machine_large_turbine, 3),
				new ItemStack(ModItems.template_folder, 1),
				new ItemStack(Items.paper, 64),
				new ItemStack(Items.dye, 64)
				), Requirement.HIDDEN, 64));
		
		special.add(new Offer(ItemKitCustom.create("Maid's Cleaning Utensils", "For the hard to reach spots", 0x00ff00, 0x008000,
				new ItemStack(ModItems.gun_calamity),
				ModItems.ammo_50bmg.stackFromEnum(64, Ammo50BMG.CHLOROPHYTE),
				ModItems.ammo_50bmg.stackFromEnum(64, Ammo50BMG.CHLOROPHYTE),
				ModItems.ammo_50bmg.stackFromEnum(64, Ammo50BMG.CHLOROPHYTE),
				ModItems.ammo_50ae.stackFromEnum(64, Ammo50AE.STAR),
				ModItems.ammo_50ae.stackFromEnum(64, Ammo50AE.STAR),
				new ItemStack(ModItems.gun_supershotgun),
				ModItems.ammo_12gauge.stackFromEnum(64, Ammo12Gauge.DU),
				ModItems.ammo_12gauge.stackFromEnum(64, Ammo12Gauge.DU),
				ModItems.ammo_12gauge.stackFromEnum(64, Ammo12Gauge.SHRAPNEL),
				ModItems.ammo_12gauge.stackFromEnum(64, Ammo12Gauge.SHRAPNEL),
				ModItems.ammo_12gauge.stackFromEnum(4, Ammo12Gauge.MARAUDER),
				new ItemStack(ModItems.gun_sauer),
				new ItemStack(ModItems.ammo_4gauge, 64),
				ModItems.ammo_4gauge.stackFromEnum(64, Ammo4Gauge.CLAW),
				ModItems.ammo_4gauge.stackFromEnum(64, Ammo4Gauge.KAMPF),
				ModItems.ammo_4gauge.stackFromEnum(64, Ammo4Gauge.FLECHETTE),
				ModItems.ammo_4gauge.stackFromEnum(64, Ammo4Gauge.VOID)
				), Requirement.HIDDEN, 64));
		
		special.add(new Offer(ItemKitNBT.create(
				new ItemStack(ModItems.rod_of_discord).setStackDisplayName("Cock Joke"),
				ModItems.canned_conserve.stackFromEnum(64, EnumFoodType.JIZZ).setStackDisplayName("Class A Horse Semen"),
				new ItemStack(ModItems.pipe_lead).setStackDisplayName("Get Nutted, Dumbass"),
				new ItemStack(ModItems.gem_alexandrite)
				).setStackDisplayName("The Nut Bucket"), Requirement.HIDDEN, 64));
		
		special.add(new Offer(ItemKitNBT.create(
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
				).setStackDisplayName("Frenchman's Reward"), Requirement.HIDDEN, 32));
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
