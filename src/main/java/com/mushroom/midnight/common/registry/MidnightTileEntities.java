package com.mushroom.midnight.common.registry;

import com.mushroom.midnight.common.tile.base.CacheTileEntity;
import com.mushroom.midnight.common.tile.base.MidnightChestTileEntity;
import com.mushroom.midnight.common.tile.base.MidnightFurnaceTileEntity;
import net.minecraft.tileentity.TileEntityType;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.ObjectHolder;

import static com.mushroom.midnight.Midnight.MODID;

@ObjectHolder(MODID)
@Mod.EventBusSubscriber(modid = MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class MidnightTileEntities {
    public static final TileEntityType<?> MIDNIGHT_CHEST = TileEntityType.CHEST;
    public static final TileEntityType<?> MIDNIGHT_FURNACE = TileEntityType.FURNACE;
    public static final TileEntityType<?> CACHE = TileEntityType.CHEST;

    @SubscribeEvent
    public static void registerTileEntity(final RegistryEvent.Register<TileEntityType<?>> event) {
        event.getRegistry().registerAll(
                TileEntityType.Builder.create(MidnightChestTileEntity::new,
                        MidnightBlocks.SHADOWROOT_CHEST, MidnightBlocks.DEAD_WOOD_CHEST, MidnightBlocks.DARK_WILLOW_CHEST,
                        MidnightBlocks.NIGHTSHROOM_CHEST, MidnightBlocks.DEWSHROOM_CHEST, MidnightBlocks.VIRIDSHROOM_CHEST, MidnightBlocks.BOGSHROOM_CHEST
                ).build(null).setRegistryName(MODID, "midnight_chest"),
                TileEntityType.Builder.create(MidnightFurnaceTileEntity::new, MidnightBlocks.NIGHTSTONE_FURNACE).build(null).setRegistryName(MODID, "midnight_furnace"),
                TileEntityType.Builder.create(CacheTileEntity::new, MidnightBlocks.VIRIDSHROOM_STEM_CACHE).build(null).setRegistryName(MODID, "cache")
        );
    }
}
