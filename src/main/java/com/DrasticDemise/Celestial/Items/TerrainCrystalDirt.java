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
		System.out.println(offsetXFirstHalf + " "  + offsetXSecondHalf);
		//Generates the first half
		for(int i = 0; i < (center); i ++){
			//Creates the outline of the circle
			//Each shell is respective to its quadrant
			BlockPos shellOne = new BlockPos(offsetXFirstHalf - i, posY-1, posZ - i);
			BlockPos shellTwo = new BlockPos(offsetXFirstHalf - i, posY - 1, posZ + i);
			generateBlock(shellOne, worldIn);
			generateBlock(shellTwo, worldIn);
			for(int placeInwards = 0; placeInwards < i+1; placeInwards++){
				//Fills across the circle
				BlockPos fillShellOne = new BlockPos(offsetXFirstHalf - i, posY - 1, posZ - i + placeInwards);
				BlockPos fillShellTwo = new BlockPos(offsetXFirstHalf - i, posY - 1, posZ + i - placeInwards);
				generateBlock(fillShellOne, worldIn);
				generateBlock(fillShellTwo, worldIn);
			}
		}
		//Generates the second half
		for(int i = 0; i < (center); i ++){
			//BlockPos shellThree = new BlockPos(offsetXFirstHalf - i - center, posY - 1, posZ -i + center - 2);
			//BlockPos shellFour = new BlockPos(offsetXFirstHalf  - i - center, posY - 1, posZ + i - center + 2);
			BlockPos shellThree = new BlockPos(offsetXSecondHalf + i, posY - 1, posZ  + i);
			BlockPos shellFour = new BlockPos(offsetXSecondHalf + i, posY - 1, posZ - i);
			generateBlock(shellThree, worldIn);
			generateBlock(shellFour, worldIn);
			for(int placeInwards = 0; placeInwards < i + 1; placeInwards++){
				BlockPos fillShellThree = new BlockPos(offsetXSecondHalf + i, posY - 1, posZ + i - placeInwards);
				BlockPos fillShellFour = new BlockPos(offsetXSecondHalf + i, posY - 1, posZ - i + placeInwards);
				generateBlock(fillShellThree, worldIn);
				generateBlock(fillShellFour, worldIn);
			}
		}
		return itemStackIn;
	}
	public void generateBlock(BlockPos pos, World worldIn){
		if(worldIn.getBlockState(pos).getBlock() == Blocks.air){
		worldIn.setBlockState(pos, Blocks.dirt.getDefaultState());
		}
	}
}
