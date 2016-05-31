package com.DrasticDemise.TerrainCrystals.Items;

import com.DrasticDemise.TerrainCrystals.ConfigurationFile;
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

public class TerrainCrystalTaiga extends TerrainCrystalAbstract{
	public TerrainCrystalTaiga(){
		setUnlocalizedName("terrainCrystalTaiga");
		setRegistryName("terrainCrystalTaiga");
		setCreativeTab(CreativeTabs.BUILDING_BLOCKS);
		setHarvestLevel("stone", 0);
		setMaxStackSize(1);
		setMaxDamage(ConfigurationFile.taigaCrystalDurability);
        GameRegistry.register(this);
	}
	@Override
	public ActionResult<ItemStack> onItemRightClick(ItemStack itemStackIn, World worldIn, EntityPlayer playerIn, EnumHand hand){
		super.gatherBlockGenList(itemStackIn, worldIn, playerIn, ConfigurationFile.taigaCrystalDiameter, Biomes.TAIGA, ConfigurationFile.taigaCrystalChangesBiome);
		return new ActionResult(EnumActionResult.PASS, itemStackIn);
	}
	@Override
	protected int generateBlocksInWorld(BlockPos pos, World worldIn, EntityPlayer playerIn, int blocksGenerated,
										Biome desiredBiome, boolean changeBiome){
		
		if(eligibleStateLocation(worldIn, pos)){
			int posY = MathHelper.floor_double(playerIn.posY);
			if(posY - pos.getY() == 1){
				setBiome(worldIn, pos, desiredBiome, changeBiome);
				if(Math.random() < .4){
					if(Math.random() < .5){
						worldIn.setBlockState(pos, Blocks.DIRT.getStateFromMeta(1));
					}else{
						worldIn.setBlockState(pos, Blocks.GRASS.getDefaultState());
					}
					if(ConfigurationFile.taigaCrystalGeneratesTrees && Math.random() < 0.08){
						growTree(worldIn, pos);
					}else{
						decoratePlatform(worldIn, pos);
					}
				}else{
					worldIn.setBlockState(pos, Blocks.DIRT.getStateFromMeta(2));
				}
			}else{
				worldIn.setBlockState(pos, Blocks.DIRT.getDefaultState());
			}
			blocksGenerated += 1;
		}
		return blocksGenerated;
	}
	private void growTree(World worldIn, BlockPos pos) {
		if (Blocks.SAPLING.canPlaceBlockAt(worldIn, pos.up()) && spacedFarEnough(worldIn, pos)){
			worldIn.setBlockState(pos.up(), Blocks.SAPLING.getStateFromMeta(1));
			bonemealTree(worldIn, pos);
		}
	}
	@Override
	protected void decoratePlatform(World worldIn, BlockPos pos) {
		if(Math.random() < 0.3){
			if(Math.random() < 0.50){
				worldIn.setBlockState(pos.up(), Blocks.TALLGRASS.getStateFromMeta(2));
			}else{
				worldIn.setBlockState(pos.up(), Blocks.TALLGRASS.getStateFromMeta(1));
			}
		}
		
	}
}

