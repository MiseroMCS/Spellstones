package com.misero.spellstones.misc;

import com.misero.spellstones.items.ModItems;
import com.misero.spellstones.util.NBTHelper;
import net.minecraft.entity.IMerchant;
import net.minecraft.entity.passive.EntityVillager;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.ResourceLocation;
import net.minecraft.village.MerchantRecipe;
import net.minecraft.village.MerchantRecipeList;
import net.minecraftforge.fml.common.registry.ForgeRegistries;
import net.minecraftforge.fml.common.registry.VillagerRegistry;

import java.util.Random;

public class VillagerTrades implements EntityVillager.ITradeList {
    @Override
    public void addMerchantRecipe(IMerchant merchant, MerchantRecipeList recipeList, Random random) {
        ItemStack spellstone_trade = new ItemStack(ModItems.SPELLSTONE);
        recipeList.add(new MerchantRecipe(new ItemStack(Items.EMERALD,3), spellstone_trade));
    }
}
