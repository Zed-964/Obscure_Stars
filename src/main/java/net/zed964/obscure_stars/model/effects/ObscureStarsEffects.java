package net.zed964.obscure_stars.model.effects;

import lombok.extern.slf4j.Slf4j;

import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

import net.minecraftforge.registries.RegistryObject;
import net.zed964.obscure_stars.ObscureStars;
import net.zed964.obscure_stars.model.effects.dimension.TestEffects;

@Slf4j
public class ObscureStarsEffects {

    public static final DeferredRegister<MobEffect> MOB_EFFECTS = DeferredRegister.create(ForgeRegistries.MOB_EFFECTS, ObscureStars.MOD_ID);

    public static final RegistryObject<MobEffect> TEST_EFFECT = MOB_EFFECTS.register("test", () -> new TestEffects(MobEffectCategory.HARMFUL, 0));

    private ObscureStarsEffects() {

    }

    public static void register(IEventBus eventBus) {
        log.info("Effects from Obscure Stars registries !");
        MOB_EFFECTS.register(eventBus);
    }
}
