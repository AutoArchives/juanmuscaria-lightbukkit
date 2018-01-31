package ru.fewizz.lightbukkit.core;

import org.bukkit.entity.HumanEntity;
import org.bukkit.inventory.*;

import net.minecraft.entity.player.InventoryPlayer;
import ru.fewizz.lightbukkit.interfaces.IPlayer;

public class LBPlayerInventory extends LBInventory implements PlayerInventory {
	LBPlayer player;

	public LBPlayerInventory(InventoryPlayer mci) {
		super(mci);
		player = ((IPlayer)mci.player).getLBPlayer();
	}

	@Override
	public ItemStack[] getArmorContents() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ItemStack[] getExtraContents() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ItemStack getHelmet() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ItemStack getChestplate() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ItemStack getLeggings() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ItemStack getBoots() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setArmorContents(ItemStack[] items) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setExtraContents(ItemStack[] items) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setHelmet(ItemStack helmet) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setChestplate(ItemStack chestplate) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setLeggings(ItemStack leggings) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setBoots(ItemStack boots) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public ItemStack getItemInMainHand() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setItemInMainHand(ItemStack item) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public ItemStack getItemInOffHand() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setItemInOffHand(ItemStack item) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public ItemStack getItemInHand() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setItemInHand(ItemStack stack) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public int getHeldItemSlot() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void setHeldItemSlot(int slot) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public int clear(int id, int data) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public HumanEntity getHolder() {
		return player;
	}

}
