package com.DrasticDemise.TerrainCrystals.api.celestialPower;


/**
 * Any block that implements this is considered a celestial tile entity. 
 * Not much to see here, see interfaces that extend this.
 * @author DrasticDemise
 *
 */
public interface ICelestialBlock {
	/**
	 * Gets current celestial power in the block
	 */
	public int getCurrentCelestialPower();
	
	/**
	 * Gets the max Celestial Power a block can contain
	 */
	public int getMaxCelestialPower();
}
