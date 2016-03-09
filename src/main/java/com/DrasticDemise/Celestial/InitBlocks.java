package com.DrasticDemise.Celestial;

import com.DrasticDemise.Celestial.blocks.CelestialStorageCell;

public class InitBlocks {

	//Creates dust objects
	//public static IronDust ironDust;
	public static CelestialStorageCell CPStorage;
	
	//Initializes new dust objects
	public static void init(){
		//ironDust = new IronDust();
		 CPStorage = new CelestialStorageCell();
	}
	
	//Adds dusts to the ore dictionary
	public static void oreRegistration(){
		//OreDictionary.registerOre("dustIron", ironDust);
	}

}
