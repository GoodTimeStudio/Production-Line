//package com.mcgoodtime.productionline.common.entity;
//
//import net.minecraft.entity.EntityLivingBase;
//import net.minecraft.entity.projectile.EntityArrow;
//import net.minecraft.world.World;
//
///**
// * Created by BestOwl on 2015.12.10.0010.
// *
// * @author BestOwl
// */
//public class EntityRay extends EntityArrow {
//
//    public EntityRay(World world) {
//        super(world);
//    }
//
//    public EntityRay(World world, double x, double y, double z) {
//        super(world, x, y, z);
//    }
//
//    public EntityRay(World world, EntityLivingBase entityLivingBase, float damage) {
//        super(world, entityLivingBase, damage);
//        this.canBePickedUp = 0;
//    }
//
//    public EntityRay(World world, EntityLivingBase entityLivingBase, EntityLivingBase entityLivingBase1, float f, float f1) {
//        super(world, entityLivingBase, entityLivingBase1, f, f1);
//    }
//
//    /**
//     * Called to update the entity's position/logic.
//     */
//    @Override
//    public void onUpdate() {
//        super.onUpdate();
////        if (this.inGround) {
////            this.setDead();
////        }
//    }
//}
