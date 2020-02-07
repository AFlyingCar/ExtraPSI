package com.tgd.xpsi.spells.constants;

import com.tgd.xpsi.ExtraPSIConfig;

import org.lwjgl.input.Keyboard;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.nbt.NBTTagCompound;
import vazkii.psi.api.spell.*;
import vazkii.psi.common.Psi;
import vazkii.psi.common.lib.LibPieceGroups;

public class PieceConstantString extends SpellPiece {
    public static final String NAME = "constant_string";
    public static final String GROUP = LibPieceGroups.TUTORIAL_1;


    private static final String TAG_CONSTANT_VALUE = "constantStringValue";

    private String valueStr;

    public PieceConstantString(Spell spell) {
        super(spell);
    }

    @Override
    public EnumPieceType getPieceType() {
        return EnumPieceType.CONSTANT;
    }

    @Override
    public Class<?> getEvaluationType() {
        return String.class;
    }

    @Override
    public void initParams() {
        super.initParams();

        valueStr = "";
    }

    @Override
    public void drawAdditional() {
        if(valueStr == null || valueStr.length() > ExtraPSIConfig.maxStringLength)
            valueStr = "";

        Minecraft mc = Minecraft.getMinecraft();
        int color = Psi.magical ? 0 : 0xFFFFFF;
        float efflen = mc.fontRenderer.getStringWidth(valueStr);
        float scale = 1;

        while(efflen > 16) {
            ++scale;
            efflen = mc.fontRenderer.getStringWidth(valueStr) / scale;
        }

        GlStateManager.pushMatrix();
        GlStateManager.scale(1F / scale, 1F / scale, 1F);
        GlStateManager.translate((9 - efflen / 2) * scale, 4 * scale, 0);
        mc.fontRenderer.drawString(valueStr, 0, 0, color);
        GlStateManager.popMatrix();
    }

    @Override
    public boolean interceptKeystrokes() {
        return true;
    }

    @Override
    public boolean onKeyPressed(char c, int i, boolean doit) {
        String oldStr = valueStr;
        String newStr = valueStr;

        if(i == Keyboard.KEY_BACK) {
            if(newStr.isEmpty())
                return false;

            newStr = newStr.substring(0, newStr.length() - 1);
        } else if(i == Keyboard.KEY_DELETE || i == Keyboard.KEY_RETURN || i == Keyboard.KEY_UP ||
                  i == Keyboard.KEY_LEFT || i == Keyboard.KEY_RIGHT || i == Keyboard.KEY_DOWN ||
                  i == Keyboard.KEY_INSERT || i == Keyboard.KEY_HOME || i == Keyboard.KEY_LCONTROL ||
                  i == Keyboard.KEY_RCONTROL || i == Keyboard.KEY_LSHIFT || i == Keyboard.KEY_RSHIFT ||
                  i == Keyboard.KEY_LMENU || i == Keyboard.KEY_RMENU || i == Keyboard.KEY_LMETA ||
                  i == Keyboard.KEY_RMETA || i == Keyboard.KEY_NUMPADENTER || i == Keyboard.KEY_NUMLOCK ||
                  i == Keyboard.KEY_SCROLL || i == Keyboard.KEY_PAUSE || i == Keyboard.KEY_SYSRQ ||
                 (i >= Keyboard.KEY_F1 && i <= Keyboard.KEY_F10) || (i >= Keyboard.KEY_F11 && i <= Keyboard.KEY_F19))
        {
            return false;
        } else {
            newStr += c;
        }

        if(newStr.length() > ExtraPSIConfig.maxStringLength)
            return false;

        if(doit)
            valueStr = newStr;

        return !newStr.equals(oldStr);
    }

    @Override
    public void readFromNBT(NBTTagCompound cmp) {
        super.readFromNBT(cmp);

        valueStr = cmp.getString(TAG_CONSTANT_VALUE);
    }

    @Override
    public void writeToNBT(NBTTagCompound cmp) {
        super.writeToNBT(cmp);

        cmp.setString(TAG_CONSTANT_VALUE, valueStr);
    }

    @Override
    public Object evaluate() {
        if(valueStr == null || valueStr.length() > ExtraPSIConfig.maxStringLength)
            return "";

        return valueStr;
    }

    @Override
    public Object execute(SpellContext context) throws SpellRuntimeException {
        return evaluate();
    }
}
