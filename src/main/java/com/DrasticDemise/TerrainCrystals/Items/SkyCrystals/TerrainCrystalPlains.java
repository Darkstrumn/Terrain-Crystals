package com.DrasticDemise.TerrainCrystals.Items.SkyCrystals;

import java.util.Random;

import com.DrasticDemise.TerrainCrystals.Items.TerrainCrystalAbstract;
import com.DrasticDemise.TerrainCrystals.core.ConfigurationFile;

import net.minecraft.block.IGrowable;
import net.minecraft.block.state.IBlockState;
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

public class TerrainCrystalPlains extends TerrainCrystalAbstract{
	public TerrainCrystalPlains(){
		super("Plains");
	}
	//Code taken from Lumien's Random Things Nature Core tile entity
	protected void decoratePlatform(World worldIn, BlockPos pos){
		if(ConfigurationFile.plainsCrystalGenerateTallGrass){
			IBlockState state = worldIn.getBlockState(pos);
			Random rand = new Random();
			//Try-catching our worries away!
			try{
				if(Math.random() < 0.10){
					bonemeal(worldIn, pos);
				}
				if(Math.random() <= 0.05){
					growTree(worldIn, pos);
				}
			}catch(Exception e){}
		}
	}
	private void growTree(World worldIn, BlockPos pos){
		if(ConfigurationFile.plainsCrystalGenerateTrees){
			//spacedFarEnough(worldIn, pos.up())
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
