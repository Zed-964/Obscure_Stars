package net.zed964.obscure_stars.model.capabilities.impl;

import net.minecraft.nbt.CompoundTag;

import net.zed964.obscure_stars.constants.CapabilitiesConstants;
import net.zed964.obscure_stars.model.capabilities.CustomFogCap;

public  class CustomFogCapImpl implements CustomFogCap {

    public enum StatusDirectionCustomFog {
        OFF,
        DECREASE,
        INCREASE,
        FINISH
    }

    private StatusDirectionCustomFog statusFog;

    private StatusDirectionCustomFog statusColor;

    public CustomFogCapImpl() {
        this.statusFog = StatusDirectionCustomFog.OFF;
        this.statusColor = StatusDirectionCustomFog.OFF;
    }

    @Override
    public void saveNBTData(CompoundTag nbt) {
        nbt.putString(CapabilitiesConstants.CUSTOM_FOG_KEY, this.statusFog.toString());
        nbt.putString(CapabilitiesConstants.CUSTOM_FOG_COLOR_KEY, this.statusColor.toString());
    }

    @Override
    public void loadNBTData(CompoundTag nbt) {
        String fogStatus = nbt.getString(CapabilitiesConstants.CUSTOM_FOG_KEY);
        this.statusFog = StatusDirectionCustomFog.valueOf(fogStatus);

        String colorStatus = nbt.getString(CapabilitiesConstants.CUSTOM_FOG_COLOR_KEY);
        this.statusColor = StatusDirectionCustomFog.valueOf(colorStatus);
    }

    @Override
    public StatusDirectionCustomFog getStatusFog() {
        return this.statusFog;
    }

    @Override
    public void setStatusFog(StatusDirectionCustomFog statusFog) {
        this.statusFog = statusFog;
    }

    @Override
    public StatusDirectionCustomFog getStatusColor() {
        return this.statusColor;
    }

    @Override
    public void setStatusColor(StatusDirectionCustomFog statusColor) {
        this.statusColor = statusColor;
    }
}