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
	
	//Rewrite using blockstates
	@Override
	public ItemStack onItemRightClick(ItemStack itemStackIn, World worldIn, EntityPlayer playerIn)
    {
		if(!worldIn.isRemote){
			int posX = MathHelper.floor_double(playerIn.posX);
			int posY = MathHelper.floor_double(playerIn.posY);
			int posZ = MathHelper.floor_double(playerIn.posZ);
			//BlockPos pos = new BlockPos(posX, posY-1, posZ);
			int xShiftUp = 0;
			int yShiftDown = -1;
			int zShiftUp = 0;
			int yShift = 1; //Y must be shifted one extra or spawns on player
			int xOffset = 1;
			int zShift = 1;
			//generateBlock(pos, worldIn);
			int center;
			double diameter = 11;
			double radius = diameter/2.0;
			if(diameter%2 != 0){
				center = (int) (radius - 0.5);
			}else{
				center = (int) radius;
			}
			int modifiedposX = (int) (posX - center);
			int modifiedposZ = (int) (posZ - center) ;
			int modifiedposY = posY - 1;
			ArrayList<BlockPos> posList = new ArrayList<BlockPos>(50);
			for(int layer = 0; layer < diameter; layer++){
				for(int shrinkCircle = 0; shrinkCircle < diameter; shrinkCircle++){
					
					int point1CurrentBlock = 1; int point1posX = modifiedposX; int point1posZ = posZ;
					int point2posX = modifiedposX; int point2posZ = posZ; int point2CurrentBlock = 1;
					//Once the block position changes, this searches to complete the circle.
					for(int expandLines = 0; expandLines < center; expandLines++){
						//Generates and adds point 1
						//sideX1
						//This side needs to increase on the X and decrement Z
						posList.add(new BlockPos(point1posX, modifiedposY, point1posZ));
						
						for(int findMoreLocationsBeneathBlock = 1; findMoreLocationsBeneathBlock < point1CurrentBlock; findMoreLocationsBeneathBlock++){
						    posList.add(new BlockPos (point1posX , modifiedposY, point1posZ - findMoreLocationsBeneathBlock));
						}
						//Generates point 2 which is one diameter from point 1.
						//sideX2
						//DECREASE ON THE X
						posList.add(new BlockPos(point2posX + center*2, modifiedposY, point2posZ));
						for(int findMoreLocationsBeneathBlock = 1; findMoreLocationsBeneathBlock < point2CurrentBlock; findMoreLocationsBeneathBlock++){
							posList.add(new BlockPos (point2posX + center*2 , modifiedposY, point2posZ - findMoreLocationsBeneathBlock));
						}
						//Increment Z to close the circle
						BlockPos centerPoint = new BlockPos(posX, modifiedposY, modifiedposZ);
						for(int findMoreLocationsBeneathBlock = 0; findMoreLocationsBeneathBlock < diameter; findMoreLocationsBeneathBlock++){
							posList.add(new BlockPos(posX, modifiedposY, modifiedposZ + findMoreLocationsBeneathBlock));
						}
						posList.add(centerPoint);
						point1posX++; point1posZ++;
						point2posX--; point2posZ++;
						point1CurrentBlock = point1CurrentBlock + 2;
						point2CurrentBlock = point2CurrentBlock + 2;
					}
				}
				//modifiedposY--;
			}
			for(BlockPos p : posList){
				generateBlock(p, worldIn);
			}
		}
        return itemStackIn;
    }
	
	public void generateBlock(BlockPos pos, World worldIn){
		worldIn.setBlockState(pos, Blocks.dirt.getDefaultState());
	}
}
