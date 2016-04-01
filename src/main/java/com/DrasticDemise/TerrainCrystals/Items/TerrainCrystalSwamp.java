package com.DrasticDemise.TerrainCrystals.Items;

import java.util.ArrayList;
import java.util.Random;

import com.DrasticDemise.TerrainCrystals.ConfigurationFile;

import net.minecraft.block.IGrowable;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraftforge.fml.common.registry.GameRegistry;

public class TerrainCrystalSwamp extends TerrainCrystalAbstract{
	public TerrainCrystalSwamp(){
		setUnlocalizedName("terrainCrystalSwamp");
		setRegistryName("terrainCrystalSwamp");
		setCreativeTab(CreativeTabs.tabBlock);
		setHarvestLevel("stone", 0);
		setMaxStackSize(1);
		setMaxDamage(ConfigurationFile.swampCrystalDurability);
        GameRegistry.registerItem(this);
	}

	@Override
	public ItemStack onItemRightClick(ItemStack itemStackIn, World worldIn, EntityPlayer playerIn) {
		return super.gatherBlockGenList(itemStackIn, worldIn, playerIn, ConfigurationFile.swampCrystalDiameter, BiomeGenBase.swampland, ConfigurationFile.swampCrystalChangesBiome);
	}

	@Override
	protected int generateBlocksInWorld(BlockPos pos, World worldIn, EntityPlayer playerIn, int blocksGenerated,
			BiomeGenBase desiredBiome, boolean changeBiome) {
		if(eligibleStateLocation(worldIn.getBlockState(pos), pos)){
			int posY = MathHelper.floor_double(playerIn.posY);
			if(posY - pos.getY() == 1){
				worldIn.setBlockState(pos, Blocks.grass.getDefaultState());
				setBiome(worldIn, pos, desiredBiome, changeBiome);
				decoratePlatform(worldIn, pos);
				blocksGenerated++;
			}else{
				worldIn.setBlockState(pos, Blocks.dirt.getDefaultState());
				blocksGenerated++;
			}
		}
		return blocksGenerated;
	}

