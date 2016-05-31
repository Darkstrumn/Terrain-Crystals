package com.DrasticDemise.TerrainCrystals;

import com.DrasticDemise.TerrainCrystals.Items.*;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class InitItems {
	
	public static TerrainCrystalPlains terrainCrystalPlains;
	public static TerrainCrystalNether terrainCrystalNether;
	public static TerrainCrystalDesert terrainCrystalDesert;
	public static TerrainCrystalMesa terrainCrystalMesa;
	public static TerrainCrystalMushroom terrainCrystalMushroom;
	public static TerrainCrystalEnd terrainCrystalEnd;
	public static TerrainCrystalTaiga terrainCrystalTaiga;
	public static TerrainCrystalPlainsIceSpikes terrainCrystalPlainsIceSpikes;
	public static TerrainCrystalJungle terrainCrystalJungle;
	public static TerrainCrystalSwamp terrainCrystalSwamp;
	public static void init(){
		terrainCrystalPlains = new TerrainCrystalPlains();
		terrainCrystalNether = new TerrainCrystalNether();
		terrainCrystalDesert = new TerrainCrystalDesert();
		terrainCrystalMesa = new TerrainCrystalMesa();
		terrainCrystalMushroom = new TerrainCrystalMushroom();
		terrainCrystalEnd = new TerrainCrystalEnd();
		terrainCrystalTaiga = new TerrainCrystalTaiga();
		terrainCrystalPlainsIceSpikes = new TerrainCrystalPlainsIceSpikes();
		terrainCrystalJungle = new TerrainCrystalJungle();
		terrainCrystalSwamp = new TerrainCrystalSwamp();
	}
	public static void recipes(){
		ItemStack dirtStack = new ItemStack(Blocks.DIRT);
		ItemStack sandStack = new ItemStack(Blocks.SAND);
		ItemStack hardenedClayStack  = new ItemStack(Blocks.HARDENED_CLAY);
		ItemStack netherrackStack = new ItemStack(Blocks.NETHERRACK);
		ItemStack endStoneStack = new ItemStack(Blocks.END_STONE);
		ItemStack snowBlockStack = new ItemStack(Blocks.SNOW);
		ItemStack iceBlockStack = new ItemStack(Blocks.ICE);
		ItemStack grassBlockStack = new ItemStack(Blocks.GRASS);
		ItemStack cactusStack = new ItemStack(Blocks.CACTUS);
		ItemStack brownMushBlockStack = new ItemStack(Blocks.BROWN_MUSHROOM_BLOCK);
		ItemStack redMushBlockStack = new ItemStack(Blocks.RED_MUSHROOM_BLOCK);
		ItemStack snowballStack = new ItemStack(Items.SNOWBALL);
		ItemStack clayBlockStack = new ItemStack(Blocks.CLAY);
		ItemStack slimeBallStack = new ItemStack(Items.SLIME_BALL);
		ItemStack bRodStack = new ItemStack(Items.BLAZE_ROD);
		ItemStack pearlStack = new ItemStack(Items.ENDER_PEARL);
		ItemStack melonSeedStack = new ItemStack(Items.MELON_SEEDS);
		
		ItemStack goldBlockStack = new ItemStack(Blocks.GOLD_BLOCK);
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
		//Swamp 
		GameRegistry.addRecipe(new ItemStack(InitItems.terrainCrystalSwamp),
				"yxy",
				"xwx",
				"yxy",
				'w', goldBlockStack, 'x',clayBlockStack, 'y', slimeBallStack);
		//Jungle
		GameRegistry.addRecipe(new ItemStack(InitItems.terrainCrystalJungle),
				"xxx",
				"xwx",
				"xxx",
				'w', goldBlockStack, 'x',melonSeedStack);
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
        terrainCrystalSwamp.initModel();
    }
}
