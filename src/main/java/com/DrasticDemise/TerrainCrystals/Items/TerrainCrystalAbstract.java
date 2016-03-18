package com.DrasticDemise.TerrainCrystals.Items;

import java.util.ArrayList;

import com.DrasticDemise.TerrainCrystals.ConfigurationFile;

import net.minecraft.client.resources.model.ModelResourceLocation;
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
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public abstract class TerrainCrystalAbstract extends Item{

	@Override
	public abstract ItemStack onItemRightClick(ItemStack itemStackIn, World worldIn, EntityPlayer playerIn);
	//Each class needs to provide its own platform makeup.
	protected abstract int generateInWorld(BlockPos pos, World worldIn, EntityPlayer playerIn, int blocksGenerated, BiomeGenBase desiredBiome, boolean changeBiome);
		
	//Each class needs to provide its own decoration rules
	abstract void decoratePlatform(World worldIn, BlockPos pos);
	
	public ItemStack gatherBlockGenList(ItemStack itemStackIn, World worldIn, EntityPlayer playerIn, int diameter, BiomeGenBase desiredBiome, Boolean changeBiome){
		int blocksGenerated = 0;
		if(!worldIn.isRemote){
			int posX = MathHelper.floor_double(playerIn.posX); 
			int posY = MathHelper.floor_double(playerIn.posY);
			int posZ = MathHelper.floor_double(playerIn.posZ);
			int center;
			double radius = diameter/2.0;
			if(diameter%2 != 0){
				center = (int) (radius + 0.5);
			}else{
				center = (int) (radius);
			}
			int offsetXFirstHalf = (int) (posX + radius);
			//Not sure why this has to be offset by 1 extra, but it does.
			int offsetXSecondHalf = (int) (posX - radius + 1);
			int yDown = 1;
			int fakeCenter = center;
			ArrayList<BlockPos> posList = new ArrayList<BlockPos>(68);
			for(int i = 0; i < (fakeCenter); i ++){
				//Creates a circle and fills it
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
				blocksGenerated = generateSpike(posList, worldIn, playerIn, blocksGenerated, itemStackIn, desiredBiome, changeBiome);
			}
		}
		itemStackIn.damageItem(blocksGenerated, playerIn);
		return itemStackIn;
	}
	public int generateSpike(ArrayList<BlockPos> posList, World worldIn, EntityPlayer playerIn, int blocksGenerated, ItemStack itemStackIn, BiomeGenBase desiredBiome, boolean changeBiome){
		ArrayList<BlockPos> recursiveList = new ArrayList<BlockPos>();
		for(BlockPos pos : posList){
			int surroundingBlocks = 0;
			
				blocksGenerated = generateInWorld(pos, worldIn, playerIn, blocksGenerated, desiredBiome, changeBiome);
				
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
					blocksGenerated = generateInWorld(pos.down(), worldIn, playerIn, blocksGenerated, desiredBiome, changeBiome);
					recursiveList.add(pos.down());
				}
			}
		if(!recursiveList.isEmpty()){
			blocksGenerated = generateSpike(recursiveList, worldIn, playerIn, blocksGenerated, itemStackIn, desiredBiome, changeBiome);
		}
		return blocksGenerated;
	}
	//Code taken from World Edit by Skq89
	//https://goo.gl/iEi0oU
	protected boolean setBiome(World worldIn, BlockPos position, BiomeGenBase desiredBiome, Boolean changeBiome) {
        if(changeBiome){
			Chunk chunk = worldIn.getChunkFromBlockCoords(position);
	       // BiomeGenBase desiredBiome = BiomeGenBase.desert;
	        if ((chunk != null) && (chunk.isLoaded())) {
	        	if(worldIn.getChunkFromBlockCoords(position).getBiome(position, worldIn.getWorldChunkManager()).biomeID != desiredBiome.biomeID){
	        		chunk.getBiomeArray()[((position.getZ() & 0xF) << 4 | position.getX() & 0xF)] = (byte) desiredBiome.biomeID;
		            return true;
	        	}
	        }
        }
        return false;
    }
	@SideOnly(Side.CLIENT)
    public void initModel() {
        ModelLoader.setCustomModelResourceLocation(this, 0, new ModelResourceLocation(getRegistryName(), "inventory"));
    }
}
