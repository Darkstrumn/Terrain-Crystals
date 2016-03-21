package com.DrasticDemise.TerrainCrystals.Items;

import java.util.ArrayList;
import java.util.Random;

import com.DrasticDemise.TerrainCrystals.ConfigurationFile;

import net.minecraft.block.IGrowable;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.resources.model.ModelResourceLocation;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.BlockPos;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.chunk.Chunk;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class TerrainCrystalEnd extends TerrainCrystalAbstract{
		public TerrainCrystalEnd(){
			setUnlocalizedName("terrainCrystalEnd");
			setRegistryName("terrainCrystalEnd");
			setCreativeTab(CreativeTabs.tabBlock);
			setHarvestLevel("stone", 0);
			setMaxStackSize(1);
			setMaxDamage(ConfigurationFile.endCrystalDurability);
	        GameRegistry.registerItem(this);
		}
		@Override
		public ItemStack onItemRightClick(ItemStack itemStackIn, World worldIn, EntityPlayer playerIn) {
			return super.gatherBlockGenList(itemStackIn, worldIn, playerIn, ConfigurationFile.endCrystalDiameter, BiomeGenBase.sky, ConfigurationFile.endCrystalChangesBiome);
		}
		@Override
		protected int generateBlocksInWorld(BlockPos pos, World worldIn, EntityPlayer playerIn, int blocksGenerated,
				BiomeGenBase desiredBiome, boolean changeBiome){
			if(worldIn.getBlockState(pos) == Blocks.air.getDefaultState() && pos.getY() > 1){
				int posY = MathHelper.floor_double(playerIn.posY);
				if(posY - pos.getY() == 1){
					worldIn.setBlockState(pos, Blocks.end_stone.getDefaultState());
					decoratePlatform(worldIn, pos);
					super.setBiome(worldIn, pos, BiomeGenBase.sky, ConfigurationFile.endCrystalChangesBiome);
					blocksGenerated++;
				}else{
					worldIn.setBlockState(pos, Blocks.end_stone.getDefaultState());
					blocksGenerated++;
				}
			}
			return blocksGenerated;
		}
		@Override
		protected void decoratePlatform(World worldIn, BlockPos pos){
			//Chance to create a pillar
			if(ConfigurationFile.endCrystalGenerateObsidianSpikes){
				if(Math.random() < .02){
					worldIn.setBlockState(pos, Blocks.obsidian.getDefaultState());
					worldIn.setBlockState(pos.up(), Blocks.obsidian.getDefaultState());
					if(Math.random() < .9){
						worldIn.setBlockState(pos.up(2), Blocks.obsidian.getDefaultState());
						if(Math.random() < 0.9){
							worldIn.setBlockState(pos.up(3), Blocks.obsidian.getDefaultState());
							if(Math.random() < 0.7){
								worldIn.setBlockState(pos.up(4), Blocks.obsidian.getDefaultState());
								if(Math.random() < .75){
									worldIn.setBlockState(pos.up(4).north(), Blocks.obsidian.getDefaultState());
								}
								if(Math.random() < .75){
									worldIn.setBlockState(pos.up(4).east(), Blocks.obsidian.getDefaultState());
								}
								if(Math.random() < .75){
									worldIn.setBlockState(pos.up(4).south(), Blocks.obsidian.getDefaultState());
								}
								if(Math.random() < .75){
									worldIn.setBlockState(pos.up(4).west(), Blocks.obsidian.getDefaultState());
								}
								if(Math.random() < .25){
									worldIn.setBlockState(pos.up(5), Blocks.obsidian.getDefaultState());
									worldIn.setBlockState(pos.up(6), Blocks.obsidian.getDefaultState());
									if(Math.random() < .50){
										if(Math.random() < .75){
											worldIn.setBlockState(pos.up(5).north(), Blocks.obsidian.getDefaultState());
											if(Math.random() < .75){
												worldIn.setBlockState(pos.up(6).north(), Blocks.obsidian.getDefaultState());
											}
											if(Math.random() < .75){
												worldIn.setBlockState(pos.up(6).east(), Blocks.obsidian.getDefaultState());
											}
											if(Math.random() < .75){
												worldIn.setBlockState(pos.up(6).south(), Blocks.obsidian.getDefaultState());
											}
											if(Math.random() < .75){
												worldIn.setBlockState(pos.up(6).west(), Blocks.obsidian.getDefaultState());
											}
										}
										if(Math.random() < .75){
											worldIn.setBlockState(pos.up(5).east(), Blocks.obsidian.getDefaultState());
											if(Math.random() < .75){
												worldIn.setBlockState(pos.up(6).north(), Blocks.obsidian.getDefaultState());
											}
											if(Math.random() < .75){
												worldIn.setBlockState(pos.up(6).east(), Blocks.obsidian.getDefaultState());
											}
											if(Math.random() < .75){
												worldIn.setBlockState(pos.up(6).south(), Blocks.obsidian.getDefaultState());
											}
											if(Math.random() < .75){
												worldIn.setBlockState(pos.up(6).west(), Blocks.obsidian.getDefaultState());
											}
										}
										if(Math.random() < .75){
											worldIn.setBlockState(pos.up(5).south(), Blocks.obsidian.getDefaultState());
											if(Math.random() < .75){
												worldIn.setBlockState(pos.up(6).north(), Blocks.obsidian.getDefaultState());
											}
											if(Math.random() < .75){
												worldIn.setBlockState(pos.up(6).east(), Blocks.obsidian.getDefaultState());
											}
											if(Math.random() < .75){
												worldIn.setBlockState(pos.up(6).south(), Blocks.obsidian.getDefaultState());
											}
											if(Math.random() < .75){
												worldIn.setBlockState(pos.up(6).west(), Blocks.obsidian.getDefaultState());
											}
										}
										if(Math.random() < .75){
											worldIn.setBlockState(pos.up(5).west(), Blocks.obsidian.getDefaultState());
											if(Math.random() < .75){
												worldIn.setBlockState(pos.up(6).north(), Blocks.obsidian.getDefaultState());
											}
											if(Math.random() < .75){
												worldIn.setBlockState(pos.up(6).east(), Blocks.obsidian.getDefaultState());
											}
											if(Math.random() < .75){
												worldIn.setBlockState(pos.up(6).south(), Blocks.obsidian.getDefaultState());
											}
											if(Math.random() < .75){
												worldIn.setBlockState(pos.up(6).west(), Blocks.obsidian.getDefaultState());
											}
										}
										if(Math.random() < .5){
											if(Math.random() < .75){
												worldIn.setBlockState(pos.up(6).north(), Blocks.obsidian.getDefaultState());
											}
											if(Math.random() < .75){
												worldIn.setBlockState(pos.up(6).east(), Blocks.obsidian.getDefaultState());
											}
											if(Math.random() < .75){
												worldIn.setBlockState(pos.up(6).south(), Blocks.obsidian.getDefaultState());
											}
											if(Math.random() < .75){
												worldIn.setBlockState(pos.up(6).west(), Blocks.obsidian.getDefaultState());
											}
										}
									}
								}
							}
						}
					}
				}
			}
		}
	}
