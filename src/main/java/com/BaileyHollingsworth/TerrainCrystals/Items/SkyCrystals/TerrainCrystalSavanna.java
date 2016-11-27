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
public class TerrainCrystalSavanna extends TerrainCrystalAbstract{

    public TerrainCrystalSavanna(){
        super("Savanna");
    }

    @Override
    protected void decoratePlatform(World worldIn, BlockPos pos) {
        try{
            if(Math.random() <= 0.02){
                bonemeal(worldIn, pos);
            }
            if(Math.random() <= 0.01){
                growTree(worldIn, pos);
            }
        }catch(Exception e){}
    }
    private void growTree(World worldIn, BlockPos pos){
        if(ConfigurationFile.savannaCrystalGeneratesTrees){
            if (spacedFarEnough(worldIn, pos.up(), 30)){
                worldIn.setBlockState(pos.up(), Blocks.SAPLING.getStateFromMeta(4));
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
        return ConfigurationFile.savannaCrystalChangesBiome;
    }

    @Override
    protected Biome getBiomeType() {
        return Biomes.SAVANNA;
    }

    @Override
    protected int getDiameter() {
        return ConfigurationFile.savannaCrystalDiameter;
    }

    @Override
    protected int getDurability() {
        return ConfigurationFile.savannaCrystalDurability;
    }
}
