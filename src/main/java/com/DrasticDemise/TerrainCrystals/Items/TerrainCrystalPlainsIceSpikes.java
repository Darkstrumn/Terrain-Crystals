package com.DrasticDemise.TerrainCrystals.Items;

import com.DrasticDemise.TerrainCrystals.ConfigurationFile;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraftforge.fml.common.registry.GameRegistry;

public class TerrainCrystalPlainsIceSpikes extends TerrainCrystalAbstract{
	public TerrainCrystalPlainsIceSpikes(){
		setUnlocalizedName("terrainCrystalPlainsIceSpikes");
		setRegistryName("terrainCrystalPlainsIceSpikes");
		setCreativeTab(CreativeTabs.tabBlock);
		setHarvestLevel("stone", 0);
		setMaxStackSize(1);
		setMaxDamage(ConfigurationFile.plainsIceCrystalDurability);
        GameRegistry.registerItem(this);
	}
	@Override
	public ItemStack onItemRightClick(ItemStack itemStackIn, World worldIn, EntityPlayer playerIn) {
		return super.gatherBlockGenList(itemStackIn, worldIn, playerIn, ConfigurationFile.plainsIceCrystalDiameter, BiomeGenBase.icePlains, ConfigurationFile.plainsIceCrystalChangesBiome);
	}

	@Override
	protected int generateBlocksInWorld(BlockPos pos, World worldIn, EntityPlayer playerIn, int blocksGenerated,
			BiomeGenBase desiredBiome, boolean changeBiome) {
		if(eligibleStateLocation(worldIn.getBlockState(pos), pos)){
			int posY = MathHelper.floor_double(playerIn.posY);
			int layer = posY - pos.getY();
			if(layer == 1){
				worldIn.setBlockState(pos, Blocks.snow.getDefaultState());
				decoratePlatform(worldIn, pos);
				setBiome(worldIn, pos, desiredBiome, changeBiome);
				
				blocksGenerated++;
			}else if(layer == 2){
				if(Math.random() < .80){
					worldIn.setBlockState(pos, Blocks.snow.getDefaultState());
				}else{
					worldIn.setBlockState(pos, Blocks.stone.getDefaultState());
				}
			}else if(layer == 3){
				if(Math.random() < .40){
					worldIn.setBlockState(pos, Blocks.snow.getDefaultState());
				}else{
					worldIn.setBlockState(pos, Blocks.stone.getDefaultState());
				}
			}
			else{
				worldIn.setBlockState(pos, Blocks.stone.getDefaultState());
				blocksGenerated++;
			}
		}
		return blocksGenerated;
	}

	@Override
	void decoratePlatform(World worldIn, BlockPos pos) {
		if(Math.random() < .55){
			if(worldIn.getBlockState(pos.up()) == Blocks.air.getDefaultState())
				worldIn.setBlockState(pos.up(), Blocks.snow_layer.getDefaultState());
		}else{
			if(Math.random() < 0.03){
				//Creates the pillar up
				worldIn.setBlockState(pos, Blocks.packed_ice.getDefaultState());
				worldIn.setBlockState(pos.up(), Blocks.packed_ice.getDefaultState());
				worldIn.setBlockState(pos.up(2), Blocks.packed_ice.getDefaultState());
				worldIn.setBlockState(pos.up(3), Blocks.packed_ice.getDefaultState());
				//Creates the sides beneath the snow
				worldIn.setBlockState(pos.north(), Blocks.packed_ice.getDefaultState());
				worldIn.setBlockState(pos.south(), Blocks.packed_ice.getDefaultState());
				worldIn.setBlockState(pos.east(), Blocks.packed_ice.getDefaultState());
				worldIn.setBlockState(pos.west(), Blocks.packed_ice.getDefaultState());
				//Creates the sides on the surface
				worldIn.setBlockState(pos.up().north(), Blocks.packed_ice.getDefaultState());
				worldIn.setBlockState(pos.up().south(), Blocks.packed_ice.getDefaultState());
				worldIn.setBlockState(pos.up().east(), Blocks.packed_ice.getDefaultState());
				worldIn.setBlockState(pos.up().west(), Blocks.packed_ice.getDefaultState());
				int layer = 4;
				int surfaceOutwardsIncrease = 1;
				for(int i = 0; i < 9; i ++){
					if(Math.random() < .3){
						worldIn.setBlockState(pos.up(layer), Blocks.packed_ice.getDefaultState());
						layer++;
						if(Math.random() < 0.3){
							surfaceOutwardsIncrease++;
							worldIn.setBlockState(pos.up(surfaceOutwardsIncrease).north(), Blocks.packed_ice.getDefaultState());
							worldIn.setBlockState(pos.up(surfaceOutwardsIncrease).south(), Blocks.packed_ice.getDefaultState());
							worldIn.setBlockState(pos.up(surfaceOutwardsIncrease).east(), Blocks.packed_ice.getDefaultState());
							worldIn.setBlockState(pos.up(surfaceOutwardsIncrease).west(), Blocks.packed_ice.getDefaultState());
						}
					}
				}
				if(Math.random() < .5){
					worldIn.setBlockState(pos.up(layer), Blocks.packed_ice.getDefaultState());
				}
				int flowerLayer = layer - 2;
				int outwardsLayer = flowerLayer;
				boolean generateOutwards = false;
				if(Math.random() < .5 && layer > 5){
					for(int i = 0; i < 2; i ++){
						worldIn.setBlockState(pos.up(flowerLayer).north(), Blocks.packed_ice.getDefaultState());
						worldIn.setBlockState(pos.up(flowerLayer).south(), Blocks.packed_ice.getDefaultState());
						worldIn.setBlockState(pos.up(flowerLayer).east(), Blocks.packed_ice.getDefaultState());
						worldIn.setBlockState(pos.up(flowerLayer).west(), Blocks.packed_ice.getDefaultState());
						flowerLayer--;
					}
					generateOutwards = true;
				}
				if(Math.random() < .4 && generateOutwards == true){
					for(int i = 0; i < 2; i ++){
						worldIn.setBlockState(pos.up(outwardsLayer).north().east(), Blocks.packed_ice.getDefaultState());
						worldIn.setBlockState(pos.up(outwardsLayer).north().west(), Blocks.packed_ice.getDefaultState());
						worldIn.setBlockState(pos.up(outwardsLayer).south().east(), Blocks.packed_ice.getDefaultState());
						worldIn.setBlockState(pos.up(outwardsLayer).south().west(), Blocks.packed_ice.getDefaultState());
						outwardsLayer--;
					}
				}
			}	
		}
	}
}
