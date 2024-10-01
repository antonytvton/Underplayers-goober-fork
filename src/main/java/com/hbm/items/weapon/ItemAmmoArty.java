package com.hbm.items.weapon;

import java.io.Console;
import java.util.List;
import java.util.Random;

import com.hbm.blocks.ModBlocks;
import com.hbm.config.BombConfig;
import com.hbm.entity.effect.EntityMist;
import com.hbm.entity.effect.EntityNukeTorex;
import com.hbm.entity.logic.EntityNukeExplosionMK5;
import com.hbm.entity.projectile.EntityArtilleryShell;
import com.hbm.explosion.ExplosionChaos;
import com.hbm.explosion.ExplosionLarge;
import com.hbm.explosion.ExplosionNT;
import com.hbm.explosion.ExplosionNukeSmall;
import com.hbm.explosion.ExplosionNT.ExAttrib;
import com.hbm.explosion.vanillant.ExplosionVNT;
import com.hbm.explosion.vanillant.standard.BlockAllocatorStandard;
import com.hbm.explosion.vanillant.standard.BlockMutatorDebris;
import com.hbm.explosion.vanillant.standard.BlockProcessorStandard;
import com.hbm.explosion.vanillant.standard.EntityProcessorCross;
import com.hbm.explosion.vanillant.standard.PlayerProcessorStandard;
import com.hbm.handler.pollution.PollutionHandler;
import com.hbm.handler.pollution.PollutionHandler.PollutionType;
import com.hbm.inventory.fluid.Fluids;
import com.hbm.lib.RefStrings;
import com.hbm.main.MainRegistry;
import com.hbm.packet.PacketDispatcher;
import com.hbm.packet.toclient.AuxParticlePacketNT;
import com.hbm.particle.SpentCasing;
import com.hbm.particle.SpentCasing.CasingType;
import com.hbm.particle.helper.ExplosionCreator;
import com.hbm.potion.HbmPotion;

import cpw.mods.fml.common.network.NetworkRegistry.TargetPoint;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.IIcon;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.util.Vec3;
import net.minecraft.util.MovingObjectPosition.MovingObjectType;

public class ItemAmmoArty extends Item {
	
	public static Random rand = new Random();
	public static ArtilleryShell[] itemTypes =	new ArtilleryShell[ /* >>> */ 14 /* <<< */ ];
	/* item types */
	public final int HE = 0;
	public final int FRAG = 1;
	public final int LASER = 2;
	public final int HHE = 3;
	public final int BUNKER = 4;
	public final int PHOSPHORUS = 5;
	public final int PHOSPHORUS_MULTI = 6;
	public final int CHLORINE = 7;
	public final int PHOSGENE = 8;
	public final int MUSTARD = 9;
	public final int NUKE = 10;
	public final int MINI_NUKE = 11;
	public final int MINI_NUKE_MULTI = 12;
	public final int CARGO = 13;

	/* non-item shell types */
	
