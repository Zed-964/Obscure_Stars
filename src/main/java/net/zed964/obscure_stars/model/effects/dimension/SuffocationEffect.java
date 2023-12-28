package net.zed964.obscure_stars.model.effects.dimension;

import lombok.Getter;

import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.AttributeMap;

import net.zed964.obscure_stars.vue.effects.suffocation.SuffocationColor;
import net.zed964.obscure_stars.vue.effects.suffocation.SuffocationFog;

import org.jetbrains.annotations.NotNull;

@Getter
public class SuffocationEffect extends MobEffect {

    private final SuffocationFog suffocationFog;

    private final SuffocationColor suffocationColor;

    public SuffocationEffect(MobEffectCategory pCategory, int pColor, SuffocationFog pSuffocationFog, SuffocationColor pSuffocationColor) {
        super(pCategory, pColor);
        this.suffocationFog = pSuffocationFog;
        this.suffocationColor = pSuffocationColor;
    }


    @Override
    public void applyEffectTick(@NotNull LivingEntity pLivingEntity, int pAmplifier) {
        if (suffocationFog.getIsAppliedFog()) {
            pLivingEntity.hurt(DamageSource.GENERIC, 2);
        }
    }

    @Override
    public void removeAttributeModifiers(@NotNull LivingEntity pLivingEntity, @NotNull AttributeMap pAttributeMap, int pAmplifier) {
        super.removeAttributeModifiers(pLivingEntity, pAttributeMap, pAmplifier);
        suffocationFog.setAllToFalse();
        suffocationColor.setAllToFalse();
    }

    @Override
    public boolean isDurationEffectTick(int pDuration, int pAmplifier) {
        return true;
    }

}
