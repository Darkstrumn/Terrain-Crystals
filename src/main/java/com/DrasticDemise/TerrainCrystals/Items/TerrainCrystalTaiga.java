package com.DrasticDemise.TerrainCrystals.Items;

import java.util.ArrayList;
import java.util.Random;

import com.DrasticDemise.TerrainCrystals.ConfigurationFile;

import net.minecraft.block.IGrowable;
import net.minecraft.block.state.BlockState;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.resources.model.ModelResourceLocation;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.BlockPos;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.chunk.Chunk;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class TerrainCrystalTaiga extends TerrainCrystalAbstract{
	public TerrainCrystalTaiga(){
		setUnlocalizedName("terrainCrystalTaiga");
		setRegistryName("terrainCrystalTaiga");
		setCreativeTab(CreativeTabs.tabBlock);
		setHarvestLevel("stone", 0);
		setMaxStackSize(1);
		setMaxDamage(ConfigurationFile.taigaCrystalDurability);
        GameRegistry.registerItem(this);
	}
	@Override
	public ItemStack onItemRightClick(ItemStack itemStackIn, World worldIn, EntityPlayer playerIn) {
		return super.gatherBlockGenList(itemStackIn, worldIn, playerIn, ConfigurationFile.taigaCrystalDiameter, BiomeGenBase.coldTaiga, ConfigurationFile.taigaCrystalChangesBiome);
	}
	@Override
	protected int generateInWorld(BlockPos pos, World worldIn, EntityPlayer playerIn, int blocksGenerated,
			BiomeGenBase desiredBiome, boolean changeBiome){
		if(worldIn.getBlockState(pos) == Blocks.air.getDefaultState()){
			int posY = MathHelper.floor_double(playerIn.posY);
			if(posY - pos.getY() == 1){
				if(Math.random() < .4){
					if(Math.random() < .5){
						worldIn.setBlockState(pos, Blocks.dirt.getStateFromMeta(1));
					}else{
						worldIn.setBlockState(pos, Blocks.grass.getDefaultState());
					}
				}else{
					worldIn.setBlockState(pos, Blocks.dirt.getStateFromMeta(2));
				}
				if(ConfigurationFile.taigaCrystalGeneratesTrees){
					decoratePlatform(worldIn, pos);
				}
				if(ConfigurationFile.taigaCrystalChangesBiome){
					setBiome(worldIn, pos, desiredBiome, changeBiome);
				}
				
				blocksGenerated++;
			}else{
				worldIn.setBlockState(pos, Blocks.dirt.getDefaultState());
				blocksGenerated++;
			}
		}
		return blocksGenerated;
	}
	protected void decoratePlatform(World worldIn, BlockPos pos){
		if(ConfigurationFile.taigaCrystalGeneratesTrees){
			IBlockState state  = worldIn.getBlockState(pos.up());
			if (Blocks.sapling.canPlaceBlockAt(worldIn, pos.up())){
				if(Math.random() < .05){
					worldIn.setBlockState(pos.up(), Blocks.sapling.getStateFromMeta(1));
				}
				if(state != Blocks.air.getDefaultState() && state != Blocks.dirt.getStateFromMeta(1)){
					IGrowable growable = (IGrowable) worldIn.getBlockState(pos.up()).getBlock();
					Random rand = new Random();	
					while(worldIn.getBlockState(pos.up()) != Blocks.log.getDefaultState()){
						growable.grow(worldIn, rand, pos.up(), worldIn.getBlockState(pos.up()));
					}
				}
			}
		}
	}
}

