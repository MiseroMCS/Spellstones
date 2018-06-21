package com.misero.spellstones.items;

import com.misero.spellstones.Spellstones;
import com.misero.spellstones.blocks.focus.EnumSpellstoneColor;
import com.misero.spellstones.util.IHasModel;
import com.misero.spellstones.util.NBTHelper;
import net.minecraft.client.renderer.ItemMeshDefinition;
import net.minecraft.client.renderer.block.model.ModelBakery;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.util.ArrayList;
import java.util.List;

public class ItemSpellstone extends Item implements IHasModel {
    public ItemSpellstone() {
        setUnlocalizedName("spellstone");
        setRegistryName(Spellstones.MODID, "spellstone");
        setHasSubtypes(true);
        setMaxDamage(0);
        ModItems.ITEMS.add(this);
    }
    @Override
    public String getUnlocalizedName(ItemStack stack){
        return "item.spellstone." + EnumSpellstoneColor.fromMeta(stack.getMetadata()).name;
    }
    @Override
    @SideOnly(Side.CLIENT)
    public void registerModels() {
        List<ModelResourceLocation> locations = new ArrayList<>();
        for(EnumSpellstoneColor color : EnumSpellstoneColor.values()){
            locations.add(new ModelResourceLocation("spellstones:spellstone/spellstone_" + color.name, "inventory"));
        }
        ModelBakery.registerItemVariants(this, locations.toArray(new ModelResourceLocation[locations.size()]));
        ModelLoader.setCustomMeshDefinition(this, new ItemMeshDefinition() {
            @Override
            public ModelResourceLocation getModelLocation(ItemStack stack){
                int colorMeta = stack.getMetadata();
                EnumSpellstoneColor color = EnumSpellstoneColor.fromMeta(colorMeta);
                return new ModelResourceLocation("spellstones:spellstone/spellstone_" + color.name, "inventory");
            }
        });
    }
}
