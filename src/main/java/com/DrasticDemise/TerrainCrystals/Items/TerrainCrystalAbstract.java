package com.DrasticDemise.TerrainCrystals.Items;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Random;

import net.minecraft.block.IGrowable;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.chunk.Chunk;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public abstract class TerrainCrystalAbstract extends Item{
	public static HashSet replaceableBlockStates;
	public static HashSet invalidSpaces;
	
	/**
	 * Initializes the hashSet with block states that can be replaced by the platform.
	 */
	public static void initReplaceableBlocks(){
		replaceableBlockStates = new HashSet();
		replaceableBlockStates.add(Blocks.flowing_water.getStateFromMeta(1));
		replaceableBlockStates.add(Blocks.flowing_water.getStateFromMeta(2));
		replaceableBlockStates.add(Blocks.flowing_water.getStateFromMeta(3));
		replaceableBlockStates.add(Blocks.flowing_water.getStateFromMeta(4));
		replaceableBlockStates.add(Blocks.flowing_water.getStateFromMeta(5));
		replaceableBlockStates.add(Blocks.flowing_water.getStateFromMeta(6));
		replaceableBlockStates.add(Blocks.flowing_water.getStateFromMeta(7));
		replaceableBlockStates.add(Blocks.flowing_water.getStateFromMeta(8));
		replaceableBlockStates.add(Blocks.flowing_water.getStateFromMeta(9));
		replaceableBlockStates.add(Blocks.flowing_lava.getDefaultState());
		
		replaceableBlockStates.add(Blocks.tallgrass.getDefaultState());
		replaceableBlockStates.add(Blocks.tallgrass.getStateFromMeta(1));
		replaceableBlockStates.add(Blocks.red_mushroom.getDefaultState());
		replaceableBlockStates.add(Blocks.brown_mushroom.getDefaultState());

		replaceableBlockStates.add(Blocks.melon_block.getDefaultState());
		replaceableBlockStates.add(Blocks.cactus.getDefaultState());
		
		replaceableBlockStates.add(Blocks.air.getDefaultState());
		
		replaceableBlockStates.add(Blocks.sapling.getStateFromMeta(1));
		replaceableBlockStates.add(Blocks.deadbush.getDefaultState());
		replaceableBlockStates.add(Blocks.leaves.getDefaultState());
		replaceableBlockStates.add(Blocks.leaves.getStateFromMeta(1));
		replaceableBlockStates.add(Blocks.leaves.getStateFromMeta(3));
		replaceableBlockStates.add(Blocks.yellow_flower.getDefaultState());
		
		for(int i = 0; i < 9; i ++){
			replaceableBlockStates.add(Blocks.red_flower.getStateFromMeta(i));
		}
	//	replaceableBlockStates.add(Blocks.log.getDefaultState());
	//	replaceableBlockStates.add(Blocks.log.getStateFromMeta(1));
	//	replaceableBlockStates.add(Blocks.log.getStateFromMeta(3));
	}
	public static void initInvalidSpaces(){
		invalidSpaces = new HashSet();
		invalidSpaces.add(Blocks.log.getDefaultState());
		invalidSpaces.add(Blocks.log.getStateFromMeta(1));
		invalidSpaces.add(Blocks.log.getStateFromMeta(2));
		invalidSpaces.add(Blocks.log.getStateFromMeta(3));
		invalidSpaces.add(Blocks.log2.getDefaultState());
		
		invalidSpaces.add(Blocks.leaves.getDefaultState());
		invalidSpaces.add(Blocks.leaves.getStateFromMeta(1));
		invalidSpaces.add(Blocks.leaves.getStateFromMeta(2));
		invalidSpaces.add(Blocks.leaves.getStateFromMeta(3));
		
		invalidSpaces.add(Blocks.sapling.getDefaultState());
		invalidSpaces.add(Blocks.sapling.getStateFromMeta(1));
		invalidSpaces.add(Blocks.sapling.getStateFromMeta(2));
		invalidSpaces.add(Blocks.sapling.getStateFromMeta(3));
		//Used for end crystal
		invalidSpaces.add(Blocks.obsidian.getDefaultState());
		//Used to ice spikes plains
		invalidSpaces.add(Blocks.packed_ice.getDefaultState());
		//Mushroom Islands
		invalidSpaces.add(Blocks.red_mushroom_block.getDefaultState());
		invalidSpaces.add(Blocks.brown_mushroom_block.getDefaultState());
	}
	/**
	 * If the block given has enough room around it to generate a tree.
	 * @param blockState Takes a block state
	 * @param pos Position
	 * @return Returns a boolean if the blockstate given is an eligble location for a tree.
	 */
	private static boolean eligibleSpaceForTree(IBlockState blockState, BlockPos pos){
		if(pos.getY() > 1){
			if(invalidSpaces.contains(blockState)){
				return false;
			}
			return true;
		}
		return false;
	}
	/**
	 * Returns if the state is eligible for replacing. Checks Y Level and block state
	 * @param blockState Takes the intended block state from the position in the world
	 * @return returns a boolean if eligible or not.
	 */
	public static boolean eligibleStateLocation(World worldIn, BlockPos pos){
		if(pos.getY() > 1){
			if(replaceableBlockStates.contains(worldIn.getBlockState(pos))){
				return true;
			}
		}
		return false;
	}
	/**
	 * Needs to return the itemstack from the method call gatherBlockGenList with the itemStack,
	 * world, player, diameter, desired biome type and the biome change boolean.
	 */
	public abstract ActionResult<ItemStack> onItemRightClick(ItemStack itemStackIn, World worldIn, EntityPlayer playerIn, EnumHand hand);
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
	protected int generateBlocksInWorld(BlockPos pos, World worldIn, EntityPlayer playerIn, int blocksGenerated, BiomeGenBase desiredBiome, boolean changeBiome){
		if(eligibleStateLocation(worldIn, pos)){
			int posY = MathHelper.floor_double(playerIn.posY);
			if(posY - pos.getY() == 1){
				setBiome(worldIn, pos, desiredBiome, changeBiome);
				worldIn.setBlockState(pos, Blocks.grass.getDefaultState());
				decoratePlatform(worldIn, pos);
			}else{
				worldIn.setBlockState(pos, Blocks.dirt.getDefaultState());
			}
		}
		return blocksGenerated++;
	}
		
	//Each class needs to provide its own decoration rules
	/**
	 * Optional implementation that needs to be called by generateBlocksInWorld on the SURFACE of the platform.
	 * @param worldIn The world
	 * @param pos The SURFACE block to be decorated.
	 */
	protected abstract void decoratePlatform(World worldIn, BlockPos pos);
	//Code taken from Lumien's Random Things Nature Core tile entity
	/**
	 * Bonemeals the grass at a given position
	 * @param worldIn World
	 * @param pos Pos
	 */
	protected void bonemeal(World worldIn, BlockPos pos){
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
		}catch(Exception e){
		}
	}
	
	/**
	 * Gathers the list of blocks to be generated 
	 * @param itemStackIn
	 * @param worldIn
	 * @param playerIn
	 * @param diameter
	 * @param desiredBiome
	 * @param changeBiome
	 * @return
	 */
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
			//		if(eligibleStateLocation(worldIn, fillShellOne)){
						posList.add(fillShellOne);
			//		}
					BlockPos fillShellTwo = new BlockPos(offsetXFirstHalf - i, posY - yDown, posZ + i - placeInwards);
			//		if(eligibleStateLocation(worldIn, fillShellTwo)){
						posList.add(fillShellTwo);
			//		}
				}
			}
			//Generates the second half
			for(int i = 0; i < (center); i ++){
				BlockPos shellThree = new BlockPos(offsetXSecondHalf + i, posY - 1, posZ  + i);
				BlockPos shellFour = new BlockPos(offsetXSecondHalf + i, posY - 1, posZ - i);
				//if(eligibleStateLocation(worldIn, shellThree)){
					posList.add(shellThree); 
				//}
				//if(eligibleStateLocation(worldIn, shellFour)){
					posList.add(shellFour);
				//}
				for(int placeInwards = 0; placeInwards < i + 1; placeInwards++){
					BlockPos fillShellThree = new BlockPos(offsetXSecondHalf + i, posY - 1, posZ + i - placeInwards);
					BlockPos fillShellFour = new BlockPos(offsetXSecondHalf + i, posY - 1, posZ - i + placeInwards);
				//	if(eligibleStateLocation(worldIn, fillShellThree)){
						posList.add(fillShellThree);
			//		}
				//	if(eligibleStateLocation(worldIn, fillShellFour)){
						posList.add(fillShellFour);
				//	}
				}
			}
			for(BlockPos p : posList){
				blocksGenerated = generateSpike(posList, worldIn, playerIn, blocksGenerated, itemStackIn, desiredBiome, changeBiome);
			}
		}
		itemStackIn.damageItem(blocksGenerated, playerIn);
		return itemStackIn;
	}
	/**
	 * Converts the list of positions into blocks in the shape of a spike.
	 * @param posList List of positions
	 * @param worldIn World
	 * @param playerIn Player location
	 * @param blocksGenerated Number of blocks that have been generated so far
	 * @param itemStackIn The item used
	 * @param desiredBiome Biome type desired
	 * @param changeBiome If the config allows the biome to be changed
	 * @return Returns an int, usually the number of blocks generated in the world.
	 */
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

	        	if(BiomeGenBase.getIdForBiome(worldIn.getChunkFromBlockCoords(position).getBiome(position, worldIn.getBiomeProvider())) != BiomeGenBase.getIdForBiome(desiredBiome)){
	        		chunk.getBiomeArray()[((position.getZ() & 0xF) << 4 | position.getX() & 0xF)] = (byte) desiredBiome.getIdForBiome(desiredBiome);
		            return true;
	        	}
	        }
        }
        return false;
    }
	/**
	 * Pass this method the position that the sapling will OCCUPY, not REST ON. Meaning Pos.UP of the platform position.
	 * @param worldIn World
	 * @param pos Position
	 * @return Returns if viable location
	 */
	protected boolean spacedFarEnough(World worldIn, BlockPos pos){
		int diameter = 5;
		int posX = pos.getX();
		int posY = pos.getY();
		int posZ = pos.getZ();
		int center;
		ArrayList<BlockPos> posList = new ArrayList<BlockPos>(13);
		double radius = diameter/2.0;
		if(diameter%2 != 0){
			center = (int) (radius + 0.5);
		}else{
			center = (int) (radius);
		}
		int offsetXFirstHalf = (int) (posX + radius);
		//Not sure why this has to be offset by 1 extra, but it does.
		int offsetXSecondHalf = (int) (posX - radius + 1);
		for(int i = 0; i < (center); i ++){
			//Creates a circle and fills it
			for(int placeInwards = 0; placeInwards < i+1; placeInwards++){
				//Fills across the circle
				BlockPos fillShellOne = new BlockPos(offsetXFirstHalf - i, posY, posZ - i + placeInwards);
				posList.add(fillShellOne);
				BlockPos fillShellTwo = new BlockPos(offsetXFirstHalf - i, posY, posZ + i - placeInwards);
				posList.add(fillShellTwo);
			}
		}
		//Generates the second half
		for(int i = 0; i < (center); i ++){
			BlockPos shellThree = new BlockPos(offsetXSecondHalf + i, posY, posZ  + i);
			BlockPos shellFour = new BlockPos(offsetXSecondHalf + i, posY, posZ - i);
			posList.add(shellThree); 
			posList.add(shellFour);
			
			for(int placeInwards = 0; placeInwards < i + 1; placeInwards++){
				BlockPos fillShellThree = new BlockPos(offsetXSecondHalf + i, posY, posZ + i - placeInwards);
				BlockPos fillShellFour = new BlockPos(offsetXSecondHalf + i, posY, posZ - i + placeInwards);
				posList.add(fillShellThree);
				posList.add(fillShellFour);
			}
		}
		for(BlockPos b : posList){
			if(!eligibleSpaceForTree(worldIn.getBlockState(b), b)){
				return false;
			}
		}
		return true;
	}
	protected boolean spacedFarEnough(World worldIn, BlockPos pos, int diameter){
		int posX = pos.getX();
		int posY = pos.getY();
		int posZ = pos.getZ();
		int center;
		ArrayList<BlockPos> posList = new ArrayList<BlockPos>(13);
		double radius = diameter/2.0;
		if(diameter%2 != 0){
			center = (int) (radius + 0.5);
		}else{
			center = (int) (radius);
		}
		int offsetXFirstHalf = (int) (posX + radius);
		//Not sure why this has to be offset by 1 extra, but it does.
		int offsetXSecondHalf = (int) (posX - radius + 1);
		for(int i = 0; i < (center); i ++){
			//Creates a circle and fills it
			for(int placeInwards = 0; placeInwards < i+1; placeInwards++){
				//Fills across the circle
				BlockPos fillShellOne = new BlockPos(offsetXFirstHalf - i, posY, posZ - i + placeInwards);
				posList.add(fillShellOne);
				BlockPos fillShellTwo = new BlockPos(offsetXFirstHalf - i, posY, posZ + i - placeInwards);
				posList.add(fillShellTwo);
			}
		}
		//Generates the second half
		for(int i = 0; i < (center); i ++){
			BlockPos shellThree = new BlockPos(offsetXSecondHalf + i, posY, posZ  + i);
			BlockPos shellFour = new BlockPos(offsetXSecondHalf + i, posY, posZ - i);
			posList.add(shellThree); 
			posList.add(shellFour);
			
			for(int placeInwards = 0; placeInwards < i + 1; placeInwards++){
				BlockPos fillShellThree = new BlockPos(offsetXSecondHalf + i, posY, posZ + i - placeInwards);
				BlockPos fillShellFour = new BlockPos(offsetXSecondHalf + i, posY, posZ - i + placeInwards);
				posList.add(fillShellThree);
				posList.add(fillShellFour);
			}
		}
		for(BlockPos b : posList){
			if(!eligibleSpaceForTree(worldIn.getBlockState(b), b)){
				return false;
			}
		}
		return true;
	}
	
	@SideOnly(Side.CLIENT)
    public void addInformation(ItemStack stack, EntityPlayer playerIn, List<String> tooltip, boolean advanced)
    {
		tooltip.add("You must relog in order to see the biome change client-side. Changes server-side instantly.");
    }
	
	@SideOnly(Side.CLIENT)
    public void initModel() {
        ModelLoader.setCustomModelResourceLocation(this, 0, new ModelResourceLocation(getRegistryName(), "inventory"));
    }
}
