package com.DrasticDemise.Celestial.api.celestialPower;

/*
 * Blocks that implement this can receive CP
 */
public interface ICelestialReceiver extends ICelestialBlock{
	/*
	 * Returns if the block is full. If full, can no longer receive CP
	 */
	public boolean isFull();
	
	/*
	 * Modifies the current CP in the block
	 */
	public void recieveCelestial(int Celestial);
	
	/*
	 * If the block can consume its own power
	 */
	public boolean canExpendPower();
}
