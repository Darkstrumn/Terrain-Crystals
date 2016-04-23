package com.DrasticDemise.TerrainCrystals;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayer.EnumChatVisibility;
import net.minecraftforge.fml.common.FMLCommonHandler;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent.ClientTickEvent;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.Style;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraft.util.text.TextFormatting;

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
