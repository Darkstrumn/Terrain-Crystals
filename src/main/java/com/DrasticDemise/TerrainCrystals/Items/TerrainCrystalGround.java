package com.DrasticDemise.TerrainCrystals.Items;

import java.util.ArrayList;

import com.DrasticDemise.TerrainCrystals.core.ConfigurationFile;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;
import net.minecraft.world.biome.Biome;

public abstract class TerrainCrystalGround extends TerrainCrystalAbstract{
	public TerrainCrystalGround(String name) {
		super(name);
	}
	@Override
	public ActionResult<ItemStack> onItemRightClick(ItemStack itemStackIn, World worldIn, EntityPlayer playerIn, EnumHand hand){
	    gatherBlockGenList(itemStackIn, worldIn, playerIn, getDiameter(), getBiomeType(), changesBiomeOnUse());
		return new ActionResult(EnumActionResult.PASS, itemStackIn);
	}
	
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
			generateBlocksInWorld(playerIn.playerLocation.down(), worldIn, playerIn, 0, desiredBiome, changeBiome);
			itemStackIn.damageItem(posList.size(), playerIn);
		}
		return itemStackIn;
	}
	@Override
	protected int generateBlocksInWorld(BlockPos pos, World worldIn, EntityPlayer playerIn, int blocksGenerated, Biome desiredBiome, boolean changeBiome){
		if(eligibleStateLocation(worldIn, pos)){
			int posY = MathHelper.floor_double(playerIn.posY);
			if(posY - pos.getY() == 1){
				setBiome(worldIn, pos, desiredBiome, changeBiome);
				worldIn.setBlockState(pos, Blocks.GRASS.getDefaultState());
				decoratePlatform(worldIn, pos);
			}else if(ConfigurationFile.generateStone && posY - pos.getY() >= ConfigurationFile.stoneSpawnDepth){
				if(ConfigurationFile.generateOres && Math.random() < 0.05){
					worldIn.setBlockState(pos, oreListHelper());
				}else{
					worldIn.setBlockState(pos, Blocks.STONE.getDefaultState());
				}
			}
			else{
				worldIn.setBlockState(pos, Blocks.DIRT.getDefaultState());
			}
			blocksGenerated += 1;
		}
		return blocksGenerated;
	}
}
