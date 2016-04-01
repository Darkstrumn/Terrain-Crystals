package com.DrasticDemise.TerrainCrystals.Items;

import java.util.Random;
import java.util.concurrent.ExecutionException;

import com.DrasticDemise.TerrainCrystals.ConfigurationFile;

import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.block.IGrowable;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Biomes;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraftforge.fml.common.registry.GameRegistry;

public class TerrainCrystalJungle extends TerrainCrystalAbstract{
	public TerrainCrystalJungle(){
		setUnlocalizedName("terrainCrystalJungle");
		setRegistryName("terrainCrystalJungle");
		setCreativeTab(CreativeTabs.tabBlock);
		setHarvestLevel("stone", 0);
		setMaxStackSize(1);
		setMaxDamage(ConfigurationFile.jungleCrystalDurability);
        GameRegistry.registerItem(this);
	}
	
	
	@Override
	public ActionResult<ItemStack> onItemRightClick(ItemStack itemStackIn, World worldIn, EntityPlayer playerIn, EnumHand hand){
		super.gatherBlockGenList(itemStackIn, worldIn, playerIn, ConfigurationFile.jungleCrystalDiameter, Biomes.jungle, ConfigurationFile.jungleCrystalChangesBiome);
		return new ActionResult(EnumActionResult.PASS, itemStackIn);
	}

	@Override
	protected int generateBlocksInWorld(BlockPos pos, World worldIn, EntityPlayer playerIn, int blocksGenerated,
			BiomeGenBase desiredBiome, boolean changeBiome) {
		if((eligibleStateLocation(worldIn.getBlockState(pos), pos))
				&& pos.getY() > 1){
			int posY = MathHelper.floor_double(playerIn.posY);
			if(posY - pos.getY() == 1){
				worldIn.setBlockState(pos, Blocks.grass.getDefaultState());
				super.setBiome(worldIn, pos, desiredBiome, changeBiome);
				decoratePlatform(worldIn, pos);
			}else{
				worldIn.setBlockState(pos, Blocks.dirt.getDefaultState());
			}
			blocksGenerated++;
		}
		return blocksGenerated;
	}

