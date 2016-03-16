package com.DrasticDemise.TerrainCrystals.blocks;

import com.DrasticDemise.TerrainCrystals.api.celestialPower.ICelestialPowerProvider;
import com.DrasticDemise.TerrainCrystals.api.celestialPower.ICelestialReceiver;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ITickable;

public class CStorageCellTileEntity extends CelestialTileEntity implements ICelestialPowerProvider, ITickable{
	private int counter = 0;

	@Override
	public boolean isFull() {
		if(this.currentCelestialPower == this.maxCelestialPower){
			return true;
		}
		return false;
	}


	@Override
	public boolean hasEnoughPower() {
		return true;
	}
	
	@Override
    public void readFromNBT(NBTTagCompound compound) {
        super.readFromNBT(compound);
        //Needs the key
        this.currentCelestialPower = compound.getInteger("CP");
    }

    @Override
    public void writeToNBT(NBTTagCompound compound) {
        super.writeToNBT(compound);
        //CP is the key
        compound.setInteger("CP", this.currentCelestialPower);
    }

    @Override
    public void update() {
        // This method is called every tick (20 times per second normally)

        if (!worldObj.isRemote) {
            //Stored server side
            counter++;
            if (counter > 200){//default 400
                counter = 0;      // This is 20 seconds.
                addedCelestialPowerSuccessfully(30);
               // worldObj.markBlockRangeForRenderUpdate(getPos(), getPos());
            }
        }
    }


	@Override
	public void removeCelestialPower(int Celestial) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public boolean canSendPower() {
		// TODO Auto-generated method stub
		return false;
	}
    
}
