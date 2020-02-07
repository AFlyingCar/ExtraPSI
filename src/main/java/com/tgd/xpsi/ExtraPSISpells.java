package com.tgd.xpsi;

import com.tgd.xpsi.spells.constants.PieceConstantString;
import com.tgd.xpsi.spells.selectors.PieceSelectorNameTag;
import vazkii.psi.api.PsiAPI;
import vazkii.psi.api.spell.SpellPiece;

import static com.tgd.xpsi.ExtraPSIMod.MODID;

public class ExtraPSISpells {

    public static void registerAll() {
        registerSpell(PieceSelectorNameTag.NAME, PieceSelectorNameTag.GROUP, PieceSelectorNameTag.class);
        registerSpell(PieceConstantString.NAME, PieceConstantString.GROUP, PieceConstantString.class);
    }

    public static void registerSpell(String name, String group, Class<? extends SpellPiece> klass) {
        PsiAPI.registerSpellPieceAndTexture(MODID + name, klass);
        PsiAPI.addPieceToGroup(klass, group, false);
    }
}
