package ru.fewizz.lightbukkit.core;

import java.util.*;

import org.bukkit.*;
import org.bukkit.block.PistonMoveReaction;
import org.bukkit.entity.*;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.player.PlayerTeleportEvent.TeleportCause;
import org.bukkit.metadata.MetadataValue;
import org.bukkit.permissions.*;
import org.bukkit.plugin.Plugin;
import org.bukkit.util.Vector;

import ru.fewizz.lightbukkit.interfaces.*;

public abstract class LBEntityBase<E extends net.minecraft.entity.Entity> implements Entity {
	protected E mcEntity;

	public LBEntityBase(E e) {
		this.mcEntity = e;
	}
	
	public E getMCEntity() {
		return mcEntity;
	}
	
	public void setMCEntity(E e) {
		mcEntity = e;
	}
	
	@Override
	public void setMetadata(String metadataKey, MetadataValue newMetadataValue) {
		LightBukkit.server.entityMetaStore.setMetadata(this, metadataKey, newMetadataValue);
	}

	@Override
	public List<MetadataValue> getMetadata(String metadataKey) {
		return LightBukkit.server.entityMetaStore.getMetadata(this, metadataKey);
	}

	@Override
	public boolean hasMetadata(String metadataKey) {
		return LightBukkit.server.entityMetaStore.hasMetadata(this, metadataKey);
	}

	@Override
	public void removeMetadata(String metadataKey, Plugin owningPlugin) {
		LightBukkit.server.entityMetaStore.removeMetadata(this, metadataKey, owningPlugin);
	}

	@Override
	public String getName() {
		return mcEntity.getName();
	}

	@Override
	public String getCustomName() {
		return mcEntity.getCustomNameTag();
	}

	@Override
	public void setCustomName(String name) {
		mcEntity.setCustomNameTag(name);
	}

	@Override
	public Location getLocation() {
		return new Location(getWorld(), mcEntity.posX, mcEntity.posY, mcEntity.posZ, mcEntity.rotationYaw, mcEntity.rotationPitch);
	}

	@Override
	public Location getLocation(Location loc) {
		loc.setWorld(getWorld());
		loc.setX(mcEntity.posX);
		loc.setY(mcEntity.posY);
		loc.setZ(mcEntity.posZ);
		loc.setYaw(mcEntity.rotationYaw);
		loc.setPitch(mcEntity.rotationPitch);
		return loc;
	}

	@Override
	public void setVelocity(Vector velocity) {
		mcEntity.setVelocity(velocity.getX(), velocity.getY(), velocity.getZ());
	}

	@Override
	public Vector getVelocity() {
		return new Vector(mcEntity.motionX, mcEntity.motionY, mcEntity.motionZ);
	}

	@Override
	public double getHeight() {
		return mcEntity.height;
	}

	@Override
	public double getWidth() {
		return mcEntity.width;
	}

	@Override
	public boolean isOnGround() {
		return mcEntity.onGround;
	}

	@Override
	public World getWorld() {
		return ((ILBWorldProvider)mcEntity.getEntityWorld()).getLBWorld();
	}

	@Override
	public boolean teleport(Location location) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean teleport(Location location, TeleportCause cause) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean teleport(Entity destination) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean teleport(Entity destination, TeleportCause cause) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public List<Entity> getNearbyEntities(double x, double y, double z) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int getEntityId() {
		return mcEntity.getEntityId();
	}

	@Override
	public int getFireTicks() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int getMaxFireTicks() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void setFireTicks(int ticks) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void remove() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean isDead() {
		return mcEntity.isDead;
	}

	@Override
	public boolean isValid() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Server getServer() {
		return LightBukkit.server;
	}

	@Override
	public Entity getPassenger() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean setPassenger(Entity passenger) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public List<Entity> getPassengers() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean addPassenger(Entity passenger) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean removePassenger(Entity passenger) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isEmpty() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean eject() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public float getFallDistance() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void setFallDistance(float distance) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setLastDamageCause(EntityDamageEvent event) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public EntityDamageEvent getLastDamageCause() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public UUID getUniqueId() {
		return mcEntity.getUniqueID();
	}

	@Override
	public int getTicksLived() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void setTicksLived(int value) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void playEffect(EntityEffect type) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean isInsideVehicle() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean leaveVehicle() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Entity getVehicle() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setCustomNameVisible(boolean flag) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean isCustomNameVisible() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void setGlowing(boolean flag) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean isGlowing() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void setInvulnerable(boolean flag) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean isInvulnerable() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isSilent() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void setSilent(boolean flag) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean hasGravity() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void setGravity(boolean gravity) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public int getPortalCooldown() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void setPortalCooldown(int cooldown) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Set<String> getScoreboardTags() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean addScoreboardTag(String tag) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean removeScoreboardTag(String tag) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public PistonMoveReaction getPistonMoveReaction() {
		// TODO Auto-generated method stub
		return null;
	}

}
