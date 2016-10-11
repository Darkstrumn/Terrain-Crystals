package com.DrasticDemise.TerrainCrystals.core;

import net.minecraftforge.common.config.Configuration;

public class ConfigurationFile {
	public static void configFile(Configuration config){
		config.load();
		
		//Plains Crystal
		int propPlainsCrystalDurability = config.getInt("Plains Crystal Durability", Configuration.CATEGORY_GENERAL, 7000, 1, Integer.MAX_VALUE, "How many blocks can the crystal generate before breaking. Can go up to integer max.");
		plainsCrystalDurability = propPlainsCrystalDurability;
		
		int propPlainsCrystalDiameter = config.getInt("Plains Crystal Diameter", Configuration.CATEGORY_GENERAL, 11, 1, 9999, "Odd numbers work best. Default: 11");
		plainsCrystalDiameter = propPlainsCrystalDiameter;
		
		boolean propPlainsCrystalGenerateTallGrass = config.getBoolean("Plains Crystal generate tall grass", Configuration.CATEGORY_GENERAL, true, "Should platforms have a bonemeal effect on the surface?");
		plainsCrystalGenerateTallGrass = propPlainsCrystalGenerateTallGrass;
		//This needs tested
		boolean propPlainsCrystalGenerateTrees = config.getBoolean("Plains Crystal Spawns Trees", Configuration.CATEGORY_GENERAL, true, "Should the plains biome have a chance to spawn trees? Tall Grass must be enabled.");
		plainsCrystalGenerateTrees = propPlainsCrystalGenerateTrees;
		
		boolean propPlainsCrystalChangesBiome = config.getBoolean("Plains Crystal Turns Other Biomes Plains", Configuration.CATEGORY_GENERAL, true, "Should the plains biome change biomes? Changes biomes in each location of a spawned surface block.");
		plainsCrystalChangesBiome = propPlainsCrystalChangesBiome;
		//Desert Crystal
		int propDesertCrystalDurability = config.getInt("Desert Crystal Durability", Configuration.CATEGORY_GENERAL, 7000, 1, Integer.MAX_VALUE, "How many blocks can the crystal generate before breaking. Can go up to integer max.");
		desertCrystalDurability = propDesertCrystalDurability;
		
		int propDesertCrystalDiameter = config.getInt("Desert Crystal Diameter", Configuration.CATEGORY_GENERAL, 11, 1, 9999, "Odd numbers work best. Default: 11");
		desertCrystalDiameter = propDesertCrystalDiameter;
		
		boolean propDesertCrystalGenerateCactus = config.getBoolean("Desert Crystal Generates Cactus", Configuration.CATEGORY_GENERAL, true, "Should platform have cactus on it?");
		desertCrystalGenerateCactus = propDesertCrystalGenerateCactus;
		
		boolean propDesertCrystalChangesBiome = config.getBoolean("Desert Crystal Turns Other Biomes Desert", Configuration.CATEGORY_GENERAL, true, "Changes biomes in each location of a spawned surface block.");
		desertCrystalChangesBiome = propDesertCrystalChangesBiome;
		
		//Mesa Crystal
		int propMesaCrystalDurability = config.getInt("Mesa Crystal Durability", Configuration.CATEGORY_GENERAL, 7000, 1, Integer.MAX_VALUE, "How many blocks can the crystal generate before breaking. Can go up to integer max.");
		mesaCrystalDurability = propMesaCrystalDurability;
		
		int propMesaCrystalDiameter = config.getInt("Mesa Crystal Diameter", Configuration.CATEGORY_GENERAL, 11, 1, 9999, "Odd numbers work best. Default: 11");
		mesaCrystalDiameter = propMesaCrystalDiameter;
		boolean propMesaCrystalChangesBiome = config.getBoolean("Mesa Crystal Turns Other Biomes Mesa", Configuration.CATEGORY_GENERAL, true, "Changes biomes in each location of a spawned surface block.");
		mesaCrystalChangesBiome = propMesaCrystalChangesBiome;
		//Mushroom Crystal
		int propMushroomCrystalDurability = config.getInt("Mushroom Crystal Durability", Configuration.CATEGORY_GENERAL, 7000, 1, Integer.MAX_VALUE, "How many blocks can the crystal generate before breaking. Can go up to integer max.");
		mushroomCrystalDurability = propMushroomCrystalDurability;
		
		int propMushroomCrystalDiameter = config.getInt("Mushroom Crystal Diameter", Configuration.CATEGORY_GENERAL, 11, 1, 9999, "Odd numbers work best. Default: 11");
		mushroomCrystalDiameter = propMushroomCrystalDiameter;
		
		boolean propMushroomCrystalChangesBiome = config.getBoolean("Mushroom Crystal Turns Other Biomes Mushroom", Configuration.CATEGORY_GENERAL, true, "Changes biomes in each location of a spawned surface block.");
		mushroomCrystalChangesBiome = propMushroomCrystalChangesBiome;
		//Nether Crystal
		int propNetherCrystalDurability = config.getInt("Nether Crystal Durability", Configuration.CATEGORY_GENERAL, 7000, 1, Integer.MAX_VALUE, "How many blocks can the crystal generate before breaking. Can go up to integer max.");
		netherCrystalDurability = propNetherCrystalDurability;
		
		int propNetherCrystalDiameter = config.getInt("Nether Crystal Diameter", Configuration.CATEGORY_GENERAL, 11, 1, 9999, "Odd numbers work best. Default: 11");
		netherCrystalDiameter = propNetherCrystalDiameter;
		
		boolean propNetherCrystalChangesBiome = config.getBoolean("Nether Crystal Turns Other Biomes Nether", Configuration.CATEGORY_GENERAL, true, "Changes biomes in each location of a spawned surface block.");
		netherCrystalChangesBiome = propNetherCrystalChangesBiome;
		
		//End Crystal
		int propEndCrystalDurability = config.getInt("End Crystal Durability", Configuration.CATEGORY_GENERAL, 7000, 1, Integer.MAX_VALUE, "How many blocks can the crystal generate before breaking. Can go up to integer max.");
		endCrystalDurability = propEndCrystalDurability;
		
		int propEndCrystalDiameter = config.getInt("End Crystal Diameter", Configuration.CATEGORY_GENERAL, 11, 1, 9999, "Odd numbers work best. Default: 11");
		endCrystalDiameter = propEndCrystalDiameter;
		
		boolean propEndCrystalGenerateObsidianSpikes = config.getBoolean("End Crystal Generates Obsidian Spikes", Configuration.CATEGORY_GENERAL, true, "Should platforms have ruin-like structures on top?");
		endCrystalGenerateObsidianSpikes = propEndCrystalGenerateObsidianSpikes;
		
		boolean propEndCrystalChangesBiome = config.getBoolean("End Crystal Turns Other Biomes End", Configuration.CATEGORY_GENERAL, true, "Changes biomes in each location of a spawned surface block.");
		endCrystalChangesBiome = propEndCrystalChangesBiome;
		
		
		int propTaigaCrystalDurability = config.getInt("Taiga Crystal Durability", Configuration.CATEGORY_GENERAL, 7000, 1, Integer.MAX_VALUE, "How many blocks can the crystal generate before breaking. Can go up to integer max.");
		taigaCrystalDurability = propTaigaCrystalDurability;
		
		int propTaigaCrystalDiameter = config.getInt("Taiga Crystal Diameter", Configuration.CATEGORY_GENERAL, 11, 1, 9999, "Odd numbers work best. Default: 11");
		taigaCrystalDiameter = propTaigaCrystalDiameter;
		
		boolean propTaigaCrystalGeneratesTrees = config.getBoolean("Taiga Crystal Generates Trees", Configuration.CATEGORY_GENERAL, true, "Should platform have spruce trees on it?");
		taigaCrystalGeneratesTrees = propTaigaCrystalGeneratesTrees;
		
		boolean propTaigaCrystalChangesBiome = config.getBoolean("Taiga Crystal Turns Other Biomes Cold Taiga", Configuration.CATEGORY_GENERAL, true, "Changes biomes in each location of a spawned surface block.");
		taigaCrystalChangesBiome = propTaigaCrystalChangesBiome;
		
		//Ice Plains Spikes
		int propPlainsIceCrystalDurability = config.getInt("Ice Plains Crystal Durability", Configuration.CATEGORY_GENERAL, 7000, 1, Integer.MAX_VALUE, "How many blocks can the crystal generate before breaking. Can go up to integer max.");
		plainsIceCrystalDurability = propPlainsIceCrystalDurability;
		
		int propPlainsIceCrystalDiameter = config.getInt("Ice Plains Crystal Diameter", Configuration.CATEGORY_GENERAL, 11, 1, 9999, "Odd numbers work best. Default: 11");
		plainsIceCrystalDiameter = propPlainsIceCrystalDiameter;
		
		boolean propPlainsIceCrystalChangesBiome = config.getBoolean("Ice Plains Crystal Turns Other Biomes Ice Plains", Configuration.CATEGORY_GENERAL, true, "Changes biomes in each location of a spawned surface block.");
		plainsIceCrystalChangesBiome = propPlainsIceCrystalChangesBiome;
		
		//Jungle
		int propJungleCrystalDurability = config.getInt("Jungle Crystal Durability", Configuration.CATEGORY_GENERAL, 7000, 1, Integer.MAX_VALUE, "How many blocks can the crystal generate before breaking. Can go up to integer max.");
		jungleCrystalDurability = propJungleCrystalDurability;
		
		int propJungleCrystalDiameter = config.getInt("Jungle Crystal Diameter", Configuration.CATEGORY_GENERAL, 11, 1, 9999, "Odd numbers work best. Default: 11");
		jungleCrystalDiameter = propJungleCrystalDiameter;
		
		boolean propJungleCrystalChangesBiome = config.getBoolean("Jungle Crystal Turns Other Biomes Jungle", Configuration.CATEGORY_GENERAL, true, "Changes biomes in each location of a spawned surface block.");
		jungleCrystalChangesBiome = propJungleCrystalChangesBiome;
		
		boolean propJungleCrystalGeneratesTrees = config.getBoolean("Jungle Crystal Generates Trees", Configuration.CATEGORY_GENERAL, true, "Should platform have spruce trees on it?");
		jungleCrystalGeneratesTrees = propJungleCrystalGeneratesTrees;
		
		boolean propJungleCrystalGeneratesMelon = config.getBoolean("Jungle Crystal Generates Melon", Configuration.CATEGORY_GENERAL, true, "Should platform have melons on it?");
		jungleCrystalGeneratesMelon = propJungleCrystalGeneratesMelon;
		
		boolean propJungleCrystalGeneratesCocoa = config.getBoolean("Jungle Crystal Generates Cocoa Beans", Configuration.CATEGORY_GENERAL, true, "Should Jungle trees have cocoa beans?");
		jungleCrystalGeneratesCocoa = propJungleCrystalGeneratesCocoa;
		
		boolean propJungleCrystalGeneratesBushes = config.getBoolean("Jungle Crystal Generates small bushes", Configuration.CATEGORY_GENERAL, true, "Should platform have bushes?");
		jungleCrystalGeneratesBushes = propJungleCrystalGeneratesBushes;
		
		int propSwampCrystalDurability = config.getInt("Swamp Crystal Durability", Configuration.CATEGORY_GENERAL, 7000, 1, Integer.MAX_VALUE, "How many blocks can the crystal generate before breaking. Can go up to integer max.");
		swampCrystalDurability = propSwampCrystalDurability;
		
		int propSwampCrystalDiameter = config.getInt("Swamp Crystal Diameter", Configuration.CATEGORY_GENERAL, 11, 1, 9999, "Odd numbers work best. Default: 11");
		swampCrystalDiameter = propSwampCrystalDiameter;
		
		boolean propSwampCrystalGensWater = config.getBoolean("Swamp Crystal Generates water pools", Configuration.CATEGORY_GENERAL, true, "Creates small water pools");
		swampCrystalGensWater = propSwampCrystalGensWater;
		
		boolean propSwampCrystalGensClay = config.getBoolean("Swamp Crystal Generates Clay", Configuration.CATEGORY_GENERAL, true, "If water pools can generate clay");
		swampCrystalGensClay = propSwampCrystalGensClay;
		
		boolean propSwampCrystalChangesBiome = config.getBoolean("Swamp Crystal Turns Other Biomes Swamplands", Configuration.CATEGORY_GENERAL, true, "Changes biomes in each location of a spawned surface block.");
		swampCrystalChangesBiome = propSwampCrystalChangesBiome;
		
		boolean propGenerateStone = config.getBoolean("Have the islands generate stone by default", Configuration.CATEGORY_GENERAL, true, "Forces stone to spawn beneath certain crystals");
		generateStone = propGenerateStone;
		
		boolean propGenerateOres = config.getBoolean("Have stone generating islands also spawn ore", Configuration.CATEGORY_GENERAL, false, "Generate stone must be enabled.");
		generateOres = propGenerateOres;
		
		int propStoneSpawnDepth = config.getInt("How far down islands start to spawn stone. Does nothing if GenerateStone is not enabled.", Configuration.CATEGORY_GENERAL, 3, 2, 9999, "Must be atleast 2. Keep within range of the island depth. Default: 3");;
		stoneSpawnDepth = propStoneSpawnDepth;
		
		boolean propVersionChecker = config.getBoolean("Version Checker Enabled", Configuration.CATEGORY_CLIENT, true, "If you want to be notified when a new version is available");
		versionChecker = propVersionChecker;
		
		if(config.hasChanged()){
			config.save();
		}
	}
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
	public static boolean endCrystalGenerateObsidianSpikes;
    public static boolean endCrystalChangesBiome;
    //Nether
    public static int netherCrystalDurability;
	public static int netherCrystalDiameter;
    public static boolean netherCrystalChangesBiome;
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
