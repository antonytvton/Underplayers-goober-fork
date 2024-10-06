package com.hbm.entity.grenade;

import com.hbm.explosion.ExplosionNT;
import com.hbm.explosion.ExplosionNT.ExAttrib;
import com.hbm.items.ModItems;
import com.hbm.items.weapon.ItemGrenade;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.world.World;

public class EntitySatchelCharge extends EntityGrenadeBouncyBase
{
    public EntitySatchelCharge(World p_i1773_1_)
    {
        super(p_i1773_1_);
    }

    public EntitySatchelCharge(World p_i1774_1_, EntityLivingBase p_i1774_2_)
    {
        super(p_i1774_1_, p_i1774_2_);
    }

    public EntitySatchelCharge(World p_i1775_1_, double p_i1775_2_, double p_i1775_4_, double p_i1775_6_)
    {
        super(p_i1775_1_, p_i1775_2_, p_i1775_4_, p_i1775_6_);
    }

    @Override
    public void explode() {
    	
        if (!this.worldObj.isRemote)
        {
            this.setDead();
			this.worldObj.newExplosion((Entity)null, (float)this.posX, (float)this.posY, (float)this.posZ, 3F, false, true);
			ExplosionNT explosion = new ExplosionNT(worldObj, this, posX, posY, posZ, 2F);
			explosion.addAllAttrib(ExAttrib.ERRODE);
			explosion.explode();
        }
    }

	@Override
	protected int getMaxTimer() {
		return ItemGrenade.getFuseTicks(ModItems.satchel_charge);
	}

	@Override
	protected double getBounceMod() {
		return 0.25D;
	}
}
