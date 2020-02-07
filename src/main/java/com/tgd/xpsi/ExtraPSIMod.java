package com.tgd.xpsi;

import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import org.apache.logging.log4j.Logger;

@Mod(modid = ExtraPSIMod.MODID, name = ExtraPSIMod.NAME, version = ExtraPSIMod.VERSION, dependencies = "required-after:psi@[r1.1-76,);")
public class ExtraPSIMod {
    public static final String MODID = "extrapsi";
    public static final String NAME = "Extra PSI";
    public static final String VERSION = "1.0";

    public static Logger logger;

    @Mod.Instance
    public static ExtraPSIMod INSTANCE;

    @Mod.EventHandler
    public void preInit(FMLPreInitializationEvent event) {
        // TODO
        // gui handlers???

        ExtraPSISpells.registerAll();
    }

    @Mod.EventHandler
    public void init(FMLInitializationEvent event) {
    }
}
