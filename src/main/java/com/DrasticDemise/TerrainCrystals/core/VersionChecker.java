package com.DrasticDemise.TerrainCrystals.core;

import java.io.IOException;

import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraftforge.fml.common.FMLCommonHandler;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent.ClientTickEvent;

public class VersionChecker {
	public static String version = "";
	public static boolean doneChecking = false;
	
	public void init() {
		new ThreadVersionChecker();
		FMLCommonHandler.instance().bus().register(this);
	}
	
	@SubscribeEvent
	public void onTick(ClientTickEvent event) throws IOException {
		if(!doneChecking && Minecraft.getMinecraft().thePlayer != null){
			EntityPlayer player = Minecraft.getMinecraft().thePlayer;
			if(!VersionChecker.version.equals(TerrainCrystals.VERSION) && ConfigurationFile.versionChecker){
				System.out.println("VC: " + VersionChecker.version + " " + "TC V: " + TerrainCrystals.VERSION);
				player.addChatComponentMessage(new TextComponentTranslation("There is a new version for Terrain Crystals Available!"));
			}
			VersionChecker.doneChecking = true;
		}
	}
}