	public ItemAmmoArty() {
		this.setHasSubtypes(true);
		this.setCreativeTab(MainRegistry.weaponTab);
		init();
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void getSubItems(Item item, CreativeTabs tab, List list) {
		list.add(new ItemStack(item, 1, HE));
		list.add(new ItemStack(item, 1, FRAG));
		list.add(new ItemStack(item, 1, LASER));
		list.add(new ItemStack(item, 1, HHE));
		list.add(new ItemStack(item, 1, BUNKER));
		list.add(new ItemStack(item, 1, PHOSPHORUS));
		list.add(new ItemStack(item, 1, PHOSPHORUS_MULTI));
		list.add(new ItemStack(item, 1, CHLORINE));
		list.add(new ItemStack(item, 1, PHOSGENE));
		list.add(new ItemStack(item, 1, MUSTARD));
		list.add(new ItemStack(item, 1, NUKE));
		list.add(new ItemStack(item, 1, MINI_NUKE));
		list.add(new ItemStack(item, 1, MINI_NUKE_MULTI));
		list.add(new ItemStack(item, 1, CARGO));

	}

	@Override
	public void addInformation(ItemStack stack, EntityPlayer player, List list, boolean bool) {

		String r = EnumChatFormatting.RED + "";
		String y = EnumChatFormatting.YELLOW + "";
		String b = EnumChatFormatting.BLUE + "";
		
		switch(stack.getItemDamage()) {
		case HE:
			list.add(y + "Destroys blocks");
			list.add(y + "Normal Player Dammage");
			list.add(b + "Accuracy: 25 blocks");
			list.add(b + "Reload time: 2s");
			list.add(r + "For when you want to remove medium problems");
			
			break;
		case FRAG:
			list.add(y + "Doesnt destroy blocks");
			list.add(y + "Increased Player Dammage");
			list.add(b + "Accuracy: 25 blocks");
			list.add(b + "Reload time: 2s");
			list.add(r + "AIRBURST");
			break;
		case LASER:
			list.add(y + "Destroys blocks");
			list.add(y + "Normal Player Dammage");
			list.add(b + "Accuracy: 3 blocks");
			list.add(b + "Reload time: 2s");
			list.add(r + "G-hardened electronics are my best friend");
			break;
		case HHE:
			list.add(y + "Destroys blocks extra well");
			list.add(y + "Normal Player Dammage");
			list.add(b + "Accuracy: 25 blocks");
			list.add(b + "Reload time: 8s");
			list.add(r + "For when you want to remove large problems");
			break;
		case BUNKER:
			list.add(y + "Destroys concrete");
			list.add(y + "Lower Player Dammage");
			list.add(b + "Accuracy: 5 blocks");
			list.add(b + "Reload time: 8s");
			list.add(r + "For when you want to remove emplaced problems");
			break;
		case PHOSPHORUS:
			list.add(r + "Its not a war crime if you dont declare war");
			list.add(b + "Reload time: 2s");
			list.add(r + "Ive gotta warn you this doesnt do too much player damage. It excells in forest removal however");
			break;
		case PHOSPHORUS_MULTI:
			list.add(b + "Reload time: 8s");
			list.add(r + "Just as our founding fathers would have intended");
			break;
		case CHLORINE:
			list.add(b + "Reload time: 2s");
			list.add(r + "Pray the wind is traveling the right direction");
			break;
		case PHOSGENE:
			list.add(b + "Reload time: 2s");
			list.add(r + "This ones food safe!");
			break;
		case MUSTARD:
			list.add(b + "Reload time: 2s");
			list.add(r + "Ive run out of fun jokes");
			break;
		case NUKE:
			list.add(r + "☠");
			list.add(r + "(that is the best skull and crossbones");
			list.add(r + "minecraft's unicode has to offer)");
			list.add(b + "Reload time: 8s");
			break;
		case MINI_NUKE:
			list.add(r + "kinda self explanatory");
			list.add(b + "Reload time: 2s");
			break;
			
		case MINI_NUKE_MULTI:
			list.add(r + "Nuke but smaller, but also more");
			list.add(b + "Reload time: 2s");
			break;
		case CARGO:
			
			if(stack.hasTagCompound() && stack.stackTagCompound.getCompoundTag("cargo") != null) {
				ItemStack cargo = ItemStack.loadItemStackFromNBT(stack.stackTagCompound.getCompoundTag("cargo"));
				list.add(y + cargo.getDisplayName());
			} else {
				list.add(r + "Empty");
			}
			break;
		}
	}

	private IIcon[] icons = new IIcon[itemTypes.length];
	private IIcon iconCargo;

	@SideOnly(Side.CLIENT)
	public void registerIcons(IIconRegister reg) {
		
		this.icons = new IIcon[itemTypes.length];

		for(int i = 0; i < icons.length; i++) {
			this.icons[i] = reg.registerIcon(RefStrings.MODID + ":" + itemTypes[i].name);
		}
		
		this.iconCargo = reg.registerIcon(RefStrings.MODID + ":ammo_arty_cargo_full");
	}
	
	@SideOnly(Side.CLIENT)
	public IIcon getIconIndex(ItemStack stack) {
		
		if(stack.getItemDamage() == CARGO && stack.hasTagCompound() && stack.stackTagCompound.getCompoundTag("cargo") != null) {
			return this.iconCargo;
		}
		
		return this.getIconFromDamage(stack.getItemDamage());
	}
	
	@Override
	@SideOnly(Side.CLIENT)
	public IIcon getIconFromDamage(int meta) {
		return this.icons[meta];
	}
	
	@Override
	public String getUnlocalizedName(ItemStack stack) {
		return "item." + itemTypes[Math.abs(stack.getItemDamage()) % itemTypes.length].name;
	}
	
	protected static SpentCasing SIXTEEN_INCH_CASE = new SpentCasing(CasingType.STRAIGHT).setScale(15F, 15F, 10F).setupSmoke(1F, 1D, 200, 60).setMaxAge(300);
	
	public abstract class ArtilleryShell {
		
		String name;
		public SpentCasing casing;
		public int reloadmult;
		
		public ArtilleryShell(String name, int casingColor, int reloadmult) {
			this.name = name;
			this.casing = SIXTEEN_INCH_CASE.clone().register(name).setColor(casingColor);
			this.reloadmult = reloadmult;
		}
		
		public abstract void onImpact(EntityArtilleryShell shell, MovingObjectPosition mop);
		public void onUpdate(EntityArtilleryShell shell) { }
	}
	
	public static void standardExplosion(EntityArtilleryShell shell, MovingObjectPosition mop, float size, float rangeMod, boolean breaksBlocks, boolean breaksConcrete, int innacuracy, int bunkerbusting) {
		Random rand = new Random();
		
		Vec3 vec = Vec3.createVectorHelper(shell.motionX, shell.motionY, shell.motionZ).normalize();
		int offest = -rand.nextInt(2*innacuracy)+innacuracy;
		int offest2 = -rand.nextInt(2*innacuracy)+innacuracy;
		int lift = 0;
		while ((shell.worldObj.isAirBlock((int)(Math.floor(mop.hitVec.xCoord+offest)), ((int)Math.floor(mop.hitVec.yCoord))+lift, ((int)Math.floor(mop.hitVec.zCoord+offest2))))&&
		(shell.worldObj.isAirBlock((int)(Math.floor(mop.hitVec.xCoord+offest)), ((int)Math.floor(mop.hitVec.yCoord))+lift, ((int)Math.floor(mop.hitVec.zCoord+offest2))))){//this fucking sucks
			if (!(shell.worldObj.isAirBlock((int)(Math.floor(mop.hitVec.xCoord+offest)), ((int)Math.floor(mop.hitVec.yCoord))-lift, ((int)Math.floor(mop.hitVec.zCoord+offest2))))) {
				lift = -lift;
			}else {
				lift = lift + 1;

			}
		}
		ExplosionVNT xnt = new ExplosionVNT(shell.worldObj, mop.hitVec.xCoord - vec.xCoord + offest, mop.hitVec.yCoord - vec.yCoord+lift, mop.hitVec.zCoord + offest2, size);


		if(breaksBlocks) {
			
			if (bunkerbusting > 0) {
				for(int i = 0; i < 6; i++) {
					ExplosionNT explosion = new ExplosionNT(shell.worldObj, shell, mop.blockX+ offest, mop.blockY+lift - i+5, mop.blockZ - offest2, 3F);
					explosion.addAllAttrib(ExAttrib.ERRODE);
					explosion.explode(); //an explosion exploded!
				}
			}
			else {
				xnt.setBlockAllocator(new BlockAllocatorStandard(48));
				xnt.setBlockProcessor(new BlockProcessorStandard().setNoDrop().withBlockEffect(new BlockMutatorDebris(ModBlocks.block_slag, 1)));
			}
			
		}
		if (bunkerbusting < 0) {
			for(int i = 0; i < 20; i++) {
				ExplosionNT explosion = new ExplosionNT(shell.worldObj, shell, mop.blockX+ offest, mop.blockY+lift - i+10, mop.blockZ + offest2, 20F);
				explosion.addAllAttrib(ExAttrib.STRIP);
				explosion.addAllAttrib(ExAttrib.NODROP);
				explosion.addAllAttrib(ExAttrib.NOPARTICLE);
				explosion.addAllAttrib(ExAttrib.NOSOUND);
				explosion.addAllAttrib(ExAttrib.NOPARTICLE);
				explosion.explode();
			}
		}
		ExplosionCreator.composeEffect(shell.worldObj, mop.blockX + 0.5+offest, mop.blockY+lift-bunkerbusting, mop.blockZ + 0.5+offest2, 10, 2F, 0.5F, 25F, 5, 0, 20, 0.75F, 1F, -2F, 150);
		xnt.setEntityProcessor(new EntityProcessorCross(7.5D).withRangeMod(rangeMod));
		xnt.setPlayerProcessor(new PlayerProcessorStandard());
		//xnt.setSFX(new ExplosionEffectStandard());
		xnt.explode();
		shell.killAndClear();
	}
	
	public static void standardCluster(EntityArtilleryShell shell, int clusterType, int amount, double splitHeight, double deviation) {
		if(!shell.getWhistle() || shell.motionY > 0) return;
		if(shell.getTargetHeight() + splitHeight < shell.posY) return;
		
		shell.killAndClear();
		
		NBTTagCompound data = new NBTTagCompound();
		data.setString("type", "plasmablast");
		data.setFloat("r", 1.0F);
		data.setFloat("g", 1.0F);
		data.setFloat("b", 1.0F);
		data.setFloat("scale", 50F);
		PacketDispatcher.wrapper.sendToAllAround(new AuxParticlePacketNT(data, shell.posX, shell.posY, shell.posZ),
				new TargetPoint(shell.dimension, shell.posX, shell.posY, shell.posZ, 500));
		
		for(int i = 0; i < amount; i++) {
			EntityArtilleryShell cluster = new EntityArtilleryShell(shell.worldObj);
			cluster.setType(clusterType);
			cluster.motionX = i == 0 ? shell.motionX : (shell.motionX + rand.nextGaussian() * deviation);
			cluster.motionY = shell.motionY;
			cluster.motionZ = i == 0 ? shell.motionZ : (shell.motionZ + rand.nextGaussian() * deviation);
			cluster.setPositionAndRotation(shell.posX, shell.posY, shell.posZ, shell.rotationYaw, shell.rotationPitch);
			double[] target = shell.getTarget();
			cluster.setTarget(target[0], target[1], target[2]);
			cluster.setWhistle(shell.getWhistle() && !shell.didWhistle());
			shell.worldObj.spawnEntityInWorld(cluster);
		}
	}
	
	private void init() {
		/* STANDARD SHELLS */
		this.itemTypes[HE] = new ArtilleryShell("ammo_arty_he", SpentCasing.COLOR_CASE_16INCH, 1) { 
			public void onImpact(EntityArtilleryShell shell, MovingObjectPosition mop) { 
				standardExplosion(shell, mop, 10F, 1F, true, false, 25, 0);}};
				
				
		this.itemTypes[FRAG] = new ArtilleryShell("ammo_arty_frag", SpentCasing.COLOR_CASE_16INCH, 1) { 
			public void onImpact(EntityArtilleryShell shell, MovingObjectPosition mop) { 
				standardExplosion(shell, mop, 8F, 2F, false, false, 25, -25); }};
				
		this.itemTypes[LASER] = new ArtilleryShell("ammo_arty_laser", SpentCasing.COLOR_CASE_16INCH, 1) { 
			public void onImpact(EntityArtilleryShell shell, MovingObjectPosition mop) { 
				standardExplosion(shell, mop, 8F, 1F, true, false, 2, 0); }};
				
		this.itemTypes[HHE] = new ArtilleryShell("ammo_arty_higher_explosive", SpentCasing.COLOR_CASE_16INCH, 4) { 
			public void onImpact(EntityArtilleryShell shell, MovingObjectPosition mop) { 
				standardExplosion(shell, mop, 20F, 1F, true, false, 25, 0); }};
				
		this.itemTypes[BUNKER] = new ArtilleryShell("ammo_arty_bunker", SpentCasing.COLOR_CASE_16INCH, 4) { 
			public void onImpact(EntityArtilleryShell shell, MovingObjectPosition mop) { 
				standardExplosion(shell, mop, 10F, 1F, true, true, 5, 5); }};

		/* MINI NUKE */
		this.itemTypes[MINI_NUKE] = new ArtilleryShell("ammo_arty_mini_nuke", SpentCasing.COLOR_CASE_16INCH_NUKE, 1) {
			public void onImpact(EntityArtilleryShell shell, MovingObjectPosition mop) {
				shell.killAndClear();
				Vec3 vec = Vec3.createVectorHelper(shell.motionX, shell.motionY, shell.motionZ).normalize();
				ExplosionNukeSmall.explode(shell.worldObj, mop.hitVec.xCoord - vec.xCoord, mop.hitVec.yCoord - vec.yCoord, mop.hitVec.zCoord - vec.zCoord, ExplosionNukeSmall.PARAMS_MEDIUM);
			}
		};
		
		/* FULL NUKE */
		this.itemTypes[NUKE] = new ArtilleryShell("ammo_arty_nuke", SpentCasing.COLOR_CASE_16INCH_NUKE, 4) {
			public void onImpact(EntityArtilleryShell shell, MovingObjectPosition mop) {
				shell.worldObj.spawnEntityInWorld(EntityNukeExplosionMK5.statFac(shell.worldObj, BombConfig.missileRadius, mop.hitVec.xCoord, mop.hitVec.yCoord, mop.hitVec.zCoord));
				EntityNukeTorex.statFac(shell.worldObj, mop.hitVec.xCoord, mop.hitVec.yCoord, mop.hitVec.zCoord, BombConfig.missileRadius);
				shell.setDead();
			}
		};
		
		/* PHOSPHORUS */
		this.itemTypes[PHOSPHORUS] = new ArtilleryShell("ammo_arty_phosphorus", SpentCasing.COLOR_CASE_16INCH_PHOS, 1) {
			public void onImpact(EntityArtilleryShell shell, MovingObjectPosition mop) {
				Random explosionRNG = new Random();
				int offset3 = 25 - explosionRNG.nextInt(50);
				int offset4 = 25 - explosionRNG.nextInt(50);
				shell.worldObj.playSoundEffect(shell.posX + offset3, shell.posY, shell.posZ + offset4, "hbm:weapon.explosionMedium", 20.0F, 0.9F + rand.nextFloat() * 0.2F);
				ExplosionLarge.spawnShrapnels(shell.worldObj, (int) mop.hitVec.xCoord + offset3, (int) mop.hitVec.yCoord, (int) mop.hitVec.zCoord + offset4, 15);
				int radius = 30;
				int r = radius;
				int r2 = r * r;
				int r22 = r2 / 2;
				for (int xx = -r; xx < r; xx++) {
					int X = xx + (int) mop.hitVec.xCoord + offset3;
					int XX = xx * xx;
					for (int yy = -r; yy < r; yy++) {
						int Y = yy + (int) mop.hitVec.yCoord;
						int YY = XX + yy * yy;
						for (int zz = -r; zz < r; zz++) {
							int Z = zz + (int) mop.hitVec.zCoord + offset4;
							int ZZ = YY + zz * zz;
							if (ZZ < r22) {
								if ((shell.worldObj.getBlock(X, Y + 1, Z) == Blocks.air
										|| shell.worldObj.getBlock(X, Y + 1, Z) == Blocks.snow_layer)
										&& shell.worldObj.getBlock(X, Y, Z) != Blocks.air) {
									float b = explosionRNG.nextFloat();
									if (b < 0.2f){ 
										shell.worldObj.setBlock(X, Y + 1, Z, Blocks.fire); }
								}
							}
						}
					}
				}
				List<Entity> hit = shell.worldObj.getEntitiesWithinAABBExcludingEntity(shell, AxisAlignedBB.getBoundingBox(shell.posX - radius, shell.posY - radius, shell.posZ - radius, shell.posX + radius, shell.posY + radius, shell.posZ + radius));
				for(Entity e : hit) {
					e.setFire(5);
					if(e instanceof EntityLivingBase) {
						PotionEffect eff = new PotionEffect(HbmPotion.phosphorus.id, 30 * 20, 0, true);
						eff.getCurativeItems().clear();
						((EntityLivingBase)e).addPotionEffect(eff);
					}
				}
				for(int i = 0; i < 5; i++) {
					NBTTagCompound haze = new NBTTagCompound();
					haze.setString("type", "haze");
					PacketDispatcher.wrapper.sendToAllAround(new AuxParticlePacketNT(haze, mop.hitVec.xCoord + shell.worldObj.rand.nextGaussian() * 10+offset3, mop.hitVec.yCoord, mop.hitVec.zCoord +offset4+ shell.worldObj.rand.nextGaussian() * 10), new TargetPoint(shell.dimension, shell.posX, shell.posY, shell.posZ, 150));
				}
				NBTTagCompound data = new NBTTagCompound();
				data.setString("type", "rbmkmush");
				data.setFloat("scale", 10);
				PacketDispatcher.wrapper.sendToAllAround(new AuxParticlePacketNT(data, mop.hitVec.xCoord+offset3, mop.hitVec.yCoord, mop.hitVec.zCoord+offset4), new TargetPoint(shell.dimension, shell.posX+offset3, shell.posY, shell.posZ+offset4, 250));
			}
		};
		
		/* THIS DOOFUS */
		this.itemTypes[CARGO] = new ArtilleryShell("ammo_arty_cargo", SpentCasing.COLOR_CASE_16INCH, 1) { public void onImpact(EntityArtilleryShell shell, MovingObjectPosition mop) {
			if(mop.typeOfHit == MovingObjectType.BLOCK) {
				shell.setPosition(mop.hitVec.xCoord, mop.hitVec.yCoord, mop.hitVec.zCoord);
				shell.getStuck(mop.blockX, mop.blockY, mop.blockZ, mop.sideHit);
			}
		}};
		
		/* GAS */
		this.itemTypes[CHLORINE] = new ArtilleryShell("ammo_arty_chlorine_gas", SpentCasing.COLOR_CASE_16INCH, 1) {
			public void onImpact(EntityArtilleryShell shell, MovingObjectPosition mop) {
				shell.killAndClear();
				Vec3 vec = Vec3.createVectorHelper(shell.motionX, shell.motionY, shell.motionZ).normalize();
				shell.worldObj.createExplosion(shell, mop.hitVec.xCoord - vec.xCoord, mop.hitVec.yCoord - vec.yCoord, mop.hitVec.zCoord - vec.zCoord, 5F, false);
				EntityMist mist = new EntityMist(shell.worldObj);
				mist.setType(Fluids.CHLORINE);
				mist.setPosition(mop.hitVec.xCoord - vec.xCoord, mop.hitVec.yCoord - vec.yCoord - 3, mop.hitVec.zCoord - vec.zCoord);
				mist.setArea(15, 7.5F);
				shell.worldObj.spawnEntityInWorld(mist);
				PollutionHandler.incrementPollution(shell.worldObj, mop.blockX, mop.blockY, mop.blockZ, PollutionType.HEAVYMETAL, 5F);
			}
		};
		this.itemTypes[PHOSGENE] = new ArtilleryShell("ammo_arty_phosgene_gas", SpentCasing.COLOR_CASE_16INCH_NUKE, 1) {
			public void onImpact(EntityArtilleryShell shell, MovingObjectPosition mop) {
				shell.killAndClear();
				Vec3 vec = Vec3.createVectorHelper(shell.motionX, shell.motionY, shell.motionZ).normalize();
				shell.worldObj.createExplosion(shell, mop.hitVec.xCoord - vec.xCoord, mop.hitVec.yCoord - vec.yCoord, mop.hitVec.zCoord - vec.zCoord, 5F, false);
				for(int i = 0; i < 3; i++) {
					EntityMist mist = new EntityMist(shell.worldObj);
					mist.setType(Fluids.PHOSGENE);
					double x = mop.hitVec.xCoord - vec.xCoord;
					double z = mop.hitVec.zCoord - vec.zCoord;
					if(i > 0) {
						x += rand.nextGaussian() * 15;
						z += rand.nextGaussian() * 15;
					}
					mist.setPosition(x, mop.hitVec.yCoord - vec.yCoord - 5, z);
					mist.setArea(15, 10);
					shell.worldObj.spawnEntityInWorld(mist);
				}
				PollutionHandler.incrementPollution(shell.worldObj, mop.blockX, mop.blockY, mop.blockZ, PollutionType.HEAVYMETAL, 10F);
				PollutionHandler.incrementPollution(shell.worldObj, mop.blockX, mop.blockY, mop.blockZ, PollutionType.POISON, 15F);
			}
		};
		this.itemTypes[MUSTARD] = new ArtilleryShell("ammo_arty_mustard_gas", SpentCasing.COLOR_CASE_16INCH_NUKE, 1) {
			public void onImpact(EntityArtilleryShell shell, MovingObjectPosition mop) {
				shell.killAndClear();
				Vec3 vec = Vec3.createVectorHelper(shell.motionX, shell.motionY, shell.motionZ).normalize();
				shell.worldObj.createExplosion(shell, mop.hitVec.xCoord - vec.xCoord, mop.hitVec.yCoord - vec.yCoord, mop.hitVec.zCoord - vec.zCoord, 5F, false);
				for(int i = 0; i < 5; i++) {
					EntityMist mist = new EntityMist(shell.worldObj);
					mist.setType(Fluids.MUSTARDGAS);
					double x = mop.hitVec.xCoord - vec.xCoord;
					double z = mop.hitVec.zCoord - vec.zCoord;
					if(i > 0) {
						x += rand.nextGaussian() * 25;
						z += rand.nextGaussian() * 25;
					}
					mist.setPosition(x, mop.hitVec.yCoord - vec.yCoord - 5, z);
					mist.setArea(20, 10);
					shell.worldObj.spawnEntityInWorld(mist);
				}
				PollutionHandler.incrementPollution(shell.worldObj, mop.blockX, mop.blockY, mop.blockZ, PollutionType.HEAVYMETAL, 15F);
				PollutionHandler.incrementPollution(shell.worldObj, mop.blockX, mop.blockY, mop.blockZ, PollutionType.POISON, 30F);
			}
		};
		
		/* CLUSTER SHELLS */
		this.itemTypes[PHOSPHORUS_MULTI] = new ArtilleryShell("ammo_arty_phosphorus_multi", SpentCasing.COLOR_CASE_16INCH_PHOS, 4) {
			public void onImpact(EntityArtilleryShell shell, MovingObjectPosition mop) { ItemAmmoArty.this.itemTypes[PHOSPHORUS].onImpact(shell, mop); }
			public void onUpdate(EntityArtilleryShell shell) { standardCluster(shell, PHOSPHORUS, 10, 500, 12); }
		};
		this.itemTypes[MINI_NUKE_MULTI] = new ArtilleryShell("ammo_arty_mini_nuke_multi", SpentCasing.COLOR_CASE_16INCH_NUKE, 4) {
			public void onImpact(EntityArtilleryShell shell, MovingObjectPosition mop) { ItemAmmoArty.this.itemTypes[MINI_NUKE].onImpact(shell, mop); }
			public void onUpdate(EntityArtilleryShell shell) { standardCluster(shell, MINI_NUKE, 5, 500, 12); }
		};
	}
}
