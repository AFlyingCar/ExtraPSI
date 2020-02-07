package com.tgd.xpsi.spells.params;

import vazkii.psi.api.spell.param.ParamSpecific;

public class ParamString extends ParamSpecific {
    public ParamString(String name, int color, boolean canDisable, boolean constant) {
        super(name, color, canDisable, constant);
    }

    @Override
    protected Class<?> getRequiredType() {
        return String.class;
    }
}
