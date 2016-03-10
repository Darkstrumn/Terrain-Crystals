package com.DrasticDemise.Celestial.blocks;

import net.minecraft.tileentity.TileEntity;

public abstract class CelestialTileEntity extends TileEntity{
	protected int currentCelestialPower = 0;
	protected int celestialSendRate = 50;
	protected int maxCelestialPower = 5000;
	
	public int getCurrentCelestialPower() {
		return this.currentCelestialPower;
	}
	
	public int getMaxCelestialPower(){
		return this.maxCelestialPower;
	}
	/**
	 * Adds a designated amount of celestial power to the cap
	 * @param CelestialPower
	 */
	public boolean addedCelestialPowerSuccessfully(int CelestialPower){
		if(this.canReceivePower()){
			int spareValue = getAvailableSpace();
			if(getAvailableSpace() > CelestialPower){
				this.currentCelestialPower = this.currentCelestialPower + CelestialPower;
				markDirty();
				return true;
			}
			else{
				this.currentCelestialPower = this.currentCelestialPower + spareValue;
				markDirty();
				return true;
			}
		}
		return false;
	}
	
	public boolean removedCelestialPowerSuccessfully(int CelestialPower){
		if(CelestialPower <= this.currentCelestialPower){
			this.currentCelestialPower = this.currentCelestialPower - CelestialPower;
			markDirty();
			return true;
		}
		return false;
	}
	public void setMaxSendRate(int modifyRate){
		this.celestialSendRate = modifyRate;
		markDirty();
	}
	/**
	 * Checks if there is enough room in the block to receive power
	 * @return Returns the status if the block has enough room
	 */
	public boolean canReceivePower(){
		System.out.println("Entered RECEIVEPOWER");
		if(currentCelestialPower < maxCelestialPower){
			return true;
		}
		return false;
	}
	
	/**
	 * Returns the amount of space from the cap
	 */
	public int getAvailableSpace(){
		return this.maxCelestialPower - this.currentCelestialPower;
	}
}
