package com.tgd.xpsi.spells.tricks;

import com.tgd.xpsi.spells.params.ParamString;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.util.text.TextFormatting;
import vazkii.psi.api.spell.Spell;
import vazkii.psi.api.spell.SpellContext;
import vazkii.psi.api.spell.SpellParam;
import vazkii.psi.api.spell.SpellRuntimeException;
import vazkii.psi.api.spell.piece.PieceTrick;
import vazkii.psi.common.lib.LibPieceGroups;

public class PieceTrickAnnounce extends PieceTrick {
    public final static String NAME = "trick_announce";
    public final static String GROUP = LibPieceGroups.ENTITIES_INTRO;

    private SpellParam messageParam;

    public PieceTrickAnnounce(Spell spell) {
        super(spell);
    }

    @Override
    public void initParams() {
        addParam(messageParam = new ParamString(SpellParam.GENERIC_NAME_TARGET, SpellParam.BLUE, false, true));
    }

    @Override
    public Object execute(SpellContext context) throws SpellRuntimeException {
        String message = getParamValue(context, messageParam);

        String label = this.comment;

        String s = "";
        if(message != null)
            s = message;

        if(!label.isEmpty())
            s = TextFormatting.AQUA + "[" + label + "] " + TextFormatting.RESET + s;

        TextComponentString component = new TextComponentString(s);
        context.caster.sendStatusMessage(component, false);

        return null;
    }
}
