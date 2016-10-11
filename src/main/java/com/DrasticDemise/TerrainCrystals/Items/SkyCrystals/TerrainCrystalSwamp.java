package com.DrasticDemise.TerrainCrystals.Items.SkyCrystals;

import com.DrasticDemise.TerrainCrystals.Items.TerrainCrystalAbstract;
import com.DrasticDemise.TerrainCrystals.core.ConfigurationFile;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Biomes;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.biome.Biome;
import net.minecraftforge.fml.common.registry.GameRegistry;

public class TerrainCrystalSwamp extends TerrainCrystalAbstract{
	public TerrainCrystalSwamp(){
		super("Swamp");
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
				if (Blocks.SAPLING.canPlaceBlockAt(worldIn, pos.up()) && Math.random() <= 0.03 && spacedFarEnough(worldIn, pos.up())){
					worldIn.setBlockState(pos.up(), Blocks.SAPLING.getDefaultState());
					bonemealTree(worldIn, pos);
				}else if(Math.random() < 0.10){
					bonemeal(worldIn, pos);
				}
			}catch(Exception e){}
		}
	}

	private void plantSugarcane(World worldIn,BlockPos pos){
			worldIn.setBlockState(pos.up(), Blocks.REEDS.getDefaultState());
			if(Math.random() < 0.9){
				worldIn.setBlockState(pos.up(2), Blocks.REEDS.getDefaultState());
				if(Math.random() < 0.5){
					worldIn.setBlockState(pos.up(3), Blocks.REEDS.getDefaultState());
				}
			}
	}
	private void generateWaterLily(World worldIn, BlockPos pos){
		worldIn.setBlockState(pos.up(), Blocks.WATERLILY.getDefaultState());
		if(Math.random() < 0.50){
			worldIn.setBlockState(pos.north().up(), Blocks.WATERLILY.getDefaultState());
		}else if(Math.random() < 0.75){
			worldIn.setBlockState(pos.north().up().east(), Blocks.WATERLILY.getDefaultState());
		}else if(Math.random() < 1.0){
			worldIn.setBlockState(pos.east().up(), Blocks.WATERLILY.getDefaultState());
		}
	}
	private void generateClay(World worldIn, BlockPos pos){
		worldIn.setBlockState(pos.down(), Blocks.CLAY.getDefaultState());
		worldIn.setBlockState(pos.down(2).east(), Blocks.CLAY.getDefaultState());
		worldIn.setBlockState(pos.down().west(2), Blocks.CLAY.getDefaultState());
		worldIn.setBlockState(pos.north().down(), Blocks.CLAY.getDefaultState());
		worldIn.setBlockState(pos.north().east().down(), Blocks.CLAY.getDefaultState());
		worldIn.setBlockState(pos.east().down(), Blocks.CLAY.getDefaultState());
	}
	
	private void generateWater(World worldIn, BlockPos pos){
		worldIn.setBlockState(pos, Blocks.WATER.getDefaultState());
		worldIn.setBlockState(pos.north(), Blocks.WATER.getDefaultState());
		worldIn.setBlockState(pos.north().east(), Blocks.WATER.getDefaultState());
		worldIn.setBlockState(pos.east(), Blocks.WATER.getDefaultState());
		generateWaterLily(worldIn, pos);
	}
	private void generateSand(World worldIn, BlockPos pos){
		if(Math.random() < 0.25){
			worldIn.setBlockState(pos.north(2).down(), Blocks.DIRT.getDefaultState());
			worldIn.setBlockState(pos.north(2).east().down(), Blocks.DIRT.getDefaultState());
			worldIn.setBlockState(pos.north(2), Blocks.SAND.getDefaultState());
			worldIn.setBlockState(pos.north(2).east(), Blocks.SAND.getDefaultState());
			plantSugarcane(worldIn, pos.north(2));
			plantSugarcane(worldIn, pos.north(2).east());
		}
		if(Math.random() < 0.25){
			worldIn.setBlockState(pos.down().south(), Blocks.DIRT.getDefaultState());
			worldIn.setBlockState(pos.south().east().down(), Blocks.DIRT.getDefaultState());
			worldIn.setBlockState(pos.south(), Blocks.SAND.getDefaultState());
			worldIn.setBlockState(pos.south().east(), Blocks.SAND.getDefaultState());
			plantSugarcane(worldIn, pos.south());
			plantSugarcane(worldIn, pos.south().east());
		}
		if(Math.random() < 0.45){
			worldIn.setBlockState(pos.down().east(2), Blocks.DIRT.getDefaultState());
			worldIn.setBlockState(pos.east(2), Blocks.SAND.getDefaultState());
			worldIn.setBlockState(pos.south().down().east(), Blocks.DIRT.getDefaultState());
			worldIn.setBlockState(pos.south().east(), Blocks.SAND.getDefaultState());
			plantSugarcane(worldIn, pos.east(2));
			plantSugarcane(worldIn,pos.south().east());
		}
	}
	@Override
	protected Boolean changesBiomeOnUse() {
		return ConfigurationFile.swampCrystalChangesBiome;
	}
	@Override
	protected Biome getBiomeType() {
		return Biomes.SWAMPLAND;
	}
	@Override
	protected int getDiameter() {
		return ConfigurationFile.swampCrystalDiameter;
	}
	@Override
	protected int getDurability() {
		return ConfigurationFile.swampCrystalDurability;
	}
}
