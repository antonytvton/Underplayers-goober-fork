package com.hbm.crafting;

import com.hbm.blocks.ModBlocks;
import com.hbm.config.GeneralConfig;
import com.hbm.inventory.OreDictManager.DictFrame;
import com.hbm.inventory.fluid.Fluids;
import com.hbm.items.ItemAmmoEnums.AmmoFatman;
import com.hbm.items.machine.ItemCircuit.EnumCircuitType;
import com.hbm.items.ItemEnums;
import com.hbm.items.ModItems;
import com.hbm.main.CraftingManager;

import static com.hbm.inventory.OreDictManager.*;

import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.oredict.OreDictionary;

/**
 * For foods, drinks or other consumables
 * @author hbm
 */
public class ConsumableRecipes {
	
	public static void register() {
		
		//Peas
		CraftingManager.addRecipeAuto(new ItemStack(ModItems.peas), new Object[] { " S ", "SNS", " S ", 'S', Items.wheat_seeds, 'N', GOLD.nugget() });
		//Syringes
		CraftingManager.addRecipeAuto(new ItemStack(ModItems.syringe_empty, 6), new Object[] { "P", "C", "B", 'B', Item.getItemFromBlock(Blocks.iron_bars), 'C', ModItems.cell_empty, 'P', IRON.plate() });
		CraftingManager.addRecipeAuto(new ItemStack(ModItems.syringe_antidote, 6), new Object[] { "SSS", "PMP", "SSS", 'S', ModItems.syringe_empty, 'P', Items.pumpkin_seeds, 'M', Items.milk_bucket });
		CraftingManager.addRecipeAuto(new ItemStack(ModItems.syringe_antidote, 6), new Object[] { "SPS", "SMS", "SPS", 'S', ModItems.syringe_empty, 'P', Items.pumpkin_seeds, 'M', Items.milk_bucket });
		CraftingManager.addRecipeAuto(new ItemStack(ModItems.syringe_antidote, 6), new Object[] { "SSS", "PMP", "SSS", 'S', ModItems.syringe_empty, 'P', Items.pumpkin_seeds, 'M', Items.reeds });
		CraftingManager.addRecipeAuto(new ItemStack(ModItems.syringe_antidote, 6), new Object[] { "SPS", "SMS", "SPS", 'S', ModItems.syringe_empty, 'P', Items.pumpkin_seeds, 'M', Items.reeds });
		CraftingManager.addRecipeAuto(new ItemStack(ModItems.syringe_poison, 1), new Object[] { "SLS", "LCL", "SLS", 'C', ModItems.syringe_empty, 'S', Items.spider_eye, 'L', PB.dust() });
		CraftingManager.addRecipeAuto(new ItemStack(ModItems.syringe_poison, 1), new Object[] { "SLS", "LCL", "SLS", 'C', ModItems.syringe_empty, 'S', Items.spider_eye, 'L', ModItems.powder_poison });
		CraftingManager.addRecipeAuto(new ItemStack(ModItems.syringe_metal_empty, 6), new Object[] { "P", "C", "B", 'B', Blocks.iron_bars, 'C', ModItems.rod_empty, 'P', IRON.plate() });
		CraftingManager.addRecipeAuto(new ItemStack(ModItems.syringe_metal_stimpak, 1), new Object[] { " N ", "NSN", " N ", 'N', Items.nether_wart, 'S', ModItems.syringe_metal_empty });
		CraftingManager.addShapelessAuto(new ItemStack(ModItems.syringe_metal_stimpak, 1), new Object[] { ModItems.nitra_small, ModItems.nitra_small, ModItems.nitra_small, ModItems.syringe_metal_empty });
		CraftingManager.addShapelessAuto(new ItemStack(ModItems.syringe_taint), new Object[] { ModItems.bottle2_empty, ModItems.syringe_metal_empty, ModItems.ducttape, ModItems.powder_magic, SA326.nugget(), Items.potionitem });
		
		//Medicine
		CraftingManager.addRecipeAuto(new ItemStack(ModItems.pill_iodine, 8), new Object[] { "IF", 'I', I.dust(), 'F', F.dust() });
		CraftingManager.addRecipeAuto(new ItemStack(ModItems.plan_c, 1), new Object[] { "PFP", 'P', ModItems.powder_poison, 'F', F.dust() });
		CraftingManager.addShapelessAuto(new ItemStack(ModItems.radx, 1), new Object[] { COAL.dust(), COAL.dust(), F.dust() });
		CraftingManager.addShapelessAuto(new ItemStack(ModItems.fmn, 1), new Object[] { COAL.dust(), PO210.dust(), ST.dust() });
		CraftingManager.addShapelessAuto(new ItemStack(ModItems.five_htp, 1), new Object[] { COAL.dust(), EUPH.dust(), ModItems.canteen_fab });
		CraftingManager.addShapelessAuto(new ItemStack(ModItems.cigarette, 16), new Object[] { ASBESTOS.ingot(), ANY_TAR.any(), PO210.nugget(), DictFrame.fromOne(ModItems.plant_item, ItemEnums.EnumPlantType.TOBACCO) });
		CraftingManager.addShapelessAuto(new ItemStack(ModItems.crackpipe, 1), new Object[] { ModItems.catalytic_converter });
		
		if(GeneralConfig.enableLBSM && GeneralConfig.enableLBSMSimpleMedicineRecipes) {
			CraftingManager.addShapelessAuto(new ItemStack(ModItems.siox, 8), new Object[] { COAL.dust(), ASBESTOS.dust(), GOLD.nugget() });
			CraftingManager.addShapelessAuto(new ItemStack(ModItems.xanax, 1), new Object[] { COAL.dust(), KNO.dust(), NETHERQUARTZ.dust() });
		} else {
			CraftingManager.addShapelessAuto(new ItemStack(ModItems.siox, 8), new Object[] { COAL.dust(), ASBESTOS.dust(), ModItems.nugget_bismuth });
			CraftingManager.addShapelessAuto(new ItemStack(ModItems.xanax, 1), new Object[] { COAL.dust(), KNO.dust(), BR.dust() });
		}

		//IV Bags
		CraftingManager.addRecipeAuto(new ItemStack(ModItems.iv_empty, 4), new Object[] { "S", "I", "S", 'S', ANY_RUBBER.ingot(), 'I', IRON.plate() });
		CraftingManager.addShapelessAuto(new ItemStack(ModItems.iv_xp_empty, 1), new Object[] { ModItems.iv_empty, ModItems.powder_magic });
		
		//Radaway
		CraftingManager.addShapelessAuto(new ItemStack(ModItems.radaway, 1), new Object[] { ModItems.iv_blood, COAL.dust(), Items.pumpkin_seeds });
		CraftingManager.addShapelessAuto(new ItemStack(ModItems.radaway_strong, 1), new Object[] { ModItems.radaway, ModBlocks.mush });
		CraftingManager.addShapelessAuto(new ItemStack(ModItems.radaway_flush, 1), new Object[] { ModItems.radaway_strong, I.dust() });

		//Cladding
		CraftingManager.addShapelessAuto(new ItemStack(ModItems.cladding_paint, 1), new Object[] { PB.nugget(), PB.nugget(), PB.nugget(), PB.nugget(), Items.clay_ball, Items.glass_bottle });
		CraftingManager.addRecipeAuto(new ItemStack(ModItems.cladding_rubber, 1), new Object[] { "RCR", "CDC", "RCR", 'R', ANY_RUBBER.ingot(), 'C', COAL.dust(), 'D', ModItems.ducttape });
		CraftingManager.addRecipeAuto(new ItemStack(ModItems.cladding_lead, 1), new Object[] { "DPD", "PRP", "DPD", 'R', ModItems.cladding_rubber, 'P', PB.plate(), 'D', ModItems.ducttape });
		CraftingManager.addRecipeAuto(new ItemStack(ModItems.cladding_desh, 1), new Object[] { "DPD", "PRP", "DPD", 'R', ModItems.cladding_lead, 'P', ModItems.plate_desh, 'D', ModItems.ducttape });
		CraftingManager.addRecipeAuto(new ItemStack(ModItems.cladding_ghiorsium, 1), new Object[] { "DPD", "PRP", "DPD", 'R', ModItems.cladding_desh, 'P', ModItems.ingot_gh336, 'D', ModItems.ducttape });
		CraftingManager.addRecipeAuto(new ItemStack(ModItems.cladding_obsidian, 1), new Object[] { "OOO", "PDP", "OOO", 'O', Blocks.obsidian, 'P', STEEL.plate(), 'D', ModItems.ducttape });
		CraftingManager.addRecipeAuto(new ItemStack(ModItems.cladding_iron, 1), new Object[] { "OOO", "PDP", "OOO", 'O', IRON.plate(), 'P', ModItems.plate_polymer, 'D', ModItems.ducttape });
		
		//Servos
		CraftingManager.addRecipeAuto(new ItemStack(ModItems.servo_set, 1), new Object[] { "MBM", "PBP", "MBM", 'M', ModItems.motor, 'B', STEEL.bolt(), 'P', IRON.plate() });
		CraftingManager.addRecipeAuto(new ItemStack(ModItems.servo_set_desh, 1), new Object[] { "MBM", "PSP", "MBM", 'M', ModItems.motor_desh, 'B', DURA.bolt(), 'P', ALLOY.plate(), 'S', ModItems.servo_set });
		
		//Helmet Mods
		CraftingManager.addRecipeAuto(new ItemStack(ModItems.attachment_mask, 1), new Object[] { "DID", "IGI", " F ", 'D', ModItems.ducttape, 'I', ANY_RUBBER.ingot(), 'G', KEY_ANYPANE, 'F', IRON.plate() });
		CraftingManager.addRecipeAuto(new ItemStack(ModItems.attachment_mask_mono, 1), new Object[] { " D ", "DID", " F ", 'D', ModItems.ducttape, 'I', ANY_RUBBER.ingot(), 'F', IRON.plate() });
		
		//Boot Mods
		CraftingManager.addRecipeAuto(new ItemStack(ModItems.pads_rubber, 1), new Object[] { "P P", "IDI", "P P", 'P', ANY_RUBBER.ingot(), 'I', IRON.plate(), 'D', ModItems.ducttape });
		CraftingManager.addRecipeAuto(new ItemStack(ModItems.pads_slime, 1), new Object[] { "SPS", "DSD", "SPS", 'S', KEY_SLIME, 'P', ModItems.pads_rubber, 'D', ModItems.ducttape });
		CraftingManager.addRecipeAuto(new ItemStack(ModItems.pads_static, 1), new Object[] { "CDC", "ISI", "CDC", 'C', CU.ingot(), 'D', ModItems.ducttape, 'I', ANY_RUBBER.ingot(), 'S', ModItems.pads_slime });
		
		//Batteries
		CraftingManager.addRecipeAuto(new ItemStack(ModItems.armor_battery, 1), new Object[] { "PCP", "PCP", "PCP", 'P', STEEL.plate(), 'C', ModBlocks.capacitor_gold });
		CraftingManager.addRecipeAuto(new ItemStack(ModItems.armor_battery_mk2, 1), new Object[] { "PCP", "PCP", "PCP", 'P', ANY_PLASTIC.ingot(), 'C', ModBlocks.capacitor_niobium });
		CraftingManager.addRecipeAuto(new ItemStack(ModItems.armor_battery_mk3, 1), new Object[] { "PCP", "PCP", "PCP", 'P', GOLD.plate(), 'C', ModBlocks.capacitor_tantalium });
		
		//Special Mods
		CraftingManager.addRecipeAuto(new ItemStack(ModItems.horseshoe_magnet, 1), new Object[] { "L L", "I I", "ILI", 'L', ModItems.lodestone, 'I', IRON.ingot() });
		CraftingManager.addRecipeAuto(new ItemStack(ModItems.industrial_magnet, 1), new Object[] { "SMS", " B ", "SMS", 'S', STEEL.ingot(), 'M', ModItems.horseshoe_magnet, 'B', ModBlocks.fusion_conductor });
		CraftingManager.addRecipeAuto(new ItemStack(ModItems.heart_container, 1), new Object[] { "HAH", "ACA", "HAH", 'H', ModItems.heart_piece, 'A', AL.ingot(), 'C', ModItems.coin_creeper });
		CraftingManager.addRecipeAuto(new ItemStack(ModItems.heart_booster, 1), new Object[] { "GHG", "MCM", "GHG", 'G', GOLD.ingot(), 'H', ModItems.heart_container, 'M', ModItems.morning_glory, 'C', ModItems.coin_maskman });
		CraftingManager.addRecipeAuto(new ItemStack(ModItems.heart_fab, 1), new Object[] { "GHG", "MCM", "GHG", 'G', PO210.billet(), 'H', ModItems.heart_booster, 'M', ModItems.canteen_fab, 'C', ModItems.coin_worm });
		CraftingManager.addRecipeAuto(new ItemStack(ModItems.ink, 1), new Object[] { "FPF", "PIP", "FPF", 'F', new ItemStack(Blocks.red_flower, 1, OreDictionary.WILDCARD_VALUE), 'P', ModItems.armor_polish, 'I', KEY_BLACK });
		CraftingManager.addRecipeAuto(new ItemStack(ModItems.bathwater_mk2, 1), new Object[] { "MWM", "WBW", "MWM", 'M', ModItems.bottle_mercury, 'W', ModItems.nuclear_waste, 'B', ModItems.bathwater });
		CraftingManager.addRecipeAuto(new ItemStack(ModItems.back_tesla, 1), new Object[] { "DGD", "GTG", "DGD", 'D', ModItems.ducttape, 'G', GOLD.wireFine(), 'T', ModBlocks.tesla });
		CraftingManager.addRecipeAuto(new ItemStack(ModItems.medal_liquidator, 1), new Object[] { "GBG", "BFB", "GBG", 'G', AU198.nugget(), 'B', B.ingot(), 'F', ModItems.debris_fuel });
		CraftingManager.addShapelessAuto(new ItemStack(ModItems.injector_5htp, 1), new Object[] { ModItems.five_htp, DictFrame.fromOne(ModItems.circuit, EnumCircuitType.BASIC), BIGMT.plate() });
		CraftingManager.addShapelessAuto(new ItemStack(ModItems.injector_knife, 1), new Object[] { ModItems.injector_5htp, Items.iron_sword });
		CraftingManager.addRecipeAuto(new ItemStack(ModItems.shackles, 1), new Object[] { "CIC", "C C", "I I", 'I', ModItems.ingot_chainsteel, 'C', ModBlocks.chain });
		CraftingManager.addRecipeAuto(new ItemStack(ModItems.black_diamond, 1), new Object[] { "NIN", "IGI", "NIN", 'N', AU198.nugget(), 'I', ModItems.ink, 'G', VOLCANIC.gem() });
		CraftingManager.addRecipeAuto(new ItemStack(ModItems.protection_charm, 1), new Object[] { " M ", "MDM", " M ", 'M', ModItems.fragment_meteorite, 'D', DIAMOND.gem() });
		CraftingManager.addRecipeAuto(new ItemStack(ModItems.meteor_charm, 1), new Object[] { " M ", "MDM", " M ", 'M', ModItems.fragment_meteorite, 'D', VOLCANIC.gem() });
		CraftingManager.addRecipeAuto(new ItemStack(ModItems.neutrino_lens, 1), new Object[] { "PSP", "SCS", "PSP", 'P', ANY_PLASTIC.ingot(), 'S', STAR.ingot(), 'C', DictFrame.fromOne(ModItems.circuit, EnumCircuitType.BISMOID) });
		CraftingManager.addRecipeAuto(new ItemStack(ModItems.gas_tester, 1), new Object[] { "G", "C", "I", 'G', GOLD.plate(), 'C', DictFrame.fromOne(ModItems.circuit, EnumCircuitType.VACUUM_TUBE), 'I', IRON.plate() });
		CraftingManager.addRecipeAuto(new ItemStack(ModItems.defuser_gold, 1), new Object[] { "GPG", "PRP", "GPG", 'G', Items.gunpowder, 'P', GOLD.plate(), 'R', "record" });
		CraftingManager.addRecipeAuto(new ItemStack(ModItems.ballistic_gauntlet, 1), new Object[] { " WS", "WRS", " RS", 'W', CU.wireFine(), 'R', ModItems.ring_starmetal, 'S', STEEL.plate() });
		CraftingManager.addRecipeAuto(new ItemStack(ModItems.night_vision, 1), "P P", "GCG", 'P', ANY_PLASTIC.ingot(), 'G', KEY_ANYGLASS, 'C', DictFrame.fromOne(ModItems.circuit, EnumCircuitType.BASIC));

		//Stealth boy
		CraftingManager.addRecipeAuto(new ItemStack(ModItems.stealth_boy, 1), new Object[] { " B", "LI", "LC", 'B', Blocks.stone_button, 'L', Items.leather, 'I', STEEL.ingot(), 'C', DictFrame.fromOne(ModItems.circuit, EnumCircuitType.BASIC) });

		//RD40 Filters
		CraftingManager.addRecipeAuto(new ItemStack(ModItems.gas_mask_filter, 1), new Object[] { "I", "F", 'F', ModItems.filter_coal, 'I', IRON.plate() });
		CraftingManager.addRecipeAuto(new ItemStack(ModItems.gas_mask_filter_mono, 1), new Object[] { "ZZZ", "ZCZ", "ZZZ", 'Z', ZR.nugget(), 'C', ModItems.catalyst_clay });
		CraftingManager.addRecipeAuto(new ItemStack(ModItems.gas_mask_filter_combo, 1), new Object[] { "ZCZ", "CFC", "ZCZ", 'Z', ZR.ingot(), 'C', ModItems.catalyst_clay, 'F', ModItems.gas_mask_filter });
		CraftingManager.addRecipeAuto(new ItemStack(ModItems.gas_mask_filter_rag, 1), new Object[] { "I", "F", 'F', ModItems.rag_damp, 'I', IRON.ingot() });
		CraftingManager.addRecipeAuto(new ItemStack(ModItems.gas_mask_filter_piss, 1), new Object[] { "I", "F", 'F', ModItems.rag_piss, 'I', IRON.ingot() });
	}
}
