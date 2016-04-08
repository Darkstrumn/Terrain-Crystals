package com.DrasticDemise.TerrainCrystals.Items;

import java.util.ArrayList;

import com.DrasticDemise.TerrainCrystals.ConfigurationFile;

import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Biomes;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.chunk.Chunk;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class TerrainCrystalDesert extends TerrainCrystalAbstract{
	public TerrainCrystalDesert(){
		setUnlocalizedName("terrainCrystalDesert");
		setRegistryName("terrainCrystalDesert");
		setCreativeTab(CreativeTabs.tabBlock);
		setHarvestLevel("stone", 0);
		setMaxStackSize(1);
		//setMaxDamage
		setMaxDamage(ConfigurationFile.desertCrystalDurability);
        GameRegistry.register(this);
	}
	@Override
	public ActionResult<ItemStack> onItemRightClick(ItemStack itemStackIn, World worldIn, EntityPlayer playerIn, EnumHand hand){
		super.gatherBlockGenList(itemStackIn, worldIn, playerIn, ConfigurationFile.desertCrystalDiameter, Biomes.desert, ConfigurationFile.desertCrystalChangesBiome);
		return new ActionResult(EnumActionResult.PASS, itemStackIn);
	}
	
	@Override
	protected int generateBlocksInWorld(BlockPos pos, World worldIn, EntityPlayer playerIn, int blocksGenerated,
			BiomeGenBase desiredBiome, boolean changeBiome) {
		
		int posY = MathHelper.floor_double(playerIn.posY);
		if(posY - pos.getY() == 1){
			super.setBiome(worldIn, pos, desiredBiome, changeBiome);
			
			if(Math.random() < 0.7){
				worldIn.setBlockState(pos, Blocks.sand.getDefaultState());
				decoratePlatform(worldIn, pos);	
			}else{
				worldIn.setBlockState(pos, Blocks.sandstone.getDefaultState());
			}
		}else{
			if(Math.random() < .9){
				worldIn.setBlockState(pos, Blocks.sandstone.getDefaultState());
			}else{
				if(worldIn.getBlockState(pos.down()).equals(Blocks.air.getDefaultState())){
					worldIn.setBlockState(pos, Blocks.sandstone.getDefaultState());
				}else{
					worldIn.setBlockState(pos, Blocks.sand.getDefaultState());
				}
			}
		}
		blocksGenerated++;
		return blocksGenerated;
	}
	
	@Override
	protected void decoratePlatform(World worldIn, BlockPos pos){
		if(Blocks.cactus.canPlaceBlockAt(worldIn, pos.up()) && ConfigurationFile.desertCrystalGenerateCactus){
			if(Math.random() < .10){
				if(Math.random() < .5){
					worldIn.setBlockState(pos.up(), Blocks.cactus.getDefaultState());
					if(Math.random() < .5){
						worldIn.setBlockState(pos.up(2), Blocks.cactus.getDefaultState());
						if(Math.random() < .5){
							worldIn.setBlockState(pos.up(3), Blocks.cactus.getDefaultState());
						}
					}
				}else{
					worldIn.setBlockState(pos.up(), Blocks.deadbush.getDefaultState());
				}
			}
		}
	}
}
