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

/**
 * Class de l'effet Suffocation
 */
@Getter
public class SuffocationEffect extends MobEffect {

    private final SuffocationFog suffocationFog;

    private final SuffocationColor suffocationColor;

    /**
     * Constructeur par défaut
     * @param pCategory La catégorie de l'effet
     * @param pColor La couleur des particules de l'effet
     * @param pSuffocationFog L'état du brouillard qu'applique l'effet
     * @param pSuffocationColor L'état de la couleur du brouillard qu'applique l'effet
     */
    public SuffocationEffect(MobEffectCategory pCategory, int pColor, SuffocationFog pSuffocationFog, SuffocationColor pSuffocationColor) {
        super(pCategory, pColor);
        this.suffocationFog = pSuffocationFog;
        this.suffocationColor = pSuffocationColor;
    }

    /**
     * Méthode qui execute ce qui est appliqué à l'entité quand il a l'effet
     * @param pLivingEntity Entité vivante dans le monde
     * @param pAmplifier Amplificateur d'effet (pas utilisé pour cet effet)
     */
    @Override
    public void applyEffectTick(@NotNull LivingEntity pLivingEntity, int pAmplifier) {
        if (suffocationFog.getIsAppliedFog()) {
            pLivingEntity.hurt(DamageSource.GENERIC, 2);
        }
    }

    /**
     * Méthode qui execute ce qui se passe quand l'effet est retiré d'une entité
     * @param pLivingEntity Entité vivante dans le monde
     * @param pAttributeMap Attributs de l'entité
     * @param pAmplifier Amplificateur d'effet (pas utilisé pour cet effet)
     */
    @Override
    public void removeAttributeModifiers(@NotNull LivingEntity pLivingEntity, @NotNull AttributeMap pAttributeMap, int pAmplifier) {
        super.removeAttributeModifiers(pLivingEntity, pAttributeMap, pAmplifier);
        suffocationFog.setAllToFalse();
        suffocationColor.setAllToFalse();
    }

    /**
     * Méthode qui vérifie que l'effet est applicable ce tick
     * @param pDuration Durée de l'effet
     * @param pAmplifier Amplificateur de l'effet (pas utilisé pour cet effet)
     * @return Vrai
     */
    @Override
    public boolean isDurationEffectTick(int pDuration, int pAmplifier) {
        return true;
    }

}
