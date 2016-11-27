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
		if(checkIfDimensionMatters(playerIn)){
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
		}
		return blocksGenerated;
	}

	private boolean checkIfDimensionMatters(EntityPlayer playerIn){
		if(ConfigurationFile.endCrystalRestrictedToEnd){
			if(playerIn.dimension == 1){
				return true;
			}else{
				playerIn.addChatComponentMessage(new TextComponentTranslation("This crystal is only available for use in the End."));
				return false;
			}
		}
		return true;
	}
	
	@Override
	protected void decoratePlatform(World worldIn, BlockPos pos){

		if(ConfigurationFile.endCrystalGenerateChorus && spacedFarEnough(worldIn, pos.up())){
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
		tooltip.add("Relog for client sync.");
	}
}
