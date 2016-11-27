package com.BaileyHollingsworth.TerrainCrystals.core;

import net.minecraftforge.common.config.Configuration;

public class ConfigurationFile {
	public static final String DURABILITY = "Durability";
    public static final String DIAMETER = "Diameter";
    public static final String BIOME_CONVERSION = "Biome";
    public static final String DECORATION = "Decoration";
    public static final String MISC = "Misc";
	public static void configFile(Configuration config){
		config.load();

		//Durabilities
		plainsCrystalDurability = config.getInt("Plains Crystal Durability", ConfigurationFile.DURABILITY, 7000, 1, Integer.MAX_VALUE, "How many blocks can the crystal generate before breaking. Can go up to integer max.");
		desertCrystalDurability = config.getInt("Desert Crystal Durability", ConfigurationFile.DURABILITY, 7000, 1, Integer.MAX_VALUE, "");
		mesaCrystalDurability = config.getInt("Mesa Crystal Durability", ConfigurationFile.DURABILITY, 7000, 1, Integer.MAX_VALUE, "");
		mushroomCrystalDurability = config.getInt("Mushroom Crystal Durability", ConfigurationFile.DURABILITY, 7000, 1, Integer.MAX_VALUE, "");
		netherCrystalDurability = config.getInt("Nether Crystal Durability", ConfigurationFile.DURABILITY, 7000, 1, Integer.MAX_VALUE, "");
		endCrystalDurability = config.getInt("End Crystal Durability", ConfigurationFile.DURABILITY, 7000, 1, Integer.MAX_VALUE, "");
        taigaCrystalDurability = config.getInt("Taiga Crystal Durability", ConfigurationFile.DURABILITY, 7000, 1, Integer.MAX_VALUE, "");
        plainsIceCrystalDurability = config.getInt("Ice Plains Crystal Durability", ConfigurationFile.DURABILITY, 7000, 1, Integer.MAX_VALUE, "");
        jungleCrystalDurability = config.getInt("Jungle Crystal Durability", ConfigurationFile.DURABILITY, 7000, 1, Integer.MAX_VALUE, "");
        swampCrystalDurability = config.getInt("Swamp Crystal Durability", ConfigurationFile.DURABILITY, 7000, 1, Integer.MAX_VALUE, "");


		//Diameters
		plainsCrystalDiameter = config.getInt("Plains Crystal Diameter", ConfigurationFile.DIAMETER, 11, 1, 9999, "Odd numbers work best. Default: 11");
		desertCrystalDiameter = config.getInt("Desert Crystal Diameter", ConfigurationFile.DIAMETER, 11, 1, 9999, "");
		mesaCrystalDiameter = config.getInt("Mesa Crystal Diameter", ConfigurationFile.DIAMETER, 11, 1, 9999, "");
		mushroomCrystalDiameter = config.getInt("Mushroom Crystal Diameter", ConfigurationFile.DIAMETER, 11, 1, 9999, "");
		netherCrystalDiameter = config.getInt("Nether Crystal Diameter", ConfigurationFile.DIAMETER, 11, 1, 9999, "");
		endCrystalDiameter = config.getInt("End Crystal Diameter", ConfigurationFile.DIAMETER, 11, 1, 9999, "");
        taigaCrystalDiameter = config.getInt("Taiga Crystal Diameter", ConfigurationFile.DIAMETER, 11, 1, 9999, "");
        plainsIceCrystalDiameter = config.getInt("Ice Plains Crystal Diameter", ConfigurationFile.DIAMETER, 11, 1, 9999, "");
        jungleCrystalDiameter = config.getInt("Jungle Crystal Diameter", ConfigurationFile.DIAMETER, 11, 1, 9999, "");
        swampCrystalDiameter = config.getInt("Swamp Crystal Diameter", ConfigurationFile.DIAMETER, 11, 1, 9999, "");


		//Changes Biome
		plainsCrystalChangesBiome = config.getBoolean("Plains Crystal Turns Other Biomes Plains", ConfigurationFile.BIOME_CONVERSION, true, "Should this crystal transform other biomes when blocks spawn?");
		desertCrystalChangesBiome = config.getBoolean("Desert Crystal Turns Other Biomes Desert", ConfigurationFile.BIOME_CONVERSION, true, "");
		mesaCrystalChangesBiome = config.getBoolean("Mesa Crystal Turns Other Biomes Mesa", ConfigurationFile.BIOME_CONVERSION, true, "");
		mushroomCrystalChangesBiome = config.getBoolean("Mushroom Crystal Turns Other Biomes Mushroom", ConfigurationFile.BIOME_CONVERSION, true, "");
		netherCrystalChangesBiome = config.getBoolean("Nether Crystal Turns Other Biomes Nether", ConfigurationFile.BIOME_CONVERSION, true, "");
		endCrystalChangesBiome = config.getBoolean("End Crystal Turns Other Biomes End", ConfigurationFile.BIOME_CONVERSION, true, "");
        taigaCrystalChangesBiome = config.getBoolean("Taiga Crystal Turns Other Biomes Cold Taiga", ConfigurationFile.BIOME_CONVERSION, true, "");
        plainsIceCrystalChangesBiome = config.getBoolean("Ice Plains Crystal Turns Other Biomes Ice Plains", ConfigurationFile.BIOME_CONVERSION, true, "");
        jungleCrystalChangesBiome = config.getBoolean("Jungle Crystal Turns Other Biomes Jungle", ConfigurationFile.BIOME_CONVERSION, true, "");
        swampCrystalChangesBiome = config.getBoolean("Swamp Crystal Turns Other Biomes Swamplands", ConfigurationFile.BIOME_CONVERSION, true, "");


		//Foliage
		plainsCrystalGenerateTallGrass = config.getBoolean("Plains Crystal generate tall grass", ConfigurationFile.DECORATION, true, "");
		plainsCrystalGenerateTrees = config.getBoolean("Plains Crystal Spawns Trees", ConfigurationFile.DECORATION, true, "Should the plains biome have a chance to spawn trees? Tall Grass must be enabled.");
		desertCrystalGenerateCactus = config.getBoolean("Desert Crystal Generates Cactus", ConfigurationFile.DECORATION, true, "");
		endCrystalGenerateChorus = config.getBoolean("End Crystal Generates Chorus", ConfigurationFile.DECORATION, true, "");
        taigaCrystalGeneratesTrees = config.getBoolean("Taiga Crystal Generates Spruce Trees", ConfigurationFile.DECORATION, true, "");
        jungleCrystalGeneratesTrees = config.getBoolean("Jungle Crystal Generates Jungle Trees", ConfigurationFile.DECORATION, true, "");
        jungleCrystalGeneratesMelon = config.getBoolean("Jungle Crystal Generates Melon", ConfigurationFile.DECORATION, true, "");
        jungleCrystalGeneratesCocoa = config.getBoolean("Jungle Crystal Generates Cocoa Beans", ConfigurationFile.DECORATION, true, "");
        jungleCrystalGeneratesBushes = config.getBoolean("Jungle Crystal Generates small bushes", ConfigurationFile.DECORATION, true, "");
        swampCrystalGensWater = config.getBoolean("Swamp Crystal Generates water pools", ConfigurationFile.DECORATION, true, "");
        swampCrystalGensClay = config.getBoolean("Swamp Crystal Generates Clay", ConfigurationFile.DECORATION, true, "");

		//Nether Crystal
		netherCrystalRestrictedToNether = config.getBoolean("If The Nether Crystal only works in the Nether", ConfigurationFile.MISC, false, "");
		//End Crystal
		endCrystalRestrictedToEnd = config.getBoolean("End Crystal Restricted to End Dimension", ConfigurationFile.MISC, false, "");
		//Misc
		generateStone = config.getBoolean("Have the islands generate stone by default", ConfigurationFile.MISC, true, "Forces stone to spawn beneath certain crystals");
		generateOres = config.getBoolean("Have stone generating islands also spawn ore", ConfigurationFile.MISC, false, "Generate stone must be enabled.");
		stoneSpawnDepth = config.getInt("How far down islands start to spawn stone. Does nothing if GenerateStone is not enabled.", Configuration.CATEGORY_GENERAL, 3, 2, 9999, "Must be at least 2. Keep within range of the island depth. Default: 3");
		versionChecker = config.getBoolean("Version Checker Enabled", ConfigurationFile.MISC, true, "If you want to be notified when a new version is available");
		if(config.hasChanged()){
			config.save();
		}
	}
	
