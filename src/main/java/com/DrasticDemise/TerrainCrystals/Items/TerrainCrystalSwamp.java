package com.DrasticDemise.TerrainCrystals.Items;

import java.util.ArrayList;
import java.util.Random;

import com.DrasticDemise.TerrainCrystals.ConfigurationFile;

import net.minecraft.block.IGrowable;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Biomes;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraftforge.fml.common.registry.GameRegistry;

public class TerrainCrystalSwamp extends TerrainCrystalAbstract{
	public TerrainCrystalSwamp(){
		setUnlocalizedName("terrainCrystalSwamp");
		setRegistryName("terrainCrystalSwamp");
		setCreativeTab(CreativeTabs.tabBlock);
		setHarvestLevel("stone", 0);
		setMaxStackSize(1);
		setMaxDamage(ConfigurationFile.swampCrystalDurability);
        GameRegistry.register(this);
	}

	@Override
	public ActionResult<ItemStack> onItemRightClick(ItemStack itemStackIn, World worldIn, EntityPlayer playerIn, EnumHand hand){
		super.gatherBlockGenList(itemStackIn, worldIn, playerIn, ConfigurationFile.swampCrystalDiameter, Biomes.swampland, ConfigurationFile.swampCrystalChangesBiome);
		return new ActionResult(EnumActionResult.PASS, itemStackIn);
	}

	@Override
	protected void decoratePlatform(World worldIn, BlockPos pos) {
		if(Math.random() <= 0.02 && ConfigurationFile.swampCrystalGensWater){
			if(Math.random() < 0.75 && pos != null){
				generateWater(worldIn, pos);
				if(Math.random() < 0.8){
					generateSand(worldIn, pos);
				}
				if(Math.random() < .10 && ConfigurationFile.swampCrystalGensClay){
					generateClay(worldIn, pos);
				}
			}
		}else{
			try{
				if (Blocks.sapling.canPlaceBlockAt(worldIn, pos.up()) && Math.random() <= 0.03 && spacedFarEnough(worldIn, pos.up())){
					worldIn.setBlockState(pos.up(), Blocks.sapling.getDefaultState());
					IGrowable growable = (IGrowable) worldIn.getBlockState(pos.up()).getBlock();
					Random rand = new Random();	
					int attemptCap = 0;
					while((worldIn.getBlockState(pos.up()) != Blocks.log.getDefaultState()) && attemptCap < 8){
						growable.grow(worldIn, rand, pos.up(), worldIn.getBlockState(pos.up()));
						attemptCap++;
					}
				}else if(Math.random() < 0.10){
					bonemeal(worldIn, pos);
				}
			}catch(Exception e){}
		}
	}

	private void plantSugarcane(World worldIn,BlockPos pos){
			worldIn.setBlockState(pos.up(), Blocks.reeds.getDefaultState());
			if(Math.random() < 0.9){
				worldIn.setBlockState(pos.up(2), Blocks.reeds.getDefaultState());
				if(Math.random() < 0.5){
					worldIn.setBlockState(pos.up(3), Blocks.reeds.getDefaultState());
				}
			}
	}
	private void generateWaterLily(World worldIn, BlockPos pos){
		worldIn.setBlockState(pos.up(), Blocks.waterlily.getDefaultState());
		if(Math.random() < 0.50){
			worldIn.setBlockState(pos.north().up(), Blocks.waterlily.getDefaultState());
		}else if(Math.random() < 0.75){
			worldIn.setBlockState(pos.north().up().east(), Blocks.waterlily.getDefaultState());
		}else if(Math.random() < 1.0){
			worldIn.setBlockState(pos.east().up(), Blocks.waterlily.getDefaultState());
		}
	}
	private void generateClay(World worldIn, BlockPos pos){
		worldIn.setBlockState(pos.down(), Blocks.clay.getDefaultState());
		worldIn.setBlockState(pos.down(2).east(), Blocks.clay.getDefaultState());
		worldIn.setBlockState(pos.down().west(2), Blocks.clay.getDefaultState());
		worldIn.setBlockState(pos.north().down(), Blocks.clay.getDefaultState());
		worldIn.setBlockState(pos.north().east().down(), Blocks.clay.getDefaultState());
		worldIn.setBlockState(pos.east().down(), Blocks.clay.getDefaultState());
	}
	
	private void generateWater(World worldIn, BlockPos pos){
		worldIn.setBlockState(pos, Blocks.water.getDefaultState());
		worldIn.setBlockState(pos.north(), Blocks.water.getDefaultState());
		worldIn.setBlockState(pos.north().east(), Blocks.water.getDefaultState());
		worldIn.setBlockState(pos.east(), Blocks.water.getDefaultState());
		generateWaterLily(worldIn, pos);
	}
	private void generateSand(World worldIn, BlockPos pos){
		if(Math.random() < 0.25){
			worldIn.setBlockState(pos.north(2).down(), Blocks.dirt.getDefaultState());
			worldIn.setBlockState(pos.north(2).east().down(), Blocks.dirt.getDefaultState());
			worldIn.setBlockState(pos.north(2), Blocks.sand.getDefaultState());
			worldIn.setBlockState(pos.north(2).east(), Blocks.sand.getDefaultState());
			plantSugarcane(worldIn, pos.north(2));
			plantSugarcane(worldIn, pos.north(2).east());
		}
		if(Math.random() < 0.25){
			worldIn.setBlockState(pos.down().south(), Blocks.dirt.getDefaultState());
			worldIn.setBlockState(pos.south().east().down(), Blocks.dirt.getDefaultState());
			worldIn.setBlockState(pos.south(), Blocks.sand.getDefaultState());
			worldIn.setBlockState(pos.south().east(), Blocks.sand.getDefaultState());
			plantSugarcane(worldIn, pos.south());
			plantSugarcane(worldIn, pos.south().east());
		}
		if(Math.random() < 0.45){
			worldIn.setBlockState(pos.down().east(2), Blocks.dirt.getDefaultState());
			worldIn.setBlockState(pos.east(2), Blocks.sand.getDefaultState());
			worldIn.setBlockState(pos.south().down().east(), Blocks.dirt.getDefaultState());
			worldIn.setBlockState(pos.south().east(), Blocks.sand.getDefaultState());
			plantSugarcane(worldIn, pos.east(2));
			plantSugarcane(worldIn,pos.south().east());
		}
	}
}
