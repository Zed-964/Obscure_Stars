package net.zed964.obscure_stars.model.capabilities.impl;

import net.minecraft.nbt.CompoundTag;

import net.zed964.obscure_stars.constants.CapabilitiesConstants;
import net.zed964.obscure_stars.model.capabilities.CustomFogCap;

public class CustomFogCapImpl implements CustomFogCap {

    public enum StateAnimationCustomFog {
        INACTIVE,
        STARTING,
        APPEARING,
        DISAPPEARING,
        COMPLETE
    }

    public enum CustomFogEffect {
        SUFFOCATION
    }

    private StateAnimationCustomFog stateAnimationFog;

    private StateAnimationCustomFog stateAnimationFogColor;

    public CustomFogCapImpl() {
        this.stateAnimationFog = StateAnimationCustomFog.INACTIVE;
        this.stateAnimationFogColor = StateAnimationCustomFog.INACTIVE;
    }

    @Override
    public void saveNBTData(CompoundTag nbt) {
        nbt.putString(CapabilitiesConstants.CUSTOM_FOG_KEY, this.stateAnimationFog.toString());
        nbt.putString(CapabilitiesConstants.CUSTOM_FOG_COLOR_KEY, this.stateAnimationFogColor.toString());
    }

    @Override
    public void loadNBTData(CompoundTag nbt) {
        String fogStatus = nbt.getString(CapabilitiesConstants.CUSTOM_FOG_KEY);
        this.stateAnimationFog = StateAnimationCustomFog.valueOf(fogStatus);

        String colorStatus = nbt.getString(CapabilitiesConstants.CUSTOM_FOG_COLOR_KEY);
        this.stateAnimationFogColor = StateAnimationCustomFog.valueOf(colorStatus);
    }

    @Override
    public StateAnimationCustomFog getStateAnimationFog() {
        return this.stateAnimationFog;
    }

    @Override
    public void setStateAnimationFog(StateAnimationCustomFog stateAnimationFog) {
        this.stateAnimationFog = stateAnimationFog;
    }

    @Override
    public StateAnimationCustomFog getStateAnimationFogColor() {
        return this.stateAnimationFogColor;
    }

    @Override
    public void setStateAnimationFogColor(StateAnimationCustomFog stateAnimationFogColor) {
        this.stateAnimationFogColor = stateAnimationFogColor;
    }
}