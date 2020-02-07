package com.tgd.xpsi;

import net.minecraftforge.common.config.Config;

@Config(modid = ExtraPSIMod.MODID)
public class ExtraPSIConfig {
    @Config.Name("enabled")
    @Config.Comment("Should this mod even do anything?")
    public static boolean enabled = true;

    @Config.Name("maxStringLength")
    @Config.Comment("Maximum length of strings.")
    public static int maxStringLength = 20;
}
