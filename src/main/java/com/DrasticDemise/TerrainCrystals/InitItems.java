package com.DrasticDemise.TerrainCrystals;

import com.DrasticDemise.TerrainCrystals.Items.TerrainCrystalDesert;
import com.DrasticDemise.TerrainCrystals.Items.TerrainCrystalEnd;
import com.DrasticDemise.TerrainCrystals.Items.TerrainCrystalMesa;
import com.DrasticDemise.TerrainCrystals.Items.TerrainCrystalMushroom;
import com.DrasticDemise.TerrainCrystals.Items.TerrainCrystalNether;
import com.DrasticDemise.TerrainCrystals.Items.TerrainCrystalPlains;

import net.minecraft.client.resources.model.ModelResourceLocation;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.minecraftforge.oredict.OreDictionary;

public class InitItems {
	
	//Creates dust objects
	//public static IronDust ironDust;
	public static TerrainCrystalPlains terrainCrystalPlains;
	public static TerrainCrystalNether terrainCrystalNether;
	public static TerrainCrystalDesert terrainCrystalDesert;
	public static TerrainCrystalMesa terrainCrystalMesa;
	public static TerrainCrystalMushroom terrainCrystalPlainsMushroom;
	public static TerrainCrystalEnd terrainCrystalEnd;
	//Initializes new dust objects
	public static void init(){
		//ironDust = new IronDust();
		terrainCrystalPlains = new TerrainCrystalPlains();
		terrainCrystalNether = new TerrainCrystalNether();
		terrainCrystalDesert = new TerrainCrystalDesert();
		terrainCrystalMesa = new TerrainCrystalMesa();
		terrainCrystalPlainsMushroom = new TerrainCrystalMushroom();
		terrainCrystalEnd = new TerrainCrystalEnd();
	}
	@SideOnly(Side.CLIENT)
    public static void initModels() {
        terrainCrystalPlains.initModel();
        terrainCrystalNether.initModel();
        terrainCrystalDesert.initModel();
        terrainCrystalMesa.initModel();
        terrainCrystalPlainsMushroom.initModel();
        terrainCrystalEnd.initModel();
    }
	//Adds dusts to the ore dictionary
	public static void oreRegistration(){
		//OreDictionary.registerOre("dustIron", ironDust);
	}
}
