package com.mushroom.midnight.common.biome.surface;

import com.mushroom.midnight.Midnight;
import com.mushroom.midnight.common.biome.MidnightBiomeConfigurator;
import com.mushroom.midnight.common.biome.MidnightSurfaceBuilders;
import com.mushroom.midnight.common.registry.MidnightEntities;
import net.minecraft.world.biome.Biome;

public class WarpedFieldsBiome extends SurfaceBiome {
    public WarpedFieldsBiome() {
        super(new Properties()
                .surfaceBuilder(MidnightSurfaceBuilders.SURFACE, MidnightSurfaceBuilders.GRASS_DIRT_MUD_CONFIG)
                .category(Category.FOREST)
                .depth(0.1F)
                .scale(0.8F)
                .densityScale(0.5F)
                .ridgeWeight(0.0F)
        );

        MidnightBiomeConfigurator.addStructureFeatures(this);
        MidnightBiomeConfigurator.addGlobalOres(this);

        MidnightBiomeConfigurator.addLumen(this);
        MidnightBiomeConfigurator.addSmallFungis(this);
        MidnightBiomeConfigurator.addGrasses(this);

        MidnightBiomeConfigurator.addSparseShadowrootTrees(this);

        MidnightBiomeConfigurator.addGlobalFeatures(this);

        MidnightBiomeConfigurator.addStandardCreatureSpawns(this);
        MidnightBiomeConfigurator.addStandardMonsterSpawns(this);

        this.add(Midnight.MIDNIGHT_MOB, new Biome.SpawnListEntry(MidnightEntities.HUNTER, 5, 0, 2));
    }
}
