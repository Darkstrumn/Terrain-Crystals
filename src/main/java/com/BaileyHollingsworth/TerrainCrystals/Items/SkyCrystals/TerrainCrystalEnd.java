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

public class TerrainCrystalEnd extends TerrainCrystalAbstract{
		public TerrainCrystalEnd(){
			super("End");
		}
		public TerrainCrystalEnd(boolean isGroundCrystal){
			super("End", isGroundCrystal);
		}
		@Override
		protected int generateBlocksInWorld(BlockPos pos, World worldIn, EntityPlayer playerIn, int blocksGenerated,
											Biome desiredBiome, boolean changeBiome){
			if(eligibleStateLocation(worldIn, pos)){
				int posY = MathHelper.floor_double(playerIn.posY);
				if(posY - pos.getY() == 1){
					worldIn.setBlockState(pos, Blocks.END_STONE.getDefaultState());
					decoratePlatform(worldIn, pos);
					super.setBiome(worldIn, pos, desiredBiome, changeBiome);
				}else{
					worldIn.setBlockState(pos, Blocks.END_STONE.getDefaultState());
				}
				blocksGenerated += 1;
			}
			return blocksGenerated;
		}
		
		@Override
		protected void decoratePlatform(World worldIn, BlockPos pos){
			//Chance to create a pillar
			if(ConfigurationFile.endCrystalGenerateObsidianSpikes && spacedFarEnough(worldIn, pos.up())){
				if(Math.random() < .02){
					worldIn.setBlockState(pos, Blocks.OBSIDIAN.getDefaultState());
					worldIn.setBlockState(pos.up(), Blocks.OBSIDIAN.getDefaultState());
					if(Math.random() < .9){
						worldIn.setBlockState(pos.up(2), Blocks.OBSIDIAN.getDefaultState());
						if(Math.random() < 0.9){
							worldIn.setBlockState(pos.up(3), Blocks.OBSIDIAN.getDefaultState());
							if(Math.random() < 0.7){
								worldIn.setBlockState(pos.up(4), Blocks.OBSIDIAN.getDefaultState());
								if(Math.random() < .75){
									worldIn.setBlockState(pos.up(4).north(), Blocks.OBSIDIAN.getDefaultState());
								}
								if(Math.random() < .75){
									worldIn.setBlockState(pos.up(4).east(), Blocks.OBSIDIAN.getDefaultState());
								}
								if(Math.random() < .75){
									worldIn.setBlockState(pos.up(4).south(), Blocks.OBSIDIAN.getDefaultState());
								}
								if(Math.random() < .75){
									worldIn.setBlockState(pos.up(4).west(), Blocks.OBSIDIAN.getDefaultState());
								}
								if(Math.random() < .25){
									worldIn.setBlockState(pos.up(5), Blocks.OBSIDIAN.getDefaultState());
									worldIn.setBlockState(pos.up(6), Blocks.OBSIDIAN.getDefaultState());
									if(Math.random() < .50){
										if(Math.random() < .75){
											worldIn.setBlockState(pos.up(5).north(), Blocks.OBSIDIAN.getDefaultState());
											if(Math.random() < .75){
												worldIn.setBlockState(pos.up(6).north(), Blocks.OBSIDIAN.getDefaultState());
											}
											if(Math.random() < .75){
												worldIn.setBlockState(pos.up(6).east(), Blocks.OBSIDIAN.getDefaultState());
											}
											if(Math.random() < .75){
												worldIn.setBlockState(pos.up(6).south(), Blocks.OBSIDIAN.getDefaultState());
											}
											if(Math.random() < .75){
												worldIn.setBlockState(pos.up(6).west(), Blocks.OBSIDIAN.getDefaultState());
											}
										}
										if(Math.random() < .75){
											worldIn.setBlockState(pos.up(5).east(), Blocks.OBSIDIAN.getDefaultState());
											if(Math.random() < .75){
												worldIn.setBlockState(pos.up(6).north(), Blocks.OBSIDIAN.getDefaultState());
											}
											if(Math.random() < .75){
												worldIn.setBlockState(pos.up(6).east(), Blocks.OBSIDIAN.getDefaultState());
											}
											if(Math.random() < .75){
												worldIn.setBlockState(pos.up(6).south(), Blocks.OBSIDIAN.getDefaultState());
											}
											if(Math.random() < .75){
												worldIn.setBlockState(pos.up(6).west(), Blocks.OBSIDIAN.getDefaultState());
											}
										}
										if(Math.random() < .75){
											worldIn.setBlockState(pos.up(5).south(), Blocks.OBSIDIAN.getDefaultState());
											if(Math.random() < .75){
												worldIn.setBlockState(pos.up(6).north(), Blocks.OBSIDIAN.getDefaultState());
											}
											if(Math.random() < .75){
												worldIn.setBlockState(pos.up(6).east(), Blocks.OBSIDIAN.getDefaultState());
											}
											if(Math.random() < .75){
												worldIn.setBlockState(pos.up(6).south(), Blocks.OBSIDIAN.getDefaultState());
											}
											if(Math.random() < .75){
												worldIn.setBlockState(pos.up(6).west(), Blocks.OBSIDIAN.getDefaultState());
											}
										}
										if(Math.random() < .75){
											worldIn.setBlockState(pos.up(5).west(), Blocks.OBSIDIAN.getDefaultState());
											if(Math.random() < .75){
												worldIn.setBlockState(pos.up(6).north(), Blocks.OBSIDIAN.getDefaultState());
											}
											if(Math.random() < .75){
												worldIn.setBlockState(pos.up(6).east(), Blocks.OBSIDIAN.getDefaultState());
											}
											if(Math.random() < .75){
												worldIn.setBlockState(pos.up(6).south(), Blocks.OBSIDIAN.getDefaultState());
											}
											if(Math.random() < .75){
												worldIn.setBlockState(pos.up(6).west(), Blocks.OBSIDIAN.getDefaultState());
											}
										}
										if(Math.random() < .5){
											if(Math.random() < .75){
												worldIn.setBlockState(pos.up(6).north(), Blocks.OBSIDIAN.getDefaultState());
											}
											if(Math.random() < .75){
												worldIn.setBlockState(pos.up(6).east(), Blocks.OBSIDIAN.getDefaultState());
											}
											if(Math.random() < .75){
												worldIn.setBlockState(pos.up(6).south(), Blocks.OBSIDIAN.getDefaultState());
											}
											if(Math.random() < .75){
												worldIn.setBlockState(pos.up(6).west(), Blocks.OBSIDIAN.getDefaultState());
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
		@Override
		protected Boolean changesBiomeOnUse() {
			return ConfigurationFile.endCrystalChangesBiome;
		}
		@Override
		protected Biome getBiomeType() {
			return Biomes.SKY;
		}
		@Override
		protected int getDiameter() {
			return ConfigurationFile.endCrystalDiameter;
		}
		@Override
		protected int getDurability() {
			return ConfigurationFile.endCrystalDurability;
		}
	}
