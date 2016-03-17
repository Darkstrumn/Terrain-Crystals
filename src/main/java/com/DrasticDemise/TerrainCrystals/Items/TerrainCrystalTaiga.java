package com.DrasticDemise.TerrainCrystals.Items;

import java.util.ArrayList;
import java.util.Random;

import com.DrasticDemise.TerrainCrystals.ConfigurationFile;

import net.minecraft.block.IGrowable;
import net.minecraft.block.state.BlockState;
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

public class TerrainCrystalTaiga extends Item{
	public TerrainCrystalTaiga(){
		setUnlocalizedName("terrainCrystalTaiga");
		setRegistryName("terrainCrystalTaiga");
		setCreativeTab(CreativeTabs.tabBlock);
		setHarvestLevel("stone", 0);
		setMaxStackSize(1);
		setMaxDamage(ConfigurationFile.taigaCrystalDurability);
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
			int diameter = ConfigurationFile.taigaCrystalDiameter;
			double radius = diameter/2.0;
			BlockPos playerLocation = new BlockPos(posX, posY, posZ);
			setBiome(worldIn, playerLocation);
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
		itemStackIn.damageItem(blocksGenerated, playerIn);
		return itemStackIn;
	}
	//Code taken from World Edit by Skq89
		//https://goo.gl/iEi0oU
	 public boolean setBiome(World worldIn, BlockPos position) {
	        Chunk chunk = worldIn.getChunkFromBlockCoords(position);
	        BiomeGenBase desiredBiome = BiomeGenBase.coldTaiga;
	        if ((chunk != null) && (chunk.isLoaded())) {
	        	if(worldIn.getChunkFromBlockCoords(position).getBiome(position, worldIn.getWorldChunkManager()).biomeID != desiredBiome.biomeID){
	        		chunk.getBiomeArray()[((position.getZ() & 0xF) << 4 | position.getX() & 0xF)] = (byte) desiredBiome.biomeID;
		            return true;
	        	}
	        }
	        return false;
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
				if(Math.random() < .4){
					if(Math.random() < .5){
						worldIn.setBlockState(pos, Blocks.dirt.getStateFromMeta(1));
					}else{
						worldIn.setBlockState(pos, Blocks.grass.getDefaultState());
					}
				}else{
					worldIn.setBlockState(pos, Blocks.dirt.getStateFromMeta(2));
				}
				if(ConfigurationFile.taigaCrystalGeneratesTrees){
					growTree(worldIn, pos);
				}
				if(ConfigurationFile.taigaCrystalChangesBiome){
					setBiome(worldIn, pos);
				}
				
				blocksGenerated++;
			}else{
				worldIn.setBlockState(pos, Blocks.dirt.getDefaultState());
				blocksGenerated++;
			}
		}
		return blocksGenerated;
	}
	private void growTree(World worldIn, BlockPos pos){
		IBlockState state  = worldIn.getBlockState(pos.up());
		if (Blocks.sapling.canPlaceBlockAt(worldIn, pos.up())){
			if(Math.random() < .05){
				worldIn.setBlockState(pos.up(), Blocks.sapling.getStateFromMeta(1));
			}
			if(state != Blocks.air.getDefaultState() && state != Blocks.dirt.getStateFromMeta(1)){
				IGrowable growable = (IGrowable) worldIn.getBlockState(pos.up()).getBlock();
				Random rand = new Random();	
				while(worldIn.getBlockState(pos.up()) != Blocks.log.getDefaultState()){
					growable.grow(worldIn, rand, pos.up(), worldIn.getBlockState(pos.up()));
				}
			}
		}
	}
	@SideOnly(Side.CLIENT)
    public void initModel() {
        ModelLoader.setCustomModelResourceLocation(this, 0, new ModelResourceLocation(getRegistryName(), "inventory"));
    }
}

