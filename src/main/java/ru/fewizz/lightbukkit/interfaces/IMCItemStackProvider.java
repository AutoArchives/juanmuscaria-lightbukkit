package ru.fewizz.lightbukkit.interfaces;

public interface IMCItemStackProvider {
	net.minecraft.item.ItemStack getMCItemStack();
	void setMCItemStack(net.minecraft.item.ItemStack mcStack);
}
