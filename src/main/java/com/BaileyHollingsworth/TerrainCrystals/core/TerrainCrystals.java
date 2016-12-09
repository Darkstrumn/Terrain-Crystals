package com.BaileyHollingsworth.TerrainCrystals.core;

import com.BaileyHollingsworth.TerrainCrystals.Items.TerrainCrystalAbstract;
import com.BaileyHollingsworth.TerrainCrystals.core.VersionChecker.VersionChecker;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemStack;
import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@Mod(modid = TerrainCrystals.MODID, name = TerrainCrystals.MODNAME, useMetadata = true, version = TerrainCrystals.VERSION)


public class TerrainCrystals {
	public static final String MODID = "terraincrystals";
	public static final String MODNAME = "Terrain Crystals";
	public static final String VERSION = "1.2.2";
	public static final String URL = "https://raw.githubusercontent.com/baileyholl/Terrain-Crystals/master/1.11%20Update%20Handler";
	
	public static CreativeTabs tab = new CreativeTabs("Terrain Crystals") {
		@Override
		public ItemStack getTabIconItem() {
			return InitItems.terrainCrystalPlains.func_190903_i();
		}
		@Override
		@SideOnly(Side.CLIENT)
	    public String getTranslatedTabLabel()
	    {
	        return this.getTabLabel();
	    }
	};
	
	@SidedProxy
	public static CommonProxy proxy;
	
	@Mod.Instance
	public static TerrainCrystals instance;
	
	@Mod.EventHandler
	public void preInit(FMLPreInitializationEvent event){
		//logger = event.getModLog();
		proxy.preInit(event);
	}
	
	@Mod.EventHandler
	public void init(FMLInitializationEvent e){
		proxy.init(e);
	}
	
	@Mod.EventHandler
	public void postInit(FMLPostInitializationEvent e){
		proxy.postInit(e);
	}
	
	public static class CommonProxy {
		public void preInit(FMLPreInitializationEvent e){
			Configuration config = new Configuration(e.getSuggestedConfigurationFile());
			ConfigurationFile.configFile(config);
			//Initialization of Blocks and Items
			InitItems.init();
		}
		public void init(FMLInitializationEvent e){
			InitItems.recipes();
			TerrainCrystalAbstract.initReplaceableBlocks();
			TerrainCrystalAbstract.initInvalidSpaces();
		}
		public void postInit(FMLPostInitializationEvent e){
			
		}
	}
	
	public static class ClientProxy extends CommonProxy{
		@Override
		public void preInit(FMLPreInitializationEvent e){
			super.preInit(e);
			//Initialization of models
			InitItems.initModels();
			new VersionChecker().init();
		}
	}
	
	public static class ServerProxy extends CommonProxy{

	}
}