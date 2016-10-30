package com.BaileyHollingsworth.TerrainCrystals.Items.SkyCrystals;

import com.BaileyHollingsworth.TerrainCrystals.Items.TerrainCrystalAbstract;
import com.BaileyHollingsworth.TerrainCrystals.core.ConfigurationFile;

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

public class TerrainCrystalNether extends TerrainCrystalAbstract{
	public TerrainCrystalNether(){
		super("Nether");
	}
	public TerrainCrystalNether(boolean isGroundCrystal){
		super("Nether", isGroundCrystal);
	}
	@Override
	protected int generateBlocksInWorld(BlockPos pos, World worldIn, EntityPlayer playerIn, int blocksGenerated,
			Biome desiredBiome, boolean changeBiome){
		if(checkIfDimensionMatters(playerIn)){
			if(eligibleStateLocation(worldIn, pos)){
				int posY = MathHelper.floor_double(playerIn.posY);
				if(posY - pos.getY() == 1){
					if(Math.random() < .9){
						worldIn.setBlockState(pos, Blocks.NETHERRACK.getDefaultState());
						if(ConfigurationFile.generateOres && Math.random() < .05){
							if(Math.random() < 0.5){
								worldIn.setBlockState(pos, Blocks.QUARTZ_ORE.getDefaultState());
							}else{
								worldIn.setBlockState(pos, Blocks.GLOWSTONE.getDefaultState());
							}
						}
						decoratePlatform(worldIn, pos);
					}else if (Math.random() < 0.3){
						worldIn.setBlockState(pos, Blocks.SOUL_SAND.getDefaultState());
						decoratePlatform(worldIn, pos);
					}else{
						worldIn.setBlockState(pos, Blocks.GRAVEL.getDefaultState());
					}
					if(ConfigurationFile.netherCrystalChangesBiome){
						setBiome(worldIn, pos, desiredBiome, changeBiome);
					}
				}else{
					if(Math.random() < .95){
						worldIn.setBlockState(pos, Blocks.NETHERRACK.getDefaultState());
					}else{
						worldIn.setBlockState(pos, Blocks.SOUL_SAND.getDefaultState());
					}
				}
				blocksGenerated += 1;
			}
		}
		return blocksGenerated;
	}
	private boolean checkIfDimensionMatters(EntityPlayer playerIn){
		if(ConfigurationFile.netherCrystalRestrictedToNether){
			if(playerIn.dimension == -1){
				return true;
			}else{
				return false;
			}
		}
		return true;
	}
	protected void decoratePlatform(World worldIn, BlockPos pos){
		if(Blocks.BROWN_MUSHROOM.canPlaceBlockAt(worldIn, pos.up())){
			if(Math.random() < .10){
				if(Math.random() < .3){
					worldIn.setBlockState(pos.up(), Blocks.BROWN_MUSHROOM.getDefaultState());
				}else{
					worldIn.setBlockState(pos.up(), Blocks.RED_MUSHROOM.getDefaultState());
				}
			}
		}
	}
	@Override
	protected Boolean changesBiomeOnUse() {
		return ConfigurationFile.netherCrystalChangesBiome;
	}
	@Override
	protected Biome getBiomeType() {
		return Biomes.HELL;
	}
	@Override
	protected int getDiameter() {
		return ConfigurationFile.netherCrystalDiameter;
	}
	@Override
	protected int getDurability() {
		return ConfigurationFile.netherCrystalDurability;
	}
}
