package com.misero.spellstones.blocks.focus;

import com.misero.spellstones.blocks.focus.properties.*;
import net.minecraft.util.IStringSerializable;
import net.minecraft.block.Block;

public enum EnumSpellstoneColor implements IStringSerializable {
    DIM(0, "dim", null),
    RED(1, "red", RedFocusProperties.class),
    GREEN(2, "green", GreenFocusProperties.class),
    BLUE(3, "blue", BlueFocusProperties.class),
    BRIGHT(4, "bright", BrightFocusProperties.class),
    YELLOW(5, "yellow", YellowFocusProperties.class),
    ORANGE(6, "orange", OrangeFocusProperties.class),
    PURPLE(7, "purple", PurpleFocusProperties.class);

    public int index;
    public String name;
    public Class<? extends FocusPropertyHolder> propertyClass;

    EnumSpellstoneColor(int index, String name, Class<? extends FocusPropertyHolder> propertyClass){
        this.index = index;
        this.name = name;
        this.propertyClass = propertyClass;
    }

    public static EnumSpellstoneColor fromMeta(int meta){
        for(EnumSpellstoneColor color : EnumSpellstoneColor.values()){
            if(color.index == meta){
                return color;
            }
        }
        return DIM;
    }

    @Override
    public String getName() {
        return this.name;
    }
}
