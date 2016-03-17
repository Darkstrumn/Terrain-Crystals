package com.DrasticDemise.TerrainCrystals;

import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.common.config.Property;

public class ConfigurationFile {
	public static void configFile(Configuration config){
		config.load();
		
		plainsCrystalDurability = config.getInt("Plains Crystal Durability", Configuration.CATEGORY_GENERAL, 1, 1, Integer.MAX_VALUE, "How many blocks can the crystal generate before breaking. Can go up to integer max.");
		plainsCrystalDiameter = config.getInt("Plains Crystal Diameter", Configuration.CATEGORY_GENERAL, 11, 1, 9999, "Odd numbers work best. Default: 11");
		plainsCrystalGenerateTallGrass = config.getBoolean("Plains Crystal generate tall grass", Configuration.CATEGORY_GENERAL, true, "Should platforms have a bonemeal effect on the surface?");
		plainsCrystalGenerateTrees = config.get(Configuration.CATEGORY_GENERAL, "plainsCrystalGenerateTrees", true, "Enable tree spawning when used? Must have tall grass generation enabled.").getBoolean();
		plainsCrystalChangesBiome = config.getBoolean("Plains Crystal Turns Other Biomes Plains", Configuration.CATEGORY_GENERAL, true, "Should the plains biome change biomes? Changes biomes in each location of a spawned surface block.");
  
        
        config.save();
	}
	public static int plainsCrystalDurability;
	public static int plainsCrystalDiameter;
	public static boolean plainsCrystalGenerateTallGrass;
    public static boolean plainsCrystalGenerateTrees;
    public static boolean plainsCrystalChangesBiome;
}
