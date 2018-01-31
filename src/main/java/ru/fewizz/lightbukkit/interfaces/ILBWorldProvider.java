package ru.fewizz.lightbukkit.interfaces;

import ru.fewizz.lightbukkit.core.LBWorld;

public interface ILBWorldProvider {
	public LBWorld getLBWorld();
	default void setLBWorld(LBWorld w) {};
}
