package com.DrasticDemise.TerrainCrystals.Items;

import java.util.ArrayList;

import com.DrasticDemise.TerrainCrystals.ConfigurationFile;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.chunk.Chunk;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class TerrainCrystalMesa extends TerrainCrystalAbstract{
	public TerrainCrystalMesa(){
		setUnlocalizedName("terrainCrystalMesa");
		setRegistryName("terrainCrystalMesa");
		setCreativeTab(CreativeTabs.tabBlock);
		setHarvestLevel("stone", 0);
		setMaxStackSize(1);
		setMaxDamage(ConfigurationFile.mesaCrystalDurability);
        GameRegistry.registerItem(this);
	}
	@Override
	public ItemStack onItemRightClick(ItemStack itemStackIn, World worldIn, EntityPlayer playerIn) {
		return super.gatherBlockGenList(itemStackIn, worldIn, playerIn, ConfigurationFile.mesaCrystalDiameter, BiomeGenBase.mesa, ConfigurationFile.mesaCrystalChangesBiome);
	}
	@Override
	protected int generateBlocksInWorld(BlockPos pos, World worldIn, EntityPlayer playerIn, int blocksGenerated,
			BiomeGenBase desiredBiome, boolean changeBiome){
		if(eligibleStateLocation(worldIn.getBlockState(pos), pos)){
			int posY = MathHelper.floor_double(playerIn.posY);
			int getMetaFromPlayerDistance = posY - pos.getY();
			if(posY - pos.getY() == 1){
				if(Math.random() < .7){
					worldIn.setBlockState(pos, Blocks.sand.getStateFromMeta(1));
					if(ConfigurationFile.mesaCrystalChangesBiome){
						super.setBiome(worldIn, pos, desiredBiome, changeBiome);
					}
					decoratePlatform(worldIn, pos);
				}else{
					if(Math.random() < .50){
						worldIn.setBlockState(pos, Blocks.dirt.getDefaultState());
					}else{
						worldIn.setBlockState(pos, Blocks.stained_hardened_clay.getStateFromMeta(1));
					}
				}
			}else{
				if(getMetaFromPlayerDistance == 2){
					worldIn.setBlockState(pos, Blocks.stained_hardened_clay.getStateFromMeta(getMetaFromPlayerDistance - 1));
				}else if (getMetaFromPlayerDistance == 3 || getMetaFromPlayerDistance == 4){
					worldIn.setBlockState(pos, Blocks.stained_hardened_clay.getStateFromMeta(4));
				}else if (getMetaFromPlayerDistance == 5 || getMetaFromPlayerDistance == 6){
					worldIn.setBlockState(pos, Blocks.stained_hardened_clay.getStateFromMeta(5));
				}else if (getMetaFromPlayerDistance == 7 || getMetaFromPlayerDistance == 8){
					worldIn.setBlockState(pos, Blocks.stained_hardened_clay.getStateFromMeta(7));
				}else if (getMetaFromPlayerDistance == 9 || getMetaFromPlayerDistance == 10){
					worldIn.setBlockState(pos, Blocks.stained_hardened_clay.getStateFromMeta(8));
				}else if (getMetaFromPlayerDistance == 11){
					worldIn.setBlockState(pos, Blocks.stained_hardened_clay.getStateFromMeta(getMetaFromPlayerDistance));
				}else if (getMetaFromPlayerDistance == 12){
					worldIn.setBlockState(pos, Blocks.stained_hardened_clay.getStateFromMeta(getMetaFromPlayerDistance));
				}else if (getMetaFromPlayerDistance == 13){
					worldIn.setBlockState(pos, Blocks.stained_hardened_clay.getStateFromMeta(getMetaFromPlayerDistance));
				}else if (getMetaFromPlayerDistance == 14){
					worldIn.setBlockState(pos, Blocks.stained_hardened_clay.getStateFromMeta(getMetaFromPlayerDistance));
				}else{
					worldIn.setBlockState(pos, Blocks.stained_hardened_clay.getStateFromMeta(1));
				}
			}
			blocksGenerated++;
		}
		return blocksGenerated;
	}
	@Override
	protected void decoratePlatform(World worldIn, BlockPos pos){
		if(Blocks.cactus.canPlaceBlockAt(worldIn, pos.up())){
			if(Math.random() < .08){
				//Reds
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