///*
// * The MIT License (MIT)
// *
// * Copyright (c) GoodTimeStudio <http://mcgoodtime.com>
// * Copyright (c) contributors
// *
// * Permission is hereby granted, free of charge, to any person obtaining a copy
// * of this software and associated documentation files (the "Software"), to deal
// * in the Software without restriction, including without limitation the rights
// * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
// * copies of the Software, and to permit persons to whom the Software is
// * furnished to do so, subject to the following conditions:
// *
// * The above copyright notice and this permission notice shall be included in
// * all copies or substantial portions of the Software.
// *
// * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
// * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
// * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
// * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
// * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
// * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
// * THE SOFTWARE.
// */
//package com.mcgoodtime.productionline.common.entity.ai;
//
//import com.mcgoodtime.productionline.common.init.PLBlocks;
//import net.minecraft.block.Block;
//import net.minecraft.entity.EntityCreature;
//import net.minecraft.entity.ai.EntityAIBase;
//import net.minecraft.init.Blocks;
//import net.minecraft.pathfinding.PathEntity;
//import net.minecraft.pathfinding.PathNavigate;
//import net.minecraft.util.MathHelper;
//import net.minecraft.world.World;
//
///**
// * Created by liach on 9/20/2016.
// *
// * @author liach
// */
//public class EntityAiEatWaterHyacinth extends EntityAIBase {
//
//    int targetX;
//    int targetY;
//    int targetZ;
//    int eatCountdown;
//    EntityCreature owner;
//    World world;
//    PathEntity cached;
//    boolean avoidWaterBefore;
//
//    public EntityAiEatWaterHyacinth(EntityCreature owner) {
//        this.owner = owner;
//        world = owner.worldObj;
//        setMutexBits(7);
//    }
//
//    @Override
//    public boolean shouldExecute() {
//        if (owner.getRNG().nextDouble() > 0.3D) {
//            return false;
//        }
//        int x = (int) owner.posX;
//        int y = (int) owner.posY;
//        int z = (int) owner.posZ;
//        for (int i = x - 20; i <= x + 20; i++) {
//            for (int j = y - 10; j <= y + 10; j++) {
//                for (int k = z - 20; k <= z + 20; z++) {
//                    if (world.getBlock(i, j - 1, k) == PLBlocks.waterHyacinth) {
//                        PathNavigate navigator = owner.getNavigator();
//                        return (cached = navigator.getPathToXYZ(i, j, k)) != null;
//                    }
//                }
//            }
//        }
//        return false;
//    }
//
//    @Override
//    public boolean continueExecuting() {
//        return (world.getBlock(targetX, targetY - 1, targetZ) == PLBlocks.waterHyacinth) && eatCountdown > 0;
//    }
//
//    @Override
//    public void updateTask() {
//        PathNavigate navigator = owner.getNavigator();
//        double dis = owner.getDistanceSq(targetX, targetY, targetZ);
//
//        if (dis <= 1D) {
//            navigator.clearPathEntity();
//            this.eatCountdown = Math.max(0, eatCountdown - 1);
//
//            if (eatCountdown == 4)
//            {
//                int i = MathHelper.floor_double(owner.posX);
//                int j = MathHelper.floor_double(owner.posY);
//                int k = MathHelper.floor_double(owner.posZ);
//
//                if (world.getBlock(i, j - 1, k) == PLBlocks.waterHyacinth)
//                {
//                    if (world.getGameRules().getGameRuleBooleanValue("mobGriefing"))
//                    {
//                        world.playAuxSFX(2001, i, j - 1, k, Block.getIdFromBlock(Blocks.grass));
//                        world.setBlock(i, j - 1, k, Blocks.air, 0, 2);
//                    }
//
//                    owner.eatGrassBonus();
//                }
//            }
//        } else {
//            cached = navigator.getPathToXYZ(targetX, targetY, targetZ);
//            navigator.setPath(cached, 1.2D);
//        }
//    }
//
//    @Override
//    public void resetTask() {
//        PathNavigate navigator = owner.getNavigator();
//        navigator.clearPathEntity();
//        navigator.setAvoidsWater(avoidWaterBefore);
//        eatCountdown = 0;
//    }
//
//    @Override
//    public void startExecuting() {
//        if (cached == null) {
//            return;
//        }
//        eatCountdown = 40;
//        world.setEntityState(owner, (byte)10);
//        PathNavigate navigator = owner.getNavigator();
//        navigator.clearPathEntity();
//        avoidWaterBefore = navigator.getAvoidsWater();
//        navigator.setAvoidsWater(false);
//        navigator.setCanSwim(true);
//    }
//}