	@Override
	void decoratePlatform(World worldIn, BlockPos pos) {
		//Generate bush things
		if(Math.random() < 0.01 && ConfigurationFile.jungleCrystalGeneratesBushes){
			worldIn.setBlockState(pos.up(), Blocks.leaves.getDefaultState());
			worldIn.setBlockState(pos.up(2), Blocks.leaves.getDefaultState());
			worldIn.setBlockState(pos.up().east(), Blocks.leaves.getDefaultState());
			worldIn.setBlockState(pos.up().north(), Blocks.leaves.getDefaultState());
			worldIn.setBlockState(pos.up().south(), Blocks.leaves.getDefaultState());
			worldIn.setBlockState(pos.up().west(), Blocks.leaves.getDefaultState());
			if(Math.random() < .3){
				worldIn.setBlockState(pos.up().north().east(), Blocks.leaves.getDefaultState());
				worldIn.setBlockState(pos.up().north().west(), Blocks.leaves.getDefaultState());
				worldIn.setBlockState(pos.up().south().east(), Blocks.leaves.getDefaultState());
				worldIn.setBlockState(pos.up().south().west(), Blocks.leaves.getDefaultState());
			}
			worldIn.setBlockState(pos, Blocks.log.getStateFromMeta(3));
		}
		//Decorate with tall grass
		if(Math.random() < 0.08){
			IBlockState state = worldIn.getBlockState(pos);
			Random rand = new Random();
			//Try-catching our worries away!
			try{
				if (state.getBlock() instanceof IGrowable)
				{
					IGrowable growable = (IGrowable) state.getBlock();
					if (growable.canGrow(worldIn, pos, state, worldIn.isRemote))
					{
						worldIn.playAuxSFX(2005, pos, 0);
						growable.grow(worldIn, rand, pos, state);
					}
				}
				if(Math.random() < 0.05 && ConfigurationFile.jungleCrystalGeneratesMelon){
					if(worldIn.getBlockState(pos.up()) == Blocks.air.getDefaultState())
						worldIn.setBlockState(pos.up(), Blocks.melon_block.getDefaultState());
				}
			}catch(IllegalArgumentException e){
				//System.out.println("Caught an error in tree growing! Tossing it out, goodbye chunk error!");
				return;
			}
		}
		
		if(Math.random() < 0.07 && ConfigurationFile.jungleCrystalGeneratesTrees){
			growTree(worldIn, pos);
		}
	}
	private void growTree(World worldIn, BlockPos pos) {
		try{
			if (Blocks.sapling.canPlaceBlockAt(worldIn, pos.up())){
				worldIn.setBlockState(pos.up(), Blocks.sapling.getStateFromMeta(3));
				try{
					IGrowable growable = (IGrowable) worldIn.getBlockState(pos.up()).getBlock();
					Random rand = new Random();	
					int attemptCap = 0;
					while(worldIn.getBlockState(pos.up()) != Blocks.log.getStateFromMeta(3) && attemptCap < 10){
						growable.grow(worldIn, rand, pos.up(), worldIn.getBlockState(pos.up()));
						attemptCap++;
					}
					if(attemptCap > 8){
						worldIn.setBlockState(pos.up(), Blocks.air.getDefaultState());
					}
					generateCocoaBeans(worldIn, pos.up());
				}catch(ClassCastException e){
					
				}
				
			}
		}catch(IllegalArgumentException e){
			
		}
	}
	private boolean safelyExpandPlatform(World worldIn, BlockPos pos){
		if(worldIn.getBlockState(pos) == Blocks.air.getDefaultState() || worldIn.getBlockState(pos) == Blocks.grass.getDefaultState()
				|| worldIn.getBlockState(pos) == Blocks.dirt.getDefaultState()){
			worldIn.setBlockState(pos, Blocks.grass.getDefaultState());
			return true;
		}
		return false;
	}
	private void generateCocoaBeans(World worldIn, BlockPos pos){
		int counter = 2;
		while((worldIn.getBlockState(pos.up(counter)) == Blocks.log.getStateFromMeta(3)) && counter < 16 && ConfigurationFile.jungleCrystalGeneratesCocoa){
			if(worldIn.getBlockState(pos.up(counter).north()) != Blocks.leaves.getStateFromMeta(3)){
				if(Math.random() < .05){
					if(Math.random() < 0.25){
						if(worldIn.getBlockState(pos.up().north()) == Blocks.air.getDefaultState())
							worldIn.setBlockState(pos.up().north(), Blocks.cocoa.getStateFromMeta(4));
					}else if(Math.random() < 0.50){
						if(worldIn.getBlockState(pos.up().south()) == Blocks.air.getDefaultState())
							worldIn.setBlockState(pos.up().south(), Blocks.cocoa.getStateFromMeta(2));
					}else if(Math.random() < 0.75){
						if(worldIn.getBlockState(pos.up().east()) == Blocks.air.getDefaultState())
							worldIn.setBlockState(pos.up().east(), Blocks.cocoa.getStateFromMeta(1));
					}else{
						if(worldIn.getBlockState(pos.up().west()) == Blocks.air.getDefaultState())
							worldIn.setBlockState(pos.up().west(), Blocks.cocoa.getStateFromMeta(3));
					}
				}
			}
			counter++;
		}
	}
	//Broken. Vines place in the wrong direction on half of the island.
	private void generateVines(World worldIn, BlockPos pos) {
		if(worldIn.getBlockState(pos.north()) == Blocks.air.getDefaultState()){
			//System.out.println("entered northCheck");
			if(worldIn.getBlockState(pos.north()) != Blocks.grass.getDefaultState())
				worldIn.setBlockState(pos.north(), Blocks.vine.getDefaultState());
		}
		
		if(worldIn.getBlockState(pos.east()) == Blocks.air.getDefaultState()){
			worldIn.setBlockState(pos.east(), Blocks.vine.getDefaultState());
		}
		
		if(worldIn.getBlockState(pos.south()) == Blocks.air.getDefaultState()){
			worldIn.setBlockState(pos.south(), Blocks.vine.getStateFromMeta(0));
		}
		
		if(worldIn.getBlockState(pos.west()) == Blocks.air.getDefaultState()){
			worldIn.setBlockState(pos.west(), Blocks.vine.getDefaultState());
		}
	}
}