	//Misc
	public static boolean versionChecker;
	public static boolean generateStone;
	public static boolean generateOres;
	public static int stoneSpawnDepth;
	//Swamp
	public static int swampCrystalDurability;
	public static int swampCrystalDiameter;
	public static boolean swampCrystalGensWater;
	public static boolean swampCrystalGensClay;
	public static boolean swampCrystalChangesBiome;
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
	public static boolean endCrystalGenerateChorus;
    public static boolean endCrystalChangesBiome;
    public static boolean endCrystalRestrictedToEnd;
    //Nether
    public static int netherCrystalDurability;
	public static int netherCrystalDiameter;
    public static boolean netherCrystalChangesBiome;
    public static boolean netherCrystalRestrictedToNether;
    //Taiga
    public static int taigaCrystalDurability;
    public static int taigaCrystalDiameter;
    public static boolean taigaCrystalGeneratesTrees;
    public static boolean taigaCrystalChangesBiome;
    //Plains Ice Spikes
    public static int plainsIceCrystalDurability;
    public static int plainsIceCrystalDiameter;
    public static boolean plainsIceCrystalChangesBiome;
    //Jungle
    public static int jungleCrystalDurability;
    public static int jungleCrystalDiameter;
    public static boolean jungleCrystalGeneratesTrees;
    public static boolean jungleCrystalChangesBiome;
    public static boolean jungleCrystalGeneratesMelon;
    public static boolean jungleCrystalGeneratesCocoa;
    public static boolean jungleCrystalGeneratesBushes;
}
