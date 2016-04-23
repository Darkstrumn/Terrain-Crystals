package com.DrasticDemise.TerrainCrystals.Items;

import java.util.ArrayList;
import java.util.Random;

import com.DrasticDemise.TerrainCrystals.ConfigurationFile;

import net.minecraft.block.IGrowable;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Biomes;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.chunk.Chunk;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class TerrainCrystalNether extends TerrainCrystalAbstract{
	public TerrainCrystalNether(){
		setUnlocalizedName("terrainCrystalNether");
		setRegistryName("terrainCrystalNether");
		setCreativeTab(CreativeTabs.tabBlock);
		setHarvestLevel("stone", 0);
		setMaxStackSize(1);
		setMaxDamage(ConfigurationFile.netherCrystalDurability);
        GameRegistry.register(this);
	}
	@Override
	public ActionResult<ItemStack> onItemRightClick(ItemStack itemStackIn, World worldIn, EntityPlayer playerIn, EnumHand hand){
		super.gatherBlockGenList(itemStackIn, worldIn, playerIn, ConfigurationFile.netherCrystalDiameter, Biomes.hell, ConfigurationFile.netherCrystalChangesBiome);
		return new ActionResult(EnumActionResult.PASS, itemStackIn);
	}
	@Override
	protected int generateBlocksInWorld(BlockPos pos, World worldIn, EntityPlayer playerIn, int blocksGenerated,
			BiomeGenBase desiredBiome, boolean changeBiome){
		if(eligibleStateLocation(worldIn, pos)){
			int posY = MathHelper.floor_double(playerIn.posY);
			if(posY - pos.getY() == 1){
				if(Math.random() < .9){
					worldIn.setBlockState(pos, Blocks.netherrack.getDefaultState());
					if(ConfigurationFile.netherCrystalChangesBiome){
						setBiome(worldIn, pos, desiredBiome, changeBiome);
					}
					decoratePlatform(worldIn, pos);
				}else if (Math.random() < 0.3){
					worldIn.setBlockState(pos, Blocks.soul_sand.getDefaultState());
					decoratePlatform(worldIn, pos);
				}else{
					worldIn.setBlockState(pos, Blocks.gravel.getDefaultState());
				}
			}else{
				if(Math.random() < .95){
					worldIn.setBlockState(pos, Blocks.netherrack.getDefaultState());
				}else{
					worldIn.setBlockState(pos, Blocks.soul_sand.getDefaultState());
				}
			}
			blocksGenerated += 1;
		}
		return blocksGenerated;
	}
	protected void decoratePlatform(World worldIn, BlockPos pos){
		if(Blocks.brown_mushroom.canPlaceBlockAt(worldIn, pos.up())){
			if(Math.random() < .10){
				if(Math.random() < .3){
					worldIn.setBlockState(pos.up(), Blocks.brown_mushroom.getDefaultState());
				}else{
					worldIn.setBlockState(pos.up(), Blocks.red_mushroom.getDefaultState());
				}
			}
		}
	}
}
