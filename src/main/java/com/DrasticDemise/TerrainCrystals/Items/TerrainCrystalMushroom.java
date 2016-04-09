package com.DrasticDemise.TerrainCrystals.Items;

import java.util.ArrayList;
import java.util.Random;

import com.DrasticDemise.TerrainCrystals.ConfigurationFile;

import net.minecraft.block.IGrowable;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.entity.player.EntityPlayer;
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

public class TerrainCrystalMushroom extends TerrainCrystalAbstract{
	
	public TerrainCrystalMushroom(){
		setUnlocalizedName("terrainCrystalMushroom");
		setRegistryName("terrainCrystalMushroom");
		setCreativeTab(CreativeTabs.tabBlock);
		setHarvestLevel("stone", 0);
		setMaxStackSize(1);
		setMaxDamage(ConfigurationFile.mushroomCrystalDurability);
	    GameRegistry.register(this);
	}
	@Override
	public ActionResult<ItemStack> onItemRightClick(ItemStack itemStackIn, World worldIn, EntityPlayer playerIn, EnumHand hand){
		super.gatherBlockGenList(itemStackIn, worldIn, playerIn, ConfigurationFile.mushroomCrystalDiameter, Biomes.mushroomIsland, ConfigurationFile.mushroomCrystalChangesBiome);
		return new ActionResult(EnumActionResult.PASS, itemStackIn);
	}
	@Override
	protected int generateBlocksInWorld(BlockPos pos, World worldIn, EntityPlayer playerIn, int blocksGenerated,
			BiomeGenBase desiredBiome, boolean changeBiome){
		if(eligibleStateLocation(worldIn, pos)){
			int posY = MathHelper.floor_double(playerIn.posY);
			if(posY - pos.getY() == 1){
				worldIn.setBlockState(pos, Blocks.mycelium.getDefaultState());
				super.setBiome(worldIn, pos, desiredBiome, changeBiome);
				decoratePlatform(worldIn, pos);
			}else{
				worldIn.setBlockState(pos, Blocks.dirt.getDefaultState());
			}
		}
		return blocksGenerated++;
	}
	protected void decoratePlatform(World worldIn, BlockPos pos){
		if(Blocks.brown_mushroom.canPlaceBlockAt(worldIn, pos.up())){
			if(Math.random() < .10){
				if(Math.random() < .5){
					worldIn.setBlockState(pos.up(), Blocks.brown_mushroom.getDefaultState());
					if(Math.random() < 0.1 && spacedFarEnough(worldIn, pos)){
						IGrowable growable = (IGrowable) worldIn.getBlockState(pos.up()).getBlock();
						Random rand = new Random();
						growable.grow(worldIn, rand, pos.up(), worldIn.getBlockState(pos));
					}
				}else{
					worldIn.setBlockState(pos.up(), Blocks.red_mushroom.getDefaultState());
					if(Math.random() < 0.1&& spacedFarEnough(worldIn, pos)){
						IGrowable growable = (IGrowable) worldIn.getBlockState(pos.up()).getBlock();
						Random rand = new Random();
						growable.grow(worldIn, rand, pos.up(), worldIn.getBlockState(pos));
					}
				}
			}
		}
	}
}

