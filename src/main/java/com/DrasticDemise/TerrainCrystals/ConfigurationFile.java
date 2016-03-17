package com.DrasticDemise.TerrainCrystals;

import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.common.config.Property;

public class ConfigurationFile {
	public static void configFile(Configuration config){
		config.load();
		
		//Plains Crystal
		plainsCrystalDurability = config.getInt("Plains Crystal Durability", Configuration.CATEGORY_GENERAL, 7000, 1, Integer.MAX_VALUE, "How many blocks can the crystal generate before breaking. Can go up to integer max.");
		plainsCrystalDiameter = config.getInt("Plains Crystal Diameter", Configuration.CATEGORY_GENERAL, 11, 1, 9999, "Odd numbers work best. Default: 11");
		plainsCrystalGenerateTallGrass = config.getBoolean("Plains Crystal generate tall grass", Configuration.CATEGORY_GENERAL, true, "Should platforms have a bonemeal effect on the surface?");
		plainsCrystalGenerateTrees = config.get(Configuration.CATEGORY_GENERAL, "plainsCrystalGenerateTrees", true, "Enable tree spawning when used? Must have tall grass generation enabled.").getBoolean();
		plainsCrystalChangesBiome = config.getBoolean("Plains Crystal Turns Other Biomes Plains", Configuration.CATEGORY_GENERAL, true, "Should the plains biome change biomes? Changes biomes in each location of a spawned surface block.");
		
		//Desert Crystal
		desertCrystalDurability = config.getInt("Desert Crystal Durability", Configuration.CATEGORY_GENERAL, 7000, 1, Integer.MAX_VALUE, "How many blocks can the crystal generate before breaking. Can go up to integer max.");
		desertCrystalDiameter = config.getInt("Desert Crystal Diameter", Configuration.CATEGORY_GENERAL, 11, 1, 9999, "Odd numbers work best. Default: 11");
		desertCrystalGenerateCactus = config.getBoolean("Desert Crystal Generates Cactus", Configuration.CATEGORY_GENERAL, true, "Should platform have cactus on it?");
		desertCrystalChangesBiome = config.getBoolean("Desert Crystal Turns Other Biomes Desert", Configuration.CATEGORY_GENERAL, true, "Changes biomes in each location of a spawned surface block.");
		//Mesa Crystal
		mesaCrystalDurability = config.getInt("Mesa Crystal Durability", Configuration.CATEGORY_GENERAL, 7000, 1, Integer.MAX_VALUE, "How many blocks can the crystal generate before breaking. Can go up to integer max.");
		mesaCrystalDiameter = config.getInt("Mesa Crystal Diameter", Configuration.CATEGORY_GENERAL, 11, 1, 9999, "Odd numbers work best. Default: 11");
		mesaCrystalChangesBiome = config.getBoolean("Mesa Crystal Turns Other Biomes Mesa", Configuration.CATEGORY_GENERAL, true, "Changes biomes in each location of a spawned surface block.");
		//Mushroom Crystal
		mushroomCrystalDurability = config.getInt("Mushroom Crystal Durability", Configuration.CATEGORY_GENERAL, 7000, 1, Integer.MAX_VALUE, "How many blocks can the crystal generate before breaking. Can go up to integer max.");
		mushroomCrystalDiameter = config.getInt("Mushroom Crystal Diameter", Configuration.CATEGORY_GENERAL, 11, 1, 9999, "Odd numbers work best. Default: 11");
		mushroomCrystalChangesBiome = config.getBoolean("Mushroom Crystal Turns Other Biomes Mushroom", Configuration.CATEGORY_GENERAL, true, "Changes biomes in each location of a spawned surface block.");
		//Nether Crystal
		netherCrystalDurability = config.getInt("Nether Crystal Durability", Configuration.CATEGORY_GENERAL, 7000, 1, Integer.MAX_VALUE, "How many blocks can the crystal generate before breaking. Can go up to integer max.");
		netherCrystalDiameter = config.getInt("Nether Crystal Diameter", Configuration.CATEGORY_GENERAL, 11, 1, 9999, "Odd numbers work best. Default: 11");
		netherCrystalChangesBiome = config.getBoolean("Nether Crystal Turns Other Biomes Nether", Configuration.CATEGORY_GENERAL, true, "Changes biomes in each location of a spawned surface block.");
		//End Crystal
		endCrystalDurability = config.getInt("End Crystal Durability", Configuration.CATEGORY_GENERAL, 7000, 1, Integer.MAX_VALUE, "How many blocks can the crystal generate before breaking. Can go up to integer max.");
		endCrystalDiameter = config.getInt("End Crystal Diameter", Configuration.CATEGORY_GENERAL, 11, 1, 9999, "Odd numbers work best. Default: 11");
		endCrystalGenerateObsidianSpikes = config.getBoolean("End Crystal Generates Obsidian Spikes", Configuration.CATEGORY_GENERAL, true, "Should platforms have ruin-like structures on top?");
		endCrystalChangesBiome = config.getBoolean("End Crystal Turns Other Biomes End", Configuration.CATEGORY_GENERAL, true, "Changes biomes in each location of a spawned surface block.");
		if(config.hasChanged()){
			config.save();
		}
	}
	//Plains
	public static int plainsCrystalDurability;
	public static int plainsCrystalDiameter;
	public static boolean plainsCrystalGenerateTallGrass;
    public static boolean plainsCrystalGenerateTrees;
    public static boolean plainsCrystalChangesBiome;
    //Desert
    public static int desertCrystalDurability;
	public static int desertCrystalDiameter;
    public static boolean desertCrystalGenerateCactus;
    public static boolean desertCrystalChangesBiome;
    //Mesa
    public static int mesaCrystalDurability;
	public static int mesaCrystalDiameter;
    public static boolean mesaCrystalChangesBiome;
    //Mushroom
    public static int mushroomCrystalDurability;
	public static int mushroomCrystalDiameter;
    public static boolean mushroomCrystalChangesBiome;
    //End
    public static int endCrystalDurability;
	public static int endCrystalDiameter;
	public static boolean endCrystalGenerateObsidianSpikes;
    public static boolean endCrystalChangesBiome;
    //Nether
    public static int netherCrystalDurability;
	public static int netherCrystalDiameter;
    public static boolean netherCrystalChangesBiome;
}
