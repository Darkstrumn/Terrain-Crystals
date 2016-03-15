package com.DrasticDemise.Celestial;

import com.DrasticDemise.Celestial.Items.TerrainCrystalPlains;

import net.minecraftforge.oredict.OreDictionary;

public class InitItems {
	
	//Creates dust objects
	//public static IronDust ironDust;
	public static TerrainCrystalPlains dirtCrystal;
	//Initializes new dust objects
	public static void init(){
		//ironDust = new IronDust();
		dirtCrystal = new TerrainCrystalPlains();
	}
	
	//Adds dusts to the ore dictionary
	public static void oreRegistration(){
		//OreDictionary.registerOre("dustIron", ironDust);
	}
}
