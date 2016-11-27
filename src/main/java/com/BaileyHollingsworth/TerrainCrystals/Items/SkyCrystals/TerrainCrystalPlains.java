package com.BaileyHollingsworth.TerrainCrystals.Items.SkyCrystals;

import com.BaileyHollingsworth.TerrainCrystals.Items.TerrainCrystalAbstract;
import com.BaileyHollingsworth.TerrainCrystals.core.ConfigurationFile;
import net.minecraft.block.IGrowable;
import net.minecraft.init.Biomes;
import net.minecraft.init.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.biome.Biome;

import java.util.Random;

public class TerrainCrystalPlains extends TerrainCrystalAbstract{
	
	public TerrainCrystalPlains(){
		super("Plains");
	}
	
	public TerrainCrystalPlains(boolean isGroundCrystal){
		super("Plains", isGroundCrystal);
	}
	
	//Code taken from Lumien's Random Things Nature Core tile entity
	@Override
	protected void decoratePlatform(World worldIn, BlockPos pos){
		try{
			if(Math.random() < 0.06 && ConfigurationFile.plainsCrystalGenerateTallGrass){
				bonemeal(worldIn, pos);
			}
			if(Math.random() <= 0.01){
				growTree(worldIn, pos);
			}
		}catch(Exception ignored){}
	}
	
	private void growTree(World worldIn, BlockPos pos){
		if(ConfigurationFile.plainsCrystalGenerateTrees){
			if (spacedFarEnough(worldIn, pos.up())){
				if(Math.random() < .5){
					worldIn.setBlockState(pos.up(), Blocks.SAPLING.getStateFromMeta(2));
				}else{
					worldIn.setBlockState(pos.up(), Blocks.SAPLING.getDefaultState());
				}
				IGrowable growable = (IGrowable) worldIn.getBlockState(pos.up()).getBlock();
				Random rand = new Random();	
				int attemptCap = 0;
				while((worldIn.getBlockState(pos.up()) != Blocks.LOG.getDefaultState()) && attemptCap < 10){
					growable.grow(worldIn, rand, pos.up(), worldIn.getBlockState(pos.up()));
					attemptCap++;
				}
			}
		}
	}
	
	@Override
	protected Boolean changesBiomeOnUse() {
		return ConfigurationFile.plainsCrystalChangesBiome;
	}
	
	@Override
	protected Biome getBiomeType() {
		return Biomes.PLAINS;
	}
	
	@Override
	protected int getDiameter() {
		return ConfigurationFile.plainsCrystalDiameter;
	}
	
	@Override
	protected int getDurability() {
		return ConfigurationFile.plainsCrystalDurability;
	}
}
