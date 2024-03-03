package net.zed964.obscure_stars.model.effects.custom;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.AttributeMap;

import net.zed964.obscure_stars.model.capabilities.impl.CustomFogCapImpl;
import net.zed964.obscure_stars.model.capabilities.provider.CustomFogProvider;
import net.zed964.obscure_stars.model.packets.ObscureStarsPackets;
import net.zed964.obscure_stars.model.packets.custom.S2CSyncStatusColorFog;
import net.zed964.obscure_stars.model.packets.custom.S2CSyncStatusFog;
import net.zed964.obscure_stars.vue.fog.custom.SuffocationColor;
import net.zed964.obscure_stars.vue.fog.custom.SuffocationFog;

import org.jetbrains.annotations.NotNull;

/**
 * Class de l'effet Suffocation
 */
public class SuffocationEffect extends MobEffect {

    /**
     * Constructeur par défaut
     * @param pCategory La catégorie de l'effet
     * @param pColor La couleur des particules de l'effet
     */
    public SuffocationEffect(MobEffectCategory pCategory, int pColor) {
        super(pCategory, pColor);
    }

    /**
     * Méthode qui execute ce qui est appliqué à l'entité quand il a l'effet
     * @param pLivingEntity Entité vivante dans le monde
     * @param pAmplifier Amplificateur d'effet (pas utilisé pour cet effet)
     */
    @Override
    public void applyEffectTick(@NotNull LivingEntity pLivingEntity, int pAmplifier) {
        pLivingEntity.getCapability(CustomFogProvider.PLAYER_CUSTOM_FOG).ifPresent(cap -> {
            if (cap.getStatusFog() == CustomFogCapImpl.StatusDirectionCustomFog.OFF) {
                cap.setStatusFog(CustomFogCapImpl.StatusDirectionCustomFog.DECREASE);
                cap.setStatusColor(CustomFogCapImpl.StatusDirectionCustomFog.DECREASE);
                cap.saveNBTData(new CompoundTag());
                ObscureStarsPackets.sendToPlayer(new S2CSyncStatusFog(cap.getStatusFog(), SuffocationFog.getInstance()), (ServerPlayer) pLivingEntity);
                ObscureStarsPackets.sendToPlayer(new S2CSyncStatusColorFog(cap.getStatusColor(), SuffocationColor.getInstance()), (ServerPlayer) pLivingEntity);

            } else if (cap.getStatusFog() == CustomFogCapImpl.StatusDirectionCustomFog.FINISH) {
                pLivingEntity.hurt(DamageSource.GENERIC, 2);
            }
        });
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

        pLivingEntity.getCapability(CustomFogProvider.PLAYER_CUSTOM_FOG).ifPresent(cap -> {
            cap.setStatusFog(CustomFogCapImpl.StatusDirectionCustomFog.INCREASE);
            cap.setStatusColor(CustomFogCapImpl.StatusDirectionCustomFog.INCREASE);
            cap.saveNBTData(new CompoundTag());
            ObscureStarsPackets.sendToPlayer(new S2CSyncStatusFog(cap.getStatusFog(), SuffocationFog.getInstance()), (ServerPlayer) pLivingEntity);
            ObscureStarsPackets.sendToPlayer(new S2CSyncStatusColorFog(cap.getStatusColor(), SuffocationColor.getInstance()), (ServerPlayer) pLivingEntity);
        });
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
