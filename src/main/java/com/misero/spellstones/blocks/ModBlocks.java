package com.misero.spellstones.blocks;

import com.misero.spellstones.blocks.focus.BlockFocus;
import com.misero.spellstones.blocks.focus.EnumSpellstoneColor;
import com.misero.spellstones.blocks.lenschamber.BlockLensChamber;
import net.minecraft.block.Block;

import java.util.ArrayList;
import java.util.List;

public class ModBlocks {
    public static final List<Block> BLOCKS = new ArrayList<Block>();

    public static final Block ORE = new BlockOre();

    public static final Block FOCUS_RED = new BlockFocus(EnumSpellstoneColor.RED);
    public static final Block FOCUS_GREEN = new BlockFocus(EnumSpellstoneColor.GREEN);
    public static final Block FOCUS_BLUE = new BlockFocus(EnumSpellstoneColor.BLUE);
    public static final Block FOCUS_BRIGHT = new BlockFocus(EnumSpellstoneColor.BRIGHT);
    public static final Block FOCUS_YELLOW = new BlockFocus(EnumSpellstoneColor.YELLOW);
    public static final Block FOCUS_ORANGE = new BlockFocus(EnumSpellstoneColor.ORANGE);
    public static final Block FOCUS_PURPLE = new BlockFocus(EnumSpellstoneColor.PURPLE);

    public static final Block LENS_CHAMBER = new BlockLensChamber();

    public static void init(){

    }
}
