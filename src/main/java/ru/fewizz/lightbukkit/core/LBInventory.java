package ru.fewizz.lightbukkit.core;

import java.util.*;

import org.bukkit.*;
import org.bukkit.entity.HumanEntity;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.inventory.*;

import net.minecraft.inventory.IInventory;

public class LBInventory implements Inventory {
	final IInventory mcInventory;

	public LBInventory(IInventory mci) {
		mcInventory = mci;
	}
	
	@Override
	public int getSize() {
		return mcInventory.getSizeInventory();
	}

	@Override
	public int getMaxStackSize() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void setMaxStackSize(int size) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ItemStack getItem(int index) {
		return LBUtils.lbStack(mcInventory.getStackInSlot(index));
	}

	@Override
	public void setItem(int index, ItemStack item) {
		mcInventory.setInventorySlotContents(index, LBUtils.mcStack(item));
	}

	@Override
	public HashMap<Integer, ItemStack> addItem(ItemStack... items) throws IllegalArgumentException {
		int item = 0;
		HashMap<Integer, ItemStack> map = new HashMap<>();
		
		for(int i = 0; i < getSize() && item < items.length; i++) {
			if(getItem(i) == null) {
				map.put(item, items[item]);
				setItem(i, items[item++]);
			}
		}
		return map;
	}

	@Override
	public HashMap<Integer, ItemStack> removeItem(ItemStack... items) throws IllegalArgumentException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ItemStack[] getContents() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setContents(ItemStack[] items) throws IllegalArgumentException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public ItemStack[] getStorageContents() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setStorageContents(ItemStack[] items) throws IllegalArgumentException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean contains(int materialId) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean contains(Material material) throws IllegalArgumentException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean contains(ItemStack item) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean contains(int materialId, int amount) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean contains(Material material, int amount) throws IllegalArgumentException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean contains(ItemStack item, int amount) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean containsAtLeast(ItemStack item, int amount) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public HashMap<Integer, ? extends ItemStack> all(int materialId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public HashMap<Integer, ? extends ItemStack> all(Material material) throws IllegalArgumentException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public HashMap<Integer, ? extends ItemStack> all(ItemStack item) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int first(int materialId) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int first(Material material) throws IllegalArgumentException {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int first(ItemStack item) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int firstEmpty() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void remove(int materialId) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void remove(Material material) throws IllegalArgumentException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void remove(ItemStack item) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void clear(int index) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void clear() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<HumanEntity> getViewers() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getTitle() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public InventoryType getType() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public InventoryHolder getHolder() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ListIterator<ItemStack> iterator() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ListIterator<ItemStack> iterator(int index) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Location getLocation() {
		// TODO Auto-generated method stub
		return null;
	}

}
