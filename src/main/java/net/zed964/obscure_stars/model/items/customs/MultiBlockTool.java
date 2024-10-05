package net.zed964.obscure_stars.model.items.customs;

import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.SimpleMenuProvider;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

import net.zed964.obscure_stars.constants.GuiConstants;
import net.zed964.obscure_stars.model.menus.custom.TestMenu;

import org.jetbrains.annotations.NotNull;


public class MultiBlockTool extends Item {
    public MultiBlockTool(Properties pProperties) {
        super(pProperties);
    }

    @Override
    public @NotNull InteractionResultHolder<ItemStack> use(Level pLevel, @NotNull Player pPlayer, @NotNull InteractionHand pUsedHand) {
        if (!pLevel.isClientSide()) {
            pPlayer.openMenu(new SimpleMenuProvider((id, inventory, p) -> new TestMenu(id, inventory), GuiConstants.TEST_SCREEN_TITLE));
        }
        return super.use(pLevel, pPlayer, pUsedHand);
    }
}
