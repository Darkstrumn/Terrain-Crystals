package com.DrasticDemise.Celestial.Items;

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
			//generateBlock(pos, worldIn);
			for(int i = 0; i < 6; i++){
				//TopLayer1.0 - Generates the first row
				BlockPos pos1 = new BlockPos(posX - i, posY-1, posZ);
				worldIn.setBlockState(pos1, Blocks.dirt.getDefaultState());
				BlockPos pos2 = new BlockPos(posX + i, posY-1, posZ);
				worldIn.setBlockState(pos2, Blocks.dirt.getDefaultState());
				
				//Toplayer1.1 -- If facing north, this generates the row behind you.
				BlockPos pos3 = new BlockPos(posX + i -1, posY-1, posZ + 1);
				worldIn.setBlockState(pos3, Blocks.dirt.getDefaultState());
				BlockPos pos4 = new BlockPos(posX - i +1, posY-1, posZ + 1);
				worldIn.setBlockState(pos4, Blocks.dirt.getDefaultState());
				//TopLayer1.2 -- If facing north, this generates the row in front of you.
				BlockPos pos5 = new BlockPos(posX + i -1, posY-1, posZ - 1);
				worldIn.setBlockState(pos5, Blocks.dirt.getDefaultState());
				BlockPos pos6 = new BlockPos(posX - i +1, posY-1, posZ - 1);
				worldIn.setBlockState(pos6, Blocks.dirt.getDefaultState());
				
				//Toplayer1.3 -- If facing north, this generates the row behind you.
				BlockPos pos7 = new BlockPos(posX + i -2, posY-1, posZ + 2);
				worldIn.setBlockState(pos7, Blocks.dirt.getDefaultState());
				BlockPos pos8 = new BlockPos(posX - i + 2, posY-1, posZ + 2);
				worldIn.setBlockState(pos8, Blocks.dirt.getDefaultState());
				
				//TopLayer1.4 -- If facing north, this generates the row in front of you.
				BlockPos pos9 = new BlockPos(posX + i -2, posY-1, posZ - 2);
				worldIn.setBlockState(pos9, Blocks.dirt.getDefaultState());
				BlockPos pos10 = new BlockPos(posX - i +2, posY-1, posZ - 2);
				worldIn.setBlockState(pos10, Blocks.dirt.getDefaultState());
			}
			//System.out.println(" ");
			//worldIn.setBlockState(pos, Blocks.dirt.getDefaultState());
		
		
		
		
		
		
		
		
		
		}
	//	placeDirt(playerIn, worldIn);
		/*int modifyY = 16; //offset by 1 to place under the player
		int shiftAxisSecondary = 0;
		int shiftAxis = 0;
		for(shiftAxis = 0; shiftAxis < 8; shiftAxis++){
			if(shiftAxis == 8){
				System.out.println("Entering customShift");
				placeDirtForward(playerIn, worldIn, itemStackIn, EnumFacing.DOWN, shiftAxis-2, modifyY);
				placeDirtRight(playerIn, worldIn, itemStackIn, EnumFacing.DOWN, shiftAxis-2, modifyY);
				placeDirtLeft(playerIn, worldIn, itemStackIn, EnumFacing.DOWN, shiftAxis-2, modifyY);
				placeDirtBackwards(playerIn, worldIn, itemStackIn, EnumFacing.DOWN, shiftAxis- 2, modifyY);
			}else{
				placeDirtForward(playerIn, worldIn, itemStackIn, EnumFacing.DOWN, shiftAxis, modifyY);
				placeDirtRight(playerIn, worldIn, itemStackIn, EnumFacing.DOWN, shiftAxis, modifyY);
				placeDirtLeft(playerIn, worldIn, itemStackIn, EnumFacing.DOWN, shiftAxis, modifyY);
				placeDirtBackwards(playerIn, worldIn, itemStackIn, EnumFacing.DOWN, shiftAxis, modifyY);
			}
			modifyY--;
			modifyY--;
			modifyY--;

		*/
        return itemStackIn;
    }
	
	public void generateBlock(BlockPos pos, World worldIn){
		worldIn.setBlockState(pos, Blocks.dirt.getDefaultState());
	}
	
	
	//
	private void placeDirtLeft(EntityPlayer playerIn, World worldIn, ItemStack itemStackIn, EnumFacing side,
			int modifyZAxis, int modifyY) {
		int posX = MathHelper.floor_double(playerIn.posX);
		int posY = MathHelper.floor_double(playerIn.posY);
		int posZ = MathHelper.floor_double(playerIn.posZ);
		BlockPos pos = new BlockPos(posX, posY - modifyY, posZ - modifyZAxis);
		ItemStack dirt = new ItemStack(Blocks.dirt);
		dirt.onItemUse(playerIn, worldIn, pos, side, posX, posY, posZ);
	}
	private void placeDirtRight(EntityPlayer playerIn, World worldIn, ItemStack itemStackIn, EnumFacing side,
			int modifyZAxis, int modifyY) {
		int posX = MathHelper.floor_double(playerIn.posX);
		int posY = MathHelper.floor_double(playerIn.posY);
		int posZ = MathHelper.floor_double(playerIn.posZ);
		BlockPos pos = new BlockPos(posX, posY - modifyY, posZ + modifyZAxis);
		ItemStack dirt = new ItemStack(Blocks.dirt);
		dirt.onItemUse(playerIn, worldIn, pos, side, posX, posY, posZ);
	}
	private void placeDirtForward(EntityPlayer playerIn, World worldIn, ItemStack itemStackIn, EnumFacing side, int modifyXAxis, int modifyY){
		int posX = MathHelper.floor_double(playerIn.posX);
		int posY = MathHelper.floor_double(playerIn.posY);
		int posZ = MathHelper.floor_double(playerIn.posZ);
		BlockPos pos = new BlockPos(posX + modifyXAxis, posY - modifyY, posZ);
		ItemStack dirt = new ItemStack(Blocks.dirt);
		dirt.onItemUse(playerIn, worldIn, pos, side, posX, posY, posZ);
	}
	private void placeDirtBackwards(EntityPlayer playerIn, World worldIn, ItemStack itemStackIn, EnumFacing side, int modifyXAxis, int modifyY){
		int posX = MathHelper.floor_double(playerIn.posX);
		int posY = MathHelper.floor_double(playerIn.posY);
		int posZ = MathHelper.floor_double(playerIn.posZ);
		BlockPos pos = new BlockPos(posX - modifyXAxis, posY - modifyY, posZ);
		ItemStack dirt = new ItemStack(Blocks.dirt);
		dirt.onItemUse(playerIn, worldIn, pos, side, posX, posY, posZ);
	}
	
}
