package com.DrasticDemise.Celestial.blocks;

import com.DrasticDemise.Celestial.api.celestialPower.ICelestialReciever;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ITickable;

public class CStorageCellTileEntity extends TileEntity implements ICelestialReciever, ITickable{
	public static final int MAX_CELESTIAL_POWER = 5000;
	
	private int celestialPower = 0;
	private int counter = 0;
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
		if(celestialPower < MAX_CELESTIAL_POWER){
			celestialPower = celestialPower + Celestial;
		}
		markDirty();
	}

	@Override
	public boolean canExpendPower() {
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
            // We only do something on the client so we don't have to bother about client-server communication.
            // The effect that we want to have (blinking the block) is client-side as well.
            counter++;
            if (counter > 400){
                counter = 0;      // This is 20 seconds. Rate increases if more mods are near
                recieveCelestial(30);
               // worldObj.markBlockRangeForRenderUpdate(getPos(), getPos());
            }
        }
    }
    
}
