package com.hbm.entity.grenade;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.world.World;

public class EntityGrenadeDynamite extends EntityGrenadeBouncyBase {

	public EntityGrenadeDynamite(World world) {
		super(world);
	}

	public EntityGrenadeDynamite(World world, EntityLivingBase living) {
		super(world, living);
	}

	public EntityGrenadeDynamite(World world, double x, double y, double z) {
		super(world, x, y, z);
	}
	
	protected float func_70182_d()
    {
        return 0.5F;
    }

	@Override
	public void explode() {
		worldObj.newExplosion(this, posX, posY + 0.25D, posZ, 10F, false, true);
		this.setDead();
	}

	@Override
	protected int getMaxTimer() {
		return 160;
	}

	@Override
	protected double getBounceMod() {
		return 0.3D;
	}
}
