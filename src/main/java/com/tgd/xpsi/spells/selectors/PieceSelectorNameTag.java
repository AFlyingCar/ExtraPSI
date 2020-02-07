package com.tgd.xpsi.spells.selectors;

import com.google.common.base.Predicate;
import com.tgd.xpsi.spells.params.ParamString;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.math.AxisAlignedBB;
import vazkii.psi.api.internal.Vector3;
import vazkii.psi.api.spell.Spell;
import vazkii.psi.api.spell.SpellContext;
import vazkii.psi.api.spell.SpellParam;
import vazkii.psi.api.spell.SpellRuntimeException;
import vazkii.psi.api.spell.param.ParamEntity;
import vazkii.psi.api.spell.param.ParamNumber;
import vazkii.psi.api.spell.param.ParamVector;
import vazkii.psi.api.spell.piece.PieceSelector;
import vazkii.psi.api.spell.wrapper.EntityListWrapper;
import vazkii.psi.common.lib.LibPieceGroups;
import vazkii.psi.common.spell.selector.entity.PieceSelectorNearby;

import java.util.List;
import java.util.Objects;

// Inputs Entity, String
public class PieceSelectorNameTag extends PieceSelector {
    public final static String NAME = "selector_nametag";
    public final static String GROUP = LibPieceGroups.ENTITIES_INTRO;

    private SpellParam name;
    private SpellParam radius;
    private SpellParam position;

    public PieceSelectorNameTag(Spell spell) {
        super(spell);
    }

    @Override
    public void initParams() {
        super.initParams();

        addParam(position = new ParamVector(SpellParam.GENERIC_NAME_POSITION, SpellParam.BLUE, false, false));
        addParam(name = new ParamString("extrapsi.spellparam.name", SpellParam.RED, false, true));
        addParam(radius = new ParamNumber(SpellParam.GENERIC_NAME_RADIUS, SpellParam.GREEN, false, true));

    }

    @Override
    public Class<?> getEvaluationType() {
        return EntityListWrapper.class;
    }

    @Override
    public Object execute(SpellContext context) throws SpellRuntimeException {
        double radiusValue = getParamValue(context, radius);

        String nameValue = getParamValue(context, name);

        Vector3 posValue = getParamValue(context, position);

        AxisAlignedBB aabb = new AxisAlignedBB(posValue.x - radiusValue, posValue.y - radiusValue, posValue.z - radiusValue,
                                               posValue.x + radiusValue, posValue.y + radiusValue, posValue.z + radiusValue);

        List<Entity> entities = context.caster.world.getEntitiesWithinAABB(Entity.class, aabb,
                (e) -> e != null && e != context.caster && e != context.focalPoint &&
                       context.isInRadius(e) && e.getName().equals(nameValue));

        return new EntityListWrapper(entities);
    }
}
