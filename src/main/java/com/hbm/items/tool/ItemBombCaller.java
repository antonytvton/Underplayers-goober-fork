package com.hbm.items.tool;

import java.util.List;
import java.util.Random;

import com.hbm.entity.logic.EntityBomber;
import com.hbm.entity.projectile.EntityArtilleryShell;
import com.hbm.items.weapon.ItemAmmoArty;
import com.hbm.lib.Library;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import com.hbm.world.WorldUtil;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.World;

public class ItemBombCaller extends Item {

	public ItemBombCaller() {
		super();
		this.setHasSubtypes(true);
	}

	@Override
	public void addInformation(ItemStack stack, EntityPlayer player, List list, boolean bool)
	{
		switch (stack.getItemDamage()) {
			case 0: list.add("Type:  Orbital 155MM HE Barrage");
			list.add("Call in 8 155mm artillery shell");						break;
			case 1: list.add("Type:  Orbital Airbust Strike");
			list.add("Call in 4 airbust shells");								break;
			case 2: list.add("Type:  Orbital 350MM HE Barrage");
			list.add("Call in a 350mm artillery shell");						break;
			case 3: list.add("Type:  Orbital 155MM Precision Barrage");
			list.add("Call in 4 artillery highly accurate shells");				break;
			case 4: list.add("Type:  Orbital 155MM Creeping Barrage");
			list.add("Call in 10 creeping artillery shells");					break;
			case 5: list.add("Type:  Orbital Posion Gas");
			list.add("Call in posion gas artillery shells");					break;
			case 6: list.add("Type:  Orbital Smoke Barrage");
			list.add("Call in orbital smoke artillery shells");					break;
			default: list.add("Type: INVALID, Report it to underplayer since he probably fucked this up");

		}

	}
	
	public void artystrike(World world, double x, double y, double z, int type, int number, int innacuracy, int clumptime) {
		Random rand = new Random();
		for(int i = 0; i < number; i++) {
			EntityArtilleryShell proj = new EntityArtilleryShell(world);
			double x_2 = x+rand.nextInt(2*innacuracy)-innacuracy;
			double z_2 = z+rand.nextInt(2*innacuracy)-innacuracy;

			proj.setPositionAndRotation(x_2, y+3000+rand.nextDouble()*clumptime*20*20, z_2, 0.0F, 0.0F);
			proj.setThrowableHeading(0, -9, 0, (float) 20, 0.0F);
			proj.setTarget((int) x_2, (int) y, (int) z_2);
			proj.setType(type);
			proj.setWhistle(true);
			world.spawnEntityInWorld(proj);
		}
	}
	
	public void creeping(World world, double x, double y, double z, EntityPlayer player) {
		Random rand = new Random();
		double x_dif = x - player.posX;
		double z_dif = z - player.posZ;
		double distance = Math.sqrt((x_dif*x_dif)+(z_dif*z_dif));
		double norm_x = x_dif/distance;
		double norm_z = z_dif/distance;
		System.out.println(norm_x);
		System.out.println(norm_z);

		for(int i = 0; i < 2; i++) {
			for(int l = 0; l < 5; l++) {
				EntityArtilleryShell proj = new EntityArtilleryShell(world);
				double movex = x+(norm_x*l*12-norm_z*(i-0.5)*10);
				double movez = z+(norm_z*l*12+norm_x*(i-0.5)*10);
				proj.setPositionAndRotation(movex, 2400+l*400+rand.nextInt(200),movez, 0.0F, 0.0F);
				proj.setThrowableHeading(0, -9, 0, (float) 20, 0.0F);
				proj.setTarget((int) (movex), (int) y, (int) movez);
				proj.setType(ItemAmmoArty.LASER);
				proj.setWhistle(true);
				world.spawnEntityInWorld(proj);
			}
		}
	}

	@Override
	public ItemStack onItemRightClick(ItemStack stack, World world, EntityPlayer player)
	{
		MovingObjectPosition pos = Library.rayTrace(player, 500, 1);
		int x = pos.blockX;
		int y = pos.blockY;
		int z = pos.blockZ;

		if(!world.isRemote)
		{
			if(stack.getItemDamage() == 0) {
				artystrike(world, x, y, z, ItemAmmoArty.HE, 8, 20, 10);
			}
			if(stack.getItemDamage() == 1) {
				artystrike(world, x, y, z, ItemAmmoArty.FRAG, 3, 1, 3);
			}
			if(stack.getItemDamage() == 2) {
				artystrike(world, x, y, z, ItemAmmoArty.HHE, 1, 1, 1);
			}
			if(stack.getItemDamage() == 3) {
				artystrike(world, x, y, z, ItemAmmoArty.LASER, 4, 8, 3);
			}
			if(stack.getItemDamage() == 4) {
				creeping(world, x, y, z, player);
			}
			if(stack.getItemDamage() == 5) {
				artystrike(world, x, y, z, ItemAmmoArty.PHOSGENE, 3, 20, 3);
			}
			if(stack.getItemDamage() == 6) {
				artystrike(world, x, y, z, ItemAmmoArty.SMOKE, 4, 8, 3);
			}
			
		}

		stack.stackSize -= 1;

		return stack;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void getSubItems(Item p_150895_1_, CreativeTabs p_150895_2_, List p_150895_3_)
	{
		p_150895_3_.add(new ItemStack(p_150895_1_, 1, 0));
		p_150895_3_.add(new ItemStack(p_150895_1_, 1, 1));
		p_150895_3_.add(new ItemStack(p_150895_1_, 1, 2));
		p_150895_3_.add(new ItemStack(p_150895_1_, 1, 3));
		p_150895_3_.add(new ItemStack(p_150895_1_, 1, 4));
		p_150895_3_.add(new ItemStack(p_150895_1_, 1, 5));
		p_150895_3_.add(new ItemStack(p_150895_1_, 1, 6));


	}

	@Override
	@SideOnly(Side.CLIENT)
	public boolean hasEffect(ItemStack p_77636_1_)
	{
		return p_77636_1_.getItemDamage() == 4;
	}
}
