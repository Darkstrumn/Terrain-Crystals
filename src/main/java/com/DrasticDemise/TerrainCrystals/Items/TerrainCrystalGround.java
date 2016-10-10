package com.DrasticDemise.TerrainCrystals.Items;

import java.util.ArrayList;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;
import net.minecraft.world.biome.Biome;

public abstract class TerrainCrystalGround extends TerrainCrystalAbstract{
	@Override
	protected ItemStack gatherBlockGenList(ItemStack itemStackIn, World worldIn, EntityPlayer playerIn, int diameter, Biome desiredBiome, Boolean changeBiome){
		if(!worldIn.isRemote){
			int posX = MathHelper.floor_double(playerIn.posX); 
			int posY = MathHelper.floor_double(playerIn.posY);
			int posZ = MathHelper.floor_double(playerIn.posZ);
			int center;
			double radius = diameter/2.0;
			if(diameter%2 != 0){
				center = (int) (radius + 0.5);
			}else{
				center = (int) (radius);
			}
			int offsetXFirstHalf = (int) (posX + radius);
			//Not sure why this has to be offset by 1 extra, but it does.
			int offsetXSecondHalf = (int) (posX - radius + 1);
			ArrayList<BlockPos> posList = new ArrayList<BlockPos>(68);
			for(int i = 0; i < (center); i ++){
				//Creates a circle and fills it
				for(int placeInwards = 0; placeInwards < i+1; placeInwards++){
					//Fills across the circle
					posList.add(new BlockPos(offsetXFirstHalf - i, posY - 1, posZ - i + placeInwards));
					posList.add(new BlockPos(offsetXFirstHalf - i, posY - 1, posZ + i - placeInwards));
				}
			}
			//Generates the second half
			for(int i = 0; i < (center); i ++){
				posList.add(new BlockPos(offsetXSecondHalf + i, posY - 1, posZ  + i)); 
				posList.add(new BlockPos(offsetXSecondHalf + i, posY - 1, posZ - i));
				for(int placeInwards = 0; placeInwards < i + 1; placeInwards++){
						posList.add(new BlockPos(offsetXSecondHalf + i, posY - 1, posZ + i - placeInwards));
						posList.add(new BlockPos(offsetXSecondHalf + i, posY - 1, posZ - i + placeInwards));
				}
			}
			itemStackIn.damageItem(posList.size(), playerIn);
		}
		return itemStackIn;
	}
}
