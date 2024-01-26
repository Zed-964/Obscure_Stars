package net.zed964.obscure_stars.model.dimensions;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.zed964.obscure_stars.ObscureStarsFileConfig;

public class SpawnCustom {
    private SpawnCustom() {

    }

    public static void verifySpawn(PlayerEvent.PlayerLoggedInEvent event) {

        Level level = event.getEntity().level;

        int posX = event.getEntity().getBlockX();
        int posY = event.getEntity().getBlockY();
        int posZ = event.getEntity().getBlockZ();

        BlockPos positionBlockPlayer = new BlockPos(posX, posY, posZ);

        if (Boolean.TRUE.equals(ObscureStarsFileConfig.ALLOWED_SPAWN_WITH_STRUCTURE_CUSTOM.get())) {
            BlockPos posGround = positionBlockPlayer.mutable().setY(positionBlockPlayer.getY() - 1);

            if (event.getEntity().level.getBlockState(posGround).isAir()) {
                place3x3BlockInSpecificLevel(level, posGround);
            }

            remove3x3BlockInSpecificLevel(level, positionBlockPlayer);
        }
    }

    private static void place3x3BlockInSpecificLevel(Level levelToAct, BlockPos middlePos) {
        levelToAct.setBlock(middlePos, Blocks.OBSIDIAN.defaultBlockState(), 1);
        BlockPos posNorth = middlePos.north();
        levelToAct.setBlock(posNorth, Blocks.OBSIDIAN.defaultBlockState(), 1);
        BlockPos posNorthEast = posNorth.east();
        levelToAct.setBlock(posNorthEast, Blocks.OBSIDIAN.defaultBlockState(), 1);
        BlockPos posEast = posNorthEast.south();
        levelToAct.setBlock(posEast, Blocks.OBSIDIAN.defaultBlockState(), 1);
        BlockPos posSouthEast = posEast.south();
        levelToAct.setBlock(posSouthEast, Blocks.OBSIDIAN.defaultBlockState(), 1);
        BlockPos posSouth = posSouthEast.west();
        levelToAct.setBlock(posSouth, Blocks.OBSIDIAN.defaultBlockState(), 1);
        BlockPos posSouthWest = posSouth.west();
        levelToAct.setBlock(posSouthWest, Blocks.OBSIDIAN.defaultBlockState(), 1);
        BlockPos posWest = posSouthWest.north();
        levelToAct.setBlock(posWest, Blocks.OBSIDIAN.defaultBlockState(), 1);
        BlockPos posNorthWest = posWest.north();
        levelToAct.setBlock(posNorthWest, Blocks.OBSIDIAN.defaultBlockState(), 1);
    }

    private static void remove3x3BlockInSpecificLevel(Level levelToRemoveBlock, BlockPos middlePos) {

        int i = 0;

        do {
            levelToRemoveBlock.removeBlock(middlePos, false);
            BlockPos posNorth = middlePos.north();
            levelToRemoveBlock.removeBlock(posNorth, false);
            BlockPos posNorthEast = posNorth.east();
            levelToRemoveBlock.removeBlock(posNorthEast, false);
            BlockPos posEast = posNorthEast.south();
            levelToRemoveBlock.removeBlock(posEast, false);
            BlockPos posSouthEast = posEast.south();
            levelToRemoveBlock.removeBlock(posSouthEast, false);
            BlockPos posSouth = posSouthEast.west();
            levelToRemoveBlock.removeBlock(posSouth, false);
            BlockPos posSouthWest = posSouth.west();
            levelToRemoveBlock.removeBlock(posSouthWest, false);
            BlockPos posWest = posSouthWest.north();
            levelToRemoveBlock.removeBlock(posWest, false);
            BlockPos posNorthWest = posWest.north();
            levelToRemoveBlock.removeBlock(posNorthWest, false);

            middlePos = middlePos.mutable().setY(middlePos.getY() + 1);
            i++;

        } while (i < 3);
    }
}
