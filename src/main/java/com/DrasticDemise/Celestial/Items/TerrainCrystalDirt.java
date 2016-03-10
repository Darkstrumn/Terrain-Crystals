package com.DrasticDemise.Celestial.Items;

import com.DrasticDemise.Celestial.blocks.CStorageCellTileEntity;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.registry.GameRegistry;

public class TerrainCrystalDirt extends Item{
	public TerrainCrystalDirt(){
		setUnlocalizedName("terrainCrystalDirt");
		setRegistryName("terrainCrystalDirt");
		setCreativeTab(CreativeTabs.tabBlock);
		setHarvestLevel("stone", 0);
        GameRegistry.registerItem(this);
	}
	@Override
	public ItemStack onItemRightClick(ItemStack itemStackIn, World worldIn, EntityPlayer playerIn)
    {
		System.out.println("Invoking onItemRightClick");
        return itemStackIn;
    }
}
