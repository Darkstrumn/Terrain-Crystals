package com.DrasticDemise.Celestial.Items;

import com.DrasticDemise.Celestial.blocks.CStorageCellTileEntity;
import com.mojang.realmsclient.dto.PlayerInfo;

import net.minecraft.block.Block;
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
	public ItemStack onItemRightClick(ItemStack itemStackIn, World worldIn, EntityPlayer playerIn)
    {
	//	placeDirt(playerIn, worldIn);
		int modifyY = 16; //offset by 1 to place under the player
		int shiftAxisSecondary = 0;
		int shiftAxis = 0;
		for(shiftAxis = 0; shiftAxis < 12; shiftAxis++){
			if(shiftAxis == 11){
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
			shiftAxis++;
		}
		/*
		for(int shiftAxis = 0; shiftAxis < 10; shiftAxis++){
			placeDirtForward(playerIn, worldIn, itemStackIn, EnumFacing.DOWN, shiftAxis, modifyY);
			placeDirtRight(playerIn, worldIn, itemStackIn, EnumFacing.DOWN, shiftAxis, modifyY);
			placeDirtLeft(playerIn, worldIn, itemStackIn, EnumFacing.DOWN, shiftAxis, modifyY);
			placeDirtBackwards(playerIn, worldIn, itemStackIn, EnumFacing.DOWN, shiftAxis, modifyY);
			//Increasing downwards
			for(modifyY = 1; modifyY < 10; modifyY++){
				placeDirtForward(playerIn, worldIn, itemStackIn, EnumFacing.DOWN, shiftAxis - modifyY, modifyY);
				placeDirtRight(playerIn, worldIn, itemStackIn, EnumFacing.DOWN, shiftAxis - modifyY, modifyY);
				placeDirtLeft(playerIn, worldIn, itemStackIn, EnumFacing.DOWN, shiftAxis - modifyY, modifyY);
				placeDirtBackwards(playerIn, worldIn, itemStackIn, EnumFacing.DOWN, shiftAxis - modifyY, modifyY);
			}
		}*/
        return itemStackIn;
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
	
	//Causes the item to place a dirt block.
	/*@Override
	public boolean onItemUse(ItemStack par1ItemStack, EntityPlayer par2EntityPlayer, World par3World, BlockPos pos, EnumFacing side, float par8, float par9, float par10) {
		return placeDirt(par1ItemStack, par2EntityPlayer, par3World, pos, side, par8, par9, par10, Blocks.dirt, 0.35F, 0.2F, 0.05F);
	}
	private boolean placeDirt(ItemStack par1ItemStack, EntityPlayer playerIn, World worldIn, BlockPos pos, EnumFacing side, float par8, float par9, float par10, Block dirt2, float f, float g, float h) {
		int posX = MathHelper.floor_double(playerIn.posX);
		int posY = MathHelper.floor_double(playerIn.posY);;
		int posZ = MathHelper.floor_double(playerIn.posZ);;
		System.out.println("Getting user location XYZ: " + posX +" " + posY + " " + posZ);
		BlockPos blockPosition = new BlockPos(posX, posY, posZ);
		ItemStack stackToPlace = new ItemStack(Blocks.dirt);
		stackToPlace.onItemUse(playerIn, worldIn, pos, side, par8, par9, par10);
		return true;
	}
	*/
}
