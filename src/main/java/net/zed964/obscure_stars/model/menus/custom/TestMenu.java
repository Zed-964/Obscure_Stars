package net.zed964.obscure_stars.model.menus.custom;

import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.item.ItemStack;
import net.zed964.obscure_stars.model.menus.ObscureStarsMenus;
import org.jetbrains.annotations.NotNull;

public class TestMenu extends AbstractContainerMenu {

    public TestMenu(int id, Inventory playerInventory) {
        super(ObscureStarsMenus.TEST_MENU.get(), id);
    }

    @Override
    public @NotNull ItemStack quickMoveStack(@NotNull Player pPlayer, int pIndex) {
        return ItemStack.EMPTY;
    }

    @Override
    public boolean stillValid(@NotNull Player pPlayer) {
        return true;
    }
}
