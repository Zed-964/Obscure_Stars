package net.zed964.obscure_stars.constants;

import net.minecraft.network.chat.Component;
import net.zed964.obscure_stars.ObscureStars;

public class GuiConstants {

    private static final String GUI_KEY = "gui";

    private static final String DOT = ".";

    public static final Component TEST_SCREEN_TITLE = Component.translatable(GUI_KEY + DOT + ObscureStars.MOD_ID + DOT + "test_screen");

    /**
     * Constructeur privé par défault
     */
    private GuiConstants() {

    }
}
