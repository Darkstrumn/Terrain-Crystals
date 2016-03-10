package com.DrasticDemise.Celestial.blocks;

import net.minecraft.tileentity.TileEntity;

public abstract class CelestialTileEntity extends TileEntity{
	private int celestialPower;
	private int celestialSendRate;
	private int maxCelestialPower;
	
	public int getMaxCelestialPower(){
		return this.maxCelestialPower;
	}
	/**
	 * Adds a designated amount of celestial power to the cap
	 * @param CelestialPower
	 */
	public void addCelestialPower(int CelestialPower){
		if(canReceivePower()){
			int spareValue = getAvailableSpace();
			if(getAvailableSpace() < CelestialPower){
				this.celestialPower = this.celestialPower + spareValue;
			}
			else{
				this.celestialPower = this.celestialPower + CelestialPower;
			}
			markDirty();
		}
	}
	public void setMaxSendRate(int modifyRate){
		this.celestialSendRate = modifyRate;
	}
	/**
	 * Checks if there is enough room in the block to receive power
	 * @return Returns the status if the block has enough room
	 */
	public boolean canReceivePower(){
		if(this.celestialPower < this.maxCelestialPower){
			return true;
		}
		return false;
	}
	
	/**
	 * Returns the amount of space from the cap
	 */
	public int getAvailableSpace(){
		return this.maxCelestialPower - this.celestialPower;
	}
}
