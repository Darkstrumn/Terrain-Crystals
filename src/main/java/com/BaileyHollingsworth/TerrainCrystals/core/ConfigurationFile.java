package com.BaileyHollingsworth.TerrainCrystals.core;

import net.minecraftforge.common.config.Configuration;

public class ConfigurationFile {
	
	public static void configFile(Configuration config){
		config.load();


		//Durabilities
		plainsCrystalDurability = config.getInt("Plains Crystal Durability", Configuration.CATEGORY_GENERAL, 7000, 1, Integer.MAX_VALUE, "How many blocks can the crystal generate before breaking. Can go up to integer max.");
		desertCrystalDurability = config.getInt("Desert Crystal Durability", Configuration.CATEGORY_GENERAL, 7000, 1, Integer.MAX_VALUE, "");
		mesaCrystalDurability = config.getInt("Mesa Crystal Durability", Configuration.CATEGORY_GENERAL, 7000, 1, Integer.MAX_VALUE, "");
		mushroomCrystalDurability = config.getInt("Mushroom Crystal Durability", Configuration.CATEGORY_GENERAL, 7000, 1, Integer.MAX_VALUE, "");
		netherCrystalDurability = config.getInt("Nether Crystal Durability", Configuration.CATEGORY_GENERAL, 7000, 1, Integer.MAX_VALUE, "");
		endCrystalDurability = config.getInt("End Crystal Durability", Configuration.CATEGORY_GENERAL, 7000, 1, Integer.MAX_VALUE, "");
        taigaCrystalDurability = config.getInt("Taiga Crystal Durability", Configuration.CATEGORY_GENERAL, 7000, 1, Integer.MAX_VALUE, "");
        plainsIceCrystalDurability = config.getInt("Ice Plains Crystal Durability", Configuration.CATEGORY_GENERAL, 7000, 1, Integer.MAX_VALUE, "");
        jungleCrystalDurability = config.getInt("Jungle Crystal Durability", Configuration.CATEGORY_GENERAL, 7000, 1, Integer.MAX_VALUE, "");
        swampCrystalDurability = config.getInt("Swamp Crystal Durability", Configuration.CATEGORY_GENERAL, 7000, 1, Integer.MAX_VALUE, "");


		//Diameters
		plainsCrystalDiameter = config.getInt("Plains Crystal Diameter", Configuration.CATEGORY_GENERAL, 11, 1, 9999, "Odd numbers work best. Default: 11");
		desertCrystalDiameter = config.getInt("Desert Crystal Diameter", Configuration.CATEGORY_GENERAL, 11, 1, 9999, "");
		mesaCrystalDiameter = config.getInt("Mesa Crystal Diameter", Configuration.CATEGORY_GENERAL, 11, 1, 9999, "");
		mushroomCrystalDiameter = config.getInt("Mushroom Crystal Diameter", Configuration.CATEGORY_GENERAL, 11, 1, 9999, "");
		netherCrystalDiameter = config.getInt("Nether Crystal Diameter", Configuration.CATEGORY_GENERAL, 11, 1, 9999, "");
		endCrystalDiameter = config.getInt("End Crystal Diameter", Configuration.CATEGORY_GENERAL, 11, 1, 9999, "");
        taigaCrystalDiameter = config.getInt("Taiga Crystal Diameter", Configuration.CATEGORY_GENERAL, 11, 1, 9999, "");
        plainsIceCrystalDiameter = config.getInt("Ice Plains Crystal Diameter", Configuration.CATEGORY_GENERAL, 11, 1, 9999, "");
        jungleCrystalDiameter = config.getInt("Jungle Crystal Diameter", Configuration.CATEGORY_GENERAL, 11, 1, 9999, "");
        swampCrystalDiameter = config.getInt("Swamp Crystal Diameter", Configuration.CATEGORY_GENERAL, 11, 1, 9999, "");


		//Changes Biome
		plainsCrystalChangesBiome = config.getBoolean("Plains Crystal Turns Other Biomes Plains", Configuration.CATEGORY_GENERAL, true, "Should this crystal transform other biomes when blocks spawn?");
		desertCrystalChangesBiome = config.getBoolean("Desert Crystal Turns Other Biomes Desert", Configuration.CATEGORY_GENERAL, true, "");
		mesaCrystalChangesBiome = config.getBoolean("Mesa Crystal Turns Other Biomes Mesa", Configuration.CATEGORY_GENERAL, true, "");
		mushroomCrystalChangesBiome = config.getBoolean("Mushroom Crystal Turns Other Biomes Mushroom", Configuration.CATEGORY_GENERAL, true, "");
		netherCrystalChangesBiome = config.getBoolean("Nether Crystal Turns Other Biomes Nether", Configuration.CATEGORY_GENERAL, true, "");
		endCrystalChangesBiome = config.getBoolean("End Crystal Turns Other Biomes End", Configuration.CATEGORY_GENERAL, true, "");
        taigaCrystalChangesBiome = config.getBoolean("Taiga Crystal Turns Other Biomes Cold Taiga", Configuration.CATEGORY_GENERAL, true, "");
        plainsIceCrystalChangesBiome = config.getBoolean("Ice Plains Crystal Turns Other Biomes Ice Plains", Configuration.CATEGORY_GENERAL, true, "");
        jungleCrystalChangesBiome = config.getBoolean("Jungle Crystal Turns Other Biomes Jungle", Configuration.CATEGORY_GENERAL, true, "");
        swampCrystalChangesBiome = config.getBoolean("Swamp Crystal Turns Other Biomes Swamplands", Configuration.CATEGORY_GENERAL, true, "");


		//Foliage
		plainsCrystalGenerateTallGrass = config.getBoolean("Plains Crystal generate tall grass", Configuration.CATEGORY_GENERAL, true, "");
		plainsCrystalGenerateTrees = config.getBoolean("Plains Crystal Spawns Trees", Configuration.CATEGORY_GENERAL, true, "Should the plains biome have a chance to spawn trees? Tall Grass must be enabled.");
		desertCrystalGenerateCactus = config.getBoolean("Desert Crystal Generates Cactus", Configuration.CATEGORY_GENERAL, true, "");
		endCrystalGenerateChorus = config.getBoolean("End Crystal Generates Chorus", Configuration.CATEGORY_GENERAL, true, "");
        taigaCrystalGeneratesTrees = config.getBoolean("Taiga Crystal Generates Spruce Trees", Configuration.CATEGORY_GENERAL, true, "");
        jungleCrystalGeneratesTrees = config.getBoolean("Jungle Crystal Generates Jungle Trees", Configuration.CATEGORY_GENERAL, true, "");
        jungleCrystalGeneratesMelon = config.getBoolean("Jungle Crystal Generates Melon", Configuration.CATEGORY_GENERAL, true, "");
        jungleCrystalGeneratesCocoa = config.getBoolean("Jungle Crystal Generates Cocoa Beans", Configuration.CATEGORY_GENERAL, true, "");
        jungleCrystalGeneratesBushes = config.getBoolean("Jungle Crystal Generates small bushes", Configuration.CATEGORY_GENERAL, true, "");
        swampCrystalGensWater = config.getBoolean("Swamp Crystal Generates water pools", Configuration.CATEGORY_GENERAL, true, "");
        swampCrystalGensClay = config.getBoolean("Swamp Crystal Generates Clay", Configuration.CATEGORY_GENERAL, true, "");

		//Nether Crystal
		netherCrystalRestrictedToNether = config.getBoolean("If The Nether Crystal only works in the Nether", Configuration.CATEGORY_GENERAL, false, "");
		//End Crystal
		endCrystalRestrictedToEnd = config.getBoolean("End Crystal Restricted to End Dimension", Configuration.CATEGORY_GENERAL, false, "");
		//Misc
		generateStone = config.getBoolean("Have the islands generate stone by default", Configuration.CATEGORY_GENERAL, true, "Forces stone to spawn beneath certain crystals");
		generateOres = config.getBoolean("Have stone generating islands also spawn ore", Configuration.CATEGORY_GENERAL, false, "Generate stone must be enabled.");
		stoneSpawnDepth = config.getInt("How far down islands start to spawn stone. Does nothing if GenerateStone is not enabled.", Configuration.CATEGORY_GENERAL, 3, 2, 9999, "Must be at least 2. Keep within range of the island depth. Default: 3");
		versionChecker = config.getBoolean("Version Checker Enabled", Configuration.CATEGORY_CLIENT, true, "If you want to be notified when a new version is available");
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
