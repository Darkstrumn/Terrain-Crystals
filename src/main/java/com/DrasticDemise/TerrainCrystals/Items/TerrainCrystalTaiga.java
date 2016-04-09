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

public class TerrainCrystalTaiga extends TerrainCrystalAbstract{
	public TerrainCrystalTaiga(){
		setUnlocalizedName("terrainCrystalTaiga");
		setRegistryName("terrainCrystalTaiga");
		setCreativeTab(CreativeTabs.tabBlock);
		setHarvestLevel("stone", 0);
		setMaxStackSize(1);
		setMaxDamage(ConfigurationFile.taigaCrystalDurability);
        GameRegistry.register(this);
	}
	@Override
	public ActionResult<ItemStack> onItemRightClick(ItemStack itemStackIn, World worldIn, EntityPlayer playerIn, EnumHand hand){
		super.gatherBlockGenList(itemStackIn, worldIn, playerIn, ConfigurationFile.taigaCrystalDiameter, Biomes.taiga, ConfigurationFile.taigaCrystalChangesBiome);
		return new ActionResult(EnumActionResult.PASS, itemStackIn);
	}
	@Override
	protected int generateBlocksInWorld(BlockPos pos, World worldIn, EntityPlayer playerIn, int blocksGenerated,
			BiomeGenBase desiredBiome, boolean changeBiome){
		
		if(eligibleStateLocation(worldIn, pos)){
			int posY = MathHelper.floor_double(playerIn.posY);
			if(posY - pos.getY() == 1){
				setBiome(worldIn, pos, desiredBiome, changeBiome);
				if(Math.random() < .4){
					if(Math.random() < .5){
						worldIn.setBlockState(pos, Blocks.dirt.getStateFromMeta(1));
					}else{
						worldIn.setBlockState(pos, Blocks.grass.getDefaultState());
					}
					if(ConfigurationFile.taigaCrystalGeneratesTrees && Math.random() <= 0.01){
						if(Math.random() < 0.08){
							growTree(worldIn, pos);
						}
					}else{
						decoratePlatform(worldIn, pos);
					}
				}else{
					worldIn.setBlockState(pos, Blocks.dirt.getStateFromMeta(2));
				}
			}else{
				worldIn.setBlockState(pos, Blocks.dirt.getDefaultState());
			}
		}
		return blocksGenerated++;
	}
	private void growTree(World worldIn, BlockPos pos) {
		try{
			if (Blocks.sapling.canPlaceBlockAt(worldIn, pos.up()) && spacedFarEnough(worldIn, pos)){
				worldIn.setBlockState(pos.up(), Blocks.sapling.getStateFromMeta(1));
				IGrowable growable = (IGrowable) worldIn.getBlockState(pos.up()).getBlock();
				Random rand = new Random();	
				int attemptCap = 0;
				while(worldIn.getBlockState(pos.up()) != Blocks.log.getStateFromMeta(1) && attemptCap < 10){
					growable.grow(worldIn, rand, pos.up(), worldIn.getBlockState(pos.up()));
					attemptCap++;
				}
				if(attemptCap > 8){
					worldIn.setBlockState(pos.up(), Blocks.air.getDefaultState());
				}
			}
		}catch(Exception e){
			
		}
	}
	@Override
	protected void decoratePlatform(World worldIn, BlockPos pos) {
		if(Math.random() < 0.02){
			if(Math.random() < 0.50){
				worldIn.setBlockState(pos.up(), Blocks.tallgrass.getStateFromMeta(2));
			}else{
				worldIn.setBlockState(pos.up(), Blocks.tallgrass.getStateFromMeta(1));
			}
		}
		
	}
}

