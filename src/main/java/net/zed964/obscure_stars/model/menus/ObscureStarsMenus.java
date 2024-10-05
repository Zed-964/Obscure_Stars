package net.zed964.obscure_stars.model.menus;

import net.minecraft.world.inventory.MenuType;
import net.minecraftforge.common.extensions.IForgeMenuType;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import net.zed964.obscure_stars.ObscureStars;
import net.zed964.obscure_stars.model.menus.custom.TestMenu;

public class ObscureStarsMenus {

    public static final DeferredRegister<MenuType<?>> MENUS = DeferredRegister.create(ForgeRegistries.MENU_TYPES, ObscureStars.MOD_ID);

    @SuppressWarnings("unused")
    public static final RegistryObject<MenuType<TestMenu>> TEST_MENU = MENUS.register("test_menu",
            () -> IForgeMenuType.create((id, inv, data) -> new TestMenu(id, inv)));

    /**
     * Constructeur par défaut
     */
    private ObscureStarsMenus() {

    }

    public static void register(IEventBus eventBus) {
        MENUS.register(eventBus);
    }
}
