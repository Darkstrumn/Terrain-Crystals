package com.BaileyHollingsworth.TerrainCrystals.Items.SkyCrystals;

import com.BaileyHollingsworth.TerrainCrystals.Items.TerrainCrystalAbstract;
import com.BaileyHollingsworth.TerrainCrystals.core.ConfigurationFile;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Biomes;
import net.minecraft.init.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;
import net.minecraft.world.biome.Biome;

public class TerrainCrystalTaiga extends TerrainCrystalAbstract{
	
	public TerrainCrystalTaiga(){
		super("Taiga");
	}
	
	public TerrainCrystalTaiga(boolean isGroundCrystal){
		super("Taiga", isGroundCrystal);
	}
	
	@Override
	protected int generateBlocksInWorld(BlockPos pos, World worldIn, EntityPlayer playerIn, int blocksGenerated,
										Biome desiredBiome, boolean changeBiome){
		
		if(eligibleStateLocation(worldIn, pos)){
			int posY = MathHelper.floor_double(playerIn.posY);
			if(posY - pos.getY() == 1){
				setBiome(worldIn, pos, desiredBiome, changeBiome);
<<<<<<< HEAD
				if(Math.random() < .4){
					if(Math.random() < .5){
						worldIn.setBlockState(pos, Blocks.DIRT.getStateFromMeta(1));
					}else{
						worldIn.setBlockState(pos, Blocks.GRASS.getDefaultState());
					}
					if(!worldIn.isRemote) {
						if (ConfigurationFile.taigaCrystalGeneratesTrees && Math.random() < 0.08) {
							growTree(worldIn, pos);
						} else {
							decoratePlatform(worldIn, pos);
						}
					}
				}else{
					worldIn.setBlockState(pos, Blocks.DIRT.getStateFromMeta(2));
				}
			}else if(ConfigurationFile.generateStone && posY - pos.getY() >= ConfigurationFile.stoneSpawnDepth){
				if(ConfigurationFile.generateOres && Math.random() < 0.05){
					worldIn.setBlockState(pos, oreListHelper());
				}else{
					worldIn.setBlockState(pos, Blocks.STONE.getDefaultState());
				}
=======
				decoratePlatform(worldIn, pos);
>>>>>>> master
			}else{
                handleDepthGeneration(worldIn, pos, posY);
            }
			blocksGenerated += 1;
		}
		return blocksGenerated;
	}
	
	private void growTree(World worldIn, BlockPos pos) {
		if (Blocks.SAPLING.canPlaceBlockAt(worldIn, pos.up()) && spacedFarEnough(worldIn, pos)){
			worldIn.setBlockState(pos.up(), Blocks.SAPLING.getStateFromMeta(1));
			bonemealTree(worldIn, pos);
		}
	}
	
	@Override
	protected void decoratePlatform(World worldIn, BlockPos pos) {
        if(!worldIn.isRemote) {
            if (Math.random() < .4) {
                worldIn.setBlockState(pos, Math.random() < .5 ? Blocks.DIRT.getStateFromMeta(1) : Blocks.GRASS.getDefaultState());
                if (ConfigurationFile.taigaCrystalGeneratesTrees && Math.random() < 0.08) {
                    growTree(worldIn, pos);
                } else {
                    if (Math.random() < 0.3) {
                        if (Math.random() < 0.50) {
                            worldIn.setBlockState(pos.up(), Blocks.TALLGRASS.getStateFromMeta(2));
                        } else {
                            worldIn.setBlockState(pos.up(), Blocks.TALLGRASS.getStateFromMeta(1));
                        }
                    }
                }
            } else {
                worldIn.setBlockState(pos, Blocks.DIRT.getStateFromMeta(2));
            }
        }
	}
	
	@Override
	protected Boolean changesBiomeOnUse() {
		return ConfigurationFile.taigaCrystalChangesBiome;
	}
	
	@Override
	protected Biome getBiomeType() {
		return Biomes.TAIGA;
	}
	
	@Override
	protected int getDiameter() {
		return ConfigurationFile.taigaCrystalDiameter;
	}
	
	@Override
	protected int getDurability() {
		return ConfigurationFile.taigaCrystalDurability;
	}
}

