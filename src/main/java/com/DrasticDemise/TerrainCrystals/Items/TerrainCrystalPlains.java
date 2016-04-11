package com.DrasticDemise.TerrainCrystals.Items;

import java.util.ArrayList;
import java.util.Random;

import com.DrasticDemise.TerrainCrystals.ConfigurationFile;

import net.minecraft.block.IGrowable;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.init.Biomes;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.chunk.Chunk;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class TerrainCrystalPlains extends TerrainCrystalAbstract{
	public TerrainCrystalPlains(){
		setUnlocalizedName("terrainCrystalPlains");
		setRegistryName("terrainCrystalPlains");
		setCreativeTab(CreativeTabs.tabBlock);
		setHarvestLevel("stone", 0);
		setMaxStackSize(1);
		setMaxDamage(ConfigurationFile.plainsCrystalDurability);
        GameRegistry.register(this);
	}
	@Override
	public ActionResult<ItemStack> onItemRightClick(ItemStack itemStackIn, World worldIn, EntityPlayer playerIn, EnumHand hand){
	    super.gatherBlockGenList(itemStackIn, worldIn, playerIn, ConfigurationFile.plainsCrystalDiameter, Biomes.plains, ConfigurationFile.plainsCrystalChangesBiome);
		return new ActionResult(EnumActionResult.PASS, itemStackIn);
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
					worldIn.setBlockState(pos.up(), Blocks.sapling.getStateFromMeta(2));
				}else{
					worldIn.setBlockState(pos.up(), Blocks.sapling.getDefaultState());
				}
				IGrowable growable = (IGrowable) worldIn.getBlockState(pos.up()).getBlock();
				Random rand = new Random();	
				int attemptCap = 0;
				while((worldIn.getBlockState(pos.up()) != Blocks.log.getDefaultState()) && attemptCap < 10){
					growable.grow(worldIn, rand, pos.up(), worldIn.getBlockState(pos.up()));
					attemptCap++;
				}
			}
		}
	}
}
