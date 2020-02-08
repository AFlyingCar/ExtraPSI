package com.tgd.xpsi.spells.tricks;

import com.tgd.xpsi.spells.params.ParamString;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.util.text.TextFormatting;
import vazkii.psi.api.internal.Vector3;
import vazkii.psi.api.spell.Spell;
import vazkii.psi.api.spell.SpellContext;
import vazkii.psi.api.spell.SpellParam;
import vazkii.psi.api.spell.SpellRuntimeException;
import vazkii.psi.api.spell.param.ParamEntity;
import vazkii.psi.api.spell.param.ParamNumber;
import vazkii.psi.api.spell.param.ParamVector;
import vazkii.psi.api.spell.piece.PieceTrick;
import vazkii.psi.common.lib.LibPieceGroups;

public class PieceTrickSetLookVector extends PieceTrick {
    public PieceTrickSetLookVector(Spell spell) {
        super(spell);
    }

    public final static String NAME = "trick_set_look_vector";
    public final static String GROUP = LibPieceGroups.VECTORS_INTRO;

    private SpellParam targetParam;
    private SpellParam lookVecParam;
    private SpellParam speedParam;

    @Override
    public void initParams() {
        addParam(targetParam = new ParamEntity(SpellParam.GENERIC_NAME_TARGET, SpellParam.BLUE, false, false));
        addParam(lookVecParam = new ParamVector(SpellParam.GENERIC_NAME_VECTOR1, SpellParam.RED, false, false));
        addParam(speedParam = new ParamNumber(SpellParam.GENERIC_NAME_NUMBER, SpellParam.GREEN, false, true));
    }

    @Override
    public Object execute(SpellContext context) throws SpellRuntimeException {
        Entity target = getParamValue(context, targetParam);
        Vector3 lookVec = getParamValue(context, lookVecParam);
        double speed = getParamValue(context, speedParam);

        if(lookVec != null && target instanceof EntityLiving) {
            ((EntityLiving) target).getLookHelper().setLookPosition(lookVec.x, lookVec.y, lookVec.z, (float)speed, (float) speed);
        }

        return null;
    }
}
