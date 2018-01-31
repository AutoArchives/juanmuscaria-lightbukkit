package ru.fewizz.lightbukkit.mixin.bukkit;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.spongepowered.asm.mixin.*;

import net.minecraft.item.Item;
import ru.fewizz.lightbukkit.interfaces.IMCItemStackProvider;

@Mixin(ItemStack.class)
public abstract class MixinItemStack implements IMCItemStackProvider {
	net.minecraft.item.ItemStack mcStack;
	
	@Overwrite
	public int getTypeId() {
		return Item.getIdFromItem(mcStack.getItem());
	}
	
	@Shadow
	abstract public Material getType();
	
	@Overwrite
	public Material getType0() {
		return getType();
	}
	
	@Overwrite
	public int getAmount() {
		return mcStack.getCount();
	}
	
	@Shadow
	abstract short getDurability();
	
	@Override
	public net.minecraft.item.ItemStack getMCItemStack() {
		if(mcStack == null) {
			mcStack = new net.minecraft.item.ItemStack(Item.getItemById(getTypeId()), getAmount(), getDurability());
		}
		return mcStack;
	}
	
	@Override
	public void setMCItemStack(net.minecraft.item.ItemStack mcStack) {
		this.mcStack = mcStack;
	}
	
	
}
