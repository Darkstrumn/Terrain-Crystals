package com.BaileyHollingsworth.TerrainCrystals.Items.SkyCrystals;

import com.BaileyHollingsworth.TerrainCrystals.Items.TerrainCrystalAbstract;
import com.BaileyHollingsworth.TerrainCrystals.core.ConfigurationFile;
import net.minecraft.block.IGrowable;
import net.minecraft.init.Biomes;
import net.minecraft.init.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.biome.Biome;

import java.util.Random;

/**
 * Created by Bailey Hollingsworth on 11/26/16.
 */
public class TerrainCrystalRoofedForest extends TerrainCrystalAbstract{

    public TerrainCrystalRoofedForest(){super("RoofedForest");}

    @Override
    protected void decoratePlatform(World worldIn, BlockPos pos) {
        try{
            if(Math.random() < 0.1){
                bonemeal(worldIn, pos);
            }
            if(Math.random() <= 0.04){
                growTree(worldIn, pos);
            }
        }catch(Exception ignored){}
    }
    private void growTree(World worldIn, BlockPos pos){
        if(ConfigurationFile.roofedForestGeneratesTrees){
            if (spacedFarEnough(worldIn, pos.up())){
                BlockPos newPos = pos.up();
                worldIn.setBlockState(pos, Blocks.DIRT.getDefaultState());
                worldIn.setBlockState(pos.east(), Blocks.DIRT.getDefaultState());
                worldIn.setBlockState(pos.south(), Blocks.DIRT.getDefaultState());
                worldIn.setBlockState(pos.east().south(), Blocks.DIRT.getDefaultState());
                worldIn.setBlockState(newPos, Blocks.SAPLING.getStateFromMeta(5));
                worldIn.setBlockState(newPos.south(), Blocks.SAPLING.getStateFromMeta(5));
                worldIn.setBlockState(newPos.east(), Blocks.SAPLING.getStateFromMeta(5));
                worldIn.setBlockState(newPos.east().south(), Blocks.SAPLING.getStateFromMeta(5));
                IGrowable growable = (IGrowable) worldIn.getBlockState(pos.up()).getBlock();
                Random rand = new Random();
                int attemptCap = 0;
                while((worldIn.getBlockState(pos.up()) != Blocks.LOG.getDefaultState()) && attemptCap < 10){
                    growable.grow(worldIn, rand, pos.up(), worldIn.getBlockState(pos.up()));
                    attemptCap++;
                }
            }
        }
    }
    @Override
    protected Boolean changesBiomeOnUse() {
        return ConfigurationFile.roofedForestChangesBiome;
    }

    @Override
    protected Biome getBiomeType() {
        return Biomes.ROOFED_FOREST;
    }

    @Override
    protected int getDiameter() {
        return ConfigurationFile.roofedForestDiameter;
    }

    @Override
    protected int getDurability() {
        return ConfigurationFile.roofedForestDurability;
    }
}
