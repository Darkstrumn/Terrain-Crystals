package com.DrasticDemise.TerrainCrystals;

import com.DrasticDemise.TerrainCrystals.Items.TerrainCrystalDesert;
import com.DrasticDemise.TerrainCrystals.Items.TerrainCrystalEnd;
import com.DrasticDemise.TerrainCrystals.Items.TerrainCrystalJungle;
import com.DrasticDemise.TerrainCrystals.Items.TerrainCrystalMesa;
import com.DrasticDemise.TerrainCrystals.Items.TerrainCrystalMushroom;
import com.DrasticDemise.TerrainCrystals.Items.TerrainCrystalNether;
import com.DrasticDemise.TerrainCrystals.Items.TerrainCrystalPlains;
import com.DrasticDemise.TerrainCrystals.Items.TerrainCrystalPlainsIceSpikes;
import com.DrasticDemise.TerrainCrystals.Items.TerrainCrystalTaiga;

import net.minecraft.block.Block;
import net.minecraft.client.resources.model.ModelResourceLocation;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.common.registry.GameRegistry;
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
	public static TerrainCrystalMushroom terrainCrystalMushroom;
	public static TerrainCrystalEnd terrainCrystalEnd;
	public static TerrainCrystalTaiga terrainCrystalTaiga;
	public static TerrainCrystalPlainsIceSpikes terrainCrystalPlainsIceSpikes;
	public static TerrainCrystalJungle terrainCrystalJungle;
	//Initializes new dust objects
	public static void init(){
		//ironDust = new IronDust();
		terrainCrystalPlains = new TerrainCrystalPlains();
		terrainCrystalNether = new TerrainCrystalNether();
		terrainCrystalDesert = new TerrainCrystalDesert();
		terrainCrystalMesa = new TerrainCrystalMesa();
		terrainCrystalMushroom = new TerrainCrystalMushroom();
		terrainCrystalEnd = new TerrainCrystalEnd();
		terrainCrystalTaiga = new TerrainCrystalTaiga();
		terrainCrystalPlainsIceSpikes = new TerrainCrystalPlainsIceSpikes();
		terrainCrystalJungle = new TerrainCrystalJungle();
	}
	public static void recipes(){
		ItemStack dirtStack = new ItemStack(Blocks.dirt);
		ItemStack sandStack = new ItemStack(Blocks.sand);
		ItemStack hardenedClayStack  = new ItemStack(Blocks.hardened_clay);
		ItemStack netherrackStack = new ItemStack(Blocks.netherrack);
		ItemStack endStoneStack = new ItemStack(Blocks.end_stone);
		ItemStack snowBlockStack = new ItemStack(Blocks.snow);
		ItemStack iceBlockStack = new ItemStack(Blocks.ice);
		ItemStack grassBlockStack = new ItemStack(Blocks.grass);
		ItemStack cactusStack = new ItemStack(Blocks.cactus);
		ItemStack brownMushBlockStack = new ItemStack(Blocks.brown_mushroom_block);
		ItemStack redMushBlockStack = new ItemStack(Blocks.red_mushroom_block);
		ItemStack snowballStack = new ItemStack(Items.snowball);
		
		ItemStack bRodStack = new ItemStack(Items.blaze_rod);
		ItemStack pearlStack = new ItemStack(Items.ender_pearl);
		
		ItemStack goldBlockStack = new ItemStack(Blocks.gold_block);
		//Plains Crystal
		GameRegistry.addRecipe(
			    new ItemStack(InitItems.terrainCrystalPlains),
			    "xxx",
			    "xwx",
			    "xxx",
			    'w', goldBlockStack,'x', dirtStack);
		//Desert Crystal
		GameRegistry.addRecipe(new ItemStack(InitItems.terrainCrystalDesert),
				"xyx",
				"ywy",
				"xyx",
				'w', goldBlockStack, 'x',sandStack, 'y', cactusStack);
		//Mesa Crystal
		GameRegistry.addRecipe(new ItemStack(InitItems.terrainCrystalMesa),
				"xyx",
				"ywy",
				"xyx",
				'w', goldBlockStack, 'x',hardenedClayStack, 'y', cactusStack);
		//Nether Crystal
		GameRegistry.addRecipe(new ItemStack(InitItems.terrainCrystalNether),
				"xyx",
				"ywy",
				"xyx",
				'w', goldBlockStack, 'x',bRodStack, 'y', netherrackStack);
		//End Crystal
		GameRegistry.addRecipe(new ItemStack(InitItems.terrainCrystalEnd),
				"xyx",
				"ywy",
				"xyx",
				'w', goldBlockStack, 'x',endStoneStack, 'y', pearlStack);
		//Mushroom
		GameRegistry.addRecipe(new ItemStack(InitItems.terrainCrystalMushroom),
				"xyx",
				"ywy",
				"xyx",
				'w', goldBlockStack, 'x',brownMushBlockStack, 'y', redMushBlockStack);
		//Taiga
		GameRegistry.addRecipe(new ItemStack(InitItems.terrainCrystalTaiga),
				"xxx",
				"xwx",
				"xxx",
				'w', goldBlockStack, 'x',grassBlockStack);
		//Ice Spikes Plains
		GameRegistry.addRecipe(new ItemStack(InitItems.terrainCrystalPlainsIceSpikes),
				"yxy",
				"xwx",
				"yxy",
				'w', goldBlockStack, 'x',iceBlockStack, 'y', snowBlockStack);
	}

	@SideOnly(Side.CLIENT)
    public static void initModels() {
        terrainCrystalPlains.initModel();
        terrainCrystalNether.initModel();
        terrainCrystalDesert.initModel();
        terrainCrystalMesa.initModel();
        terrainCrystalMushroom.initModel();
        terrainCrystalEnd.initModel();
        terrainCrystalTaiga.initModel();
        terrainCrystalPlainsIceSpikes.initModel();
        terrainCrystalJungle.initModel();
    }
	//Adds dusts to the ore dictionary
	public static void oreRegistration(){
		//OreDictionary.registerOre("dustIron", ironDust);
	}
}
