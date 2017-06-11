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

public class TerrainCrystalDesert extends TerrainCrystalAbstract{
	
	public TerrainCrystalDesert(){
		super("Desert");
	}
	
	public TerrainCrystalDesert(boolean isGroundCrystal){
		super("Desert", isGroundCrystal);
	}
	
	@Override
	protected int generateBlocksInWorld(BlockPos pos, World worldIn, EntityPlayer playerIn, int blocksGenerated,
										Biome desiredBiome, boolean changeBiome) {
		if(eligibleStateLocation(worldIn, pos)){
			int posY = MathHelper.floor_double(playerIn.posY);
			if(posY - pos.getY() == 1){
				super.setBiome(worldIn, pos, desiredBiome, changeBiome);
<<<<<<< HEAD
				if(Math.random() < 0.7){
					worldIn.setBlockState(pos.down(), Blocks.SANDSTONE.getDefaultState());
					worldIn.setBlockState(pos, Blocks.SAND.getDefaultState());
					if(!worldIn.isRemote)
						decoratePlatform(worldIn, pos);
				}else{
					worldIn.setBlockState(pos, Blocks.SANDSTONE.getDefaultState());
				}
			}else if (posY - pos.getY() < 4){
                worldIn.setBlockState(pos, Blocks.SANDSTONE.getDefaultState());
			}else if(ConfigurationFile.generateStone && posY - pos.getY() >= ConfigurationFile.stoneSpawnDepth){
                if(ConfigurationFile.generateOres && Math.random() < 0.05){
                    worldIn.setBlockState(pos, oreListHelper());
                }else{
                    worldIn.setBlockState(pos, Blocks.STONE.getDefaultState());
                }
            }
            else{
=======
				decoratePlatform(worldIn, pos);
			}else if (posY - pos.getY() < 4 && !worldIn.isRemote){
>>>>>>> master
                worldIn.setBlockState(pos, Blocks.SANDSTONE.getDefaultState());
			}else {
                handleDepthGeneration(worldIn, pos, posY, Blocks.SANDSTONE.getDefaultState());
            }
			blocksGenerated += 1;
		}
		return blocksGenerated;
	}

	@Override
	protected void decoratePlatform(World worldIn, BlockPos pos){
        if(!worldIn.isRemote) {
            if (Math.random() < 0.7) {
                worldIn.setBlockState(pos.down(), Blocks.SANDSTONE.getDefaultState());
                worldIn.setBlockState(pos, Blocks.SAND.getDefaultState());
                if (Blocks.CACTUS.canPlaceBlockAt(worldIn, pos.up()) && ConfigurationFile.desertCrystalGenerateCactus) {
                    if (Math.random() < .10) {
                        if (Math.random() < .5) {
                            worldIn.setBlockState(pos.up(), Blocks.CACTUS.getDefaultState());
                            if (Math.random() < .5) {
                                worldIn.setBlockState(pos.up(2), Blocks.CACTUS.getDefaultState());
                                if (Math.random() < .5) {
                                    worldIn.setBlockState(pos.up(3), Blocks.CACTUS.getDefaultState());
                                }
                            }
                        } else {
                            worldIn.setBlockState(pos.up(), Blocks.DEADBUSH.getDefaultState());
                        }
                    }
                }
            }else{
                worldIn.setBlockState(pos, Blocks.SANDSTONE.getDefaultState());
            }
        }
	}
	
	@Override
	protected Boolean changesBiomeOnUse() {
		return ConfigurationFile.desertCrystalChangesBiome;
	}
	
	@Override
	protected Biome getBiomeType() {
		return Biomes.DESERT;
	}
	
	@Override
	protected int getDiameter() {
		return ConfigurationFile.desertCrystalDiameter;
	}
	
	@Override
	protected int getDurability() {
		return ConfigurationFile.desertCrystalDurability;
	}
}
