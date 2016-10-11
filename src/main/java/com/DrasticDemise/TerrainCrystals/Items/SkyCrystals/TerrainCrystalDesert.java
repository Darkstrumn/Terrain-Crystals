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

public class TerrainCrystalDesert extends TerrainCrystalAbstract{
	public TerrainCrystalDesert(){
		super("Desert");
	}
	@Override
	protected int generateBlocksInWorld(BlockPos pos, World worldIn, EntityPlayer playerIn, int blocksGenerated,
										Biome desiredBiome, boolean changeBiome) {
		if(eligibleStateLocation(worldIn, pos)){
			int posY = MathHelper.floor_double(playerIn.posY);
			if(posY - pos.getY() == 1){
				super.setBiome(worldIn, pos, desiredBiome, changeBiome);
				
				if(Math.random() < 0.7){
					worldIn.setBlockState(pos, Blocks.SAND.getDefaultState());
					decoratePlatform(worldIn, pos);	
				}else{
					worldIn.setBlockState(pos, Blocks.SANDSTONE.getDefaultState());
				}
			}else{
				if(Math.random() < .9){
					worldIn.setBlockState(pos, Blocks.SANDSTONE.getDefaultState());
				}else{
					if(worldIn.getBlockState(pos.down()).equals(Blocks.AIR.getDefaultState())){
						worldIn.setBlockState(pos, Blocks.SANDSTONE.getDefaultState());
					}else{
						worldIn.setBlockState(pos, Blocks.SAND.getDefaultState());
					}
				}
			}
			blocksGenerated += 1;
		}
		return blocksGenerated;
	}
	
	@Override
	protected void decoratePlatform(World worldIn, BlockPos pos){
		if(Blocks.CACTUS.canPlaceBlockAt(worldIn, pos.up()) && ConfigurationFile.desertCrystalGenerateCactus){
			if(Math.random() < .10){
				if(Math.random() < .5){
					worldIn.setBlockState(pos.up(), Blocks.CACTUS.getDefaultState());
					if(Math.random() < .5){
						worldIn.setBlockState(pos.up(2), Blocks.CACTUS.getDefaultState());
						if(Math.random() < .5){
							worldIn.setBlockState(pos.up(3), Blocks.CACTUS.getDefaultState());
						}
					}
				}else{
					worldIn.setBlockState(pos.up(), Blocks.DEADBUSH.getDefaultState());
				}
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
