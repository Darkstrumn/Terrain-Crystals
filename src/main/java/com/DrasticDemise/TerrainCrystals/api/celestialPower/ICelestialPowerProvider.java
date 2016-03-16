package com.DrasticDemise.TerrainCrystals.api.celestialPower;
/**
 * Blocks of this type are typically going to provide power to nearby blocks instead of having its own
 * functionality.
 * @author DrasticDemise
 *
 */
public interface ICelestialPowerProvider extends ICelestialReceiver{
	
	/**
	 * If this block can send power to nearby blocks
	 * @return true/false
	 */
	public boolean canSendPower();
	
}