	@Override
	void decoratePlatform(World worldIn, BlockPos pos) {
		if(Math.random() < 0.02 && ConfigurationFile.swampCrystalGensWater){
			if(Math.random() < 0.5){
				worldIn.setBlockState(pos, Blocks.water.getDefaultState());
				worldIn.setBlockState(pos.north(), Blocks.water.getDefaultState());
				worldIn.setBlockState(pos.north().east(), Blocks.water.getDefaultState());
				worldIn.setBlockState(pos.east(), Blocks.water.getDefaultState());
				if(Math.random() < 0.30){
						if(Math.random() < 0.25){
							worldIn.setBlockState(pos.up(), Blocks.waterlily.getDefaultState());
						}else if(Math.random() < 0.50){
							worldIn.setBlockState(pos.north().up(), Blocks.waterlily.getDefaultState());
						}else if(Math.random() < 0.75){
							worldIn.setBlockState(pos.north().up().east(), Blocks.waterlily.getDefaultState());
						}else if(Math.random() < 1.0){
							worldIn.setBlockState(pos.east().up(), Blocks.waterlily.getDefaultState());
						}
				}
				if(Math.random() < .10 && ConfigurationFile.swampCrystalGensClay){
					worldIn.setBlockState(pos.down(), Blocks.clay.getDefaultState());
					worldIn.setBlockState(pos.down(2).east(), Blocks.clay.getDefaultState());
					worldIn.setBlockState(pos.down().west(2), Blocks.clay.getDefaultState());
					worldIn.setBlockState(pos.north().down(), Blocks.clay.getDefaultState());
					worldIn.setBlockState(pos.north().east().down(), Blocks.clay.getDefaultState());
					worldIn.setBlockState(pos.east().down(), Blocks.clay.getDefaultState());
				}
				if(Math.random() < 0.5){
					if(Math.random() < 0.25){
						worldIn.setBlockState(pos.north(2).down(), Blocks.dirt.getDefaultState());
						worldIn.setBlockState(pos.north(2).east().down(), Blocks.dirt.getDefaultState());
						worldIn.setBlockState(pos.north(2), Blocks.sand.getDefaultState());
						worldIn.setBlockState(pos.north(2).east(), Blocks.sand.getDefaultState());
						plantSugarcane(worldIn, pos.north(2));
						plantSugarcane(worldIn, pos.north(2).east());
					}
					if(Math.random() < 0.25){
						worldIn.setBlockState(pos.down().south(), Blocks.dirt.getDefaultState());
						worldIn.setBlockState(pos.south().east().down(), Blocks.dirt.getDefaultState());
						worldIn.setBlockState(pos.south(), Blocks.sand.getDefaultState());
						worldIn.setBlockState(pos.south().east(), Blocks.sand.getDefaultState());
						plantSugarcane(worldIn, pos.south());
						plantSugarcane(worldIn, pos.south().east());
					}
					if(Math.random() < 0.45){
						worldIn.setBlockState(pos.down().east(2), Blocks.dirt.getDefaultState());
						worldIn.setBlockState(pos.east(2), Blocks.sand.getDefaultState());
						worldIn.setBlockState(pos.south().down().east(), Blocks.dirt.getDefaultState());
						worldIn.setBlockState(pos.south().east(), Blocks.sand.getDefaultState());
						plantSugarcane(worldIn, pos.east(2));
						plantSugarcane(worldIn,pos.south().east());
					}
				}
			}
		}
		if (Blocks.sapling.canPlaceBlockAt(worldIn, pos.up()) && Math.random() <= 0.01){
			worldIn.setBlockState(pos.up(), Blocks.sapling.getDefaultState());
			IGrowable growable = (IGrowable) worldIn.getBlockState(pos.up()).getBlock();
			Random rand = new Random();	
			int attemptCap = 0;
			while((worldIn.getBlockState(pos.up()) != Blocks.log.getDefaultState()) && attemptCap < 10){
				growable.grow(worldIn, rand, pos.up(), worldIn.getBlockState(pos.up()));
				attemptCap++;
			}
			//plantMushrooms(worldIn, pos);
		}
		try{
			if(Math.random() < 0.05){
				Random rand = new Random();
				IBlockState state = worldIn.getBlockState(pos);
					IGrowable growable = (IGrowable) state.getBlock();
					if (growable.canGrow(worldIn, pos, state, worldIn.isRemote))
					{
						worldIn.playAuxSFX(2005, pos, 0);
						growable.grow(worldIn, rand, pos, state);
					}
				}
		}catch(ClassCastException e){
			//System.out.println("Caught an error in tree growing! Tossing it out, goodbye chunk error!");
			return;
		}
	}
	
	
	//Unimplemented
	private void plantMushrooms(World worldIn, BlockPos pos) {
		ArrayList<BlockPos> m = new ArrayList<BlockPos>(11);
		m.add(pos);
		
		for(BlockPos pos1 : m)
		if(worldIn.getLight(pos1) < 13 && Blocks.red_mushroom.canBlockStay(worldIn, pos, Blocks.red_mushroom.getDefaultState())){
			if(Math.random() < .50){
				worldIn.setBlockState(pos1.up(), Blocks.red_mushroom.getDefaultState());
			}else{
				worldIn.setBlockState(pos1.up(), Blocks.brown_mushroom.getDefaultState());
			}
		}
		
	}

	private void plantSugarcane(World worldIn,BlockPos pos){
		worldIn.setBlockState(pos.up(), Blocks.reeds.getDefaultState());
		worldIn.setBlockState(pos.up(2), Blocks.reeds.getDefaultState());
		worldIn.setBlockState(pos.up(3), Blocks.reeds.getDefaultState());
	}
}
