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
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;
import net.minecraft.world.biome.Biome;
import net.minecraftforge.fml.common.registry.GameRegistry;

public class TerrainCrystalPlainsIceSpikes extends TerrainCrystalAbstract{
	public TerrainCrystalPlainsIceSpikes(){
		super("PlainsIceSpikes");
	}
	@Override
	protected int generateBlocksInWorld(BlockPos pos, World worldIn, EntityPlayer playerIn, int blocksGenerated,
										Biome desiredBiome, boolean changeBiome) {
		if(eligibleStateLocation(worldIn, pos)){
			int posY = MathHelper.floor_double(playerIn.posY);
			int layer = posY - pos.getY();
			if(layer == 1){
				setBiome(worldIn, pos, desiredBiome, changeBiome);
				worldIn.setBlockState(pos, Blocks.SNOW.getDefaultState());
				decoratePlatform(worldIn, pos);
			}else if(layer == 2){
				if(Math.random() < .80){
					worldIn.setBlockState(pos, Blocks.SNOW.getDefaultState());
				}else{
					worldIn.setBlockState(pos, Blocks.STONE.getDefaultState());
				}
			}else if(layer == 3){
				if(Math.random() < .40){
					worldIn.setBlockState(pos, Blocks.SNOW.getDefaultState());
				}else{
					worldIn.setBlockState(pos, Blocks.STONE.getDefaultState());
				}
			}else if(ConfigurationFile.generateStone && posY - pos.getY() >= ConfigurationFile.stoneSpawnDepth){
				if(ConfigurationFile.generateOres && Math.random() < 0.05){
					worldIn.setBlockState(pos, oreListHelper());
				}else{
					worldIn.setBlockState(pos, Blocks.STONE.getDefaultState());
				}
			}
			else{
				worldIn.setBlockState(pos, Blocks.STONE.getDefaultState());
			}
			blocksGenerated += 1;
		}
		return blocksGenerated;
	}

	@Override
	protected void decoratePlatform(World worldIn, BlockPos pos) {
		if(Math.random() < .55){
			if(worldIn.getBlockState(pos.up()) == Blocks.AIR.getDefaultState())
				worldIn.setBlockState(pos.up(), Blocks.SNOW_LAYER.getDefaultState());
		}else{
			if(Math.random() < 0.03 && spacedFarEnough(worldIn, pos)){
				//Creates the pillar up
				worldIn.setBlockState(pos, Blocks.PACKED_ICE.getDefaultState());
				worldIn.setBlockState(pos.up(), Blocks.PACKED_ICE.getDefaultState());
				worldIn.setBlockState(pos.up(2), Blocks.PACKED_ICE.getDefaultState());
				worldIn.setBlockState(pos.up(3), Blocks.PACKED_ICE.getDefaultState());
				//Creates the sides beneath the SNOW
				worldIn.setBlockState(pos.north(), Blocks.PACKED_ICE.getDefaultState());
				worldIn.setBlockState(pos.south(), Blocks.PACKED_ICE.getDefaultState());
				worldIn.setBlockState(pos.east(), Blocks.PACKED_ICE.getDefaultState());
				worldIn.setBlockState(pos.west(), Blocks.PACKED_ICE.getDefaultState());
				//Creates the sides on the surface
				worldIn.setBlockState(pos.up().north(), Blocks.PACKED_ICE.getDefaultState());
				worldIn.setBlockState(pos.up().south(), Blocks.PACKED_ICE.getDefaultState());
				worldIn.setBlockState(pos.up().east(), Blocks.PACKED_ICE.getDefaultState());
				worldIn.setBlockState(pos.up().west(), Blocks.PACKED_ICE.getDefaultState());
				int layer = 4;
				int surfaceOutwardsIncrease = 1;
				for(int i = 0; i < 9; i ++){
					if(Math.random() < .3){
						worldIn.setBlockState(pos.up(layer), Blocks.PACKED_ICE.getDefaultState());
						layer++;
						if(Math.random() < 0.3){
							surfaceOutwardsIncrease++;
							worldIn.setBlockState(pos.up(surfaceOutwardsIncrease).north(), Blocks.PACKED_ICE.getDefaultState());
							worldIn.setBlockState(pos.up(surfaceOutwardsIncrease).south(), Blocks.PACKED_ICE.getDefaultState());
							worldIn.setBlockState(pos.up(surfaceOutwardsIncrease).east(), Blocks.PACKED_ICE.getDefaultState());
							worldIn.setBlockState(pos.up(surfaceOutwardsIncrease).west(), Blocks.PACKED_ICE.getDefaultState());
						}
					}
				}
				if(Math.random() < .5){
					worldIn.setBlockState(pos.up(layer), Blocks.PACKED_ICE.getDefaultState());
				}
				int flowerLayer = layer - 2;
				int outwardsLayer = flowerLayer;
				//boolean generateOutwards = false;
				if(Math.random() < .5 && layer > 5){
					for(int i = 0; i < 2; i ++){
						worldIn.setBlockState(pos.up(flowerLayer).north(), Blocks.PACKED_ICE.getDefaultState());
						worldIn.setBlockState(pos.up(flowerLayer).south(), Blocks.PACKED_ICE.getDefaultState());
						worldIn.setBlockState(pos.up(flowerLayer).east(), Blocks.PACKED_ICE.getDefaultState());
						worldIn.setBlockState(pos.up(flowerLayer).west(), Blocks.PACKED_ICE.getDefaultState());
						flowerLayer--;
					}
					if(Math.random() < .4){
						for(int i = 0; i < 2; i ++){
							worldIn.setBlockState(pos.up(outwardsLayer).north().east(), Blocks.PACKED_ICE.getDefaultState());
							worldIn.setBlockState(pos.up(outwardsLayer).north().west(), Blocks.PACKED_ICE.getDefaultState());
							worldIn.setBlockState(pos.up(outwardsLayer).south().east(), Blocks.PACKED_ICE.getDefaultState());
							worldIn.setBlockState(pos.up(outwardsLayer).south().west(), Blocks.PACKED_ICE.getDefaultState());
							outwardsLayer--;
						}
					}
				}
			}	
		}
	}
	@Override
	protected Boolean changesBiomeOnUse() {
		return ConfigurationFile.plainsIceCrystalChangesBiome;
	}
	@Override
	protected Biome getBiomeType() {
		return Biomes.ICE_PLAINS;
	}
	@Override
	protected int getDiameter() {
		return ConfigurationFile.plainsIceCrystalDiameter;
	}
	@Override
	protected int getDurability() {
		return ConfigurationFile.plainsIceCrystalDurability;
	}
}
