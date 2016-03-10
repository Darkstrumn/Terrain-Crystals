package com.DrasticDemise.Celestial.blocks;

import com.DrasticDemise.Celestial.api.celestialPower.ICelestialPowerProvider;
import com.DrasticDemise.Celestial.api.celestialPower.ICelestialReceiver;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ITickable;

public class CStorageCellTileEntity extends CelestialTileEntity implements ICelestialPowerProvider, ITickable{
	int celestialPower = 0;
	int maxCelestialPower = 5000;
	int counter = 0;
	@Override
	public int getCurrentCelestialPower() {
		return this.celestialPower;
	}

	@Override
	public boolean isFull() {
		if(this.celestialPower == this.maxCelestialPower){
			return true;
		}
		return false;
	}

	@Override
	public void addCelestialPower(int Celestial) {
		if(this.celestialPower < this.celestialPower){
			this.celestialPower = this.celestialPower + Celestial;
		}
		markDirty();
	}

	@Override
	public boolean hasEnoughPower() {
		return true;
	}
	
	@Override
    public void readFromNBT(NBTTagCompound compound) {
        super.readFromNBT(compound);
        //Needs the key
        celestialPower = compound.getInteger("CP");
    }

    @Override
    public void writeToNBT(NBTTagCompound compound) {
        super.writeToNBT(compound);
        //CP is the key
        compound.setInteger("CP", celestialPower);
    }

    @Override
    public void update() {
        // This method is called every tick (20 times per second normally)

        if (!worldObj.isRemote) {
            //Stored server side
            counter++;
            if (counter > 400){
                counter = 0;      // This is 20 seconds.
                addCelestialPower(30);
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

	@Override
	public boolean canReceivePower() {
		// TODO Auto-generated method stub
		return false;
	}
    
}
