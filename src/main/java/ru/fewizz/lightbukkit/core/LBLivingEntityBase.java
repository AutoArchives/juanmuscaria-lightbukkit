package ru.fewizz.lightbukkit.core;

import java.util.*;

import org.bukkit.*;
import org.bukkit.attribute.*;
import org.bukkit.block.Block;
import org.bukkit.entity.*;
import org.bukkit.inventory.EntityEquipment;
import org.bukkit.potion.*;
import org.bukkit.util.Vector;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.DamageSource;

public abstract class LBLivingEntityBase<E extends net.minecraft.entity.EntityLivingBase> extends LBEntityBase<E> implements LivingEntity {

	public LBLivingEntityBase(E e) {
		super(e);
	}

	@Override
	public AttributeInstance getAttribute(Attribute attribute) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void damage(double amount) {
		mcEntity.attackEntityFrom(DamageSource.GENERIC, (float) amount);
	}

	@Override
	public void damage(double amount, Entity source) {
		net.minecraft.entity.Entity e = ((LBEntityBase<?>)source).getMCEntity();
		
		if(e instanceof EntityLivingBase)
			mcEntity.attackEntityFrom(DamageSource.causeMobDamage((EntityLivingBase)e), (float) amount);
		else
			mcEntity.attackEntityFrom(DamageSource.causeIndirectDamage(e, null), (float) amount);
	}

	@Override
	public double getHealth() {
		return mcEntity.getHealth();
	}

	@Override
	public void setHealth(double health) {
		mcEntity.setHealth((float) health);
	}

	@Override
	public double getMaxHealth() {
		return mcEntity.getMaxHealth();
	}

	@Override
	public void setMaxHealth(double health) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void resetMaxHealth() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public <T extends Projectile> T launchProjectile(Class<? extends T> projectile) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <T extends Projectile> T launchProjectile(Class<? extends T> projectile, Vector velocity) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public double getEyeHeight() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public double getEyeHeight(boolean ignoreSneaking) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public Location getEyeLocation() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Block> getLineOfSight(Set<Material> transparent, int maxDistance) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Block getTargetBlock(Set<Material> transparent, int maxDistance) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Block> getLastTwoTargetBlocks(Set<Material> transparent, int maxDistance) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int getRemainingAir() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void setRemainingAir(int ticks) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public int getMaximumAir() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void setMaximumAir(int ticks) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public int getMaximumNoDamageTicks() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void setMaximumNoDamageTicks(int ticks) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public double getLastDamage() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void setLastDamage(double damage) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public int getNoDamageTicks() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void setNoDamageTicks(int ticks) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Player getKiller() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean addPotionEffect(PotionEffect effect) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean addPotionEffect(PotionEffect effect, boolean force) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean addPotionEffects(Collection<PotionEffect> effects) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean hasPotionEffect(PotionEffectType type) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public PotionEffect getPotionEffect(PotionEffectType type) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void removePotionEffect(PotionEffectType type) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Collection<PotionEffect> getActivePotionEffects() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean hasLineOfSight(Entity other) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean getRemoveWhenFarAway() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void setRemoveWhenFarAway(boolean remove) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public EntityEquipment getEquipment() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setCanPickupItems(boolean pickup) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean getCanPickupItems() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isLeashed() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Entity getLeashHolder() throws IllegalStateException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean setLeashHolder(Entity holder) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isGliding() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void setGliding(boolean gliding) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setAI(boolean ai) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean hasAI() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void setCollidable(boolean collidable) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean isCollidable() {
		return mcEntity.canBeCollidedWith();
	}
}
