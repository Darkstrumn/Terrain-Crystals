package com.DrasticDemise.Celestial.blocks;

import com.DrasticDemise.Celestial.api.celestialPower.ICelestialReciever;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;

public class CStorageCellTileEntity extends TileEntity implements ICelestialReciever{
	public static final int MAX_CELESTIAL_POWER = 5000;
	
	private int celestialPower = 5000;
	@Override
    public void readFromNBT(NBTTagCompound compound) {
        super.readFromNBT(compound);
        celestialPower = compound.getInteger("celestialPower");
    }

    @Override
    public void writeToNBT(NBTTagCompound compound) {
        super.writeToNBT(compound);
        compound.setInteger("CP", celestialPower);
    }

	@Override
	public int getCurrentCelestialPower() {
		return celestialPower;
	}

	@Override
	public boolean isFull() {
		if(celestialPower == MAX_CELESTIAL_POWER){
			return true;
		}
		return false;
	}

	@Override
	public void recieveCelestial(int Celestial) {
		celestialPower = celestialPower + Celestial;
		markDirty();
	}

	@Override
	public boolean canExpendPower() {
		return true;
	}
}
