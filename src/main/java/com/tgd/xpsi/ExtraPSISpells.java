package com.tgd.xpsi;

import com.tgd.xpsi.spells.constants.PieceConstantString;
import com.tgd.xpsi.spells.selectors.PieceSelectorNameTag;
import com.tgd.xpsi.spells.tricks.PieceTrickAnnounce;
import com.tgd.xpsi.spells.tricks.PieceTrickSetLookVector;
import vazkii.psi.api.PsiAPI;
import vazkii.psi.api.spell.SpellPiece;

import static com.tgd.xpsi.ExtraPSIMod.MODID;

public class ExtraPSISpells {

    public static void registerAll() {
        registerSpell(PieceSelectorNameTag.NAME, PieceSelectorNameTag.GROUP, PieceSelectorNameTag.class);
        registerSpell(PieceConstantString.NAME, PieceConstantString.GROUP, PieceConstantString.class);
        registerSpell(PieceTrickAnnounce.NAME, PieceTrickAnnounce.GROUP, PieceTrickAnnounce.class);
        registerSpell(PieceTrickSetLookVector.NAME, PieceTrickSetLookVector.GROUP, PieceTrickSetLookVector.class);
    }

    public static void registerSpell(String name, String group, Class<? extends SpellPiece> klass) {
        PsiAPI.registerSpellPieceAndTexture(MODID + name, klass);
        PsiAPI.addPieceToGroup(klass, group, false);
    }
}
