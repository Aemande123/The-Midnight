package com.mushroom.midnight.common.item;

import com.mushroom.midnight.common.entity.projectile.BladeshroomCapEntity;
import com.mushroom.midnight.common.registry.MidnightCriterion;
import com.mushroom.midnight.common.registry.MidnightSounds;
import net.minecraft.block.DispenserBlock;
import net.minecraft.dispenser.IPosition;
import net.minecraft.dispenser.ProjectileDispenseBehavior;
import net.minecraft.entity.IProjectile;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.stats.Stats;
import net.minecraft.util.ActionResult;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.CooldownTracker;
import net.minecraft.util.Hand;
import net.minecraft.util.SoundCategory;
import net.minecraft.world.World;

public class BladeshroomCapItem extends Item {
    public BladeshroomCapItem(Item.Properties properties) {
        super(properties);
        DispenserBlock.registerDispenseBehavior(this, new DispenserBehavior());
    }

    @Override
    public ActionResult<ItemStack> onItemRightClick(World world, PlayerEntity player, Hand hand) {
        CooldownTracker cd = player.getCooldownTracker();
        if (cd.hasCooldown(this)) {
            return super.onItemRightClick(world, player, hand);
        }

        cd.setCooldown(this, 10);

        ItemStack heldItem = player.getHeldItem(hand);
        if (!player.abilities.isCreativeMode) {
            heldItem.shrink(1);
        }

        world.playSound(null, player.posX, player.posY, player.posZ, MidnightSounds.BLADESHROOM_CAP_SHOOT, SoundCategory.NEUTRAL, 0.5F, 0.4F / (random.nextFloat() * 0.4F + 0.8F));

        if (!world.isRemote) {
            BladeshroomCapEntity entity = new BladeshroomCapEntity(world, player);
            entity.shoot(player, player.rotationPitch, player.rotationYaw, 0.0F, 1.5F, 1.0F);
            if (player.abilities.isCreativeMode) {
                entity.pickupStatus = BladeshroomCapEntity.PickupStatus.CREATIVE_ONLY;
            }

            world.addEntity(entity);

            if (player instanceof ServerPlayerEntity) {
                MidnightCriterion.THROWN_BLADESHROOM.trigger((ServerPlayerEntity) player);
            }
        }

        player.addStat(Stats.ITEM_USED.get(this));

        return new ActionResult<>(ActionResultType.SUCCESS, heldItem);
    }

    private static class DispenserBehavior extends ProjectileDispenseBehavior {
        @Override
        protected IProjectile getProjectileEntity(World world, IPosition pos, ItemStack stack) {
            BladeshroomCapEntity cap = new BladeshroomCapEntity(world, pos.getX(), pos.getY(), pos.getZ());
            cap.pickupStatus = BladeshroomCapEntity.PickupStatus.ALLOWED;
            return cap;
        }
    }
}
