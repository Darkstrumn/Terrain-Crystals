package com.DrasticDemise.TerrainCrystals.Items;

import java.util.ArrayList;
import java.util.HashSet;

import net.minecraft.block.state.IBlockState;
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
	public static HashSet replaceableBlockStates;
	
	/**
	 * Initializes the hashSet with block states that can be replaced by the platform.
	 */
	public static void initReplaceableBlocks(){
		replaceableBlockStates = new HashSet();
		replaceableBlockStates.add(Blocks.tallgrass.getDefaultState());
		replaceableBlockStates.add(Blocks.flowing_water.getDefaultState());
		replaceableBlockStates.add(Blocks.flowing_lava.getDefaultState());
		replaceableBlockStates.add(Blocks.red_flower.getDefaultState());
		replaceableBlockStates.add(Blocks.yellow_flower.getDefaultState());
		replaceableBlockStates.add(Blocks.red_mushroom.getDefaultState());
		replaceableBlockStates.add(Blocks.brown_mushroom.getDefaultState());
		replaceableBlockStates.add(Blocks.log.getStateFromMeta(3));
		replaceableBlockStates.add(Blocks.leaves.getStateFromMeta(3));
		replaceableBlockStates.add(Blocks.melon_block.getDefaultState());
		replaceableBlockStates.add(Blocks.cactus.getDefaultState());
		replaceableBlockStates.add(Blocks.air.getDefaultState());
		replaceableBlockStates.add(Blocks.sapling.getStateFromMeta(1));
		replaceableBlockStates.add(Blocks.deadbush.getDefaultState());
		replaceableBlockStates.add(Blocks.log.getStateFromMeta(1));
		replaceableBlockStates.add(Blocks.leaves.getStateFromMeta(1));
		replaceableBlockStates.add(Blocks.log.getDefaultState());
		replaceableBlockStates.add(Blocks.leaves.getDefaultState());
	}
	/**
	 * Returns if the state is eligible for replacing.
	 * @param blockState
	 * @return
	 */
	public static boolean eligibleStateLocation(IBlockState blockstate, BlockPos pos){
		if(pos.getY() > 1){
			if(replaceableBlockStates.contains(blockstate)){
				return true;
			}
		}
		return false;
	}
	/**
	 * Needs to return the itemstack from the method call gatherBlockGenList with the itemStack,
	 * world, player, diameter, desired biome type and the biome change boolean.
	 */
	@Override
	public abstract ItemStack onItemRightClick(ItemStack itemStackIn, World worldIn, EntityPlayer playerIn);
	//Each class needs to provide its own platform makeup.
	/**
	 * This method is called after the list of positions has been created. Each position is then passed into the method
	 * and needs to be set the world as a blockstate. The positions need to have a Y value greater than 1.
	 * Checks should be made for air blocks before placement and a call to
	 * eligibleStateLocation: EX: eligibleStateLocation(worldIn.getBlockState(pos))
	 * This method also needs to call super.setBiome at blocks that are placed. 
	 * @param pos Position that the block is being placed at.
	 * @param worldIn The world
	 * @param playerIn The player
	 * @param blocksGenerated The number of the blocks placed in the world. This keeps track of durability
	 * @param desiredBiome The desired biome type of the world. 
	 * @param changeBiome If the biome will be changed at the block column.
	 * @return Needs to return the amount of blocks placed. This keeps track of the durability.
	 */
	protected abstract int generateBlocksInWorld(BlockPos pos, World worldIn, EntityPlayer playerIn, int blocksGenerated, BiomeGenBase desiredBiome, boolean changeBiome);
		
	//Each class needs to provide its own decoration rules
	/**
	 * Optional implementation that needs to be called by generateBlocksInWorld on the SURFACE of the platform.
	 * @param worldIn The world
	 * @param pos The SURFACE block to be decorated.
	 */
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
			
				blocksGenerated = generateBlocksInWorld(pos, worldIn, playerIn, blocksGenerated, desiredBiome, changeBiome);
				
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
				if((surroundingBlocks >= 3 || Math.random() < 0.05) && pos.getY() > 1){
					blocksGenerated = generateBlocksInWorld(pos.down(), worldIn, playerIn, blocksGenerated, desiredBiome, changeBiome);
					recursiveList.add(pos.down());
				}
			}
		if(!recursiveList.isEmpty()){
			blocksGenerated = generateSpike(recursiveList, worldIn, playerIn, blocksGenerated, itemStackIn, desiredBiome, changeBiome);
		}
		return blocksGenerated;
	}
	
	public void canSafelyGenerate(BlockPos pos){
		
	}
	
	//Code taken from World Edit by Skq89
	//https://goo.gl/iEi0oU
	/**
	 * Changes the biome at a given position to the one given from the original method calls.
	 * @param worldIn World that it takes place in
	 * @param position Location of the biome change
	 * @param desiredBiome Biome ID that the biome will be moved to
	 * @param changeBiome If the config allows the biome change
	 * @return Returns whether or not the biome was changed.
	 */
	protected boolean setBiome(World worldIn, BlockPos position, BiomeGenBase desiredBiome, Boolean changeBiome) {
        if(changeBiome){
			Chunk chunk = worldIn.getChunkFromBlockCoords(position);
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
