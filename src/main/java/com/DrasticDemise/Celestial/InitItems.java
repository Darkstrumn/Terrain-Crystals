package com.DrasticDemise.Celestial;

import com.DrasticDemise.Celestial.Items.TerrainCrystalDirt;

import net.minecraftforge.oredict.OreDictionary;

public class InitItems {
	
	//Creates dust objects
	//public static IronDust ironDust;
	public static TerrainCrystalDirt dirtCrystal;
	//Initializes new dust objects
	public static void init(){
		//ironDust = new IronDust();
		dirtCrystal = new TerrainCrystalDirt();
	}
	
	//Adds dusts to the ore dictionary
	public static void oreRegistration(){
		//OreDictionary.registerOre("dustIron", ironDust);
	}
}
