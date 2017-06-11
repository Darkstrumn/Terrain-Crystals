package com.BaileyHollingsworth.TerrainCrystals.Items.SkyCrystals;

import com.BaileyHollingsworth.TerrainCrystals.Items.TerrainCrystalAbstract;
import com.BaileyHollingsworth.TerrainCrystals.core.ConfigurationFile;
import net.minecraft.init.Biomes;
import net.minecraft.init.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.biome.Biome;

public class TerrainCrystalJungle extends TerrainCrystalAbstract{
	
	public TerrainCrystalJungle(){
		super("Jungle");
	}
	
	public TerrainCrystalJungle(boolean isGroundCrystal){
		super("Jungle", isGroundCrystal);
	}
	
	@Override
	protected void decoratePlatform(World worldIn, BlockPos pos) {
		if(!worldIn.isRemote) {
			//Generate bush things
			if (Math.random() < 0.01 && ConfigurationFile.jungleCrystalGeneratesBushes && spacedFarEnough(worldIn, pos.up())) {
				worldIn.setBlockState(pos.up(), Blocks.LEAVES.getDefaultState());
				worldIn.setBlockState(pos.up(2), Blocks.LEAVES.getDefaultState());
				worldIn.setBlockState(pos.up().east(), Blocks.LEAVES.getDefaultState());
				worldIn.setBlockState(pos.up().north(), Blocks.LEAVES.getDefaultState());
				worldIn.setBlockState(pos.up().south(), Blocks.LEAVES.getDefaultState());
				worldIn.setBlockState(pos.up().west(), Blocks.LEAVES.getDefaultState());
				if (Math.random() < .3) {
					worldIn.setBlockState(pos.up().north().east(), Blocks.LEAVES.getDefaultState());
					worldIn.setBlockState(pos.up().north().west(), Blocks.LEAVES.getDefaultState());
					worldIn.setBlockState(pos.up().south().east(), Blocks.LEAVES.getDefaultState());
					worldIn.setBlockState(pos.up().south().west(), Blocks.LEAVES.getDefaultState());
				}
				worldIn.setBlockState(pos, Blocks.LOG.getStateFromMeta(3));
			}
			//Decorate with tall grass
			if (Math.random() < 0.08) {
				bonemeal(worldIn, pos);

				if (Math.random() < 0.05 && ConfigurationFile.jungleCrystalGeneratesMelon) {
					if (worldIn.getBlockState(pos.up()) == Blocks.AIR.getDefaultState())
						worldIn.setBlockState(pos.up(), Blocks.MELON_BLOCK.getDefaultState());
				}
			}

			if (Math.random() < 0.07 && ConfigurationFile.jungleCrystalGeneratesTrees && spacedFarEnough(worldIn, pos.up())) {
				growTree(worldIn, pos);
			}
		}
	}
	
	private void growTree(World worldIn, BlockPos pos) {
			if (Blocks.SAPLING.canPlaceBlockAt(worldIn, pos.up())){
				worldIn.setBlockState(pos.up(), Blocks.SAPLING.getStateFromMeta(3));
				bonemealTree(worldIn, pos);
				generateCocoaBeans(worldIn, pos.up());
			}
	}
	
	private boolean safelyExpandPlatform(World worldIn, BlockPos pos){
		if(worldIn.getBlockState(pos) == Blocks.AIR.getDefaultState() || worldIn.getBlockState(pos) == Blocks.GRASS.getDefaultState()
				|| worldIn.getBlockState(pos) == Blocks.DIRT.getDefaultState()){
			worldIn.setBlockState(pos, Blocks.GRASS.getDefaultState());
			return true;
		}
		return false;
	}
	
	private void generateCocoaBeans(World worldIn, BlockPos pos){
		int counter = 2;
		while((worldIn.getBlockState(pos.up(counter)) == Blocks.LOG.getStateFromMeta(3)) && counter < 16 && ConfigurationFile.jungleCrystalGeneratesCocoa){
			if(worldIn.getBlockState(pos.up(counter).north()) != Blocks.LEAVES.getStateFromMeta(3)){
				if(Math.random() < .05){
					if(Math.random() < 0.25){
						if(worldIn.getBlockState(pos.up().north()) == Blocks.AIR.getDefaultState())
							worldIn.setBlockState(pos.up().north(), Blocks.COCOA.getStateFromMeta(4));
					}else if(Math.random() < 0.50){
						if(worldIn.getBlockState(pos.up().south()) == Blocks.AIR.getDefaultState())
							worldIn.setBlockState(pos.up().south(), Blocks.COCOA.getStateFromMeta(2));
					}else if(Math.random() < 0.75){
						if(worldIn.getBlockState(pos.up().east()) == Blocks.AIR.getDefaultState())
							worldIn.setBlockState(pos.up().east(), Blocks.COCOA.getStateFromMeta(1));
					}else{
						if(worldIn.getBlockState(pos.up().west()) == Blocks.AIR.getDefaultState())
							worldIn.setBlockState(pos.up().west(), Blocks.COCOA.getStateFromMeta(3));
					}
				}
			}
			counter++;
		}
	}
	
	@Override
	protected Boolean changesBiomeOnUse() {
		return ConfigurationFile.jungleCrystalChangesBiome;
	}
	
	@Override
	protected Biome getBiomeType() {
		return Biomes.JUNGLE;
	}
	
	@Override
	protected int getDiameter() {
		return ConfigurationFile.jungleCrystalDiameter;
	}
	
	@Override
	protected int getDurability() {
		return ConfigurationFile.jungleCrystalDurability;
	}
}