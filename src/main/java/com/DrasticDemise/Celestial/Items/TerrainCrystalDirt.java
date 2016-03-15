package com.DrasticDemise.Celestial.Items;

import java.util.ArrayList;

import com.DrasticDemise.Celestial.blocks.CStorageCellTileEntity;
import com.mojang.realmsclient.dto.PlayerInfo;

import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;
import net.minecraft.world.storage.WorldInfo;
import net.minecraftforge.fml.common.registry.GameRegistry;

public class TerrainCrystalDirt extends Item{
	public TerrainCrystalDirt(){
		setUnlocalizedName("terrainCrystalDirt");
		setRegistryName("terrainCrystalDirt");
		setCreativeTab(CreativeTabs.tabBlock);
		setHarvestLevel("stone", 0);
        GameRegistry.registerItem(this);
	}
	@Override
	public ItemStack onItemRightClick(ItemStack itemStackIn, World worldIn, EntityPlayer playerIn){
		int posX = MathHelper.floor_double(playerIn.posX);
		int posY = MathHelper.floor_double(playerIn.posY);
		int posZ = MathHelper.floor_double(playerIn.posZ);
		int center;
		int diameter = 11;
		double radius = diameter/2.0;
		if(diameter%2 != 0){
			center = (int) (radius + 0.5);
		}else{
			center = (int) (radius);
		}
		int offsetXFirstHalf = (int) (posX + radius);
		//Not sure why this has to be offset by 1 extra, but it does.
		int offsetXSecondHalf = (int) (posX - radius + 1);
		//Generates the first half
		int yDown = 1;
		int fakeCenter = center;
		ArrayList<BlockPos> posList = new ArrayList<BlockPos>(68);
		for(int i = 0; i < (fakeCenter); i ++){
			//Creates the outline of the circle
			//Each shell is respective to its quadrant
			//These are added in the loop already
			//BlockPos shellOne = new BlockPos(offsetXFirstHalf - i, posY-yDown, posZ - i);
			//BlockPos shellTwo = new BlockPos(offsetXFirstHalf - i, posY - yDown, posZ + i);
			for(int placeInwards = 0; placeInwards < i+1; placeInwards++){
				//Fills across the circle
				BlockPos fillShellOne = new BlockPos(offsetXFirstHalf - i, posY - yDown, posZ - i + placeInwards);
				posList.add(fillShellOne);
				BlockPos fillShellTwo = new BlockPos(offsetXFirstHalf - i, posY - yDown, posZ + i - placeInwards);
				posList.add(fillShellTwo);
			}
		}
		//Generates the second half
		for(int i = 0; i < (center); i ++){
			BlockPos shellThree = new BlockPos(offsetXSecondHalf + i, posY - 1, posZ  + i);
			BlockPos shellFour = new BlockPos(offsetXSecondHalf + i, posY - 1, posZ - i);
			posList.add(shellThree); 
			posList.add(shellFour);
			
			for(int placeInwards = 0; placeInwards < i + 1; placeInwards++){
				BlockPos fillShellThree = new BlockPos(offsetXSecondHalf + i, posY - 1, posZ + i - placeInwards);
				BlockPos fillShellFour = new BlockPos(offsetXSecondHalf + i, posY - 1, posZ - i + placeInwards);
				posList.add(fillShellThree);
				posList.add(fillShellFour);
			}
		}
		for(BlockPos p : posList){
			generateSpike(posList, worldIn, playerIn);
		}
		return itemStackIn;
	}
	public void generateSpike(ArrayList<BlockPos> posList, World worldIn, EntityPlayer playerIn){
		ArrayList<BlockPos> recursiveList = new ArrayList<BlockPos>();
		int blocksSpawned = 0;
		for(BlockPos pos : posList){
			int surroundingBlocks = 0;
			
				generateInWorld(pos, worldIn, playerIn);
				
				if(worldIn.getBlockState(pos.north()) != Blocks.air.getDefaultState()){
					//System.out.println("entered northCheck");
					surroundingBlocks++;
				}
				
				if(worldIn.getBlockState(pos.east()) != Blocks.air.getDefaultState()){
					surroundingBlocks++;
				}
				
				if(worldIn.getBlockState(pos.south()) != Blocks.air.getDefaultState()){
					surroundingBlocks++;
				}
				
				if(worldIn.getBlockState(pos.west()) != Blocks.air.getDefaultState()){
					surroundingBlocks++;
				}
				System.out.println(surroundingBlocks);
				if(surroundingBlocks >= 3 || Math.random() < 0.05){
					generateInWorld(pos.down(), worldIn, playerIn);
					recursiveList.add(pos.down());
				}
			}
		if(!recursiveList.isEmpty()){
			generateSpike(recursiveList, worldIn, playerIn);
		}
	}
	private void generateInWorld(BlockPos pos, World worldIn, EntityPlayer playerIn){
		if(worldIn.getBlockState(pos) == Blocks.air.getDefaultState()){
			int posY = MathHelper.floor_double(playerIn.posY);
			if(posY - pos.getY() == 1){
				worldIn.setBlockState(pos, Blocks.grass.getDefaultState());
			}else{
				worldIn.setBlockState(pos, Blocks.dirt.getDefaultState());
			}
		}
	}
	private void boneMeal(World worldIn){
		
	}
}
