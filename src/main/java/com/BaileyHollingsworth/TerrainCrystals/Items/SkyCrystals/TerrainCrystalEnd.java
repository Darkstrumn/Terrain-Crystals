package com.BaileyHollingsworth.TerrainCrystals.Items.SkyCrystals;

import com.BaileyHollingsworth.TerrainCrystals.Items.TerrainCrystalAbstract;
import com.BaileyHollingsworth.TerrainCrystals.core.ConfigurationFile;
import net.minecraft.block.BlockChorusFlower;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Biomes;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraft.world.World;
import net.minecraft.world.biome.Biome;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.util.List;
import java.util.Random;

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
		if(checkIfDimensionMatters(playerIn, worldIn)){
			if(eligibleStateLocation(worldIn, pos)){
				int posY = MathHelper.floor_double(playerIn.posY);
				if(posY - pos.getY() == 1){
<<<<<<< HEAD
					worldIn.setBlockState(pos, Blocks.END_STONE.getDefaultState());
					setBiome(worldIn, pos, desiredBiome, changeBiome);
					if(!worldIn.isRemote)
						decoratePlatform(worldIn, pos);
				}else{
=======
					decoratePlatform(worldIn, pos);
					setBiome(worldIn, pos, desiredBiome, changeBiome);
				}else if(!worldIn.isRemote){
>>>>>>> master
					worldIn.setBlockState(pos, Blocks.END_STONE.getDefaultState());
				}
				blocksGenerated += 1;
			}
		}
		return blocksGenerated;
	}

	private boolean checkIfDimensionMatters(EntityPlayer playerIn, World worldIn){
		if(ConfigurationFile.endCrystalRestrictedToEnd){
			if(playerIn.dimension == 1){
				return true;
			}else{
<<<<<<< HEAD
				playerIn.addChatComponentMessage(new TextComponentTranslation("This crystal is only available for use in the End."), true);
=======
				if(!worldIn.isRemote) {
                    playerIn.addChatComponentMessage(new TextComponentTranslation("This crystal is only available for use in the End."));
                }
>>>>>>> master
				return false;
			}
		}
		return true;
	}
	
	@Override
	protected void decoratePlatform(World worldIn, BlockPos pos){
		if(!worldIn.isRemote && ConfigurationFile.endCrystalGenerateChorus && spacedFarEnough(worldIn, pos.up())){
            worldIn.setBlockState(pos, Blocks.END_STONE.getDefaultState());
			if(Math.random() > .98){
				try{
					Random rand = new Random();	
					BlockChorusFlower.generatePlant(worldIn, pos.up(), rand, 1);
				}catch(Exception ignored){}
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

	@Override
	@SideOnly(Side.CLIENT)
	public void addInformation(ItemStack stack, EntityPlayer playerIn, List<String> tooltip, boolean advanced)
	{
		if(ConfigurationFile.endCrystalRestrictedToEnd){
			tooltip.add("Can only be used in the End.");
		}
	}
}
