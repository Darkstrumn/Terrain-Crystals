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

public class TerrainCrystalMushroom extends TerrainCrystalAbstract{
	
	public TerrainCrystalMushroom(){
		super("Mushroom_Island");
	}
	
	public TerrainCrystalMushroom(boolean isGroundCrystal){
		super("Mushroom_Island", isGroundCrystal);
	}
	
	@Override
	protected int generateBlocksInWorld(BlockPos pos, World worldIn, EntityPlayer playerIn, int blocksGenerated,
										Biome desiredBiome, boolean changeBiome){
		if(eligibleStateLocation(worldIn, pos)){
			int posY = MathHelper.floor_double(playerIn.posY);
			if(posY - pos.getY() == 1){
<<<<<<< HEAD
				worldIn.setBlockState(pos, Blocks.MYCELIUM.getDefaultState());
				setBiome(worldIn, pos, desiredBiome, changeBiome);
				if(!worldIn.isRemote)
					decoratePlatform(worldIn, pos);
			}else if(ConfigurationFile.generateStone && posY - pos.getY() >= ConfigurationFile.stoneSpawnDepth){
				if(ConfigurationFile.generateOres && Math.random() < 0.05){
					worldIn.setBlockState(pos, oreListHelper());
				}else{
					worldIn.setBlockState(pos, Blocks.STONE.getDefaultState());
				}
			}else{
				worldIn.setBlockState(pos, Blocks.DIRT.getDefaultState());
=======
				setBiome(worldIn, pos, desiredBiome, changeBiome);
				decoratePlatform(worldIn, pos);
			}else {
				handleDepthGeneration(worldIn, pos, posY);
>>>>>>> master
			}
			blocksGenerated += 1;
		}
		return blocksGenerated;
	}
	
	@Override
	protected void decoratePlatform(World worldIn, BlockPos pos){
        if(!worldIn.isRemote) {
            worldIn.setBlockState(pos, Blocks.MYCELIUM.getDefaultState());
            if (Blocks.BROWN_MUSHROOM.canPlaceBlockAt(worldIn, pos.up())) {
                worldIn.setBlockState(pos, Blocks.MYCELIUM.getDefaultState());
                if (Math.random() < .10) {
                    if (Math.random() < .5) {
                        worldIn.setBlockState(pos.up(), Blocks.BROWN_MUSHROOM.getDefaultState());
                        if (Math.random() < 0.1 && spacedFarEnough(worldIn, pos)) {
                            bonemealBlockNoRemoval(worldIn, pos);
                        }
                    } else {
                        worldIn.setBlockState(pos.up(), Blocks.RED_MUSHROOM.getDefaultState());
                        if (Math.random() < 0.1 && spacedFarEnough(worldIn, pos)) {
                            bonemealBlockNoRemoval(worldIn, pos);
                        }
                    }
                }
            }
        }
	}

	
	@Override
	protected Boolean changesBiomeOnUse() {
		return ConfigurationFile.mushroomCrystalChangesBiome;
	}
	
	@Override
	protected Biome getBiomeType() {
		return Biomes.MUSHROOM_ISLAND;
	}
	
	@Override
	protected int getDiameter() {
		return ConfigurationFile.mushroomCrystalDiameter;
	}
	
	@Override
	protected int getDurability() {
		return ConfigurationFile.mushroomCrystalDurability;
	}
}

