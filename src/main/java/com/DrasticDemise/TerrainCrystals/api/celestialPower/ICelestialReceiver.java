package com.DrasticDemise.TerrainCrystals.api.celestialPower;

/**
 * Blocks that implement this are expected to receive power. 
 * Methods exist that allow the block to add or remove power from itself. 
 * @author DrasticDemise
 *
 */
public interface ICelestialReceiver extends ICelestialBlock{
	
	/**
	 * Lets the user know if the tile entity is full of power
	 * @return Returns a boolean determining if it is full
	 */
	public boolean isFull();
	

	/**
	 * Removes celestial power from the block
	 * @param Celestial Takes the amount of power going to be removed from current levels.
	 */
	public void removeCelestialPower(int Celestial);
	
	/**
	 * If a block has enough power to be removed
	 * @return Returns a boolean
	 */
	public boolean hasEnoughPower();
}
