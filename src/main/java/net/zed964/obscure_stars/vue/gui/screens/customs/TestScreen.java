package net.zed964.obscure_stars.vue.gui.screens.customs;

import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Axis;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.components.Button;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.client.gui.screens.inventory.MenuAccess;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.block.BlockRenderDispatcher;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

import net.zed964.obscure_stars.ObscureStars;
import net.zed964.obscure_stars.model.menus.custom.TestMenu;

import org.jetbrains.annotations.NotNull;

@OnlyIn(Dist.CLIENT)
public class TestScreen extends AbstractContainerScreen<TestMenu> implements MenuAccess<TestMenu> {

    private static final Component buttonTitle = Component.translatable("gui." + ObscureStars.MOD_ID + "test_screen.test_button");

    private static final ResourceLocation TEXTURE = new ResourceLocation(ObscureStars.MOD_ID, "textures/gui/test_screen");

    private final int imageWidth;
    private final int imageHeight;

    private int leftPos;
    private int topPos;

    private Button button;

    public TestScreen(TestMenu menu, Inventory inventory, Component title) {
        super(menu, inventory, title);

        this.imageWidth = 176;
        this.imageHeight = 166;
    }

//    @Override
//    protected void init() {
//        super.init();
//
//        this.leftPos = (this.width - this.imageWidth) / 2;
//        this.topPos = (this.height - this.imageHeight) / 2;
//
//        this.button = addRenderableWidget(Button.builder(buttonTitle, this::testLogicOnPress)
//                .bounds(this.leftPos + 8, this.topPos + 8, 20, 20)
//                .build());
//    }

    @Override
    public void render(@NotNull PoseStack graphics, int pMouseX, int pMouseY, float delta) {
        this.renderBackground(graphics);
        super.render(graphics, pMouseX, pMouseY, delta);
        this.renderTooltip(graphics, pMouseX, pMouseY);

    }

    @Override
    protected void renderBg(@NotNull PoseStack pPoseStack, float pPartialTick, int pMouseX, int pMouseY) {
        // On rend l'arrière-plan du menu (peut-être une image de fond simple)
        RenderSystem.setShaderTexture(0, TEXTURE);
        this.blit(pPoseStack, this.leftPos, this.topPos, 0, 0, this.imageWidth, this.imageHeight);

        // On appelle la méthode pour rendre le bloc de dirt
        renderBlockInGUI(pPoseStack, Blocks.DIRT.defaultBlockState(), this.leftPos + 88, this.topPos + 60);
    }

    private void renderBlockInGUI(PoseStack poseStack, BlockState blockState, int x, int y) {
        Minecraft mc = Minecraft.getInstance();
        MultiBufferSource.BufferSource buffer = mc.renderBuffers().bufferSource();

        poseStack.pushPose();
        poseStack.translate(x, y, 100); // Position du bloc dans la GUI
        poseStack.scale(30.0F, 30.0F, 30.0F); // Ajuster la taille du bloc

        // Appliquer une légère rotation pour bien voir le bloc
        poseStack.mulPose(Axis.XP.rotationDegrees(30.0F));
        poseStack.mulPose(Axis.YP.rotationDegrees(45.0F));

        // Rendu du bloc
        BlockRenderDispatcher dispatcher = mc.getBlockRenderer();
        dispatcher.renderSingleBlock(blockState, poseStack, buffer, 15728880, OverlayTexture.NO_OVERLAY);

        buffer.endBatch();
        poseStack.popPose();
    }
}
