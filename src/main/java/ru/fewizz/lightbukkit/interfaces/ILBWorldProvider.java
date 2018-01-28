package ru.fewizz.lightbukkit.interfaces;

import ru.fewizz.lightbukkit.impl.LBWorld;

public interface ILBWorldProvider {
	public LBWorld getLBWorld();
	default void setLBWorld(LBWorld w) {};
}
