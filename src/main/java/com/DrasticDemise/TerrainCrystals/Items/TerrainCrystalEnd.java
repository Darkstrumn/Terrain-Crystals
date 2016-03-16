package com.DrasticDemise.TerrainCrystals.Items;

import java.util.ArrayList;
import java.util.Random;

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
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class TerrainCrystalEnd extends Item{
		public TerrainCrystalEnd(){
			setUnlocalizedName("terrainCrystalEnd");
			setRegistryName("terrainCrystalEnd");
			setCreativeTab(CreativeTabs.tabBlock);
			setHarvestLevel("stone", 0);
	        GameRegistry.registerItem(this);
		}
		@Override
		public ItemStack onItemRightClick(ItemStack itemStackIn, World worldIn, EntityPlayer playerIn){
			int blocksGenerated = 0;
			if(!worldIn.isRemote){
				int posX = MathHelper.floor_double(playerIn.posX);
				int posY = MathHelper.floor_double(playerIn.posY);
				int posZ = MathHelper.floor_double(playerIn.posZ);
				int center;
				int diameter = 11;
				double radius = diameter/2.0;
				if(diameter%2 != 0){
					center = (int) (radius + 0.5);
				}else{
					center = (int) (radius);
				}
				int offsetXFirstHalf = (int) (posX + radius);
				//Not sure why this has to be offset by 1 extra, but it does.
				int offsetXSecondHalf = (int) (posX - radius + 1);
				//Generates the first half
				int yDown = 1;
				int fakeCenter = center;
				ArrayList<BlockPos> posList = new ArrayList<BlockPos>(68);
				for(int i = 0; i < (fakeCenter); i ++){
					//Creates the outline of the circle
					//Each shell is respective to its quadrant
					//These are added in the loop already
					//BlockPos shellOne = new BlockPos(offsetXFirstHalf - i, posY-yDown, posZ - i);
					//BlockPos shellTwo = new BlockPos(offsetXFirstHalf - i, posY - yDown, posZ + i);
					for(int placeInwards = 0; placeInwards < i+1; placeInwards++){
						//Fills across the circle
						BlockPos fillShellOne = new BlockPos(offsetXFirstHalf - i, posY - yDown, posZ - i + placeInwards);
						posList.add(fillShellOne);
						BlockPos fillShellTwo = new BlockPos(offsetXFirstHalf - i, posY - yDown, posZ + i - placeInwards);
						posList.add(fillShellTwo);
					}
				}
				//Generates the second half
				for(int i = 0; i < (center); i ++){
					BlockPos shellThree = new BlockPos(offsetXSecondHalf + i, posY - 1, posZ  + i);
					BlockPos shellFour = new BlockPos(offsetXSecondHalf + i, posY - 1, posZ - i);
					posList.add(shellThree); 
					posList.add(shellFour);
					
					for(int placeInwards = 0; placeInwards < i + 1; placeInwards++){
						BlockPos fillShellThree = new BlockPos(offsetXSecondHalf + i, posY - 1, posZ + i - placeInwards);
						BlockPos fillShellFour = new BlockPos(offsetXSecondHalf + i, posY - 1, posZ - i + placeInwards);
						posList.add(fillShellThree);
						posList.add(fillShellFour);
					}
				}
				for(BlockPos p : posList){
					blocksGenerated = generateSpike(posList, worldIn, playerIn, blocksGenerated);
				}
			}
			//System.out.println(blocksGenerated);
			return itemStackIn;
		}
		public int generateSpike(ArrayList<BlockPos> posList, World worldIn, EntityPlayer playerIn, int blocksGenerated){
			ArrayList<BlockPos> recursiveList = new ArrayList<BlockPos>();
			for(BlockPos pos : posList){
				int surroundingBlocks = 0;
				
					blocksGenerated = generateInWorld(pos, worldIn, playerIn, blocksGenerated);
					
					if(worldIn.getBlockState(pos.north()) != Blocks.air.getDefaultState()){
						//System.out.println("entered northCheck");
						surroundingBlocks++;
					}
					
					if(worldIn.getBlockState(pos.east()) != Blocks.air.getDefaultState()){
						surroundingBlocks++;
					}
					
					if(worldIn.getBlockState(pos.south()) != Blocks.air.getDefaultState()){
						surroundingBlocks++;
					}
					
					if(worldIn.getBlockState(pos.west()) != Blocks.air.getDefaultState()){
						surroundingBlocks++;
					}
					if(surroundingBlocks >= 3 || Math.random() < 0.05){
						blocksGenerated = generateInWorld(pos.down(), worldIn, playerIn, blocksGenerated);
						recursiveList.add(pos.down());
					}
				}
			if(!recursiveList.isEmpty()){
				blocksGenerated = generateSpike(recursiveList, worldIn, playerIn, blocksGenerated);
			}
			return blocksGenerated;
		}
		private int generateInWorld(BlockPos pos, World worldIn, EntityPlayer playerIn, int blocksGenerated){
			if(worldIn.getBlockState(pos) == Blocks.air.getDefaultState()){
				int posY = MathHelper.floor_double(playerIn.posY);
				if(posY - pos.getY() == 1){
					worldIn.setBlockState(pos, Blocks.end_stone.getDefaultState());
					decorateEnd(worldIn, pos);
					blocksGenerated++;
				}else{
					worldIn.setBlockState(pos, Blocks.end_stone.getDefaultState());
					blocksGenerated++;
				}
			}
			return blocksGenerated;
		}
		private void decorateEnd(World worldIn, BlockPos pos){
			//Chance to create a pillar
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
		@SideOnly(Side.CLIENT)
	    public void initModel() {
	        ModelLoader.setCustomModelResourceLocation(this, 0, new ModelResourceLocation(getRegistryName(), "inventory"));
	    }
	}