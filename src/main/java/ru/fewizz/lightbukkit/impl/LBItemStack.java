package ru.fewizz.lightbukkit.impl;

import org.bukkit.inventory.ItemStack;

import net.minecraft.item.Item;

public class LBItemStack extends ItemStack {
	net.minecraft.item.ItemStack mcItemStack;
	
	@SuppressWarnings("deprecation")
	public LBItemStack(net.minecraft.item.ItemStack mcStack) {
		super(Item.getIdFromItem(mcStack.getItem()), mcStack.getCount(), (short)mcStack.getItemDamage());
		this.mcItemStack = mcStack;
	}
}
